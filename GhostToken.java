/*
 * GhostToken is a subclass of GameToken. GhostToken turns invisible 
 * after the player's memorization time passes.
 * 
 * @author Benjamin Luong 500655908.
 * @version March 11, 2015
 */
public class GhostToken extends GameToken
{
	/*
	 * Constructor method to create a GhostToken with a random pattern type.
	 * 
	 * @param x the top left x coordinate of the bounding box.
	 * @param y the top left y coordinate of the bounding box.
	 * @param width the width of the bounding box.
	 * @param height the height of the bounding box.
	 */
	public GhostToken(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}
	
	
	/*
	 * Constructor method to create a GhostToken with a specific pattern type.
	 * 
	 * @param patternType the pattern type of the GhostToken.
	 * @param x the top left x coordinate of the bounding box.
	 * @param y the top left y coordinate of the bounding box.
	 * @param width the width of the bounding box.
	 * @param height the height of the bounding box.
	 */
	public GhostToken(int patternType, int x, int y, int width, int height)
	{
		super(patternType, x, y, width, height);
	}
	
	
	/*
	 * Set the visibility policy to invisible for the GhostToken.
	 */
	public void setVisibilityPolicy()
	{
		setVisible(false);
	}//End of setVisibilityPolicy method
	
}//End of GhostToken
