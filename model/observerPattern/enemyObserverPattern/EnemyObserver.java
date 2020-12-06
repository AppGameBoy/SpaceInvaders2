package model.observerPattern.enemyObserverPattern;

import view.GameBoard;
import view.TextDraw;

import java.awt.Color;

public class EnemyObserver implements Observer2 {

	private GameBoard gameBoard;

    public EnemyObserver(GameBoard gameBoard){
		this.gameBoard = gameBoard;
    }
    
    @Override
    public void TouchedBottom() {

		int score = gameBoard.getScore();
    gameBoard.getCanvas().getGameElements().clear();
		gameBoard.getCanvas().getGameElements().add(new TextDraw("Game Over", 100, 100, Color.RED, 30));
		gameBoard.getCanvas().getGameElements().add(new TextDraw("Score: " + score, 100, 150, Color.YELLOW, 30));

    }
	
}
