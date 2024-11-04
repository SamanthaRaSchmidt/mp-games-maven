package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Jenifer Silva
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

      int height;
      int width;
      T givenValue;
      T[][] contents;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+
  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   * @param def
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height, T def) {
    if(width < 0 || height < 0 ){
      throw new NegativeArraySizeException("Can't have a negative width or height");
    }
    this.width = width;
    this.height = height;
    this.givenValue = def;
    this.contents = (T[][]) new Object[height][width];
    for(int i =0; i < height; i++){
      for(int j = 0; j < width; j++){
      this.contents[i][j] = this.givenValue;
      }
    }

  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    if((row<0 || row>=height) || (col < 0 || col >= width)){
      throw new IndexOutOfBoundsException("Index is out of bounds");
    }
    else{
      return contents[row][col];
    }
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    if((row < 0 || row >= this.height) || (col < 0 || col >= this.width)){
      throw new IndexOutOfBoundsException();
    }
    else{
      contents[row][col] = val;
    }
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.height;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.width;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  public void insertRow(int row) {
    if(row < 0 || row > height){
      throw new IndexOutOfBoundsException();
    }
    T[][] updatedArray = (T[][]) new Object[height+1][width];

    for(int a = 0; a < row; a++){
      for(int b = 0; b < this.width; b++){
        updatedArray[a][b] = contents[a][b];
      }
    }

      for(int j = 0; j < this.width; j++){
      updatedArray[row][j] = this.givenValue;
    }

    for(int c = row+1; c < updatedArray.length; c++){
      for(int d = 0; d < this.width; d++){
        updatedArray[c][d] = contents[c-1][d];
      }
    }
    this.contents = updatedArray;
    height = height + 1;
  }

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if(row < 0 || row > height){
      throw new IndexOutOfBoundsException();
    }
    T[][] updatedArray = (T[][]) new Object[height+1][width];
    T[] valuesToInsert = vals;
    if(valuesToInsert.length != this.width){
      throw new ArraySizeException();
    }

    for(int a = 0; a < row; a++){
      for(int b = 0; b < this.width; b++){
        updatedArray[a][b] = contents[a][b];
      }
    }

      for(int j = 0; j < this.width; j++){
      updatedArray[row][j] = valuesToInsert[j];
    }

    for(int c = row+1; c < updatedArray.length; c++){
      for(int d = 0; d < this.width; d++){
        updatedArray[c][d] = contents[c-1][d];
      }
    }
    this.contents = updatedArray;
    height = height + 1;
  }// insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    if(col < 0 || col > width){
      throw new IndexOutOfBoundsException();
    }
    T[][] updatedArray = (T[][]) new Object[height][width+1];

    for(int a = 0; a < this.height; a++){
      for(int b = 0; b < col; b++){
        updatedArray[a][b] = contents[a][b];
      }
    }

    for(int j = 0; j < this.height; j++){
      updatedArray[j][col] = this.givenValue;
    }

    for(int c = 0; c < this.height; c++){
      for(int d = col+1; d < this.width+1; d++){
        updatedArray[c][d] = contents[c][d-1];
      }
    }
    this.contents = updatedArray;
    width = width + 1;
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if(col < 0 || col > width){
      throw new IndexOutOfBoundsException();
    }
    T[][] updatedArray = (T[][]) new Object[height][width+1];
    T[] valuesToInsert = vals;
    if(valuesToInsert.length != this.height){
      throw new ArraySizeException();
    }

    for(int a = 0; a < this.height; a++){
      for(int b = 0; b < col; b++){
        updatedArray[a][b] = contents[a][b];
      }
    }

    for(int j = 0; j < this.height; j++){
      updatedArray[j][col] = valuesToInsert[j];
    }

    for(int c = 0; c < this.height; c++){
      for(int d = col+1; d < this.width+1; d++){
        updatedArray[c][d] = contents[c][d-1];
      }
    }
    this.contents = updatedArray;
    width = width + 1;
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  public void deleteRow(int row) {
    if(row < 0 || row >= this.height){
      throw new IndexOutOfBoundsException();
    }
    T[][] deleteRowArray = (T[][]) new Object[height-1][width];
    
    for(int a = 0; a < row; a++){
      for(int b = 0; b < this.width; b++){
        deleteRowArray[a][b] = contents[a][b];
      }
    }

    for(int c = row; c < this.height-1; c++){
      for(int d = 0; d < this.width; d++){
        deleteRowArray[c][d] = contents[c+1][d];
      }
    }
    this.contents = deleteRowArray;
    height = height - 1;



  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  public void deleteCol(int col) {
    if(col < 0 || col >= this.width){
      throw new IndexOutOfBoundsException();
    }
    T[][] deleteColArray = (T[][]) new Object[height][width-1];


    for(int a = 0; a < this.height; a++){
      for(int b = 0; b < col; b++){
        deleteColArray[a][b] = contents[a][b];
      }
    }

    for(int c = 0; c < this.height; c++){
      for(int d = col; d < this.width - 1; d++){
        deleteColArray[c][d] = contents[c][d+1];
      }
    }
    this.contents = deleteColArray;
    width = width - 1;
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {
        if((startRow < 0 || endRow > this.height) || (startCol < 0 || endCol > this.width)){
          throw new IndexOutOfBoundsException();
        }

        for(int i = startRow; i < endRow; i++){
          for(int j = startCol; j < endCol; j++){
            contents[i][j] = val;
          }
        }
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
        if((startRow < 0 || endRow > this.height) || (startCol < 0 || endCol > this.width)){
          throw new IndexOutOfBoundsException();
        }
        int deltaTimes = 0;
        int indexRow = 0;
        int indexCol = 0;
        while(indexRow < endRow && indexCol < endCol){
            indexRow = startRow + (deltaTimes * deltaRow);
            indexCol= startCol + (deltaTimes * deltaCol);
            if(indexRow < endRow && indexCol < endCol){
            contents[indexRow][indexCol] = val;
            deltaTimes++;
            }
          }
        } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix clone() {
    Matrix<T> matrixClone = new MatrixV0(this.height, this.width);
    for(int i = 0; i < this.height; i++){
      for(int k  = 0; i< this.width; k++){
        T val= this.get(i,k);
        matrixClone.set(i, k, val);
      }
    }

    return matrixClone; 
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  public boolean equals(Object other) {
    boolean height = false;
    boolean width = false;
    //boolean val = false;
    if(!(other instanceof Matrix)){
      return false;
    }
    if(this.height != ((Matrix) other).height()){
      return false;
    }
    if(this.width != ((Matrix) other).width()){
      return false;
    }
    for(int i = 0; i < this.height; i++){
      for(int k  = 0; k< this.width; k++){
        T curval= this.get(i,k);
        T otherval = (T)((Matrix) other).get(i, k);
        if(!curval.equals((otherval))){
          return false;
        }
      }
    }
      return true;
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
