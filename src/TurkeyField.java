import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Jerry Chen (yc4qy) Rachel Stadler (rvs5wj) Section: 103 Date: 11/21/2012
 */
/**
 * TurkeyField.java
 * 
 * The TurkeyField is the field of play for the game. It passes messages between
 * the Farmer and the Turkeys. It also picks up the commands from the mouse and
 * does the appropriate action. This is the class that will have the main method
 * to start the game.
 * 
 * @authors
 * @compid
 * @lab
 */
public class TurkeyField {

	// The TurkeyField needs a canvas to draw on. The canvas is the window that
	// we'll use as the field.
	private SimpleCanvas canvas;

	// The InfoFrame that you use for output instead of System.out. It makes it
	// as a popup to the side.
	private InfoFrame output;

	// --------------------------------------------------------------------------
	// Fields:
	// There are a number of things that the TurkeyField will have to deal with:
	// You should setup fields to store the following:
	// - something that can hold an ever-growing list of Turkeys
	// - a single Farmer
	// - some way to know if the game is over
	// - a way to keep track of how many "ticks" have gone by so you know when
	// to make another turkey appear
	// - how many Turkeys you should start with
	// - how many Turkeys it takes to end the game
	// - the time the game starts
	// - the time the game ends
	// --------------------------------------------------------------------------
	private ArrayList<Turkey> turkeys = new ArrayList<Turkey>();
	private Farmer bob;
	private boolean gameOver;
	private int ticks;
	private int initialTurkeys = 4;
	private int goal = 10;
	private int startTime = 0;
	private int endTime;

	Random rand = new Random();

	private double randomX;
	private double randomY;
	private int randomSide;

	private Dog myDog = new Dog(501, 501, 501, 501, 0);
	int dogRand = rand.nextInt(2);
	private int dogCounter = 0;

	// --------------------------------------------------------------------------
	// Methods - Declare your methods down here! Also, fill in the ones we tell
	// you to!

	/**
	 * The Constructor - This method should instantiate a new canvas, create a
	 * new player character, and create the first four turkeys in random
	 * locations around the board.
	 */
	public TurkeyField() {

		// Create canvas object with 500x500 spatial dimensions.
		canvas = new SimpleCanvas(500, 500, this);

		// Initialize your output frame
		output = new InfoFrame();

		// Here is where you should create your initial turkeys and your Farmer
		// 30 is a good speed for the farmer - 10 for the turkey, but
		// experiment!

		// Farmer(staring x pos, starting y pos, speed)
		bob = new Farmer(250, 250, 30);

		output.println("Turkey Farmer v1.0!");
		output.println("Lowest time wins!");

		// Add the appropriate number of turkeys to start the game
		for (int i = 0; i < initialTurkeys; i++) {
			int j = rand.nextInt(500);
			int k = rand.nextInt(500);
			turkeys.add(new Turkey(j, k, j, k, 500, 500, 10, bob));
		}
	}

	public SimpleCanvas getCanvas() {
		return canvas;
	}

	public void setCanvas(SimpleCanvas canvas) {
		this.canvas = canvas;
	}

	public InfoFrame getOutput() {
		return output;
	}

	public void setOutput(InfoFrame output) {
		this.output = output;
	}

	public ArrayList<Turkey> getTurkeys() {
		return turkeys;
	}

	public void setTurkeys(ArrayList<Turkey> turkeys) {
		this.turkeys = turkeys;
	}

	public Farmer getBob() {
		return bob;
	}

