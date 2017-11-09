package LWJGL;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
public class SimpleOGLRenderer {
	
	public SimpleOGLRenderer(){
		try{
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setTitle("Hello LWJGL");
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
		}
		//initialization code
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	while(!Display.isCloseRequested()){
		//Render
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		glBegin(GL_QUADS);
			glVertex2i(400,400);// upper left
			glVertex2i(450,400); //upper right
			glVertex2i(450,450);//lower right
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
	public static void main(String args[]){
		new SimpleOGLRenderer();
	}
	
}
