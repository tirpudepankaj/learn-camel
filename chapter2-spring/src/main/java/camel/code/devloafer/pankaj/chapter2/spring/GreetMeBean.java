package camel.code.devloafer.pankaj.chapter2.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author Pankaj Tirpude [tirpudepankaj@gmail.com]
 * @date 20190504
 *
 */
public class GreetMeBean
{
  private Greeter greeter;

  private final static Logger logger = LoggerFactory
      .getLogger(GreetMeBean.class);

  private static ApplicationContext springContext;

  public void setGreeter(Greeter greeter)
  {
    this.greeter = greeter;
  }

  public void execute()
  {
    logger.info(greeter.sayHello());
  }

  public static void main(String[] args)
  {
    springContext = new ClassPathXmlApplicationContext("spring-context.xml");
    GreetMeBean bean = springContext.getBean(GreetMeBean.class);
    bean.execute();
  }

}
