//Programmer: Aidan Campbell
//Description: Game of Flood it (Filler)
//Date Created: 08/13/2018
//Date Revised: 08/14/2018

import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FloodIt implements ActionListener

{

   JFrame f;
   JPanel contentPane, main1, main2, main3;
   JButton pinkB, orangeB, yellowB, greenB, blueB, purpleB;
   Color[] colours = new Color[6];
   JPanel[][] board = new JPanel[14][14];
   int[][] boardColours = new int[14][14];
   int[][] userArea = new int[14][14];
   JLabel name, moves;
   int moveCount = 0;
   int userSpaces = 0;
   String username = "";

   public FloodIt()
   
   {
      
      username = JOptionPane.showInputDialog(null, "Enter your name");
      
      Random ran = new Random();
      colours[0] = Color.decode("#FF0080");//pink background
      colours[1] = Color.decode("#FF8000");//orange background
      colours[2] = Color.decode("#FFFF00");//yellow background
      colours[3] = Color.decode("#00FF00");//green background
      colours[4] = Color.decode("#00FFFF");//blue background
      colours[5] = Color.decode("#8000FF");//purple background
      
      f = new JFrame("Flood It!");
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      contentPane = new JPanel();
      contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
      contentPane.setBorder(BorderFactory.createEmptyBorder(0,30,30,30));
      
      main1 = new JPanel();
      main1.setLayout(new GridLayout(15,14,0,0));
      main1.setBorder(BorderFactory.createEmptyBorder(0,70,10,70));//top, left, bottom, right 
      main2 = new JPanel();
      main2.setLayout(new GridLayout(1,6,10,50));
      main2.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
      main3 = new JPanel();
      
      for(int i = 0; i < 14; i++)
      {
         JPanel temp = new JPanel();
         temp.add(new JLabel(new ImageIcon("pink1.png")));
         main1.add(temp);
         temp.setVisible(false);
      }
      
      userArea[0][0] = 1;
      for(int i = 0; i < 196; i++)
      {
         if(!(i==0 || i==1 || i==14 || i==15))
         {
            int ranColour = ran.nextInt(6);
            boardColours[i/14][i%14] = ranColour;
            board[i/14][i%14] = new JPanel();
            board[i/14][i%14].setBackground(colours[ranColour]);
            main1.add(board[i/14][i%14]);
         }
         else
         {
            int value = 0;
            switch(i)
            {
               case 0: value = ran.nextInt(3); break;
               case 1: value = 3; break;
               case 14: value = 4; break;
               case 15: value = 5; break;
            }
            boardColours[i/14][i%14] = value;
            board[i/14][i%14] = new JPanel();
            board[i/14][i%14].setBackground(colours[value]);
            main1.add(board[i/14][i%14]);
         }
      }
         
      pinkB = new JButton();
      pinkB.setActionCommand("pink");
      pinkB.addActionListener(this);
      pinkB.setIcon(new ImageIcon("pink.png"));
      orangeB = new JButton();
      orangeB.setActionCommand("orange");
      orangeB.addActionListener(this);
      orangeB.setIcon(new ImageIcon("orange.png"));
      yellowB = new JButton();
      yellowB.setActionCommand("yellow");
      yellowB.addActionListener(this);
      yellowB.setIcon(new ImageIcon("yellow.png"));
      greenB = new JButton();
      greenB.setActionCommand("green");
      greenB.addActionListener(this);
      greenB.setIcon(new ImageIcon("green.png"));
      blueB = new JButton();
      blueB.setActionCommand("blue");
      blueB.addActionListener(this);
      blueB.setIcon(new ImageIcon("blue.png"));
      purpleB = new JButton();
      purpleB.setActionCommand("purple");
      purpleB.addActionListener(this);
      purpleB.setIcon(new ImageIcon("purple.png"));
      
      name = new JLabel(username);
      name.setFont(new Font("Arial", Font.BOLD, 25));
      name.setBorder(BorderFactory.createEmptyBorder(0,0,0,40));
      moves = new JLabel("Moves: 0");
      moves.setFont(new Font("Arial", Font.BOLD, 25));
      
      main2.add(pinkB);
      main2.add(orangeB);
      main2.add(yellowB);
      main2.add(greenB);
      main2.add(blueB);
      main2.add(purpleB);
      
      main3.add(name);
      main3.add(moves);
      
      contentPane.add(main1);
      contentPane.add(main2);
      contentPane.add(main3);
      
      f.setContentPane(contentPane);
      f.pack();
      f.setVisible(true);
      
   }
   
   public void actionPerformed(ActionEvent event)
   
   {
   
      String eventName = event.getActionCommand();
      int choice = 0;
      
      if(eventName == "pink")
      {
         choice = 0;
      }
      if(eventName == "orange")
      {
         choice = 1;
      }
      if(eventName == "yellow")
      {
         choice = 2;
      }
      if(eventName == "green")
      {
         choice = 3;
      }
      if(eventName == "blue")
      {
         choice = 4;
      }
      if(eventName == "purple")
      {
         choice = 5;
      }
      
      if(!(eventName == "highscores"))
      {
         moveCount += 1;
         moves.setText("Moves: " + moveCount);
         
         for(int i = 0; i < 196; i++)
         {
            if(userArea[i/14][i%14] >= 1)
            {
               boardColours[i/14][i%14] = choice;
               board[i/14][i%14].setBackground(colours[choice]);
            }
         }
      
         updateArea(0,0,choice);
         userSpaces = 0;
      
         for(int i = 0; i < 196; i++)
         {
            if(userArea[i/14][i%14] >= 1)
            {
               userArea[i/14][i%14] = 1;
               userSpaces += 1;
            }
         }
      
         if(userSpaces == 196)
         {
            endGame();
         }
      }
      else
      {
         highScores();
      }
      
   }
   
   public void updateArea(int row, int col, int myColour)
   
   {
   
      if(!(row < 0 || row >= 14 || col < 0 || col >= 14 || boardColours[row][col] != myColour || userArea[row][col] == 2))
      {
         userArea[row][col] = 2;
         updateArea(row+1, col, myColour);
         updateArea(row-1, col, myColour);
         updateArea(row, col+1, myColour);
         updateArea(row, col-1, myColour);
      }

   }
      
   public void endGame()
   {
      name.setVisible(false);
      moves.setText("Congratulations! You won in " + moveCount + " moves!");
      pinkB.setVisible(false);
      orangeB.setVisible(false);
      yellowB.setVisible(false);
      greenB.setVisible(false);
      blueB.setVisible(false);
      purpleB.setVisible(false);
      
      JButton seeHigh = new JButton("See Highscores");
      seeHigh.setFont(new Font("Arial", Font.BOLD, 25));
      seeHigh.setActionCommand("highscores");
      seeHigh.addActionListener(this);
      main2.add(seeHigh);
      main2.setLayout(new FlowLayout());
      
   }
   
   public void highScores()
   
   {
   
      JFrame scoresFrame = new JFrame("HighScores");
      scoresFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      File highScores = new File("HighScores.txt");
      FileReader in;
      BufferedReader readFile;
      FileWriter out;
      BufferedWriter writeFile;
      String lineOfText;
          
      int places[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
      int ties = 0;
      
      String[] names = new String[16];
      int[] scores = new int[16];
      
      try
      {
         if(highScores.exists())//If the highscores file cannot be found, program will create a new list
         {
            in = new FileReader(highScores);//Opens reading streams
            readFile = new BufferedReader(in);
            for(int i = 0; i < 15; i++)//Reads a maximum of 15 names and scores
            {
               lineOfText = readFile.readLine();//reads a name
               names[i] = lineOfText;
               if(lineOfText != null)//If less than 15 scores are present, the program will need to stop reading at a point
               {
                  lineOfText = readFile.readLine();//reads a score
                  scores[i] = Integer.parseInt(lineOfText);
               }
               
            }
            readFile.close();//Closes reading streams
            in.close();
         }
         else
         {
            highScores.createNewFile();//creates a new highscore file
            for(int i = 0; i < 15; i++)
            {
               scores[i] = 1000;
               names[i] = "temporary";
            }
         }
      } 
      catch(FileNotFoundException e) {}//catches possible exceptions
      catch(IOException e) {}
      
      if(moveCount <= scores[14])
      {
         scores[15] = moveCount;//declares last element as user's score
         names[15] = username;
      }
      if(scores[14] == scores[15])//if scores 15 ties scores 14, scores 15 is placed in the highscores list
      {
         scores[14] = 1000;
      } 
       
      for(int a = 0; a < 15; a++)//Sorts highscores in descending order using bubble sort
      {
         for(int b = 0; b < 15; b++)
         {
            if(scores[b] > scores[b + 1])
            {
               int temp = scores[b];
               scores[b] = scores[b + 1];
               scores[b + 1] = temp;
               String tempS = names[b];//Since the names are associated with the scores, if a score switches its respective name must also switch
               names[b] = names[b + 1];
               names[b + 1] = tempS;
            }  
            else if(a == 14 && scores[b] == scores[b + 1])//last iteration of loop (everything is essentially in order)
            {
               ties += 1;
               places[b + 1] = places[b];//the next placement is the same as the previous in a tie
            }
         }
      }
      
      String[][] data = {{String.valueOf(places[0]),names[0], String.valueOf(scores[0])},{String.valueOf(places[1]),names[1], String.valueOf(scores[1])},{String.valueOf(places[2]),names[2], String.valueOf(scores[2])},{String.valueOf(places[3]),names[3], String.valueOf(scores[3])},{String.valueOf(places[4]),names[4], String.valueOf(scores[4])},
                        {String.valueOf(places[5]),names[5], String.valueOf(scores[5])},{String.valueOf(places[6]),names[6], String.valueOf(scores[6])},{String.valueOf(places[7]),names[7], String.valueOf(scores[7])},{String.valueOf(places[8]),names[8], String.valueOf(scores[8])},{String.valueOf(places[9]),names[9], String.valueOf(scores[9])},
                        {String.valueOf(places[10]),names[10], String.valueOf(scores[10])},{String.valueOf(places[11]),names[11], String.valueOf(scores[11])},{String.valueOf(places[12]),names[12], String.valueOf(scores[12])},{String.valueOf(places[13]),names[13], String.valueOf(scores[13])},{String.valueOf(places[14]),names[14], String.valueOf(scores[14])}};
      String[] column = {"Ranking", "Name", "Score"};
      JTable t = new JTable(data,column);
      t.setBounds(0,0,750,450);//Setting boundaries of the table
      t.setRowHeight(30);//Setting the row height
      t.setFont(new Font("Arial", Font.BOLD, 21));//Setting the table font
      t.setBackground(colours[5]);//Setting the background colour
      t.setForeground(Color.WHITE);
      JTableHeader h = t.getTableHeader();//Allows me to modify the header colour, size, font, etc.
      h.setPreferredSize(new Dimension(250, 30));
      h.setBackground(Color.BLACK);//Different colours to show that it is a header
      h.setForeground(Color.WHITE);
      h.setFont(new Font("Arial", Font.BOLD, 21));
      JScrollPane sp = new JScrollPane(t);
      scoresFrame.add(sp);
      scoresFrame.setSize(800,500);//Sets size of JFrame
      scoresFrame.setVisible(true);//Makes it visible   
         
      try
      {
         out = new FileWriter(highScores);
         writeFile = new BufferedWriter(out);
         for(int i = 0; i < 15; i++)//Writes top 15 names and scores in high scores file
         {
            if(scores[i] != 0)
            {
               writeFile.write(names[i]);
               writeFile.newLine();
               writeFile.write(String.valueOf(scores[i]));
               writeFile.newLine();
            }
         }
         writeFile.close();
         out.close();
      } catch(IOException e) {}
   } 

   private static void runGUI()
   {
      JFrame.setDefaultLookAndFeelDecorated(false);
      FloodIt game = new FloodIt();
   }
	
   public static void main(String[] args)
   {
      javax.swing.SwingUtilities.invokeLater(
         new Runnable()
         {
            public void run()
            {
               runGUI();
            }
         });
   }
}
