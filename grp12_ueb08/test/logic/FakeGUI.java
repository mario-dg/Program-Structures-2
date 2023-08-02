/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import logic.GUIConnector;

/**
 *
 * @author Nutzer
 */
public class FakeGUI implements GUIConnector{

    @Override
    public void displaySymbol(int[] coord, GameTicTacToe.Symbol symbol) {
    }

    @Override
    public void setCurrentPlayer(String name, GameTicTacToe.Symbol symbol) {
    }

    @Override
    public void onGameEnd(String winnerName) {
    }
    
}
