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
 * A sample one-player game of minesweeper. Intended as a potential
 * use of our Matrix interface.
 *
 * @author Sam Schmidt
 * @author Jenifer Silva
 */
public class MinesweepInterface {

    // +----------------+----------------------------------------------
    // | Helper methods |
    // +----------------+

    /**
     * Print the insturctions.
     *
     * @param pen
     *            The printwriter used to print the instructions.
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
     * @param pen
     *              What to use for printing.
     * @param board
     *              The game board at the end.
     */
    static void printResults(PrintWriter pen, Matrix<Integer> board) {
        Matrix.print(pen, board, true);
    } // printResults

    /**
     * Print the board while game continues.
     *
     * @param pen
     *              What to use for printing.
     * @param board
     *              The game board while playing the game.
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
   * Checks the 8 (or less if an edge square) surrounding squares for mines to
   * calculate the number.
   * @param board
   *              The underlying matrix filled out.
   * @param row
   *              The row of the square to be checked.
   * @param col
   *              The col of the square to be checked.
   */
  public static void checkNums(Matrix<Integer> board, int row, int col) {
    // Will only fill in non-mine and not aready checked spaces
    if (board.get(row, col) == null) {
      // Special case of the top edge
      if (row == 0) {
        // Special case of the upper left corner
        if (col == 0) {
          if (board.get(row + 1, col) == 100) {
            
          } // endif
          if (board.get(row + 1, col + 1) == 100) {
            
          } // endif
          if (board.get(row, col + 1) == 100) {
            
          } // endif
          board.set(row, col, );
          //Special case of the upper right corner
        } else if (col == board.width()) {
          if (board.get(row, col - 1) == 100) {
            
          } // endif
          if (board.get(row + 1, col - 1) == 100) {
            
          } // endif
          if (board.get(row + 1, col) == 100) {
            
          } // endif
          board.set(row, col, );
          // The default upper edge
        } else {
          if (board.get(row - 1, col) == 100) {
            
          } // endif
          if (board.get(row - 1, col + 1) == 100) {
            
          } // endif
          if (board.get(row, col + 1) == 100) {
            
          } // endif
          if (board.get(row + 1, col) == 100) {
            
          } // endif
          if (board.get(row + 1, col + 1) == 100) {
            
          } // endif
          board.set(row, col, );
        } // endif
        // Special case of the bottom row
      } else if (row == board.height()) {
        // Lower left corner
        if (col == 0) {
          if (board.get(row - 1, col) == 100) {
            
          } // endif
          if (board.get(row - 1, col + 1) == 100) {
            
          } // endif
          if (board.get(row, col + 1) == 100) {
            
          } // endif
          board.set(row, col, );
          // Lower right corner
        } else if (col == board.width()) {
          if (board.get(row - 1, col) == 100) {
            
          } // endif
          if (board.get(row - 1, col - 1) == 100) {
            
          } // endif
          if (board.get(row, col) == 100) {
            
          } // endif
          board.set(row, col, );
          /// Bottom row
        } else {
          if (board.get(row - 1, col) == 100) {
            
          } // endif
          if (board.get(row - 1, col - 1) == 100) {
            
          } // endif
          if (board.get(row, col - 1) == 100) {
            
          } // endif
          if (board.get(row + 1, col) == 100) {
            
          } // endif
          if (board.get(row + 1, col + 1) == 100) {
            
          } // endif
          board.set(row, col, );
        } // endif
        // Left col
      } else if (col == 0) {
        if (board.get(row, col - 1) == 100) {
          
        } // endif
        if (board.get(row + 1, col - 1) == 100) {
          
        } // endif
        if (board.get(row - 1, col) == 100) {
          
        } // endif
        if (board.get(row - 1, col + 1) == 100) {
          
        } // endif
        if (board.get(row, col + 1) == 100) {
          
        } // endif
        board.set(row, col, );
        // Right col
      } else if (col == board.width()) {
        if (board.get(row, col - 1) == 100) {
          
        } // endif
        if (board.get(row - 1, col - 1) == 100) {
          
        } // endif
        if (board.get(row + 1, col) == 100) {
          
        } // endif
        if (board.get(row + 1, col + 1) == 100) {
          
        } // endif
        if (board.get(row, col + 1) == 100) {
          
        } // endif
        board.set(row, col, );
        // Literally every other case
      } else {
        for (int i = 0; i < 3; i++) {
          if (board.get(row - 1, (col - 1) + i) == 100) {
            
          } // endif
        } // for
      } // for
      for (int i = 0; i < 3; i++) {
        if (board.get(row + 1, (col - 1) + i) == 100) {
          
        } // endif
      } // for

      if (board.get(row, (col - 1)) == 100) {
        
      } // endif

      if (board.get(row, (col - 1)) == 100) {
        
      } // endif
    } // endif block
  } // checkNums(Matrix<Integer>, int, int)

