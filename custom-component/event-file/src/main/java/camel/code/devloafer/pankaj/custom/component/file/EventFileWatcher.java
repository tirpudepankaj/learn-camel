package camel.code.devloafer.pankaj.custom.component.file;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Pankaj Tirpude [tirpudepankaj@gmail.com]
 * @date 20190518
 */

public class EventFileWatcher
{
  private final static Logger logger = LoggerFactory.getLogger(EventFileWatcher.class);
  private static Path  directoryToBeWatch;
  private WatchKey watchKety;
  private WatchService watchService;
  private static EventFileWatcher eventFileWatcher;
  
  
  private EventFileWatcher()
  {
    initWatcher();
  }
  
  public static EventFileWatcher factory(Path directoryToBeWatch)
  {
    if(eventFileWatcher == null && directoryToBeWatch != null)
    {
      EventFileWatcher.directoryToBeWatch = directoryToBeWatch;
      eventFileWatcher = new EventFileWatcher();
    }
    else if(directoryToBeWatch.equals(EventFileWatcher.directoryToBeWatch))
    {
      // throw runtime exception...
      logger.error("Event Watcher is already instantiated for directory : {} , and multiple watcher are not supported at this time", directoryToBeWatch);
      return null;
    }
    return eventFileWatcher;
  }
  
  private void initWatcher()
  {
    try
    {
      watchService = FileSystems.getDefault().newWatchService();
      watchKety = directoryToBeWatch.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
      logger.info("{} directory is register with service : {} ", directoryToBeWatch, watchService);
    } 
    catch (IOException ioException)
    {
      logger.error("Exception : {} ", ioException);
    }
    
  }
  
  public WatchService getWatchService()
  {
    return watchService;
  }
  
  public WatchKey getWatchKety()
  {
    return watchKety;
  }
  
}
