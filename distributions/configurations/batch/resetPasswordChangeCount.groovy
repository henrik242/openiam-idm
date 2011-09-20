// changes the status us users that have been inactive
// runs every night


System.out.println("resetPasswordChangeCount.groovy is being executed.");

def loginManager = context.getBean("loginManager")

loginManager.bulkResetPasswordChangeCount();


output=1