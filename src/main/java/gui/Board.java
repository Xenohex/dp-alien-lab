package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import environment.Cell;
import environment.Environment;
import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import exceptions.RecoveryRateException;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.*;


/**
 * @author chris
 * This is the GUI. The GUI will display the environment and will 
 * get updated by observing the environment. The GUI will then 
 * redraw the affected cells. There is also a legend displayed to aid the 
 * user in determining what is displayed in each cell.
 */
public class Board extends JFrame implements ActionListener {

  
  //Cell selectedCell;
  static Environment e;
  //JButton textButton, imageButton;
  JButton[][] buttonArray;
  
  JLabel[][] textLabel, imageLabel;
  JButton btn;
  JLabel blank ;
  JPanel centerPanel;
  Cell[][] selectedCell;
  int[] x  = new int[3];
  int[] y = new int[3];
  
  /**
   * This constructor takes in the environment variable 
   * and will also draw the gameBoard based on the Environment's
   * rows and columns.
   * @param e
   */
  public Board(Environment e) {
    
    x[0] = 25;
    x[1] = 15;
    x[2] = 35;
    y[0] = 15;
    y[1] = 35;
    y[2] = 35;
    
    
    Board.e = e;
    int r = e.getNumRows();
    int c = e.getNumCols();
    setLayout(new BorderLayout());
    
    
    buttonArray = new JButton[r][c];
    imageLabel = new JLabel[r][c];
    centerPanel = new JPanel(new GridLayout(r,c));
    for (int i=0;i<r;i++)
    {
     for (int j=0;j<c;j++)
     {
       
       imageLabel[i][j] = new JLabel(createCell(i, j));
       buttonArray[i][j] = new JButton(""+ i + "," + j);
       buttonArray[i][j].add(imageLabel[i][j]);
       buttonArray[i][j].addActionListener(this);
       
       centerPanel.add(buttonArray[i][j]);
     }
    }
    
    add("Center",centerPanel);
    
    pack();
    setVisible(true);
  }
  
  /**
   * 
   * @param row
   * @param col
   * @return the image that will go into each cell.
   */
  public ImageIcon createCell(int row, int col) {
    
    BufferedImage exampleImage = new 
        BufferedImage(50,50,BufferedImage.TYPE_3BYTE_BGR);
    Graphics drawer = exampleImage.getGraphics();
    
    drawer.setColor(new Color(160,160,160));
    drawer.fillRect(0, 0, 50, 50);
    
    determineLifeForm(drawer, row, col);
    drawCellWeapons(drawer, row, col);

    return new ImageIcon(exampleImage);
  }
  
  public ImageIcon clearCell() {
    BufferedImage exampleImage = new 
        BufferedImage(50,50,BufferedImage.TYPE_3BYTE_BGR);
    Graphics drawer = exampleImage.getGraphics();
    
    drawer.setColor(new Color(160,160,160));
    drawer.fillRect(0, 0, 50, 50);
    
    

    return new ImageIcon(exampleImage);
  }
  
  
  /**
   * @param drawer
   * @param row
   * @param col
   * Draws a human.
   */
  public void drawHuman(Graphics drawer, int row, int col) { 
    drawer.setColor(new Color(230,180,140));
    direct(row, col);
    drawer.fillPolygon(x, y, 3);
  }
  
  /**
   * @param drawer
   * @param row
   * @param col
   * Draws an alien.
   */
  public void drawAlien(Graphics drawer, int row, int col) {
    drawer.setColor(new Color(0,200,0));
    direct(row, col);
    drawer.fillPolygon(x,y,3);
  }
  
  /**
   * @param drawer
   * @param row
   * @param col
   * Draws a Weapon if the lifeForm has it. Draws attachments on 
   * the weapon when applicable.
   */
  public void drawWeapon(Graphics drawer, int row, int col) {
    if (e.getLifeForm(row, col).hasWeapon() == true) {
      drawer.setColor(new Color(255,0,0));
      int[] newx  = new int[4];
      int[] newy = new int[4];
      for(int i = 0; i < newx.length; i++) {
        newx[i] = x[i % 3];
        newy[i] = y[i % 3];
      }
      drawer.drawPolyline(newx, newy, 4);
      int attachments = e.getLifeForm(row, col).getWeapon().getNumAttachments();
      switch(attachments) {
      case 2:
        drawer.setColor(new Color(95,0,135));
        drawer.fillRect(0, 0, 10, 20);
        break;
      case 1:
        drawer.setColor(new Color(150,0,175));
        drawer.fillRect(0, 0, 10, 10);
        break;
      case 0:
        break;
      }
    }
  }
    
    
  
  
  /**
   * @param drawer
   * @param row
   * @param col
   * Draws weapons in the cell for display.
   */
  public void drawCellWeapons(Graphics drawer, int row, int col) {
    if (e.getWeapons(row, col).length != 0) {
      int weapons = e.getWeapons(row, col).length;
    switch(weapons) {
    case 2:
      drawer.setColor(new Color(255,0,0));
      drawer.fillRect(40, 0, 10, 10);
      drawer.fillRect(40, 40, 10, 10);
      break;
    case 1:
      drawer.setColor(new Color(255,0,0));
      drawer.fillRect(40, 40, 10, 10);
      break;
    case 0:
      break;
    }
    
    }
    
  }
  
