# K-Nearest_Neighbors
## 360-420-DW - Section 2
## Author: Yu Lu Liu
 
 ### The amount of confidence we can put in our model
##### Each time you run the classification model, you should be getting a different accuracy. Why? (hint: lines 148-150 in `DataSet.java`)
Lines 148 - 150 are:
```
// Random rnd = new Random(123);
// Collections.shuffle(fullDataSet, rnd);
Collections.shuffle(fullDataSet);
```
These functions shuffle the entire dataset, and then a selection is made to form the training set and the test test. Therefore, each time I run the model, the training set and test set I get are different, which explains the difference in accuracy at the end. 

##### Run the entire classification process 1000 times (load data, split into off 30% for a test set, evaluate model performance). store the results of each run in a `double[]`; use the `mean` and `standardDeviation` methods in `kNNMain.java` to calculate how much performance can be expected to vary on unseen data. 
The mean accuracy I got is 97.69% with a standard deviation of 0.0089. Since the standard deviation is very small, performance did not vary much for me. 

##### What is a sensible baseline against which we should compare our model's performance? (hint: line 200 in `DataSet.java`)
Line 200 in `DataSet.java` is a method that display the frequencies of labels in the dataset. In the dataset that I used, `breastCancer.csv`, there are 444 datapoints representing benigh tumors and 237 datapoints representing malignant tumors. If the the model always predict the most frequent class in the dataset, which is in this case the benign tumors, the accuracy percentage would be 444/(444+237) = 0.6520. Since 97.69% is higher than 65.2%, the model is predicting better than the baseline model. 

### the types of errors that our classifier makes
##### *Accuracy* is only one way that we can evaluate model performance. However in the context of medical diagnosis, different types of classification errors carry importances.
what is a False Positive, a False Negative?
A false positive is when we predict a positive answer, but the truth is actually negative. In the case of predicting whether a tumor is benigh or malignant, a false positive would be when the model predicts that the tumor is malignant when it is actually benign. A false negative is the inverse. We predict a negative answer, but the truth is actually positive. In this case, a false negative would be when the model predicts that the tumor is benign when it is actually malignant. A false negative is therefore more serious than a false positive since the patient, receiving the negative answer, will not be treated. 

##### Extend your analysis in the previous step (with the 1000 runs) to keep track of **Recall** and **Precision** as well.
What makes these two measures different?
I obtained:
- mean of precision: 96.14% with a standard deviation of 0.042
- mean of recall: 97.32% with a standard deviation of 0.033
In this case, the precision is the percentage of truly malignant tumors over all the malignant tumors the model predicts. It measures how precise the prediction is. The recall is the percentage of truly malignant tumors over all the truly malignant tumors in the dataset. It measures how complete the prediction is. 

##### What are sensible baseline for each of these measures? 
If we use the same baseline prediction as before, which means that we always predict the most frequent class,
then we would have 444 true negatives, 237 true positives, 0 false positives and 237 false negatives. 
Based on these numbers, the precision would be 100% and the recall would be 50%. Since the precision and the recall are not balanced, this is not a great baseline.
If we use a model which predicts randomly (`RandomPred.java`), here are the following results:
mean of precision: 34.75% 
mean of recall: 49.95%
Since the KNN model's precision and recall are both greater than this baseline, the KNN model is predicting better than the random one.

##### How do the above results change with the **hyperparameter** *k*?
I created a loop that increases the hyperparameter k at each iteration. 
The accuracy percentage decreased. The precision percentage increased. The recall percentage decreased. These trends are illustrated in the graph below.
![alt text](??? "Graph")