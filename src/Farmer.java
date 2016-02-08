import java.util.ArrayList;
import java.util.Random;

/**
 * Jerry Chen (yc4qy) Rachel Stadler (rvs5wj) Section: 103 Date: 11/21/2012
 */
/**
 * Farmer.java
 * 
 * The player's character. It should have a location, a location to which it is
 * trying to go, a relative speed, and a counter as to how many turkeys it has
 * caught.
 * 
 * Some descriptions of particularly important methods are provided below. You
 * will probably have to create more methods than just those provided. You are
 * also allowed to change the methods provided if you want. They have been added
 * as a guide.
 * 
 * @authors
 * @compids
 * @lab
 * 
 */
public class Farmer {

	// Fields - Add the fields here!
	private double farmerX;
	private double farmerY;
	private double goalX;
	private double goalY;
	private int farmerSpeed;
	private int turkeysCaught;

	// Methods ------------------------------------------------
	/**
	 * This method controls how the Farmer catches turkeys. By default, it takes
	 * an ArrayList of Turkeys as a parameter. The method should then determine
	 * if any of the Turkeys are within 50 pixels of the farmer. If so, the
	 * number of turkeys caught should go up accordingly and the caught turkeys
	 * should be removed from the ArrayList. The method returns information on
	 * how many turkeys were caught so that can be displayed to the output.
	 * 
	 * You are allowed to change this method if you have a different algorithm.
	 * 
	 * @param turkeys
	 *            An ArrayList of all the Turkeys on the field
	 * @return The string you want to print to the output
	 */

	public Farmer(int farmerX, int farmerY, int farmerSpeed) {
		super();
		this.farmerSpeed = farmerSpeed;
		this.farmerX = farmerX;
		this.farmerY = farmerY;
	}

	public double getFarmerX() {
		return farmerX;
	}

	public void setFarmerX(double farmerX) {
		this.farmerX = 250;
	}

	public double getFarmerY() {
		return farmerY;
	}

	public void setFarmerY(double farmerY) {
		this.farmerY = 250;
	}

	public double getGoalX() {
		return goalX;
	}

	public void setGoalX(double goalX) {
		this.goalX = goalX;
	}

	public double getGoalY() {
		return goalY;
	}

	public void setGoalY(int goalY) {
		this.goalY = goalY;
	}

	public int getFarmerSpeed() {
		return farmerSpeed;
	}

	public void setFarmerSpeed(int farmerSpeed) {
		this.farmerSpeed = farmerSpeed;
	}

	public int getTurkeysCaught() {
		return turkeysCaught;
	}

	public void setTurkeysCaught(int turkeysCaught) {
		this.turkeysCaught = turkeysCaught;
	}

	public String catchTurkey(Farmer bob, ArrayList<Turkey> turkeys) {
		String temp = "";
		ArrayList<Double> distances = new ArrayList<Double>();
		for (int i = 0; i < turkeys.size(); i++) {
			distances.add(Math.sqrt((bob.getFarmerX() - turkeys.get(i).getTurkeyX())
					* (bob.getFarmerX() - turkeys.get(i).getTurkeyX())
					+ (bob.getFarmerY() - turkeys.get(i).getTurkeyY()))
					* (bob.getFarmerY() - turkeys.get(i).getTurkeyY()));
			if (distances.get(i) < 50) {
				turkeys.remove(i);
				turkeysCaught += 1;
				return "You have caught " + turkeysCaught + " turkeys.";
			} else if (distances.get(i) >= 50) {
				temp = "No turkeys nearby!";
			}
		}
		return temp;
	}

	/**
	 * Move the Farmer according to its speed and the elapsed time since the
	 * last screen refresh. The Farmer should always move toward the last
	 * location of the mouse pointer. This can be represented by target x and y
	 * values that are updated in TurkeyField when the mouse pointer moves.
	 * 
	 * @param elapsedTime
	 *            seconds since last update
	 */
	public void move(float elapsedTime) {
		if (goalX > farmerX && goalY > farmerY) {
			farmerX = farmerX + farmerSpeed * (elapsedTime);
			farmerY = farmerY + farmerSpeed * (elapsedTime);
		} else if (goalX > farmerX && goalY < farmerY) {
			farmerX = farmerX + farmerSpeed * (elapsedTime);
			farmerY = farmerY - farmerSpeed * (elapsedTime);
		} else if (goalX < farmerX && goalY > farmerY) {
			farmerX = farmerX - farmerSpeed * (elapsedTime);
			farmerY = farmerY + farmerSpeed * (elapsedTime);
		} else if (goalX < farmerX && goalY < farmerY) {
			farmerX = farmerX - farmerSpeed * (elapsedTime);
			farmerY = farmerY - farmerSpeed * (elapsedTime);
		}
	}

}
