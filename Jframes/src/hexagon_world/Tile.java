package hexagon_world;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
	Image tileImage;

	public Tile(int type) {
		try {

			switch (type) {
			case 0:
				tileImage = ImageIO.read(new File("res/hexDirt.png"));
				break;
			case 1:
				tileImage = ImageIO.read(new File("res/hexGrass.png"));
				break;
			case 2:
				tileImage = ImageIO.read(new File("res/hexGrassTree.png"));
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Image getImage(){
		return tileImage;
	}
}
