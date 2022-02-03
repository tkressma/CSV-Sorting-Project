package com.company;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadCSV {

    ArrayList<Long> upc14List = new ArrayList<Long>();
    ArrayList<String> nameList = new ArrayList<String>();
    Hashtable<Long, String> productList = new Hashtable<Long, String>();
    File file = new File("Grocery_UPC_Database.csv");

    public void readCSV() throws IOException {

        String line = "";
        System.out.println("Selected file: " + file.getAbsolutePath());
        BufferedReader reader = new BufferedReader(new FileReader("Grocery_UPC_Database.csv"));

        while ((line = reader.readLine()) != null) {
            if (!line.contains("upc14")) {

                String REGEX = "^(\".*?\"|.*?),(\".*?\"|.*?),(\".*?\"|.*?),(\".*?\"|.*?),(\".*?\"|.*?)$";
                Pattern pattern = Pattern.compile(REGEX);
                Matcher matcher = pattern.matcher(line);
                matcher.find();

                Long upc14 = Long.parseLong(matcher.group(2));
                String productName = matcher.group(5);

                upc14List.add(upc14);
                nameList.add(productName);
                productList.put(upc14, productName);
            }
        }
    }

    public ArrayList<Long> getUpc14List() {
        return upc14List;
    }

    public Hashtable<Long, String> getUpc14ProductMap() {
        return productList;
    }

}
