package model.strategyPattern;

import model.Shooter;
import java.awt.Graphics2D;

public class ShooterAliveStrategy implements ShooterRenderStrategy{

	private Shooter shooter;

	public ShooterAliveStrategy(Shooter shooter){
		this.shooter = shooter;
	}

	@Override
	public void renderAlgorithm(Graphics2D g2) {
		//
		var composite = shooter.getComponents();
		boolean filled = true;

		for(var s: composite){
			s.filled = filled;
			filled = !filled;
			s.render(g2);
		}

	}
	
}
