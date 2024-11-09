package edu.grinnell.csc207.sample;

import edu.grinnell.csc207.util.ArrayUtils;
import edu.grinnell.csc207.util.IOUtils;
import edu.grinnell.csc207.util.Matrix;
import edu.grinnell.csc207.util.MatrixV0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;

import edu.grinnell.csc207.sample.*;

/**
 * A sample one-player game of minesweeper. Intended as a potential use of our Matrix interface.
 *
 * @author Sam Schmidt
 * @author Jenifer Silva
 */
public class MinesweeperVisible {

  // +----------------+----------------------------------------------
  // | Helper methods |
  // +----------------+

  /**
   * Print the insturctions.
   *
   * @param pen The printwriter used to print the instructions.
   */
  public static void printInstructions(PrintWriter pen) {
    pen.println("""
        Welcome to Minesweeper.

        Command-line arguments:

        * -w width - set up the width of the board
        * -h height - set up the height of the board

        Your game board is a grid containing randomly placed mines
        hidden from your eyes. Mines are represented by 100's.

        Your goal is to click through as manys safe spaces as you can.

        Each turn you will enter the row and column number you wish to reveal.
        Our row and column start at 1.
        You can only enter numbers 1- max row / max column number.

        After each move, the surrounding blocks will reveal the number of mines near them.
        If you click a mine, you die.
        GAME OVER

            1|6|7|
            -+-+-
            5|*|4
            -+-+-
            8|3|2
        """);
  } // printInstructions(PrintWriter)

  /**
   * Print the results of the game.
   *
   * @param pen What to use for printing.
   * @param board The game board at the end.
   */
  static void printResults(PrintWriter pen, Matrix<Integer> board) {
    Matrix.print(pen, board, true);
  } // printResults

  /**
   * Print the board while game continues.
   *
   * @param pen What to use for printing.
   * @param board The game board while playing the game.
   */
  static void printProcess(PrintWriter pen, Matrix<String> board) {
    Matrix<Integer> sboard = new MatrixV0<Integer>(10, 10);
    for (int i = 0; i < sboard.height(); i++) {
      for (int k = 0; k < sboard.width(); k++) {
        sboard.set(i, k, null);
      } // for
    }
  }

  static boolean hasBomb(int row, int col, Matrix<Integer> board) {
    if (board.get(row, col) == 100) {
      return true;
    } // endif
    return false;
  } // hasBomb(int, int, Matrix<int>)

