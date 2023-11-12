public enum Method{
  NAIVE, /** Select the Review of highest sample mean */
  PAIRED, /** Take the sample ratings of each Review, add a high score and low score, recalculate sample means, and then apply Naive */
  LOWER_BOUND /** Take the sample ratings of each Review, create a 95% CI for each, and return the Review of the highest lower bound */
}
