package edu.grinnell.csc207.sample;

import edu.grinnell.csc207.util.Matrix;
import edu.grinnell.csc207.util.MatrixV0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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

        Your goal is to click through as many safe spaces as you can.

        Each turn you will enter the row and column number you wish to reveal.
        Our row and column start at 0.
        You can only enter numbers 0 to max row / max column number.

        After each move, the surrounding blocks will reveal the number of mines near them.
        If you click a mine, you die.
        GAME OVER
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
  static void printProcess(PrintWriter pen, Matrix<Integer> board) {
    Matrix<Integer> sboard = new MatrixV0<Integer>(Underlying.WIDTH_DEFAULT, Underlying.HEIGHT_DEFAULT);
    for (int i = 0; i < sboard.height(); i++) {
      for (int k = 0; k < sboard.width(); k++) {
        sboard.set(i, k, null);
      } // for
    } // for
  } // printProcess(PrintWriter, Matrix<Integer>)

  /**
   * Checks if the given spot has a bomb.
   *
   * @param row The row of the spot.
   * @param col The col of the spot.
   * @param uboard The underlying board that holds the mines/bombs.
   * @return true if found, false otherwise.
   */
  static boolean hasBomb(int row, int col, Matrix<Integer> uboard) {
    if (uboard.get(row, col) == 100) {
      return true;
    } // endif
    return false;
  } // hasBomb(int, int, Matrix<int>)

  /**
   * Checks the 8 (or less if an edge square) surrounding squares for mines to calculate the number.
   *
   * @param sboard The gameboard that will be filled in.
   * @param uboard The underlying matrix filled out.
   * @param row The row of the square to be checked.
   * @param col The col of the square to be checked.
   */
  public static void checkNums(Matrix<Integer> uboard, Matrix<Integer> sboard, int row, int col) {
    if (uboard.get(row, col) > 0 && uboard.get(row, col) < 100) {
      sboard.set(row, col, uboard.get(row, col));

    } else {
      // Special case of the top edge
      if (row == 0) {
        // Special case of the upper left corner
        if (col == 0) {
          if (sboard.get(row, col + 1) == null) {
            if (uboard.get(row, col + 1) == 0) {
              sboard.set(row, col + 1, 0);

              checkNums(uboard, sboard, row, col + 1);
            } else {
              sboard.set(row, col + 1, uboard.get(row, col + 1));

            } // endif
          } // endif - mid right
          if (sboard.get(row + 1, col + 1) == null) {
            if (uboard.get(row + 1, col + 1) == 0) {
              sboard.set(row + 1, col + 1, 0);

              checkNums(uboard, sboard, row + 1, col + 1);
            } else {
              sboard.set(row + 1, col + 1, uboard.get(row + 1, col + 1));

            } // endif
          } // endif - bottom right
          if (sboard.get(row + 1, col) == null) {
            if (uboard.get(row + 1, col) == 0) {
              sboard.set(row + 1, col, 0);

              checkNums(uboard, sboard, row + 1, col);
            } else {
              sboard.set(row + 1, col, uboard.get(row + 1, col));

            } // endif
          } // endif - center bottom
            // Special case of the upper right corner
        } else if (col == sboard.width() - 1) {
          if (sboard.get(row, col - 1) == null) {
            if (uboard.get(row, col - 1) == 0) {
              sboard.set(row, col - 1, 0);

              checkNums(uboard, sboard, row, col - 1);
            } else {
              sboard.set(row, col - 1, uboard.get(row, col - 1));

            } // endif
          } // endif - middle left
          if (sboard.get(row + 1, col - 1) == null) {
            if (uboard.get(row + 1, col - 1) == 0) {
              sboard.set(row + 1, col - 1, 0);

              checkNums(uboard, sboard, row + 1, col - 1);
            } else {
              sboard.set(row + 1, col - 1, uboard.get(row + 1, col - 1));

            } // endif
          } // endif - bottom left
          if (sboard.get(row + 1, col) == null) {
            if (uboard.get(row + 1, col) == 0) {
              sboard.set(row + 1, col, 0);

              checkNums(uboard, sboard, row + 1, col);
            } else {
              sboard.set(row + 1, col, uboard.get(row + 1, col));

            } // endif
          } // endif - center bottom
            // The default upper edge
        } else {
          if (sboard.get(row, col - 1) == null) {
            if (uboard.get(row, col - 1) == 0) {
              sboard.set(row, col - 1, 0);

              checkNums(uboard, sboard, row, col - 1);
            } else {
              sboard.set(row, col - 1, uboard.get(row, col - 1));

            } // endif
          } // endif - middle left
          if (sboard.get(row + 1, col - 1) == null) {
            if (uboard.get(row + 1, col - 1) == 0) {
              sboard.set(row + 1, col - 1, 0);

              checkNums(uboard, sboard, row + 1, col - 1);
            } else {
              sboard.set(row + 1, col - 1, uboard.get(row + 1, col - 1));

            } // endif
          } // endif - bottom left
          if (sboard.get(row + 1, col) == null) {
            if (uboard.get(row + 1, col) == 0) {
              sboard.set(row + 1, col, 0);

              checkNums(uboard, sboard, row + 1, col);
            } else {
              sboard.set(row + 1, col, uboard.get(row + 1, col));

            } // endif
          } // endif - center bottom
          if (sboard.get(row, col + 1) == null) {
            if (uboard.get(row, col + 1) == 0) {
              sboard.set(row, col + 1, 0);

              checkNums(uboard, sboard, row, col + 1);
            } else {
              sboard.set(row, col + 1, uboard.get(row, col + 1));

            } // endif
          } // endif - mid right
          if (sboard.get(row + 1, col + 1) == null) {
            if (uboard.get(row + 1, col + 1) == 0) {
              sboard.set(row + 1, col + 1, 0);

              checkNums(uboard, sboard, row + 1, col + 1);
            } else {
              sboard.set(row + 1, col + 1, uboard.get(row + 1, col + 1));

            } // endif
          } // endif - bottom right
        } // endif - top row
        // Special case of the bottom row
      } else if (row == sboard.height() - 1) {
        // Lower left corner
        if (col == 0) {
          if (sboard.get(row - 1, col) == null) {
            if (uboard.get(row - 1, col) == 0) {
              sboard.set(row - 1, col, 0);

              checkNums(uboard, sboard, row - 1, col);
            } else {
              sboard.set(row - 1, col, uboard.get(row - 1, col));

            } // endif
          } // endif - center top
          if (sboard.get(row, col + 1) == null) {
            if (uboard.get(row, col + 1) == 0) {
              sboard.set(row, col + 1, 0);

              checkNums(uboard, sboard, row, col + 1);
            } else {
              sboard.set(row, col + 1, uboard.get(row, col + 1));

            } // endif
          } // endif - mid right
          if (sboard.get(row - 1, col + 1) == null) {
            if (uboard.get(row - 1, col + 1) == 0) {
              sboard.set(row - 1, col + 1, 0);

              checkNums(uboard, sboard, row - 1, col + 1);
            } else {
              sboard.set(row - 1, col + 1, uboard.get(row - 1, col + 1));

            } // endif
          } // endif - upper right
          // Lower right corner
        } else if (col == sboard.width() - 1) {
          if (sboard.get(row - 1, col) == null) {
            if (uboard.get(row - 1, col) == 0) {
              sboard.set(row - 1, col, 0);

              checkNums(uboard, sboard, row - 1, col);
            } else {
              sboard.set(row - 1, col, uboard.get(row - 1, col));

            } // endif
          } // endif - center top
          if (sboard.get(row, col - 1) == null) {
            if (uboard.get(row, col - 1) == 0) {
              sboard.set(row, col - 1, 0);

              checkNums(uboard, sboard, row, col - 1);
            } else {
              sboard.set(row, col - 1, uboard.get(row, col - 1));

            } // endif
          } // endif - mid left
          if (sboard.get(row - 1, col - 1) == null) {
            if (uboard.get(row - 1, col + 1) == 0) {
              sboard.set(row - 1, col - 1, 0);

              checkNums(uboard, sboard, row - 1, col - 1);
            } else {
              sboard.set(row - 1, col - 1, uboard.get(row - 1, col + 1));

            } // endif
          } // endif - upper right
          /// Bottom row
        } else {
          if (sboard.get(row - 1, col) == null) {
            if (uboard.get(row - 1, col) == 0) {
              sboard.set(row - 1, col, 0);

              checkNums(uboard, sboard, row - 1, col);
            } else {
              sboard.set(row - 1, col, uboard.get(row - 1, col));

            } // endif
          } // endif - center top
          if (sboard.get(row, col + 1) == null) {
            if (uboard.get(row, col + 1) == 0) {
              sboard.set(row, col + 1, 0);

              checkNums(uboard, sboard, row, col + 1);
            } else {
              sboard.set(row, col + 1, uboard.get(row, col + 1));

            } // endif
          } // endif - mid right
          if (sboard.get(row - 1, col + 1) == null) {
            if (uboard.get(row - 1, col + 1) == 0) {
              sboard.set(row - 1, col + 1, 0);

              checkNums(uboard, sboard, row - 1, col + 1);
            } else {
              sboard.set(row - 1, col + 1, uboard.get(row - 1, col + 1));

            } // endif
          } // endif - upper right
          if (sboard.get(row, col - 1) == null) {
            if (uboard.get(row, col - 1) == 0) {
              sboard.set(row, col - 1, 0);

              checkNums(uboard, sboard, row, col - 1);
            } else {
              sboard.set(row, col - 1, uboard.get(row, col - 1));

            } // endif
          } // endif - mid left
          if (sboard.get(row - 1, col - 1) == null) {
            if (uboard.get(row - 1, col - 1) == 0) {
              sboard.set(row - 1, col - 1, 0);

              checkNums(uboard, sboard, row - 1, col - 1);
            } else {
              sboard.set(row - 1, col - 1, uboard.get(row - 1, col - 1));

            } // endif
          } // endif - upper left
        } // endif
        // Left col
      } else if (col == 0) {
        if (sboard.get(row - 1, col) == null) {
          if (uboard.get(row - 1, col) == 0) {
            sboard.set(row - 1, col, 0);

            checkNums(uboard, sboard, row - 1, col);
          } else {
            sboard.set(row - 1, col, uboard.get(row - 1, col));

          } // endif
        } // endif - center top
        if (sboard.get(row + 1, col) == null) {
          if (uboard.get(row + 1, col) == 0) {
            sboard.set(row + 1, col, 0);

            checkNums(uboard, sboard, row + 1, col);
          } else {
            sboard.set(row + 1, col, uboard.get(row + 1, col));

          } // endif
        } // endif - center bottom
        if (sboard.get(row - 1, col + 1) == null) {
          if (uboard.get(row - 1, col + 1) == 0) {
            sboard.set(row - 1, col + 1, 0);

            checkNums(uboard, sboard, row - 1, col + 1);
          } else {
            sboard.set(row - 1, col + 1, uboard.get(row - 1, col + 1));

          } // endif
        } // endif - upper right
        if (sboard.get(row, col + 1) == null) {
          if (uboard.get(row, col + 1) == 0) {
            sboard.set(row, col + 1, 0);

            checkNums(uboard, sboard, row, col + 1);
          } else {
            sboard.set(row, col + 1, uboard.get(row, col + 1));

          } // endif
        } // endif - mid right
        if (sboard.get(row + 1, col + 1) == null) {
          if (uboard.get(row + 1, col + 1) == 0) {
            sboard.set(row + 1, col + 1, 0);

            checkNums(uboard, sboard, row + 1, col + 1);
          } else {
            sboard.set(row + 1, col + 1, uboard.get(row + 1, col + 1));

          } // endif
        } // endif - bottom right
        // Right col
      } else if (col == sboard.width() - 1) {
        if (sboard.get(row - 1, col) == null) {
          if (uboard.get(row - 1, col) == 0) {
            sboard.set(row - 1, col, 0);

            checkNums(uboard, sboard, row - 1, col);
          } else {
            sboard.set(row - 1, col, uboard.get(row - 1, col));

          } // endif
        } // endif - center top
        if (sboard.get(row + 1, col) == null) {
          if (uboard.get(row + 1, col) == 0) {
            sboard.set(row + 1, col, 0);

            checkNums(uboard, sboard, row + 1, col);
          } else {
            sboard.set(row + 1, col, uboard.get(row + 1, col));

          } // endif
        } // endif - center bottom
        if (sboard.get(row - 1, col - 1) == null) {
          if (uboard.get(row - 1, col - 1) == 0) {
            sboard.set(row - 1, col - 1, 0);

            checkNums(uboard, sboard, row - 1, col - 1);
          } else {
            sboard.set(row - 1, col - 1, uboard.get(row - 1, col - 1));

          } // endif
        } // endif - upper left
        if (sboard.get(row, col - 1) == null) {
          if (uboard.get(row, col - 1) == 0) {
            sboard.set(row, col - 1, 0);

            checkNums(uboard, sboard, row, col - 1);
          } else {
            sboard.set(row, col - 1, uboard.get(row, col - 1));

          } // endif
        } // endif - mid left
        if (sboard.get(row + 1, col - 1) == null) {
          if (uboard.get(row + 1, col - 1) == 0) {
            sboard.set(row + 1, col - 1, 0);

            checkNums(uboard, sboard, row + 1, col - 1);
          } else {
            sboard.set(row + 1, col - 1, uboard.get(row + 1, col - 1));

          } // endif
        } // endif - bottom left
        // Literally every other case
      } else {
        if (sboard.get(row - 1, col) == null) {
          if (uboard.get(row - 1, col) == 0) {
            sboard.set(row - 1, col, 0);

            checkNums(uboard, sboard, row - 1, col);
          } else {
            sboard.set(row - 1, col, uboard.get(row - 1, col));

          } // endif
        } // endif - center top
        if (sboard.get(row + 1, col) == null) {
          if (uboard.get(row + 1, col) == 0) {
            sboard.set(row + 1, col, 0);

            checkNums(uboard, sboard, row + 1, col);
          } else {
            sboard.set(row + 1, col, uboard.get(row + 1, col));

          } // endif
        } // endif - center bottom
        if (sboard.get(row - 1, col - 1) == null) {
          if (uboard.get(row - 1, col - 1) == 0) {
            sboard.set(row - 1, col - 1, 0);

            checkNums(uboard, sboard, row - 1, col - 1);
          } else {
            sboard.set(row - 1, col - 1, sboard.get(row - 1, col - 1));

          } // endif
        } // endif - upper left
        if (sboard.get(row, col - 1) == null) {
          if (uboard.get(row, col - 1) == 0) {
            sboard.set(row, col - 1, 0);

            checkNums(uboard, sboard, row, col - 1);
          } else {
            sboard.set(row, col - 1, uboard.get(row, col - 1));

          } // endif
        } // endif - mid left
        if (sboard.get(row + 1, col - 1) == null) {
          if (uboard.get(row + 1, col - 1) == 0) {
            sboard.set(row + 1, col - 1, 0);

            checkNums(uboard, sboard, row + 1, col - 1);
          } else {
            sboard.set(row + 1, col - 1, uboard.get(row + 1, col - 1));

          } // endif
        } // endif - bottom left
        if (sboard.get(row - 1, col + 1) == null) {
          if (uboard.get(row - 1, col + 1) == 0) {
            sboard.set(row - 1, col + 1, 0);

            checkNums(uboard, sboard, row - 1, col + 1);
          } else {
            sboard.set(row - 1, col + 1, uboard.get(row - 1, col + 1));

          } // endif
        } // endif - upper right
        if (sboard.get(row, col + 1) == null) {
          if (uboard.get(row, col + 1) == 0) {
            sboard.set(row, col + 1, 0);

            checkNums(uboard, sboard, row, col + 1);
          } else {
            sboard.set(row, col + 1, uboard.get(row, col + 1));

          } // endif
        } // endif - mid right
        if (sboard.get(row + 1, col + 1) == null) {
          if (uboard.get(row + 1, col + 1) == 0) {
            sboard.set(row + 1, col + 1, 0);

            checkNums(uboard, sboard, row + 1, col + 1);
          } else {
            sboard.set(row + 1, col + 1, uboard.get(row + 1, col + 1));

          } // endif
        } // endif - bottom right
      } // endif
    } // endif
  } // checkNums(Matrix<Integer>, int, int)

  /**
   * @param sboard the board we are checking
   * @return the number of spaces that are null
   */
  public static int numNul(Matrix<Integer> sboard) {
    int numberOfNulls = 0;
    for (int i = 0; i < sboard.height(); i++) {
      for (int k = 0; k < sboard.width(); k++) {
        if (sboard.get(i, k) == null) {
          numberOfNulls++;
        } //if
      } //for
    } //for
    return numberOfNulls;
  } //numNul

  /**
   * The main part of the program that processes user input for the game.
   *
   * @param args The width and height to customize the board.
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    int defaultHeight = Underlying.HEIGHT_DEFAULT;
    int defaultWidth = Underlying.WIDTH_DEFAULT;
    PrintWriter pen = new PrintWriter(System.out, true);
    BufferedReader eyes = new BufferedReader(new InputStreamReader(System.in));

    int width = defaultWidth;
    int height = defaultHeight;

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
    Matrix<Integer> uboard = Underlying.setupBoard(width, height);
    Matrix<Integer> sboard = new MatrixV0<Integer>(width, height);
    for (int i = 0; i < sboard.height(); i++) {
      for (int k = 0; k < sboard.width(); k++) {
        sboard.set(i, k, null);
      } //for
    } // for

    // Run the game
    pen.println(sboard);
    int row = 0;
    int col = 0;
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
        } // endif
        row = Integer.parseInt(userInput.substring(0, space)); // try catch invalid input do ?
        col = Integer.parseInt(userInput.substring(space + 1));
        badInput = false;
      } catch (NumberFormatException e) {
        System.err.printf("You did not give me a number Try again");
        badInput = true;
        continue;

      } catch (Exception e) {
        System.err.printf("Some other exception. Print valid Numbers!");
        badInput = true;
        continue;
      } // catches

    } // try/catch

    boolean done = false;
    while (!done) {
      if (hasBomb(row, col, uboard)) {
        done = true;
        printResults(pen, uboard);
        pen.println("You died :'( ");
        return;
      } else {
        checkNums(uboard, sboard, row, col);
        int nullsInBoard = numNul(sboard);
        if (nullsInBoard == (width * height) / 6) {
          printResults(pen, uboard);
          pen.println("YOU WON!!!! :O  :) ");
          return;
        } //endif
        Matrix.print(pen, sboard, true);
        pen.println("Good job, you didn't die :) now hit return then pick another box.");
        badInput = true;
        userInput = eyes.readLine();
        while (badInput) {
          try {
            userInput = eyes.readLine();
            space = userInput.indexOf(" ");
            if (space == -1) {
              System.err
                  .println("Incorrect number of inputs please try again. Please do -> ROW COL");
              continue;
            } // endif
            row = Integer.parseInt(userInput.substring(0, space)); // try catch invalid input do ?
            col = Integer.parseInt(userInput.substring(space + 1));
            badInput = false;
          } catch (NumberFormatException e) {
            System.err.printf("You did not give me a number Try again");
            badInput = true;
            continue;

          } catch (Exception e) {
            System.err.printf("Some other exception. Print valid Numbers!");
            badInput = true;
            continue;
          } // catches
        } // try/catch
      } // endif
    } // while
  } // main(String[])
} // class MinesweepVisible
