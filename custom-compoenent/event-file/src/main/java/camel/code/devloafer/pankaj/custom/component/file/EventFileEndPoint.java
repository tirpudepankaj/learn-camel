package camel.code.devloafer.pankaj.custom.component.file;

import org.apache.camel.Component;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Pankaj Tirpude [tirpudepankaj@gmail.com]
 *
 * @date 20190518
 * 
 *
 */

@UriEndpoint(scheme="evtfile", title="EventFile", syntax="evtfile:directoryToBeWatch", label="evtfile")
public class EventFileEndPoint extends DefaultEndpoint
{

  private final static Logger logger = LoggerFactory.getLogger(EventFileEndPoint.class);
  
  @UriParam
  @Metadata(required="true")
  private String directoryToBeWatch;
  
  public EventFileEndPoint(String uri, Component component)
  {
    super(uri, component);
  }
  
  
  public Producer createProducer() throws Exception
  {
    logger.info("creating producer..");
    Producer producer = new EventFileProducer(EventFileEndPoint.this);
    return producer;
  }

  public Consumer createConsumer(Processor processor) throws Exception
  {
    logger.info("creating consumer..");
    Consumer consumer = new EventFileConsumer(EventFileEndPoint.this, processor, directoryToBeWatch);
    configureConsumer(consumer);
    return consumer;
  }

  public boolean isSingleton()
  {
    logger.info("setting singleton flag..");
    return true;
  }
  
  public void setDirectoryToBeWatch(String directoryToBeWatch)
  {
    this.directoryToBeWatch = directoryToBeWatch;
  }
  
  public String getDirectoryToBeWatch()
  {
    return directoryToBeWatch;
  }

}
