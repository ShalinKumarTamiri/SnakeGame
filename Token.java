

/*
 * 
 * 
 * Class Token : 
 * 
 * 
 *    Variables:
 * 			1. x        -> X coordinate of token
 * 		  	2. y        -> Y coordinate of token
 * 			3. score    -> To get score
 * 			4. snake    -> Object of snake class
 * 			5. speed    -> Specifies speed of the snake
 * 
 *    Methods :
 *    		1. Token()            -> Constructor to initialize values
 *    		2. changePosition()   -> To change X position and Y position of the token
 *    		3. getScore()         -> To get score
 *    		4. getSpeed()         -> To get speed of the snake
 *    		5. draw()             -> To draw graphics
 *    		6. snakeCollision()   -> Check whether the token is collected by snake
 *          
 */



//Importing required packages
import java.awt.Color;
import java.awt.Graphics;


public class Token {

	private int x,y,score;
	private Snake snake;
	long speed;
	
	public Token(Snake snake){
		//Setting random positions to x and y where food or token located
		// we consider 389 because the size of the token should not exceed the window position
		x = (int)(Math.random() * 389);
		y = (int)(Math.random() * 389);
		this.snake = snake;
		//Setting initialize speed of the snake
		speed = 50;
		
	}
	
	public void changePosition(){
		//Setting random positions for the tokens
		x = (int)(Math.random() * 389);
		y = (int)(Math.random() * 389);
	}
	
	public int getScore(){
		return score;
	}
	
	public long getSpeed(){
		return speed;
	}
	
	public void draw(Graphics g){
		//Setting color and shape of the token
		g.setColor(Color.red);
		g.fillOval(x, y, 10, 10);
	}
	
	public boolean snakeCollision(){
		// we add 2 to the position of x and y because we need to find the center of the head of the snake and the size of the each block is 4
		int snakeX = snake.getX() + 2;
		int snakeY = snake.getY() + 2;
		//If any portion of the snake is touching any portion of token then increment the score and change the token position
		if(snakeX >= x-1 && snakeX <= (x+11)){
			if(snakeY >= y-1 && snakeY <= (y+11)){
				changePosition();
				//Incrementing the score and making elongate to true
				score++;
				snake.setElongate(true);
				//if speed is  greater than 20 then decrease the speed variable which will increase the speed of the snake  
				if(speed > 20){
					speed-=2;
				}
				return true;
			}
		}
		return false;	
	}
}
