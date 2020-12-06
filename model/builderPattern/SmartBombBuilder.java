package model.builderPattern;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

/*
 * participant: ConcreteBuilder
 */

public class SmartBombBuilder extends BombBuilder {

    @Override
    public void buildShape() {
        bomb.setShape(new Ellipse2D.Float(0, 0, 20, 20));
    }

    @Override
    public void buildColor() {
        bomb.setColor(Color.red);
    }

    @Override
    public void buildStrategy() {
        bomb.setStrategy("Moving Strategy: Follow the shooter");
    }
    
}
