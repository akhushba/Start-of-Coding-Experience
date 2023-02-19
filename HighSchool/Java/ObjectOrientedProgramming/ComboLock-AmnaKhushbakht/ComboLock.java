public class ComboLock {

  // private variables
  private int secretOne;
  private int secretTwo;
  private int secretThree;
  private String password;
  private int turns = 0;
  private int[] combo = new int[3];

  // first constructor
  public ComboLock(String password) {
    this.password = password;

    this.secretOne = (int) Math.floor(Math.random() * 60 + 1);
    this.secretTwo = (int) Math.floor(Math.random() * 60 + 1);
    this.secretThree = (int) Math.floor(Math.random() * 60 + 1);

    while (secretTwo == secretOne) {
      this.secretTwo = (int) Math.floor(Math.random() * 60 + 1);
    }

    while (secretThree == secretTwo || secretThree == secretOne) {
      this.secretThree = (int) Math.floor(Math.random() * 60 + 1);
    }
  }

  // second constructor
  public ComboLock(int secretOne, int secretTwo, int secretThree) {
    this.password = null;

    this.secretOne = secretOne;
    this.secretTwo = secretTwo;
    this.secretThree = secretThree;
  }

  // third constructor
  public ComboLock(int secretOne, int secretTwo, int secretThree, String password) {
    this.password = password;
    this.secretOne = secretOne;
    this.secretTwo = secretTwo;
    this.secretThree = secretThree;
  }

  // first method
  public void turnRight(int number) {
    if (number == this.secretOne) {
      this.turns++;

    } else if (turns == 2 && number == this.secretThree) {
      this.turns++;
    }
  }

  // second method
  public void turnLeft(int number) {
    if (number == this.secretTwo) {
      this.turns++;
    }
  }

  // third method
  public boolean isOpen() {
    if (this.turns == 3) {
      this.turns = 0;
      return true;

    } else {
      return false;
    }
  }

  // fourth method
  public int[] forgotNumbers(String passwordGuess) {
    if (this.password.equals(passwordGuess)) {
      this.combo[0] = this.secretOne;
      this.combo[1] = this.secretTwo;
      this.combo[2] = this.secretThree;
      return this.combo;

    } else {
      return null;
    }
  }
}