  /**
   * Checks the 8 (or less if an edge square) surrounding squares for mines to calculate the number.
   * 
   * @param board The underlying matrix filled out.
   * @param row The row of the square to be checked.
   * @param col The col of the square to be checked.
   */
  public static void checkNums(Matrix<Integer> uboard, Matrix<Integer> sboard, int row, int col) {
    // First letter: U for "upper", M for "middle", B for "bottom", C for "center"
    // Second letter: L for "left", R for "right", T for "top", B for "Bottom"
    Integer UL = uboard.get(row - 1, col - 1);
    Integer ML = uboard.get(row, col - 1);
    Integer BL = uboard.get(row + 1, col - 1);
    Integer CT = uboard.get(row - 1, col);
    Integer CB = uboard.get(row + 1, col);
    Integer UR = uboard.get(row - 1, col + 1);
    Integer MR = uboard.get(row, col + 1);
    Integer BR = uboard.get(row + 1, col + 1);

    // Special case of the top edge
    if (row == 0) {
      // Special case of the upper left corner
      if (col == 0) {
        if (sboard.get(row, col + 1) == null) {
          if (MR == 0) {
            sboard.set(row, col + 1, 0);
            checkNums(uboard, sboard, row, col + 1);
          } else {
            sboard.set(row, col + 1, MR);
          } // endif
        } // endif - mid right
        if (sboard.get(row + 1, col + 1) == null) {
          if (BR == 0) {
            sboard.set(row + 1, col + 1, 0);
            checkNums(uboard, sboard, row + 1, col + 1);
          } else {
            sboard.set(row + 1, col + 1, BR);
          } // endif
        } // endif - bottom right
        if (sboard.get(row + 1, col) == null) {
          if (CB == 0) {
            sboard.set(row + 1, col, 0);
            checkNums(uboard, sboard, row + 1, col);
          } else {
            sboard.set(row + 1, col, CB);
          } // endif
        } // endif - center bottom
          // Special case of the upper right corner
      } else if (col == sboard.width()) {
        if (sboard.get(row, col - 1) == null) {
          if (ML == 0) {
            sboard.set(row, col - 1, 0);
            checkNums(uboard, sboard, row, col - 1);
          } else {
            sboard.set(row, col - 1, ML);
          } // endif
        } // endif - middle left
        if (sboard.get(row + 1, col - 1) == null) {
          if (BL == 0) {
            sboard.set(row + 1, col - 1, 0);
            checkNums(uboard, sboard, row + 1, col - 1);
          } else {
            sboard.set(row + 1, col - 1, BL);
          } // endif
        } // endif - bottom left
        if (sboard.get(row + 1, col) == null) {
          if (CB == 0) {
            sboard.set(row + 1, col, 0);
            checkNums(uboard, sboard, row + 1, col);
          } else {
            sboard.set(row + 1, col, CB);
          } // endif
        } // endif - center bottom
          // The default upper edge
      } else {
        if (sboard.get(row, col - 1) == null) {
          if (ML == 0) {
            sboard.set(row, col - 1, 0);
            checkNums(uboard, sboard, row, col - 1);
          } else {
            sboard.set(row, col - 1, ML);
          } // endif
        } // endif - middle left
        if (sboard.get(row + 1, col - 1) == null) {
          if (BL == 0) {
            sboard.set(row + 1, col - 1, 0);
            checkNums(uboard, sboard, row + 1, col - 1);
          } else {
            sboard.set(row + 1, col - 1, BL);
          } // endif
        } // endif - bottom left
        if (sboard.get(row + 1, col) == null) {
          if (CB == 0) {
            sboard.set(row + 1, col, 0);
            checkNums(uboard, sboard, row + 1, col);
          } else {
            sboard.set(row + 1, col, CB);
          } // endif
        } // endif - center bottom
        if (sboard.get(row, col + 1) == null) {
          if (MR == 0) {
            sboard.set(row, col + 1, 0);
            checkNums(uboard, sboard, row, col + 1);
          } else {
            sboard.set(row, col + 1, MR);
          } // endif
        } // endif - mid right
        if (sboard.get(row + 1, col + 1) == null) {
          if (BR == 0) {
            sboard.set(row + 1, col + 1, 0);
            checkNums(uboard, sboard, row + 1, col + 1);
          } else {
            sboard.set(row + 1, col + 1, BR);
          } // endif
        } // endif - bottom right
      } //endif - top row
      // Special case of the bottom row
    } else if (row == sboard.height()) {
      // Lower left corner
      if (col == 0) {
        if (sboard.get(row - 1, col) == null) {
          if (CT == 0) {
            sboard.set(row - 1, col, 0);
            checkNums(uboard, sboard, row - 1, col);
          } else {
            sboard.set(row - 1, col, CT);
          } // endif
        } // endif - center top
        if (sboard.get(row, col + 1) == null) {
          if (MR == 0) {
            sboard.set(row, col + 1, 0);
            checkNums(uboard, sboard, row, col + 1);
          } else {
            sboard.set(row, col + 1, MR);
          } // endif
        } // endif - mid right
        if (sboard.get(row - 1, col + 1) == null) {
          if (UR == 0) {
            sboard.set(row - 1, col + 1, 0);
            checkNums(uboard, sboard, row - 1, col + 1);
          } else {
            sboard.set(row - 1, col + 1, UR);
          } // endif
        } // endif - upper right
        // Lower right corner
      } else if (col == sboard.width()) {
        if (sboard.get(row - 1, col) == null) {
          if (CT == 0) {
            sboard.set(row - 1, col, 0);
            checkNums(uboard, sboard, row - 1, col);
          } else {
            sboard.set(row - 1, col, CT);
          } // endif
        } // endif - center top
        if (sboard.get(row, col - 1) == null) {
          if (ML == 0) {
            sboard.set(row, col - 1, 0);
            checkNums(uboard, sboard, row, col - 1);
          } else {
            sboard.set(row, col - 1, ML);
          } // endif
        } // endif - mid left
        if (sboard.get(row - 1, col - 1) == null) {
          if (UR == 0) {
            sboard.set(row - 1, col - 1, 0);
            checkNums(uboard, sboard, row - 1, col - 1);
          } else {
            sboard.set(row - 1, col - 1, UR);
          } // endif
        } // endif - upper right
        /// Bottom row
      } else {
        if (sboard.get(row - 1, col) == null) {
          if (CT == 0) {
            sboard.set(row - 1, col, 0);
            checkNums(uboard, sboard, row - 1, col);
          } else {
            sboard.set(row - 1, col, CT);
          } // endif
        } // endif - center top
        if (sboard.get(row, col + 1) == null) {
          if (MR == 0) {
            sboard.set(row, col + 1, 0);
            checkNums(uboard, sboard, row, col + 1);
          } else {
            sboard.set(row, col + 1, MR);
          } // endif
        } // endif - mid right
        if (sboard.get(row - 1, col + 1) == null) {
          if (UR == 0) {
            sboard.set(row - 1, col + 1, 0);
            checkNums(uboard, sboard, row - 1, col + 1);
          } else {
            sboard.set(row - 1, col + 1, UR);
          } // endif
        } // endif - upper right
        if (sboard.get(row, col - 1) == null) {
          if (ML == 0) {
            sboard.set(row, col - 1, 0);
            checkNums(uboard, sboard, row, col - 1);
          } else {
            sboard.set(row, col - 1, ML);
          } // endif
        } // endif - mid left
        if (sboard.get(row - 1, col - 1) == null) {
          if (UL == 0) {
            sboard.set(row - 1, col - 1, 0);
            checkNums(uboard, sboard, row - 1, col - 1);
          } else {
            sboard.set(row - 1, col - 1, UL);
          } // endif
        } // endif - upper left
      } // endif
      // Left col
    } else if (col == 0) {
      if (sboard.get(row - 1, col) == null) {
        if (CT == 0) {
          sboard.set(row - 1, col, 0);
          checkNums(uboard, sboard, row - 1, col);
        } else {
          sboard.set(row - 1, col, CT);
        } // endif
      } // endif - center top
      if (sboard.get(row + 1, col) == null) {
        if (CB == 0) {
          sboard.set(row + 1, col, 0);
          checkNums(uboard, sboard, row + 1, col);
        } else {
          sboard.set(row + 1, col, CB);
        } // endif
      } // endif - center bottom
      if (sboard.get(row - 1, col + 1) == null) {
        if (UR == 0) {
          sboard.set(row - 1, col + 1, 0);
          checkNums(uboard, sboard, row - 1, col + 1);
        } else {
          sboard.set(row - 1, col + 1, UR);
        } // endif
      } // endif - upper right
      if (sboard.get(row, col + 1) == null) {
        if (MR == 0) {
          sboard.set(row, col + 1, 0);
          checkNums(uboard, sboard, row, col + 1);
        } else {
          sboard.set(row, col + 1, MR);
        } // endif
      } // endif - mid right
      if (sboard.get(row + 1, col + 1) == null) {
        if (BR == 0) {
          sboard.set(row + 1, col + 1, 0);
          checkNums(uboard, sboard, row + 1, col + 1);
        } else {
          sboard.set(row + 1, col + 1, BR);
        } // endif
      } // endif - bottom right
      // Right col
    } else if (col == sboard.width()) {
      if (sboard.get(row - 1, col) == null) {
        if (CT == 0) {
          sboard.set(row - 1, col, 0);
          checkNums(uboard, sboard, row - 1, col);
        } else {
          sboard.set(row - 1, col, CT);
        } // endif
      } // endif - center top
      if (sboard.get(row + 1, col) == null) {
        if (CB == 0) {
          sboard.set(row + 1, col, 0);
          checkNums(uboard, sboard, row + 1, col);
        } else {
          sboard.set(row + 1, col, CB);
        } // endif
      } // endif - center bottom
      if (sboard.get(row - 1, col - 1) == null) {
        if (UL == 0) {
          sboard.set(row - 1, col - 1, 0);
          checkNums(uboard, sboard, row - 1, col - 1);
        } else {
          sboard.set(row - 1, col - 1, UL);
        } // endif
      } // endif - upper left
      if (sboard.get(row, col - 1) == null) {
        if (ML == 0) {
          sboard.set(row, col - 1, 0);
          checkNums(uboard, sboard, row, col - 1);
        } else {
          sboard.set(row, col - 1, ML);
        } // endif
      } // endif - mid left
      if (sboard.get(row + 1, col - 1) == null) {
        if (BL == 0) {
          sboard.set(row + 1, col - 1, 0);
          checkNums(uboard, sboard, row + 1, col - 1);
        } else {
          sboard.set(row + 1, col - 1, BL);
        } // endif
      } // endif - bottom left
      // Literally every other case
    } else {
      if (sboard.get(row - 1, col) == null) {
        if (CT == 0) {
          sboard.set(row - 1, col, 0);
          checkNums(uboard, sboard, row - 1, col);
        } else {
          sboard.set(row - 1, col, CT);
        } // endif
      } // endif - center top
      if (sboard.get(row + 1, col) == null) {
        if (CB == 0) {
          sboard.set(row + 1, col, 0);
          checkNums(uboard, sboard, row + 1, col);
        } else {
          sboard.set(row + 1, col, CB);
        } // endif
      } // endif - center bottom
      if (sboard.get(row - 1, col - 1) == null) {
        if (UL == 0) {
          sboard.set(row - 1, col - 1, 0);
          checkNums(uboard, sboard, row - 1, col - 1);
        } else {
          sboard.set(row - 1, col - 1, UL);
        } // endif
      } // endif - upper left
      if (sboard.get(row, col - 1) == null) {
        if (ML == 0) {
          sboard.set(row, col - 1, 0);
          checkNums(uboard, sboard, row, col - 1);
        } else {
          sboard.set(row, col - 1, ML);
        } // endif
      } // endif - mid left
      if (sboard.get(row + 1, col - 1) == null) {
        if (BL == 0) {
          sboard.set(row + 1, col - 1, 0);
          checkNums(uboard, sboard, row + 1, col - 1);
        } else {
          sboard.set(row + 1, col - 1, BL);
        } // endif
      } // endif - bottom left
      if (sboard.get(row - 1, col + 1) == null) {
        if (UR == 0) {
          sboard.set(row - 1, col + 1, 0);
          checkNums(uboard, sboard, row - 1, col + 1);
        } else {
          sboard.set(row - 1, col + 1, UR);
        } // endif
      } // endif - upper right
      if (sboard.get(row, col + 1) == null) {
        if (MR == 0) {
          sboard.set(row, col + 1, 0);
          checkNums(uboard, sboard, row, col + 1);
        } else {
          sboard.set(row, col + 1, MR);
        } // endif
      } // endif - mid right
      if (sboard.get(row + 1, col + 1) == null) {
        if (BR == 0) {
          sboard.set(row + 1, col + 1, 0);
          checkNums(uboard, sboard, row + 1, col + 1);
        } else {
          sboard.set(row + 1, col + 1, BR);
        } // endif
      } // endif - bottom right
    } // endif
  } // checkNums(Matrix<Integer>, int, int)

