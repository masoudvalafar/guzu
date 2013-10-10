package entity;

import force.Force2D;

public interface EntityManager {

	void updateEntities(Force2D forceField, long time);
	void createNewEntities(int numEntities, Coordinate2D coordinate, long time);
	void killSlowEntities();
	void draw();
}
