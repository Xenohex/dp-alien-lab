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
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import environment.Cell;
import environment.Environment;
import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import exceptions.RecoveryRateException;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.Weapon;


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
  private String[] legendList = {"             Human","              Alien",
      "Human with weapon","   1 Weapon in cell",
      "   2 weapons in cell", "LifeForm pointed East","     Highlighted cell",
      "<html>Human with <br>weapon  with <br>2 attachments</html>",
      "<html>Alien with <br>weapon with <br>1 attachment</html>"};
  private boolean[][] fun = { {false, false, true, false, false, false, false, false, false}, 
      {false, false, false, true, false, false, false, false, false},
      {false, false, true, false, true, false, false, false, false},
      {false, false, false, false, false, false, false, true, false},
      {false, false, false, false, false, false, false, false, true},
      //{false, false, false, false, false, false, false, false},
      {false, true, false, true, false, false, false, false, false},
      {true, false, false, false, false, false, false, false, false},
      {false, false, true, false, false, false, true, false, false},
      {false, false, false, true, false, true, false, false, false},
  };
  private JButton[][] buttonArray;
  private JLabel[][] imageLabel;
  private JLabel[][] legend = new JLabel[2][9];
  private JButton btn;
  private JLabel initial;
  private JPanel centerPanel;
  private JPanel bottomPanel;
  private Cell selectedCell;
  //Cell[][] selectedCell;
  private int selectedRow;
  private int selectedCol;
  private int[] xCoordinates  = new int[3];
  private int[] yCoordinates = new int[3];
  
  /**
   * This constructor takes in the environment variable 
   * and will also draw the gameBoard based on the Environment's
   * rows and columns.
   * @param e
   */
  public Board(Environment e) {
   
    //instance of Environment initialized
    Board.e = e;
    
    //rows and columns of environment allocated
    int r = e.getNumRows();
    int c = e.getNumCols();
    buttonArray = new JButton[r][c];
    imageLabel = new JLabel[r][c];
    
    //sets up GUI aas well as the centerPanel to hold the board
    setLayout(new BorderLayout());
    centerPanel = new JPanel(new GridLayout(r,c));
    
    //Initializes the board with appropriate entities
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        imageLabel[i][j] = new JLabel(createCell(i, j));
        buttonArray[i][j] = new JButton();
       
        //sets each button with row,col as its name
        // this allows for easier row and column retrieval
        // once a button is pressed.
        buttonArray[i][j].setName("" + i + "," + j);
        buttonArray[i][j].add(imageLabel[i][j]);
        buttonArray[i][j].addActionListener(this);
        centerPanel.add(buttonArray[i][j]);
      }
    }

    //sets text on West which will be filled with Cell info when 
    // a cell is clicked on. Adds the Board to the GUI
    initial = new JLabel();
    initial.setText("<html>------------------------------------------------------</html>");
    add("Center",centerPanel);
    add("West", initial);
    
    //will create and set the legend
    bottomPanel = new JPanel(new GridLayout(2,9));
    for (int i = 0; i < 9; i++) {
      legend[1][i] = new JLabel();
      legend[1][i].setText(legendList[i]);
      bottomPanel.add(legend[1][i]);
    }
    for (int i = 0; i < 9; i++) {
      legend[0][i] = new JLabel(createLegendCell(fun[i]));
      bottomPanel.add(legend[0][i]);
    }
    //legend[0][0] = ;
    add("South",bottomPanel);
    pack();
    setVisible(true);
  }
  
  /**
   * @param fun
   * @return
   */
  private ImageIcon createLegendCell(boolean[] fun) {
    BufferedImage exampleImage = new 
        BufferedImage(50,50,BufferedImage.TYPE_3BYTE_BGR);
    Graphics drawer = exampleImage.getGraphics();
    
    if (fun[0]) {
      drawer.setColor(new Color(0,0,255));
    } else {
      drawer.setColor(new Color(160,160,160));
    }
    drawer.fillRect(0, 0, 50, 50);
    turnNorth();
    if (fun[1]) {
      turnEast();
    }
    if (fun[2]) {
      drawer.setColor(new Color(230,180,140));
      drawer.fillPolygon(xCoordinates, yCoordinates, 3);
    }
    if (fun[3]) {
      drawer.setColor(new Color(0,200,0));
      drawer.fillPolygon(xCoordinates,yCoordinates,3);
    }
    if (fun[4]) {
      drawer.setColor(new Color(255,0,0));
      int[] newx  = new int[4];
      int[] newy = new int[4];
      for (int i = 0; i < newx.length; i++) {
        newx[i] = xCoordinates[i % 3];
        newy[i] = yCoordinates[i % 3];
      }
      drawer.drawPolyline(newx, newy, 4);
    }
    if (fun[5]) {
      drawer.setColor(new Color(150,0,175));
      drawer.fillRect(0, 0, 10, 10);
    }
    if (fun[6]) {
      drawer.setColor(new Color(95,0,135));
      drawer.fillRect(0, 0, 10, 20);
    }
    if (fun[7]) {
      drawer.setColor(new Color(255,0,0));
      drawer.fillRect(40, 40, 10, 10);
    }
    if (fun[8]) {
      drawer.setColor(new Color(255,0,0));
      drawer.fillRect(40, 0, 10, 10);
      drawer.fillRect(40, 40, 10, 10);
    }
    
    
    return new ImageIcon(exampleImage);
  }
  
  /**
   * 
   * @param row
   * @param col
   * @return the image that will go into each cell.
   */
  private ImageIcon createCell(int row, int col) {
    
    BufferedImage exampleImage = new 
        BufferedImage(50,50,BufferedImage.TYPE_3BYTE_BGR);
    Graphics drawer = exampleImage.getGraphics();
    
    drawer.setColor(new Color(160,160,160));
    drawer.fillRect(0, 0, 50, 50);
    
    /**   if (e.getLifeForm(row, col) != null) {
      determineLifeForm(drawer, row, col);
      } could be used to make lifeform not draw if dead
    **/   
    determineLifeForm(drawer, row, col);
    drawCellWeapons(drawer, row, col);

    return new ImageIcon(exampleImage);
  }
  
  /**
   * @return an empty cell
   */
  private ImageIcon clearCell() {
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
   * This function determines what lifeform should be drawn and then
   * calls for a the appropriate function as well as for drawWeapon
   */
  private void determineLifeForm(Graphics drawer, int row, int col) {
    if (e.getLifeForm(row, col) != null 
        && e.getLifeForm(row, col).getClass() == Human.class) {
      drawHuman(drawer, row, col);
      drawWeapon(drawer, row, col);
    } else if (e.getLifeForm(row, col) != null 
        && e.getLifeForm(row, col).getClass() == Alien.class) {
      drawAlien(drawer, row, col);
      drawWeapon(drawer, row, col);
    }
  }
  
  /**
   * @param drawer
   * @param row
   * @param col
   * Draws a human.
   */
  private void drawHuman(Graphics drawer, int row, int col) { 
    drawer.setColor(new Color(230,180,140));
    direct(row, col);
    drawer.fillPolygon(xCoordinates, yCoordinates, 3);
  }
  
  /**
   * @param drawer
   * @param row
   * @param col
   * Draws an alien.
   */
  private void drawAlien(Graphics drawer, int row, int col) {
    drawer.setColor(new Color(0,200,0));
    direct(row, col);
    drawer.fillPolygon(xCoordinates,yCoordinates,3);
  }
  
  /**
   * @param drawer
   * @param row
   * @param col
   * Draws a Weapon if the lifeForm has it. Draws attachments on 
   * the weapon when applicable.
   */
  private void drawWeapon(Graphics drawer, int row, int col) {
    if (e.getLifeForm(row, col).hasWeapon() == true) {
      drawer.setColor(new Color(255,0,0));
      int[] newx  = new int[4];
      int[] newy = new int[4];
      for (int i = 0; i < newx.length; i++) {
        newx[i] = xCoordinates[i % 3];
        newy[i] = yCoordinates[i % 3];
      }
      drawer.drawPolyline(newx, newy, 4);
      
      int attachments = e.getLifeForm(row, col).getWeapon().getNumAttachments();
      switch (attachments) {
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
        default:
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
  private void drawCellWeapons(Graphics drawer, int row, int col) {
    if (e.getWeapons(row, col).length != 0) {
      int weapons = e.getWeapons(row, col).length;
      switch (weapons) {
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
        default:
          break;
      }
    
    }
    
  }
  
  /**
   * Sets coordinates so a lifeform can be drawn pointing North.
   */
  private void turnNorth() {
    xCoordinates[0] = 25;
    xCoordinates[1] = 15;
    xCoordinates[2] = 35;
    yCoordinates[0] = 15;
    yCoordinates[1] = 35;
    yCoordinates[2] = 35;
  }
  
  /**
   * Sets coordinates so a lifeform can be drawn pointing South.
   */
  private void turnSouth() {
    xCoordinates[0] = 25;
    xCoordinates[1] = 15;
    xCoordinates[2] = 35;
    yCoordinates[0] = 35;
    yCoordinates[1] = 15;
    yCoordinates[2] = 15;
  }
  
  /**
   * Sets coordinates so a lifeform can be drawn pointing West.
   */
  private void turnWest() {
    xCoordinates[0] = 15;
    xCoordinates[1] = 35;
    xCoordinates[2] = 35;
    yCoordinates[0] = 25;
    yCoordinates[1] = 15;
    yCoordinates[2] = 35;
  }
  
  /**
   * Sets coordinates so a lifeform can be drawn pointing East.
   */
  private void turnEast() {
    xCoordinates[0] = 35;
    xCoordinates[1] = 15;
    xCoordinates[2] = 15;
    yCoordinates[0] = 25;
    yCoordinates[1] = 15;
    yCoordinates[2] = 35;
  }
  
  
  /**
   * @param row
   * @param col
   * This will set the direction based on the LifeForm's 
   * current direction so they can be drawn properly.
   */
  private void direct(int row, int col) {
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
   * @param row
   * @param col
   * will be called when environment notifies the observer(board)
   * Will redraw the previously selected Cell and the new coordinates
   * of the newly affected Cell.
   */
  public void update(int row, int col, Environment e) {
    Board.e = e;
    
    
    buttonArray[row][col].remove(imageLabel[row][col]);
    imageLabel[row][col] = new JLabel(createCell(row, col));
    buttonArray[row][col].add(imageLabel[row][col]);
    
    
    buttonArray[selectedRow][selectedCol].remove(imageLabel[selectedRow][selectedCol]);
    imageLabel[selectedRow][selectedCol] = new JLabel(createCell(selectedRow, selectedCol));
    buttonArray[selectedRow][selectedCol].add(imageLabel[selectedRow][selectedCol]);
    
    highlightCell(row, col);
    setVisible(true);
    
  }
  
  private void highlightCell(int row, int col) {
    if (btn != null) {
      //sets background of previously clicked button to default background
      btn.setBackground(new JButton().getBackground()); 
      
    }
    
    btn = buttonArray[row][col];
    btn.setBackground(new Color(0, 0, 255));
    selectedCell = e.getCell(row, col);
    selectedRow = row;
    selectedCol = col;
    
    String lfInfo = "<html> ------------------------------------------------------ " + "<br>";
    String cellInfo = "";
    
    //Creates lifeForm info if there is a lifeForm
    if (selectedCell.getLifeForm() != null) {
      lfInfo += "Name: " + selectedCell.getName() + "<br>" 
        + "Health Points: " + selectedCell.getLifeForm().getCurrentLifePoints() + "<br>"; 
      
      //This will give weapon information if lifeForm is holding a weapon
      if (selectedCell.getLifeForm().getWeapon() != null) {
        lfInfo += selectedCell.getLifeForm().getWeapon().toString() + "<br>" 
            + "Ammo:  " + selectedCell.getLifeForm().getWeapon().getCurrentAmmo() 
            + "/" + selectedCell.getLifeForm().getWeapon().getMaxAmmo() + "<br>";
      }
    }
    
    //This will give the names of the weapons in the cell
    if (selectedCell.getWeaponsCount() != 0) {
      for (int i = 0; i < selectedCell.getWeaponsCount(); i++) {
        Weapon[] arr = e.getWeapons(selectedRow, selectedCol);
        cellInfo += "Weapon " + (i + 1) + ": " + arr[i].toString() + "<br>";
      }
    }
    
    
    //ends the html block which was added because \n did not work
    cellInfo += "</html>";
    initial.setText(lfInfo.concat(cellInfo));
    
  }
  
  /**
   * @return
   * this is a getter so Environment can know which cell is selected
   */
  public Cell getSelectedCell() {
    return selectedCell;
  }
    
  
  /** For testing purposes only
   * @param args
   * @throws EnvironmentException
   * @throws RecoveryRateException
   * @throws AttachmentException
   * For testing purposes.
   */
  /**
  public static void main(String args[]) throws EnvironmentException, 
    RecoveryRateException, AttachmentException {
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
  }
  **/
 
  /**
   * will display all information regarding the lifeform 
   * (its weapon, name, health, ammo, etc...)
   *  and the available weapons in the cell. 
   *  Will also highlight the selected cell
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    String[] row = ((JButton) event.getSource()).getName().split(",");
    selectedRow = Integer.parseInt(row[0]);
    selectedCol = Integer.parseInt(row[1]);
    highlightCell(selectedRow,selectedCol);
  }
}
