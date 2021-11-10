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

  public Invoker() {
    
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
    
    JPanel actionPanel = new JPanel(new GridLayout(1,4));
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
    Invoker gui = new Invoker();
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == eastButton) {
      System.out.println("east pressed");
    } else if (e.getSource() == westButton) {
      System.out.println("west pressed");
    } else if (e.getSource() == northButton) {
      System.out.println("north pressed");
    } else if (e.getSource() == southButton) {
      System.out.println("south pressed");
    } else if (e.getSource() == moveButton) {
      System.out.println("move pressed");
    }
    
    else if (e.getSource() == attackButton) {
      System.out.println("attack");
    } else if (e.getSource() == pickUpWeaponButton) {
      System.out.println("pick up weapon");
    } else if (e.getSource() == dropWeaponButton) {
      System.out.println("drop weapon");
    } else if (e.getSource() == reloadButton) {
      System.out.println("reload");
    }
  }
  
}
