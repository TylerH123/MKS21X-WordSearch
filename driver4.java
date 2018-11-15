import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class driver4{
  public static void main(String[] args){
    try{
      if (args.length > 2){
        if (args.length == 3){
          System.out.println("Row Length: " + args[0] + ", Column Length: " + args[1] + "\n" + "File: " + args[2]);
          WordSearch p = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);
          System.out.println(p.toString());
        }
        if (args.length == 4){
          WordSearch p = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), false);
        }
        //if (args.length == 5){
        //  WordSearch p = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(argss[3]), args[4]);
        //}
      }
      else{
        System.out.println("Please input arguments: rows, cols, filename");
      }
    }
    catch(IllegalArgumentException e){
      System.out.println("Row or Column cannot be 0");
    }
  }
}
