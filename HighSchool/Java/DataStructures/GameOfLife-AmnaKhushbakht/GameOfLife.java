// two/three cells around an alive cell = stagnant cell
// one cell or fewer around it = death
// four cells or more around it = death
// three cells around it = new life

class GameOfLife{

  boolean[][] game;
  int howManyCells;

  // constructor
  public GameOfLife(boolean[][] board) {
    game = board;
  }

  // method for calculating the next generation
  public void calculateNextGeneration() {
    //used to compare cells in the previous generation
    boolean[][] intergeneration = new boolean[game.length][game[0].length];

    // scanning every cell
    for (int i = 0; i < game.length; i ++){
      for(int j = 0; j < game[0].length; j++){
        howManyCells = 0;
        
        // checking the cells around a certain point
        for (int k = -1; k < 2; k++){
          for (int l = -1; l < 2; l++){

            // not counting itself, no solipsism allowed here, checking bounds
            if ((k == 0 && l == 0) || (i + k) >= game.length || (i + k) < 0 || (j + l) >= game[0].length || (j + l) < 0) {
              continue;
              
              // counting how many live cells are around a certain point
            } else if (game[i + k][j + l] == true){
              howManyCells++;
              
            }
          }
        }

        // two cells = an alive cells stays alive, three = life no matter what
        if (howManyCells == 2 && game[i][j] == true || howManyCells == 3){
          intergeneration[i][j] = true;

        // everything else equals death
        } else {
          intergeneration[i][j] = false;
        }
      }
    }
    
  // making the board show the current generation
  game = intergeneration;
  }

  // print method
  public void print() {
    // looping through the cells
    for (int i = 0; i < game.length; i ++){
      for(int j = 0; j < game[0].length; j++){
        
        // printing out the game
        if (game[i][j] == true){
          System.out.print('*');
        } else {
          System.out.print('.');
        }
      }
      System.out.print("\n");
    }
  }

  // sets a particular cell to alive or not
  public void setAlive(int x, int y, boolean isAlive) {
    
    // getting rid of the null by doing something random
    if (game == null){
      isAlive = false;

    // setting the right cell to alive
    } else if(x < game.length && x >= 0 && y < game[0].length && y >= 0) {
      game[x][y] = isAlive;
    }
  }

  // returns if a particular cell is alive or not
  public boolean isAlive(int x, int y) {

    // if the game is null/out of bounds then return false
    if (game == null || x >= game.length  || y >= game[0].length || x < 0 || y < 0) {
      return false;
      
    // is cell is alive, return true
    } else if (game[x][y] == true){
      return true;
      
    // anything else happens, return false
    } else {
      return false;
    }
  }
}