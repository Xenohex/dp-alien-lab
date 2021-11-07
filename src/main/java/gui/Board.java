package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import environment.Cell;
import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.RecoveryRateException;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.Pistol;
import weapon.Weapon;

public class Board extends JFrame implements ActionListener {

  
  //Cell selectedCell;
  static Environment e;
  JButton textButton, imageButton;
  JLabel textLabel, imageLabel;
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
    this.e = e;
    int r = e.getNumRows();
    int c = e.getNumCols();
    setLayout(new BorderLayout());
    
    JButton[][] buttonArray = new JButton[r][c];
    JPanel centerPanel = new JPanel(new GridLayout(r,c));
    //JLabel[][] labelArray = new JLabel[r][c];
    for (int i=0;i<r;i++)
    {
     for (int j=0;j<c;j++)
     {
       
       buttonArray[i][j] = new JButton(createImage(e, i, j));
       centerPanel.add(buttonArray[i][j]);
     //labelArray[i][j] = new JLabel(" ("+i+":"+j+") ");
     //centerPanel.add(labelArray[i][j]);
     }
    }
    
    //textLabel = new JLabel("North");
    //add("North",textLabel);
     
    //imageButton = new JButton(createImage());
    //imageButton.addActionListener(this);
    //add("West",imageButton);
     
    //imageLabel = new JLabel(createImage());
    //add("South",imageLabel);
     
    //textButton = new JButton("A Button");
    //add("East",textButton);
    
    
    add("Center",centerPanel);
    
    pack();
    setVisible(true);
  }
  
  /**
   * @param e
   * @param row
   * @param col
   * @return
   */
  public ImageIcon createImage(Environment e, int row, int col) {
    
    BufferedImage exampleImage = new 
        BufferedImage(50,50,BufferedImage.TYPE_3BYTE_BGR);
    Graphics drawer = exampleImage.getGraphics();
    
    drawer.setColor(new Color(160,160,160));
    drawer.fillRect(0, 0, 50, 50);
    
    if(e.getLifeForm(row, col) != null && 
        e.getLifeForm(row, col).getClass() == Human.class) {
      drawHuman(drawer);
      if(e.getLifeForm(row, col).hasWeapon() == true) {
        drawWeapon(drawer);
      }
    } else if (e.getLifeForm(row, col) != null && 
        e.getLifeForm(row, col).getClass() == Alien.class) {
      drawAlien(drawer);
      if(e.getLifeForm(row, col).hasWeapon() == true) {
        drawWeapon(drawer);
      }
    }
    
    
    
    
   
    
    
    
    return new ImageIcon(exampleImage);
  }
  
  public void drawHuman(Graphics drawer) {
    
    drawer.setColor(new Color(230,180,140));
    drawer.fillPolygon(x, y, 3);
    
  }
  
  public void drawAlien(Graphics drawer) {
    drawer.setColor(new Color(0,200,0));
    drawer.fillPolygon(x,y,3);
  }
  
  public void drawWeapon(Graphics drawer) {
    drawer.setColor(new Color(255,0,0));
    int[] newx  = new int[4];
    int[] newy = new int[4];
    for(int i = 0; i < newx.length; i++) {
      newx[i] = x[i % 3];
      newy[i] = y[i % 3];
    }
    
    drawer.drawPolyline(newx, newy, 4);
      //drawer.drawImage(pisol.jpg, 45, 0, 5, 5, null);
    
  }
  
  public void drawCellWeapons(Graphics drawer) {
    
  }
  
  public void turnNorth() {
    x[0] = 25;
    x[1] = 15;
    x[2] = 35;
    y[0] = 15;
    y[1] = 35;
    y[2] = 35;
  }
  
  public void turnSouth() {
    x[0] = 25;
    x[1] = 15;
    x[2] = 35;
    y[0] = 35;
    y[1] = 15;
    y[2] = 15;
  }
  
  public void turnWest() {
    
  }
  
  public void turnEast() {
    
  }
  
  public void move() {
    
  }
  
  public void drop() {
    
  }
  
  public void acquire() {
    
  }
  
  
  
  public static void main(String args[]) throws EnvironmentException, RecoveryRateException {
    Environment e = Environment.getEnvironment(7, 10);
    LifeForm bob = new Human("bob", 10, 2);
    e.addLifeForm(bob, 2, 3);
    LifeForm al = new Alien("Al", 10);
    e.addLifeForm(al, 6, 3);
    Weapon w = new Pistol();
    al.pickUpWeapon(w);
    Board board = new Board(e);
    //Board board;
  }
 
  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == imageButton) {
      //imageButton.repaint();
    } 
    
  }
}
