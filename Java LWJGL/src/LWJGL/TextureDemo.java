package LWJGL;
import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
public class TextureDemo {
	private static Texture wood;
	
	public static void main(String args[]){
		try{
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setTitle("Texture Demo");
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
		}
			
		try {
			wood = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/wood.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
			
		//initialization code
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		while(!Display.isCloseRequested()){
			//Render
			
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			wood.bind();
			
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2i(400,400);// upper left
				glTexCoord2f(1,0);
				glVertex2i(450,400); //upper right
				glTexCoord2f(1,1);
				glVertex2i(450,450);//lower right
				glTexCoord2f(0,1);
				glVertex2i(400,450);//lower left
			glEnd();
			glBegin(GL_LINES);
				glVertex2i(100,100);
				glVertex2i(200, 200);
			glEnd();
				
			Display.update();
			Display.sync(60);
		}
			Display.destroy();
		}
		private Texture loadTexture(String key){
			try {
				return TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + key + ".png")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

	}

