package com.sjsu.cmp239;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.io.File;
import java.util.List;

/**
 * Created by ashish on 4/8/16.
 */
public class ItemBasedRecommendation {


    static long userID = 0;
    static int noOfItemsRecommendations = 1;
    static ItemBasedRecommender itemRecommender = null;
    static DataModel movieModel = null;

    public static void buildRecommender(String csvFile) {

        try {

            //get data-model from movieCSV File
            movieModel = new FileDataModel(new File(csvFile));

            // build customerSimilarity coeffient
            // we will use pearson correlation similarity.
            ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(movieModel);

            // Finally we will create our moive recommender.
            itemRecommender = new GenericItemBasedRecommender(movieModel, itemSimilarity);

            //Step 4:- Create object of UserBasedRecommender or ItemBasedRecommender


        } catch (Exception e) {
            System.out.println("There was an error.");
            e.printStackTrace();
        }


    }

    public void showRecommendations() {

        List<RecommendedItem> recommendations = null;
        try {
            recommendations = itemRecommender.recommend(userID, noOfItemsRecommendations);
        } catch (TasteException e) {
            System.out.println("Taste Exception in recommender");
            e.printStackTrace();
        }

        for (RecommendedItem recommendation : recommendations) {
            System.out.println("Movie : " + recommendation.getValue());
        }
    }



    public  DataModel getMovieModel() {
        return movieModel;
    }


    public  ItemBasedRecommender getMovieRecommender() {
        return itemRecommender;
    }

    public  void setMovieRecommender(String csvFile) {
        buildRecommender(csvFile);
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
