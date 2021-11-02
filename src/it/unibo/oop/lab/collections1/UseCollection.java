package it.unibo.oop.lab.collections1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Example class using {@link java.util.List} and {@link java.util.Map}.
 * 
 */
public final class UseCollection {

    private static final int TO_MS = 1_000_000;
    
    private UseCollection() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
	ArrayList<Integer> list =new ArrayList<Integer>();
	for(int i=1000; i<2000; i++) {
	    list.add(list.size(), i);
	}
	
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
	LinkedList<Integer> list2=new LinkedList<>();
	list2.addAll(list);
	
	
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
	
	int firstElem=0;
	int lastElem=list.size()-1;
	int oldLastValue=list.get(lastElem);
	list.set(lastElem, list.get(firstElem));
	list.set(firstElem, oldLastValue);
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
	for(Integer elem:list) {
	    System.out.print(" "+elem.toString()+" ");
	}
	System.out.println(" ");
	
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
	
	long timeList1 = System.nanoTime();
	for(int i=0; i<100000; i++) {
	    list.add(0, i);
	}
	timeList1 = System.nanoTime() - timeList1;
	System.out.println("Adding 100000 elements as first element in a Array List took " + timeList1
                + "ns (" + timeList1 / TO_MS + "ms)");
	
	long timeList2 = System.nanoTime();
	for(int i=0; i<100000; i++) {
	    list2.add(0, i);
	}
	timeList2 = System.nanoTime() - timeList2;
	System.out.println("Adding 100000 elements as first element in a Linked List took " + timeList2
                + "ns (" + timeList2 / TO_MS + "ms)");
	
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
	long timeList3 = System.nanoTime();
	{
	int middle=(list.size()/2);
	for(int i=0; i<1000; i++) {
	    list.get(middle);
	}
	timeList3 = System.nanoTime() - timeList3;
	System.out.println("Reading the middle element in a Array List took " + timeList3
                + "ns (" + timeList3 / TO_MS + "ms)");
	}
	
	long timeList4 = System.nanoTime();
	{
	int middle=(list2.size()/2);
	for(int i=0; i<1000; i++) {
	    list2.get(middle);
	}
	timeList4 = System.nanoTime() - timeList4;
	System.out.println("Reading the middle element in a Linked List took " + timeList4
                + "ns (" + timeList4 / TO_MS + "ms)");
	}
	
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         * 
         * Africa -> 1,110,635,000
         * 
         * Americas -> 972,005,000
         * 
         * Antarctica -> 0
         * 
         * Asia -> 4,298,723,000
         * 
         * Europe -> 742,452,000
         * 
         * Oceania -> 38,304,000
         */
	
	HashMap<String, Long> world= new HashMap<>();
	
	world.put("Africa", 1_110_635_000L);
	world.put("Americas", 972_005_000L);
	world.put("Antarctica", 0L);
	world.put("Asia", 4_298_723_000L);
	world.put("Europe", 742_452_000L);
	world.put("Oceania", 38_304_000L);
	
        /*
         * 8) Compute the population of the world
         */
	
	Long population=0L;
	for(long elem: world.values()) {
	    population=population+elem;	    
	}
	System.out.println("World population: "+String.format("%,d", population));
    }
}
