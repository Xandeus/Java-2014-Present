package imageData;
import java.io.*;
import java.util.HashSet;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
public class ImageParser {
	 HashSet<Color> colors;
	 public ImageParser(String fileName){
		File file= new File(fileName);
		HashSet<Color> colors = new HashSet();
		BufferedImage image;
		try {
			image = ImageIO.read(file);
			// Getting pixel color by position x and y 
			  int imgWidth = image.getWidth();
			  int imgHeight = image.getHeight();
			  for(int x = 0;x<imgWidth;x++){
				  colors.add(new Color(image.getRGB(x,0)));
			  }
			  System.out.println(colors.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 public Color[] getColors(){
		 System.out.println(colors.toString());

		 return (Color[]) colors.toArray();
	 }
}