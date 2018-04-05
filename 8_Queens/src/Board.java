import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * @(#)Board.java
 *
 *
 * @author Saner Turhaner
 * @version 1.00 2017/12/11
 */

public class Board implements DrawableObject {
	
	//Properties
	private ArrayList<Grid> grids;
	private ArrayList<Queen> vezirList;
	
	//GUI
	ImageIcon board;
	
	//Constructor	
    public Board() {
    	initialize();
    	vezirList = new ArrayList<Queen>();
    	board = new ImageIcon("src/chessboard.png");
    }  
    	
    //Functions    
    private void initialize(){
    	grids = new ArrayList<Grid>();
    	
    	//Grids
    	for(int i = 1; i <= 8; i++){
    		for(int j = 1; j <= 8; j++)
    			grids.add(new Grid(i,j));
    	}
    } 
    
    //Threat Calculations	
    public void calcThreatenGrids(){
    	//First remove all
    	removeThreats();    	
    	
    	int x, y;
    	for(int i = 0; i < vezirList.size(); i++){
    		//Location of the queen is threaten
    		x = vezirList.get(i).getX();
    		y = vezirList.get(i).getY();
    		getGrid(x,y).threaten();
    		
    		//All threat areas of that queen will be threaten
    		for(int j = 0; j < vezirList.get(i).getThreat().size(); j++){
    			x = vezirList.get(i).getThreat().get(j).getX();
    			y = vezirList.get(i).getThreat().get(j).getY();
    			getGrid(x,y).threaten();
    		}   		
    	}
    }
    
    public ArrayList<Grid> getFreeGrids(){ 
    	ArrayList<Grid> list = new ArrayList<Grid>(); 
    	for(int i = 0; i < 64; i++){    		
    		if(!grids.get(i).isThreat())
    			list.add(grids.get(i));
    	}	   
    	return list;  	
    }
    
    public ArrayList<Grid> getThreatenGrids(){ 
    	ArrayList<Grid> list = new ArrayList<Grid>();    	    	
    	for(int i = 0; i < 64; i++){    		
    		if(grids.get(i).isThreat())
    			list.add(grids.get(i));
    	}	
    	return list;    	
    }
    
    public void removeThreats(){
    	for(int i = 0; i < grids.size(); i++){
    		grids.get(i).removeThreat();    		    		
    	}
    }
    
    //Getters
    public ArrayList<Queen> getQueens(){
    	return vezirList;
    }
    
    public Grid getGrid(int x, int y){
    	return grids.get((x-1)*8+y-1);
    }
            
    //Play game  
    public void addQueen(int x, int y){
    	Queen newOne = new Queen(x, y);
    	vezirList.add(newOne);
    	calcThreatenGrids();//Automatically calculate free and threaten grids
    }
    	
    public int addRandomQueen(){
    	/*try{    		
    		Thread.sleep(1000);  
    	}
    	catch(Exception e){
    		System.out.print("Exception " + e.toString() + " is catched.");
    	}*/
    	if(getFreeGrids().size() > 0){
    		Random rand = new Random();  	
			int random = rand.nextInt(getFreeGrids().size());
			Grid randomGrid = getFreeGrids().get(random);
			Queen newOne = new Queen(randomGrid.getX(), randomGrid.getY());
	    	vezirList.add(newOne);
	    	calcThreatenGrids();//Automatically calculate free and threaten grids
	    	
	    	return random;
    	}
		return -1;
    }
    
    public int addLeastThreatfulQueen(){    	
		if(getFreeGrids().size() > 0){
			Queen newOne = new Queen();
			int num = 64;
			int x = 0;
			int y = 0;
			
			for(int i = 0; i < getFreeGrids().size(); i++){
				newOne.relocate(getFreeGrids().get(i).getX(),getFreeGrids().get(i).getY());
				calcThreatenGrids();
				if(getThreatenGrids().size() < num){
					num = getThreatenGrids().size();
					x = getFreeGrids().get(i).getX();
					y = getFreeGrids().get(i).getY();					
				}
			}
			
			newOne.relocate(x,y);
			vezirList.add(newOne);
	    	calcThreatenGrids();//Automatically calculate free and threaten grids
	    	
	    	return num;
    	}
    	
    	return -1;
    }
    
    public void resetBoard(){
    	removeThreats();
     	vezirList.clear();
    }
    
    public void solve8QueensBruteForce(int n){
    	if(n == 0)
    		return;
    	else{
    		if(addRandomQueen() != -1){
    			n--;
    			solve8QueensBruteForce(n);
    		}
    		else{
    			resetBoard();
    			solve8QueensBruteForce(8);
    		}
    	}
    }
            
    //Print functions
    public void printFreeGrids(){
    	System.out.print("Free grids are: ");
    	for(int i = 0; i < grids.size(); i++)
    		if(!grids.get(i).isThreat())
    			grids.get(i).print();
    }
    
    public void printThreatenGrids(){
    	System.out.print("Threaten grids are: ");
    	for(int i = 0; i < grids.size(); i++)    		
    		if(grids.get(i).isThreat())
    			grids.get(i).print();
    }
    
    //Draw
    @Override
	public void draw(Component c, Graphics g)
	{
		board.paintIcon(c, g, 10, 10);						
	}   
		
	public void drawAllQueens(Component c, Graphics g)
	{
		for(int i = 0; i < vezirList.size(); i++)
			vezirList.get(i).draw(c, g);						
	}
	
	public void drawAllThreats(Component c, Graphics g)
	{
		for(int i = 0; i < vezirList.size(); i++)
			vezirList.get(i).drawThreatenArea(c, g);						
	}   
}