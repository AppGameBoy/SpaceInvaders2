package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Item extends GameElements {

	public static final int SIZE = 10;
    public static final int UNIT_MOVE = 5;

	public Item(int x, int y){
        super(x, y, Color.pink, true, SIZE,SIZE *2 );
    }

	@Override
	public void render(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.setColor(color);
        if(filled){
            g2.fillRoundRect(x, y, width, height,3,3);
        } else {
            g2.drawRoundRect(x, y, width, height,3,3);
        }

	}

	@Override
	public void animate() {
		// TODO Auto-generated method stub
        super.y += UNIT_MOVE;


	}
	
}
