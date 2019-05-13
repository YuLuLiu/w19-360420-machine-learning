import java.util.List;
import java.io.FileNotFoundException;
import java.util.Arrays;



public class RandomPred{

  public static void main(String... args) throws FileNotFoundException{
	  
	int k = 3; //the k value of the k nearest neighbors
	double[] accuracies = new double [1000];
	double[] precisions  = new double [1000];
	double[] recalls = new double [1000];
    List<DataPoint> dataset = DataSet.readDataSet("data/breastCancer.csv");
	 
	for(int i = 0; i < accuracies.length; i ++){
		List<DataPoint> train_dataset = DataSet.getTrainingSet(dataset, 0.7);
		List<DataPoint> test_dataset = DataSet.getTrainingSet(dataset, 0.3);
		int total_num = test_dataset.size();
		double num_true_neg = 0.0; //so the division would work.
		double num_true_pos = 0.0;
		double num_false_pos = 0.0;
		double num_false_neg = 0.0;
		for (int j = 0; j < test_dataset.size(); j++)
		{
			//random prediction
			double random = Math.random();
			int prediction = 0;
			if (random < 0.5){
				prediction = 6; //benign
			}
			else{
				prediction = 9; //malignant
			}
			//True negative
			if (prediction == test_dataset.get(j).getLabel().length() && prediction == 6) //benign
			{
				num_true_neg = num_true_neg + 1;
			}
			
			//True positive
			if (prediction == test_dataset.get(j).getLabel().length() && prediction == 9) //malignant
			{
				num_true_pos = num_true_pos + 1;
			}
			
			//False positive
			else if (prediction > test_dataset.get(j).getLabel().length()) //if pred = malignant, truth = benign. 
			{
				num_false_pos = num_false_pos + 1;
			}
			
			//False negative
			else if (prediction < test_dataset.get(j).getLabel().length()) //if pred = benign, truth = malignant. 
			{
				num_false_neg = num_false_neg + 1;
			}
		}//loop in dataset
		
		accuracies[i] = (num_true_neg + num_true_pos)/total_num;
		precisions[i] = num_true_pos/(num_false_pos + num_true_pos);
		recalls[i] = num_true_pos/(num_true_pos + num_false_neg);
		
	}//loop n times through the datasets
	double mean_accuracy = mean(accuracies);
	double standardDeviation_accuracy = standardDeviation(accuracies);
	
	double mean_precision = mean(precisions);
	double standardDeviation_precision = standardDeviation(precisions);
	
	double mean_recall = mean(recalls);
	double standardDeviation_recall = standardDeviation(recalls);
	
	System.out.println("mean of accuracy: " + (mean_accuracy*100) + "%	" + "standard deviation: " + (standardDeviation_accuracy*100));
	System.out.println("mean of precision: " + (mean_precision*100) + "%	" + "standard deviation: " + (standardDeviation_precision*100));
	System.out.println("mean of recall: " + (mean_recall*100) + "%	" + "standard deviation: " + (standardDeviation_recall*100));
  }//main

	
public static double mean(double[] arr){
    /*
    Method that takes as an argument an array of doubles
    Returns: average of the elements of array, as a double
    */
    double sum = 0.0;

    for (double a : arr){
      sum += a;
    }
    return (double)sum/arr.length;
  }

 public static double standardDeviation(double[] arr){
    /*
    Method that takes as an argument an array of doubles
    Returns: standard deviation of the elements of array, as a double
    Dependencies: requires the *mean* method written above
    */
    double avg = mean(arr);
    double sum = 0.0;
    for (double a : arr){
      sum += Math.pow(a-avg,2);
    }
    return (double)sum/arr.length;
  }
	

  

}
