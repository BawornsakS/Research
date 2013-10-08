import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.*;
import org.opencv.highgui.*;
 

public class Main {
    public static void main(String[] args) {
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//    	Mat mm  = Mat.eye(3, 3, CvType.CV_8UC1);
//    	System.out.println("m = " + mm.dump());

    	Mat m = Highgui.imread("C:/Users/Bawornsak.S/Desktop/Research/Images/phoenix.jpg",Highgui.CV_LOAD_IMAGE_COLOR);
    	new LoadImage("C:/Users/Bawornsak.S/Desktop/Research/Images/phoenix.jpg",m);
    	
    	if(m == null)
    		System.out.println("NULL");
    	
    	
    	
    }
}

