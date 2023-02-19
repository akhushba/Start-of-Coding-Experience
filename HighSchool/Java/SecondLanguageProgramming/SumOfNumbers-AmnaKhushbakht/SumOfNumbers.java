public class SumOfNumbers {

  // Create method getSum here!
  public String getSum(int start, int end) {

    // variables
    int runningCount;
    int totalSum = 0;
    runningCount = start;
    String output = String.valueOf(runningCount);

    // return empty String if value is greater
    if (runningCount > end) {
      return "";
    }

    // compiling the series of numbers
    while (runningCount <= end) {
      totalSum += runningCount;
      runningCount++;

      // stop outputting numbers after reaching the end
      if (runningCount > end) {break;}

      // adding to the series output
      output += " + " + String.valueOf(runningCount);
    }

    // final output values
    output += " = " + String.valueOf(totalSum);
    return output;
  }
}