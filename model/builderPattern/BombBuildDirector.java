package model.builderPattern;

/*
 * participant: Director
 */

public class BombBuildDirector {
    
    private BombBuilder builder;
    
    public void setBombBuilder(BombBuilder builder) {
        this.builder = builder;
    }
    
    public Bomb getBomb() {
        return builder.getBomb();
    }
    
    public void createBomb(int x, int y) {
        builder.createBomb( x,  y);
        builder.buildShape(x,y );
        builder.buildColor();
        builder.buildStrategy();
    }
}
