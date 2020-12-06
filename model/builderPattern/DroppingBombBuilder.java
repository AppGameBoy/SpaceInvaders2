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
    public void buildShape() {
        
        bomb.setShape(new Rectangle2D.Float(random.nextInt(700), random.nextInt(700), 500, 500));
    }

    @Override
    public void buildColor() {
        
        bomb.setColor(Color.GREEN);
    }

    @Override
    public void buildStrategy() {
        
        bomb.setStrategy("dropping");
    }
    
}
