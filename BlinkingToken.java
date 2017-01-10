import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

/*
 * This class is subclass of GameToken, BlinkingToken blinks for random intervals after
 * the player's memorization time passes.
 * 
 * @author Benjamin Luong 500655908.
 * @version March 11, 2015.
 */
public class BlinkingToken extends GameToken
{
	//Instance Variables
	private Random random = new Random();
	private final int DELAY_IN_MILLISECONDS = 2000;
	private boolean vis;
	
	
	/*
	 * Constructor method to create a BlinkingToken with a random pattern.
	 * @param x the top left x coordinate of the bounding box.
	 * @param y the top left y coordinate of the bounding box.
	 * @param width the width of the bounding box.
	 * @param height the height of the bounding box.
	 */
	public BlinkingToken(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}//End of Contructor method
	
	
	/*
	 * Constructor method to create a BlinkingToken with a specific pattern.
	 * @param patternType the pattern to set the token.
	 * @param x the top left x coordinate of the bounding box.
	 * @param y the top left y coordinate of the bounding box.
	 * @param width the width of the bounding box.
	 * @param height the height of the bounding box.
	 */
	public BlinkingToken(int patternType, int x, int y, int width, int height)
	{
		super(patternType, x, y, width, height);
	}//End of Constructor method.
	
	
	/*
	 * Set the visibility policy for token.
	 */
	public void setVisibilityPolicy()
	{
		int DELAY_TIME = random.nextInt(DELAY_IN_MILLISECONDS);
		class TimerListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				if (getVisible())
				{
					setVisible(false);
				}
				else
				{
					setVisible(true);
				}
			}//End of actionPerformed
		}//End of TimerListener
		Timer blinkTimer = new Timer(DELAY_TIME, new TimerListener());
		blinkTimer.start();
	}//End of setVisibilityPolicy
	
}//End of class
