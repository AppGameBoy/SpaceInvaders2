package model.builderPattern;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/*
 * participant: ConcreteBuilder
 */

public class DroppingBombBuilder extends BombBuilder {
    
    private Random random = new Random();

    @Override
    public void buildShape(int x, int y) {
        
        bomb.setShape(new Rectangle2D.Float(x,y,10, 10));
    }

    @Override
    public void buildColor() {
        
        bomb.setColor(Color.blue);
    }

    @Override
    public void buildStrategy() {
        
        bomb.setStrategy("dropping");
    }
    
}
