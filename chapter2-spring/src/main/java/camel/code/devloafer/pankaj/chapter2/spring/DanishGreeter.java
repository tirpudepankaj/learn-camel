package camel.code.devloafer.pankaj.chapter2.spring;

/**
 * 
 * @author Pankaj Tirpude [tirpudepankaj@gmail.com]
 * @date 20190504
 *
 */

public class DanishGreeter implements Greeter
{

  public String sayHello()
  {
    return "Hello " + System.getProperty("user.name");
  }

}
