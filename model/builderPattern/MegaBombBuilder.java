package model.builderPattern;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

/*
 * participant: ConcreteBuilder
 */

public class MegaBombBuilder extends BombBuilder {

    @Override
    public void buildShape(int x , int y) {
        bomb.setShape(new Ellipse2D.Float(x, y, 30, 30));
    }

    @Override
    public void buildColor() {
        bomb.setColor(Color.RED);
    }
 
    @Override
    public void buildStrategy() {
        bomb.setStrategy("mega");
    }
    
}
