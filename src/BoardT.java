/**
 * @file: BoardT.java
 * @Author: Qiang Gao   gaoq20
 * @Date: March.31th, 2021
 * @Description: generate a board for initiating and playing the game
 */

package src;

// Import java libraries
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class BoardT {

    private int scores;
    private Cell[][] board;
    private boolean isAdd;

    /**
     * @brief constructor for BoardT object
     * @details generates a 4x4 board with 2 random values and 0s
     */
    public BoardT(){
        this.scores = 0;
        this.board = new Cell[4][4];
        for(int a = 0; a < 4; a++){
            for (int b = 0; b < 4; b++){
                this.board[a][b] = new Cell(0);
            }
        }
        //create two random numbers
        isAdd = true;
        createNew(this.board);
        isAdd = true;
        createNew(this.board);
    }

    private List<Cell> getEmpty(Cell[][] board){
        List<Cell> emptyList = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (board[i][j].getValue() == 0){
                    emptyList.add(board[i][j]);
                }
            }
        }
        return emptyList;
    }

    private void createNew(Cell[][] board){
        List<Cell> list = getEmpty(board);
        if (!list.isEmpty() && isAdd){
            Random random = new Random();
            int index = random.nextInt(list.size());
            //Chance for appearance of 2 is 25%.Chance for appearance of 4 is 75%
            int chance = random.nextInt(100);
            if (chance < 25) {
                list.get(index).setValue(2);
            }
            else {
                list.get(index).setValue(4);
            }
            isAdd = false;
        }
        int k = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (board[i][j].getValue() == 0){
                    board[i][j] = list.get(k);
                    k++;
                }
            }
        }

    }
    /**
     * @brief get current scores
     * @return scores
     */
    public int getScores() {
        return scores;
    }
    /**
     * @brief get current board
     * @return board
     */
    public Cell[][] getBoard(){
        return board;
    }
    /**
     * @brief check if game is over
     * @details true if values for all Cell in board are not zero and there is no cell whose value
     * is as same as its left Cell or its right Cell or its unpper Cell or its lower Cell.
     */
    public boolean ifGameOver(){
        if (!getEmpty(this.board).isEmpty()){
            return false;
        }
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (this.board[i][j].getValue() == this.board[i][j+1].getValue()
                        || this.board[i][j].getValue() == this.board[i+1][j].getValue()){
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * @brief check if winning
     * @details true if 2048 appears
     */
    public boolean ifWin(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (this.board[i][j].getValue() == 2048){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @brief Move right action
     * @details If there are Cells whose value is 0, all the Cells on their left whose value is not 0 will be moved one position to the right. For any Cell in the board,
     * if the value of its left most Cell whose value is not 0 is as same as the Cell itself, these values for the two Cells would be added to the right Cell. If a Cell whose value is 0 appears, steps repeat.
     */
    public void MoveRight(){
        boolean merge = false;
        for (int i = 0; i < 4; ++i){
            for (int j = 3; j >= 0; --j){
                for (int k = j - 1; k >= 0; --k){
                    if (this.board[i][k].getValue() > 0){
                        if (this.board[i][j].getValue() == 0){
                            this.board[i][j].setValue(this.board[i][k].getValue());
                            this.board[i][k].setValue(0);
                            j++;
                            merge = true;
                        }
                        else if(this.board[i][j].getValue() == this.board[i][k].getValue()){
                            this.board[i][j].setValue(this.board[i][k].getValue() * 2);
                            this.board[i][k].setValue(0);
                            scores += this.board[i][j].getValue();
                            merge = true;
                        }
                    }
                }
            }
        }
        if (merge){
            createNew(this.board);
        }
    }

    /**
     * @brief Move left action
     * @details If there are Cells whose value is 0, all the Cells on their right whose value is not 0 will be moved one position to the left. For any Cell in the board,
     * if the value of its right most Cell whose value is not 0 is as same as the Cell itself, these values for the two Cells would be added to the left Cell. If a Cell whose value is 0 appears, steps repeat.
     */
    public void MoveLeft(){
        boolean merge = false;
        for (int i = 0; i < 4; ++i){
            for (int j = 0; j < 4; ++j){
                for (int k = j + 1; k < 4; ++k){
                    if (this.board[i][k].getValue() > 0){
                        if (this.board[i][j].getValue() == 0){
                            this.board[i][j].setValue(this.board[i][k].getValue());
                            this.board[i][k].setValue(0);
                            j--;
                            merge = true;
                        }
                        else if(this.board[i][j].getValue() == this.board[i][k].getValue()){
                            this.board[i][j].setValue(this.board[i][k].getValue() * 2);
                            this.board[i][k].setValue(0);
                            scores += this.board[i][j].getValue();
                            merge = true;
                        }
                    }
                }
            }
        }
        if (merge){
            createNew(this.board);
        }
    }

    /**
     * @brief Move left action
     * @details If there are Cells whose value is 0, all the lower Cells whose value is not 0 will be moved one position up. For any Cell in the board,
     * if the value of its lower most Cell whose value is not 0 is as same as the Cell itself, these values for the two Cells would be added to the upper Cell. If a Cell whose value is 0 appears, steps repeat.
     */
    public void MoveUp(){
        boolean merge = false;
        for (int j = 0; j < 4; ++j){
            for (int i = 0; i < 4; ++i){
                for (int k = i + 1; k < 4; ++k){
                    if (this.board[k][j].getValue() > 0){
                        if (this.board[i][j].getValue() == 0){
                            this.board[i][j].setValue(this.board[k][j].getValue());
                            this.board[k][j].setValue(0);
                            j--;
                            merge = true;
                        }
                        else if(this.board[i][j].getValue() == this.board[k][j].getValue()){
                            this.board[i][j].setValue(this.board[k][j].getValue() * 2);
                            this.board[k][j].setValue(0);
                            scores += this.board[i][j].getValue();
                            merge = true;
                        }
                    }
                }
            }
        }
        if (merge){
            createNew(this.board);
        }
    }

    /**
     * @brief Move left action
     * @details If there are Cells whose value is 0, all the upper Cells whose value is not 0 will be moved one position down. For any Cell in the board,
     * if the value of its upper most Cell whose value is not 0 is as same as the Cell itself, these values for the two Cells would be added to the lower Cell. If a Cell whose value is 0 appears, steps repeat.
     */
    public void MoveDown(){
        boolean merge = false;
        for (int j = 0; j < 4; ++j){
            for (int i = 3; i >= 0; --i){
                for (int k = i - 1; k >= 0; --k){
                    if (this.board[k][j].getValue() > 0){
                        if (this.board[i][j].getValue() == 0){
                            this.board[i][j].setValue(this.board[k][j].getValue());
                            this.board[k][j].setValue(0);
                            i++;
                            merge = true;
                        }
                        else if(this.board[i][j].getValue() == this.board[k][j].getValue()){
                            this.board[i][j].setValue(this.board[k][j].getValue() * 2);
                            this.board[k][j].setValue(0);
                            scores += this.board[i][j].getValue();
                            merge = true;
                        }
                    }
                }
            }
        }
        if (merge){
            createNew(this.board);
        }
    }


}

