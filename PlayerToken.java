import java.awt.Color;
import java.awt.Graphics2D;

/*
 * This class is a subclass of GameToken, PlayerToken is a special token designed for
 * the player only.
 * 
 * @author Benjamin Luong 500655908.
 * @version March 11, 2015.
 */
public class PlayerToken extends GameToken
{
	//Instance Variables
	private int current;//To keep track of current pattern type
	
	
	/*
	 * Constructor method to create a PlayerToken with a random pattern type.
	 * @param x the top left x coordinate of the bounding box.
	 * @param y the top left y coordinate of the bounding box.
	 * @param width the width of the bounding box.
	 * @param height the height of the bounding box.
	 */
	public PlayerToken(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		current = getPatternType();
		setColor(Color.BLUE);
		setVisible(false);
	}

	
	/*
	 * Constructor method to create a  PlayerToken with a specific pattern type. 
	 * @param patternType the pattern type of the token.
	 * @param x the top left x coordinate of the bounding box.
	 * @param y the top left y coordinate of the bounding box.
	 * @param width the width of the bounding box.
	 * @param height the height of the bounding box.
	 */
	public PlayerToken(int patternType, int x, int y, int width, int height)
	{
		super(patternType, x, y, width, height);
		current = getPatternType();
		setColor(Color.BLUE);
		setVisible(false);
	}
	
	/*
	 * Draws the player's token.
	 * @param g2 the Graphics2D tool
	 */
	public void draw(Graphics2D g2)
	{
		//if the token is invisible, set the color to the color of the background.
		if (getVisible() == false)
		{
			g2.setColor(Color.LIGHT_GRAY);
		}
		else
		{
			g2.setColor(getColor());
		}
		g2.draw(getBox());
		getPattern().draw(g2);
	}
	
	/*
	 * Changes the pattern on the token to the next type.
	 */
	public void update()
	{
		//If the current pattern is the last one, change it to the first
		if (current == Pattern.TOTAL_PATTERNS - 1)
		{
			current = 0;
		}
		//Change the current type to the next
		else
		{
			current++;
		}
		setPatternType(current);
	}//End of update method
	
}//End of class
