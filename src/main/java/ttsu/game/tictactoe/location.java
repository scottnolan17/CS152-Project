package main.java.ttsu.game.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class location {
  int row;
  int col;

  location(int row, int col){
    this.row = row;
    this.col = col;
  }
  List<location> O = new ArrayList<location>();
  List<location> X = new ArrayList<location>();
}
