

/*
 * 
 * 
 * Class Point : 
 * 
 * 
 *    Variables:
 * 			1. x        -> X coordinate of point
 * 		  	2. y        -> Y coordinate of point
 * 
 * 
 *    Methods :
 *    		1. Point()            -> Constructor to initialize with default values
 *    		2. Point(int,int)     -> Constructor to initialize with given values
 *    		3. setX()             -> To set X position of point
 *    		4. setY()             -> To set Y position of point
 *    		5. getX()             -> To get X position of point
 *    		6. getY()             -> To get Y position of point 
 *          
 *          
 */



public class Point {
	private int x,y;
	
	// Setting 0 0 as initial position
	public Point(){
		x = 0;
		y = 0;
	}
	
	//Setting the position by specified values
	public Point(int x,int y){
		this.x = x;
		this.y = y;
	}

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
}
