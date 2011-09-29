package org.openiam.webadmin.policy.attr;

/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the Lesser GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.webadmin.util.FileIO;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.CancellableFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Controller for the attribute policy form
 * 
 * @author suneet
 * 
 */
public class AttributePolicyController extends CancellableFormController {

	// Name of directory into which to store rules with no script path
	private final String DEFAULT_SCRIPT_DIR = "prov-default";

	private static final Log log = LogFactory
			.getLog(AttributePolicyController.class);

	PolicyDataService policyDataService;

	public AttributePolicyController() {
		super();
	}

	@Override
	protected ModelAndView onCancel(Object command) throws Exception {
		return new ModelAndView(new RedirectView(getCancelView(), true));
	}

	/*
	 * Save the attribute's rule to a groovy script specified by its path. If
	 * the path is invalid or not specified, create a groovy script in a default
	 * location and update path.
	 */
	private void saveRuleToFile(AttributePolicyCommand pc) {
		if (pc.getRule() != null && !pc.getRule().trim().isEmpty()) {
			String updated_file_path = null;

			File dest = null; // Will contain final destination file

			// >>>> Try and find a valid destination for storing the script
			// Get base address
			String base = FileIO.getScriptFolderBasePath();

			// Check associated rule path
			String pc_pth = pc.getRuleSrcUrl();
			if (pc_pth != null && !pc_pth.trim().isEmpty()) {
				// Path specified; test path validity
				File test_rule = new File(base, pc_pth);

				if (test_rule.exists() && test_rule.canWrite()) {
					dest = test_rule;
					updated_file_path = pc_pth;
				}
			}

			// Set default path
			if (dest == null) {
				File default_dir = new File(base, DEFAULT_SCRIPT_DIR);

				if (!default_dir.isDirectory())
					default_dir.mkdir();

				// Get new unique file name for default file
				String newname = FileIO.getUniqueFilename(default_dir,
						pc.getName(), "groovy");

				dest = new File(default_dir, newname);
				updated_file_path = new File(DEFAULT_SCRIPT_DIR, newname)
						.getPath();
			}

			// >>>> Destination set, save file and update path on success
			if (FileIO.saveTextFile(dest, pc.getRule()))
				pc.setRuleSrcUrl(updated_file_path);
		}
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		AttributePolicyCommand attrPolicyCommand = null;

		// load the policy in the event of a view operation
		String policyId = request.getParameter("policyId");
		if (policyId != null) {
			Policy policy = policyDataService.getPolicy(policyId);
			attrPolicyCommand = policyToCommand(policy);

			// Populate rule box with text from file
			String path = attrPolicyCommand.getRuleSrcUrl();
			if (path != null && !path.isEmpty()) {
				// Load rule from file
				String base = FileIO.getScriptFolderBasePath();
				StringBuffer sb = FileIO.loadTextFile(new File(base, path));
				if (sb != null)
					attrPolicyCommand.setRule(sb.toString());
			}
		} else {
			attrPolicyCommand = new AttributePolicyCommand();
		}

		return attrPolicyCommand;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		AttributePolicyCommand attrPolicyCommand = (AttributePolicyCommand) command;

		// check which button was clicked
		String btn = request.getParameter("btn");

		if (btn != null) {
			if (btn.equalsIgnoreCase("Delete")) {
				// Delete
				Policy plcy = commandToPolicy(attrPolicyCommand);
				policyDataService.removePolicy(plcy.getPolicyId());
				
			} else if (btn.equalsIgnoreCase("Reload")) {
				// Reload

				
			} else {
				// Submit

				// Save the policy text to file; if no file specified, create a default
				saveRuleToFile(attrPolicyCommand);

				Policy plcy = commandToPolicy(attrPolicyCommand);
				if (plcy.getPolicyId() == null || plcy.getPolicyId().length() == 0) {
					// new
					policyDataService.addPolicy(plcy);
				} else {
					// update
					policyDataService.updatePolicy(plcy);
				}
			}

		}

		// get the new policy list to show on the confirmation page
		List<Policy> policyAry = policyDataService
				.getAllPolicies(attrPolicyCommand.getPolicyDefId());

		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("attrPolicyCmd", attrPolicyCommand);
		mav.addObject("confmsg", "Information was successfully saved");
		mav.addObject("policyAry", policyAry);

		return mav;
	}

	private Policy commandToPolicy(AttributePolicyCommand cmd) {
		Policy plcy = new Policy();

		plcy.setPolicyId(cmd.getPolicyPKId());
		if (cmd.getPolicyId() == null || cmd.getPolicyId().length() == 0) {
			plcy.setCreateDate(new Date(System.currentTimeMillis()));
		}
		plcy.setLastUpdate(new Date(System.currentTimeMillis()));
		plcy.setName(cmd.getName());
		plcy.setDescription(cmd.getDescription());
		plcy.setPolicyDefId(cmd.getPolicyDefId());
		plcy.setRule(cmd.getRule());
		plcy.setStatus(cmd.getStatus());
		if (cmd.getRuleSrcUrl() != null && cmd.getRuleSrcUrl().length() == 0) {
			plcy.setRuleSrcUrl(null);
		} else {
			plcy.setRuleSrcUrl(cmd.getRuleSrcUrl());
		}

		return plcy;
	}

	private AttributePolicyCommand policyToCommand(Policy policy) {
		AttributePolicyCommand attrPolicyCommand = new AttributePolicyCommand();

		attrPolicyCommand.setPolicyPKId(policy.getPolicyId());
		attrPolicyCommand.setCreateDate(policy.getCreateDate());
		attrPolicyCommand.setDescription(policy.getDescription());
		attrPolicyCommand.setName(policy.getName());
		attrPolicyCommand.setPolicyDefId(policy.getPolicyDefId());
		attrPolicyCommand.setRule(policy.getRule());
		attrPolicyCommand.setStatus(policy.getStatus());
		attrPolicyCommand.setRuleSrcUrl(policy.getRuleSrcUrl());

		return attrPolicyCommand;
	}

	public PolicyDataService getPolicyDataService() {
		return policyDataService;
	}

	public void setPolicyDataService(PolicyDataService policyDataService) {
		this.policyDataService = policyDataService;
	}

}
