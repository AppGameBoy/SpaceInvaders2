package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import model.Bullet;
import model.Sheild;
import model.Shooter;

import view.GameBoard;


public class TimerListener implements ActionListener{

    public enum EventType{
        KEY_RIGHT, KEY_LEFT, KEY_SPACE, S, keyReleased
    }

    private GameBoard gameBoard;
    private LinkedList<EventType> eventQueue;
    private final int BOMB_DROP_FREQ = 20;
    private int frameCounter = 0;
	
    



    public TimerListener(GameBoard gameBoard){
        this.gameBoard = gameBoard;
        eventQueue = new LinkedList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        ++frameCounter;
        update();
        processEventQueue();
        processCollision();
        gameBoard.getCanvas().repaint();

    }

    private void processEventQueue(){
        Shooter shooter = gameBoard.getShooter();

        Sheild sheild = new Sheild(shooter.x-36, shooter.y-35);
        while(!eventQueue.isEmpty()){
            var e = eventQueue.getFirst();
            eventQueue.removeFirst();
            
            if(shooter == null)return;


            switch(e){
                case KEY_LEFT:
                    shooter.moveLeft();
                    
                    break;
                case KEY_RIGHT:
                    shooter.moveRight();
                    
                    break;                    
                case KEY_SPACE:
                    if(shooter.canFireMoreBullets()){
                        shooter.getWeapons().add(new Bullet(shooter.x, shooter.y));
                    }
                    break;
                case S:
                    shooter.getSheilds().add(sheild);
                            
                  
                    break;
                case keyReleased:
                    shooter.getSheilds().clear();
                    System.out.println("worked");
                    break;
                    
            }
            

        }

        if(frameCounter == BOMB_DROP_FREQ){
            gameBoard.getEnemyComposite().dropBombs();
            gameBoard.getEnemyComposite().dropItems();
            frameCounter = 0;
        }
    }
    

    private void processCollision(){
        var shooter = gameBoard.getShooter();
        var enemyComposite = gameBoard.getEnemyComposite();
        
        shooter.removeBulletsOutOfBound();
        enemyComposite.removeBombsOutOfBound();
        enemyComposite.processCollision(shooter);
        shooter.processCollision(enemyComposite);
        enemyComposite.touchedBottom();
    }

    private void update() {
        for(var e: gameBoard.getCanvas().getGameElements()){
            e.animate();
        }   
     }

    public LinkedList<EventType> getEventQueue() {
        return eventQueue;
    }

   
}
