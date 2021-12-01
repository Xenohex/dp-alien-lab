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

public class Simulator { 

  Environment env;
  SimpleTimer timer;
  RandList<RandomHuman> humanlst;
  List<AiContext> conListHuman;
  List<AiContext> conListAlien;
  
  Simulator(Environment e, SimpleTimer t, int h, int a) throws EnvironmentException, 
      AttachmentException {
    env = e;
    timer = t;
    Cell c;
    Human bob;
    Alien al;
    Weapon w;
    boolean b;
    AiContext con;
    Random ran = new Random();
    conListHuman = new ArrayList<>();
    conListAlien = new ArrayList<>();
    ActionState as;
    for (int i = 0; i < h; i++) {
      
      c = e.getRandomCell();
      bob = new RandomHuman().choose();
      b = false;
      
      
      do {
        b = e.addLifeForm(bob,ran.nextInt(e.getNumRows()), ran.nextInt(e.getNumCols()));
      } while (!b);
      conListHuman.add(new AiContext(bob, e));
      t.addTimeObserver(conListHuman.get(i));
      
      w = new RandomWeapon().choose();
      c = e.getRandomCell();
      b = false;
      
      do {
        b = e.addWeapon(w,ran.nextInt(e.getNumRows()), ran.nextInt(e.getNumCols()));
      } while (!b);
      t.addTimeObserver(w);
    }
    
    
    for (int i = 0; i < a; i++) {
      c = e.getRandomCell();
      al = new RandomAlien().choose();
      b = false;
      
      do {
        b = e.addLifeForm(al,ran.nextInt(e.getNumRows()), ran.nextInt(e.getNumCols()));
      } while (!b);
   
      conListAlien.add(new AiContext(al, e));
      
      t.addTimeObserver(conListAlien.get(i));
      
      w = new RandomWeapon().choose();
      c = e.getRandomCell();
      b = false;
      
      do {
        b = e.addWeapon(w,ran.nextInt(e.getNumRows()), ran.nextInt(e.getNumCols()));
      } while (!b);
      t.addTimeObserver(w);
    }
  }
  
  
  /**
   * @param args
   * tests the simulator class
   */
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
  

}
