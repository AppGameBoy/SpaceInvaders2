package model.builderPattern;

/*
 * participant: Builder
 */

public abstract class BombBuilder {
    
    protected Bomb bomb;
    
    public Bomb getBomb() {
        return bomb;
    }
    
    public void createBomb() {
        bomb = new Bomb();
    }
    
    public abstract void buildShape();
    public abstract void buildColor();
    public abstract void buildStrategy();
}
