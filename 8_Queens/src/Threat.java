import java.awt.*;
import java.util.*;

/**
 * @(#)Threat.java
 *
 *
 * @author Saner Turhaner
 * @version 1.00 2017/12/11
 */

public class Threat {

	//Properties
	private String threatDefinition;
	private Grid location;
	private ArrayList<Grid> threatArea;	
	
	//Constructor
    public Threat(String threatDefinition, int x, int y) {
    	this.threatDefinition = threatDefinition; //Type of the chessman
		location = new Grid(x, y);
		threatArea = new ArrayList<Grid>();
		updateThreatArea();
    }
    
    //Functions
    public void updateLocation(int x, int y){
    	location.setX(x);
    	location.setY(y);    	
    	updateThreatArea();
    }
    
    public void updateThreatArea(){
    	if(threatDefinition.equalsIgnoreCase("vezir")){
    		int x,y;
    		
    		for(x = location.getX()+1; x <= 8; x++)//Right
    			threatArea.add(new Grid(x,location.getY()));
    		for(x = location.getX()-1; x >= 1; x--)//Left
    			threatArea.add(new Grid(x,location.getY()));
    		for(y = location.getY()+1; y <= 8; y++)//Down
    			threatArea.add(new Grid(location.getX(),y));
    		for(y = location.getY()-1; y >= 1; y--)//Up
    			threatArea.add(new Grid(location.getX(),y));
    		    		
    		//LeftUp
    		x = location.getX()-1;
    		y = location.getY()-1;
    		while(x >= 1 && y >= 1){
	    		threatArea.add(new Grid(x,y));
	    		x--;
	    		y--;
    		}    		
    		//RightUp
    		x = location.getX()+1;
    		y = location.getY()-1;
		    while(x <= 8 && y >= 1){
	    		threatArea.add(new Grid(x,y));
	    		x++;
	    		y--;
    		}     		
    		//RightDown
    		x = location.getX()+1;
    		y = location.getY()+1;
		    while(x <= 8 && y <= 8){
	    		threatArea.add(new Grid(x,y));
	    		x++;
	    		y++;
    		}     		
    		//LeftDown
    		x = location.getX()-1;
    		y = location.getY()+1;
		    while(x >= 1 && y <= 8){
	    		threatArea.add(new Grid(x,y));
	    		x--;
	    		y++;
    		} 
    	}
    }
        
    //Getters
    public ArrayList<Grid> getArea(){
    	return threatArea;
    }
    
    public int getX(){
    	return location.getX();
    }
    
    public int getY(){
    	return location.getY();
    }
    
    //Print draw
    public void printThreatArea(){
    	for(int i = 0; i < threatArea.size(); i++)
			threatArea.get(i).print();
    }
      
    public void drawAll(Component c, Graphics g){
    	for(int i = 0; i < threatArea.size(); i++){
    		threatArea.get(i).draw(c, g);
    	}
    }
}