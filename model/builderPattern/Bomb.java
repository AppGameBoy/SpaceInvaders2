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
    private int strat;
    
    public Bomb(int x, int y){
        super(x, y,Color.GREEN,true, SIZE,SIZE *2 );
    }

    public void setShape(Shape shape) {
        this.shape = shape;
        
	}


	public void setColor(Color color) {
        this.color = color;
        super.color = color;
	}


	public void setStrategy(String strategy) {
        this.strategy = strategy;

        if (strategy == "dropping" ){
            UNIT_MOVE += 10;
            strat = 1;
            //y = super.y;
        } else if( strategy == "mega"){
            strat =2;
            super.height = 30;
            super.width = 30;
        }
        
	}


    @Override
    public void render(Graphics2D g2) {

        
        
        g2.setColor(color);
       /*  if(filled){
            g2.fillOval(x, y, width, height);
            
        }  */

        if(strat == 1){
            g2.fillRect(x, y, width, height);
        }else if (strat == 2){
            g2.fillOval(x, y, width, height);
        }

        
        
        
        

    }

    @Override
    public void animate() {
        
        
        if(strat == 1){
            super.y += UNIT_MOVE;

        }else if (strat == 2){
            int dx = UNIT_MOVE;
            boolean moveright = true;
            if(moveright){

                x += dx;
                
            }else{
                x -= dx;
            }
            super.y += UNIT_MOVE;
            
            
        }
        
        


    }


	
    

}
