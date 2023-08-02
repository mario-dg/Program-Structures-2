package logic;

import java.util.Arrays;

/**
 *
 * @author cei
 */
public class GameTicTacToe {

    /**
     * Name of the players in an array. Length must be 2.
     */
    private String[] players;
    /**
     * Index of the player. Must be either 0 or 1.
     */
    private int idxCurrPlayer;
    /**
     * Connection to the gui.
     */
    private GUIConnector gui;

    /**
     * Enum for the symbols used by the players. The ordinal value of the
     * respective symbol of a player should correspond with the index of this
     * player in the player array. The additional value "EMPTY" (marking an
     * empty cell) thus has the highest ordinal value. Visibility should be as
     * restricted as possible.
     */
    public enum Symbol {
        X, O, EMPTY
    }

    /**
     * The 2-dimensional field on which the player play.
     */
    public Symbol[][] field;

    /**
     * TODO Done JAvadoc Fills the Array with EMPTYS to initialize the Gamefield
     */
    private void fillFieldWithEmpty() {
        for (Symbol[] field1 : this.field) {
            for (int i = 0; i < field1.length; i++) {
                field1[i] = Symbol.EMPTY;
            }
        }
    }

    /**
     * Constructor for a game of tic tac toe. Initializes the field.
     *
     * @param p1 name of the first player
     * @param p2 name of the second player
     * @param size size of the game field
     * @param gui connection to the gui
     */
    public GameTicTacToe(String p1, String p2, int size, GUIConnector gui) {
        this.players = new String[]{p1, p2};
        this.idxCurrPlayer = 0;
        this.field = new Symbol[size][size];
        this.fillFieldWithEmpty();
        this.gui = gui;
    }

    /**
     * Constructor for a gamesituatiuon of tic tac toe.
     *
     * @param players Array with the playernames
     * @param field gamesituation
     * @param gui connection to the gui
     */
    GameTicTacToe(String[] players, Symbol[][] field, GUIConnector gui) {
        this.players = players;
        this.field = field;
        this.idxCurrPlayer = 0;
        this.gui = gui;
    }

    /**
     * Figures out whose turn it was in the last round
     *
     * @return int, 0 or 1 for player x or o
     */
    private int lastPlayer() {
        int x = 0;
        int o = 0;
        for (Symbol[] field1 : this.field) {
            for (int i = 0; i < field1.length; i++) {
                if (field1[i] != Symbol.EMPTY) {
                    if (field1[i] == Symbol.X) {
                        x++;
                    } else {
                        o++;
                    }
                }
            }
        }
        return (x != o) ? 0 : 1;
    }

    /**
     * Figures out, if someone won the game
     *
     * @return boolean, if someone has won or not
     */
    private boolean win() {
        idxCurrPlayer = lastPlayer();
        Symbol xo = Symbol.values()[idxCurrPlayer];
        int length = this.field.length;
        Symbol[] compare = new Symbol[length];

        for (int i = 0; i < length; i++) {
            compare[i] = xo;
        }
        boolean win = false;

        //TODO Done Keine if in den Schleifen notwending und vorzeitig abbrechen
        //TODO Done Keine row oder diagonal win, wenn schon column win (Tipp nur ein win boolean)
        // column win
        for (int i = 0; i < length && !win; i++) {
            win = Arrays.equals(compare, this.field[i]);
        }

        // row win
        for (int i = 0; i < length && !win; i++) {
            Symbol[] temp = new Symbol[length];
            for (int j = 0; j < length; j++) {
                temp[j] = this.field[j][i];
            }
            win = Arrays.equals(compare, temp);
        }

        // diagonal win
        for (int i = 0; i < 2 && !win; i++) {
            Symbol[] temp = new Symbol[length];
            for (int j = 0; j < length && !win; j++) {
                temp[j] = this.field[j][Math.abs(j - (i * (length - 1)))];
            }
            win = Arrays.equals(compare, temp);
        }
        return win;
    }

    /**
     * Handles the turn of a player. If the cell chosen by the player is not
     * empty, nothing happens (the player can chose another cell). If it was
     * empty, the symbol of the current player is placed and the update of the
     * gui is initiated. Then the current player is changed, so that it is the
     * turn of the next player. Finally, the method checks if through this turn
     * a player has won the game.
     *
     * @param coord coordinate in the field at which the player wants to place
     * his/her symbol, length must be 2 otherwise program terminates through
     * assert
     */
    public void playerTurn(int[] coord) {
        //TODO DONE Assert darf nicht bei gültigen eingaben ausgelöst werden
        assert coord != null && coord.length == 2;
        Symbol xo = Symbol.values()[idxCurrPlayer];
        if (this.field[coord[0]][coord[1]] == Symbol.EMPTY) {
            this.field[coord[0]][coord[1]] = xo;
            gui.displaySymbol(coord, xo);
            handleEndOfGame();
            idxCurrPlayer = (idxCurrPlayer + 1) % 2;
            gui.setCurrentPlayer(players[idxCurrPlayer], Symbol.values()[idxCurrPlayer]);
        }
    }

    /**
     * Handles the end of the game
     */
    protected void handleEndOfGame() {
        if (win()) {
            gui.onGameEnd(players[idxCurrPlayer]);
        } else if (!emptyCellsLeft()) {
            gui.onGameEnd(null);
        }
    }

    /**
     * Figures out, if some cells are still empty. If none are emtpy and there
     * is no winner, we have a tie
     *
     * @return boolean, if cells empty or not
     */
    protected boolean emptyCellsLeft() {
        // TODO Done Kein return in Schleifen (verwendet hier eine boolsche Variable zum abbrechen; in beiden Schleifen!!!)
        boolean isEmpty = false;
        for (int i = 0; i < this.field.length && !isEmpty; i++) {
            for (int j = 0; j < this.field[i].length && !isEmpty; j++) {
                isEmpty = this.field[i][j] == Symbol.EMPTY;
            }
        }
        return isEmpty;
    }

    /**
     * Gets the Name of the winner
     *
     * @return String, name of the winner
     */
    protected String getWinnerName() {
        if (win()) {
            return players[idxCurrPlayer];
        } else {
            return null;
        }
    }

    /**
     * Converts the playing field into a String
     *
     * @return String, playing field
     */
    String fieldToString() {
        String s = "";

        for (int j = 0; j < this.field.length; j++) {
            for (int i = 0; i < this.field[j].length; i++) {
                s += String.format("%5s ", this.field[i][j]);
            }
            s += "\n";
        }
        return s;
    }
}
