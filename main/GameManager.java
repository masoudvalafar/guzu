package main;

import input.MouseInputStatus;

import java.util.Random;

import org.lwjgl.input.Mouse;

import entity.EntityManager;
import entity.SimpleEntityManager;
import force.Force2D;
import force.MouseField;

public class GameManager {

	private static final long NANO_TO_MILI = 1000000;
	Random random = new Random();
	
	ConfigurationBean config;
	Force2D forceField;
	EntityManager entityManager;
	
	// mouse input object
	MouseInputStatus mouseInputStatus = new MouseInputStatus();
	
	public GameManager(ConfigurationBean config) {
		this.config = config;
		config.setGameStatus(0);
		
		forceField = new MouseField(mouseInputStatus);
		entityManager = new SimpleEntityManager(config);
	}

	public void update() {
		// update entities
		updateEntities();
		
		// update inputs
		updateInputs();
		
		// set game status
		updateGameStatus();
		
	}
	
	public void draw() {
		forceField.draw();
		entityManager.draw();
	}

	private void updateGameStatus() {
		config.setGameStatus(0);
		
		if (mouseInputStatus.getLeftButtonStatus()) {
			config.setGameStatus(1);
		}
	}

	private void updateEntities() {
		long time = System.nanoTime() / NANO_TO_MILI;
		
		entityManager.updateEntities(forceField, time);
		
		switch(config.getGameStatue()){
			case 1:
				break;
			case 0:
				entityManager.killSlowEntities();
				entityManager.createNewEntities(1 + random.nextInt(5), mouseInputStatus.getCoordinate(), time);
				break;
		}

	}

	private void updateInputs() {
		// mouse action
		updateMouseInput(mouseInputStatus);
		
	}

	private void updateMouseInput(MouseInputStatus mouseInputStatus) {
		mouseInputStatus.setMouseCoordinate(Mouse.getX(), Mouse.getY());
		mouseInputStatus.setLeftButtonStatus(Mouse.isButtonDown(0));
		mouseInputStatus.setRighttButtonStatus(Mouse.isButtonDown(1));
	}

}
