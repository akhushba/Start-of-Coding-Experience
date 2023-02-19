import java.util.Arrays;

class Main {
  public static void main(String[] args) {

    //ten random integers
    int numberOne = (int)Math.floor(Math.random()*(100-0+1)+0);
    int numberTwo = (int)Math.floor(Math.random()*(100-0+1)+0);
    int numberThree = (int)Math.floor(Math.random()*(100-0+1)+0);
    int numberFour = (int)Math.floor(Math.random()*(100-0+1)+0);
    int numberFive = (int)Math.floor(Math.random()*(100-0+1)+0);
    int numberSix = (int)Math.floor(Math.random()*(100-0+1)+0);
    int numberSeven = (int)Math.floor(Math.random()*(100-0+1)+0);
    int numberEight = (int)Math.floor(Math.random()*(100-0+1)+0);
    int numberNine = (int)Math.floor(Math.random()*(100-0+1)+0);
    int numberTen = (int)Math.floor(Math.random()*(100-0+1)+0);
    int[] numbers = {numberOne, numberTwo, numberThree, numberFour, numberFive, numberSix, numberSeven, numberEight, numberNine, numberTen};
    
    //printing out every element at an even index
    int[] evenIndex = new int[5];
    for(int i = 0; i < 10; i+=2){
      evenIndex[i/2] = numbers[i];
    }
    
    //printing out the elements in reverse order
    int[] reverseOrder = new int[10];
    for(int i = 0; i < 10; i++){
      reverseOrder[i] = numbers[9-i];
    }
    
    //printing out the first and last element
    int[] firstLast = new int[2];
    firstLast[0] = numbers[0];
    firstLast[1] = numbers[9];

    //printing even elements
    int[] evenElement;
    int HowMany = 0;
    
    //finding out how many elements are even
    for(int i = 0; i < 10; i++){
      if (numbers[i]%2 == 0){
        HowMany++;
      }
    }
    evenElement = new int[HowMany];
    
    //adding the new elements to the even
    for(int i = 0; i < HowMany; i++){
      for(int j = 0; j < 10; j++){
      if (numbers[j]%2 == 0){
        evenElement[i] = numbers[j];
        numbers[j] = 1;
        break;
        }
      }
    }

    //printing out all the different outputs
    System.out.println("Every element at an even index: " + Arrays.toString(evenIndex));
    System.out.println("Every even element: " + Arrays.toString(evenElement));
    System.out.println("Reverse order: " + Arrays.toString(reverseOrder));
    System.out.println("First and last element: " + Arrays.toString(firstLast));
  }
}