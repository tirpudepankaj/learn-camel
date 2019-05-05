package camel.code.devloafer.pankaj.chapter2;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * 
 * @author Pankaj Tirpude [tirpudepankaj@gmail.com]
 *
 */
public class FtpToJMSExample
{
  public static void main(String[] args) throws Exception
  {
    CamelContext camelContext = new DefaultCamelContext();
    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
    camelContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
    camelContext.addRoutes(new RouteBuilder()
    {
      public void configure()
      {
        from("ftp://rider.com/orders?username=rider?password=secret")
        .to("jms.incomingOrders");
      }
    });
   camelContext.start();
   Thread.sleep(10000);
   camelContext.stop();
    
  }
}
