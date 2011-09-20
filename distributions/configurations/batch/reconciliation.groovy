import org.openiam.idm.groovy.helper.ServiceHelper;


import org.openiam.util.WebServiceHelper;

import org.openiam.idm.srvc.recon.ws.AsynchReconciliationService;
import org.openiam.idm.srvc.recon.ws.ReconciliationWebService;
import org.openiam.idm.srvc.recon.dto.ReconciliationConfig;

println("reconciliation.groovy")


def AsynchReconciliationService asyncReconService = ServiceHelper.asyncReconciliationService();
def ReconciliationWebService reconService = ServiceHelper.reconciliationService();


def configId 		=  taskObj.param1;


println("configid=" + configId)

def ReconciliationConfig config = reconService.getConfigById (configId).config

println("Reconciliation configuration =" + config)

asyncReconService.startReconciliation (config)	


output=1


