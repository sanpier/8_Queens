import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @(#)MainPanel.java
 *
 *
 * @author Saner Turhaner
 * @version 1.00 2017/12/11
 */

public class MainPanel extends JPanel{

	//Properties
	JFrame frame;
	Board board;
	
	boolean visualizeThreat;
	boolean solution;
	
	Timer timer;
	int time;
	
	//JButtons
	JButton solveB;
	JButton showThreat;
	JButton exit;
	
	//Constructor
    public MainPanel(Board given) {
    	//Panel Constructured
		setLayout(null);
		setPreferredSize(new Dimension(820,920));
		setBackground(Color.BLACK);
		
    	//Frame initialized
    	frame = new JFrame("Queens");			
		frame.setSize(820,920);		
		frame.setResizable(false);//Not changable	
		frame.setVisible(true);			
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().add(this);//Panel is added to frame		
    	frame.pack();//Size  
    	    	
    	//Board
    	board = given;    	
    	    	
    	//Threaten Area - Solution
    	visualizeThreat = false;
    	solution = false;
    	
    	//Timer
		TimerListener timeListener = new TimerListener();
		timer = new Timer(1500, timeListener);
		time = 0;		
				
		//JButtons initialized
		solveB = new JButton("Solve");
		solveB.setSize(new Dimension(150,50));
		solveB.setLocation(90,845);
		solveB.setFont(new Font("Monotype Corsiva", Font.PLAIN + Font.BOLD, 25));
		solveB.setBackground(new Color(238,232,170));
		
		showThreat = new JButton("Threat");
		showThreat.setSize(new Dimension(150,50));
		showThreat.setLocation(330,845);
		showThreat.setFont(new Font("Monotype Corsiva", Font.PLAIN + Font.BOLD, 25));
		showThreat.setBackground(new Color(238,232,170));
		
		exit = new JButton("Exit");
		exit.setSize(new Dimension(150,50));
		exit.setLocation(570,845);
		exit.setFont(new Font("Monotype Corsiva", Font.PLAIN + Font.BOLD, 25));
		exit.setBackground(new Color(238,232,170));
		
		//Add listener to buttons
		solveB.addActionListener(new ButtonListener());
		showThreat.addActionListener(new ButtonListener());
		exit.addActionListener(new ButtonListener());
		
		//Add components in panel
		add(solveB);
		add(showThreat);
		add(exit);
    }
    
    //Paint
    public void paintComponent(Graphics g)//Drawing cards
	{
		super.paintComponent(g);//Default (must)	
				
		board.draw(this, g);		
		if(solution){
			for(int i = 0; i <= time; i++){
				board.getQueens().get(i).draw(this, g);
				if(visualizeThreat)	
					board.getQueens().get(i).drawThreatenArea(this, g);				
			}
			if(time == 7)
				timer.stop();		
		}
				
		/*board.drawAllQueens(this, g);	
		if(visualizeThreat)
			board.drawAllThreats(this, g);*/					
	}
    
    //Listeners
    public class ButtonListener implements ActionListener//Inner class, listener for Buttons
	{
		public void actionPerformed(ActionEvent event)
		{
			try
			{
				Object current = event.getSource();
							
				if(current == exit)
					frame.dispose();
				else if(current == solveB){
					board.solve8QueensBruteForce(8);
					solution = true;
					
					time = 0;	
        			timer.start();
					repaint();		
				}
				else if(current == showThreat){
					if(!visualizeThreat)
						visualizeThreat = true;
					else
						visualizeThreat = false;
					repaint();
				}
					
			}
			catch(Exception e)
			{
				System.out.println("Exception is catched");
			}				
		}	
	}
    	
    public class TimerListener implements ActionListener//Listener for timer
	{
		public void actionPerformed(ActionEvent event)//Time passing
		{			
			repaint();								
			time++;
			time = time % 8;		
		}		
	}	
    						    
    //Functions
    public Board getBoard()
    {
		return board;
	}
	
    public void exit()//dispose() method exit from frame
	{
		frame.dispose();
	}	
}