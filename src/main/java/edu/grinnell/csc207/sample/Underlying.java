package edu.grinnell.csc207.sample;

import edu.grinnell.csc207.util.Matrix;
import edu.grinnell.csc207.util.MatrixV0;

import java.util.Random;

/**
 * Creates a matrix that will not be shown. This will have the randomly generated
 * mine placement and calculate the number of mines around a cell.
 */
public class Underlying {
  // +----------------+----------------------------------------------
  // | Static methods |
  // +----------------+

  /**
   * The default width.
   */
  static final int WIDTH_DEFAULT = 10;

  /**
   * The default height.
   */
  static final int HEIGHT_DEFAULT = 10;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  // +-------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  // +----------------+----------------------------------------------
  // | Helper methods |
  // +----------------+

  /**
   * Searches the array for an equal value.
   * @param arr
   *  The array to search through.
   * @param val
   *  The value searching for.
   * @return true if found, false otherwise.
   */
  public static boolean searchArray(Integer[] arr, int val) {
    for (int i = 0; i < arr.length; i++) {
      if(arr[i] == null) {
        return false;
      } else if (arr[i] == val) {
        return true;
      } // endif
    } // endfor
    return false;
  } // searchArray(int[], int)

  /**
   * Checks if the array is full.
   * @param arr
   *  The array to check.
   * @return true if full, false otherwise.
   */
  public static boolean isFull(Integer[] arr) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == null) {
        return false;
      } // endif
    } // endfor
    return true;
  } // isFull(Integer[])

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Checks the 8 (or less if an edge square) surrounding squares for mines to
   * calculate the number.
   * @param board
   *              The matrix with mines placed.
   * @param row
   *              The row of the square to be checked.
   * @param col
   *              The col of the square to be checked.
   */
  public static void checkMines(Matrix<Integer> board, int row, int col) {
    Integer numMines = 0;
    if (board.get(row, col) == 9) {
      // Special case of the top edge
      if (row == 0) {
        if (col == 0) {
          if (board.get(row + 1, col) == 100) {
            numMines++;
          } // endif
          if (board.get(row + 1, col + 1) == 100) {
            numMines++;
          } // endif
          if (board.get(row, col + 1) == 100) {
            numMines++;
          } // endif
          board.set(row, col, numMines);
        } else if (col == board.width() - 1) {
          if (board.get(row, col - 1) == 100) {
            numMines++;
          } // endif
          if (board.get(row + 1, col - 1) == 100) {
            numMines++;
          } // endif
          if (board.get(row + 1, col) == 100) {
            numMines++;
          } // endif
          board.set(row, col, numMines);
        } else {
          if (board.get(row, col - 1) == 100) {
            numMines++;
          } // endif
          if (board.get(row + 1, col - 1) == 100) {
            numMines++;
          } // endif
          if (board.get(row + 1, col) == 100) {
            numMines++;
          } // endif
          if (board.get(row + 1, col + 1) == 100) {
            numMines++;
          } // endif
          if (board.get(row, col + 1) == 100) {
            numMines++;
          } // endif
          board.set(row, col, numMines);
        } // endif
      }  else if (row == board.height() - 1) {
        if (col == 0) {
          if (board.get(row - 1, col) == 100) {
            numMines++;
          } // endif
          if (board.get(row - 1, col + 1) == 100) {
            numMines++;
          } // endif
          if (board.get(row, col + 1) == 100) {
            numMines++;
          } // endif
          board.set(row, col, numMines);
        } else if (col == board.width() - 1) {
          if (board.get(row - 1, col) == 100) {
            numMines++;
          } // endif
          if (board.get(row - 1, col - 1) == 100) {
            numMines++;
          } // endif
          if (board.get(row, col - 1) == 100) {
            numMines++;
          } // endif
          board.set(row, col, numMines);
        } else {
          if (board.get(row - 1, col) == 100) {
            numMines++;
          } // endif
          if (board.get(row - 1, col - 1) == 100) {
            numMines++;
          } // endif
          if (board.get(row, col - 1) == 100) {
            numMines++;
          } // endif
          if (board.get(row, col + 1) == 100) {
            numMines++;
          } // endif
          if (board.get(row - 1, col + 1) == 100) {
            numMines++;
          } // endif
          board.set(row, col, numMines);
        } // endif
        // Left col
      } else if (col == 0) {
        if (board.get(row - 1, col) == 100) {
          numMines++;
        } // endif
        if (board.get(row - 1, col + 1) == 100) {
          numMines++;
        } // endif
        if (board.get(row, col + 1) == 100) {
          numMines++;
        } // endif
        if (board.get(row + 1, col + 1) == 100) {
          numMines++;
        } // endif
        if (board.get(row + 1, col) == 100) {
          numMines++;
        } // endif
        board.set(row, col, numMines);
        // Right col
      } else if (col == board.width() - 1) {
        if (board.get(row - 1, col) == 100) {
          numMines++;
        } // endif
        if (board.get(row - 1, col - 1) == 100) {
          numMines++;
        } // endif
        if (board.get(row, col - 1) == 100) {
          numMines++;
        } // endif
        if (board.get(row + 1, col - 1) == 100) {
          numMines++;
        } // endif
        if (board.get(row + 1, col) == 100) {
          numMines++;
        } // endif
        board.set(row, col, numMines);
      } // Literally every other case
       else {
        for (int i = 0; i < 3; i++) {
          if (board.get(row - 1, ((col - 1) + i)) == 100) {
            numMines++;
          } // endif
        } // for
      for (int i = 0; i < 3; i++) {
        if (board.get(row + 1, ((col - 1) + i)) == 100) {
          numMines++;
        } // endif
      } // for

      if (board.get(row, (col - 1)) == 100) {
        numMines++;
      } // endif

      if (board.get(row, (col + 1)) == 100) {
        numMines++;
      } // endif

      board.set(row, col, numMines);
    } // endif block
  } } // checkMines(Matrix<Integer>, int, int)

  /**
   * Converts an Integer to a row and col to add mines to the board.
   * @param board
   *  The underlying board the mines will be set in.
   * @param val
   *  The random Integer that will be turned into a row and col.
   */
  public static void setMine(Matrix<Integer> board, Integer val) {
    int row = val / board.height();
    int col = val % board.width();
    if (col == 0) {
      col = board.width() - 1;
    } // endif
    board.set(row, col, 100);
  } // setMine(Matrix<Integer>, Integer)

  /**
   * Sets up the board with randomized bombs.
   *
   * @param width
   *               The width of the gameboard.
   * @param height
   *               The height of the game board.
   * @return the randomized matrix board.
   */
  static Matrix<Integer> setupBoard(int width, int height) {
    Matrix<Integer> board = new MatrixV0<Integer>(width, height, 9);

    int totalSquares = width * height;

    Integer[] array = new Integer[totalSquares / 6];
    while (!isFull(array)) {
      for (int i = 0; i < array.length; i++) {
        Random rand = new Random();
        int number = rand.nextInt(totalSquares);
        if (!searchArray(array, number)) {
          array[i] = number;
        } else {
          i--;
        } // endif
      } // for
    } // while

    for (int i = 0; i < array.length; i++) {
      setMine(board, array[i]);
    } // for

    for (int i = 0; i < totalSquares; i++) {
      int row = i / board.height();
      int col = i % board.width();
      checkMines(board, row, col);
    } // for

    return board;
  } // setupBoard(int, int)

} // class Underlying
