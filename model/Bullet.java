package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends GameElements {

    public static final int  WIDTH = 5;
    public static final int UNIT_MOVE = 10;


    public Bullet(int x , int y){
        super(x, y, Color.RED, true, WIDTH, WIDTH*3);
    }

    @Override
    public void render(Graphics2D g2) {
        // TODO Auto-generated method stub

        g2.setColor(color);
        if(filled){
            g2.fillRect(x, y, width, height);
        } else {
            g2.drawRect(x, y, width, height);
        }

    }

    @Override
    public void animate() {
        // TODO Auto-generated method stub
        super.y -= UNIT_MOVE;

    }
    
}
