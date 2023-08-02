package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logic.GameTicTacToe;
import logic.GameTicTacToe.Symbol;
/**
 *
 * @author Nutzer
 */
public class FXMLDocumentController implements Initializable {

    /**
     * All GUI elements
     */
    @FXML
    private GridPane grdPane;
    @FXML
    private Button btn00;
    @FXML
    private Button btn10;
    @FXML
    private Button btn20;
    @FXML
    private Button btn01;
    @FXML
    private Button btn11;
    @FXML
    private Button btn21;
    @FXML
    private Button btn02;
    @FXML
    private Button btn12;
    @FXML
    private Button btn22;
    @FXML
    private Button btnNewGame;
    @FXML
    private Button btnStartGame;
    @FXML
    private Label lblCurrPlayer;
    @FXML
    private Label lblWinner;
    @FXML
    private TextField txtfldNamePlayer1;
    @FXML
    private TextField txtfldNamePlayer2;

    private GameTicTacToe game;

    private final int FIELD_SIZE = 3;
    //private final Button[][] BTNS_GAME_PLAY = {{btn00, btn01, btn02}, {btn10, btn11, btn12}, {btn20, btn21, btn22}};


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblWinner.setVisible(false);
        lblCurrPlayer.setVisible(false);
        Button[][] btnsGamePlay = {{btn00, btn01, btn02}, {btn10, btn11, btn12}, {btn20, btn21, btn22}};
        for (int x = 0; x < FIELD_SIZE; x++) {
            for (int y = 0; y < FIELD_SIZE; y++) {
                GridPane.setColumnIndex(btnsGamePlay[x][y], x);
                GridPane.setRowIndex(btnsGamePlay[x][y], y);
            }
        }
    }

    /**
     * Gameplay button click
     * @param event, Button click
     */
    @FXML
    private void handleButtonGameplay(ActionEvent event) {
        game.playerTurn(new int[]{GridPane.getColumnIndex((Button) event.getSource()),
            GridPane.getRowIndex((Button) event.getSource())});
    }

    /**
     * Sets up a new game
     * @param event, Button click
     */
    @FXML
    private void handleButtonNewGame(ActionEvent event) {
        grdPane.setDisable(true);
        btnStartGame.setDisable(false);
        txtfldNamePlayer1.setDisable(false);
        txtfldNamePlayer2.setDisable(false);
        lblWinner.setVisible(false);
        Button[][] btnsGamePlay = {{btn00, btn01, btn02}, {btn10, btn11, btn12}, 
            {btn20, btn21, btn22}};
        for (Button[] btnsGamePlay1 : btnsGamePlay) {
            for (Button item : btnsGamePlay1) {
                item.setText("");
            }
        }
    }

    /**
     * Starts the new game
     * @param event, Button click
     */
    @FXML
    private void handleButtonStartGame(ActionEvent event) {
        //TODO Done Beide namen müssen irgendwie gesetzt werden (also nicht leer)
        //XXX Pop-up Fenster wäre nett für fehlerhafte Eingaben
        if (!(txtfldNamePlayer1.getText().equals("") || txtfldNamePlayer2.getText().equals(""))) {
            grdPane.setDisable(false);
            Button[][] btnsGamePlay = {{btn00, btn01, btn02}, {btn10, btn11, btn12},
                {btn20, btn21, btn22}};
            this.game = new GameTicTacToe(txtfldNamePlayer1.getText(), txtfldNamePlayer2.getText(),
                    FIELD_SIZE, new JavaFXGUI(btnsGamePlay, lblCurrPlayer, lblWinner, grdPane));
            lblCurrPlayer.setText(txtfldNamePlayer1.getText() + " (" + Symbol.values()[0] + ")"); //TODO DONE siehe Logikklasse (hier wird ein assertion ausgelöst)
            lblCurrPlayer.setVisible(true);
            btnStartGame.setDisable(true);
            txtfldNamePlayer1.setDisable(true);
            txtfldNamePlayer2.setDisable(true);
        }
    }

}
