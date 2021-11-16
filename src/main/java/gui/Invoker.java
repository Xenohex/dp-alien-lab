package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import environment.Environment;
import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import exceptions.RecoveryRateException;
import lifeform.LifeForm;
import lifeform.Alien;
import lifeform.Human;
import weapon.Pistol;
import weapon.ChainGun;
import weapon.PlasmaCannon;

/**
 * 
 * @author Austin Pliska
 * This class has been designated to initalize the entire program, and the class
 * itself creates the remote that allows the user to command the lifeform's in 
 * the board.
 *
 */
public class Invoker extends JFrame implements ActionListener {
  
  JButton eastButton;
  JButton westButton;
  JButton northButton;
  JButton southButton;
  JButton moveButton;
  
  JButton attackButton;
  JButton dropWeaponButton;
  JButton acquireWeaponButton;
  JButton reloadButton;
  
  Environment env;
  Board board;
  
  /**
   * 
   * @param e  singleton environment
   * @param b  board
   * Constructor that takes in the environment and game board to initialize them and
   * control what happens in them using the concrete commands implemented with the game.
   */
  public Invoker(Environment e, Board b) {
    
    this.env = e;
    this.board = b;
    
    setLayout(new BorderLayout());
    
    JPanel movementPanel = new JPanel(new BorderLayout());
    
    westButton = new JButton("west");
    westButton.addActionListener(this);
    movementPanel.add("West", westButton);
    
    eastButton = new JButton("east");
    eastButton.addActionListener(this);
    movementPanel.add("East", eastButton);
    
    northButton = new JButton("north");
    northButton.addActionListener(this);
    movementPanel.add("North", northButton);
    
    southButton = new JButton("south");
    southButton.addActionListener(this);
    movementPanel.add("South", southButton);
    
    moveButton = new JButton("move");
    moveButton.addActionListener(this);
    movementPanel.add("Center", moveButton);
    
    JPanel actionPanel = new JPanel(new GridLayout(4,1));
    JButton[][] buttonArray = new JButton[4][1];
    
    attackButton = new JButton("attack");
    attackButton.addActionListener(this);
    buttonArray[0][0] = attackButton;
    
    acquireWeaponButton = new JButton("pick-up weapon");
    acquireWeaponButton.addActionListener(this);
    buttonArray[1][0] = acquireWeaponButton;
    
    dropWeaponButton = new JButton("drop weapon");
    dropWeaponButton.addActionListener(this);
    buttonArray[2][0] = dropWeaponButton;
    
    reloadButton = new JButton("reload");
    reloadButton.addActionListener(this);
    buttonArray[3][0] = reloadButton;
    
    for (int i = 0; i < 4; i++) {
      actionPanel.add(buttonArray[i][0]);
    }
    
    add("West", movementPanel);
    add("East", actionPanel);
    
    pack();
    setVisible(true);
    
  }
  
  /**
   * 
   * @param args
   * @throws EnvironmentException
   * @throws RecoveryRateException
   * @throws AttachmentException
   * 
   * This is the main method that initializes the entire program and spawns in 
   * various lifeforms as well as the environment, game board, and remote controller.
   */
  public static void main(String[] args) 
      throws EnvironmentException, RecoveryRateException, AttachmentException {
    Environment e = Environment.getEnvironment(7, 10);
    
    LifeForm bob = new Alien("bob", 100);
    e.addLifeForm(bob, 0, 0);
    var w1 = new Pistol();
    //bob.pickUpWeapon(w1);
    e.addWeapon(w1, 4, 6);
    
    LifeForm joe = new Human("joe", 100, 0, 1);
    e.addLifeForm(joe, 0, 1);
    var w2 = new ChainGun();
    joe.pickUpWeapon(w2);
    
    
    LifeForm bran = new Human("bran", 100, 0, 1);
    e.addLifeForm(bran, 0, 2);
    var w3 = new PlasmaCannon();
    bran.pickUpWeapon(w3);
    
    Board b = new Board(e);
    Invoker gui = new Invoker(e, b);
  }

  /**
   * Decides what command to execute based off of which button was pressed.
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    
    if (board.getSelectedCell() == null) {
      System.out.println("Error: no cell selected");
    } else if (board.getSelectedCell().getLifeForm() == null) {
      System.out.println("Error: no lifeform selected");
    } else {
    
      LifeForm lifeform = board.getSelectedCell().getLifeForm();
      var remote = new Remote();
      
      if (event.getSource() == eastButton) {  // East
        var east = new FaceEastCommand(lifeform);
        remote.setCommand(east);
        remote.buttonPressed();
        
      } else if (event.getSource() == westButton) { // West
        var west = new FaceWestCommand(lifeform);
        remote.setCommand(west);
        remote.buttonPressed();
        
      } else if (event.getSource() == northButton) {  // North
        var north = new FaceNorthCommand(lifeform);
        remote.setCommand(north);
        remote.buttonPressed();
        
      } else if (event.getSource() == southButton) {  // South
        var south = new FaceSouthCommand(lifeform);
        remote.setCommand(south);
        remote.buttonPressed();
        
      } else if (event.getSource() == moveButton) { // Move
        var move = new MoveCommand(lifeform, env, board);
        remote.setCommand(move);
        remote.buttonPressed();
        
      } else if (event.getSource() == attackButton) { // Attack
        var attack = new AttackCommand(lifeform, env);
        remote.setCommand(attack);
        remote.buttonPressed();
        
      } else if (event.getSource() == acquireWeaponButton) { // Pick Up Weapon
        var acquire = new AcquireCommand(lifeform, env);
        remote.setCommand(acquire);
        remote.buttonPressed();
        
      } else if (event.getSource() == dropWeaponButton) { // Drop Weapon
        var drop = new DropCommand(lifeform, env);
        remote.setCommand(drop);
        remote.buttonPressed();
        
      } else if (event.getSource() == reloadButton) { // Reload
        var reload = new ReloadCommand(lifeform);
        remote.setCommand(reload);
        remote.buttonPressed();
        
      } else {
      System.out.println("unknown command");
    }
      
      board.update(lifeform.getRow(), lifeform.getCol(),env);
    }
  }
  
}
