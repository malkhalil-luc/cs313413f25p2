COMP 313/413 Project 2 Report Template

TestList.java and TestIterator.java

	TODO also try with a LinkedList - does it make any difference?

		No difference, tests will pass; as test methods rely on the iterator i.
                Iterator interface defined by the List interface, List interface extends Collection which in turn
                extends Iterable.therefore, iterator is implemented by both ArrayList<> and LinkedList<>;
                so it has the same behaviour in this scenario.

    TODO Question: What happens if you use list.remove(Integer.valueOf(77))?

        The list is synchronized with the iterator cursor; using list .remove() will directly alter the list
        which will cause inconsistency between the list and the iterator, using the iterator is safer to
        remove elements can keep the consistency between the list and iterator because it will automatically adjust the state.
        By using list.remove() an exception ConcurrentModificationException will be thrown.
        The remove(Integer.valueOf(77) will remove teh value 77 from teh first location that contains it and reduce the size by 1


    TODO using assertEquals and List.of, express which values are left in the list

        assertEquals(List.of(33, 44, 55, 66), list);

    TODO use an iterator and a while loop to compute the average (mean) of the values

         final var i = list.iterator();
            while (i.hasNext()) {
              sum += i.next().intValue();
              n++;
            }

____________________________________________________________________________________________________________

TestList.java

    public void testSizeNonEmpty() {
        // TODO fix the expected values in the assertions:

                assertEquals(false, list.isEmpty());
                assertEquals(1, list.size());
                assertEquals(77, list.get(0).intValue());

    public void testContains() {
        // TODO write assertions using list.contains(77) that hold before and after adding 77 to the list

                assertEquals(false, list.contains(77));
                list.add(77);
                assertEquals(true, list.contains(77));

    public void testAddMultiple()
        // TODO fix the expected values in the assertions

                assertEquals(3, list.size());
                assertEquals(0, list.indexOf(77));
                assertEquals(77, list.get(1).intValue());
                assertEquals(2, list.lastIndexOf(77));

    public void testAddMultiple2()
        // TODO fix the expected values in the assertions

            assertEquals(7, list.size());
            assertEquals(1, list.indexOf(77));
            assertEquals(5, list.lastIndexOf(77));
            assertEquals(44, list.get(2).intValue());
            assertEquals(77, list.get(3).intValue());
            assertEquals(List.of(33, 77, 44,77,55,77,66), list);



	testRemoveObject()
	        ***remove method is overloaded: List.remove(int index), List.remove(Object o)***
	        remove(int index): will remove the value at the specified index and reduce the list size by 1.
	        remove(Object o): will remove the value froom teh first index that contains that value and reduce the size by 1


        // TODO Question: What does this method do? list.remove(5);

		    remove(int index): takes the element index and remove the element at that index from the list
		    in this case: the 5the element > which is 77.

        // TODO fix the expected values in the assertions

            assertEquals(6, list.size());
            assertEquals(1, list.indexOf(77));
            assertEquals(3, list.lastIndexOf(77));
            assertEquals(4, list.get(2).intValue());
            assertEquals(77, list.get(3).intValue());

            assertEquals(5, list.size());
            assertEquals(1, list.indexOf(77));
            assertEquals(3, list.lastIndexOf(77));
            assertEquals(4, list.get(2).intValue());
            assertEquals(77, list.get(3).intValue());


        // TODO Question: What does this one do? list.remove(Integer.valueOf(5));

            Integer.valueOf( int x):is a static factory method from Integer class returns or create an integer object from a primitive like int or a String.
            Integer.valueOf(5) will return an Integer object, which is passed to List.remove(Object o) method.
            List.remove(Integer.valueOf(5)) will remove the value 5 from the first index that contains that value
            in this case index 4.

    public void testContainsAll()
        // TODO using containsAll and List.of (see above),
             1) assert that list contains all five different numbers added
             2) assert that list does not contain all of 11, 22, and 33

            assertTrue(list.containsAll(List.of(33, 44, 55, 66, 77)));
            assertFalse(list.containsAll(List.of(11, 22, 33))); // import org.junit.Assert.assertFalse;

    public void testAddAll()
        // TODO in a single statement using addAll and List.of

            list.addAll(List.of(33, 77, 44, 77, 55, 77, 66));

    public void testRemoveAll()
        // TODO in a single statement using removeAll and List.of

            list.removeAll(List.of(33, 44, 55, 66));

    public void testRetainAll()
        // TODO in a single statement using retainAll and List.of,

            list.retainAll(List.of(77));

    public void testSet()
        // TODO use the set method to change specific elements in the list

            list.set(1, 99);
            list.set(3, 99);
            list.set(5, 99);

    public void testSubList()
        // TODO fix the arguments in the subList method so that the assertion
            x
            returns elements from index 2 to 4:
                assertEquals(List.of(44, 77, 55), list.subList(2, 5));

____________________________________________________________________________________________________________

TestIterator.java

	testRemove()

		i.remove(); // what happens if you use list.remove(77)?
		    it will use the version List.remove(int index), the list size is 7, passing 77 will
		    result in IndexOutOfBoundsException.


____________________________________________________________________________________________________________

TestPerformance.java

** The time is recorded using System.nanoTime(), System.currentTimeMillis(); can also be used but less precise.**
    	see: https://www.baeldung.com/java-system-currenttimemillis-vs-system-nanotime
    	     https://docs.oracle.com/javase/8/docs/api/java/lang/System.html#nanoTime--
    	     https://docs.oracle.com/javase/8/docs/api/java/lang/System.html#currentTimeMillis--

    TODO run test and record running times for SIZE = 10, 100, 1000, 10000, ...

           Tests ran accoding to teh following fctors:
                final int [] SIZES={10,100,1000,5000};  //5000 to finish tests faster.
                final int REPEAT=5;
                private final int REPS = 10000000;


     TODO Question: What conclusions can you draw about the performance of LinkedList vs. ArrayList when
      // comparing their running times for AddRemove vs. Access? Record those running times in README.txt!

                Access:
                    ArrayList: access time almost the same (close enough to be called constant) across different input sizes
                            Average for each size respectively: 11.895ms , 11.301ms, 10.956ms , 10.938ms
                    LinkedList: higher than ArrayList, and grows higher as the inputs grows.
                            Average for each size respectively: 27.275ms, 130.255ms, 2527.125ms, 11716.921ms

                AddRemove:
                    ArrayList: higher than ArrayList, and grows higher as the inputs grows.
                            Average for each size respectively: 108.746ms , 187.886ms , 1519.878ms , 7707.529ms
                    LinkedList: almost the same (close enough to be called constant) across different input sizes
                            Average for each size respectively: 48.09ms, 42.301ms , 40.916ms , 41.083ms


      TODO (optional) refactor to DRY

            PerformanceDry.java: some refactoring made to teh code, tests ran.

      TODO which of the two lists performs better as the size increases?

            At random access ArrayList.
            At Add and Remove LinkedList head and tail.



	TODO State how many times the tests were executed for each SIZE (10, 100, 1000 and 10000)
	      to get the running time in milliseconds and how the test running times were recorded.

	        >> The test was executed 5 times,
	        the time recorded using System.nanoTime() and converted to ms by dividing by 1000.0 to get the double form
	        stored after rounding to 3 decimal precision.

	SIZE 10
								     #1   #2   #3   #4   #5   #6
    ArrayList Access Test:        [12.389, 13.597, 11.145, 11.163, 11.181]
    LinkedList Access Test:       [30.669, 26.495, 25.978, 27.039, 26.195]
    ArrayList AddRemove Test:     [105.455, 110.821, 109.148, 108.946, 109.362]
    LinkedList AddRemove Test:    [63.965, 48.168, 42.456, 43.195, 42.666]

	SIZE 100
                                     #1   #2   #3   #4   #5   #6
    ArrayList Access Test:        [11.098, 10.931, 10.896, 12.639, 10.942]
    LinkedList Access Test:       [131.763, 130.253, 130.172, 129.301, 129.784]
    ArrayList AddRemove Test:     [190.312, 189.299, 185.915, 189.208, 184.694]
    LinkedList AddRemove Test:    [45.194, 40.996, 42.116, 41.238, 41.959]

	SIZE 1000
                                    #1   #2   #3   #4   #5   #6
    ArrayList Access Test:        [11.042, 10.944, 10.98, 10.893, 10.923]
    LinkedList Access Test:       [2572.995, 2485.589, 2548.26, 2551.974, 2476.809]
    ArrayList AddRemove Test:     [1515.456, 1529.714, 1515.931, 1508.132, 1530.155]
    LinkedList AddRemove Test:    [40.552, 41.137, 41.032, 41.003, 40.858]

	SIZE 5000
                                     #1   #2   #3   #4   #5   #6
    ArrayList Access Test:        [10.953, 10.971, 10.921, 10.898, 10.949]
    LinkedList Access Test:       [11336.734, 11422.947, 11300.873, 11240.218, 13283.833]
    ArrayList AddRemove Test:     [7698.116, 7708.883, 7719.136, 7707.212, 7704.3]
    LinkedList AddRemove Test:    [40.69, 41.547, 40.843, 41.696, 40.638]

	listAccess - which type of List is better to use, and why?

		ArrayList is better because it is indexed, and will access directly the element at a specific index,
		with the index known it will take constant time

	listAddRemove - which type of List is better to use, and why?

        LinkedList as it only needed to change where elements point (next, previous), did not require traversal.

