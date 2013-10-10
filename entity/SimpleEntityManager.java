package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.ArithmaticHelper;
import main.Color;
import main.ConfigurationBean;
import force.Force2D;

public class SimpleEntityManager implements EntityManager{

	final double FRICTION = 0.425;
	final boolean SET_INITIAL_SPEED = true; 
	final boolean SET_INITIAL_MASS = true; 
	Random random = new Random();
	
	List<Entity2D> entities = new ArrayList<Entity2D>();
	private ConfigurationBean config;
	
	public SimpleEntityManager(ConfigurationBean config) {
		this.config = config;
	}

	@Override
	public void updateEntities(Force2D forceField, long time) {
		// updating old entities
		for(Entity2D e: entities){
			e.update(forceField, time);
		
			// enforce boundary criteria on coordinate and speed
			if (config.getReflexiveBoundries()){
				Coordinate2D entityCoordinate = e.getCoordinate();
				Coordinate2D entitySpeed = e.getSpeed();
				
				if (entityCoordinate.getX() < 0) {
					entityCoordinate.setX(entityCoordinate.getX() * -1);
					entitySpeed.setX(entitySpeed.getX() * -1);
				}
				
				if (entityCoordinate.getX() > config.getWidth()) {
					entityCoordinate.setX(2 * config.getWidth() - entityCoordinate.getX());
					entitySpeed.setX(entitySpeed.getX() * -1);
				}
				
				if (entityCoordinate.getY() < 0) {
					entityCoordinate.setY(entityCoordinate.getY() * -1);
					entitySpeed.setY(entitySpeed.getY() * -1);
				}
				
				if (entityCoordinate.getY() > config.getHeight()) {
					entityCoordinate.setY(2 * config.getHeight() - entityCoordinate.getY());
					entitySpeed.setY(entitySpeed.getY() * -1);
				}
			}
		}
		
	}

	@Override
	public void createNewEntities(int numEntities, Coordinate2D coordinate, long time) {
		// adding new entities
		for (int counter = 0; counter < numEntities; counter++) {
			Entity2D e = new Entity2D(time, coordinate.getX(), coordinate.getY());
			e.setColor(new Color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
			e.setFriction(FRICTION);
			
			if(SET_INITIAL_SPEED){
				e.setSpeed(new Coordinate2D(random.nextDouble() * 500 - 250, random.nextDouble() * 500 - 250));
			}
			
			if (SET_INITIAL_MASS){
				e.setMass(1 + random.nextInt(49));
			}
			
			entities.add(e);
		}
			
	}

	@Override
	public void killSlowEntities() {
		List<Entity2D> remainingEntities = new ArrayList<Entity2D>();
		for (Entity2D e: entities) {
			if (ArithmaticHelper.getCoordinate2DSize(e.getSpeed()) > 10.0) {
				remainingEntities.add(e);
			}
		}
		entities = remainingEntities;
		
	}

	@Override
	public void draw() {
		for(Entity2D e: entities){
			e.draw();
		}
	}


}
