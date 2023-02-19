import java.util.Scanner;
import java.util.Arrays;

class Main {

  // opening the ComboLock
  public static void main(String[] args) {

    // define variables
    int howManyTurns = 0;
    Scanner input = new Scanner(System.in);
    boolean lockClosed = true;

    // set up String password for lock to generate numbers
    System.out.println("Enter new password: ");
    String newPassword = input.nextLine();
    ComboLock lockPassword = new ComboLock(newPassword);

    // process of opening the lock
    while (lockClosed) {

      System.out.println("\nPress 1 to turn the lock to the right.");
      System.out.println("Press 2 to turn the lock to the left.");
      System.out.println("Press 3 to open the lock.");
      System.out.println("Press 4 to retrieve the combination.");

      int choice = input.nextInt();

      // turn the lock to the right
      if (choice == 1) {
        // spin the lock towards the first number
        if (howManyTurns == 0) {
          System.out.println("\nTurn the lock right to the first number: ");
          int numberOne = input.nextInt();
          lockPassword.turnRight(numberOne);
          howManyTurns++;
          // spin the lock towards the third number
        } else if (howManyTurns == 2) {
          System.out.println("\nTurn the lock right to the third number: ");
          int numberThree = input.nextInt();
          lockPassword.turnRight(numberThree);
          howManyTurns = 0;
        }

        // turn the lock to the left
      } else if (choice == 2) {
        // spin the lock towards the second number
        if (howManyTurns == 1) {
          System.out.println("\nTurn the lock left to the second number: ");
          int numberTwo = input.nextInt();
          lockPassword.turnLeft(numberTwo);
          howManyTurns++;
        }

        // check to see if the lock can be opened, resets progress if wrong
      } else if (choice == 3) {
        if (lockPassword.isOpen()) {
          System.out.println("\nThe lock was opened");
          lockClosed = false;
        } else if (!lockPassword.isOpen()) {
          System.out.println("\nThe lock remains locked");
          howManyTurns = 0;
        }

        // retrieve the combination by entering in the String password
      } else if (choice == 4) {

        Scanner anotherInput = new Scanner(System.in);
        System.out.println("\nEnter password: ");
        String guess = anotherInput.nextLine();

        // printing combo out in an array style
        int[] comboArray = lockPassword.forgotNumbers(guess);
        System.out.println("\nCombo: " + Arrays.toString(comboArray));

      }
    }
  }
}