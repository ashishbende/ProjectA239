package com.sjsu.cmp239;

import com.sjsu.cmp239.collaborativeFiltering.EvaluateRecommendations;
import com.sjsu.cmp239.collaborativeFiltering.ItemBasedRecommendation;
import com.sjsu.cmp239.collaborativeFiltering.UserBasedRecommendation;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;

/**
 * Created by ashish on 4/8/16.
 */
public class App {

    //csv file after data preprocessing.
    static final String INPUT_CSV_DATASET = "/home/ashish/SJSU/239/ProjectA239/src/main/assets/output/output.csv";


    public static void main(String[] args) {



        System.out.println(" \n ---- [UserbBased Recommendation]  ---- \n ");

        UserBasedRecommendation customUserRecommender = new UserBasedRecommendation();
        // set userID
        customUserRecommender.setUserID(204);
        // set noOfRecommendations
        customUserRecommender.setNoOfItemsRecommendations(3);
        customUserRecommender.setMovieRecommender(INPUT_CSV_DATASET);
        customUserRecommender.showRecommendations();

        System.out.println(" \n ---- [Item Based Recommendation]  ---- \n ");

        ItemBasedRecommendation customItemBasedRecommender = new ItemBasedRecommendation();
        customItemBasedRecommender.setUserID(204);
        customItemBasedRecommender.setNoOfItemsRecommendations(3);
        customItemBasedRecommender.setItemRecommender(INPUT_CSV_DATASET);
        customItemBasedRecommender.showRecommendations();


        System.out.println(" \n ---- [UserbBased Recommendation] Evaluation Results ---- \n ");
        EvaluateRecommendations userRecoEval = new EvaluateRecommendations();
        EvaluateRecommendations.setTypeOfRecommendation("ub");
        EvaluateRecommendations.setRecommender((GenericUserBasedRecommender) customUserRecommender.getMovieRecommender());
        EvaluateRecommendations.setDataModel(customUserRecommender.getMovieModel());
        userRecoEval.evalRecommender();


        System.out.println(" \n ---- [ItemBased Recommendation] Evaluation Results ---- \n ");
        EvaluateRecommendations itemRecoEval = new EvaluateRecommendations();
        EvaluateRecommendations.setTypeOfRecommendation("ib");
        EvaluateRecommendations.setRecommender((GenericUserBasedRecommender) customUserRecommender.getMovieRecommender());
        EvaluateRecommendations.setDataModel(customItemBasedRecommender.getMovieModel());
        itemRecoEval.evalRecommender();
    }
}