  /**
   * Sets coordinates so a lifeform can be drawn pointing North.
   */
  public void turnNorth() {
    x[0] = 25;
    x[1] = 15;
    x[2] = 35;
    y[0] = 15;
    y[1] = 35;
    y[2] = 35;
  }
  
  /**
   * Sets coordinates so a lifeform can be drawn pointing South.
   */
  public void turnSouth() {
    x[0] = 25;
    x[1] = 15;
    x[2] = 35;
    y[0] = 35;
    y[1] = 15;
    y[2] = 15;
  }
  
  /**
   * Sets coordinates so a lifeform can be drawn pointing West.
   */
  public void turnWest() {
    x[0] = 15;
    x[1] = 35;
    x[2] = 35;
    y[0] = 25;
    y[1] = 15;
    y[2] = 35;
  }
  
  /**
   * Sets coordinates so a lifeform can be drawn pointing East.
   */
  public void turnEast() {
    x[0] = 35;
    x[1] = 15;
    x[2] = 15;
    y[0] = 25;
    y[1] = 15;
    y[2] = 35;
  }
  
  
  /**
   * @param row
   * @param col
   * will display all information regarding the lifeform 
   * (its weapon, name, health, ammo, etc...)
   *  and the available weapons in the cell. 
   */
  public void highlighted() {
    
    //returns attachment info, weapon, lifeform, health
    //returns weapons in cell
    //ammo left
  }
  
  /**
   * @param row
   * @param col
   * This will set the direction based on the LifeForm's 
   * current direction so they can be drawn properly.
   */
  public void direct(int row, int col) {
    if (e.getLifeForm(row, col).getCurrentDirection() == "North") {
      turnNorth();
    } else if (e.getLifeForm(row, col).getCurrentDirection() == "South") {
      turnSouth();
    } else if (e.getLifeForm(row, col).getCurrentDirection() == "West") {
      turnWest();
    } else if (e.getLifeForm(row, col).getCurrentDirection() == "East") {
      turnEast();
    }
  }
  
  /**
   * @param drawer
   * @param row
   * @param col
   * This function determines what lifeform should be drawn and then
   * calls for a the appropriate function as well as for drawWeapon
   */
  public void determineLifeForm(Graphics drawer, int row, int col) {
    if(e.getLifeForm(row, col) != null && 
        e.getLifeForm(row, col).getClass() == Human.class) {
      drawHuman(drawer, row, col);
      drawWeapon(drawer, row, col);
      
    } else if (e.getLifeForm(row, col) != null && 
        e.getLifeForm(row, col).getClass() == Alien.class) {
      drawAlien(drawer, row, col);
      drawWeapon(drawer, row, col);
    }
  }
      
    

  
  
  
  public static void main(String args[]) throws EnvironmentException, RecoveryRateException, AttachmentException {
    Environment e = Environment.getEnvironment(7, 10);
    LifeForm bob = new Human("bob", 10, 2);
    e.addLifeForm(bob, 2, 3);
    LifeForm al = new Alien("Al", 10);
    e.addLifeForm(al, 6, 3);
    al.changeDirection("West");
    bob.changeDirection("East");
    Weapon w = new Scope(new Stabilizer(new Pistol()));
    Weapon w1 = new Pistol();
    al.pickUpWeapon(w);
    e.addWeapon(w, 3, 5);
    e.addWeapon(w, 3, 3);
    e.addWeapon(w1, 3, 3);
    Board board = new Board(e);
    //Board board;
  }
 
  @Override
  public void actionPerformed(ActionEvent event) {
    if(btn != null) {
      String[] rowCol = btn.getText().split(",");
      //buttonArray[][4].setBackground(new Color(255,0,0));
      //buttonArray[btn.getX()][btn.getY()].setBackground(new JButton().getBackground());
     /** for(int i = 0; i < e.getNumRows(); i++) {
        for (int j = 0; j < e.getNumCols(); j++) {
          if (buttonArray[i][j] == btn) {
            int row = i;
            int col = j;
          }
        }
      } **/
      //String a = btn.getText();
      
      int x = Integer.parseInt(rowCol[0]);
      int y = Integer.parseInt(rowCol[1]);
      selectedCell = new Cell[x][y];
      
      /** This proves that I can remove and add an imageLabel
      buttonArray[x][y].remove(imageLabel[x][y]);
      buttonArray[x][y].add(imageLabel[x][y]);
      **/
      
      blank = new JLabel(clearCell());
      //buttonArray[x][y].add(blank);
      //buttonArray[x][y].add(imageLabel[x][y]);
      //add("West",btn);
      //buttonArray[x][y].remove(imageLabel[x][y]);
      //buttonArray[x][y].add(blank);
      buttonArray[x][y].setBackground(new JButton().getBackground()); 
      
      
    }
    
    btn = (JButton) event.getSource();
    btn.setBackground(new Color(0,0,255));
    
  }
}
