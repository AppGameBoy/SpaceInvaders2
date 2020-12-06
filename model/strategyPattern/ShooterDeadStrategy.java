package model.strategyPattern;

import java.awt.Color;
import java.awt.Graphics2D;

import model.Shooter;

public class ShooterDeadStrategy implements ShooterRenderStrategy {

	private Shooter shooter;

    public ShooterDeadStrategy(Shooter shooter){
		this.shooter = shooter;
    }
    
    @Override
    public void renderAlgorithm(Graphics2D g2) {
        var composite = shooter.getComponents();
		for(var s: composite){
            s.color = Color.gray;
            s.filled = false;
			s.render(g2);
		}

    }
    
}
