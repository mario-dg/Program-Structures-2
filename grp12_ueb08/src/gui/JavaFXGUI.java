package gui;

import logic.GUIConnector;
import logic.GameTicTacToe.Symbol;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * This class is responsible for changing the gui when the logic deems it necessary. Created by the
 * gui and then passed as a parameter into the logic.
 * <br>
 * Addtional private or protected methods may be added to this.
 *
 * @author cei
 */
public class JavaFXGUI implements GUIConnector {

    /**
     * The buttons of the game field stored in an array (position in the array = position on the
     * surface).
     */
    private final Button[][] btnsField;

    // Further attributes of the GUI
    private final Label lblCurrPlayer;
    private final Label lblWinner;
    private final GridPane grdPane;

    /**
     * The constructor. Gets passed all components of the gui that may change due to actions in the
     * logic.
     *
     * @param btns the buttons of the game field (can change their text to the symbols of the
     * players)
     * <br>
     * @param lblCurrPlayer
     * @param lblWinner
     * @param grdPane
     */
    public JavaFXGUI(Button[][] btns, Label lblCurrPlayer, Label lblWinner, GridPane grdPane) {
        this.btnsField = btns;
        this.lblCurrPlayer = lblCurrPlayer;
        this.lblWinner = lblWinner;
        this.grdPane = grdPane;
    }

    /**
     * Displays the correct player symbol
     * @param coord, coordinates of the clicked Button  
     * @param symbol, symbol (X,O) that is displayed in the button
     */
    @Override
    public void displaySymbol(int[] coord, Symbol symbol) {
        assert coord != null && coord.length == 2;
        btnsField[coord[0]][coord[1]].setText(symbol.toString());
    }

    /**
     * Shows which players turn it is
     * @param name Name of the player
     * @param symbol Symbol of the player in this game
     */
    @Override
    public void setCurrentPlayer(String name, Symbol symbol) {
        lblCurrPlayer.setText(name + " (" + symbol + ")");
    }

    /**
     * Handles what happens in case the game ends
     * @param winnerName Winner of the game 
     */
    @Override
    public void onGameEnd(String winnerName) {
        if (!(winnerName == null)) {
            this.lblWinner.setText("Winner is " + winnerName);
        } else {
            this.lblWinner.setText("It's a tie");
        }
        this.lblWinner.setVisible(true);
        this.grdPane.setDisable(true);
        this.lblCurrPlayer.setVisible(false);
    }

}
