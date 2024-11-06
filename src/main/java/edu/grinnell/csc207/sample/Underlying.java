package edu.grinnell.csc207.sample;

import edu.grinnell.csc207.util.ArrayUtils;
import edu.grinnell.csc207.util.IOUtils;
import edu.grinnell.csc207.util.Matrix;
import edu.grinnell.csc207.util.MatrixV0;
import java.util.function.Predicate;

import java.util.Random;


public class Underlying {
      // +----------------+----------------------------------------------
  // | Static methods |
  // +----------------+

  /*
   * The default width.
   */
  static final int WIDTH_DEFAULT = 10;
  
  /*
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

  public static boolean searchArray(Integer[] arr, int val) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == val) {
            return true;
        } //endif
    } //endfor
    return false;
  } //searchArray(int[], int)

    public static boolean isFull(Integer[] arr) {
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == null){
                return false;
            } //endif
        } //endfor
        return true;
    } // isFull(Integer[])

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Checks the 8 (or less if an edge square) surrounding squares for mines to calculate the number.
   * @param board
   *    The matrix with mines placed.
   * @param row
   *    The row of the square to be checked.
   * @param col
   *    The col of the square to be checked.
   */
  public static void checkMines(Matrix<Integer> board, int row, int col) {
    Integer numMines = 0;
    if(board.get(row, col) == null) {
      if (row == 0) {
        if(col == 0) {
          if (board.get(row + 1, col) == 100) {
            numMines++;
          } //endif
          if (board.get(row + 1, col + 1) == 100) {
            numMines++;
          } //endif
          if (board.get(row, col + 1) == 100) {
            numMines++;
          } //endif
          board.set(row, col, numMines);
        } else if(col == board.width()) {
          if (board.get(row, col - 1) == 100) {
            numMines++;
          } //endif
          if (board.get(row + 1, col - 1) == 100) {
            numMines++;
          } //endif
          if (board.get(row + 1, col) == 100) {
            numMines++;
          } //endif
          board.set(row, col, numMines);
        } else {
          if (board.get(row - 1, col) == 100) {
            numMines++;
          } //endif
          if (board.get(row - 1, col + 1) == 100) {
            numMines++;
          } //endif
          if (board.get(row, col +1) == 100) {
            numMines++;
          } //endif
          if (board.get(row + 1, col) == 100) {
            numMines++;
          } //endif
          if (board.get(row + 1, col + 1) == 100) {
            numMines++;
          } //endif
          board.set(row, col, numMines);
        } //endif
      } else if(row == board.height()) {
        if(col == 0) {
          if (board.get(row - 1, col) == 100) {
            numMines++;
          } //endif
          if (board.get(row - 1, col + 1) == 100) {
            numMines++;
          } //endif
          if (board.get(row, col + 1) == 100) {
            numMines++;
          } //endif
          board.set(row, col, numMines);
        } else if(col == board.width()) {
          if (board.get(row - 1, col) == 100) {
            numMines++;
          } //endif
          if (board.get(row - 1, col - 1) == 100) {
            numMines++;
          } //endif
          if (board.get(row, col) == 100) {
            numMines++;
          } //endif
          board.set(row, col, numMines);
        } else {
          if (board.get(row - 1, col) == 100) {
            numMines++;
          } //endif
          if (board.get(row - 1, col - 1) == 100) {
            numMines++;
          } //endif
          if (board.get(row, col - 1) == 100) {
            numMines++;
          } //endif
          if (board.get(row + 1, col) == 100) {
            numMines++;
          } //endif
          if (board.get(row + 1, col + 1) == 100) {
            numMines++;
          } //endif
          board.set(row, col, numMines);
        } //endif
        } else if(col == 0) {
          if (board.get(row, col - 1) == 100) {
            numMines++;
          } //endif
          if (board.get(row + 1, col - 1) == 100) {
            numMines++;
          } //endif
          if (board.get(row - 1, col) == 100) {
            numMines++;
          } //endif
          if (board.get(row - 1, col + 1) == 100) {
            numMines++;
          } //endif
          if (board.get(row, col + 1) == 100) {
            numMines++;
          } //endif
          board.set(row, col, numMines);
        } else if(col == board.width()) {
          if (board.get(row, col - 1) == 100) {
            numMines++;
          } //endif
          if (board.get(row - 1, col - 1) == 100) {
            numMines++;
          } //endif
          if (board.get(row + 1, col) == 100) {
            numMines++;
          } //endif
          if (board.get(row + 1, col + 1) == 100) {
            numMines++;
          } //endif
          if (board.get(row, col + 1) == 100) {
            numMines++;
          } //endif
          board.set(row, col, numMines);
        } else {
         for (int i = 0; i < 3; i++) {
            if (board.get(row - 1, (col - 1) + i) == 100) {
                numMines++;
            } //endif
        } //for

        } //for

        for (int i = 0; i < 3; i++) {
            if (board.get(row + 1, (col - 1) + i) == 100) {
                numMines++;
            } //endif
        } //for

        if (board.get(row, (col - 1)) == 100) {
            numMines++;
        } //endif

        if (board.get(row, (col - 1)) == 100) {
            numMines++;
        } //endif

        board.set(row, col, numMines);
      } //endif block
  } // checkMines(Matrix<Integer>, int, int)



  public static void setMine(Matrix<Integer> board, Integer val) {
    int row = val / board.height();
    int col = val / board.width();
    if (col == 0){
        col = board.width()-1;
    } //endif
    board.set(row, col, 100);
    } // setMine(Matrix<Integer>, Integer)


    /**
     * Sets up the board with randomized bombs.
     * @param width
     *      The width of the gameboard.
     * @param height
     *      The height of the game board.
     * @return the randomized matrix board.
     */
    static Matrix<String> setupBoard(int width, int height) {
        Matrix<Integer> board = new MatrixV0<Integer>(width, height, 0);

        int totalSquares = width * height;

        Integer[] array = new Integer[totalSquares / 6];
        while(!isFull(array)){
            for(int i = 0; i < array.length; i++) {
                Random rand = new Random();
                int number = rand.nextInt(totalSquares);
                if(!searchArray(array, number)) {
                    array[i] = number;
                } else {
                    i--;
                } //endif
            } //for
        } //while

        for (int i = 0; i < 0; i++) {
            setMine(board, array[i]);
        } //for 


        return board; // STUB
    } // setupBoard(int, int)
    
} // class Underlying
