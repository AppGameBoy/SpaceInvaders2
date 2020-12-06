package model.observerPattern.shooterObserverPattern;

import java.awt.Color;
import java.util.Random;

import model.Shooter;
import model.strategyPattern.ShooterDeadStrategy;
import view.GameBoard;
import view.TextDraw;

public class ShooterObserver implements Observer{

	private GameBoard gameBoard;
	private Random random = new Random();
	
	public ShooterObserver(GameBoard gameBoard){
		this.gameBoard = gameBoard;
	}

	@Override
	public void enemyIsDestroyed() {
		//handles score
		int score = gameBoard.getScore();
		score += 10;
		gameBoard.setScore(score);
		gameBoard.getScoreTextField().setText("" + score);
		if (score == 200){
			gameBoard.getCanvas().getGameElements().clear();
			gameBoard.getCanvas().getGameElements().add(new TextDraw("YOU WIN!", 100, 100, Color.GREEN, 30));
			
			gameBoard.getCanvas().getGameElements().add(new TextDraw("Score: " + score, 100, 150, Color.YELLOW, 30));
		}
		
		

		
	}

	@Override
	public void shooterIsDamaged() {
		int lives = gameBoard.getLives();
		lives--;
		gameBoard.setLives(lives);
		gameBoard.getLivesDisplay().setText("" + lives);
		if(gameBoard.getLives()==0){
			gameBoard.getCanvas().getGameElements().clear();
			gameBoard.getCanvas().getGameElements().add(new TextDraw("Game Over", 100, 100, Color.RED, 30));
			int score = gameBoard.getScore();
			Shooter shooter = gameBoard.getShooter();
			gameBoard.getCanvas().getGameElements().add(new TextDraw("Score: " + score, 100, 150, Color.YELLOW, 30));
			shooter.setRenderStrategy(new ShooterDeadStrategy(shooter));

		}

	}

	@Override
	public void annihilation() {
		

	}



	@Override
	public void touchEnemies() {
		

			gameBoard.getCanvas().getGameElements().clear();
			gameBoard.getCanvas().getGameElements().add(new TextDraw("Game Over", 100, 100, Color.RED, 30));
			int score = gameBoard.getScore();
			gameBoard.getCanvas().getGameElements().add(new TextDraw("Score: " + score, 100, 150, Color.YELLOW, 30));

	}
	
}
