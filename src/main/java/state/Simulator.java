package state;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import environment.Cell;
import environment.Environment;
import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import gameplay.SimpleTimer;
import gameplay.TimerObserver;
import gui.Board;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import random.RandList;
import random.RandomAlien;
import random.RandomHuman;
import random.RandomWeapon;
import weapon.Weapon;

public class Simulator { //implements TimerObserver {

  Environment e;
  SimpleTimer timer;
  RandList<RandomHuman> humanlst;
  List<AIContext> conListHuman;
  List<AIContext> conListAlien;
  // rand human list/generator;
  // rand alien list/generator;
  // rand weapon list/generator;
  
  Simulator(Environment e, SimpleTimer t, int h, int a) throws EnvironmentException, AttachmentException {
    this.e = e;
    timer = t;
    Cell c;
    Human bob;
    Alien al;
    Weapon w;
    boolean b;
    AIContext con;
    Random ran = new Random();
    conListHuman = new ArrayList<>();
    conListAlien = new ArrayList<>();
    ActionState as;
    for (int i = 0; i < h; i++) {
      
      c = e.getRandomCell();
      bob = new RandomHuman().choose();
      b = false;
      
      
      do {
        //c = e.getRandomCell();
        b = e.addLifeForm(bob,ran.nextInt(e.getNumRows()), ran.nextInt(e.getNumCols()) );
        //b = c.addLifeForm(bob);
        
      } while (!b);
      //bob.setLocation(c.getLifeForm().getRow(), c.getLifeForm().getCol());
      conListHuman.add(new AIContext(bob, e));
      //con = new AIContext(bob, e);
      t.addTimeObserver(conListHuman.get(i));
      
      w = new RandomWeapon().choose();
      c = e.getRandomCell();
      b = false;
      
      do {
        //c = e.getRandomCell();
        b = e.addWeapon(w,ran.nextInt(e.getNumRows()), ran.nextInt(e.getNumCols()) );
        //b = c.addWeapon(w);
      } while (!b);
      t.addTimeObserver(w);
    }
    
    
    for (int i = 0; i < a; i++) {
      c = e.getRandomCell();
      al = new RandomAlien().choose();
      b = false;
      
      do {
        b = e.addLifeForm(al,ran.nextInt(e.getNumRows()), ran.nextInt(e.getNumCols()) );
      } while (!b);
      //al.setLocation(c.getLifeForm().getRow(), c.getLifeForm().getCol());
      //con = new AIContext(al, e);
      conListAlien.add(new AIContext(al, e));
      //con = new AIContext(bob, e);
      t.addTimeObserver(conListAlien.get(i));
      //t.addTimeObserver(con);
      //ActionState as = new ActionState(con);
      
      w = new RandomWeapon().choose();
      c = e.getRandomCell();
      b = false;
      
      do {
        b = e.addWeapon(w,ran.nextInt(e.getNumRows()), ran.nextInt(e.getNumCols()) );
      } while(!b);
      t.addTimeObserver(w);
    }
  }
  
  
  public static void main(String[] args) {
    
    Environment e = Environment.getEnvironment(3,3);
    SimpleTimer t = new SimpleTimer(1000);
    try {
      Simulator s = new Simulator(e, t, 1, 2);
    } catch (EnvironmentException e1) {
      e1.printStackTrace();
    } catch (AttachmentException e1) {
      e1.printStackTrace();
    }
    Board b = new Board(e);
    t.run();
    
    
    
   
  }
  
  
  
  
  
  
  
  /**
  @Override
  public void updateTime(int time) {
    
    // TODO Auto-generated method stub
    
  }

  **/

}