  static void show(int row, int col, Matrix<Integer> board) {
    Integer val = board.get(row, col);
    if (val == 0) {

    } else {
      board.set(row, col, val);
    }
  }

  public static void main(String[] args) throws IOException {
    int DEFAULT_HEIGHT = 10;
    int DEFAULT_WIDTH = 10;
    PrintWriter pen = new PrintWriter(System.out, true);
    BufferedReader eyes = new BufferedReader(new InputStreamReader(System.in));

    int width = DEFAULT_WIDTH;
    int height = DEFAULT_HEIGHT;

    // Process the command line
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-w":
          try {
            width = Integer.parseInt(args[++i]);
          } catch (Exception e) {
            System.err.printf("Invalid width: %s (not an integer)\n", args[i]);
            return;
          } // try/catch
          if (width < 3) {
            System.err.printf("Invalid width: %s (less than 3)\n", width);
            return;
          } // if
          break;

        case "-h":
          try {
            height = Integer.parseInt(args[++i]);
          } catch (Exception e) {
            System.err.printf("Invalid height: %s (not an integer)\n", args[i]);
            return;
          } // try/catch
          if (height < 3) {
            System.err.printf("Invalid height: %s (less than 3)\n", height);
            return;
          } // if
          break;

        default:
          System.err.printf("Invalid command-line flag: %s\n", args[i]);
          return;
      } // switch
    } // for

    // Get started
    printInstructions(pen);
    pen.print("Hit return to continue");
    pen.flush();
    eyes.readLine();

    // Set up the board
    Matrix<Integer> uboard = new MatrixV0<Integer>(width, height);
    Matrix<Integer> sboard = new MatrixV0<Integer>(width, height);
    for (int i = 0; i < sboard.height(); i++) {
      for (int k = 0; k < sboard.width(); k++) {
        sboard.set(i, k, null);
      } // for

      // Run the game
      pen.println(sboard);
      Integer row = 0;
      Integer col = 0;
      String userInput = "";
      int space;
      boolean badInput = true;

      while (badInput) {
        try {
          userInput = eyes.readLine();
          space = userInput.indexOf(" ");
          if (space == -1) {
            System.err.println("Incorrect number of inputs please try again. Please do -> ROW COL");
            continue;
          }
          row = Integer.parseInt(userInput.substring(0, space)); // try catch invalid input do ?
          col = Integer.parseInt(userInput.substring(space));
          badInput = false;
        } catch (NumberFormatException e) {
          System.err.printf("You did not give me a number Try again");
          continue;

        } catch (Exception e) {
          System.err.printf("Some other exception. Print valid Numbers!");
          continue;
        }
      }

      boolean done = false;
      while (!done) {
        if (hasBomb(row, col, uboard)) {
          done = true;
          printResults(pen, uboard);
        } else {
          checkNums(uboard, sboard, row, col);
          Matrix.print(pen, sboard);
          pen.println("Good job, you didn't die :) now pick another box.");
          badInput = true;
          while (badInput) {
            try {
              userInput = eyes.readLine();
              space = userInput.indexOf(" ");
              if (space == -1) {
                System.err
                    .println("Incorrect number of inputs please try again. Please do -> ROW COL");
                continue;
              }
              row = Integer.parseInt(userInput.substring(0, space)); // try catch invalid input do ?
              col = Integer.parseInt(userInput.substring(space));
              badInput = false;
            } catch (NumberFormatException e) {
              System.err.printf("You did not give me a number Try again");
              continue;

            } catch (Exception e) {
              System.err.printf("Some other exception. Print valid Numbers!");
              continue;
            }
          }

        }
      }
    }
  } // main(String[])
}
// }
// } // class MinesweepVisible
