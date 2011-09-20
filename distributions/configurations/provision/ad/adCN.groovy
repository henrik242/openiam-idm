import org.springframework.context.support.ClassPathXmlApplicationContext

def loginManager = context.getBean("loginManager")

loginID="cn="+user.firstName + "." + user.lastName



output=loginID


