
//Rikhil K. && Ronith K.
//Period 2-3

import javax.swing.JButton; //import necessary components
import javax.swing.JTextField;
               
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import javax.swing.JFrame;    
import javax.swing.JPanel;

import java.awt.Color;        
import java.awt.Graphics;

import java.awt.BorderLayout;    //import layouts
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.awt.Image;
import java.awt.Font;
import javax.swing.Timer;

import java.awt.event.ActionListener;    
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;    //to get user input from mouse and keyboard
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;    
import java.awt.event.KeyEvent;

public class FormulaExperiment
{    
    private JButton start;    //import JButtons that are used to switch between panels, start button is used to switch to the playing panel from the start panel
    private JButton help;    //used to switch to the helping panel from the start panel
    private JButton back;    //used to switch to start panel from the playing panel
    private JButton back2;    //used to switch to the start panel from the helping panel
    private JButton back5;
    private JButton extreme;
    private JButton race;
    private JButton replay;
    
    private JTextField rearSprings;
    private JTextField frontSprings;
   
    private CardPanel cp;        //declare necessary variables for card layout
    private CardLayout cards;
    private JFrame formula;    //JFrame used for the entire program
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
	private int vY, vX, x, y;
	private boolean up1, down1, left1, right1;
    
    public FormulaExperiment()
    {
        formula = new JFrame ("Formula Racer");    //instantiate  components and construct JFrame
        cp = new CardPanel();
        formula.getContentPane().add(cp);    //add CardLayout
        formula.setVisible(true);
        
        cards = new CardLayout();
        cp.setLayout(cards);
        
        
        StartPanel yay = new StartPanel();        //add panels into the CardLayout
        cp.add(yay, "Starting");
        
        Playing play = new Playing();        //panel in which user is given the weights of the car and uses them to get the correct suspension for the car
        cp.add(play, "Playing");
        
        RacePanel race = new RacePanel();        //panel in which user makes races their car to get faster times
        cp.add(race,"Racing");
        
        Helping thanks = new Helping();            //panel in which user gets directions on what how to play the game
        cp.add(thanks, "Helping");
        
        Equating bruh = new Equating();
        cp.add(bruh, "Equating");
        
        EndPanel end = new EndPanel();
        cp.add(end, "Ending");
        
        cards.first(cp);
    }
    
    public static void main(String[]args)
    {
        
        FormulaExperiment racer = new FormulaExperiment();
        racer.run();
    }
    
    class CardPanel extends JPanel
    {
        public CardPanel()
        {
        }
    }
    
    class Playing extends JPanel implements ActionListener
    {
        String front;
        String rear;
        public Playing()
        {
            setBackground(Color.GREEN);
            
            front = "";
            rear = "";
            
            setLayout(new GridLayout(2,1));  
            
            back = new JButton("Back");                    // create JButtons to switch to previous panels in the card layout
            back.setPreferredSize(new Dimension(300,100));    //enlarge to button to make it user friendly and better to look at
            back.addActionListener(this);
            add(back);
            
            race = new JButton("Race");                    //this button allows you to go from play panel to race panel
            race.setPreferredSize(new Dimension(300,100));    //enlarge to button to make it user friendly and better to look at
            race.addActionListener(this);
            add(race);
            
            frontSprings = new JTextField("Enter front springs pressure in pounds", 10);        //text field for user inputted answer for front suspension
            frontSprings.addActionListener(this);
            add(frontSprings);
            
            rearSprings = new JTextField("Enter rear springs pressure in pounds", 10);        //text field for user inputted answer for rear suspension
            rearSprings.addActionListener(this);
            add(rearSprings);
            
            repaint();
        }
        

