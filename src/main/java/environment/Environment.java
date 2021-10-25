package environment;

import exceptions.EnvironmentException;
import lifeform.LifeForm;
import weapon.Weapon;

/**
 * Keeps track of a small world of cells in which LifeForms can exist. These
 * cells are arranged in a simple grid.
 *
 */
public class Environment {
  
  private volatile static Environment theEnvironment;
  
  
  /**
   * A 2-Dimensional array of the individual Cells.
   */
  Cell[][] cells;

  /**
   * @param rows size of the rows in the 2d array
   * @param cols size of the cols in the 2d array
   */
  private Environment(int rows, int cols) {
    cells = new Cell[rows][cols];
    for (int i = 0; i < cells.length; i++) {
      for (int j = 0; j < cells[0].length; j++) {
        cells[i][j] = new Cell();
      }
    }
  }
  
  /**
   * @param rows
   * @param cols
   * @return a new instance of environment if there is none.
   * It permits only one instance to be created. If an instance 
   * of environment already exists, this returns that instance.
   */
  public static synchronized Environment getEnvironment(int rows, int cols) {
    if(theEnvironment == null) {
      theEnvironment = new Environment(rows, cols);
    }
    return theEnvironment;
  }

  /**
   * Adds a LifeForm to the Cell cells[row][col]
   * 
   * @param lf  the LifeForm that will be added into the 2d array
   * @param row row position of the 2d array
   * @param col col position of the 2d array
   * @return true if success and false otherwise
   */
  public boolean addLifeForm(LifeForm lf, int row, int col) {
    if (row < 0 || col < 0) {
      return false;
    }
    if (row < cells.length && col < cells[0].length && (cells[row][col].addLifeForm(lf) == true)) {
      cells[row][col].addLifeForm(lf);
      return true;
    }
    return false;
  }

  /**
   * Removes the LifeForm at cells[row][col]
   * 
   * @param row position of 2d array
   * @param col position of 2d array
   */
  public void removeLifeForm(int row, int col) {
    cells[row][col].removeLifeForm();
  }

  /**
   * @param row position of 2d array
   * @param col position of 2d array
   * @return returns the LifeForm at cells[row][col]
   */
  public LifeForm getLifeForm(int row, int col) {
    return cells[row][col].getLifeForm();
  }

  /**
   * @param weapon
   * @param row
   * @param col
   * @return true if the weapon was aded, false if the weapon
   * could not be added. There are several reasons for failure:
   * There are no spots availabe. the weapon instance is already in the cell.
   * OR the cell was off the board.
   */
  public boolean addWeapon(Weapon weapon, int row, int col) {
    return cells[row][col].addWeapon(weapon);
  }
  
  /**
   * @param weapon
   * @param row
   * @param col
   * @return the weapon removed from the cell.
   * Returns null of the cell was off the grid.
   */
  public Weapon removeWeapon(Weapon weapon, int row, int col) {
    return cells[row][col].removeWeapon(weapon);
  }
  
  public Weapon[] getWeapons(int row, int col) {
    Weapon[] arr = new Weapon[2];
    Weapon w = cells[row][col].getWeapon1();
    Weapon w2 = cells[row][col].getWeapon2();
    
    if (cells[row][col].getWeaponsCount() == 2) {
      arr = new Weapon[2];
      arr[0] = w;
      arr[1] = w2;
    } else if (cells[row][col].getWeaponsCount() == 1) {
      arr = new Weapon[1];
      if (w == null) {
        arr[0] = w2;
      } else
        arr[0] = w;
    }
    return arr;
  }

  /**
   * Reset the Singleton environment for tests
   */
  public void clearBoard() {
    theEnvironment = null;
  }
  
  /**
   * @return the number of rows in the environment
   */
  public int getNumRows() {
    return cells.length;
  }
  
  /**
   * @return the number of columns in the environment
   */
  public int getNumCols() {
    return cells[0].length;
  }
  
  /**
   * Calculate the distance between two lifeForms using their coordinates
   * @param row1
   * @param col1
   * @param row2
   * @param col2
   * @return the distance
   * @throws EnvironmentException
   */
  public double getDistance(int row1, int col1, int row2, int col2) throws EnvironmentException {
    int displace = 0;
    if (row1 < 0 || row1 > getNumRows() || row2 < 0 || row2 > getNumRows() || 
        col1 < 0 || col1 > getNumCols() || col2 < 0 || col2 > getNumCols()) {
      throw new EnvironmentException("Coordinates are invalid!");
    }
    if (row1 == row2) {
      displace = col2 - col1;
    } else if (col1 == col2) {
      displace = row1 - row2;
    } else {
      
    }
    return displace * 5;
  }
  
}
