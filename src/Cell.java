/**
 * @file: Cell.java
 * @Author: Qiang Gao   gaoq20
 * @Date: March.31th, 2021
 * @Description: defines cells
 */

package src;

public class Cell {

    private int value;
    /**
     * @brief create a Cell object
     * @param v the value of the Cell object
     * @throws IllegalArgumentException - if the value is not 0 or 2 or 4 or 8 or 16 or 32 or 64 or 128 or 256 or 512 or 1024 or 2048
     */
    public Cell(int v){
        if (!(v == 2 || v == 4 || v == 8 || v == 16 || v == 32 || v == 64 || v == 128
                || v == 256 || v == 512 || v == 1024 || v == 2048 || v == 0)){
            throw new IllegalArgumentException();
        }
        this.value = v;
    }

    /**
     * @brief get the value of the Cell object
     * @return value
     */
    public int getValue(){
        return this.value;
    }

    /**
     * @brief Set the value for a Cell object
     * @param v the value
     * @throws IllegalArgumentException - if the value is not 0 or 2 or 4 or 8 or 16 or 32 or 64 or 128 or 256 or 512 or 1024 or 2048
     */
    public void setValue(int v){
        if (!(v == 2 || v == 4 || v == 8 || v == 16 || v == 32 || v == 64 || v == 128
                || v == 256 || v == 512 || v == 1024 || v == 2048 || v == 0)){
            throw new IllegalArgumentException();
        }
        this.value = v;
    }

}
