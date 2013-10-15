import java.util.ArrayList;
import java.util.List;

import org.opencv.core.*;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.highgui.*;
import org.opencv.imgproc.*;
 
public class Main {
	
	

    public static void main(String[] args) {
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	String fileLoc = "C:/Users/Bawornsak.S/Desktop/Research/Images/";
    	
    	Mat templ = Highgui.imread(fileLoc + "templ1.jpg");
    	Mat img = Highgui.imread(fileLoc + "img1.jpg");
    	
    	// Template Matching
    	
        int result_cols = img.cols() - templ.cols() + 1;
        int result_rows = img.rows() - templ.rows() + 1;
        Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

        // Matching and Normalizing
        Imgproc.matchTemplate(img, templ, result, Imgproc.TM_CCOEFF);
        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        // Find the best match
        MinMaxLocResult mmr = Core.minMaxLoc(result);

        Point matchLoc;
        if (Imgproc.TM_CCOEFF == Imgproc.TM_SQDIFF || Imgproc.TM_CCOEFF == Imgproc.TM_SQDIFF_NORMED) {
            matchLoc = mmr.minLoc;
        } else {
            matchLoc = mmr.maxLoc;
        }
        
        // Draw green box around the target
        Core.rectangle(img, matchLoc, new Point(matchLoc.x + templ.cols(),
                matchLoc.y + templ.rows()), new Scalar(0, 255, 0));


        // Output an image
        Highgui.imwrite(fileLoc + "template-output.jpg", img);

    	
    	
    	
    	

//        Mat imageHSV = new Mat(image.size(), Core.DEPTH_MASK_8U);
//        Mat imageBlurr = new Mat(image.size(), Core.DEPTH_MASK_8U);
//        Mat imageA = new Mat(image.size(), Core.DEPTH_MASK_ALL);
//        Imgproc.cvtColor(image, imageHSV, Imgproc.COLOR_BGR2GRAY);
//        Imgproc.GaussianBlur(imageHSV, imageBlurr, new Size(5,5), 0);
//        Imgproc.adaptiveThreshold(imageBlurr, imageA, 255,Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY,7, 5);
//
//   
//        
//
//        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();    
//        Imgproc.findContours(imageA, contours, new Mat(), Imgproc.RETR_LIST,Imgproc.CHAIN_APPROX_SIMPLE);
//        Imgproc.drawContours(imageBlurr, contours, 1, new Scalar(0,0,255));
//        for(int i=0; i< contours.size();i++){
//        	System.out.println(Imgproc.contourArea(contours.get(i)));
//        	if (Imgproc.contourArea(contours.get(i)) > 50 ){
//        		Rect rect = Imgproc.boundingRect(contours.get(i));
//        		System.out.println(rect.height);
//        		if (rect.height > 28){
//        			Core.rectangle(image, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height),new Scalar(0,0,255));
//        		}
//        	}
//        }
//     	
//    	
//        Highgui.imwrite(fileLoc + "output2.jpg", imageBlurr);
//
//        Highgui.imwrite(fileLoc + "output.jpg", image);
//        
//    	
    
//    	loop:
    	
// 		Extract Feature
// 		train tree (use LA)
//    	if (aliasing){
//    		present images from aliased states
//    		user highlights differences between aliased states
//    		difference ==> feature extractor
//    	}else
//    		break
    	
    }
}

