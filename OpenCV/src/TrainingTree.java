import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import javax.swing.JFrame;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSink;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;



public class TrainingTree {

	final static Charset ENCODING = StandardCharsets.UTF_8;
	final static String CONFIG = "C:/Users/Bawornsak.S/Desktop/Research/Data/config.txt";
	final static String FILE_NAME = "C:/Users/Bawornsak.S/Desktop/Research/Data/iris.csv";

	private String[] trainingAttribute = null;
	private String[] hiddenAttribute = null;
	private Instances data1;
	private Instances original;

	
	public TrainingTree(){
		
	}
	
	
	//----------------------------------------------------------------
	// Load Data from a File (16.2)
	//----------------------------------------------------------------

	public void loadConfig(String config){
		try (BufferedReader br = new BufferedReader(new FileReader(config)))
		{
			String sCurrentLine;

			if((sCurrentLine = br.readLine()) != null)
				trainingAttribute = sCurrentLine.split(",");

			if((sCurrentLine = br.readLine()) != null)
				hiddenAttribute = sCurrentLine.split(",");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//----------------------------------------------------------------
	// Load Data from a File (16.2)
	//----------------------------------------------------------------

	public void loadData(String fileName){
		try {
			data1 = DataSource.read(fileName);
			original = new Instances(data1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//----------------------------------------------------------------
	// Set Class Attribute (16.2)
	//----------------------------------------------------------------

	public void setClass(){
		if(data1.classIndex() == -1)
			data1.setClassIndex(0);
	}
	
			
	//----------------------------------------------------------------
	// Filter Out Hidden Attributes (16.5: Filtering Attributes)
	//----------------------------------------------------------------

	public void filter(){
	
		String[] option = new String[2];

		for(int i = hiddenAttribute.length - 1; i >= 0; i-- ){
			option[0] = "-R"; 				// "range"
			option[1] = hiddenAttribute[i]; // remove H1
			Remove remove = new Remove(); 	// new instance of filter
			try {
				remove.setOptions(option);
				remove.setInputFormat(data1); 	// inform filter about dataset
				// **AFTER** setting options
				data1 = Filter.useFilter(data1, remove); // apply filter

			} catch (Exception e) {
				e.printStackTrace();
			} 		// set options
		}		
	}
	
	
	
	//----------------------------------------------------------------
	// Build a Classifier (16.6)
	//----------------------------------------------------------------
	
	public void buildClassifier(){
		String[] options = new String[1];
		options[0] = "-U"; 				// unpruned tree
		J48 tree = new J48(); 			// new instance of tree
		try {
			tree.setOptions(options);		// set the options

			tree.buildClassifier(data1); 	// build classifier
		} catch (Exception e) {
			e.printStackTrace();
		} 			
	}
		
		
//		//----------------------------------------------------------------
//		// Evaluation (16.6) 
//		//----------------------------------------------------------------
//		
//		Evaluation eval = new Evaluation(data1);
//		eval.crossValidateModel(tree, data1, 10, new Random(1));
////		System.out.println(eval.toSummaryString("\nResults\n\n", false));
//		
//
//		//----------------------------------------------------------------
//		// Confusion Matrix (16.6)
//		//----------------------------------------------------------------
//		
//		double[][] cmMatrix = eval.confusionMatrix();
//		
//		for(int row_i=0; row_i<cmMatrix.length; row_i++){
//            for(int col_i=0; col_i<cmMatrix.length; col_i++){
//                System.out.print(cmMatrix[row_i][col_i]);
//                System.out.print("|");
//            }
//            
//            System.out.println();
//        }
//
//		System.out.println();
//
//		//----------------------------------------------------------------
//		// Check for State Aliasing
//		//----------------------------------------------------------------
//		
//		double acceptedRate = 0.8;
//		boolean alias = checkAliasing(cmMatrix,acceptedRate);
//		
////		System.out.println(checkAliasing(cmMatrix,0.1));
//
//		
//		//----------------------------------------------------------------
//		// Find Feature(s) to Fix State Aliasing 
//		//----------------------------------------------------------------
//		
//		Instances solving = null;
//		Instances solved = null;
//		int hiddenAttributeUsed = 0;
//		double errorRate = 1.0;
//		J48 result = new J48();
//		result.setOptions(options); 		// set the options
//		
//		if(alias){
//			for(String s :hiddenAttribute){
//
////				System.out.println(Integer.parseInt(s));
//				solving = new Instances (data1);
//				
//				solving.insertAttributeAt(new Attribute("H1"), solving.numAttributes());
//
//				for(int i = 0; i < data1.numInstances(); i++){
//					solving.instance(i).setValue(solving.numAttributes()-1, original.instance(i).value(Integer.parseInt(s) - 1));
//				}
//
//				tree.buildClassifier(solving); 	// build classifier
//
//				eval = new Evaluation(solving);
//				eval.crossValidateModel(tree, solving, 10, new Random(1));
//				//			System.out.println(eval.toSummaryString("\nResults\n\n", false));
//
//				cmMatrix = eval.confusionMatrix();
//
//				for(int row_i=0; row_i<cmMatrix.length; row_i++){
//					for(int col_i=0; col_i<cmMatrix.length; col_i++){
//						System.out.print(cmMatrix[row_i][col_i]);
//						System.out.print("|");
//					}
//
//					System.out.println();
//
//				}
//
//				System.out.println();
//				
//				alias = checkAliasing(cmMatrix,acceptedRate);
////				System.out.println(alias);
//				if(!alias){
//					if (checkErrorRate(cmMatrix)<errorRate){
//						hiddenAttributeUsed = Integer.parseInt(s);
//						errorRate = checkErrorRate(cmMatrix);
//						solved = new Instances(solving);
//						
//						result.buildClassifier(solving);
////						System.out.println(errorRate);
//					}
//				}
//			}
//			System.out.println(hiddenAttributeUsed);
//		}
//		//----------------------------------------------------------------
//		// Output a New File (16.9: Saving Data)
//		//----------------------------------------------------------------
//		
//		
//		// output a new data file
//		
//		DataSink.write("C:/Users/Bawornsak.S/Desktop/Research/Data/iris-new.csv", solved);
//		
//		// output a new config file
//
////		BufferedWriter writer;
////
////		try{
////			File file = new File("C:/Users/Bawornsak.S/Desktop/Research/Data/config-new.txt");
////			file.createNewFile();
////			
////			writer = new BufferedWriter(new FileWriter(file));
////			
////			String newTA = "";
////			String newHA = "";
////			for(String s : trainingAttribute){
////				newTA += s + ",";
////			}
////			newTA += Integer.toString(trainingAttribute.length+2);
////			writer.write(newTA);
////			writer.newLine();
////			
////			for(int i = 1; i < hiddenAttribute.length; i++){
////				newHA += hiddenAttribute[i];
////				if(i != hiddenAttribute.length -1)
////					newHA += ",";
////			}
////			
////			writer.write(newHA);
////			writer.flush();
////			writer.close();
////		}
////		catch(FileNotFoundException e)
////		{
////			System.out.println("File Not Found");
////			System.exit( 1 );
////		}
////		catch(IOException e)
////		{
////			System.out.println("something messed up");
////			System.exit( 1 );
////		}
//		
//		
//		//----------------------------------------------------------------
//		// Print Out Tree (16.10)
//		//----------------------------------------------------------------
//		
//		TreeVisualizer tv = new TreeVisualizer(
//				null, result.graph(), new PlaceNode2());
//		JFrame jf = new JFrame("Weka Classifier Tree Visualizer: J48");
//		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		jf.setSize(800, 600);
//		jf.getContentPane().setLayout(new BorderLayout());
//		jf.getContentPane().add(tv, BorderLayout.CENTER);
//		jf.setVisible(true);
//		// adjust tree
//		tv.fitToScreen();
//	}
//	
//	
//	
//	
//	private static boolean checkAliasing(double[][] cmMatrix, double acceptedRate){
//		
//		double sum = 0.0;
//		
//		for(int i = 0; i < cmMatrix.length; i++){
//			for(int j = 0; j < cmMatrix.length; j++){
//				sum += cmMatrix[i][j];
//			}
//			if (cmMatrix[i][i]/sum < acceptedRate) 
//				return true;
//			sum = 0.0;
//		}
//		
//		return false;
//	}
//	
//	private static double checkErrorRate(double[][] cmMatrix){
//
//		double sum = 0.0;
//		double rateMax = 0;
//
//		for(int i = 0; i < cmMatrix.length; i++){
//			for(int j = 0; j < cmMatrix.length; j++){
//				sum += cmMatrix[i][j];
//			}
//			if (1-(cmMatrix[i][i]/sum) > rateMax) 
//				rateMax = 1-(cmMatrix[i][i]/sum);
//			sum = 0.0;
//		}
//		
//		return rateMax;
//	}
}
