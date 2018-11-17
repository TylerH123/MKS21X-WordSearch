import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class driver4{
  public static void main(String[] args){
    try{
      if (args.length > 2){
        if (args.length == 3){
          System.out.println("usage: java WordSearch [rows cols filename [randomSeed [answers]]]");
          System.out.println("Row Length: " + args[0] + ", Column Length: " + args[1] + "\nFile: " + args[2]);
          WordSearch p = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);
          System.out.println(p.toString());
        }
        if (args.length == 4){
          System.out.println("usage: java WordSearch [rows cols filename [randomSeed [answers]]]");
          System.out.println("Row Length: " + args[0] + ", Column Length: " + args[1] + "\nFile: " + args[2] + "\nNo key");
          WordSearch p = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), false);
          System.out.println(p.toString());
        }
        if (args.length == 5){
          if (args[4].equals("key")){
            System.out.println("usage: java WordSearch [rows cols filename [randomSeed [answers]]]");
            System.out.println("Row Length: " + args[0] + ", Column Length: " + args[1] + "\nFile: " + args[2] + "\nAnswer key: " + args[4]);
            WordSearch p = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), true);
            System.out.println(p.toString());
          }
          else{
            System.out.println("usage: java WordSearch [rows cols filename [randomSeed [answers]]]");
            System.out.println("Row Length: " + args[0] + ", Column Length: " + args[1] + "\nFile: " + args[2] + "\nNo Key");
            WordSearch p = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), false);
          }
        }
      }
      else{
        System.out.println("usage: java WordSearch [rows cols filename [randomSeed [answers]]]");
      }
    }
    catch (NumberFormatException e){
      System.out.println("Invalid number inputs. \nPlease follow this format: rows(int), cols(int), filename(str), seed(int), answer(boolean)");
    }
    catch(IllegalArgumentException e){
      System.out.println("Row or Column cannot be 0");
    }
    System.out.println("Changing the seed, dimensions of the puzzle, or quantity of words, even by a single number/word, will cause a completely different puzzle to occur. ");
  }
}
