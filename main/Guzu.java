package main;

import entity.EntityManager;
import entity.MouseEntityManager;
import entity.SimpleEntityManager;
import force.Force2D;
import force.MouseField;
import input.MouseInputStatus;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Guzu {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final int NUM_ENTITIES = 100;
	private static final long SLEEP_TIME = 20;
	private static final int NUM_FORCE_SPOTS = 2;
	
	public static void main(String[] argv) throws InterruptedException {
		Guzu guzu = new Guzu();
		guzu.start();
	}

	private void start() throws InterruptedException {
		
		// mouse input object
		MouseInputStatus mouseInputStatus = new MouseInputStatus();
		
		// config
		ConfigurationBean config = new ConfigurationBean(WIDTH, HEIGHT, NUM_ENTITIES);
		config.setReflexiveBounries(true);
		
		// create force field
		//Force2D forceField = new CentralizedField(WIDTH, HEIGHT);
		//Force2D forceField = new ForceSpottedField(WIDTH, HEIGHT, NUM_FORCE_SPOTS);
		Force2D forceField = new MouseField(mouseInputStatus); 
		
		// create ObjectManager
		//EntityManager objectManager = new SimpleEntityManager(config);
		EntityManager objectManager = new MouseEntityManager(config, mouseInputStatus);
		
		objectManager.initialize();
		
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

			// mouse action
			updateMouseInput(mouseInputStatus);
			
			// updating and plotting entities
			objectManager.update(forceField);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
			objectManager.draw();
			
			// updating forcefield
			forceField.draw();
			
			Display.update();
		}
		
		// destroying display
		Display.destroy();
	}

	private void updateMouseInput(MouseInputStatus mouseInputStatus) {
		
		mouseInputStatus.setMouseCoordinate(Mouse.getX(), Mouse.getY());
		mouseInputStatus.setLeftButtonStatus(Mouse.isButtonDown(0));
		mouseInputStatus.setRighttButtonStatus(Mouse.isButtonDown(1));
	}
	
}
