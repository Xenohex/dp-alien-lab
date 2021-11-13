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

import environment.Cell;
import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.RecoveryRateException;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.Pistol;
import weapon.Weapon;

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
  
  public static void main(String[] args) {
    Environment e = Environment.getEnvironment(7, 10);
    Board b = new Board(e);
    Invoker gui = new Invoker(e, b);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    
    if (e.getSource() == eastButton) {
      // make getx and gety in board
      //this.e.getLifeForm(b.getx(), b.gety()).changeDirection("East");
      //TODO: get selected lifeform and make it face east
      
      System.out.println("east");
    } else if (e.getSource() == westButton) {
      //TODO: get selected lifeform and make it face west
      System.out.println("west");
    } else if (e.getSource() == northButton) {
      //TODO: get selected lifeform and make it face north
      System.out.println("north");
    } else if (e.getSource() == southButton) {
      //TODO: get selected lifeform and make it face south
      System.out.println("south");
    } else if (e.getSource() == moveButton) {
      //TODO: get selected lifeform and direction its facing and make it move that direction
      System.out.println("move");
    }
    
      else if (e.getSource() == attackButton) {
      //TODO: get selected lifeform and attack the closest lifeform it is looking at
      System.out.println("attack");
    } else if (e.getSource() == pickUpWeaponButton) {
      //TODO: get selected lifeform and pickup a dropped weapon in its cell
      System.out.println("pick up weapon");
    } else if (e.getSource() == dropWeaponButton) {
      //TODO: get selected lifeform and drop its weapon
      System.out.println("drop weapon");
    } else if (e.getSource() == reloadButton) {
      // TODO: get selected lifeform and reload its weapon
      System.out.println("reload");
    }
    
      else {
      System.out.println("unknown command");
    }
  }
  
}
