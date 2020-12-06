package model.builderPattern;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;


import model.GameElements;

public class Bomb extends GameElements {

    public final static int SIZE = 5;
    public int UNIT_MOVE = 6;
    public int speed;
    private Shape shape;
    private Color color;
    private String strategy;
    
    public Bomb(int x, int y){
        super(x, y,Color.green,true, SIZE,SIZE *2 );
    }

    public void setShape(Shape shape) {
        this.shape = shape;
	}


	public void setColor(Color color) {
        this.color = color;

	}


	public void setStrategy(String strategy) {
        this.strategy = strategy;

        if (strategy == "dropping"){
            UNIT_MOVE += 10;
        } else if( strategy == "mega"){
            
        }
        
	}


    @Override
    public void render(Graphics2D g2) {
        // TODO Auto-generated method stub
        g2.setColor(color);
        if(filled){
            g2.fillOval(x, y, width, height);
            
        } else {
            g2.drawOval(x, y, width, height);
        }
        

    }

    @Override
    public void animate() {
        // TODO Auto-generated method stub
        
        super.y += UNIT_MOVE;


    }


	
    

}
