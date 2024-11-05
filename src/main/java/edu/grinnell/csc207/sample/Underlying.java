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

  public static void setBomb(Matrix<Integer> board, Integer val) {
    int count = 0;

    for(int i = 0; i < board.height(); i++) {
        count++;
        for(int j = 0; j < board.width(); j++) {
            count++;
            if (count == val) {
                board.set(i, j, 100);
            } //endif
        } // endfor
        if (count == val) {
            board.set(i, j, 100);
        } //endif
    } //endfor
  } // setBomb(Matrix<Integer>, Integer)


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

        Integer[] array = new Integer[totalSquares / 4];
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
            setBomb(board, array[i]);
        } //for 


        return board; // STUB
    } // setupBoard(int, int)
    
} // class Underlying
