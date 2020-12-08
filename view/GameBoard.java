package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.Timer;

import controller.KeyController;
import controller.TimerListener;
import model.EnemyComposite;
import model.Shooter;

import model.observerPattern.enemyObserverPattern.EnemyObserver;
import model.observerPattern.shooterObserverPattern.ShooterObserver;
import model.strategyPattern.ShooterRenderAliveStrategy;



public class GameBoard {

    public static final int WIDTH = 1000;
    public static final int HEIGHT =  500;

    public static final int FPS = 30;
    public static final int DELAY = 1000 / FPS;

    
    
    private JFrame window;
    private MyCanvas canvas;
    private Shooter shooter;
    
    private EnemyComposite enemyComposite;
    private Timer timer;
    private TimerListener timerListener;
    private int score = 0;
    private int lives = 4;
    JLabel livesDisplay = new JLabel();
    JLabel scoreDisplay = new JLabel();


    public GameBoard(JFrame window){
        this.window = window;
    }

    public void init(){
        Container cp = window.getContentPane();

        canvas = new MyCanvas(this, WIDTH, HEIGHT);
        cp.add(BorderLayout.CENTER, canvas);
        canvas.addKeyListener(new KeyController(this));
        canvas.requestFocusInWindow();
        canvas.setFocusable(true);

        JButton startButton = new JButton("Start");
        JButton quitButton = new JButton("Quit");
        JLabel scoreJLabel = new JLabel("Score: ");
        JLabel livesJLabel = new JLabel("Lives: ");
        startButton.setFocusable(false);
        quitButton.setFocusable(false);
        scoreDisplay.setFocusable(false);
        livesJLabel.setFocusable(false);



        JPanel southPanel = new JPanel();
        southPanel.add(startButton);
        southPanel.add(quitButton);
        southPanel.add(scoreJLabel);
        scoreDisplay.setText("" + score);
        southPanel.add(scoreDisplay);
        southPanel.add(livesJLabel);
        livesDisplay.setText("" + lives);
        southPanel.add(livesDisplay);
        
        cp.add(BorderLayout.SOUTH,southPanel);
        canvas.getGameElements().add(new TextDraw("SPACE INVADERS", 350, HEIGHT/2, Color.yellow, 30));

        canvas.getGameElements().add(new TextDraw("Click <Start> to play", 300, 300, Color.yellow, 30));
        
        

        timerListener = new TimerListener(this);
        timer = new Timer(DELAY, timerListener);

        startButton.addActionListener(event -> {
            shooter = new Shooter(GameBoard.WIDTH/2, GameBoard.HEIGHT - 20);
            

            ShooterObserver observer = new ShooterObserver(this);
            EnemyObserver observer2 = new EnemyObserver(this);
            enemyComposite = new EnemyComposite();
            enemyComposite.addEnemyListener(observer2);
            shooter.addShooterListener(observer);
            canvas.getGameElements().clear();
            canvas.getGameElements().add(shooter);
            canvas.getGameElements().add(enemyComposite);
            setScore(0);
            setLives(4);
            

            shooter.setRenderStrategy(new ShooterRenderAliveStrategy(shooter));
            

            timer.start();

        });

        quitButton.addActionListener(event -> System.exit(0));
    }
    public MyCanvas getCanvas() {
        return canvas;
    }

    public Timer getTimer() {
        return timer;
    }

    public TimerListener getTimerListener() {
        return timerListener;
    }
    public Shooter getShooter() {
        return shooter;
    }
    public EnemyComposite getEnemyComposite() {
        return enemyComposite;
    }
    public JLabel getScoreTextField() {
        return scoreDisplay;
    }
    
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public void setLives(int lives) {
        this.lives = lives;
    }
    public int getLives() {
        return lives;
    }

    public JLabel getLivesDisplay() {
        return livesDisplay;
    }
    
}
