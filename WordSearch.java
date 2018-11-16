import java.util.*;
import java.io.*;

public class WordSearch{
  private char[][]data;

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
     //the random seed used to produce this WordSearch
  private int seed;

      //a random Object to unify your random calls
  private Random randgen;

      //all words from a text file get added to wordsToAdd, indicating that they have not yet been added
  private ArrayList<String>wordsToAdd = new ArrayList<>();

      //all words that were successfully added get moved into wordsAdded.
  private ArrayList<String>wordsAdded = new ArrayList<>();

  public WordSearch(int rows, int cols, String filename){
    if(cols < 0 || rows < 0) throw new IllegalArgumentException();
    data = new char[rows][cols];
    seed = (int)(Math.random() * 100000);
    randgen = new Random(seed);
    try{
      getWords(filename);
    }
    catch(FileNotFoundException e){
      System.out.println("File not found");
    }
    this.clear();
    addAllWords();
    fillInWordSearch();
  }
  public WordSearch(int rows, int cols, String filename, int randSeed, boolean ans){
    if(cols < 0 || rows < 0) throw new IllegalArgumentException();
    data = new char[rows][cols];
    seed = randSeed;
    randgen = new Random(seed);
    try{
      getWords(filename);
    }
    catch(FileNotFoundException e){
      System.out.println("File not found");
    }
    this.clear();
    addAllWords();
    if (!ans){
      fillInWordSearch();
    }
    if (ans){
      answerKey();
    }
  }
  private void getWords(String filename) throws FileNotFoundException{
    File f = new File(filename);
    Scanner file = new Scanner(f);
    while (file.hasNext()){
      wordsToAdd.add(file.next());
    }
  }
    /**Set all values in the WordSearch to underscores'_'*/
  private void clear(){
    for(int i = 0; i < data.length; i++){
      for(int j = 0; j<data[i].length; j++){
        data[i][j] = '_';
      }
    }
  }
  private boolean addWord(int r, int c, String word, int rowIncrement, int colIncrement){
    if (rowIncrement == 0 && colIncrement == 0) return false;
    try{
      int r2 = r;
      int c2 = c;
      for (int i = 0; i < word.length(); i++){
        if (data[r2][c2] != '_' && data[r2][c2] != word.charAt(i)){
          return false;
        }
        c2 += colIncrement;
        r2 += rowIncrement;
      }
    }
    catch (ArrayIndexOutOfBoundsException e){
      return false;
    }
    for (int i = 0; i < word.length(); i++){
      data[r][c] = word.charAt(i);
      c += colIncrement;
      r += rowIncrement;
    }
    wordsAdded.add(word);
    wordsToAdd.remove(word);
    return true;
  }
  private boolean addAllWords(){
    int rowInc = randgen.nextInt() % 2;
    int colInc = randgen.nextInt() % 2;
    int xcor;
    int ycor;
    Collections.shuffle(wordsToAdd, randgen);
    for (int i = 0; i < wordsToAdd.size(); i++){
      xcor = Math.abs(randgen.nextInt() % data.length);
      ycor = Math.abs(randgen.nextInt() % data[xcor].length);
      while (rowInc == 0 && colInc == 0){
        rowInc = randgen.nextInt() % 2;
        colInc = randgen.nextInt() % 2;
      }
      boolean added = false;
      int tries = 0;
      while (tries < 20 && added == false){
        if (added = addWord(xcor, ycor, wordsToAdd.get(i), rowInc, colInc)){
          added = true;
        }
        else{
          rowInc = randgen.nextInt() % 2;
          colInc = randgen.nextInt() % 2;
        }
        tries++;
      }
    }
    return true;
  }
  private void fillInWordSearch(){
    String alpha = "abcdefghijklmnopqrstuvwxyz";
    for (int i = 0; i < data.length; i++){
      for (int j = 0; j < data[i].length; j++){
        char randChar = alpha.charAt(Math.abs(randgen.nextInt() % 26));
        if (data[i][j] == '_'){
          data[i][j] = randChar;
        }
      }
    }
  }
  private void answerKey(){
    for (int i = 0; i < data.length; i++){
      for (int j = 0; j < data[i].length; j++){
        if (data[i][j] == '_'){
          data[i][j] = ' ';
        }
      }
    }
  }
    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
  public String toString(){
    String output = "|";
    for (int i = 0; i < data.length; i++){
      for (int j = 0; j < data[i].length; j++){
        if (j == data[i].length - 1){
          output += data[i][j];
        }
        else{
          output += data[i][j] + " ";
        }
      }
      output += "|\n|";
    }
    output = output.substring(0, output.length() - 1);
    output += "\nWords: ";
    for (int i = 0; i < wordsAdded.size(); i++){
      output += wordsAdded.get(i) + " ";
    }
    return output += " (seed: " + seed + ")";
  }

    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned
     * and the board is NOT modified.
     */
  /*public boolean addWordHorizontal(String word,int row, int col){
    if (word.length() > data[row].length - col) return false;
    int col2 = col;
    for (int i = 0; i < word.length(); i++){
      if (data[row][col2] != '_' && data[row][col2] != word.charAt(i)){
        return false;
      }
      col2++;
    }
    for (int i = 0; i < word.length(); i++){
      data[row][col] = word.charAt(i);
      col++;
    }
    return true;
  }

    /**Attempts to add a given word to the specified position of the WordGrid.
      *The word is added from top to bottom, must fit on the WordGrid, and must
      *have a corresponding letter to match any letters that it overlaps.
      *
      *@param word is any text to be added to the word grid.
      *@param row is the vertical locaiton of where you want the word to start.
      *@param col is the horizontal location of where you want the word to start.
      *@return true when the word is added successfully. When the word doesn't fit,
      *or there are overlapping letters that do not match, then false is returned.
      *and the board is NOT modified.
      */
  /*public boolean addWordVertical(String word,int row, int col){
    if (word.length() > data.length - row) return false;
    int row2 = row;
    for (int i = 0; i < word.length(); i++){
      if (data[row2][col] != '_' && data[row2][col] != word.charAt(i)){
        return false;
      }
      row2++;
    }
    for (int i = 0; i < word.length(); i++){
      data[row][col] = word.charAt(i);
      row++;
    }
    return true;
  }
  public boolean addWordDiagonal(String word, int row, int col){
    if (word.length() > data.length - row || word.length() > data[row].length - col) return false;
    int row2 = row;
    int col2 = col;
    for (int i = 0; i < word.length(); i++){
      if (data[row2][col2] != '_' && data[row2][col2] != word.charAt(i)){
        return false;
      }
      row2++;
      col2++;
    }
    for (int i = 0; i < word.length(); i++){
      data[row][col] = word.charAt(i);
      row++;
      col++;
    }
    return true;
  }*/
}
