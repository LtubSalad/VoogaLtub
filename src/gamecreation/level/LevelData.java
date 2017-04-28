package gamecreation.level;

import java.util.Observable;
import java.util.Observer;

import javafx.beans.property.StringProperty;
import newengine.managers.conditions.Condition;

public class LevelData extends Observable implements ILevelData {
	private int diffMod; 
	private String name;
	private Condition winningCondition;
	private Condition losingCondition;

	public LevelData(){
		this.name = "Level";
	}
	
	public void setName(String name){
		this.name = name;
		this.setChanged();
		this.notifyObservers(this.getName());
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getSpawnTime() {
		return 10.0/diffMod;
	}

	@Override
	public double getDamageMultiplier() {
		return 5.0/diffMod;
	}

	@Override
	public Condition getWinningCondition() {
		return winningCondition;
	}

	@Override
	public Condition getLosingCondition() {
		return losingCondition;
	}

	@Override
	public StringProperty getNameTextProperty() {
		return null;
	}

	public void subscribe(Observer o){
		this.addObserver(o);
	}
}
