package main.java.ttsu.game.tictactoe;
import java.util.Scanner;
public class TicTacTeoTwoPlayers {
  /**
   * enum Situation
   */
  public enum Situation{
    Progress,
    Player1_win,
    Player2_win,
    Drew;
  }
  public char arr[][] = new char[3][3];

  public char[][] getLoadArr() {
    return loadArr;
  }

  public void setLoadArr(char[][] loadArr) {
    this.loadArr = loadArr;
  }

  public char loadArr[][] = new char[3][3];
  // public Random random;
  public Scanner Input;

  /**
   * Create a input Object
   * Create a initialized Object.
   */

  public TicTacTeoTwoPlayers(){
      Input = new Scanner(System.in);
      initialization();

  }

  public void initialization(){ // initialize board with " "
    for ( int i = 0; i < 3; i++){
      for (int j = 0; j < 3; j++){
        arr[i][j] = ' ';
      }
    }
  }


  /**
   * This funciong is to print out the TicTacToe Board.
   *
   */

  public void printBoard(){
    System.out.println("-------------");
    for (int i = 0; i < 3; i++){
      System.out.print("| ");
      for(int j = 0; j < 3; j++){
        System.out.print(arr[i][j] + " | ");
      }
      System.out.println();
    }
    System.out.println();
    System.out.println("-------------");
  }

  public void printBoardOfOld(){
    System.out.println("-------------");
    for (int i = 0; i < 3; i++){
      System.out.print("| ");
      for(int j = 0; j < 3; j++){
        System.out.print(loadArr[i][j] + " | ");
      }
      System.out.println();
    }
    System.out.println();
    System.out.println("-------------");
  }
  public int calculatorHowManyRecord(char arr[][]){
    int record = 0;
    for(int i = 0; i< arr.length; i++){
      for (int j = 0; j < arr[i].length; j++){
        if(loadArr[i][j] != ' '){
          record ++;
        }

      }
    }
    return record;
  }

  // This function is to fill in the Value as X or O.

  public void fillIn(int i, int j, char value){
    arr[i][j] = value;
  }
  public void fillInLoad(int i, int j, char value){
    loadArr[i][j] = value;
  }

  public void save(char arr[][]){
    for(int i = 0; i< arr.length; i++){
      for (int j = 0; j < arr[i].length; j++){
        loadArr[i][j] = arr[i][j];
      }
    }
  }

  /* This function is to read the input from player
   *   and check its legal.
   */
  public void stop(){
      save(arr);
  }

  public void player1Turn(boolean load){
  // Player #1 is O

    while(true){
      System.out.print("\nPlayer #1,Please input 2 integers for Row(0-2) and Column(0-2)!\n");
      System.out.print("Row : ");
      int i = Input.nextInt();
      System.out.print("Column : ");
      int j = Input.nextInt();


      //Check the input is legal, which is not out of the board range.

      if ((( i < 0 ) || ( i > 2 )) && i != 9)
        System.out.println("Oops, you should input an integer between 0 and 2 ~\n");
      else if (((j < 0 )||(j > 2) && j != 9))
        System.out.println("Oops, you should input an integer between 0 and 2 ~\n");
      else if (arr[i][j] != ' ') // Check if the player is occupying the seat of the computer.
        System.out.println("Sorry, your input was Occupied square, try again!\n");
      else{
        if(load ==false){fillIn(i,j,'O');}
        else{
          fillInLoad(i,j,'O');
        }


        break;
      }



    }
  }

  /* This function is to read player2 the input from player
   *   and check its legal.
   */
  public void player2Turn(boolean load){       // Player #2 is X
    while(true){
      System.out.print("\nPlayer #2,Please input 2 integers for Row(0-2) and Column(0-2)!\n");
      System.out.print("Row : ");
      int i = Input.nextInt();
      System.out.print("Column : ");
      int j = Input.nextInt();


      //Check the input is legal, which is not out of the board range.

      if (( i < 0 ) || ( i > 2 ))
        System.out.println("Oops, you should input an integer between 0 and 2 ~\n");
      else if (((j < 0 )||(j > 2)))
        System.out.println("Oops, you should input an integer between 0 and 2 ~\n");
      else if (arr[i][j] != ' ') // Check if the player is occupying the seat of the computer.
        System.out.println("Sorry, your input was Occupied square, try again!\n");
      else{
        if(load ==false){fillIn(i,j,'X');}
        else{
          fillInLoad(i,j,'X');
        }
        break;
      }
    }
  }

  /* This function is to judge some situations
   *   that may be encountered during the running of the program.
   */

  public Situation situation(){
    //Check the drew
    boolean drewGame = true;
    for (int i = 0; i < 3; i++ )
      for (int j = 0; j < 3 ; j++ )
        if (arr[i][j] == ' ')
          drewGame = false;
    // player win
    if(victory('O'))
      return Situation.Player1_win;
      // computer win
    else if (victory('X'))
      return Situation.Player2_win;
      // Drew Game
    else if (drewGame)
      return Situation.Drew;

    return Situation.Progress;
  }


  /* This function is to judge the victory.
  * @param:vic
  * @return: victory is win or not
   */

  public boolean victory(char vic) {
    //Check for Rows
    for (int i = 0; i < 3; i++) {
      if ((arr[i][0] == vic) && (arr[i][1] == vic) && (arr[i][2] == vic)){
        //   gameOnGoing = false;
        return true;
      }

    }
    //Check for Columns
    for (int j = 0; j < 3; j++) {
      if ((arr[0][j] == vic) && (arr[1][j] == vic) && (arr[2][j] == vic)){
        //    gameOnGoing = false;
        return true;
      }

    }
    //Check for Diagonals
    if (( arr[0][0] == vic) && (arr[1][1] == vic) && (arr[2][2] == vic))
      return true;
    if (( arr[2][0] == vic) && (arr[1][1] == vic) && (arr[0][2] == vic)){
      //  gameOnGoing = false;
      return true;
    }

    return false;
  }

}
