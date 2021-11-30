package state;

import environment.Environment;
import gameplay.SimpleTimer;
import gameplay.TimerObserver;

public class Simulator { //implements TimerObserver {

  Environment e;
  SimpleTimer timer;
  //rand human list/generator;
  // rand alien list/generator;
  //rand weapon list/generator;
  
  Simulator(Environment e, SimpleTimer t, int h, int a) {
    this.e = e;
    timer = t;
    for (int i = 0; i < h; i++) {
     // e.addlifeform()
      //AIContext(randhuman, e)
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
