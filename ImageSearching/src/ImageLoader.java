import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
public class ImageLoader {
	 // File representing the folder that you select using a FileChooser
    static File dir = new File("");
	public static ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
    public static int numImage = 0;
    public static double similarLimit = .5;
    // array of supported extensions (use a List if you prefer)
    static final String[] EXTENSIONS = new String[]{
        "jpg", "png" // and other formats you need
    };
    // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };
    private static int[][] convertToPixel(BufferedImage image) {
    	int width = image.getWidth();
        int height = image.getHeight();
        int[][] result = new int[height][width];

        for (int row = 0; row < height; row++) {
           for (int col = 0; col < width; col++) {
              result[row][col] = image.getRGB(row, col);
              
           }
        }

        return result;
     }
    public static void main(String[] args) {
    	 JFileChooser chooser = new JFileChooser();
    	    chooser.setCurrentDirectory(new java.io.File("."));
    	    chooser.setDialogTitle("choosertitle");
    	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	    chooser.setAcceptAllFileFilterUsed(false);

    	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
    	      System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
    	      System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
    	      dir = chooser.getSelectedFile();
    	    } else {
    	      System.out.println("No Selection ");
    	    }
        if (dir.isDirectory()) { // make sure it's a directory
            for (final File f : dir.listFiles(IMAGE_FILTER)) {
                BufferedImage img = null;
                try {
                    img = ImageIO.read(f);
                    
                    images.add(img);
                    // you probably want something more involved here
                    // to display in your UI
                    
                   
                } catch (final IOException e) {
                    // handle errors here
                }
            }
            System.out.println("Numimages " + numImage);
        }
        for(int i = 0; i<images.size();i++){
        	for(int g = i+1; g<images.size();g++){
        	 int[][] pixelArray = convertToPixel(images.get(i));
        	 int[][] pixelArray2 = convertToPixel(images.get(g));
        	 double similarity = 0;
        	 if(pixelArray.length == pixelArray2.length && pixelArray[0].length == pixelArray2.length){
	        	 for(int x = 0;x<pixelArray.length;x++){
	        		 for(int y = 0;y<pixelArray[0].length;y++){
	        			 if(pixelArray[x][y] == pixelArray2[x][y]){
	        				 similarity++;
	        			 }
	        				 
	        		 }
	        	 }
        	 }
        	double totalPixels = images.get(i).getHeight() * images.get(i).getWidth();
        	similarity = similarity/totalPixels;
        	if(similarity >= similarLimit){
        		NumberFormat defaultFormat = NumberFormat.getPercentInstance();
        		defaultFormat.setMinimumFractionDigits(1);
        		System.out.println("Image " + (i+1) + " is " + defaultFormat.format(similarity) + " similar to image " + (g+1));
        	}
        	}
        }
    }
}
