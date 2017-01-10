import javax.swing.JFrame;

/*
 * This is the viewer class for the BrainTrainer game.
 * 
 * @author Benjamin Luong 500655908.
 * @param March 11, 2015.
 */
public class GameTokenViewer 
{
	//Constant
	public static final int FRAME_WIDTH = 600;
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setSize(FRAME_WIDTH, FRAME_WIDTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setTitle("Brain Game");
		frame.getContentPane().add(new GameTokenPanel());
	}
	
}//End of Class
