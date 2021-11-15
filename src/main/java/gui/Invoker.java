package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import environment.*;
import exceptions.*;
import lifeform.*;
import weapon.*;


public class Invoker extends JFrame implements ActionListener {
  
  JButton eastButton, westButton, northButton, southButton, moveButton;
  JButton attackButton, dropWeaponButton, pickUpWeaponButton, reloadButton;
  Environment e;
  Board b;
  
  public Invoker(Environment e, Board b) {
    
    this.e = e;
    this.b = b;
    
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
    
    pickUpWeaponButton = new JButton("pick-up weapon");
    pickUpWeaponButton.addActionListener(this);
    buttonArray[1][0] = pickUpWeaponButton;
    
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
  
  public static void main(String[] args) 
      throws EnvironmentException, RecoveryRateException, AttachmentException {
    Environment e = Environment.getEnvironment(7, 10);
    Board b = new Board(e);
    Invoker gui = new Invoker(e, b);
    
    LifeForm bob = new Human("bob", 100, 0, 1);
    e.addLifeForm(bob, 0, 0);
    var w1 = new Pistol();
    bob.pickUpWeapon(w1);
    
    LifeForm joe = new Human("joe", 100, 0, 1);
    e.addLifeForm(joe, 0, 1);
    var w2 = new ChainGun();
    joe.pickUpWeapon(w2);
  }


  @Override
  public void actionPerformed(ActionEvent event) {
    
    if (b.getSelectedCell() == null) {
      System.out.println("Error: no cell selected");
    } else {
    
      LifeForm lifeform = b.getSelectedCell().getLifeForm();
      var remote = new Remote();
      
      if (event.getSource() == eastButton) {
        // make getx and gety in board
        //this.e.getLifeForm(b.getx(), b.gety()).changeDirection("East");
        //TODO: get selected lifeform and make it face east
        var east = new faceEastCommand(lifeform);
        remote.setCommand(east);
        remote.buttonPressed();
      } else if (event.getSource() == westButton) {
        //TODO: get selected lifeform and make it face west
        var west = new faceWestCommand(lifeform);
        remote.setCommand(west);
        remote.buttonPressed();
      } else if (event.getSource() == northButton) {
        //TODO: get selected lifeform and make it face north
        var north = new faceNorthCommand(lifeform);
        remote.setCommand(north);
        remote.buttonPressed();
      } else if (event.getSource() == southButton) {
        //TODO: get selected lifeform and make it face south
        var south = new faceSouthCommand(lifeform);
        remote.setCommand(south);
        remote.buttonPressed();
      } else if (event.getSource() == moveButton) {
        //TODO: get selected lifeform and direction its facing and make it move that direction
        var move = new moveCommand(lifeform, e, b);
        remote.setCommand(move);
        remote.buttonPressed();
      }
      
        else if (event.getSource() == attackButton) {
        //TODO: get selected lifeform and attack the closest lifeform it is looking at
        var attack = new attackCommand(lifeform, e);
        remote.setCommand(attack);
        remote.buttonPressed();
      } else if (event.getSource() == pickUpWeaponButton) {
        //TODO: get selected lifeform and pickup a dropped weapon in its cell
        var acquire = new acquireCommand(lifeform, e);
        remote.setCommand(acquire);
        remote.buttonPressed();
      } else if (event.getSource() == dropWeaponButton) {
        //TODO: get selected lifeform and drop its weapon
        var drop = new dropCommand(lifeform, e);
        remote.setCommand(drop);
        remote.buttonPressed();
      } else if (event.getSource() == reloadButton) {
        // TODO: get selected lifeform and reload its weapon
        var reload = new reloadCommand(lifeform);
        remote.setCommand(reload);
        remote.buttonPressed();
      }
      
        else {
        System.out.println("unknown command");
      }
    }
  }
  
}
