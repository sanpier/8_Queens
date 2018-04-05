import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * @(#)Queen.java
 *
 *
 * @author Saner Turhaner
 * @version 1.00 2017/12/11
 */

public class Queen implements DrawableObject {
	
	//Properties
	private String type;
	private Threat threat;
	
	//GUI
	ImageIcon queen;

	//Constructors
    public Queen() {
    	type = "vezir";
    	threat = null;
    	queen = new ImageIcon("src/queen.png");
    }
    public Queen(int x, int y) {
    	type = "vezir";
    	threat = new Threat(type, x, y);
    	queen = new ImageIcon("src/queen.png");
    }
    
    //Functions
    public void relocate(int x, int y){
    	threat = new Threat(type, x, y);
    }
    
    //Getters
    public int getX(){
    	return threat.getX();
    }
    public int getY(){
    	return threat.getY();
    } 
    public ArrayList<Grid> getThreat(){
    	return threat.getArea();
    }   
    
    //Print, draw functions
    public void printThreatenArea(){
    	threat.printThreatArea();
    }  	
    
    public void drawThreatenArea(Component c, Graphics g){
    	threat.drawAll(c, g);
    }  
    	
    @Override
	public void draw(Component c, Graphics g)
	{
		queen.paintIcon(c, g, (getX()-1)*90+54, (getY()-1)*90+51);						
	}	
}