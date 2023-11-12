import java.util.Collections;
import java.util.ArrayList;
import java.lang.RuntimeException;

public class Main
{
	public static void main(String[] args) {
	  int COUNT_ROUNDS = 100;
 		double COUNT_FOUND_OPTIMAL_REVIEW_NAIVE = 0;
	  
	  for(int i = 0; i < COUNT_ROUNDS; i++)
	  {
  		int COUNT_RATINGS_PER_REVIEW = 6000;
  		int COUNT_REVIEWS = 100;
  		
  		
  		ArrayList<Review> arrReviews = popArr(COUNT_REVIEWS, COUNT_RATINGS_PER_REVIEW);
  		
  		ArrayList<Double> arrSampleMeans = popArrSampleMeans(arrReviews);
  		
  		Review optimalReview = getOptimalReview(arrReviews);
  		Review suggestedNaiveReview = getSuggestedReview(Method.NAIVE, arrReviews, arrSampleMeans);
  		
  		boolean hasFoundOptimalReviewNaive = optimalReview.isEquals(suggestedNaiveReview);
  		if(hasFoundOptimalReviewNaive) { COUNT_FOUND_OPTIMAL_REVIEW_NAIVE++; }
	  }
	  
	  double sucessRateNaive = COUNT_FOUND_OPTIMAL_REVIEW_NAIVE / COUNT_ROUNDS;
	  System.out.println(sucessRateNaive);
	}
	
	/** Returns the Review in the List with the highest population mnea. */
	public static Review getOptimalReview(ArrayList<Review> arrReviews){
	  ArrayList<Double> arrPopulationMean = getPopulationMeans(arrReviews);
	  
	  double maxPopMean = Collections.max(arrPopulationMean);
	  int index = arrPopulationMean.indexOf(maxPopMean);
	  
	  return arrReviews.get(index);
	}
	
	/** Returns a List of the true population means of the given Reviews. */
	public static ArrayList<Double> getPopulationMeans(ArrayList<Review> arrReviews){
	  ArrayList<Double> arrPopulationMean = new ArrayList<Double>();
	  
	  for(int i = 0; i < arrReviews.size(); i++){
	    Review currReview = arrReviews.get(i);
	    arrPopulationMean.add(currReview.getPopulationMean());
	  }
	  
	  return arrPopulationMean;
	}
	
	/** Given a list of Reviews, applies a Method to recommend the Review of the probable highest population mean. */
	public static Review getSuggestedReview(Method method, ArrayList<Review> arrReviews, ArrayList<Double> arrSampleMeans){
	  switch(method){
	    case NAIVE:
	      return getNaiveSuggestedReview(arrReviews, arrSampleMeans);
      default:
        throw new RuntimeException("There isn't a valid Method passed to this function!");
	  }
	}
	
	/** Returns the Review of the highest sample mean. */
	public static Review getNaiveSuggestedReview(ArrayList<Review> arrReviews, ArrayList<Double> arrSampleMeans){
	  double maxVal = Collections.max(arrSampleMeans);
	  int index = arrSampleMeans.indexOf(maxVal);
	  return arrReviews.get(index);
	}
	
	/** Returns a List of Reviews, which each of have a consistent number of Ratings. */
	public static ArrayList<Review> popArr(int COUNT_REVIEWS, int COUNT_RATINGS_PER_REVIEW){
	  ArrayList<Review> output = new ArrayList<>();
	  
	  for(int i = 0; i < COUNT_REVIEWS; i++){
	    output.add(new Review(COUNT_RATINGS_PER_REVIEW));
	  }
	  
	  return output;
	}
	
	/** Given a List of Reviews, returns a 2D List of rating samples taken from the Reviews. */
	public static ArrayList<ArrayList<Double>> popArrSamples(ArrayList<Review> arrReviews, int COUNT_SAMPLE_SIZE){
	  ArrayList<ArrayList<Double>> output = new ArrayList<ArrayList<Double>>();
	  
	  for(int i = 0; i < arrReviews.size(); i++){
	    Review currReview = arrReviews.get(i);
	    ArrayList<Double> currSample = currReview.getRandSample(COUNT_SAMPLE_SIZE);
	    output.add(currSample);
	  }
	  
	  return output;
	}
	
	/** Returns an array of sample means. */
	public static ArrayList<Double> popArrSampleMeans(ArrayList<Review> arrReviews){
	  ArrayList<Double> output = new ArrayList<>();
	  
	  for(int i = 0; i < arrReviews.size(); i++){
	    Review currReview = arrReviews.get(i);

	    double currSampleMean = currReview.getSampleMean();
	    output.add(currSampleMean);
	  }
	  return output;
	}
}
