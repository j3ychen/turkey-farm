import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 * Jerry Chen (yc4qy) Rachel Stadler (rvs5wj) Section: 103 Date: 11/21/2012
 */
/**
 * SimpleCanvas.java
 * 
 * The SimpleCanvas class contains the drawing methods needed to manage the
 * TurkeyField. Many of the methods in this class will not be called by the
 * programmer - instead, they will be automatically called when something
 * occurs. For instance, mouseClicked is called when someone clicks on the
 * TurkeyField.
 * 
 * You should not have to make any changes to this class for the assignment.
 * However, you might make changes to the mouse methods at the end of this class
 * to add extra features.
 * 
 * @author Jason Lawrence and Mark Sherriff
 * 
 */
public class SimpleCanvas extends JPanel implements MouseListener,
		MouseMotionListener {

	private static final long serialVersionUID = 1L;

	// width and height of the window
	private int width;
	private int height;

	// lastTime that the screen was refreshed
	private long lastTime;

	// a link back to the TurkeyField for updating it
	private TurkeyField simulator;

	// the images of the turkey and the farmer
	private BufferedImage turkeyPic;
	private BufferedImage farmerPic;
	private BufferedImage dogPic;

	/**
	 * Constructor for the SimpleCanvas
	 * 
	 * @param width_
	 *            width of the window
	 * @param height_
	 *            height of the window
	 * @param simulator_
	 *            link back to the SurvivalField
	 */
	public SimpleCanvas(int width_, int height_, TurkeyField simulator_) {
		width = width_;
		height = height_;
		simulator = simulator_;
		lastTime = -1L;

		try {
			turkeyPic = ImageIO.read(new File("turkey.png"));
			farmerPic = ImageIO.read(new File("farmer.png"));
			dogPic = ImageIO.read(new File("dog.gif"));
		} catch (Exception e) {
			System.err.println("Images not found in project directory!");
		}
	}

	/**
	 * Called to start the game
	 */
	public void setupAndDisplay() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new JScrollPane(this));
		f.setSize(width, height);
		f.setLocation(200, 200);
		f.setVisible(true);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	/**
	 * This method is NEVER called by the programmer. This method is called
	 * whenever the screen refreshes. Consequently, it calls the draw() method
	 * in SurvivalField, telling it to update its components.
	 */
	protected void paintComponent(Graphics g) {
		boolean first = (lastTime == -1L);
		long elapsedTime = System.nanoTime() - lastTime;
		lastTime = System.nanoTime();
		g.setColor(new Color(34, 139, 34));
		g.fillRect(0, 0, width, height);
		g.setColor(Color.white);
		simulator.draw((Graphics2D) g, (first ? 0.0f
				: (float) elapsedTime / 1e9f));
		repaint();
	}

	/**
	 * drawDot does just what it says - it puts a dot on the screen. You might
	 * want to use this if you add a cool new feature to the game.
	 * 
	 * @param g
	 *            Graphics engine passed from paintComponent() into
	 *            TurkeyField.draw()
	 * @param x
	 *            x coordinate of dot
	 * @param y
	 *            y coordinate of dot
	 * @param color
	 *            Color instance of color of dot
	 */
	public void drawDot(Graphics2D g, double x, double y, Color color, int size) {
		g.setColor(color);
		g.fillOval((int) (x + 0.5f), (int) (y + 0.5f), 8 * size, 8 * size);
	}

	/**
	 * Draws a turkey picture on the Field.
	 * 
	 * @param g
	 *            Graphics engine passed from paintComponent() into
	 *            TurkeyField.draw()
	 * @param x
	 *            x coordinate of the turkey
	 * @param y
	 *            y coordinate of the turkey
	 */
	public void drawTurkey(Graphics2D g, double x, double y) {

		if (turkeyPic != null) {
			g.drawImage(turkeyPic, null, (int) x, (int) y);
		}

	}

	/**
	 * Draws a farmer picture on the Field.
	 * 
	 * @param g
	 *            Graphics engine passed from paintComponent() into
	 *            TurkeyField.draw()
	 * @param x
	 *            x coordinate of the farmer
	 * @param y
	 *            y coordinate of the farmer
	 */
	public void drawFarmer(Graphics2D g, double x, double y) {

		if (farmerPic != null) {
			g.drawImage(farmerPic, null, (int) x - 10, (int) y - 20);
		}

	}
	
	public void drawDog(Graphics2D g, double x, double y) {

		if (dogPic != null) {
			g.drawImage(dogPic, null, (int) x, (int) y);
		}

	}

	/**
	 * Whenever the mouse is moved on the TurkeyField, this method gets called.
	 */
	public void mouseMoved(MouseEvent e) {
		simulator.mouseAction((float) e.getX(), (float) e.getY(), -1);
	}

	/**
	 * Whenever the mouse is clicked on the TurkeyField, this method gets
	 * called.
	 */
	public void mouseClicked(MouseEvent e) {
		simulator
				.mouseAction((float) e.getX(), (float) e.getY(), e.getButton());
	}

	/**
	 * Whenever the mouse enters the TurkeyField, this method gets called.
	 */
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Whenever the mouse leaves the TurkeyField, this method gets called.
	 */
	public void mouseExited(MouseEvent e) {
	}

	/**
	 * Whenever the mouse is pressed (yes, it's just barely different than
	 * clicked) on the TurkeyField, this method gets called.
	 */
	public void mousePressed(MouseEvent e) {
	}

	/**
	 * Whenever the mouse press is released on the TurkeyField, this method gets
	 * called.
	 */
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Whenever the mouse clicked and dragged on the TurkeyField, this method
	 * gets called.
	 */
	public void mouseDragged(MouseEvent arg0) {
	}

}
