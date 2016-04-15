package com.sjsu.cmp239.dataPreProcessing;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by ashish on 4/15/16.
 */
public class DataProcessing {

    private static final String inputCSV = "/home/ashish/SJSU/239/ProjectA239/src/main/assets/input/movies.csv";
    private static final String outputCSV = "/home/ashish/SJSU/239/ProjectA239/src/main/assets/output/output.csv";
    private static final String[] OUTPUT_FILE_HEADER = {"UserID", "ItemID", "Ratings"};
    private static String userID;
    private static int itemID;
    private static String rating;
    // private static String []outputString;


    public static void parseCSV() throws IOException {
        CSVParser parser = new CSVParser(new FileReader(inputCSV), CSVFormat.DEFAULT.withHeader());
        CSVFormat outputFormat = CSVFormat.DEFAULT.withRecordSeparator(",");
        FileWriter fileWriter = new FileWriter(outputCSV);
        CSVPrinter printer = new CSVPrinter(fileWriter, outputFormat);
        printer.printRecord(OUTPUT_FILE_HEADER);
        printer.print('\n');
        for (CSVRecord record : parser) {
            userID = record.get("ID");
            itemID = 0;
            for (int i = 6; i < record.size() - 5; i++) {
                rating = record.get(i);
                System.out.println("UserID: " + userID + " itemID: " + (itemID++) + " Rating: " + rating);
                //outputString = new String[]{userID,String.valueOf(itemID),rating};
                printer.printRecords(userID, itemID, rating);
                printer.print('\n');
            }
        }
        printer.close();
        parser.close();
    }

    public static void main(String[] args) {

        try {
            parseCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
