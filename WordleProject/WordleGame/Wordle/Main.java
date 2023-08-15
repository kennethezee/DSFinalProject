import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Main {
  static StringFixedArrayList wordlist = new StringFixedArrayList(15000);
  
  static Scanner mylist = new Scanner(System.in);
  
  public static void main(String[] args) 
  {
    File inputFile = new File("C:/Users/kenne/OneDrive/Desktop/wordle/wordle.txt");
    Scanner scan = null;
    try 
    {
      scan = new Scanner(inputFile);
    } catch (FileNotFoundException e) 
      {
      System.err.println(e);
      System.exit(1);
      } 
    int count = 0;
    while (scan.hasNext()) 
    {
      String word = scan.next();
      wordlist.add(word);
      count++;
    } 
    //Initaial Game prompt
    System.out.println("Would you like me to choose a word?");
    System.out.print("Enter (1) for automatic pick, or (2) manual pick: ");
    
    int auto = mylist.nextInt();
    String manual = "";
    if (auto == 1) 
    {
      Random rn = new Random();
      auto = rn.nextInt(wordlist.length());
      manual = wordlist.get(auto);
      System.out.println("Alright I've chosen, you're not gonna get it...");
    } else 
      {
        manual = getWord("Enter a secret word");
      for (int i = 0; i < 100; i++)
        System.out.println(); 
      System.out.println("HAHA! The word has been chosen, Best of Luck!");
      } 
    int guessCount = 1;
    //Guesser 
    while (guessCount < 7) 
    {
      String guess = getWord("Enter guess #" + guessCount);
      String clue = clues(manual, guess);
      System.out.println(clue);
      boolean done = true;
      for (int i = 0; i < 5; i++) 
      {
        if (clue.charAt(4 * i) != '|')
          done = false; 
      } 
      if (done)
        break; 
      guessCount++;
    } 
    if (guessCount < 7) 
    {
      System.out.println("Oh wow, you did it? Good Job.");
    } else 
      {
      System.out.println("HAHAHAHA!!! Wrong again, you didn't guess the word!!");
      System.out.println("The correct word was '" + manual + "'.");
      System.out.println("Better luck next time!");
      } 
  }
  
  public static String clues(String manual, String auto) 
  {
    String[] clue = { "", "", "", "", "" };
    String[] manArr = manual.split("");
    String[] auroArr = auto.split("");
    String unused = "";
    int i;
    for (i = 0; i < 5; i++) {
      if (manArr[i].equals(auroArr[i])) 
      {
        clue[i] = "|" + manArr[i] + "|";
      } 
      else 
        {
          unused = String.valueOf(unused) + manArr[i];
        } 
    } 
    for (i = 0; i < 5; i++) 
    {
      if (clue[i].isEmpty())
        if (unused.indexOf(auroArr[i]) != -1) 
        {
          clue[i] = "*" + auroArr[i] + "*";
        } else 
          {
          clue[i] = " " + auroArr[i] + " ";
          }  
    } 
    String retval = clue[0];
    for (int j = 1; j < 5; j++)
      retval = String.valueOf(retval) + " " + clue[j]; 
    return retval;
  }
  
  //promts for guesses
  public static String getWord(String prompt) 
  {
    String guess = "";
    boolean done = false;
    while (!done) 
    {
      System.out.print(String.valueOf(prompt) + ": ");
      guess = mylist.next();
      if (guess.length() < 5) 
      {
        System.out.println("Really? That word is too short. Try again.");
        continue;
      } 
      if (guess.length() > 5) 
      {
        System.out.println("Come on really? That word is too long. Please try again.");
        continue;
      } 
      if (!wordlist.contains(guess)) 
      {
        System.out.println("That word isn't even on the list man. Try again.");
        continue;
      } 
      done = true;
    } 
    return guess;
  }
}