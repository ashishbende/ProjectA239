package com.sjsu.cmp239.dataPreprocessing;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by ashish on 4/14/16.
 */
public class PreProcessor {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String filePath = "/assets/input/movieDataSet.csv";
        String outputFile = "/assets/output/movieDataSetOutput.csv";
        //boolean alreadyExists = new File(outputFile).exists();

        try {

            CsvReader userMovie = new CsvReader(filePath);

            CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');

            csvOutput.write("UserId");
            csvOutput.write("Item");
            csvOutput.write("Ratting");

            //Method to to end a record
            csvOutput.endRecord();

            //Reading headers of the input file
            String headers[] = userMovie.getHeaders();

            //Making an array of length same as headers to store the data of each row from
            //source file and convert it to desired format
            String rows[] = new String[headers.length];

            //Iterating the input file till the last record
            while (userMovie.readRecord()) {
                //Initializing counter for iterating each row and writing to columns and rows
                //as required by destination format
                int j = 0;

                //Storing the row data from source
                while (j < rows.length) {
                    rows[j] = userMovie.get(j);
                    j++;
                }

                //Writing the userid part of the destination
                csvOutput.write(rows[1]);

                //Creating item id and its ratting against userid from source to destination
                for (int k = 5; k < rows.length; k++) {
                    if (k == 5) {
                        csvOutput.write(headers[k]);
                        csvOutput.write(rows[k]);
                        csvOutput.endRecord();
                    } else {
                        csvOutput.write(null);
                        csvOutput.write(headers[k]);
                        csvOutput.write(rows[k]);
                        csvOutput.endRecord();
                    }
                }
                //rows=null;
//				rows[i]
/*				String userID = userMovie.get(1);
                String starWars = userMovie.get(5);
				String spotlight = userMovie.get(6);
				/*String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);
				String spotlight = userMovie.get(6);*/

				/*String productName = userMovie.get("ProductName");
                String supplierID = userMovie.get("SupplierID");
				String categoryID = userMovie.get("CategoryID");
				String quantityPerUnit = userMovie.get("QuantityPerUnit");
				String unitPrice = userMovie.get("UnitPrice");
				String unitsInStock = userMovie.get("UnitsInStock");
				String unitsOnOrder = userMovie.get("UnitsOnOrder");
				String reorderLevel = userMovie.get("ReorderLevel");
				String discontinued = userMovie.get("Discontinued");*/

                // perform program logic here
                //System.out.println( userID + " : "+starWars+" : "+spotlight);


				/*for(int i=0;i<=userMovie.getColumnCount()-5;i++){
					if(i==0){
						csvOutput.write(userID);
						csvOutput.write(starWars);
						csvOutput.write(spotlight);
						csvOutput.endRecord();
					}
					else{
						csvOutput.write(" ");
						csvOutput.write(starWars);
						csvOutput.write(spotlight);
						csvOutput.endRecord();
					}
				}*/
            }
            userMovie.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