	public void setBob(Farmer bob) {
		this.bob = bob;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public int getTicks() {
		return ticks;
	}

	public void setTicks(int ticks) {
		this.ticks = ticks;
	}

	public int getInitialTurkeys() {
		return initialTurkeys;
	}

	public void setInitialTurkeys(int initialTurkeys) {
		this.initialTurkeys = initialTurkeys;
	}

	public int getGoal() {
		return goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public int timer() {
		int time = endTime - startTime;
		return time;

	}

	/**
	 * This method should control all of your mouse actions. The mouse activity
	 * is picked up by the SimpleCanvas and then it should call this method,
	 * passing either the button that was pressed or some other flag.
	 */
	public void mouseAction(float x, float y, int button) {
		if (button == -1) {
			// output.println("Mouse: " + x + "," + y);
			bob.setGoalX((int) x);
			bob.setGoalY((int) y);
		}

		if (button == 1) {
			output.println(bob.catchTurkey(bob, turkeys));
		}

		if (button == 3) {
			if (dogCounter == 0) {
				dogCounter++;
				myDog = new Dog(bob.getFarmerX(), bob.getFarmerY(), turkeys
						.get(dogRand).getTurkeyX(), turkeys.get(dogRand)
						.getTurkeyY(), 40);
				output.println("You have sent a dog!");
			} else
				output.println("You've already sent your dog!");
		}
	}

	/**
	 * This is the main drawing function that is automatically called whenever
	 * the canvas is ready to be redrawn. The 'elapsedTime' argument is the
	 * time, in seconds, since the last time this function was called.
	 * 
	 * Other things this method should do: - draw the turkeys and the farmer on
	 * the screen - tell the turkeys and farmer to move - check to see if the
	 * game is over after they move - halt the game if the game is over - update
	 * the number of ticks by 1 - add a new turkey every 20000 ticks
	 */
	public void draw(Graphics2D g, float elapsedTime) {

		// This is how you draw the Farmer, replacing the numbers with the x and
		// y of the farmer
		bob.move(elapsedTime);
		canvas.drawFarmer(g, bob.getFarmerX(), bob.getFarmerY());

		// This is how you draw the Turkeys, replacing the numbers with the x
		// and y of the Turkey
		for (int i = 0; i < turkeys.size(); i++) {
			turkeys.get(i).move(elapsedTime);
			canvas.drawTurkey(g, turkeys.get(i).getTurkeyX(), turkeys.get(i)
					.getTurkeyY());
		}

		// speeding up
		ArrayList<Double> distances = new ArrayList<Double>();
		for (int i = 0; i < turkeys.size(); i++) {
			distances.add(Math.sqrt((bob.getFarmerX() - turkeys.get(i)
					.getTurkeyX())
					* (bob.getFarmerX() - turkeys.get(i).getTurkeyX())
					+ (bob.getFarmerY() - turkeys.get(i).getTurkeyY()))
					* (bob.getFarmerY() - turkeys.get(i).getTurkeyY()));
			if (distances.get(i) < 100) {
				turkeys.get(i).setTurkeySpeed(25);
			}

			// dog
			if (turkeys.size() >= 2) {
				myDog.move(elapsedTime, turkeys.get(dogRand).getTurkeyX(),
						turkeys.get(dogRand).getTurkeyY());
				canvas.drawDog(g, myDog.getDogX(), myDog.getDogY());
			}

			// ticks
			if (goal > bob.getTurkeysCaught()) {
				ticks++;
				if (ticks % 20000 == 0) {
					randomSide = rand.nextInt(4);
					if (randomSide == 0) {
						randomX = 0;
						randomY = rand.nextInt(500);
					} else if (randomSide == 1) {
						randomX = 500;
						randomY = rand.nextInt(500);
					} else if (randomSide == 2) {
						randomX = rand.nextInt(500);
						randomY = 0;
					} else if (randomSide == 3) {
						randomX = rand.nextInt(500);
						randomY = 500;
					}
					turkeys.add(new Turkey(randomX, randomY, randomX, randomY,
							500, 500, 20, bob));
				}
			} else if (bob.getTurkeysCaught() == goal) {
				endTime = ticks;
				output.println("You won! Time: " + endTime);
				System.exit(i);
			}
		}

	}

	/**
	 * Your standard main method. Nothing for you to change here.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		TurkeyField simulator = new TurkeyField();
		simulator.play();

	}

	/**
	 * This method starts the game. Nothing for you to change here.
	 */
	public void play() {
		canvas.setupAndDisplay();
	}
}
