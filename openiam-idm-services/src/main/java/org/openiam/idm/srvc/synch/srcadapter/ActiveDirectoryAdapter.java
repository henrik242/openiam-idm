/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
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

/**
 * 
 */
package org.openiam.idm.srvc.synch.srcadapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVStrategy;
import org.openiam.idm.srvc.audit.service.AuditHelper;
import org.openiam.idm.srvc.synch.dto.Attribute;
import org.openiam.idm.srvc.synch.dto.LineObject;

/**
 * Scan AD for any new records, changed users, or delete operations and then synchronizes them back into OpenIAM.
 * @author suneet
 *
 */
public class ActiveDirectoryAdapter {

	protected Map<String,LineObject> lineMap = new HashMap<String,LineObject>();
	protected LineObject lineHeader = new LineObject();
	protected AuditHelper auditHelper;
	
	public void startSynch(String fileName) {
		Reader reader = null;
	
		
		File file = new File(fileName);
		try {
			reader = new FileReader(file);
		}catch(FileNotFoundException fe) {
			fe.printStackTrace();
		}
		System.out.println("Setting up the file parser...");
		
		CSVParser parser = new CSVParser(reader, CSVStrategy.EXCEL_STRATEGY);
		try {
			int ctr = 0;
			String[][] fileContentAry =   parser.getAllValues();
			int size = fileContentAry.length;
			
			System.out.println("file size=..." + size);
			
			for (String[] lineAry : fileContentAry) {
				System.out.println("line= " + lineAry[0]);
				if (ctr == 0) {
					populateTemplate(lineAry);
					ctr++;
				}else {
					//populate the data object
					LineObject rowObj = lineHeader.copy();
					populateRowObject(rowObj, lineAry);
					
					System.out.println("rowObj =" + rowObj);
					
					// call the following
					// validation script - if it fails then put it in the exception table
					// transformation script
					// synchronize the call - via jms
						
				}
			
			}
			
		}catch(IOException io) {
			io.printStackTrace();
		}
		
		//while (parser.)
		
	}
	
	private void populateTemplate(String[] lineAry) {
		Map<String,Attribute> columnMap = new HashMap<String, Attribute>();
		
		int ctr =0;
		for (String s  :lineAry) {
			Attribute a = new Attribute(s, null);
			columnMap.put(Integer.toString(ctr),a);
			ctr++;
		}
		lineHeader.setColumnMap(columnMap);
	}

	private void populateRowObject(LineObject rowObj ,String[] lineAry) {
		int ctr = 0;
		for (String value  :lineAry) {
			 rowObj.get(Integer.toString(ctr)).setValue(value);
		}
	}
	public static void main(String[] args) {
		System.out.println("CSVAdapter Test...");
		ActiveDirectoryAdapter adapter = new ActiveDirectoryAdapter();
		adapter.startSynch("C:/Doc/clients/exe.cl/previred/UserList.csv");
		
	}

	public AuditHelper getAuditHelper() {
		return auditHelper;
	}

	public void setAuditHelper(AuditHelper auditHelper) {
		this.auditHelper = auditHelper;
	}
	
}
