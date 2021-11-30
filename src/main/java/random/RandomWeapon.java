package random;

import java.util.List;

import exceptions.AttachmentException;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.PowerBooster;
import weapon.Scope;
import weapon.Stabilizer;
import weapon.Weapon;

public class RandomWeapon implements Random<Weapon> {
  
  List<Weapon> choices;
  public RandomWeapon() throws AttachmentException {
    choices = List.of(new Pistol(), new ChainGun(), new PlasmaCannon(),
      new PowerBooster(new Pistol()), new Scope(new Pistol()), new Stabilizer(new Pistol()),
      
      new PowerBooster(new PowerBooster(new Pistol())), new PowerBooster(new Scope(new Pistol())), 
      new PowerBooster(new Stabilizer(new Pistol())), 
      new Scope(new Scope(new Pistol())), new Scope(new Stabilizer(new Pistol())),
      new Stabilizer(new Stabilizer(new Pistol())),
      
      new PowerBooster(new PowerBooster(new ChainGun())), new PowerBooster(new Scope(new ChainGun())), 
      new PowerBooster(new Stabilizer(new ChainGun())), 
      new Scope(new Scope(new ChainGun())), new Scope(new Stabilizer(new ChainGun())),
      new Stabilizer(new Stabilizer(new ChainGun())),
      
      new PowerBooster(new PowerBooster(new PlasmaCannon())), new PowerBooster(new Scope(new PlasmaCannon())), 
      new PowerBooster(new Stabilizer(new PlasmaCannon())), 
      new Scope(new Scope(new PlasmaCannon())), new Scope(new Stabilizer(new PlasmaCannon())),
      new Stabilizer(new Stabilizer(new PlasmaCannon()))  );
  }
  
  public Weapon choose() {
    return new FromList<Weapon>(choices).choose();
  }
  
}
