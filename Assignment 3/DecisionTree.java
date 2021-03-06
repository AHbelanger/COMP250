/* Name: Antoine Hamasaki-Belanger
 * id: 260800991
 * */


import java.io.Serializable;
import java.util.ArrayList;
import java.text.*;
import java.lang.Math;

public class DecisionTree implements Serializable {

	DTNode rootDTNode;
	int minSizeDatalist; //minimum number of datapoints that should be present in the dataset so as to initiate a split
	//Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.
	public static final long serialVersionUID = 343L;
	public DecisionTree(ArrayList<Datum> datalist , int min) {
		minSizeDatalist = min;
		rootDTNode = (new DTNode()).fillDTNode(datalist);
	}

	class DTNode implements Serializable{
		//Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.
		public static final long serialVersionUID = 438L;
		boolean leaf;
		int label = -1;      // only defined if node is a leaf
		int attribute; // only defined if node is not a leaf
		double threshold;  // only defined if node is not a leaf



		DTNode left, right; //the left and right child of a particular node. (null if leaf)

		DTNode() {
			leaf = true;
			threshold = Double.MAX_VALUE;
		}



		// this method takes in a datalist (ArrayList of type datum) and a minSizeInClassification (int) and returns
		// the calling DTNode object as the root of a decision tree trained using the datapoints present in the
		// datalist variable
		// Also, KEEP IN MIND that the left and right child of the node correspond to "less than" and "greater than or equal to" threshold
		DTNode fillDTNode(ArrayList<Datum> datalist) {
			
			//if at least minimum number of points
			if (datalist.size() >= minSizeDatalist){
				
				//check if have same label
				boolean label = true;
				for(int i = 0; i < datalist.size() - 1; i++) {
					Datum cur = datalist.get(i);
					Datum next = datalist.get(i + 1);
		
					//compare lable of each datum
					if (cur.y != next.y)
						label = false;
				}
				
				if (label) {
					//leaf node
					DTNode newLeaf = new DTNode();
					newLeaf.label = datalist.get(0).y;
					newLeaf.leaf = true;
					return newLeaf;
				}
				else{
					ArrayList<Datum> firstList = new ArrayList<Datum>();
					ArrayList<Datum> secondList = new ArrayList<Datum>();
					
					//internal node
					DTNode newNode = new DTNode();
					newNode.leaf = false;
					findBestSplit(datalist,newNode);
					
					//split into the 2 lists
					for(int j = 0; j <datalist.size(); j++){
						Datum cur = datalist.get(j);
						
						if(cur.x[newNode.attribute] < newNode.threshold)
							firstList.add(cur);
						
						else
							secondList.add(cur);
					}

					newNode.right = fillDTNode(secondList);
					newNode.left = fillDTNode(firstList);
					
					return newNode;
				}
			}
			
			else {
				//less points than minumum so find majority
				DTNode newLeaf = new DTNode();
				newLeaf.leaf = true;
				newLeaf.label = findMajority(datalist);
				return newLeaf;
			}
		}
		
		
		//helper method to find the best split
		public void findBestSplit(ArrayList<Datum> datalist, DTNode newNode){
			//set to 10 since value will be between 0 and 1
			double best_Avg_Entropy = 10;
			//set to -1 since value will be either 0 or 1
			int best_Attribute = -1;
			//set to -1 since only pos. values
			double best_Threshold = -1;
			double curr_Threshold;
			double curr_Avg_Entropy;
					
			//for each attribute
			for(int i = 0; i < 2; i++){
				//for each datapoint
				for(int dataIndex = 0; dataIndex < datalist.size(); dataIndex++){
					
					curr_Threshold = datalist.get(dataIndex).x[i];
					ArrayList<Datum> firstSplit = new ArrayList<Datum>();
					ArrayList<Datum> secondSplit = new ArrayList<Datum>();
				
					//split the list
					for(int j = 0; j <datalist.size(); j++){
						Datum cur = datalist.get(j);
						
						if(cur.x[i] < curr_Threshold)
							firstSplit.add(cur);
						
						else
							secondSplit.add(cur);
					}
					curr_Avg_Entropy= firstSplit.size() / (double) datalist.size()*calcEntropy(firstSplit) + 
							secondSplit.size() / (double) datalist.size()*calcEntropy(secondSplit);
					
					if(best_Avg_Entropy > curr_Avg_Entropy){
						best_Avg_Entropy = curr_Avg_Entropy;
						best_Attribute = i;
						best_Threshold = curr_Threshold;
					}
				}
			}
					
			// Apply best attribute and threhsold
			newNode.attribute = best_Attribute;
			newNode.threshold = best_Threshold;
		}
			

