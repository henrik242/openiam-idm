package org.openiam.idm.srvc.audit.export;

import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

/**
 * Implementation to export IHE (Healthcare) Audit Events
 * User: suneetshah
 * Date: 9/18/11
 * Time: 10:47 PM
 */
public class IHEAuditEvent implements ExportAuditEvent{
    public void event(IdmAuditLog log) {
        System.out.println("Audit Event called...");

        if (log.getObjectTypeId().equalsIgnoreCase("AUTHENTICATION")) {
            if (log.getActionId().equalsIgnoreCase("AUTHENTICATION")) {
                login(log);
            }
            if (log.getActionId().equalsIgnoreCase("LOGOUT")) {
                logout(log);
            }
        }
        if (log.getObjectTypeId().equalsIgnoreCase("USER")) {
            userChange(log);
        }
        if (log.getObjectTypeId().equalsIgnoreCase("ROLE")) {
            roleChange(log);
        }
        if (log.getObjectTypeId().equalsIgnoreCase("RESOURCE")) {
            resourceChange(log);
        }


    }

    private void login(IdmAuditLog log) {
        System.out.println("login event");

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String timeStr =  format.format(log.getActionDatetime());

        StringBuffer buf = new StringBuffer();
        buf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        buf.append(" <AuditMessage xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
        buf.append("<EventIdentification EventActionCode=\"E\" EventDateTime=\"" + timeStr + "\" EventOutcomeIndicator=\"0\">");



            <?xml version="1.0" encoding="UTF-8"?>
            <AuditMessage xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
              <EventIdentification EventActionCode="E" EventDateTime="2010-12-12T13:28:52.531-05:00" EventOutcomeIndicator="0">
              <EventID csd-code="110114" codeSystemName="DCM" displayName="Application Activity"/>
              <EventTypeCode csd-code="110122" codeSystemName="DCM" displayName="Login"/>
              </EventIdentification>
              <ActiveParticipant UserID="applicationId" UserIsRequestor="true">
                <RoleIDCode csd-code="110150" codeSystemName="DCM" displayName="Application"/>
              </ActiveParticipant>
              <AuditSourceIdentification AuditSourceID="openiam"/>
            </AuditMessage>


      /*      <?xml version="1.0" encoding="UTF-8"?>
            <AuditMessage xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
              <EventIdentification EventActionCode="E" EventDateTime="2010-12-12T13:28:52.531-05:00" EventOutcomeIndicator="0">
              <EventID csd-code="110100" codeSystemName="DCM" displayName="Application Activity"/>
              <EventTypeCode csd-code="110120" codeSystemName="DCM" displayName="Application Start"/>
              </EventIdentification>
              <ActiveParticipant UserID="applicationId" UserIsRequestor="false">
                <RoleIDCode csd-code="110150" codeSystemName="DCM" displayName="Application"/>
              </ActiveParticipant>
              <ActiveParticipant UserID="launcherId1" UserIsRequestor="false">
                <RoleIDCode csd-code="110151" codeSystemName="DCM" displayName="Application Launcher"/>
              </ActiveParticipant>
              <ActiveParticipant UserID="launcherId2" UserIsRequestor="true">
                <RoleIDCode csd-code="110151" codeSystemName="DCM" displayName="Application Launcher"/>
              </ActiveParticipant>
              <AuditSourceIdentification AuditSourceID="auditSourceId"/>
            </AuditMessage>
        */

    }

    private void logout(IdmAuditLog log) {
         System.out.println("logout event");


         <?xml version="1.0" encoding="UTF-8"?>
            <AuditMessage xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
              <EventIdentification EventActionCode="E" EventDateTime="2010-12-12T13:28:52.531-05:00" EventOutcomeIndicator="0">
              <EventID csd-code="110114" codeSystemName="DCM" displayName="Application Activity"/>
              <EventTypeCode csd-code="110123" codeSystemName="DCM" displayName="Logout"/>
              </EventIdentification>
              <ActiveParticipant UserID="applicationId" UserIsRequestor="true">
                <RoleIDCode csd-code="110150" codeSystemName="DCM" displayName="Application"/>
              </ActiveParticipant>
              <AuditSourceIdentification AuditSourceID="openiam"/>
            </AuditMessage>


    }

    private void userChange(IdmAuditLog log) {
         System.out.println("user change event");

    }

    private void roleChange(IdmAuditLog log) {
         System.out.println("role change event");

    }

    private void resourceChange(IdmAuditLog log) {
         System.out.println("resource change event");

    }

}
