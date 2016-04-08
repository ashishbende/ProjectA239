package com.sjsu.cmp239;

import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;

/**
 * Created by ashish on 4/8/16.
 */
public class App {

    static final String INPUT_CSV_DATASET = "/assets/movieDataSet.csv";


    public static void main(String[] args) {

        UserBasedRecommendation customRecommender = new UserBasedRecommendation();
        // set userID
        customRecommender.setUserID(2);
        // set noOfRecommendations
        customRecommender.setNoOfItemsRecommendations(3);
        customRecommender.buildRecommender(INPUT_CSV_DATASET);
        customRecommender.showRecommendations();
    }
}
