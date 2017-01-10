import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

/*
 * This is panel class contains all of the ActionListeners
 * and elements for the BrainTrainer game.
 * 
 * @author Benjamin Luong 500655908.
 * @version March 11, 2015
 */
public class GameTokenPanel extends JPanel
{
	//Constants
	private final int PREP_TIME = 5; //5 seconds for the player to memorize the game tokens
	private final int TOKEN_WIDTH = 30; //The size of the tokens
	
	//Instance Variables
	private ArrayList<GameToken> gameTokenList; //ArrayList that contains all computer game tokens
	private Random random = new Random();
	private int score;
	private PlayerToken userToken; //player's token
	private Timer gameTimer; //Keeps track of how long the game has been running for
	private Timer refresher; //Timer to repaint constantly
	private int time;
	private int tokensLeft;
	
	
	/*
	 * Constructor method to create panel with listeners, labels, etc.
	 */
	public GameTokenPanel()
	{
		//Initialize score, time and tokensLeft
		score = 0;
		time = 0;
		tokensLeft = 0;
		
		//Change the background of the frame
		setBackground(Color.LIGHT_GRAY);
		
		//Display the time and score
		final JLabel scoreKeeper = new JLabel("Score: " + score);
		final JLabel timeKeeper = new JLabel("Time: " + time);
		add(scoreKeeper);
		add(timeKeeper);
		
		//Create random number of Game Tokens with different positions
		gameTokenList = new ArrayList<GameToken>();
		int NUMBER_OF_TOKENS = random.nextInt(20) + 5;
	
		for (int i = 0; i < NUMBER_OF_TOKENS; i++)
		{
			boolean ontop = false;
			//Create a bigger bounding rectangle to test for overlapping
			Rectangle temp = new Rectangle(100,100, TOKEN_WIDTH + 20, TOKEN_WIDTH + 20); 
			do
			{
				ontop = false;
				//Generate random x and y locations
				int x = random.nextInt(GameTokenViewer.FRAME_WIDTH - 100);
				int y = random.nextInt(GameTokenViewer.FRAME_WIDTH - 150) + 50; //The position must be below the score board
				temp.setLocation(x, y);
				//Check for intersection with other game tokens
				for (GameToken gt: gameTokenList)
				{
					if (gt.getBox().intersects(temp))//If there exists an intersection	
					{
						ontop = true;
					}
				}
			} while (ontop);//Keep looking for a new location until no overlapping with the bigger bounding rectangle occurs
				
			int randomtype = random.nextInt(2)+1; //Randomize which type of game token to create
			GameToken newToken = null;;
			if (randomtype == 2)
			{
				//Make the new token of type GhostToken with the same location as the temp but with regular size
				newToken = new GhostToken((int)temp.getX(), (int)temp.getY(), TOKEN_WIDTH, TOKEN_WIDTH); 
			}
			else if (randomtype == 1)
			{
				//Make the new token of type BlinkingToken with the same location as the temp but with regular size
				newToken = new BlinkingToken((int)temp.getX(), (int)temp.getY(), TOKEN_WIDTH, TOKEN_WIDTH);
			}
			newToken.setColor(Color.RED);//Set the new token to be red
			gameTokenList.add(newToken);
			tokensLeft++;	
		}//End of for loop to create random GameTokens
		
		//Create player's token
		userToken = new PlayerToken(0, 0, TOKEN_WIDTH, TOKEN_WIDTH);
		
		//Initialize and declare ActionListener for GameTimer
		class GameTimerListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				//Increment time every second and display it on the screen
				time++;
				timeKeeper.setText("Time: " + time);
				
				//When the time has reached the max memorization time, the player can move it's token
				if(time == PREP_TIME)
				{
					//Make the player's token visible
					userToken.setVisible(true);
					
					//Set the visibility policy for each computer token
					for(GameToken gt: gameTokenList)
					{
						gt.setVisibilityPolicy();
					}
				}
			}//End of actionPerformed method
		}//End of GameTimerListener
		
		//Initialize game timer
		gameTimer = new Timer(1000, new GameTimerListener());
		gameTimer.start();
		
		//Initialize and Declare ActionListener for refresher
		class RefreshListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				repaint();
			}
		}//End of RefreshListener
		
		//Initialize refresher  
		refresher = new Timer(10, new RefreshListener());
		refresher.start();
		
		//Add and Declare MouseListener
		class MyMouseListener implements MouseListener
		{
			public void mouseClicked(MouseEvent event){}//End of MouseClicked
			
			public void mousePressed(MouseEvent event)
			{
				//Update the pattern on the player's token when the right mouse button is pressed
				if (event.getButton() == MouseEvent.BUTTON3)
				{
					userToken.update();
				} 
				//look for matches when the left mouse button is pressed
				else if (event.getButton() == MouseEvent.BUTTON1)
				{
					//Loop through each game token
					for (GameToken gt: gameTokenList)
					{
						//if the player's token overlaps one of the game tokens
						if(userToken.overlaps((VisibleShape)gt))
						{
							//Do nothing if the game token is set to green. 
							//Meaning this token is already evaluated
							if(gt.getColor() == Color.GREEN)
							{
								continue;
							}
							//Adjust score if the player's token equals the game token
							else if (userToken.equals(gt))
							{
								gt.setColor(Color.GREEN); //Set the color of the game token to green
								
								//Increment/decrement score depending on the number of attempt on this token.
								if (gt.getAttempts() == 0)
								{
									score += 2;
								}
								else if (gt.getAttempts() == 1)
								{
									score += 1;
								}
								else if (gt.getAttempts() > 2)
								{
									score -= 1;
								}
				
								//Display the new score and decrement the number of tokens left
								scoreKeeper.setText("Score: " + score);
								tokensLeft--;
							}//End of match
							//If the match was incorrect, increment attempts on this token
							else if (!userToken.equals(gt))
							{
								gt.incrementAttempts();
							}
						}//End overlap check
					}//End of OverLap Loop
					repaint();
					
				}//End of Left Click
			}//End of MousePressed
			
			public void mouseReleased(MouseEvent event)
			{
				
				//If there is no more game tokens left, make user's token invisible and stop game time
				if (tokensLeft == 0)
				{
					userToken.setVisible(false);
					gameTimer.stop();
				}
			}//End of MouseReleased
			
			public void mouseEntered(MouseEvent event){}//End of MouseEntered
			
			public void mouseExited(MouseEvent event){}//End of MouseExited
		}//End of MyMouseListener
		addMouseListener(new MyMouseListener());

		//Add and Declare MouseMotionListener
		class MyMouseMotion implements MouseMotionListener
		{
			public void mouseDragged(MouseEvent event){}//End of mouseDragged
			
			public void mouseMoved(MouseEvent event)
			{
				//If the userToken is visible
				if (userToken.getVisible())
				{
					//Make the userToken follow the cursor
					int x = event.getX();
					int y = event.getY();
					userToken.getBox().setLocation(x - 15, y - 15);
				}
			}//End of mouseMoved
		}//End of MyMouseMotion
		addMouseMotionListener(new MyMouseMotion());
	}//End of Constructor method
	
	
	/*
	 * Draws the game tokens in this game.
	 * 
	 * @param g the Graphics tool.
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		userToken.draw(g2);
		for (GameToken gt: gameTokenList)
		{
			gt.draw(g2);
		}
	}//End of paintComponent

}//End of Class
