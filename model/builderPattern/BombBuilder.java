package model.builderPattern;

/*
 * participant: Builder
 */

public abstract class BombBuilder {
    
    protected Bomb bomb;
    
    public Bomb getBomb() {
        return bomb;
    }
    
    public void createBomb(int x, int y) {
        bomb = new Bomb(x,y);
    }
    
    public abstract void buildShape();
    public abstract void buildColor();
    public abstract void buildStrategy();
}
