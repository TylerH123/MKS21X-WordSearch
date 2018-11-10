import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class driver4{
  public static void main(String[] args) throws FileNotFoundException{
    WordSearch p = new WordSearch(10,10, "words.txt");
    System.out.println(p.toString());
  }
}
