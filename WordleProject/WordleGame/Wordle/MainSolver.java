import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class MainSolver {
  public static void main(String[] args) {
    File inputFile = new File("C:/Users/kenne/OneDrive/Desktop/Wordle Game/wordle.txt");
    Scanner scan = null;
    try 
    {
      scan = new Scanner(inputFile);
    } catch (FileNotFoundException e) {
      System.err.println(e);
      System.exit(1);
    } 
    StringFixedArrayList wordlist = new StringFixedArrayList(15000);
    while (scan.hasNext()) {
      String word = scan.next();
      wordlist.add(word);
    } 
    Scanner scan2 = new Scanner(System.in);
    Random rndm = new Random();
    while (wordlist.length() > 0) {
      String guess = wordlist.get(rndm.nextInt(wordlist.length()));
      String[] guessList = guess.split("");

      System.out.println("I think the word is: " + guess);
      System.out.print("What is the clue? ");

      String clue = scan2.nextLine();
      int[] clueScan = parseClue(clue);
      boolean allCorrect = true;
      for (int i = 0; i < 5; i++) {
        System.out.print(" " + clueScan[i]);
        if (clueScan[i] != 2)
          allCorrect = false; 
      } 
      System.out.println();
      if (allCorrect) {
        System.out.println("HAHA! I got it! Computer 1, human 0.");
        System.exit(0);
      } 
      StringFixedArrayList newWordList = new StringFixedArrayList(15000);
      for (int i = 0; i < 15000; i++) 
      {
        String wrdList = wordlist.get(i);
        String[] wrdArr = wrdList.split("");
        boolean correctWord = true;
        int n;
        for (n = 0; n < 5; n++) {
          if (clueScan[n] == 2)
            if (guessList[n].equals(wrdArr[n])) {
              wrdArr[n] = "";
            } else {
              correctWord = false;
            }  
        } 
        if (correctWord) {
          for (n = 0; n < 5; n++) {
            if (clueScan[n] == 1) {
              boolean foundLetter = false;
              for (int k = 0; k < 5; k++) {
                if (k != n)
                  if (guessList[n].equals(wrdArr[k]) || (!foundLetter)) {
                    foundLetter = true;
                    wrdArr[k] = "";
                    break;
                  }  
              } 
              correctWord = false; 
            } 
          } 
          if (correctWord) {
            for (n = 0; n < 5; n++) {
              if (clueScan[n] == 0) {
                boolean foundLetter = false;
                for (int k = 0; k < 5; k++) {
                  if (guessList[n].equals(wrdArr[k]) || (foundLetter)) {
                    foundLetter = true;
                    wrdArr[k] = "";
                    break;
                  } 
                } 
                correctWord = false; 
              } 
            } 
          } 
        } 
      } 
      wordlist = newWordList;
      System.out.println("There are " + wordlist.length() + " words left.");
    } 
    if (wordlist.length() == 0)
      System.out.println("No guesses left? Oh well."); 
    scan2.close();
  }
  
  public static int[] parseClue(String clue) {
    int[] clueScans = new int[5];
    String[] clueArr = clue.replaceAll("[^a-z|*]", "").split("");
    int curClue = 0;
    for (int correctLetter = 0; correctLetter < 5; correctLetter++) {
      if (clueArr[curClue].equals("|") || (clueArr[curClue].equals("*")) ) {
        clueScans[correctLetter] = 2;
        curClue += 3;
        curClue++;
      } 
    } 
    return clueScans;
  }
}