import org.openiam.idm.srvc.pswd.service.PasswordGenerator;

String pswd = null;

// random password generator

pswd = PasswordGenerator.generatePassword(8);

// example of static password
//pswd = "Password";

println("Password = " + pswd);

output=pswd