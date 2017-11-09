package LWJGL;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
public class TimerDemo {
	
	private static long lastFrame;
	private static long getTime(){
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	private static double getDelta(){
		long currentTime = getTime();
		double delta = (double) (currentTime - lastFrame);
		lastFrame = getTime();
		return delta;
	}
	
	public static void main(String args[]){
		try{
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setTitle("Time demo");
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
		}
		
		int x = 100;
		int y = 100;
		int dx = 1;
		int dy = 1;
		
		//initialization code
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		lastFrame = getTime();
	while(!Display.isCloseRequested()){
		//Render
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		double delta = getDelta();
		x+= delta * dx * 0.1;
		y+= delta * dy * 0.1;
		
		glRecti(x,y,x+30,y+30);
		
		Display.update();
		Display.sync(60);
	}
		Display.destroy();
	}
	
}
