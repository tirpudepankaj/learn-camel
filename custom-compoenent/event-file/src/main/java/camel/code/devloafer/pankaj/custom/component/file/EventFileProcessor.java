package camel.code.devloafer.pankaj.custom.component.file;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventFileProcessor implements Processor
{
  
  private final static Logger logger = LoggerFactory.getLogger(EventFileProcessor.class);
  
  public void process(Exchange exchange) throws Exception
  {
    logger.info("processing exchange : {} ", exchange);
  }
  
}
