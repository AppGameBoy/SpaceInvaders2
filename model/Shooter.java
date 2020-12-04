package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import model.observerPattern.Observer;
import model.observerPattern.Subject;

public class Shooter extends GameElements implements Subject {

    public static final int UNIT_MOVE = 10;
    private int MAX_BULLETS = 3;

    private ArrayList<GameElements> components = new ArrayList<>();
    private ArrayList<GameElements> weapons = new ArrayList<>();
    private ArrayList<Observer> observers = new ArrayList<>();

    public enum Event {
        EnemyisDestroyed, ShooterIsDamaged, Annihilation, TouchedEnemies, PowerUpCollected

    }

    public Shooter(int x, int y) {
        super(x, y, 0, 0);

        var size = ShooterElement.SIZE;
        var s1 = new ShooterElement(x - size, y - size, Color.white, false);
        var s2 = new ShooterElement(x, y - size, Color.white, false);
        var s3 = new ShooterElement(x - size, y, Color.white, false);
        var s4 = new ShooterElement(x, y, Color.white, false);

        components.add(s1);
        components.add(s2);
        components.add(s3);
        components.add(s4);
    }

    public void moveRight() {
        super.x += UNIT_MOVE;
        for (var c : components) {
            c.x += UNIT_MOVE;
        }

    }
    public int getMAX_BULLETS() {
        return MAX_BULLETS;
    }
    public void setMAX_BULLETS(int mAX_BULLETS) {
        MAX_BULLETS = mAX_BULLETS;
    }

    public void moveLeft() {
        super.x -= UNIT_MOVE;
        for (var c : components) {
            c.x -= UNIT_MOVE;
        }

    }

    public boolean canFireMoreBullets() {
        return weapons.size() < MAX_BULLETS;
    }

    public void removeBulletsOutOfBound() {
        var remove = new ArrayList<GameElements>();
        for (var w : weapons) {
            if (w.y < 0)
                remove.add(w);
        }
        weapons.removeAll(remove);
    }

    @Override
    public void render(Graphics2D g2) {
        // TODO Auto-generated method stub

        for (var c : components) {
            c.render(g2);
        }

        for (var w : weapons) {
            w.render(g2);
        }

    }

    @Override
    public void animate() {
        // TODO Auto-generated method stub
        for (var w : weapons) {
            w.animate();
        }

    }

    public ArrayList<GameElements> getWeapons() {
        return weapons;
    }

    public ArrayList<GameElements> getComponents() {
        return components;
    }

    public void processCollision(EnemyComposite enemyComposite) {
        var enemyBombs = enemyComposite.getBombs();
        //var droppedItem = enemyComposite.getDroppedItems();

    }

    @Override
    public void addShooterListener(Observer o) {
        // TODO Auto-generated method stub
        observers.add(o);
    }

    @Override
    public void removeShooterListener(Observer o) {
        // TODO Auto-generated method stub
        observers.remove(o);
    }

    @Override
    public void notifyObserver(Event event) {
        switch (event) {
            case EnemyisDestroyed:
                for(var o: observers){
                    o.enemyIsDestroyed();
                }
                
                break;
        
            case ShooterIsDamaged:
                for(var o: observers){
                    o.shooterIsDamaged();
                }
                break;

            case Annihilation:
                break;

            case TouchedEnemies:
                for(var o: observers){
                    o.touchEnemies();
                }
                break;

            case PowerUpCollected:
                break;
        }

    }
    
}
