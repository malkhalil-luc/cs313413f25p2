package cs271.lab.list;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPerformance {

  // TODO run test and record running times for SIZE = 10, 100, 1000, 10000, ...
  // (choose in conjunction with REPS below up to an upper limit where the clock
  // running time is in the tens of seconds)
  // TODO Question: What conclusions can you draw about the performance of LinkedList vs. ArrayList when
  // comparing their running times for AddRemove vs. Access? Record those running times in README.txt!
  // TODO (optional) refactor to DRY
  // which of the two lists performs better as the size increases?
  private final int SIZE = 10;

  // TODO choose this value in such a way that you can observe an actual effect
  // for increasing problem sizes
  private final int REPS = 10000000;
  Logger logger;
  FileHandler logFile;
  private List<Integer> arrayList;
  private List<Integer> linkedList;
  long startTime, endTime, executionTime;

  @Before
  public void setUp() throws Exception {
    logger = Logger.getLogger("performanceLogger");
    setupLogger(logger,logFile);

    arrayList = new ArrayList<Integer>(SIZE);
    linkedList = new LinkedList<Integer>();

    for (var i = 0; i < SIZE; i++) {
      arrayList.add(i);
      linkedList.add(i);
    }
  }
public void setupLogger(Logger logger, FileHandler logFile) throws IOException {

  Files.createDirectories(Paths.get("./log"));
  try {
    logFile = new FileHandler("./log/performanceLog.log");

    logger.setUseParentHandlers(false);
    SimpleFormatter formatter = new SimpleFormatter();
    logFile.setFormatter(formatter);
    logger.addHandler(logFile);

    logger.info("Log initialized...");


  } catch (SecurityException | IOException e) {
      e.printStackTrace();
  }
}
  @After
  public void tearDown() throws Exception {
    arrayList = null;
    linkedList = null;
  }

  @Test
  public void testLinkedListAddRemove() {
     startTime = System.nanoTime();
    for (var r = 0; r < REPS; r++) {
      linkedList.add(0, 77);
      linkedList.remove(0);
    }
     endTime = System.nanoTime();
     executionTime = (endTime - startTime)/ 1_000_000; // to convert nano to ms
    logger.info(" testLinkedListAddRemove. Size:" + REPS + ", Execution time: "+ executionTime + "ms");
  }

  @Test
  public void testArrayListAddRemove() {
    startTime = System.nanoTime();

    for (var r = 0; r < REPS; r++) {
      arrayList.add(0, 77);
      arrayList.remove(0);
    }
    endTime = System.nanoTime();
    executionTime = (endTime - startTime)/ 1_000_000; // to convert nano to ms
    System.out.println(" testArrayListAddRemove time taken:" + executionTime + "ms");
  }

  @Test
  public void testLinkedListAccess() {
    var sum = 0L;
    for (var r = 0; r < REPS; r++) {
      sum += linkedList.get(r % SIZE);
    }
  }

  @Test
  public void testArrayListAccess() {
    var sum = 0L;
    for (var r = 0; r < REPS; r++) {
      sum += arrayList.get(r % SIZE);
    }
  }
}
