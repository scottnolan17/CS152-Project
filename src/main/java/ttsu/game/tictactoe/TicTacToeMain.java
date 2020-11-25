package main.java.ttsu.game.tictactoe;

import java.awt.Color;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.ttsu.game.ai.GameIntelligenceAgent;
import main.java.ttsu.game.ai.MinimaxAgent;
import main.java.ttsu.game.ai.heuristic.tictactoe.TicTacToeEvaluator;
import main.java.ttsu.game.gui.CustomButton;
import main.java.ttsu.game.tictactoe.TicTacToeGameState.Player;

public class TicTacToeMain {

  /**
   * main class start form here
   * @author Yang Liu
   * @author Yang Zhang
   */

public static final int WIDTH = 600;
public static final int HEIGHT = 500;

  public static void main(String[] args) throws Exception {
	  
	  
	JFrame frame = new JFrame("Tic-Tac-Toe");
	frame.setIconImage(new ImageIcon("images/tictactoe_thumbnail.png").getImage());

	JPanel mainScreen = new JPanel();
	JPanel playScreen = new JPanel();
	
	JLabel title = new JLabel("Tic-Tac-Toe");
	CustomButton singleplayer = new CustomButton("Player vs. CPU", WIDTH/2 - WIDTH/6, HEIGHT/8*3, WIDTH/3, HEIGHT/10);
	CustomButton multiplayer = new CustomButton("Player vs. Player", WIDTH/2 - WIDTH/6, HEIGHT/8*4, WIDTH/3, HEIGHT/10);
	CustomButton quit = new CustomButton("Quit Game", WIDTH/2 - WIDTH/6, HEIGHT/8*5, WIDTH/3, HEIGHT/10);
	
	title.setLocation(0, 0);
	
	mainScreen.setBounds(0, 0, WIDTH, HEIGHT);
	mainScreen.setBackground(Color.WHITE);
	mainScreen.setLayout(null);
	mainScreen.add(title);
	mainScreen.add(singleplayer);
	mainScreen.add(multiplayer);
	mainScreen.add(quit);
			
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLayout(null);
	frame.add(mainScreen);
	frame.setSize(WIDTH, HEIGHT);
	frame.setVisible(true);
	  
    /*================Login this game=============================*/
    System.out.println("Enter 1 to start a new game, then you have three choices;");
    System.out.println("Human to human;");
    System.out.println("Human to easy computer;");
    System.out.println("Human to hard computer;");
    System.out.println("Just follow the instruction to do");

    // receiving input from user
    // Create a input Object
    Scanner Input = new Scanner(System.in);
    int i = 0;
    try {
      i = Input.nextInt();
      if (i != 0 && i != 1) {
        System.out.println("RULE:input is only 1 or 0");
      }
    } catch (Exception e) {
      System.out.println("RULE:input is only 1 or 0");
    }
    if (i == 1) {
      // call startNewGame
      System.out.println("You want to play with your friend or computer?");
      System.out.println("Enter 1 to play with your friend;");
      System.out.println("Enter 0 to play with computer.\n");

      try {
        i = Input.nextInt();
        if (i != 0 && i != 1) {
          System.out.println("RULE:input is only 1 or 0");
        }
      } catch (Exception e) {
        System.out.println("RULE:input is only 1 or 0");
        return;
      }
      // play start from here, now is two people play
      if (i == 1) {
        humanWithHuman(1, Input);
      }
      // play start from here too, human with computer
      if (i == 0) {
        System.out.println("Playing with computer has two levels of difficulty: Easy or Hard");
        System.out.println("Enter 1 for easy;");
        System.out.println("Enter 0 for hard.\n");

        i = Input.nextInt();
        // easy computer start
        if (i == 1) {
          humanWithEasyComputer(1, Input);
        }
        // Hard computer start
        if (i == 0) {
          humanWithHardComputer();
        }
      }
    }
  }




