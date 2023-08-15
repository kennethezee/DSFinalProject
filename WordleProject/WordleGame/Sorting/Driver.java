import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Driver {
    public static void main(String[] args) {
        StringLinkedList mylist = new StringLinkedList();
        //StringFixedArrayList mylist = new StringFixedArrayList(20000);

        File inputFile = new File("C:/Users/kenne/OneDrive/Desktop/Wordle/wordle.txt");

        Scanner scan = null;
        try {
            scan = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        }

        int count = 0;
        while ( scan.hasNext() ) {
            String word = scan.next();
            mylist.add(word);
            count = count + 1;
        }

        System.out.println("Read in " + count + " words.");

        mylist.quickSort(); // For linked list
        //mylist.sort(); // For fixed array list

        for ( int i = 0 ; i < 10 ; i++ ) {
            System.out.println(mylist.get(i));
        }
    }
}
