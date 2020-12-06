package model.builderPattern;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

import model.GameElements;

/*
    participant: Product
*/
public class Bomb extends GameElements {

    private Shape shape;
    private Color color;
    private String strategy;

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    @Override
    public String toString() {
        return "Bomb[" + shape + ", " + color + ", " + strategy + "]";
    }

    @Override
    public void render(Graphics2D g2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void animate() {
        // TODO Auto-generated method stub

    }
    
}
