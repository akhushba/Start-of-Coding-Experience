
//DO IMPORTS HERE
import java.util.Scanner;

class Main {

  public static void main(String[] args) {

    // initializing
    int centsOnly;
    double dollarAmount;
    Scanner input = new Scanner(System.in);
    System.out.println("Enter a dollar amount: ");

    try {
      dollarAmount = input.nextDouble();

      // rounding to nearest nickel
      double roundedNearestNickel = Math.round(dollarAmount * 20.0) / 20.0;
      // dollars to cents
      centsOnly = (int) Math.round(roundedNearestNickel * 100);

      // calculating number of toonies and the remaining cents
      int toonieAmount = centsOnly / 200;
      int afterToonie = centsOnly % 200;

      // calculating number of loonies and the remaining cents
      int loonieAmount = afterToonie / 100;
      int afterLoonie = afterToonie % 100;

      // calculating number of quarters and the remaining cents
      int quarterAmount = afterLoonie / 25;
      int afterQuarter = afterLoonie % 25;

      // calculating number of dimes and the remaining cents
      int dimeAmount = afterQuarter / 10;
      int afterDime = afterQuarter % 10;

      // calculating number of nickels
      int nickelAmount = afterDime / 5;

      // output
      System.out.println("toonies:" + toonieAmount + "; loonies:" + loonieAmount + "; quarters:" + quarterAmount
          + "; dimes:" + dimeAmount + "; nickels:" + nickelAmount);

    // catching exceptions
    } catch (Exception e) {
      System.out.println("INVALID");
    }

    input.close();
  }
}