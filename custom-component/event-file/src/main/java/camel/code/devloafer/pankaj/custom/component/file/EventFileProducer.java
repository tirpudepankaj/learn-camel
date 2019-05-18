package camel.code.devloafer.pankaj.custom.component.file;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Pankaj Tirpude [tirpudepankaj@gmail.com]
 *
 * @date 20190518
 *
 */

public class EventFileProducer extends DefaultProducer
{

  private final static Logger logger = LoggerFactory.getLogger(EventFileProducer.class);
  
  public EventFileProducer(Endpoint endpoint)
  {
    super(endpoint);
  }
  
  public void process(Exchange exchange) throws Exception
  {
    logger.info("processing exchange : ", exchange);
  }

}
