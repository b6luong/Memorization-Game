import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Random;
import java.awt.geom.*;

/*
 * This class creates patterns drawn inside a box.
 * 
 * @author Benjamin Luong 500655908.
 * @version March 11, 2015
 */
public class Pattern 
{
	//Constant(s)
	public static final int TOTAL_PATTERNS = 7;
	
	//Patterns
	public static final int PLUS = 0;
	public static final int CROSS = 1;
	public static final int CIRCLEPLUS = 2;
	public static final int SQUARE_X = 3;
	public static final int UP_TRIANGLE = 4;
	public static final int DOWN_TRIANGLE = 5;
	public static final int DIAMOND = 6;
	
	//Instance Variables
	private static Random random = new Random();
	private int type;
	private Rectangle bound;
	
	
	/*
	 * Constructor method to create a Pattern object with a random pattern type.
	 * 
	 * @param bbox the bounding Rectangle box.
	 */
	public Pattern(Rectangle bbox)
	{
		type = random.nextInt(TOTAL_PATTERNS);
		bound = bbox;
	} 
	
	
	/*
	 * Constructor method to create a Pattern object with a specific pattern type.
	 * 
	 * @param type integer corresponding to pattern type.
	 * @param bbox the bounding Rectangle box
	 */
	public Pattern(int type, Rectangle bbox)
	{
		this.type = type;
		bound = bbox;
	}
	
	
	/*
	 * Returns the type of pattern.
	 * 
	 * @return the integer corresponding to pattern.
	 */
	public int getType()
	{
		return type;
	}
	
	
	/*
	 * Sets the type of pattern.
	 * 
	 * @param atype the type of pattern to set.
	 */
	public void setType(int atype)
	{
		type = atype;
	}
	

	/*
	 * Compares pattern types between two Pattern objects.
	 * 
	 * @param other the other pattern object.
	 * @return true if the patterns are the same, false otherwise. 
	 */
	public boolean equals(Object other)
	{
		Pattern pOther = (Pattern) other;
		return this.type == pOther.type;
	}
	
	
	/*
	 * Draws the pattern type.
	 * 
	 * @param g2 graphics2D tool.
	 */
	public void draw(Graphics2D g2)
	{
		if (this.type == PLUS)
		{
			double vx = bound.getX() + (bound.getWidth() / 2);
			double vy1 = bound.getY();
			double vy2 = vy1 + bound.getHeight();
			Line2D.Double line = new Line2D.Double(vx, vy1, vx, vy2);
			g2.draw(line);
			double hy = bound.getY() + (bound.getHeight() / 2);
			double hx1 = bound.getX();
			double hx2 = hx1 + bound.getWidth();
			line.setLine(hx1, hy, hx2, hy);
			g2.draw(line);
		}//End of PLUS Pattern 
		else if (this.type == CROSS)
		{
			int x = (int)bound.getX() + (int)(bound.getWidth())/4;
			Rectangle r = new Rectangle(x,(int)bound.getY(),(int)bound.getWidth()/2,(int)bound.getHeight());
			int y = (int)bound.getY() + (int)(bound.getHeight()/4);
			Rectangle r2 = new Rectangle((int)bound.getX(),y, (int)bound.getWidth(), (int)(bound.getHeight()/2));
			g2.fill(r);
			g2.draw(r);
			g2.fill(r2);
			g2.draw(r2);
		}//End of CROSS Pattern 
		else if (this.type == CIRCLEPLUS)
		{
			double vx = bound.getX() + (bound.getWidth() / 2);
			double vy1 = bound.getY();
			double vy2 = vy1 + bound.getHeight();
			Line2D.Double line = new Line2D.Double(vx, vy1, vx, vy2);
			g2.draw(line);
			double hy = bound.getY() + (bound.getHeight() / 2);
			double hx1 = bound.getX();
			double hx2 = hx1 + bound.getWidth();
			line.setLine(hx1, hy, hx2, hy);
			g2.draw(line);
			
			Ellipse2D.Double circle = new Ellipse2D.Double(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight());
			g2.draw(circle);
		}//End of CIRCLE_PLUS Pattern 
		else if (this.type == SQUARE_X)
		{
			double x = bound.getX() + bound.getWidth();
			double y = bound.getY() + bound.getHeight();
			Line2D.Double line = new Line2D.Double(bound.getX(), bound.getY(), x, y);
			g2.draw(line);
			line.setLine(bound.getX(), y, x, bound.getY());
			g2.draw(line);
		}//End of SQUARE_X Pattern 
		else if (this.type == UP_TRIANGLE)
		{
			Point2D.Double tc = new Point2D.Double(bound.getX() + (bound.getWidth() / 2), bound.getY());
			Point2D.Double bl = new Point2D.Double(bound.getX(), bound.getY() + bound.getHeight());
			Point2D.Double br = new Point2D.Double(bound.getX() + bound.getWidth(), bound.getY() + bound.getHeight());
			Line2D.Double line = new Line2D.Double(tc,bl);
			g2.draw(line);
			line = new Line2D.Double(tc, br);
			g2.draw(line);
		}//End of UP_TRIANGLE pattern 
		else if (this.type == DOWN_TRIANGLE)
		{
			Point2D.Double bc = new Point2D.Double(bound.getX() + (bound.getWidth() / 2), bound.getY() + bound.getHeight());
			Point2D.Double tl = new Point2D.Double(bound.getX(), bound.getY());
			Point2D.Double tr = new Point2D.Double(bound.getX() + bound.getWidth(), bound.getY());
			Line2D.Double line = new Line2D.Double(bc,tl);
			g2.draw(line);
			line = new Line2D.Double(bc, tr);
			g2.draw(line);
		}//End of DOWN_TRIANGLE pattern 
		else if (this.type == DIAMOND)
		{
			Point2D.Double bc = new Point2D.Double(bound.getX() + (bound.getWidth() / 2), bound.getY() + bound.getHeight());
			Point2D.Double tl = new Point2D.Double(bound.getX(), bound.getY());
			Point2D.Double tr = new Point2D.Double(bound.getX() + bound.getWidth(), bound.getY());
			Line2D.Double line = new Line2D.Double(bc,tl);
			g2.draw(line);
			line = new Line2D.Double(bc, tr);
			g2.draw(line);
			Point2D.Double tc = new Point2D.Double(bound.getX() + (bound.getWidth() / 2), bound.getY());
			Point2D.Double bl = new Point2D.Double(bound.getX(), bound.getY() + bound.getHeight());
			Point2D.Double br = new Point2D.Double(bound.getX() + bound.getWidth(), bound.getY() + bound.getHeight());
			line = new Line2D.Double(tc,bl);
			g2.draw(line);
			line = new Line2D.Double(tc, br);
			g2.draw(line);
		}//End of Diamond pattern
	}//End of Draw method
	
} //End of class
