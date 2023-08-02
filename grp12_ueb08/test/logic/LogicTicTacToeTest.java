package logic;

import logic.GameTicTacToe.Symbol;
import static logic.GameTicTacToe.Symbol.X;
import static logic.GameTicTacToe.Symbol.O;
import static logic.GameTicTacToe.Symbol.EMPTY;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for the logic of the Game Tic Tac Toe. While these test cases cover a lot, they do not
 * cover all possible scenarios. It is still possible for the logic to malfunction, albeit in
 * special cases. Thinking about own test cases is recommended.
 *
 * @author cei
 */
public class LogicTicTacToeTest {

    /**
     * If this test fails, all other tests can be ignored - they might just fail because they
     * require one of the two tested methods.
     */
    @Test
    public void test_BasicallyNeeded_FieldToStringAndTestConstructor() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, EMPTY, EMPTY},
                    {O, EMPTY, EMPTY},
                    {X, EMPTY, EMPTY}
                }, new FakeGUI());
        //expecting the additional whitespace and linebreak after each symbol
        //or line makes the implementation easier. As this method is just for 
        //testing anyway, this is acceptable behaviour. 
        assertEquals("    X     O     X \n"
                + "EMPTY EMPTY EMPTY \n"
                + "EMPTY EMPTY EMPTY \n",
                game.fieldToString());
    }

    /**
     * If this test fails, all other tests can be ignored - they might just fail because they
     * require one of the two tested methods.
     */
    @Test
    public void testFieldToStringAndTestConstructorSize2() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, EMPTY},
                    {O, EMPTY}
                }, new FakeGUI());
        //expecting the additional whitespace and linebreak after each symbol
        //or line makes the implementation easier. As this method is just for 
        //testing anyway, this is acceptable behaviour. 
        assertEquals("    X     O \n"
                + "EMPTY EMPTY \n", game.fieldToString());
    }

    @Test
    public void testOneTurnEachPlayerSize2() {
        GameTicTacToe game = new GameTicTacToe("Ernie", "Bert", 2, new FakeGUI());
        assertEquals("EMPTY EMPTY \n"
                + "EMPTY EMPTY \n", game.fieldToString());
        game.playerTurn(new int[]{0, 0});
        assertEquals("    X EMPTY \n"
                + "EMPTY EMPTY \n", game.fieldToString());
        game.playerTurn(new int[]{1, 1});
        assertEquals("    X EMPTY \n"
                + "EMPTY     O \n", game.fieldToString());
    }

    @Test
    public void testFillFieldNoWinner() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, O, X},
                    {X, O, X},
                    {O, X, O}
                }, new FakeGUI());
        assertFalse(game.emptyCellsLeft());
        assertNull(game.getWinnerName());
    }

    @Test
    public void testFillFieldOneWinner() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, O, X},
                    {X, O, X},
                    {O, X, X}
                }, new FakeGUI());
        assertFalse(game.emptyCellsLeft());
        assertEquals("Marcus", game.getWinnerName());
    }

    @Test
    public void testCellAlreadyOccupiedPlayerCanChooseAgain() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, O, EMPTY},
                    {X, O, EMPTY},
                    {EMPTY, EMPTY, EMPTY}
                }, new FakeGUI());
        assertEquals("    X     X EMPTY \n"
                + "    O     O EMPTY \n"
                + "EMPTY EMPTY EMPTY \n", game.fieldToString());
        game.playerTurn(new int[]{0, 1});
        assertEquals("    X     X EMPTY \n"
                + "    O     O EMPTY \n"
                + "EMPTY EMPTY EMPTY \n", game.fieldToString());
        game.playerTurn(new int[]{2, 0});
        assertTrue(game.emptyCellsLeft());
        assertEquals("    X     X     X \n"
                + "    O     O EMPTY \n"
                + "EMPTY EMPTY EMPTY \n", game.fieldToString());
        assertEquals("Marcus", game.getWinnerName());
    }

    @Test
    public void testWinnerFirstPlayerWTopRowSize2() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Trick", "Track"},
                new Symbol[][]{{X, O}, {X, EMPTY}}, new FakeGUI());
        assertEquals("Trick", game.getWinnerName());
    }

    @Test
    public void testWinnerFirstPlayerWTopRow() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, O, EMPTY},
                    {X, O, EMPTY},
                    {X, EMPTY, EMPTY}
                }, new FakeGUI());

        assertTrue(game.emptyCellsLeft());
        assertEquals("Marcus", game.getWinnerName());
    }

    @Test
    public void testWinnerFirstPlayerWBottomRow() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, O, X},
                    {X, O, X},
                    {EMPTY, EMPTY, X}
                }, new FakeGUI());
        assertTrue(game.emptyCellsLeft());
        assertEquals("Marcus", game.getWinnerName());
    }

    @Test
    public void testWinnerFirstPlayerWColumn() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, X, X},
                    {EMPTY, EMPTY, EMPTY},
                    {EMPTY, EMPTY, EMPTY}
                }, new FakeGUI());
        assertTrue(game.emptyCellsLeft());
        assertEquals("Marcus", game.getWinnerName());
    }

    @Test
    public void testWinnerFirstPlayerWDiagonalLeftTop() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, EMPTY, EMPTY},
                    {EMPTY, X, EMPTY},
                    {EMPTY, EMPTY, X}
                }, new FakeGUI());

        assertTrue(game.emptyCellsLeft());
        assertEquals("Marcus", game.getWinnerName());
    }

    @Test
    public void testWinnerFirstPlayerWDiagonalRightTop() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {EMPTY, EMPTY, X},
                    {EMPTY, X, EMPTY},
                    {X, EMPTY, EMPTY}
                }, new FakeGUI());

        assertTrue(game.emptyCellsLeft());
        assertEquals("Marcus", game.getWinnerName());
    }

    @Test
    public void testWinnerSecondPlayerWRow() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, O, EMPTY},
                    {X, O, EMPTY},
                    {EMPTY, O, X}
                }, new FakeGUI());

        assertTrue(game.emptyCellsLeft());
        assertEquals("Cordula", game.getWinnerName());
    }

    @Test
    public void testEmptyCellsLeft() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, O, EMPTY},
                    {X, O, EMPTY},
                    {EMPTY, O, X}
                }, new FakeGUI());
        assertTrue(game.emptyCellsLeft());
    }
        @Test
    public void testEmptyCellsLeft_Not() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, O, X},
                    {X, O, O},
                    {X, O, O}
                }, new FakeGUI());
        assertFalse(game.emptyCellsLeft());
    }
}
