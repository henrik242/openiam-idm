package org.openiam.webadmin.res;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteAttributeMapController extends AbstractController {


    private static final Log log = LogFactory.getLog(DeleteAttributeMapController.class);
    protected ResourceDataService resourceDataService;
    protected NavigatorDataWebService navigationDataService;
    private PolicyDataService policyDataService;
    private String redirectView;
    private ManagedSystemDataService managedSysService;


    public DeleteAttributeMapController() {
        super();
    }

    /* (non-Javadoc)
      * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
      */
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest req,
                                                 HttpServletResponse res) throws Exception {

        String attrMapId = req.getParameter("attrMapId");
        managedSysService.removeAttributeMap(attrMapId);

        return new ModelAndView(new RedirectView(redirectView, true));
    }

    public ResourceDataService getResourceDataService() {
        return resourceDataService;
    }

    public void setResourceDataService(ResourceDataService resourceDataService) {
        this.resourceDataService = resourceDataService;
    }

    public NavigatorDataWebService getNavigationDataService() {
        return navigationDataService;
    }

    public void setNavigationDataService(
            NavigatorDataWebService navigationDataService) {
        this.navigationDataService = navigationDataService;
    }

    public PolicyDataService getPolicyDataService() {
        return policyDataService;
    }

    public void setPolicyDataService(PolicyDataService policyDataService) {
        this.policyDataService = policyDataService;
    }

    public String getRedirectView() {
        return redirectView;
    }

    public void setRedirectView(String redirectView) {
        this.redirectView = redirectView;
    }

    public ManagedSystemDataService getManagedSysService() {
        return managedSysService;
    }

    public void setManagedSysService(ManagedSystemDataService managedSysService) {
        this.managedSysService = managedSysService;
    }


}
