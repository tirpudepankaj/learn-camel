package camel.code.devloafer.pankaj.chapter2.spring;

public class EnglishGreeter implements Greeter
{

  public String sayHello()
  {
    return "Davs " + System.getProperty("user.name");
  }

}
