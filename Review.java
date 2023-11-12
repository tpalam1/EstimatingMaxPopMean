import java.util.ArrayList;
import java.util.Collections;

public class Review{
  private final int MIN_SAMPLE_SIZE = 30;
  private final int MAX_SAMPLE_SIZE = 200;
  
  /** Stores the list of ratings in this review. */
  private ArrayList<Double> list;
  
  /** Stores the true population mean of all ratings in this review. */
  private double populationMean;
  
  /** Stores the default sample size if it is not given. */
  private int defaultSampleSize;
  
  /** Stores the default sample to represent this Review. */
  private ArrayList<Double> defaultSample; 
  
  /** Creates an empty review. */
  public Review(){
    list = new ArrayList<>();
    defaultSampleSize = 0; 
    defaultSample = null;
  }
  
  /** Creates a review of a specified number of ratings. */
  public Review(int count){
    list = new ArrayList<>();
    for(int i = 0; i < count; i++){
      double curr = Math.random();
      list.add(curr);
    }
    
    setPopulationMean();
    defaultSampleSize = getRandInt(MIN_SAMPLE_SIZE, MAX_SAMPLE_SIZE);
    defaultSample = getRandSample(defaultSampleSize);
  }
  
  /**
   * Collects a random sample of a specified size. 
   * 
   * @throws RuntimeException if the sample size is greater than the number of reviews
  */
  public ArrayList<Double> getRandSample(int sampleSize){
    if(sampleSize > getCountRatings()){
      throw new RuntimeException("Sample size is larger than total number of ratings!");
    }
    
    Collections.shuffle(list);
    
    ArrayList<Double> output = new ArrayList<Double>();
    
    for(int i = 0; i < sampleSize; i++){
      output.add(list.get(i));
    }
    
    return output;
  }
  
  /** Returns the default sample associated with this Review. */
  public ArrayList<Double> getDefaultSample() { return defaultSample; }
  
  /** Returns the sample mean of the default sample size. */
  public double getSampleMean() { return getSampleMean(defaultSampleSize); }
  
  /** Returns the sample mean of a given count of observations. */
  public double getSampleMean(int sampleSize){ return getAvg(getRandSample(sampleSize)); } 
  
  /** Sets the population mean of this Review. */
  private void setPopulationMean(){
    populationMean = getAvg(list);
  }
  
  /** Returns the sum of elements in the given array. */
  private double getSum(ArrayList<Double> arr){
    double sum = 0;
    for(int i = 0; i < arr.size(); i++) { sum += arr.get(i); }
    return sum;
  }
  
  /** 
   * Returns the average of the given ArrayList.
   * 
   * @throws RuntimeException if the array has no elements.
  */
  private double getAvg(ArrayList<Double> arr){
    if(arr.size() == 0 || arr == null) { throw new RuntimeException("Given array has no elements!"); }
    return getSum(arr) / (double) arr.size();
  }
  
  /** Returns a list of all ratings in this review. */
  public ArrayList<Double> getRatings(){ return list; }
  
  /** Returns the number of ratings in the whole review. */
  public int getCountRatings(){ return list.size(); }
  
  /** Returns the population mean of this whole review. */
  public double getPopulationMean(){ return populationMean; }
  
  public static double getRandDouble(double min, double max)
  {
    return Math.random() * (max - min + 1) + min;
  } // returns a random double, [min, max]
  
  public static int getRandInt(int min, int max)
  {
    return (int) getRandDouble((double) min, (double) max);
  } // see getRandDouble(min, max)
  
  public static double fixDigits(double d)
	{
	  return Math.floor(d * 1000) / 1000;
	}
	
	/** Checks whether this review is equal to another. */
	public boolean isEquals(Review r){
	  if(this.getCountRatings() != r.getCountRatings()) { return false; }
	  return this.getPopulationMean() == r.getPopulationMean();
	}
  
  /** Returns a String representation of the list of ratings. */
  public String toString() {
    return "(x = " + fixDigits(getAvg(defaultSample)) + ", n = " + defaultSampleSize + ")";
  }
}
