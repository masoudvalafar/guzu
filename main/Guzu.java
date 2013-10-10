package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;


public class Guzu {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final int NUM_ENTITIES = 100;
	private static final long SLEEP_TIME = 20;
	
	public static void main(String[] argv) throws InterruptedException {
		Guzu guzu = new Guzu();
		guzu.start();
	}

	private void start() throws InterruptedException {
		
		// config
		ConfigurationBean config = new ConfigurationBean(WIDTH, HEIGHT, NUM_ENTITIES);
		config.setReflexiveBounries(true);
		
		// create ObjectManager
		GameManager gameManager = new GameManager(config);
		
		// create display
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		// init OpenGL
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, WIDTH, 0, HEIGHT, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
				
		// loop: update, plot
		while (!Display.isCloseRequested()) {
			Thread.sleep(SLEEP_TIME);

			gameManager.update();
			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
			gameManager.draw();
			
			Display.update();
		}
		
		// destroying display
		Display.destroy();
	}
	
}
