/**
 * @file: UserInterface.java
 * @Author: Qiang Gao   gaoq20
 * @Date: March.31th, 2021
 * @Description: view module that displays the status of the game
 */

package src;

public class UserInterface{

    private static UserInterface visual = null;

    /**
     * @brief constructor
     */
    private UserInterface(){}

    /**
     * @brief public static method for obtaining a single instance
     * @return an UserInterface object
     */
    public static UserInterface getInstance(){
        if (visual == null)
            return visual = new UserInterface();
        return visual;
    }

    /**
     * @brief display a welcome message
     */
    public void printWelcomeMessage(){
        System.out.println("-------------------------------------------------");
        System.out.println("                 Welcome to 2048                ");
        System.out.println("-------------------------------------------------");
    }

    /**
     * @brief display the game objective
     * @param message - the game objective (different based on objective) retrived from the model
     */
    public void printCondition(String message){
        System.out.println(message);
    }

    /**
     * @brief Display the board on the screen
     * @param boardT - the game board
     */
    public void printBoard(BoardT boardT){
        Cell[][] matrix = boardT.getBoard();
        String s = "";
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (j == 3){
                    s += Integer.toString(matrix[i][j].getValue()) + "\n";
                }
                else {
                    s += Integer.toString(matrix[i][j].getValue()) + "    ";
                }
            }

        }
        System.out.println(s);
    }

    /**
     * @brief display an ending message after exiting the game
     */
    public void printEndingMessage(){
        System.out.println("-------------------------------------------------");
        System.out.println("             Thank You For Playing !!!           ");
        System.out.println("-------------------------------------------------");
    }
}