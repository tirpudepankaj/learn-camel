package camel.code.devloafer.pankaj.custom.component.file;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
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

public class EventFileComponent extends DefaultComponent
{

  private final static Logger logger = LoggerFactory.getLogger(EventFileComponent.class);

  public EventFileComponent()
  {
    
  }
  
  public EventFileComponent(CamelContext camelContext) 
  {
    super(camelContext);
  }

  @Override
  protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception
  {
    logger.info("creating endpoint for uri : {} ", uri);
    Endpoint endpoint = new EventFileEndPoint(uri, this);
    setProperties(endpoint, parameters);
    return endpoint;
  }

}
