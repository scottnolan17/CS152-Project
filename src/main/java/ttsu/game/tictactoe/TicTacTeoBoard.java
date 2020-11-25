package main.java.ttsu.game.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents a TicTacToe game board for two player.
 */

public class TicTacTeoBoard {
  public enum Situation {
    Progress,
    Computer_win,
    Player_win,
    Drew;
  }


  public char arr[][] = new char[3][3];
  public Random random;
  public Scanner Input;


  public TicTacTeoBoard() {
    // Create a input Object
    Input = new Scanner(System.in);

    // Create a Random Object
    random = new Random();

    //Create a initialized Object.
    initialization();

  }

  /**
   * Creates a new blank TicTacToe board.
   */
  public void initialization() { // initialize board with " "
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = ' ';
      }
    }
  }

  /**
   * This funciong is to print out the TicTacToe Board.
   */

  public void printBoard() {
    System.out.println("-------------");
    for (int i = 0; i < 3; i++) {
      System.out.print("| ");
      for (int j = 0; j < 3; j++) {
        System.out.print(arr[i][j] + " | ");
      }
      System.out.println();
    }
    System.out.println();
    System.out.println("-------------");
  }

  /**
   * This function is to fill in the Value as X or O.
   *
   * @param i the row to mark
   * @param j the column to mark
   * @return array
   */

  public void fillIn(int i, int j, char value) {
    arr[i][j] = value;
  }

  /**
   * This function is to make a randomly legal move for computer.
   */

  public void computerTurn() {     // Computer is X
    int row = 0, col = 0;

    location loc = new location(row, col);
    List<location> X = new ArrayList<>();

    while (true) {
      int i = (int) random.nextInt(3);
      int j = (int) random.nextInt(3);
      X.add(new location(i, j));

      // check
      if (arr[i][j] == ' ') {
        fillIn(i, j, 'X');
        break;
      }
    }
    for (location l : X) {
      System.out.print(l.row + " ");
      System.out.print(l.col + " ");

      System.out.println();
    }
    System.out.println("\n");
  }

  /**
   * This function is to read the input from player and check its legal.
   */

  public void playerTurn() {       // Player is O
    while (true) {
      System.out.print("\nYour turn! Please input 2 integers for Row(0-2) and Column(0-2)!\n");
      System.out.print("Row : ");
      int i = Input.nextInt();
      System.out.print("Column : ");
      int j = Input.nextInt();

      //Check the input is legal, which is not out of the board range.

      if ((i < 0) || (i > 2)) {
        System.out.println("Oops, you should input an integer between 0 and 2 ~\n");
      } else if (((j < 0) || (j > 2))) {
        System.out.println("Oops, you should input an integer between 0 and 2 ~\n");
      } else if (arr[i][j] == 'X') // Check if the player is occupying the seat of the computer.
      {
        System.out.println("Sorry, your input was Occupied square, try again!\n");
      } else {
        fillIn(i, j, 'O');
        break;
      }
    }
  }

  /**
   * This function is to judge some situations that may be encountered during the running of the program.
   */

  public Situation situation() {
    //Check the drew
    boolean drewGame = true;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (arr[i][j] == ' ') {
          drewGame = false;
        }
      }
    }


    // player win
    if (victory('O')) {
      return Situation.Player_win;
    }
    // computer win
    else if (victory('X')) {
      return Situation.Computer_win;
    }
    // Drew Game
    else if (drewGame) {
      return Situation.Drew;
    }

    return Situation.Progress;
  }

  /**
   * This function is to judge the victory.
   */

  public boolean victory(char vic) {
    //Check for Rows
    for (int i = 0; i < 3; i++) {
      if ((arr[i][0] == vic) && (arr[i][1] == vic) && (arr[i][2] == vic)) {
        return true;
      }
    }
    //Check for Columns
    for (int j = 0; j < 3; j++) {
      if ((arr[0][j] == vic) && (arr[1][j] == vic) && (arr[2][j] == vic)) {
        return true;
      }

    }
    //Check for Diagonals
    if ((arr[0][0] == vic) && (arr[1][1] == vic) && (arr[2][2] == vic)) {
      return true;
    }
    if ((arr[2][0] == vic) && (arr[1][1] == vic) && (arr[0][2] == vic)) {
      return true;
    }

    return false;
  }
}


