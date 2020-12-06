package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.GameBoard;

public class KeyController implements KeyListener {

    private GameBoard gameBoard;
    
    public KeyController(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        var eventQueue = gameBoard.getTimerListener().getEventQueue();

        switch(keyCode){
            case KeyEvent.VK_LEFT:
                eventQueue.add(TimerListener.EventType.KEY_LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                eventQueue.add(TimerListener.EventType.KEY_RIGHT);
                break;
            case KeyEvent.VK_SPACE:
            eventQueue.add(TimerListener.EventType.KEY_SPACE);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //

    }

    @Override
    public void keyReleased(KeyEvent e) {
        

    }
    
}
