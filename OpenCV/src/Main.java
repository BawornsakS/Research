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
    	
    	//-------------------------------------------------------------------------------
    	// Template Matching
    	//-------------------------------------------------------------------------------
    	
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
        
        // Draw blue box around the target
        Core.rectangle(img, matchLoc, new Point(matchLoc.x + templ.cols(),
                matchLoc.y + templ.rows()), new Scalar(255, 0, 0));


        // Output an image
        Highgui.imwrite(fileLoc + "template-output.jpg", img);

        
        

    	//-------------------------------------------------------------------------------
        // Draw Contour
    	//-------------------------------------------------------------------------------
    	
        Mat mat = Highgui.imread(fileLoc + "img1.jpg");
        Mat grey = new Mat();

        Imgproc.cvtColor(mat, grey, Imgproc.COLOR_BGR2GRAY);
        Highgui.imwrite(fileLoc + "template-output2.jpg", grey);

        Imgproc.blur(grey, grey, new Size(3,3));
       
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierarchy = new Mat();
        Mat canny_out = new Mat();
        int thresh = 30;											// how to set it
        
        

        Imgproc.Canny(grey, canny_out, thresh, thresh*2);
        
        Highgui.imwrite(fileLoc + "template-output2.jpg", canny_out);
       
        Imgproc.findContours(canny_out, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);        
   
        Highgui.imwrite(fileLoc + "template-output3.jpg", canny_out);
        
        Imgproc.drawContours(canny_out, contours, -1, new Scalar(255,0,0), 1);
    	
        Highgui.imwrite(fileLoc + "template-output4.jpg", canny_out);



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

