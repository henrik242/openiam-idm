/* Creates the DB and initial user account and privleges */

CREATE database openiam
CHARACTER SET = latin1;
USE openiam;

CREATE USER 'idmuser'@'localhost' IDENTIFIED BY 'idmuser';
GRANT ALL ON *.* TO 'idmuser'@'localhost'; 

/* enable remote access to the database */
GRANT ALL ON openiam.* TO idmuser@'*' IDENTIFIED BY 'idmuser';


CREATE database bpmcore
CHARACTER SET = latin1;
USE bpmcore;

/* enable remote access to the database */
GRANT ALL ON bpmcore.* TO idmuser@'*' IDENTIFIED BY 'idmuser';


CREATE database bpmhistory
CHARACTER SET = latin1;
USE bpmhistory;

GRANT ALL ON bpmhistory.* TO idmuser@'*' IDENTIFIED BY 'idmuser';