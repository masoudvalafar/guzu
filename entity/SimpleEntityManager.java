package entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.Color;
import main.ConfigurationBean;
import force.Force2D;

public class SimpleEntityManager implements EntityManager{

	private static final long NANO_TO_MILI = 1000000;
	
	final boolean SET_INITIAL_SPEED = true; 
	final boolean SET_INITIAL_MASS = true; 
	final boolean REFLEXIVE_BOUNDRIES = true;
	final double FRICTION = 0.025;
	
	Random random = new Random();
	List<Entity2D> entities = new ArrayList<Entity2D>();

	private ConfigurationBean config;
	
	public SimpleEntityManager(ConfigurationBean config) {
		this.config = config;
	}

	public void initialize() {
		
		long initializationTime = System.nanoTime() / NANO_TO_MILI;
		
		for (int i = 0; i < config.getNumEntities(); i++){
			Entity2D e = new Entity2D(initializationTime, random.nextDouble() * config.getWidth(), random.nextDouble() * config.getHeight());
			e.setColor(new Color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
			e.setFriction(FRICTION);
			
			if(SET_INITIAL_SPEED){
				e.setSpeed(new Coordinate2D(random.nextDouble() * 100 - 50, random.nextDouble() * 100 - 50));
			}
			
			if (SET_INITIAL_MASS){
				e.setMass(1 + random.nextInt(49));
			}
			
			entities.add(e);
		}
	}

	public void update(Force2D force) {
		
		long time = System.nanoTime() / NANO_TO_MILI;
		
		for(Entity2D e: entities){
			e.update(force, time);
			
			// enforce boundary criteria on coordinate and speed
			if (REFLEXIVE_BOUNDRIES){
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

	public void draw() {
		
		for(Entity2D e: entities){
			e.draw();
		}
		
	}

}
