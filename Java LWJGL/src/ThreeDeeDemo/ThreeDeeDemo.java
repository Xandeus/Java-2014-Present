package ThreeDeeDemo;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.input.Mouse;
import org.w3c.dom.css.Rect;

import DifferentRendering.VertexBufferObjects;

import java.awt.Point;
import java.nio.FloatBuffer;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

	
public class ThreeDeeDemo{
	
	private static Vector3f position = new Vector3f(0,0,0);
	private static Vector3f rotation = new Vector3f(0,0,0);
	private static int mouseSpeed = 2;
	private static final int maxLookUp = 85;
	private static final int maxLookDown = -85;
	private static int nextPoint = 0;
	private static long lastFrame;

    private static float getDelta() {
        long time = getTime();
        float delta = (float) (time - lastFrame);
        lastFrame = time;
        return delta;
    }

    private static long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    // The speed in which the "camera" travels
    private static int movementSpeed = 50000;
    private static final float zFar = 80000f;
    private static final float zNear = .0001f;
	
	public static void main(String args[]){
		try{
			//Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setDisplayMode(new DisplayMode(1000, 600));

			Display.setTitle("3D Test");
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		//initialization code OpenGl
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		//Create a new perspective with 30 degree angle (field of view), 649 / 480 aspect ration, 0.001f znear, 500 zfar
		// Note: +x is to the right
		//		 +y is to the top
		//		 +z is to the camera
		gluPerspective((float) 30, (float)Display.getWidth() / (float)Display.getHeight(), zNear, zFar);
		glMatrixMode(GL_MODELVIEW);
		
		//To make sure the points closest to the camera are shown before the ones further away
		glEnable(GL_DEPTH_TEST);
		
		//Initialization code random points
		Point[] points = new Point[10000];
		Random random = new Random();
		
		
		
		//iterate of every array index
		for (int i = 0; i < points.length; i++) {
			// I altered the zFar variable to adapt to the points.length.
			// Points, no matter how much, now appear evenly distributed along the screen
			points[i] = new Point((random.nextFloat() - 0.5f) * 500f, (random.nextFloat() - 0.5f) * 100f,
					random.nextInt(points.length/500) - points.length/500);
		}
			
		//Vertex Arrays 
		FloatBuffer vertexArray = BufferUtils.createFloatBuffer(points.length * 3);
	
		//iterate over all the points and store them in the float buffer
		for(Point p : points){
			vertexArray.put(new float[]{p.x,p.y,p.z});
		}
		
		//Make the buffer readable by openGl
		vertexArray.flip();
		//Create the handle for the VBO
		int vertexBufferObject = glGenBuffers();
		
		//Bind the VBOfor usage (in case of storage info)
		glBindBuffer(GL_ARRAY_BUFFER,vertexBufferObject);
		
		//Store all contents of the float buffer in the VBO
		glBufferData(GL_ARRAY_BUFFER,vertexArray,GL_STATIC_DRAW);
		
		//unbind VBO
		glBindBuffer(GL_ARRAY_BUFFER,0);
			
	while(!Display.isCloseRequested()){
        
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		float delta = getDelta() / 16;
		//push the screen inwards at the amount of speed
		glTranslatef(position.x, position.y, position.z);
		
		//Enable vertex arrays
		glEnableClientState(GL_VERTEX_ARRAY);
		
		//tell OpenGL to use our VBO
		glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);
		
		//tell OpenGl to find the data in the bound VBO with 3 
		//componets (xyz) and with the data type float
		glVertexPointer(3, GL_FLOAT, 0,0L);
		
		//Tell OpenGL to draw the data supplied by the pointer method
		//as points.length amount of points
		glDrawArrays(GL_POINTS,0,points.length);
		
		//unbind the VBO
		glBindBuffer(GL_ARRAY_BUFFER,0);
		
		//disable Vertex Arrays(VBOs)
		glDisableClientState(GL_VERTEX_ARRAY);
		
		glLoadIdentity();
        glRotatef(rotation.x, 1, 0, 0);
        glRotatef(rotation.y, 0, 1, 0);
        glRotatef(rotation.z, 0, 0, 1);
        glTranslatef(position.x, position.y, position.z);
//		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
//			speedZ += 0.01f;
//		}
//		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
//			speedZ -= 0.01f;
//		}
//		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
//			speedX += 0.01f;
//		}
//		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
//			speedX -= 0.01f;
//		}
		//Iterate over keyboard input events
        if(Mouse.isGrabbed()){
			float mouseDX = Mouse.getDX() * mouseSpeed * 0.16f;
			float mouseDY = Mouse.getDY() * mouseSpeed * 0.16f;
			if(rotation.y + mouseDX >= 360){
				rotation.y = rotation.y + mouseDX - 360;
			}
			else if(rotation.y + mouseDX < 0){
				rotation.y = 360 - rotation.y + mouseDX;
			}
			else{
				rotation.y += mouseDX;	
			}
			if(rotation.x - mouseDY >= maxLookDown && rotation.x - mouseDY <= maxLookUp){
				rotation.x += -mouseDY;
			}
			else if(rotation.x - mouseDY < maxLookDown){
				rotation.x = maxLookDown;
			}
			else if(rotation.x - mouseDY > maxLookUp){
				rotation.x = maxLookUp;
			}
		}
		while (Mouse.next()) {
             if (Mouse.isButtonDown(0)) {
                 Mouse.setGrabbed(true);
             }
             if (Mouse.isButtonDown(1)) {
                 Mouse.setGrabbed(false);
             }
         }
		if (Keyboard.isKeyDown(Keyboard.KEY_W)){ //&& Keyboard.isKeyDown(Keyboard.KEY_D) && !Keyboard.isKeyDown(Keyboard.KEY_A) && !Keyboard.isKeyDown(Keyboard.KEY_S)) {
			float angle = rotation.y;
            Vector3f newPosition = new Vector3f(position);
            float hypotenuse = (movementSpeed * 0.00002f) * delta;
            float adjacent = hypotenuse * (float) Math.cos(Math.toRadians(angle));
            float opposite = (float) (Math.sin(Math.toRadians(angle)) * hypotenuse);
            newPosition.z += adjacent;
            newPosition.x -= opposite;
            position.z = newPosition.z;
            position.x = newPosition.x;
        }
		if (Keyboard.isKeyDown(Keyboard.KEY_S)){ //&& Keyboard.isKeyDown(Keyboard.KEY_D) && !Keyboard.isKeyDown(Keyboard.KEY_A) && !Keyboard.isKeyDown(Keyboard.KEY_S)) {
			float angle = rotation.y;
            Vector3f newPosition = new Vector3f(position);
            float hypotenuse = -(movementSpeed * 0.00002f) * delta;
            float adjacent = hypotenuse * (float) Math.cos(Math.toRadians(angle));
            float opposite = (float) (Math.sin(Math.toRadians(angle)) * hypotenuse);
            newPosition.z += adjacent;
            newPosition.x -= opposite;
            position.z = newPosition.z;
            position.x = newPosition.x;
        }
		if (Keyboard.isKeyDown(Keyboard.KEY_A)){ //&& Keyboard.isKeyDown(Keyboard.KEY_D) && !Keyboard.isKeyDown(Keyboard.KEY_A) && !Keyboard.isKeyDown(Keyboard.KEY_S)) {
			float angle = rotation.y - 90;
            Vector3f newPosition = new Vector3f(position);
            float hypotenuse = (movementSpeed * 0.00002f) * delta;
            float adjacent = hypotenuse * (float) Math.cos(Math.toRadians(angle));
            float opposite = (float) (Math.sin(Math.toRadians(angle)) * hypotenuse);
            newPosition.z += adjacent;
            newPosition.x -= opposite;
            position.z = newPosition.z;
            position.x = newPosition.x;
        }
		if (Keyboard.isKeyDown(Keyboard.KEY_D)){ //&& Keyboard.isKeyDown(Keyboard.KEY_D) && !Keyboard.isKeyDown(Keyboard.KEY_A) && !Keyboard.isKeyDown(Keyboard.KEY_S)) {
			float angle = rotation.y + 90;
            Vector3f newPosition = new Vector3f(position);
            float hypotenuse = (movementSpeed * 0.00002f) * delta;
            float adjacent = hypotenuse * (float) Math.cos(Math.toRadians(angle));
            float opposite = (float) (Math.sin(Math.toRadians(angle)) * hypotenuse);
            newPosition.z += adjacent;
            newPosition.x -= opposite;
            position.z = newPosition.z;
            position.x = newPosition.x;
        }
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)){
			double newPositionY = ((movementSpeed * 0.00002)) * delta;
			position.y -= newPositionY;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Z)){
			double newPositionY = ((movementSpeed * 0.00002)) * delta;
			position.y += newPositionY;
		}
		
		while(Keyboard.next()){
			if(Keyboard.isKeyDown(Keyboard.KEY_TAB)){
				movementSpeed *= 2;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				movementSpeed /= 2;
			}
			//Mark Position
			if(Keyboard.isKeyDown(Keyboard.KEY_E)){
				System.out.println("X: " + position.x + "\n" + "Y: " + position.y + "\n"+ "Z: " + position.z);
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                if (!Mouse.isGrabbed() || Display.isFullscreen()) {
                    Display.destroy();
                    System.exit(0);
                } else {
                    Mouse.setGrabbed(false);
                }
            }
		}
		
		
		Display.update();
		Display.sync(60);
	}
		glDeleteBuffers(vertexBufferObject);
		Display.destroy();
		System.exit(0);	
	}
	
	private static class Point {

        final float x;
        final float y;
        final float z;

        public Point(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
	}
}
