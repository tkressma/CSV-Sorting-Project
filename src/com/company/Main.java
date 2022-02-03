package com.company;

import java.io.*;
import java.util.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ReadCSV readObj = new ReadCSV();
        SortCSV sortObj = new SortCSV();
        readObj.readCSV();
        Hashtable<Long, String> productList;
        productList = readObj.getUpc14ProductMap();

        for (int sortingAlgorithm = 0; sortingAlgorithm < 3; sortingAlgorithm++) {
            if (sortingAlgorithm == 0) {
                ArrayList<Long> quickSortArr = readObj.getUpc14List();
                sortObj.quicksort(quickSortArr, 0, quickSortArr.size() - 1);
                Long startTime = System.currentTimeMillis();
                sortObj.quicksort(quickSortArr, 0, quickSortArr.size() - 1);
                Long endTime = System.currentTimeMillis();
                Long runTime = (endTime - startTime);
                printOutput(quickSortArr, productList, runTime);
            } else if (sortingAlgorithm == 1) {
                ArrayList<Long> heapsortArr = readObj.getUpc14List();
                Long startTime = System.currentTimeMillis();
                sortObj.heapsort(heapsortArr);
                Long endTime = System.currentTimeMillis();
                Long runTime = (endTime - startTime);
                printOutput(heapsortArr, productList, runTime);
            } else if (sortingAlgorithm == 2) {
                Long[] radixsortArr = readObj.getUpc14List().toArray(new Long[0]);
                Long startTime = System.currentTimeMillis();
                sortObj.radixsort(radixsortArr, radixsortArr.length);
                Long endTime = System.currentTimeMillis();
                Long runTime = (endTime - startTime);
                ArrayList<Long> radixSortArrList = new ArrayList<>();
                Collections.addAll(radixSortArrList, radixsortArr);
                printOutput(radixSortArrList, productList, runTime);
            }
            System.out.println("All sorting algorithms complete. Please view Sorted_Output.txt.");
        }
    }

    private static void printOutput(ArrayList<Long> sortedList, Hashtable<Long, String> productList, Long runTime) throws IOException {
        LinkedHashMap<Long, String> sortedOutput = new LinkedHashMap<>();
        FileWriter fw = new FileWriter("Sorted_Output.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);

        for (int i = 0; i < sortedList.size(); i++) {
            Long key = sortedList.get(i);
            sortedOutput.put(key, productList.get(key));
        }

        for (Long key : sortedOutput.keySet()) {
            System.out.println("UPC14: " + String.format("%014d", key) + " -> Product: " + sortedOutput.get(key));
            bw.append("UPC14: " + String.format("%014d", key) + " -> Product: " + sortedOutput.get(key) + "\n");
        }

        System.out.println("Sorting complete, total runtime: " + runTime + " milliseconds\n");
        bw.append("Sorting complete, total runtime: " + runTime + " milliseconds\n");
        bw.close();
        fw.close();
    }
}
