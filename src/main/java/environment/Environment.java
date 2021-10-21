package environment;

import lifeform.LifeForm;

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
  public static synchronized Environment getInstance(int rows, int cols) {
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

}
