import java.awt.*;
import javax.swing.*;

/**
 * @(#)Grid.java
 *
 *
 * @author Saner Turhaner
 * @version 1.00 2017/12/11
 */

public class Grid implements DrawableObject{

	//Properties 
	private int x, y; 
	boolean isThreaten;
	ImageIcon threat;
			
	//Constructor
    public Grid(int x, int y) {
    	this.x = x;
    	this.y = y;
    	isThreaten = false;
    	threat = new ImageIcon("src/cross.png");
    }
    
    //Getters Setters
    public void setX(int x){
    	this.x = x;
    }
    public void setY(int y){
    	this.y = y;
    }
    public void threaten(){
    	isThreaten = true;
    }
    public void removeThreat(){
    	isThreaten = false;
    }
    public int getX(){
    	return x;
    }
    public int getY(){
    	return y;
    }    
    public boolean isThreat(){
    	return isThreaten;
    }
    
    //Print
    public void print(){
    	if(this != null)
    		System.out.print(x + "," + y + " ");
    }
    
    //Other
    public boolean equal(Grid x){
    	if(this.getX() == x.getX() && this.getY() == x.getY())
    		return true;
    	return false;
    }
    
    //Draw
    @Override
	public void draw(Component c, Graphics g)
	{
		threat.paintIcon(c, g, (x-1)*90+54, (y-1)*90+51);						
	}
}