		//This is a helper method. Given a datalist, this method returns the label that has the most
		// occurences. In case of a tie it returns the label with the smallest value (numerically) involved in the tie.
		int findMajority(ArrayList<Datum> datalist)
		{
			int l = datalist.get(0).x.length;
			int [] votes = new int[l];

			//loop through the data and count the occurrences of datapoints of each label
			for (Datum data : datalist)
			{
				votes[data.y]+=1;
			}
			int max = -1;
			int max_index = -1;
			//find the label with the max occurrences
			for (int i = 0 ; i < l ;i++)
			{
				if (max<votes[i])
				{
					max = votes[i];
					max_index = i;
				}
			}
			return max_index;
		}




		// This method takes in a datapoint (excluding the label) in the form of an array of type double (Datum.x) and
		// returns its corresponding label, as determined by the decision tree
		int classifyAtNode(double[] xQuery) {
			//if node is leaf, return label 
			if (this.leaf)
				return this.label;
			
			//if data point coordinate smaller than threshold, go to left child
			else if (xQuery[this.attribute] < this.threshold)
				return this.left.classifyAtNode(xQuery);
			
			//if data point coordinate larger than threshold, go to right child
			else
				return this.right.classifyAtNode(xQuery);
		}


		//given another DTNode object, this method checks if the tree rooted at the calling DTNode is equal to the tree rooted
		//at DTNode object passed as the parameter
		public boolean equals(Object dt2)
		{
			
			if(dt2 == null)
				return false;
			
			DTNode firstNode = this;
			boolean firstLeaf = firstNode.leaf;
			int firstLabel = firstNode.label;
			int firstAttribute = firstNode.attribute;
			double firstThreshold = firstNode.threshold;
			
			DTNode secondNode = (DTNode) dt2;
			boolean secondLeaf = secondNode.leaf;
			int secondLabel = secondNode.label;
			int secondAttribute = secondNode.attribute;
			double secondThreshold = secondNode.threshold;
			
			if(firstLabel != secondLabel || firstLeaf != secondLeaf || firstThreshold != secondThreshold || firstAttribute != secondAttribute)
				return false;

			else if(firstLeaf)
				return true;
			
			else{
				if(firstNode.right == null || secondNode.right == null || firstNode.left == null || secondNode.left == null)
					return false;
			
				return (firstNode.left.equals(secondNode.left)) && (firstNode.right.equals(secondNode.right));
			}
		
		}	
	}	
	
	//Given a data set, this returns the entropy of the data set
	double calcEntropy(ArrayList<Datum> datalist)
	{
		double entropy = 0;
		double px = 0;
		float [] counter= new float[2];
		if (datalist.size()==0)
			return 0;
		double num0 = 0.00000001,num1 = 0.000000001;

		//calculates the number of points belonging to each of the labels
		for (Datum d : datalist)
		{
			counter[d.y]+=1;
		}
		//calculates the entropy using the formula specified in the document
		for (int i = 0 ; i< counter.length ; i++)
		{
			if (counter[i]>0)
			{
				px = counter[i]/datalist.size();
				entropy -= (px*Math.log(px)/Math.log(2));
			}
		}

		return entropy;
	}


	// given a datapoint (without the label) calls the DTNode.classifyAtNode() on the rootnode of the calling DecisionTree object
	int classify(double[] xQuery ) {
		DTNode node = this.rootDTNode;
		return node.classifyAtNode( xQuery );
	}

    // Checks the performance of a DecisionTree on a dataset
    //  This method is provided in case you would like to compare your
    //results with the reference values provided in the PDF in the Data
    //section of the PDF

    String checkPerformance( ArrayList<Datum> datalist)
	{
		DecimalFormat df = new DecimalFormat("0.000");
		float total = datalist.size();
		float count = 0;

		for (int s = 0 ; s < datalist.size() ; s++) {
			double[] x = datalist.get(s).x;
			int result = datalist.get(s).y;
			if (classify(x) != result) {
				count = count + 1;
			}
		}

		return df.format((count/total));
	}


	//Given two DecisionTree objects, this method checks if both the trees are equal by
	//calling onto the DTNode.equals() method
	public static boolean equals(DecisionTree dt1,  DecisionTree dt2)
	{
		boolean flag = true;
		flag = dt1.rootDTNode.equals(dt2.rootDTNode);
		return flag;
	}

}
