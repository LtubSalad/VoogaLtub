package newengine.events;

import java.util.List;

import bus.BusEvent;
import bus.BusEventType;
import newengine.sprite.Sprite;

public class SpriteModelEvent extends BusEvent {

	public static final BusEventType<SpriteModelEvent> ADDSPRITE = new BusEventType<>(
			SpriteModelEvent.class.getName() + "ADD");
	public static final BusEventType<SpriteModelEvent> REMOVESPRITE = new BusEventType<>(
			SpriteModelEvent.class.getName() + "REMOVE");
	public static final BusEventType<SpriteModelEvent> ADDITEM = new BusEventType<>(
			SpriteModelEvent.class.getName() + "ADD");
	public static final BusEventType<SpriteModelEvent> REMOVEITEM = new BusEventType<>(
			SpriteModelEvent.class.getName() + "REMOVE");

	private List<Sprite> sprites;
	private Sprite sprite;

	public SpriteModelEvent(BusEventType<? extends BusEvent> busEventType, List<Sprite> sprites) {
		super(busEventType);
		this.sprites = sprites;
	}
	
	public SpriteModelEvent(BusEventType<? extends BusEvent> busEventType, Sprite sprite) {
		super(busEventType);
		this.sprite = sprite;
	}
	
	public List<Sprite> getSprites() {
		return sprites;
	}
	
	public Sprite getSprite() {
		return sprite;
	}

}
