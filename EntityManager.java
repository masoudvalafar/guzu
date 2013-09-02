import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityManager {

	final boolean SET_INITIAL_SPEED = true; 
	final boolean SET_INITIAL_MASS = true; 
	final boolean REFLEXIVE_BOUNDRIES = true;
	final double FRICTION = 0.025;
	
	private int numberEntities = 1;
	Random random = new Random();
	List<Entity2D> entities = new ArrayList<Entity2D>();
	private int frameWidth;
	private int frameHeight;
	
	public void initialize(int width, int height, long initializationTime) {
		
		this.frameWidth = width;
		this.frameHeight = height;
		
		for (int i = 0; i < numberEntities; i++){
			Entity2D e = new Entity2D(initializationTime, random.nextDouble() * frameWidth, random.nextDouble() * frameHeight);
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

	public void update(Force2D force, long time) {
		
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
				
				if (entityCoordinate.getX() > frameWidth) {
					entityCoordinate.setX(2 * frameWidth - entityCoordinate.getX());
					entitySpeed.setX(entitySpeed.getX() * -1);
				}
				
				if (entityCoordinate.getY() < 0) {
					entityCoordinate.setY(entityCoordinate.getY() * -1);
					entitySpeed.setY(entitySpeed.getY() * -1);
				}
				
				if (entityCoordinate.getY() > frameHeight) {
					entityCoordinate.setY(2 * frameHeight - entityCoordinate.getY());
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

	public void setNumberEntities(int numEntities) {
		
		this.numberEntities = numEntities;
	}

}
