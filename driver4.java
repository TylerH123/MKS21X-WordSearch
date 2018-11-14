import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class driver4{
  public static void main(String[] args) throws FileNotFoundException{
    try{
      if (args.length > 0){
          WordSearch p = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), "words.text");
        }
        if (args.length == 3){
          WordSearch p = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);
        }
      WordSearch p = new WordSearch(10,10, "words.txt");
      System.out.println(p.toString());
    }
    catch(FileNotFoundException e){
      System.out.println("Input a valid file name");
    }
  }
}
