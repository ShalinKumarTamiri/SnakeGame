

/*
 * 
 * 
 * Class Snake : 
 * 
 * 
 *    Variables:
 * 			1. snakePoints -> Points of the snake
 * 		  	2. xDir        -> X coordinate of snake
 * 			3. yDir        -> Y coordinate of snake
 * 			4. isMoving    -> For checking snake motion
 * 			5. elongate    -> Checking whether snake size is increasing
 * 			6. STARTSIZE   -> Initial size of the snake
 * 			7. STARTX      -> Starting x coordinate of snake
 * 			8. STARTY      -> Starting y coordinate of snake
 * 
 * 
 *    Methods :
 *    		1. Snake()            -> Constructor
 *    		2. draw()             -> To draw graphics
 *    		3. move()             -> To move the snake
 *    		4. snakeCollision()   -> Checking whether snake touches itself
 *    		5. isMoving()         -> Checking whether the snake is moving or not
 *    		6. setisMoving()      -> Changing the value of the isMoving variable
 *    		7. getxDir()          -> To get X direction
 *    		8. getyDir()          -> To get Y direction
 *    		9. setxDir()          -> To set X direction
 *    		10. setyDir()         -> To set Y direction
 *    		11. getX()            -> To get X position of point in snake
 *    		12. getY()            -> To get Y position of point in snake
 *          13. setElongate()     -> To set elongate value
 *          
 *          
 */



//Importing required packages
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;



public class Snake { 
	
	
	//Declaration of the variables
	List<Point> snakePoints;
	int xDir,yDir;
	boolean isMoving,elongate;
	//Setting initial size,X position and Y position of the snake
	final int STARTSIZE = 20, STARTX = 150,STARTY = 150;
	
	public Snake(){
		snakePoints = new ArrayList<Point>();
		xDir = 0;
		yDir = 0;
		isMoving = false;
		elongate = false;
		snakePoints.add(new Point(STARTX,STARTY));
		//Creating snake with initial size 
		for(int i = 1; i < STARTSIZE ; i++){
			//Body of the snake has to appear in left of head because of that we use 'STARTX-i * 4' 
			snakePoints.add(new Point(STARTX-i * 4, STARTY));
		}
	}
	
	public void draw(Graphics g){
		//Setting color of the snake and applying color for every point in the snake
		g.setColor(Color.cyan);
		for(Point p : snakePoints){
			g.fill3DRect(p.getX(), p.getY(), 4, 4,true);
		}
	}
	
	public void move(){
		//Checking whether the snake is moving 
		if(isMoving){
			//Getting head and last position of the snake
			Point temp = snakePoints.get(0);
			Point last = snakePoints.get(snakePoints.size() - 1);
			//Creating new head by incrementing previous head positions  to 4
			Point newStart = new Point(temp.getX() + xDir * 4 ,temp.getY() + yDir * 4);
			for(int i = snakePoints.size()-1 ; i >= 1 ; i--){
				//Making every current position to next position 
				snakePoints.set(i,snakePoints.get(i-1));
			}
			//Inserting head
			snakePoints.set(0,newStart);
			//Checking whether snake is it elongating  
			if(elongate){
				//Increasing length of the snake by adding previous last position to the current last position 
				snakePoints.add(last);
				elongate = false;
			}
		}
	}
	
	public boolean snakeCollision(){
		//Getting x and y positions of the snake head
		int headX = this.getX();
		int headY = this.getY();
		// Collisions occurs when head touches any portion of the snake body
		for(int i = 1; i< snakePoints.size() - 1 ; i++){
			//Checking whether x and y position of the current body of the snake matches to the head x and y positions
			if(snakePoints.get(i).getX() == headX && snakePoints.get(i).getY() == headY){
				return true;
			}
		}
		//Collision does not occurs then return false
		return false;
	}
	
	public boolean isMoving(){
		return isMoving;
	}
	
	public void setisMoving(boolean b){
		isMoving = b;
	}
	
	public int getxDir(){
		return xDir;
	}
	
	public int getyDir(){
		return yDir;
	}
	
	public void setxDir(int xDir){
		this.xDir = xDir;
	}
	
	public void setyDir(int yDir){
		this.yDir = yDir;
	}
	
	public int getX(){
		return snakePoints.get(0).getX();
	}
	
	public int getY(){
		return snakePoints.get(0).getY();
	}

	public void setElongate(boolean  b){
		elongate = b;
	}

}
