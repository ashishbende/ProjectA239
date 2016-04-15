package com.sjsu.cmp239.collaborativeFiltering;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 * Created by ashish on 4/8/16.
 */
public class EvaluateRecommendations {

    static GenericUserBasedRecommender recommender = null;
    static DataModel dataModel = null;

   /* public EvaluateRecommendations(DataModel movieModel,GenericUserBasedRecommender recommender ){
        dataModel = movieModel;
        recommender = recommender;
    }
    */

    public void evalRecommender(){

        RecommenderEvaluator evaluator=new AverageAbsoluteDifferenceRecommenderEvaluator();
        RecommenderBuilder recommenderBuilder = new RecommendationBuilder();
        try {
            recommenderBuilder.buildRecommender(dataModel);
            double result = evaluator.evaluate(recommenderBuilder,null,dataModel,0.9,0.1);
            System.out.println("Recommonder Evaluation : " +result);
        } catch (TasteException e) {
            System.out.println("DataModel input error");
            e.printStackTrace();
        }

    }

    public static GenericUserBasedRecommender getRecommender() {
        return recommender;
    }

    public static void setRecommender(GenericUserBasedRecommender recommender) {
        EvaluateRecommendations.recommender = recommender;
    }

    public static DataModel getDataModel() {
        return dataModel;
    }

    public static void setDataModel(DataModel dataModel) {
        EvaluateRecommendations.dataModel = dataModel;
    }


    class RecommendationBuilder implements RecommenderBuilder{

        public Recommender buildRecommender(DataModel dataModel) throws TasteException {

            UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
            UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, dataModel);
            return new GenericUserBasedRecommender(dataModel, neighborhood, similarity);

        }
    }

}
