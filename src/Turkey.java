import java.util.Random;
/**
 * Jerry Chen (yc4qy) Rachel Stadler (rvs5wj) Section: 103 Date: 11/21/2012
 */
/**
 * Turkey.java
 * 
 * The class representing the Turkey object in the game. This class needs to
 * have some representation of current location, a reference to the Farmer, and
 * a speed.
 * 
 * Creating a new Turkey - Turkeys should appear at the edge of the screen.
 * Thus, your constructor for a Turkey should take a Farmer, a speed, and the x
 * and y maximum values as its parameters. Randomly choose which side the turkey
 * will appear at and then randomly choose which side the turkey will exit at
 * (not the same side as the entrance)
 * 
 * Move method - The move method for the turkey will be more complicated than
 * that of the Farmer. The Farmer always walks directly toward the mouse
 * pointer. The turkey will walk in a straight line toward the randomly chosen
 * exit side. If the farmer gets close (within 50 pixels), then the turkey
 * should move directly away from the Farmer at double its speed. Once it is far
 * enough away, it resumes its original path.
 * 
 * 
 * @authors
 * @compids
 * @lab
 */
public class Turkey {
	private double entryX;
	private double entryY;
	private double turkeyX;
	private double turkeyY;
	private int turkeySpeed;
	private double xMax;
	private double yMax;
	private Farmer farmer;

	/**
	 * Move the turkey according to its speed and the elapsedTime since the last
	 * refresh of the screen.
	 * 
	 * @param elapsedTime
	 *            seconds since last update
	 */

	public Turkey(double entryX, double entryY, double turkeyX, double turkeyY,
			double xMax, double yMax, int turkeySpeed, Farmer farmer) {
		super();
		this.entryX = turkeyX;
		this.entryY = turkeyY;
		this.turkeyX = turkeyX;
		this.turkeyY = turkeyY;
		this.xMax = xMax;
		this.yMax = yMax;
		this.turkeySpeed = turkeySpeed;
		this.farmer = farmer;
	}

	public void move(float elapsedTime) {
		if (entryX == 0) {
			turkeyX = turkeyX + turkeySpeed * elapsedTime;
		} else if (entryX == 500) {
			turkeyX = turkeyX - turkeySpeed * elapsedTime;
		} else if (entryY == 0) {
			turkeyY = turkeyY + turkeySpeed * elapsedTime;
		} else if (entryY == 500) {
			turkeyY = turkeyY - turkeySpeed * elapsedTime;
		} else {
			if (turkeyX <= 250 && turkeyY <= 250) {
				turkeyX = turkeyX + turkeySpeed * elapsedTime;
			} else if (turkeyX > 250 && turkeyY <= 250) {
				turkeyY = turkeyY + turkeySpeed * elapsedTime;
			} else if (turkeyX <= 250 && turkeyY > 250) {
				turkeyX = turkeyX - turkeySpeed * elapsedTime;
			} else if (turkeyX > 250 && turkeyY > 250) {
				turkeyY = turkeyY - turkeySpeed * elapsedTime;
			}
		}
	}

	public double getTurkeyX() {
		return turkeyX;
	}

	public void setTurkeyX(double turkeyX) {
		this.turkeyX = turkeyX;
	}

	public double getTurkeyY() {
		return turkeyY;
	}

	public void setTurkeyY(double turkeyY) {
		this.turkeyY = turkeyY;
	}

	public int getTurkeySpeed() {
		return turkeySpeed;
	}

	public void setTurkeySpeed(int turkeySpeed) {
		this.turkeySpeed = turkeySpeed;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	public double getxMax() {
		return xMax;
	}

	public void setxMax(double xMax) {
		this.xMax = xMax;
	}

	public double getyMax() {
		return yMax;
	}

	public void setyMax(double yMax) {
		this.yMax = yMax;
	}

}
