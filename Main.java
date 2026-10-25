import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
      System.out.println("Unsorted Array ---------------------------------------------------");
      ArrayList<Integer> integerList = Lab4.getList();
      Lab4.outputList(integerList);

      System.out.println("\n\nBubble sort results ----------------------------------------------");
      // times how long bubble sort takes to run
      long bubbleStart = System.nanoTime();
      ArrayList<Integer> bubbleSortedList = Lab4.bubbleSort(new ArrayList<>(integerList));
      long bubbleEnd = System.nanoTime();
      long bubbleTime = bubbleEnd - bubbleStart;
      Lab4.outputList(bubbleSortedList);
      System.out.println("\nbubble sort elapsed time: " + bubbleTime + " nanoseconds");

      System.out.println("\n\nInsertion sort results -------------------------------------------");
      // times how long insertion sort takes to run
      long insertionStart = System.nanoTime();
      ArrayList<Integer> insertionSortedList = Lab4.insertionSort(new ArrayList<>(integerList));  
      long insertionEnd = System.nanoTime();
      long insertionTime = insertionEnd - insertionStart;
      Lab4.outputList(insertionSortedList);
      System.out.println("\ninsertion sort elapsed time: " + insertionTime + " nanoseconds");
    }
}

class Lab4 {
  public static ArrayList<Integer> insertionSort(ArrayList<Integer> integerList) {
    // Step 1 - Implement insertion sort algorithm here
    for (int i = 1; i < integerList.size(); i++) {
        int currentElement = integerList.get(i);
        int k;

        // shifts values larger than the current element one position right
        for (k = i - 1; k >= 0 && integerList.get(k) > currentElement; k--) {
            integerList.set(k + 1, integerList.get(k));
        }

        // inserts the current element into the gap
        integerList.set(k + 1, currentElement);
    }

    return integerList;
  }

  public static ArrayList<Integer> bubbleSort(ArrayList<Integer> integerList) {
    // Step 2 - Implement the bubble sort algorithm here
    boolean needNextPass = true;
    for (int k = 1; k < integerList.size() && needNextPass; k++) {
        // assumes list is sorted until a swap proves otherwise
        needNextPass = false;

        for (int i = 0; i < integerList.size() - k; i++) {
            // swaps the neighboring values if they are out of order
            if (integerList.get(i) > integerList.get(i + 1)) {
                int temp = integerList.get(i);
                integerList.set(i, integerList.get(i + 1));
                integerList.set(i + 1, temp);
                needNextPass = true;
            }
        }
    }

    return integerList;
  }

  public static ArrayList<Integer> getList() {
    ArrayList<Integer> integerList = new ArrayList<>();
    String line;
    try (BufferedReader br = new BufferedReader(new FileReader("integers.txt"))) {
        while ((line = br.readLine()) != null) {
            integerList.add(Integer.parseInt(line));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return integerList;
  }

  public static void outputList(ArrayList<Integer> integerList) {
    for (int i = 0; i < integerList.size(); i++) {
        System.out.print(integerList.get(i) + " ");
    }
  }
}