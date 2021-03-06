import java.util.List;
import java.io.FileNotFoundException;
import java.util.Arrays;



public class kNNMain{

  public static void main(String... args) throws FileNotFoundException{
	
	int k = 3;
	while (k < 50){
	double[] accuracies = new double [1000];
	double[] precisions  = new double [1000];
	double[] recalls = new double [1000];
    List<DataPoint> dataset = DataSet.readDataSet("data/breastCancer.csv");
	 
	for(int i = 0; i < accuracies.length; i ++){
		List<DataPoint> train_dataset = DataSet.getTrainingSet(dataset, 0.7);
		List<DataPoint> test_dataset = DataSet.getTrainingSet(dataset, 0.3);
		KNNClassifier classifier = new KNNClassifier(k);
		int total_num = test_dataset.size();
		double num_true_neg = 0.0; //so the division would work.
		double num_true_pos = 0.0;
		double num_false_pos = 0.0;
		double num_false_neg = 0.0;
		for (int j = 0; j < test_dataset.size(); j++)
		{
			String prediction = classifier.predict(train_dataset, test_dataset.get(j));
			//True negative
			if (prediction.length() == test_dataset.get(j).getLabel().length() && prediction.length() == 6) //benign
			{
				num_true_neg = num_true_neg + 1;
			}
			
			//True positive
			if (prediction.length() == test_dataset.get(j).getLabel().length() && prediction.length() == 9) //malignant
			{
				num_true_pos = num_true_pos + 1;
			}
			
			//False positive
			else if (prediction.length() > test_dataset.get(j).getLabel().length()) //if pred = malignant, truth = benign. 
			{
				num_false_pos = num_false_pos + 1;
			}
			
			//False negative
			else if (prediction.length() < test_dataset.get(j).getLabel().length()) //if pred = benign, truth = malignant. 
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
	
	//print for easy read
	//System.out.println("k: " + k);
	//System.out.println("mean of accuracy: " + (mean_accuracy*100) + "%	" + "standard deviation: " + (standardDeviation_accuracy*100));
	//System.out.println("mean of precision: " + (mean_precision*100) + "%	" + "standard deviation: " + (standardDeviation_precision*100));
	//System.out.println("mean of recall: " + (mean_recall*100) + "%	" + "standard deviation: " + (standardDeviation_recall*100));
	
	
	//print for graph
	System.out.println(k);
	System.out.println((mean_accuracy*100));
	System.out.println((mean_precision*100));
	System.out.println((mean_recall*100));
	
	k = k + 5; 
	}//for k loop
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
