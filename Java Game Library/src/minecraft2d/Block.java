package minecraft2d;

import static minecraft2d.World.BLOCK_SIZE;
import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
public class Block {
	private BlockType type = BlockType.AIR;
	private Texture texture = null;
	private float x;
	private float y;
	
	public Block(BlockType type,float x, float y){
		this.type = type;
		this.x=x;
		this.y=y;
		try {
			this.texture = TextureLoader.getTexture("PNG",new FileInputStream(new File(type.location)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void draw(){
		texture.bind();
		glLoadIdentity();
		glTranslatef(x, y,0);
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2f(0,0);
			glTexCoord2f(1,0);
			glVertex2f(BLOCK_SIZE,0);
			glTexCoord2f(1,1);
			glVertex2f(BLOCK_SIZE,BLOCK_SIZE);
			glTexCoord2f(0,1);
			glVertex2f(0,BLOCK_SIZE);
		glEnd();
		glLoadIdentity();
	}
	public BlockType getType(){
		return type;
	}
	public void setType(BlockType type){
		this.type = type;
	}
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public void stY(float y){
		this.y =y;
	}
	public void bind() {
		texture.bind();
		
	}
	
}