        public void actionPerformed(ActionEvent e)
        {
            
            front = frontSprings.getText();        //get user inputed answers in the playing panel form the text fields and store into front and rear var
            rear = rearSprings.getText();
            
            System.out.println(rear);
            
            if(e.getActionCommand().equals("Back"))        //when the back button is pressed it should switch to the start panel
            {
                cards.show(cp ,"Starting");                //card layout with start panel
            }
            else if(e.getActionCommand().equals("Race"))        //when the race button is pressed it should switch to the race panel
            {
                cards.show(cp, "Racing");                //card layout with race panel
            }
            
        }
        
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            formula.setSize(1920, 1200);
            formula.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            formula.setResizable(true);
            formula.setVisible(true);
            g.drawString("Select your values", 100,100); //Import an image of a car here  
            BufferedImage image1 = null;
            try
            {
            image1 = ImageIO.read(new File("Blueprint.jpg"));    //Image for the start panel
            }
            catch(IOException e)
            {
                System.out.println("Error: "+e);
            }
            g.drawImage(image1, 0,0, 1920, 1200, null);
            g.setColor(Color.WHITE);
            Font font1 = new Font ("Comic Sans", Font.PLAIN,50);    //get readable font
            g.setFont(font1);
            g.drawString("Select your values", 780,200);  
            
        }
    }
        
    class Helping extends JPanel implements ActionListener
    {
        public Helping()
        {
            setBackground(Color.YELLOW);
            
            back2 = new JButton("Back");        //same as previous back button but in the helping class
            back2.setPreferredSize(new Dimension(300,100));
            back2.addActionListener(this);
            add(back2);
            
            
            repaint();
        }
        
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand().equals("Back"))
            {
                cards.show(cp ,"Starting");
            }
        }
        
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            formula.setSize(1920, 1200);
            formula.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            formula.setResizable(true);
            formula.setVisible(true);
            
            BufferedImage image1 = null;
            try
            {
            image1 = ImageIO.read(new File("Garage.jpg"));
            }
            catch(IOException e)
            {
                System.out.println("Error: "+e);
            }
            g.drawImage(image1, 0,0, 1920, 1200, null);
            
            g.setColor(Color.WHITE);
            Font font2 = new Font ("Comic Sans", Font.PLAIN,50);
            g.setFont(font2);
            
            g.drawString("You must click the mouse to start the car engine once on the track. ", 100,300);
            
            g.drawString("In the Play panel you will be given the weights of various sections of the car.", 100,400);
            
            g.drawString("You must use these weights and suspension formulas in order to get the", 100,475);
            g.drawString("maximum speed from your car.",100,525);
            
            g.drawString("Below are the formulas necessary to attain maximum speed from your car:",100,600);
            g.drawString("formulas",100,650);
            
            Font font1 = new Font ("Comic Sans", Font.PLAIN,50);          //Readable font
            g.setFont(font1);
            g.drawString("Directions", 850, 175);    
        }
    }
    public void run()
    {
        formula.setSize(1920, 1200);                
        formula.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formula.setResizable(true);
        formula.setVisible(true);
    }    
    class StartPanel extends JPanel
    {
    
        public StartPanel()
        {
            setBackground(Color.BLUE);    
            repaint();
                    
        start = new JButton("Start");    
        help = new JButton("Help");
        
        PlayHandler playHandler = new PlayHandler();
        HelpHandler helpHandler = new HelpHandler();
        start.setPreferredSize(new Dimension(300,100));
        start.addActionListener(playHandler);
        help.setPreferredSize(new Dimension(300,100));        //large buttons more user friendly
        help.addActionListener(helpHandler);
        
        add(start);
        add(help);
        repaint();
        }
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            BufferedImage image1 = null;
            try
            {
            image1 = ImageIO.read(new File("FormulaR.jpg"));        //Background for the title screen
            }
            catch(IOException e)
            {
                System.out.println("Error: "+e);
            }
            g.drawImage(image1, 0,0, 1920, 1200, null);

            g.setColor(Color.WHITE);
            Font font1 = new Font ("Comic Sans", Font.ITALIC,50);
            g.setFont(font1);
            g.drawString("Welcome to Formula Racer!", 200,500);        //Title Screen caption
        }
        class PlayHandler implements ActionListener        
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getActionCommand().equals("Start"))    //If the start panel is pressed then the user will get relocated to a panel with the formulas
                {
                    cards.show(cp ,"Equating");
                }
            }
        }
    
        class HelpHandler implements ActionListener        
        {
            public void actionPerformed(ActionEvent e)
            {
                String helpingCommand = e.getActionCommand();
                if(helpingCommand.equals("Help"))        //If the start panel is pressed then the user will get relocated to a panel with the directions
                {
                    cards.show(cp,"Helping");
                }
            }
        }
    }
    
    class EndPanel extends JPanel implements ActionListener
    {
        public EndPanel()
        {
            replay = new JButton("Replay");
            replay.setPreferredSize(new Dimension(300,100));
            replay.addActionListener(this);
            add(replay);
            
            repaint();
        }
        
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand().equals("Replay"))
            {
                cards.show(cp ,"Starting");
            }
        }
    }
    class Equating extends JPanel implements ActionListener
    {
        public Equating()
        {
            setBackground(Color.YELLOW);
            
            back5 = new JButton("Back");        //same as previous back button but in the helping class
            back5.setPreferredSize(new Dimension(300,100));
            back5.addActionListener(this);
            add(back5);
            extreme = new JButton("Race Setup");        //same as previous back button but in the helping class
            extreme.setPreferredSize(new Dimension(300,100));
            extreme.addActionListener(this);
            add(extreme);
            
            
            repaint();
        }
        
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand().equals("Back"))
            {
                cards.show(cp ,"Starting");
            }
            else if(e.getActionCommand().equals("Race Setup"))
            {
                cards.show(cp ,"Playing");
            }
        }
        
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            formula.setSize(1920, 1200);
            formula.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            formula.setResizable(true);
            formula.setVisible(true);
            
            BufferedImage image1 = null;
            try
            {
            image1 = ImageIO.read(new File("Blueprint.jpg"));
            }
            catch(IOException e)
            {
                System.out.println("Error: "+e);
            }
            g.drawImage(image1, 0,0, 1920, 1200, null);
            
            g.setColor(Color.WHITE);
            Font font2 = new Font ("Comic Sans", Font.PLAIN,50);
            g.setFont(font2);
            
           /* g.drawString("You must click the mouse to start the car engine once on the track. ", 100,300);
            
            g.drawString("In the Play panel you will be given the weights of various sections of the car.", 100,400);
            
            g.drawString("You must use these weights and suspension formulas in order to get the", 100,475);
            g.drawString("maximum speed from your car.",100,500);
            
            g.drawString("Below are the formulas necessary to attain maximum speed from your car:",100,600);
            g.drawString("formulas",100,600);
            
            Font font1 = new Font ("Comic Sans", Font.PLAIN,50);          //Readable font
            g.setFont(font1);
            g.drawString("Directions", 850, 175);   */
        }
    }
    class RacePanel extends JPanel implements ActionListener, MouseListener, KeyListener
    {
    private int x, y, count;
    private boolean left, up;
    private Timer timer;
    
        public RacePanel()
        {
            setBackground(Color.GREEN);
            vX = 860; vY = 1050; count = 0;        // initial track location of the car
            left = up = false;        // initialize the movement of the car;    
            back = new JButton("Back");
            back.setPreferredSize(new Dimension(300,100));    //Sets the size of the button
            back.addActionListener(this);
            add(back);
			addKeyListener(this);
			addMouseListener(this);
            repaint();
        }
        
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand().equals("Back"))        //Allows for the button to function
            {
                cards.show(cp ,"Starting");                //Implements StartPanel on the card layout
            }
        }
        
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            formula.setSize(1920, 1200);
            formula.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            formula.setResizable(true);
            formula.setVisible(true);
            BufferedImage image1 = null;
            try
            {
            image1 = ImageIO.read(new File("Track1.jpg"));           //This is the track the user will be racing on
            }
            catch(IOException e)
            {
                System.out.println("Error: "+e);
            }
            g.drawImage(image1, 0,0, 1920, 1200, null);        //draw images in the background to make the game look visually good
            g.setColor(Color.YELLOW);
            g.drawLine(850,965,850,1160);
            
            BufferedImage image6 = null;
            try
            {
            image6 = ImageIO.read(new File("LapEnd.png"));           //This is the track the user will be racing on
            }
            catch(IOException e)
            {
                //System.out.println("Error: "+e);
            }
            g.drawImage(image6, 850,975, 100, 200, null);
            g.setColor(Color.RED);
            BufferedImage image3 = null;
            try
            {
            image1 = ImageIO.read(new File("FormulaCar.png"));        //Imported the car image for the user input on the track
            }
            catch(IOException e)                                //Catches the exceptions
            {
                System.out.println("Error: "+e);
            }
            g.drawImage(image1, vX,vY,100, 50, null);
            g.setColor(Color.BLACK);
            g.drawRect(341,370,1256,72);
            g.drawRect(350,890,1222,69);       
		}

        public void mousePressed(MouseEvent e)
        {
            requestFocusInWindow();
            //count++;
            //if(count%2 == 1)
            //    timer.setDelay(60);        //The user has to click the mouse to start the car for a race start
            //else
            //{
            //    timer.setDelay(0);
            //}
            //System.out.println("Count = " + count);
        }
        public void mouseEntered(MouseEvent evt)
        {
            
        }
        public void mouseClicked(MouseEvent evt)
        {
            System.out.println(evt.getX());    //This gets the x value when the mouse is clicked on the race panel
            System.out.println(evt.getY()); //This gets the y value when the mouse is clicked on the race panel
        }
        public void mouseReleased(MouseEvent evt)
        {
            
        }
        public void mouseExited(MouseEvent evt)
        {
            
        }
        public void keyReturned(KeyEvent e){}
        public void keyTyped(KeyEvent e){}
		
		public void go() 
		{
			x += vX;
			y += vY;
		}

		private void update() 
		{

			if(down) vY += 20;
			if(up) vY -= 20;
			if(left) vX -= 35;
			if(right) vX += 35;
			System.out.println("update called..");
			
		}

		public void keyPressed(KeyEvent e) 
		{
			switch(e.getKeyCode()) 
			{
				down = true;
				up = true;
				left = true;
				right = true;
			}
			update();
			System.out.println("key pressed called..");
		}

		public void keyReleased(KeyEvent e) 
		{
			switch(e.getKeyCode()) 
			{
				down = false;
				up = false;
				left = false;
				right = false;
			}
			update();
			System.out.println("key released called..");
		}
	}
	    //If the user goes off the track, the car will reduce to the base speed
    //Variable used for checkpoints the user needs to pass for the laps to count
    //Text fields used for getting using input for the suspension values
    //Check whether the user input roughly matches with the correct calculated values
    //Judge how fast the car should go in the turns based on the inputted values
    //Use flow layout for various text boxes inside the play panel
    //If 'q' is pressed then a pop up will come and this will allow to user to go back to the start screen

}   

