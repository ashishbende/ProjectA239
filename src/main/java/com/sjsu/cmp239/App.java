package com.sjsu.cmp239;

import com.sjsu.cmp239.collaborativeFiltering.EvaluateRecommendations;
import com.sjsu.cmp239.collaborativeFiltering.ItemBasedRecommendation;
import com.sjsu.cmp239.collaborativeFiltering.UserBasedRecommendation;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;

/**
 * Created by ashish on 4/8/16.
 */
public class App {

    static final String INPUT_CSV_DATASET = "/assets/input/movieDataSet.csv";


    public static void main(String[] args) {

        UserBasedRecommendation customUserRecommender = new UserBasedRecommendation();
        // set userID
        customUserRecommender.setUserID(2);
        // set noOfRecommendations
        customUserRecommender.setNoOfItemsRecommendations(3);
        customUserRecommender.setMovieRecommender(INPUT_CSV_DATASET);
        customUserRecommender.showRecommendations();


        ItemBasedRecommendation customItemBasedRecommender = new ItemBasedRecommendation();
        customItemBasedRecommender.setUserID(2);
        customItemBasedRecommender.setNoOfItemsRecommendations(3);
        customItemBasedRecommender.showRecommendations();


        System.out.println(" \n ---- [UserbBased Recommendation] Evaluation Results ---- \n ");
        EvaluateRecommendations userRecoEval = new EvaluateRecommendations();
        EvaluateRecommendations.setRecommender((GenericUserBasedRecommender) customUserRecommender.getMovieRecommender());
        EvaluateRecommendations.setDataModel(customUserRecommender.getMovieModel());
        userRecoEval.evalRecommender();


        System.out.println(" \n ---- [ItemBased Recommendation] Evaluation Results ---- \n ");
        EvaluateRecommendations itemRecoEval = new EvaluateRecommendations();
        EvaluateRecommendations.setRecommender((GenericUserBasedRecommender) customUserRecommender.getMovieRecommender());
        EvaluateRecommendations.setDataModel(customItemBasedRecommender.getMovieModel());
        itemRecoEval.evalRecommender();
    }
}
