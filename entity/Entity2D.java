package entity;
import main.Color;
import main.GraphicsHelper;

import org.lwjgl.opengl.GL11;

import force.Force2D;

public class Entity2D implements Plotable{
	
	private static final double MILI_TO_SECOND = 1000.0;
	Coordinate2D currentCoordinate = new Coordinate2D();
	Coordinate2D previousCoordinate = new Coordinate2D();
	Coordinate2D speed = new Coordinate2D();
	double mass = 2.5;
	long lastUpdateTime = 0;
	private Color color;
	private double friction = 0.0;

	public Entity2D(long initializationTime, double x, double y) {
		lastUpdateTime = initializationTime;
		
		currentCoordinate.setX(x);
		currentCoordinate.setY(y);
		previousCoordinate.setX(x);
		previousCoordinate.setY(y);
		
	}

	public Coordinate2D getCoordinate() {
		return currentCoordinate;
	}

	public void setCoordinate(Coordinate2D coordinate) {
		this.currentCoordinate = coordinate;
	}
	
	public Coordinate2D getSpeed(){
		return speed;
	}
	
	public void setX(double x){
		currentCoordinate.setX(x);
	}
	
	public void setY(double y){
		currentCoordinate.setY(y);
	}
	
	public double getMass(){
		return mass;
	}
	
	public void setMass(double mass){
		this.mass = mass;
	}

	public void update(Force2D force, long currentTime) {

		previousCoordinate.setX(currentCoordinate.getX());
		previousCoordinate.setY(currentCoordinate.getY());
		
		double deltaTime = (currentTime - lastUpdateTime) / MILI_TO_SECOND;
		double accelerationX = force.getForceX(currentCoordinate) / mass;
		double accelerationY = force.getForceY(currentCoordinate) / mass;
		
		currentCoordinate.setX(accelerationX * deltaTime * deltaTime +
				               speed.getX() * deltaTime+
				               currentCoordinate.getX());
		currentCoordinate.setY(accelerationY * deltaTime * deltaTime +
	               			   speed.getY() * deltaTime+
	               			   currentCoordinate.getY());
		
		speed.setX(accelerationX * deltaTime + speed.getX());
		speed.setX((1 - friction * deltaTime) * speed.getX()); 
		speed.setY(accelerationY * deltaTime + speed.getY());
		speed.setY((1 - friction * deltaTime) * speed.getY());
		
		lastUpdateTime = currentTime;
	}

	@Override
	public void draw() {
		GL11.glColor3d(color.getRed(), color.getGreen(), color.getBlue());
		GraphicsHelper.plotLine(previousCoordinate, currentCoordinate);
	}

	public void setColor(Color color) {
		
		this.color = color;
	}
	
	public Color getColor(){
		
		return color;
	}

	public void setSpeed(Coordinate2D speed) {
		
		this.speed = speed;
	}

	public void setFriction(double f) {
		this.friction  = f;
	}
	
}
