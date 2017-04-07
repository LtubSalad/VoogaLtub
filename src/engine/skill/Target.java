package engine.skill;


import java.util.Optional;

import engine.camera.GamePoint;
import engine.sprite.Sprite;

public class Target {
	
	private GamePoint location = null;
	private Sprite sprite = null;
	
	public Target(Sprite sprite) {
		this.sprite = sprite;
	}
	public Target(GamePoint location) {
		this.location = location;
	}
	public Target(GamePoint location, Sprite sprite) {
		this.location = location;
		this.sprite = sprite;
	}
	
	public Optional<Sprite> getSprite() {
		return Optional.ofNullable(sprite);
	}
	public Optional<GamePoint> getLocation() {
		return Optional.ofNullable(location);
	}
}
