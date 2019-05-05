package camel.code.devloafer.pankaj.chapter2;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Pankaj Tirpude [tirpudepankaj@gmail.com]
 *
 */

public class FtpToJMSExampleWithProcessor
{
  private final static Logger logger = LoggerFactory.getLogger(FtpToJMSExampleWithProcessor.class);
  public static void main() throws Exception{
    CamelContext camelContext = new DefaultCamelContext();
    camelContext.addRoutes(new RouteBuilder()
    {
      @Override
      public void configure() throws Exception
      {
        from("ftp://rider.com/orders?username=rider&password=secret")
         .process(new Processor()
        {
          
          public void process(Exchange exchange) throws Exception
          {
            logger.info("We just downloaded :  {} ", exchange.getIn().getHeader("CamelFileName"));
          }
        })
          .to("jms:incomingOrders");
        
      }
    });
  }
}
