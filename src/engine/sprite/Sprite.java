package engine.sprite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import commons.MathUtils;
import engine.camera.GamePoint;
import engine.player.Player;
import engine.sprite.attack.Attacker;
import engine.sprite.attack.Weapon;
import engine.sprite.collision.Collidable;
import engine.sprite.images.ImageSet;
import engine.sprite.images.LtubImage;

public class Sprite {

	// initialize empty image.
	private ImageSet imageSet;
	// private boolean locked = false; // TODO
	private GamePoint pos;
	private int z;

	private HashMap<String, Attribute> attributeMap = new HashMap<String, Attribute>();
	private HashMap<String, Boolean> identityMap = new HashMap<String, Boolean>();

	private SelectionBound selectionBound = SelectionBound.IMAGE;
	private List<GamePoint> selectionBoundVertices;
	private double detectionRange;
	/**
	 * The player that this sprite belongs to.
	 */
	private Player player = Player.DEFAULT;
	private ActionQueue actionQueue = new ActionQueue();

	public Sprite() {
		initIdentity();
		initAttributes();
	}

	private void initIdentity(){
		identityMap.put("monster", false);
		identityMap.put("tower", false);
		identityMap.put("bullet", false);
		identityMap.put("spawner", false);
	}
	
	private void initAttributes(){
		attributeMap.put("movable", null);
		attributeMap.put("collidable", null);
		attributeMap.put("attacker", null);
		attributeMap.put("weapon", null);
		attributeMap.put("healthholder", null);
	}

	public void setImageSet(ImageSet imageSet) {
		this.imageSet = imageSet;		
	}

	/**
	 * Return an image corresponding to the sprite at the 
	 * current frame. Could change with direction and moving distance.
	 * @return
	 */
	public LtubImage getImage() {
		// TODO: pass in angle and dist
		return imageSet.getImage(0, 0);
	}

	public GamePoint getPos() {
		return pos;
	}

	public void setPos(GamePoint pos) {
		this.pos = pos;
	}

	public void setSelectionBound(SelectionBound selectionBound) {
		this.selectionBound = selectionBound;
	}

	public SelectionBound getSelectionBound() {
		return selectionBound;
	}

	private void setSelectionBoundVertices() {
		selectionBoundVertices = new ArrayList<>();
		if (selectionBound == SelectionBound.IMAGE) {
			//			if (ltubImage != null) {
			//				// Image rectangle nodes are added in a clock-wise order
			//				selectionBoundVertices.add(this.getDisplayPos());
			//				selectionBoundVertices.add(new Point(this.getDisplayPos().x() + ltubImage.width(), this.getDisplayPos().y()));
			//				selectionBoundVertices.add(new Point(this.getDisplayPos().x() + ltubImage.width(), this.getDisplayPos().y() + ltubImage.height()));
			//				selectionBoundVertices.add(new Point(this.getDisplayPos().x(), this.getDisplayPos().y() + ltubImage.height()));
			//			}
		}
		else if (selectionBound == SelectionBound.POLYGON) {
			// TODO
		}
	}

	/**
	 * Get a list of Point indicating the definite display positions of selection bound vertices
	 * @return List<Point>
	 */
	public List<GamePoint> getSelectionBoundVertices() {
		setSelectionBoundVertices();
		return selectionBoundVertices;
	}

	public void setDetectionRange(double detectionRange) {
		this.detectionRange = detectionRange;
	}

	public double getDetectionRange() {
		return detectionRange;
	}
	
	//setting identity
	public void setMonster(Boolean b){
		identityMap.put("monster", b);
	}
	public Boolean isMonster(){
		return identityMap.get("monster");
	}
	public void setTower(Boolean b){
		identityMap.put("tower", b);
	}
	public Boolean isTower(){
		return identityMap.get("tower");
	}
	
	//setting attributes
	public void setAttribute(String name, Attribute attribute){
		attributeMap.put(name, attribute);
	}
	
	public Optional<Attribute> getMovable() {
		return Optional.ofNullable(attributeMap.get("movable"));
	}
	
	public Optional<Attribute> getCollidable() {
		return Optional.ofNullable(attributeMap.get("collidable"));
	}
	
	public Optional<Attribute> getAttacker() {
		return Optional.ofNullable(attributeMap.get("attacker"));
	}
	
	public Optional<Attribute> getWeapon() {
		return Optional.ofNullable(attributeMap.get("attacker"));
	}
	
	public Optional<Attribute> getHealthHolder(){
		return Optional.ofNullable(attributeMap.get("healthholder"));
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Player getPlayer() {
		return player;
	}
	public void executeAction(Action action) {
		actionQueue.clearQueue();
		action.execute();
	}
	public void queueAction(Action action) {
		actionQueue.addAction(action);
	}

	public void updatePos(double dt) {
		double tRemain = dt;
		// TODO: this piece of actions queueing code has to be improved

		//trigger Movable
		if (shouldTrigger("movable")){
			if (!actionQueue.isEmpty()) {
				actionQueue.executeNextAction();
			}
		}
		while (!MathUtils.doubleEquals(tRemain, 0) && attributeMap.get("movable").isAttribute()) {
			tRemain = attributeMap.get("movable").update(dt);
			if (!MathUtils.doubleEquals(tRemain, 0)) {
				if (!actionQueue.isEmpty()) {
					actionQueue.executeNextAction();
				}
			}
		}
	}

	private Boolean shouldTrigger(String s){
		if (attributeMap.get(s) != null){
			if (attributeMap.get(s).isAttribute()){
				return true;
			}
		}
		return false;
	}
	

}
