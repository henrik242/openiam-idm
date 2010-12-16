import org.springframework.context.support.ClassPathXmlApplicationContext


def static context = new ClassPathXmlApplicationContext("applicationContext.xml")
def userDataService = context.getBean("userManager")

newUser = userDataService.getUserWithDependent('3006',true)
name=newUser.firstName + " " + newUser.lastName
output=name