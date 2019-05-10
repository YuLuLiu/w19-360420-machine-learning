import java.util.List;
import java.io.FileNotFoundException;
import java.util.Arrays;



public class kNNMain{

  public static void main(String... args) throws FileNotFoundException{

    // TASK 1: Use command line arguments to point DataSet.readDataSet method to
    // the desired file. Choose a given DataPoint, and print its features and label

	 List<DataPoint> test_dataset = DataSet.readDataSet("data/iris.csv");
	 
	 for (int i = 0; i < test_dataset.get(1).x.length; i++){
	 System.out.print(test_dataset.get(1).getX()[i]);
	 }
	 System.out.print(test_dataset.get(1).getLabel());

    //TASK 2:Use the DataSet class to split the fullDataSet into Training and Held Out Test Dataset
	List<DataPoint> train_dataset = DataSet.getTrainingSet(test_dataset, 0.8);
	test_dataset.removeAll(train_dataset);
	System.out.println("train: ...");
	DataSet.printDataSet(train_dataset);
	System.out.println("test: ...");
	DataSet.printDataSet(test_dataset);


    // TASK 5: Use the KNNClassifier class to determine the k nearest neighbors to a given DataPoint,
    // and make a print a predicted target label



    // TASK 6: loop over the datapoints in the held out test set, and make predictions for Each
    // point based on nearest neighbors in training set. Calculate accuracy of model.


  }

}
