import java.util.Scanner;

class Main {
  
  public static void main(String[] args) {
    
    Scanner input = new Scanner(System.in);
    System.out.println("Please enter a String input: ");
    
    String examineWord = input.nextLine();
    int length = examineWord.length() - 1;
    int howManyVowels = 0;
    char letter;
    
    char[] wordArray = examineWord.toCharArray();
    
    for (int i = 0; i<= length; i++){
      letter = wordArray[i];
      
      if (letter == 'a' || letter == 'e'|| letter == 'i'|| letter == 'o'|| letter == 'u'){
        wordArray[i] = '_';
       howManyVowels++;
      }
    }

    //outputing the word with the vowels replaced
    String changedWord = new String(wordArray);
    System.out.println(changedWord);

    //outputting the number of vowels in the word
    if (howManyVowels == 1) {
      System.out.println("There is " + howManyVowels + " vowel.");
      
    } else {
      System.out.println("There are " + howManyVowels + " vowels.");

    }
    
    input.close();
  }
}
