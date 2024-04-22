/**I, Xuan Huy Pham, 000899551, certify that this material is my original work. No other person's work has been used without suitable acknowledgment and I have not made my work available to anyone else. */

/**
 * ========================================================================================
 * 1. Do you notice any significant performance difference between the SortedLinkedList<T>
 *     and the ArrayList<T> classes when adding items?
 *     Explain the differences using Big O notation for the different algorithms.
 *
 * - SortedLinked list took around 706723 us to add 18756 elements
 * - ArrayList took around 1942130 us to add the same number of elements
 * ==> ArrayList takes longer to add items compared to SortedLinkedList
 *
 * - in terms of Big O notation:
 * adding items using SortedLinkedList has a time complexity of O(n)
 * it takes longer as the list grows because each insertion requires finding the right spot in the list
 * adding items using ArrayList has a time complexity of O(n log n) because sorting is needed after each insertion
 *
 *
 *
 * 2. Do you notice any significant performance difference between these two collections when removing items?
 *    Explain the differences using Big O notation for the different algorithms.
 *
 * - SortedLinked list took around 622109 us to remove half of its elements (9378)
 * - ArrayList took around 179837 us to remove the same number of elements
 * ==> SortedLinkedList takes longer to remove items compared to ArrayList
 *
 * - in terms of Big O notation:
 * removing items using SortedLink list has a time complexity of O(n) in worst case
 * because it involves traversing the list to locate and remove items
 * removing items from an ArrayList takes O(n) time in the worst case because it
 * requires shirting elements to fill the gap created by the removed element
 *
 *
 * 3. When would you choose to use a SortedLinkedList over an ArrayList based on the results of this assignment?
 *
 * it depends on situations,
 * based on this assignment, on one hand, i would go for SortedLinkedList when i often need to keep the list sorted
 * all the time. On the other hand, i would go for ArrayList if i don't need to keep the list in a sorted order
 * also, it takes less time to remove elements
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Assignment4
{
  public static void main(String[] args)
  {
    final int NUMBER_OF_NAMES = 18756;
    final String filename = "src/bnames.txt";
    final String[] names = new String[NUMBER_OF_NAMES];

    // May be useful for selecting random words to remove
    Random random = new Random();

    // Read in all of the names
    try {
      Scanner fin = new Scanner(new File(filename));
      for (int i=0; i<NUMBER_OF_NAMES; i++)
        names[i] = fin.next();
      fin.close();
    } catch (FileNotFoundException e) {
      System.out.println("Exception caught: " + e.getMessage());
      System.exit(-1);
    }

    // Use the system to build the linked List

    // 1. Create the linkedList and add all items in Array
    SortedLinkedList<String> linkedList_String = new SortedLinkedList<>();
    long start = System.nanoTime();
    for (int i=0; i<NUMBER_OF_NAMES;i++)
      linkedList_String.add(names[i]);
    System.out.println("================================================================================");
    System.out.printf("The time required to add %d elements to a Linked List = %d us\n", NUMBER_OF_NAMES, (System.nanoTime() - start) / 1000);
    //System.out.println(linkedList_String); //remove comment of this line to print the whole list

    /**
     *
     *  for this part, if we keep it, it will remove half of the list
     *  however, the task that asks to load all the baby names to SortedLinkedList won't work properly
     *  because the list will end at n = 9378
     */
    // 2. Remove half the items and time the code.
    start = System.nanoTime();
    for (int i = 0; i < NUMBER_OF_NAMES / 2; i++){
      linkedList_String.remove(names[i]);
    }
    long end = System.nanoTime();
      System.out.println("================================================================================");
    System.out.printf("The time required to remove %d elements from a Linked List = %d us\n", NUMBER_OF_NAMES / 2, (end - start) / 1000);
    //System.out.println(linkedList_String); //remove comment of this line to print the whole list


    // 3. Create a SortedLinkedList of another data type and demonstrate
    System.out.println("================================================================================");
    System.out.println("Testing linked list:");
    SortedLinkedList<Double> linkedList_Double = new SortedLinkedList<>();
    linkedList_Double.remove(44.4);
    linkedList_Double.add(34.2);
    linkedList_Double.add(17.5);
    linkedList_Double.add(11.1);
    linkedList_Double.remove(44.4);
    linkedList_Double.remove(17.5);
    linkedList_Double.add(19.9);
    linkedList_Double.add(34.7);
    linkedList_Double.add(74.5);
    linkedList_Double.add(10.5);
    linkedList_Double.add(34.7);
    linkedList_Double.add(11.7);
    linkedList_Double.add(34.6);
    System.out.println(linkedList_Double.toString());

    /**
     * to let this task work properly, comment out the "remove half" part.
     * otherwise (n=17130, c=10) won't display anything in the list
     */
    //4. load all of the baby names to the SortedLinkedList and test
    //remove baby names
    linkedList_String.remove( "Aabha");
    linkedList_String.remove( "Narissa");
    linkedList_String.remove( "Zyva");
    linkedList_String.remove( "Remington");
    linkedList_String.remove( "Nefersari");
    linkedList_String.remove( "Mica");

    //print out specific ranges of elements from the list
    System.out.println("================================================================================");
    System.out.println("n=0, c=10:");
    System.out.println(linkedList_String.toString_page(0, 10 ));
    System.out.println("\nn=17130, c=10:");
    //this list will be empty since the whole list now just have a half (around 9000 elements)
    //unless we skip that removal step
    System.out.println(linkedList_String.toString_page(17130, 10 ));

    // 4. Build a standard ArrayList of String - Remember to sort list after each element is added
    //    Time this code.
    //    Use this timing data to compare add against SortedLinkedList in discussion
    //    Remove the half the elements and time again.
    //    Use this timing data to compare remove against SortedLinkedList in discussion

    ArrayList<String> arrayList_String = new ArrayList<>();
    long arrayListAddTime = 0;
    long arrayListRemoveTime = 0;

    start = System.nanoTime();
    for (int i = 0; i < NUMBER_OF_NAMES; i++){
      arrayList_String.add(names[i]);
      Collections.sort(arrayList_String);
    }

    long arrayListAddEndTime = System.nanoTime();
    arrayListAddTime = arrayListAddEndTime - start;

    start = System.nanoTime();
    for (int i = 0; i < NUMBER_OF_NAMES / 2; i++) {
      arrayList_String.remove(names[i]);
    }
    end = System.nanoTime(); //fine this as "long end" if comment out task 2 (remove half task)
    arrayListRemoveTime = end - start;

    System.out.println("================================================================================");
    System.out.printf("The time required to add %d elements to an ArrayList = %d us\n", NUMBER_OF_NAMES, arrayListAddTime/1000);
    System.out.printf("The time required to remove %d elements from an ArrayList = %d us\n", NUMBER_OF_NAMES/2, arrayListRemoveTime/1000);
  }
}