package camel.code.devloafer.pankaj.custom.component.file;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.function.Predicate;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultConsumer;
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


public class EventFileConsumer extends DefaultConsumer
{
  
  private final static Logger logger = LoggerFactory.getLogger(EventFileConsumer.class);
  private Path directoryToBeWatch;
  private WatchService watchService;
  private boolean start = false;
  private WatchKey watchKey;
  
  public EventFileConsumer(Endpoint endpoint, Processor processor, String directoryToBeWatch)
  {
    super(endpoint, processor);
    logger.info("creating consumer for directory to be watch : {} ", directoryToBeWatch);
    setDirectoryToBeWatch(new File(directoryToBeWatch).toPath());
    
  }
  
  @Override
  protected void doStart() throws Exception
  {
    logger.info("starting the consumer..");
    super.doStart();
    watchService = EventFileWatcher.factory(directoryToBeWatch).getWatchService();
    start = true;
    startWatching();
  }
  
  private void startWatching()
  {
    while(start)
    {
      try
      {
        watchKey = watchService.take();
      }
      catch(InterruptedException exception)
      {
        logger.error("Exception while getting watch key : {} ", exception);
        start = false;
      }
      
      for(WatchEvent<?> watchEvent : watchKey.pollEvents())
      {
        WatchEvent.Kind<?> kind = watchEvent.kind();
        if(kind == StandardWatchEventKinds.OVERFLOW) continue;
        
        
        @SuppressWarnings("unchecked")
        WatchEvent<Path> pathEvent = (WatchEvent<Path>)watchEvent;
        final Path path = pathEvent.context();
        try
        {
          new Thread(() -> {
            try {
            Path exchangeBody = directoryToBeWatch.resolve(path);
            Exchange exchange = getEndpoint().createExchange();
            exchange.getIn().setBody(exchangeBody);
              EventFileConsumer.this.getProcessor().process(exchange);
            } 
            catch (Exception camelTaskException)
            { 
              logger.error("Exception in camel task : {} ",camelTaskException);
            }
          }).start();
        }
        catch(Exception knightWatchException)
        {
          logger.error("Error while receiving file : {} ", knightWatchException);
          continue;
        }
        
        Boolean value = watchKey.reset() ? Boolean.TRUE : Boolean.FALSE;
        Predicate<Boolean> predicate = condition -> !condition;
        if(predicate.test(value)) break; 
      }
    }
    
  }
  
  protected void doStop()throws Exception
  {
    logger.info("stopping the consumer..");
    super.doStop();
    start = false;
  }
  
  public void setDirectoryToBeWatch(Path directoryToBeWatch)
  {
    this.directoryToBeWatch = directoryToBeWatch;
  }
  
  public Path getDirectoryToBeWatch()
  {
    return directoryToBeWatch;
  }

}
