import org.openiam.idm.groovy.helper.ServiceHelper;


import org.openiam.util.WebServiceHelper;
import org.openiam.idm.srvc.synch.ws.IdentitySynchWebService;
import org.openiam.idm.srvc.synch.dto.SynchConfig;
import org.openiam.idm.srvc.synch.ws.SynchConfigResponse;
import org.openiam.idm.srvc.synch.ws.IdentitySynchWebService;
import org.openiam.idm.srvc.synch.ws.AsynchIdentitySynchService;


println("synchronization.groovy")

def IdentitySynchWebService synchService = ServiceHelper.synchService();

println("service client =" + synchService);

println("Asynch service client =" + ServiceHelper.asynchSynchService());

def AsynchIdentitySynchService asyncService =  ServiceHelper.asynchSynchService();

def synchObjId 		=  taskObj.param1;

def SynchConfig config = (SynchConfig)synchService.findById (synchObjId).config
asyncService.startSynchronization (config)	


output=1