    static void show(int row, int col, Matrix<Integer> board) {
        Integer val = board.get(row, col);
        if(val == 0) {


        } else {
            board.set(row, col, val);

        }
    }

    public static void main(String[] args) throws IOException {
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
            pen.println();
            int turn = 0;
            boolean done = false;
            while (!done) {
                if (hasBomb(height, width, uboard)) {
                    done = true;
                    printResults(pen, uboard);
                } else {

                }

                // Matrix.print(pen, board, true);
                // String command = IOUtils.readCommand(pen, eyes, "Action: ", commands);
                // switch (command.toUpperCase()) {
                // case "RR":
                // if (rrRemaining < 0) {
                // pen.println("Sorry, you've used up your remove row commands.");
                // break;
                // } // if
                // --rrRemaining;
                // if (rrRemaining <= 0) {
                // commands = ArrayUtils.removeAll(commands, "RR");
                // } // if
                // int rowToRemove = IOUtils.readInt(pen, eyes, "Row: ", 0, board.height());
                // prev = board.clone();
                // board.deleteRow(rowToRemove);
                // process(board);
                // break;

                // case "RC":
                // if (rcRemaining < 0) {
                // pen.println("Sorry, you've used up your remove column commands.");
                // break;
                // } // if
                // --rcRemaining;
                // if (rcRemaining <= 0) {
                // commands = ArrayUtils.removeAll(commands, "RC");
                // } // if
                // int colToRemove = IOUtils.readInt(pen, eyes, "Column: ", 0, board.width());
                // prev = board.clone();
                // board.deleteCol(colToRemove);
                // process(board);
                // break;

                // case "IR":
                // if (irRemaining < 0) {
                // pen.println("Sorry, you've used up your insert row commands.");
                // break;
                // } // if
                // --irRemaining;
                // if (irRemaining <= 0) {
                // commands = ArrayUtils.removeAll(commands, "IR");
                // } // if
                // int rowToInsert = IOUtils.readInt(pen, eyes, "Row: ", 0, board.height() + 1);
                // prev = board.clone();
                // board.insertRow(rowToInsert);
                // process(board);
                // break;

                // case "IC":
                // if (icRemaining < 0) {
                // pen.println("Sorry, you've used up your insert row commands.");
                // break;
                // } // if
                // --icRemaining;
                // if (icRemaining <= 0) {
                // commands = ArrayUtils.removeAll(commands, "IC");
                // } // if
                // int colToInsert = IOUtils.readInt(pen, eyes, "Column: ", 0, board.width() +
                // 1);
                // prev = board.clone();
                // board.insertCol(colToInsert);
                // process(board);
                // break;

                // case "DONE":
                // done = true;
                // break;

                // case "SKIP":
                // prev = board.clone();
                // process(board);
                // break;

                // case "UNDO":
                // if (board == prev) {
                // pen.println("Sorry: There's only one level of undo.");
                // } else {
                // board = prev;
                // } // if/else
                // break;

                // default:
                // pen.printf("Unexpected command: '%s'. Please try again.\n", command);
                // break;
                // } // switch
                // } // while

                // // Print final results
                // printResults(pen, board);

                // // And we're done
                // pen.close();

            } // main(String[])
        }
    }
} // class Game1P

// }

// } // class MinesweepInterface
