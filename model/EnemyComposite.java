package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import model.Shooter.Event;
import model.builderPattern.Bomb;
import model.builderPattern.BombBuildDirector;
import model.builderPattern.BombBuilder;
import model.builderPattern.DroppingBombBuilder;
import model.builderPattern.MegaBombBuilder;
import model.observerPattern.enemyObserverPattern.Observer2;
import model.observerPattern.enemyObserverPattern.Subject2;
import view.GameBoard;


public class EnemyComposite extends GameElements implements Subject2 {

    public static final int NROWS = 2;
    public static final int NCOLS = 10;
    public static final int ENEMY_SIZE = 20;
    public static final int UNIT_MOVE = 10;

    private ArrayList<ArrayList<GameElements>> rows;
    private ArrayList<GameElements> bombs;
    private ArrayList<GameElements> items;
    private ArrayList<Observer2> observers2 = new ArrayList<>();


    private boolean movingToRight = true;
    private Random random = new Random();

    public enum Event2 {
        TouchedBottom
    }

    public EnemyComposite() {
        rows = new ArrayList<>();
        bombs = new ArrayList<>();
        items = new ArrayList<>();

        for (int r = 0; r < NROWS; r++) {
            var oneRow = new ArrayList<GameElements>();
            rows.add(oneRow);
            for (int c = 0; c < NCOLS; c++) {
                oneRow.add(new Enemy(c * ENEMY_SIZE * 2, r * ENEMY_SIZE * 2, ENEMY_SIZE, Color.yellow, true));
            }
        }
    }

    @Override
    public void render(Graphics2D g2) {
        // render enemys array
        for (var r : rows) {
            for (var e : r) {
                e.render(g2);
            }
        }

        // render bombs
        for (var b : bombs) {
            b.render(g2);
        }

        for (var i : items) {
            i.render(g2);
        }

    }

    @Override
    public void animate() {
        //
        int dx = UNIT_MOVE;
        int dy = 0;
        if (movingToRight) {
            if (rightEnd() >= GameBoard.WIDTH) {
                dx = -dx;
                dy = ENEMY_SIZE;

                movingToRight = false;
            }
        } else {
            dx = -dx;

            if (leftEnd() <= 0) {
                dx = -dx;
                dy = ENEMY_SIZE;
                movingToRight = true;
            }

        }

        // update x loc
        for (var row : rows) {
            for (var e : row) {
                e.x += dx;
                e.y += dy;

            }
        }

        // animate bombs
        for (var b : bombs) {
            b.animate();
        }
        //anime items
        for (var i : items) {
            i.animate();
        }
    }

    private int rightEnd() {
        int xEnd = -100;
        for (var row : rows) {
            if (row.size() == 0)
                continue;
            int x = row.get(row.size() - 1).x + ENEMY_SIZE;
            if (x > xEnd)
                xEnd = x;
        }
        return xEnd;
    }

    private int leftEnd() {
        int xEnd = 9000;
        for (var row : rows) {
            if (row.size() == 0)
                continue;
            int x = row.get(0).x;
            if (x < xEnd)
                xEnd = x;
        }
        return xEnd;
    }

    // drops the bombs
    public void dropBombs() {

        for (var row : rows) {
            for (var e : row) {
                if (random.nextFloat() < 0.08F) {

                    BombBuildDirector director = new BombBuildDirector();
                    BombBuilder builder = new MegaBombBuilder();
                    director.setBombBuilder(builder);
                    director.createBomb(e.x, e.y);
                    Bomb bomb = director.getBomb();
                    bombs.add(bomb);
                } else if (random.nextFloat() < 0.08f) {
                    BombBuildDirector director = new BombBuildDirector();
                    BombBuilder builder = new DroppingBombBuilder();
                    //builder = new DroppingBombBuilder();
                    director.setBombBuilder(builder);
                    director.createBomb(e.x, e.y);
                    Bomb bomb = director.getBomb();
                    bomb = director.getBomb();
                    bombs.add(bomb);
                }
            }
        }
    }

    // removes the bombs
    public void removeBombsOutOfBound() {
        var remove = new ArrayList<GameElements>();
        for (var b : bombs) {
            if (b.y >= GameBoard.HEIGHT) {
                remove.add(b);
            }
        }

        bombs.removeAll(remove);
    }
    //process the collison with bottom of the screen
    public void touchedBottom(){
        for (var row : rows) {
            for (var e : row) {
                if (e.y == GameBoard.HEIGHT) {
                    notifyObserver(Event2.TouchedBottom);
                }
            }
        }

    }
    // drops the machine gunn boost
    public void dropItems() {

        for (var row : rows) {
            for (var e : row) {
                if (random.nextFloat() < 0.1F) {
                    items.add(new Item(e.x, e.y));
                }
            }
        }
    }

    public void processCollision(Shooter shooter) {
        var removeBullets = new ArrayList<GameElements>();

        // bullets vs enemies
        for (var row : rows) {
            var removeEnemies = new ArrayList<GameElements>();
            for (var enemy : row) {
                for (var bullets : shooter.getWeapons()) {
                    if (enemy.collideWith(bullets)) {
                        removeBullets.add(bullets);
                        removeEnemies.add(enemy);
                        shooter.notifyObserver(Event.EnemyisDestroyed);

                    }
                }
            }
            row.removeAll(removeEnemies);
        }
        shooter.getWeapons().removeAll(removeBullets);

        
     

        // bullets vs bombs
        var removeBombs = new ArrayList<GameElements>();
        removeBullets.clear();
        for (var b : bombs) {
            for (var bullet : shooter.getWeapons()) {
                if (b.collideWith(bullet)) {
                    removeBombs.add(b);
                    removeBullets.add(bullet);
                }
            }
        }
        shooter.getWeapons().removeAll(removeBullets);
        bombs.removeAll(removeBombs);
        

        for(var b: bombs){
            for(var s: shooter.getSheilds()){
                if(b.collideWith(s)){
                    removeBombs.add(b);
                }
            }
        }
        // bombs vs shooter
        var removeShooterBlocks = new ArrayList<GameElements>();
        for (var blocks : shooter.getComponents()) {
            for (var b : bombs) {
                if (blocks.collideWith(b)) {
                    removeShooterBlocks.add(blocks);
                    removeBombs.add(b);
                    shooter.notifyObserver(Event.ShooterIsDamaged);
                    

                }
            }
        }
        shooter.getComponents().removeAll(removeShooterBlocks);
        bombs.removeAll(removeBombs);

        // items vs shooter
        var removeItems = new ArrayList<>();
        for (var blocks : shooter.getComponents()) {
            for (var i : items) {
                if (blocks.collideWith(i)) {
                    removeItems.add(i);

                    Thread shootThread = new Thread() {
                        @Override
                        public void run() {
                            shooter.setMAX_BULLETS(10);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                
                                e.printStackTrace();
                            }
                            shooter.setMAX_BULLETS(3);
                        }
                    };
                    shootThread.start();

                }
            }
        }
        items.removeAll(removeItems);

    }

    public ArrayList<GameElements> getBombs() {
        return bombs;
    }

    @Override
    public void addEnemyListener(Observer2 o) {
        //
        observers2.add(o);

    }

    @Override
    public void removeEnemyListener(Observer2 o) {
        //
        observers2.remove(o);
    }

    @Override
    public void notifyObserver(Event2 event2) {
       switch(event2){
            case TouchedBottom:
                for(var o: observers2){
                    o.TouchedBottom();
                }
                break;
       }    

    }

	

	
    
}
