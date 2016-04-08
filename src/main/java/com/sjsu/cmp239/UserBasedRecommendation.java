package com.sjsu.cmp239;

import java.io.*;
import java.util.*;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;

/*
*   Source: https://mahout.apache.org/users/recommender/userbased-5-minutes.html
*   Created by Ashish 04/01/2016
*   User Based Recommendation
* */

public class UserBasedRecommendation {

     static long userID =0;
     static int noOfItemsRecommendations = 1;
     static UserBasedRecommender movieRecommender = null;


    public  void buildRecommender(String csvFile) {

        try {

            //get data-model from movieCSV File
            DataModel movieModel = new FileDataModel(new File(csvFile));

            // build customerSimilarity coeffient
            // we will use pearson correlation similarity.
            UserSimilarity customerSimilarity = new PearsonCorrelationSimilarity(movieModel);

            // We will use all users who have similarity greater than 0.1
            UserNeighborhood userNeighborhood = new ThresholdUserNeighborhood(0.1, customerSimilarity, movieModel);

            // Finally we will create our moive recommender.
            movieRecommender = new GenericUserBasedRecommender(movieModel, userNeighborhood, customerSimilarity);
            //Step 4:- Create object of UserBasedRecommender or ItemBasedRecommender



        } catch (Exception e) {
            System.out.println("There was an error.");
            e.printStackTrace();
        }


    }

    public void showRecommendations(){

        List<RecommendedItem> recommendations = null;
        try {
            recommendations = movieRecommender.recommend(userID, noOfItemsRecommendations);
        } catch (TasteException e) {
            System.out.println("Taste Exception in recommender");
            e.printStackTrace();
        }

        for (RecommendedItem recommendation : recommendations) {
            System.out.println("Movie : " + recommendation.getValue());
        }
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public int getNoOfItemsRecommendations() {
        return noOfItemsRecommendations;
    }

    public void setNoOfItemsRecommendations(int noOfItemsRecommendations) {
        this.noOfItemsRecommendations = noOfItemsRecommendations;
    }


}