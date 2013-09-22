package entity;
import input.MouseInputStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.ArithmaticHelper;
import main.Color;
import main.ConfigurationBean;
import force.Force2D;


public class MouseEntityManager implements EntityManager {
	
	private static final long NANO_TO_MILI = 1000000;
	final double FRICTION = 0.425;
	final boolean SET_INITIAL_SPEED = true; 
	final boolean SET_INITIAL_MASS = true; 
	
	Random random = new Random();
	List<Entity2D> entities = new ArrayList<Entity2D>();
	private ConfigurationBean config;
	private MouseInputStatus mouseInputStatus;
	
	public MouseEntityManager(ConfigurationBean config, MouseInputStatus mouseInputStatus) {
		this.config = config;
		this.mouseInputStatus = mouseInputStatus;
	}
	
	@Override
	public void initialize() {
	}

	@Override
	public void update(Force2D force) {
		
		long time = System.nanoTime() / NANO_TO_MILI;
		
		// updating old entities
		for(Entity2D e: entities){
			e.update(force, time);
			
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
		
		if (!mouseInputStatus.getLeftButtonStatus()) {

			// delete slow entities
			List<Entity2D> remainingEntities = new ArrayList<Entity2D>();
			for (Entity2D e: entities) {
				if (ArithmaticHelper.getCoordinate2DSize(e.getSpeed()) > 10.0) {
					remainingEntities.add(e);
				}
			}
			entities = remainingEntities;
			
			// adding new entities
			for (int counter = 0; counter < 1 + random.nextInt(5); counter++) {
				Entity2D e = new Entity2D(time, mouseInputStatus.getCoordinate().getX(), mouseInputStatus.getCoordinate().getY());
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
		System.out.println(entities.size());
	}

	@Override
	public void draw() {
		
		for(Entity2D e: entities){
			e.draw();
		}
		
	}


}
