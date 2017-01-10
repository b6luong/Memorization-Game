import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

/*
 * This class creates a GameToken which has a pattern inside of it.
 * 
 * @author Benjamin Luong 500655908.
 * @version March 11, 2015
 */
public class GameToken implements VisibleShape
{
	//Instance Variables
	private boolean visible;
	private Rectangle bbox;
	private Pattern pattern;
	private Color color;
	private int matchAttempts;
	
	
	/*
	 * Constructor method to create a GameToken object with a random pattern.
	 * 
	 * @param x the top left x coordinate of the token.
	 * @param y the top left y coordinate of the token.
	 * @param width the width of the token.
	 * @param height the height of the token.
	 */
	public GameToken(int x, int y, int width, int height)
	{
		bbox = new Rectangle(x, y, width, height);
		pattern = new Pattern(bbox);
		visible = true;
		color = Color.BLACK;
		matchAttempts = 0;
	}
	
	
	/*
	 * Constructor method to create a GameToken with a specific pattern.
	 * 
	 * @param patternType the pattern on the token.
	 * @param x the top left x coordinate of the token.
	 * @param y the top left y coordinate of the token.
	 * @param width the width of the token.
	 * @param height the height of the token.
	 */
	public GameToken(int patternType, int x, int y, int width, int height)
	{
		bbox = new Rectangle(x, y, width, height);
		pattern = new Pattern(patternType, bbox);
		visible = true;
		color = Color.BLACK;
		matchAttempts = 0;
	}
	
	
	/*
	 * Returns the bounding box of the GameToken.
	 * 
	 * @return the bounding box.
	 */
	public Rectangle getBox()
	{
		return this.bbox;
	}
	
	
	/*
	 * Set the bounding box of the GameToken to a new box.
	 * 
	 * @param box the new bounding box.
	 */
	public void setBox(Rectangle box)
	{
		bbox = box;
	}
	
	
	/*
	 * Sets visibility of GameToken, true to make token visible,
	 * false to make token invisible.
	 * 
	 * @param vis visibility status.
	 */
	public void setVisible(boolean vis)
	{
		visible = vis;
	}
	
	
	/*
	 * Returns the visibility status of the GameToken.
	 * 
	 * @return true if the token is visible, false if the token invisible.
	 */
	public boolean getVisible()
	{
		return this.visible;
	}
	
	
	/*
	 * Sets the pattern type for GameToken.
	 * 
	 * @param patternType the pattern type.
	 */
	public void setPatternType(int patternType)
	{
		pattern.setType(patternType);
	}
	
	
	/*
	 * Returns the pattern type of the GameToken.
	 * 
	 * @return the current pattern type.
	 */
	public int getPatternType()
	{
		return pattern.getType();
	}
	
	
	/*
	 * Returns the Pattern object associated with the GameToken.
	 * 
	 * @return pattern object
	 */
	public Pattern getPattern()
	{
		return this.pattern;
	}
	
	
	/*
	 * Set the color of the GameToken.
	 * 
	 * @param c the color to set the token. 
	 */
	public void setColor(Color c)
	{
		color = c;
	}
	
	
	/*
	 * Returns the color of the GameToken.
	 * 
	 * @return the color of the token.
	 */
	public Color getColor()
	{
		return color;
	}
	
	
	/*
	 * Returns the match attempt count.
	 * 
	 * @return the match attempt count
	 */
	public int getAttempts()
	{
		return this.matchAttempts;
	}
	
	
	/*
	 * Increments the match attempt count by 1.
	 */
	public void incrementAttempts()
	{
		matchAttempts++;
	}
	
	
	/*
	 * Checks this GameToken's pattern against another GameToken's pattern.
	 * 
	 * @param other the other Game Token.
	 * @return true if the patterns are the same, false otherwise.
	 */
	public boolean equals(Object other)
	{
		GameToken otherGT = (GameToken) other;
		return this.pattern.equals(otherGT.pattern);
	}
	
	//INTERFACE METHODS
	
	/*
	 * Draw VisibleShape object.
	 * 
	 * @param g2 the Graphics2D drawing tool.
	 */
	public void draw(Graphics2D g2)
	{
		//If the color of the token if green, the token is automatically visible
		if (color == Color.green)
		{
			visible = true;
		}
		
		g2.setColor(color);
		g2.draw(bbox);
		if (visible == false)
		{
			g2.setColor(Color.LIGHT_GRAY);
		}
		
		pattern.draw(g2);
	}//End of draw method
	
	
	/*
	 * Controls visibility of VisibleShape object. 
	 */
	public void setVisibilityPolicy()
	{
		visible = true;
	}//End of setVisibilityPolicy method
	
	
	/*
	 * Checks to see if This GameToken contains the center of the Other GameToken.
	 * 
	 * @param other the other VisibleShape object.
	 * @return true if This GameToken contains the center of the Other GameToken, false otherwise.
	 */
	public boolean overlaps(VisibleShape other)
	{
		GameToken otherGT = (GameToken) other;
		double centerX = (otherGT.bbox.getX()) + (otherGT.bbox.getWidth() / 2); //x coordinate of other's center
		double centerY = (otherGT.bbox.getY()) + (otherGT.bbox.getHeight() / 2); //y coordinate of other's center
		Point2D.Double otherCenter = new Point2D.Double(centerX, centerY); //the center point of the other object
		return this.bbox.contains(otherCenter); //check if the point is inside this bbox
	}//End of overlaps method
	
	
} //End of class
