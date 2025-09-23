package cs271.lab.list;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class PerformanceDry {
     final int [] SIZES={10,100,1000,5000};//,1000000}; // input size
    final int REPEAT=5;
    private final int REPS = 10000000;
     List<Integer> arrayList, linkedList;
    double startTime;

    @Before
    public void setup() throws Exception{

    }

    public void initList(List<Integer>list, int size){
        for(int i=0;i<size;i++){
            list.add(i);
        }
    }
    public double getAverage(double [] data ){
        double sum=0.0;

        for (double time : data){
            sum+=time;
        }
        return Math.round((sum/data.length) * 1000.0) / 1000.0; // Round to 3 decimals

    }

    public double listAddRemove (List<Integer> list)
    {
        startTime = System.nanoTime();
        for (int i=0;i<REPS;i++){
            list.add(0, 77);
            list.remove(0);
        }
        double executionTime = (System.nanoTime() - startTime)/ 1000000.0;// in ms
        return  Math.round(executionTime * 1000.0) / 1000.0; // Round to 3 decimal places
    }

    public double listAccess(List<Integer> list){
        var sum = 0L;
        startTime = System.nanoTime();
        for(int i=0;i<REPS;i++){
            sum += list.get(i % list.size());
        }
        double executionTime = (System.nanoTime() - startTime)/ 1000000.0;// in ms
        return  Math.round(executionTime * 1000.0) / 1000.0; // Round to 3 decimal places
    }

    @Test
    public void measurePerformance() throws Exception{

        double [] testArrayListAccess;
        double [] testLinkedListAccess;
        double [] testArrayListAddRemove;
        double [] testLinkedListAddRemove;

        try (PrintWriter printWriter = new PrintWriter(new FileWriter("ExecTimeReadings.txt",true));){

            for (int size : SIZES) {
                printWriter.println("Input Size: " + size+ "\n");

                testArrayListAccess = new double[REPEAT];
                testLinkedListAccess = new double[REPEAT];
                testLinkedListAddRemove = new double[REPEAT];
                testArrayListAddRemove = new double[REPEAT];

                for (int r = 0; r < REPEAT; r++) {
                    linkedList = new LinkedList<>();
                    arrayList = new ArrayList<>(size);

                    initList(arrayList, size);
                    initList(linkedList, size);

                    testArrayListAccess[r] = listAccess(arrayList);
                    testLinkedListAccess[r] = listAccess(linkedList);
                    testArrayListAddRemove[r] = listAddRemove(arrayList);
                    testLinkedListAddRemove[r] = listAddRemove(linkedList);
                }
                printWriter.println("ArrayList Access:     " + " Average ET: " + getAverage(testArrayListAccess)     +"ms " + "\n \t ETs Recorded: "+ Arrays.toString(testArrayListAccess)+"\n" );
                printWriter.println("LinkedList Access:    " + " Average ET: " + getAverage(testLinkedListAccess)    +"ms " + "\n \t ETs Recorded: "+ Arrays.toString(testLinkedListAccess)+"\n");
                printWriter.println("ArrayList AddRemove:  " + " Average ET: " + getAverage(testArrayListAddRemove)  +"ms " + "\n \t ETs Recorded: "+ Arrays.toString(testArrayListAddRemove)+"\n");
                printWriter.println("LinkedList AddRemove: " + " Average ET: " + getAverage(testLinkedListAddRemove) +"ms " + "\n \t ETs Recorded: "+ Arrays.toString(testLinkedListAddRemove)+"\n");
                printWriter.println("____________________________________________");

            }
        } catch (Exception exc){
            exc.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        arrayList = null;
        linkedList = null;
    }
}
