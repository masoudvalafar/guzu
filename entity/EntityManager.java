package entity;

import force.Force2D;

public interface EntityManager {
	
	public void initialize();
	public void update(Force2D force);
	public void draw();
}
