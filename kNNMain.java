import java.util.List;
import java.io.FileNotFoundException;
import java.util.Arrays;



public class kNNMain{

  public static void main(String... args) throws FileNotFoundException{
	  
	int k = 3; //the k value of the k nearest neighbors
	int show = 1;
    // TASK 1: Use command line arguments to point DataSet.readDataSet method to
    // the desired file. Choose a given DataPoint, and print its features and label

	 List<DataPoint> dataset = DataSet.readDataSet("data/breastCancer.csv");
	 
	 for (int i = 0; i < dataset.get(1).x.length; i++){
	 System.out.print(dataset.get(1).getX()[i] + "   ");
	 }
	 System.out.println(dataset.get(1).getLabel());

    //TASK 2:Use the DataSet class to split the fullDataSet into Training and Held Out Test Dataset
	List<DataPoint> train_dataset = DataSet.getTrainingSet(dataset, 0.8);
	List<DataPoint> test_dataset = DataSet.getTrainingSet(dataset, 0.2);
	//System.out.println("train: ...");
	//DataSet.printDataSet(train_dataset);
	//System.out.println("test: ...");
	//DataSet.printDataSet(test_dataset);
	
	//show one datapoint of train_dataset
	for (int i = 0; i < dataset.get(show).x.length; i++){
	 System.out.print(dataset.get(show).getX()[i] + "   ");
	 }
	 System.out.println(dataset.get(show).getLabel());

    // TASK 5: Use the KNNClassifier class to determine the k nearest neighbors to a given DataPoint,
    // and make a print a predicted target label
	
	//find the three nearest neighbors
	KNNClassifier classifier = new KNNClassifier(k);
	DataPoint[] neighbors = classifier.getNearestNeighbors(train_dataset, train_dataset.get(show));
	//System.out.println(Arrays.toString(neighbors)); 
	//print the three nearest points
	String predicted_label_show = classifier.predict(train_dataset, train_dataset.get(show));
	System.out.println("prediction:  " + predicted_label_show);
	
    // TASK 6: loop over the datapoints in the held out test set, and make predictions for Each
    // point based on nearest neighbors in training set. Calculate accuracy of model.
	
	int total_num = train_dataset.size();
	double num_good_pred = 0.0;
	for (int i = 0; i < train_dataset.size(); i++)
	{
		String prediction = classifier.predict(train_dataset, train_dataset.get(i));
		System.out.println("predicted: " + prediction + "		" + "target: " + train_dataset.get(i).getLabel());
		if (prediction.length() == train_dataset.get(i).getLabel().length())
		{
			num_good_pred = num_good_pred + 1;
		}
	}

	double accuracy = num_good_pred/total_num;
	System.out.println("accuracy on training data: " + accuracy);  
	
	total_num = test_dataset.size();
	num_good_pred = 0.0;
	
	for (int i = 0; i < test_dataset.size(); i++)
	{
		String prediction = classifier.predict(train_dataset, test_dataset.get(i));
		System.out.println("predicted: " + prediction + "		" + "target: " + test_dataset.get(i).getLabel());
		if (prediction.length() == test_dataset.get(i).getLabel().length())
		{
			num_good_pred = num_good_pred + 1;
		}
	}
	
	accuracy = num_good_pred/total_num;
	System.out.println("accuracy on test data: " + accuracy); 
	

  }

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
