package model.strategyPattern;

import model.Shooter;
import java.awt.Graphics2D;

public class ShooterDeadStrategy implements ShooterRenderStrategy{

	private Shooter shooter;

	public ShooterDeadStrategy(Shooter shooter){
		this.shooter = shooter;
	}

	@Override
	public void renderAlgorithm(Graphics2D g2) {
		// TODO Auto-generated method stub
		var composite = shooter.getComponents();
		boolean filled = true;

		for(var s: composite){
			s.filled = filled;
			filled = !filled;
			s.render(g2);
		}

	}
	
}
