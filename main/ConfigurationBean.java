package main;

public class ConfigurationBean {

	private int width;
	private int numEntities;
	private int height;
	private boolean reflexiveBoundries;
	private int gameStatus;
	
	public ConfigurationBean(int width, int height, int numEntities) {
		this.width = width;
		this.height = height;
		this.numEntities = numEntities;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getNumEntities() {
		return numEntities;
	}

	public void setNumEntities(int numEntities) {
		this.numEntities = numEntities;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean getReflexiveBoundries(){
		return reflexiveBoundries;
	}
	
	public void setReflexiveBounries(boolean reflexiveBoundries) {
		this.reflexiveBoundries = reflexiveBoundries;
	}

	public int getGameStatue() {
		return gameStatus;
	}
	
	public void setGameStatus(int status) {
		this.gameStatus = status;
	}

}