  /**
   * Implementation of two human player to play
   *
   * @param  i
   * @param  Input
   *
   */
  private static void humanWithHuman(int i, Scanner Input) {
    System.out.println("plase type true and false everytime after your input to stop or continue the game");
    // play with people
    /*========================Play with People=============================*/
    TicTacTeoTwoPlayers twop = new TicTacTeoTwoPlayers();
    int player1 = 0, player2 = 0;

      while (i == 1) {
        twop.printBoard();
        TicTacTeoTwoPlayers.Situation situation2 = twop.situation();
        boolean play1_turn = true;
        while (situation2 == TicTacTeoTwoPlayers.Situation.Progress) {
          if (play1_turn) {
            twop.player1Turn(false);
            twop.printBoard();
            System.out.println("plase type true and false everytime after your input to stop or continue the game");
            System.out.println("true = stop");
            System.out.println("stop = :");
            boolean end = false;
            try{
             end = Input.nextBoolean();
            } catch(Exception e){
              System.out.println("you have to type true or false");
            }
            if(end){
              twop.stop();
              break;
            }
          } else {
            twop.player2Turn(false);
            twop.printBoard();
            System.out.println("plase type true and false everytime after your input to stop or continue the game");
            System.out.println("true = stop");
            System.out.println("stop = :");
            boolean end = Input.nextBoolean();
            if(end){
              twop.stop();
              break;
            }
          }
          play1_turn = !play1_turn;

          situation2 = twop.situation();
          if (situation2 == TicTacTeoTwoPlayers.Situation.Drew) {
            System.out.println("This is a draw!\n");
          } else if (situation2 == TicTacTeoTwoPlayers.Situation.Player1_win) {
            System.out.println("Player #1 win!\n");
            player1++;
          } else if (situation2 == TicTacTeoTwoPlayers.Situation.Player2_win) {
            System.out.println("Player #2 win!\n");
            player2++;
          }

        }
        System.out.print("The scores:\n");
        System.out.print("Player #1: " + player1 + ";   Player #2: " + player2);



        twop.initialization();


        System.out.print("\nPress 1 to start a new game;\n");
        System.out.print("Press 0 to exit the game.\n");
        i = Input.nextInt();

    }

    }



  /**
   * Implementation of one human player to easy computer
   *
   * @param  i
   * @param  Input
   *
   */
  private static void humanWithEasyComputer(int i,Scanner Input){
    // go to essy
    /*================Play with A.I. (level I)=============================*/
    TicTacTeoBoard tic = new TicTacTeoBoard();
    int player = 0, computer = 0; // their scores

    while (i == 1) {  // For start a new game
      // Randomly pick who go first.
      Random random = new Random();
      int nextStep = random.nextInt(2); // Generate a random number from 0 and 1, not include 2.

      while (tic.situation() == TicTacTeoBoard.Situation.Progress) {
        if (nextStep == 0) {
          tic.playerTurn();
        } else {
          tic.computerTurn();
        }

        tic.printBoard();

        nextStep = (nextStep + 1) % 2;
      }

      TicTacTeoBoard.Situation situation = tic.situation();

      if (situation == TicTacTeoBoard.Situation.Drew) {
        System.out.println("This is a draw!\n");
      } else if (situation == TicTacTeoBoard.Situation.Computer_win) {
        System.out.println("The computer won!\n");
        computer++;

      } else if (situation == TicTacTeoBoard.Situation.Player_win) {
        System.out.println("You win!\n");
        player++;
      }
      System.out.print("The scores:\n");
      System.out.print("You: " + player + ";   Computer: " + computer);


      //                        System.out.print("\n********************************");
      //                        System.out.print("the saved board is: \n");
      //                        tic.printBoard();
      //                        System.out.print("********************************");

      tic.initialization();


      System.out.print("\nPress 1 to start a new game;\n");
      System.out.print("Press 0 to exit.\n");
      i = Input.nextInt();

    }

  }
  /**
   * Implementation of one human player to hard computer
   */
  private static void humanWithHardComputer(){
    TicTacToeEvaluator evaluator = new TicTacToeEvaluator(Player.O);
    GameIntelligenceAgent<TicTacToeGameState> agent =
        new MinimaxAgent<TicTacToeGameState>(evaluator);
    Scanner scanner = new Scanner(System.in);
    TicTacToeGameRunner game = new TicTacToeGameRunner(agent, scanner, System.out);

    game.run();
  }

}













