

/*
 * 
 * 
 * Class SnakeGame : 
 * 
 * 
 *    Variables:
 * 			1. f1        -> Object of Font class
 * 		  	2. gfx       -> To create graphics to the image
 * 			3. img       -> To create image  
 * 			4. snake     -> Object of Snake class
 * 			5. gameOver  -> Checking whether is over or not
 * 			6. token     -> Object of Token class
 * 			7. thread    -> Object of Thread class
 * 
 *    Methods :
 *    		1. init()             -> Initialize the variables and initializing the applet
 *    		2. paint()            -> To draw graphics
 *    		3. update()           -> To update the graphics
 *    		4. repaint()          -> To repaint the graphics
 *    		5. run()              -> To run a thread and the movement of the snake
 *    		6. checkGameOver()    -> Checking for game ending conditions
 *    		7. keyPressed()       -> To get key events 
 *          
 */



//Importing required packages
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


//SnakeGame inherit the class Applet  and interfaces Runnable and KeyListener
public class SnakeGame extends Applet implements Runnable, KeyListener{

	Font f1;
	Graphics gfx;
	Image img;
	Thread thread;
	Snake snake;
	boolean gameOver;
	Token token;
	
	/* Initializing all variables */
	public void init(){
		gameOver = false;
		//Setting screen size and creating image of that size
		this.resize(400, 400);
		img = createImage(400,400);
		gfx = img.getGraphics();
		//Setting font and declaration of objects
		f1 = new Font("Elephant",Font.ITALIC,20);
		this.addKeyListener(this);
		snake = new Snake();
		token = new Token(snake);
		//Thread creation and starting the thread
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g){
		//Setting title of the window by using frame
		Frame title = (Frame)this.getParent().getParent();
	    title.setTitle("⟆ⲛⲁⲕⲉ 𝓖ⲁⲙⲉ");
        //Setting background color to image and setting position of that
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, 400, 400);
		//Checking whether game is not ended, if not draw the snake 
		if(!gameOver){
			snake.draw(gfx);
			token.draw(gfx);
		}
		//If game ended display the text as specified
		else{
			gfx.setColor(Color.lightGray);
			gfx.setFont(f1);  
			gfx.drawString("GAME OVER", 140 , 150);
			gfx.drawString("Score : " +  token.getScore(), 150 , 190);
		}
		g.drawImage(img,0,0,null);
	}
	
	public void update(Graphics g){
		paint(g);
	}
	
	public void repaint(Graphics g){
		paint(g);
	}

    
	public void run() {
		//Declaring infinite loop because game ends only if snake touches itself or walls 
		for(;;){
			//checking whether the game is not ended 
			if(!gameOver){
				snake.move();
				//Checking either the game is ended because of the previous move or it collects the token 
				this.checkGameOver();
				token.snakeCollision();
			}
			this.repaint();
			try {
				//sleep is used to control the speed of the snake
				Thread.sleep(token.getSpeed());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void checkGameOver(){
		//conditions for snake touching the walls of the window
		// 396 is taken because the size of the each block of the size is 4.
		//If x increases more than that some portion of the snake will be removed 
		if(snake.getX() < 0 || snake.getX() > 396){
			gameOver = true;
		}
		if(snake.getY() < 0 || snake.getY() > 396){
			gameOver = true;
		}
		//Condition for snake touching itself
		if(snake.snakeCollision()){
			gameOver = true;
		}
	}

/* The following methods are declared to get the keyboard events 
 * Two of them does not contain anything but we need to declare those because we are implementing KeyListener interface
 */
	
	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		if(!snake.isMoving()){
			//Checking whether the key pressed by the user is up or down or left or right
			if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT){
				snake.setisMoving(true);
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_UP){
			// 1 represents going down
			if(snake.getyDir() != 1){
				//Changing y directions to going up
				snake.setyDir(-1);
				//setting x direction as no direction
				snake.setxDir(0);
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			if(snake.getyDir() != -1){
				//Changing y directions to going down
				snake.setyDir(1);
				//setting x direction as no direction
				snake.setxDir(0);
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			if(snake.getxDir() != 1){
				snake.setxDir(-1);
				snake.setyDir(0);
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			if(snake.getxDir() != -1){
				snake.setxDir(1);
				snake.setyDir(0);
			}
		}
	}


	public void keyReleased(KeyEvent e) {
		
	}
}
