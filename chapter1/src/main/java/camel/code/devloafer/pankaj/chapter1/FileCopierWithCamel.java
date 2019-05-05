package camel.code.devloafer.pankaj.chapter1;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * 
 * @author Pankaj Tirpude [tirpudepankaj@gmail.com]
 * @date 20190504
 */

public class FileCopierWithCamel
{
  public static void main(String[] args) throws Exception
  {
    CamelContext camelContext = new DefaultCamelContext();

    camelContext.addRoutes(new RouteBuilder()
    {
      public void configure()
      {
        from("file:data/inbox?noop=true").to("file:data/inbox");
      }
    });

    camelContext.start();
    Thread.sleep(100000);
    camelContext.stop();
  }
}
