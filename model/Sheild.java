package model;

import java.awt.Color;
import java.awt.Graphics2D;



public class Sheild extends GameElements {

    public static final int  WIDTH = 70;
    public static final int UNIT_MOVE = 20;
    
	 

    
    

    public Sheild(int x , int y){
        super(x, y, Color.white, true, WIDTH,5 );
        
    }

    

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.fillRoundRect(x, y, WIDTH, height, 10, 10);

    }

    @Override
    public void animate() {
        super.x += 10;
        super.y =12;
        //x = shooter.x;
        

    }
    
}
