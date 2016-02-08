/**
 * Jerry Chen (yc4qy) Rachel Stadler (rvs5wj) Section: 103 Date: 11/21/2012
 */
public class Dog {

	private double dogX;
	private double dogY;
	private double dogGoalX;
	private double dogGoalY;
	private int dogSpeed = 30;
		
	public Dog(double dogX, double dogY, double dogGoalX, double dogGoalY,
			int dogSpeed) {
		super();
		this.dogX = dogX;
		this.dogY = dogY;
		this.dogGoalX = dogGoalX;
		this.dogGoalY = dogGoalY;
		this.dogSpeed = dogSpeed;
	}
	
	public void move(float elapsedTime, double x, double y) {
		dogGoalX = x;
		dogGoalY = y;
		if (dogGoalX > dogX && dogGoalY > dogY) {
			dogX = dogX + dogSpeed * (elapsedTime);
			dogY = dogY + dogSpeed * (elapsedTime);
		} else if (dogGoalX > dogX && dogGoalY < dogY) {
			dogX = dogX + dogSpeed * (elapsedTime);
			dogY = dogY - dogSpeed * (elapsedTime);
		} else if (dogGoalX < dogX && dogGoalY > dogY) {
			dogX = dogX - dogSpeed * (elapsedTime);
			dogY = dogY + dogSpeed * (elapsedTime);
		} else if (dogGoalX < dogX && dogGoalY < dogY) {
			dogX = dogX - dogSpeed * (elapsedTime);
			dogY = dogY - dogSpeed * (elapsedTime);
		}
	}
	
	public double getDogX() {
		return dogX;
	}
	public void setDogX(double dogX) {
		this.dogX = dogX;
	}
	public double getDogY() {
		return dogY;
	}
	public void setDogY(double dogY) {
		this.dogY = dogY;
	}
	public double getDogGoalX() {
		return dogGoalX;
	}
	public void setDogGoalX(double dogGoalX) {
		this.dogGoalX = dogGoalX;
	}
	public double getDogGoalY() {
		return dogGoalY;
	}
	public void setDogGoalY(double dogGoalY) {
		this.dogGoalY = dogGoalY;
	}
	public int getDogSpeed() {
		return dogSpeed;
	}
	public void setDogSpeed(int dogSpeed) {
		this.dogSpeed = dogSpeed;
	}

}
