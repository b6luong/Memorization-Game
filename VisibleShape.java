import java.awt.Graphics2D;

/*
 * This interface is to define common methods for VisibleShapes.
 * 
 * @author Benjamin Luong 500655908.
 * @version March 11, 2015
 */
public interface VisibleShape 
{
	void draw(Graphics2D g2);
	void setVisibilityPolicy();
	boolean overlaps(VisibleShape other);
}