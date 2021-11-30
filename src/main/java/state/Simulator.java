package state;

import environment.Environment;
import gameplay.SimpleTimer;
import gameplay.TimerObserver;
import lifeform.LifeForm;
import random.RandList;
import random.RandomHuman;

public class Simulator { //implements TimerObserver {

  Environment e;
  SimpleTimer timer;
  RandList<RandomHuman> humanlst;
  // rand human list/generator;
  // rand alien list/generator;
  // rand weapon list/generator;
  
  Simulator(Environment e, SimpleTimer t, int h, int a) {
    this.e = e;
    timer = t;
   // humanlst = new RandList(Random<>,h);   ***
    // HELP
    for (int i = 0; i < h; i++) {
      //choose();
      //choose();
     // e.addlifeform()
      //AIContext(randhuman, e)
      //timer.addTimeObserver(randHuman);
    //e.updateCell(randAlien.getRow(), randAlien.getCol());
      //e.addWeapon() --> different random cell
    }
    for (int i = 0; i < a; i++) {
      //aicontext(randAlien, e)
      //e.updateCell(randAlien.getRow(), randAlien.getCol());
    }
  }
  
  
  public static void main(String[] args) {
    
    
    
   
  }
  
  
  
  
  
  
  
  /**
  @Override
  public void updateTime(int time) {
    
    // TODO Auto-generated method stub
    
  }

  **/

}
