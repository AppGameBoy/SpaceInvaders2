package model.builderPattern;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/*
 * participant: ConcreteBuilder
 */

public class RandomBombBuilder extends BombBuilder {
    
    private Color[] colors = {Color.DARK_GRAY, Color.ORANGE, Color.RED, Color.BLACK, Color.CYAN};
    private Shape[] shapes = {
        new Ellipse2D.Float(0, 0, 20, 20),
        new Ellipse2D.Float(0, 0, 40, 10),
        new Rectangle2D.Float(0, 0, 20, 20),
        new Rectangle2D.Float(0, 0, 40, 10)
    };
    private String[] strategies  = {"Vertical", "Horizontal", "Diagonal", "Zig-zag"};
    
    private Random random = new Random();

    @Override
    public void buildShape() {
        bomb.setShape(shapes[random.nextInt(shapes.length)]);
    }

    @Override
    public void buildColor() {
        bomb.setColor(colors[random.nextInt(colors.length)]);
    }

    @Override
    public void buildStrategy() {
        bomb.setStrategy(strategies[random.nextInt(strategies.length)]);
    }
    
}
