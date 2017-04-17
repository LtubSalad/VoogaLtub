package newengine.events.selection;

import bus.BusEvent;
import bus.BusEventType;
import newengine.skill.Skill;

public class SelectSkillEvent extends BusEvent {

	public static final BusEventType<SelectSkillEvent> SELECT = new BusEventType<>(
			SelectSkillEvent.class.getName() + "SELECT");
	public static final BusEventType<SelectSkillEvent> CANCEL = new BusEventType<>(
			SelectSkillEvent.class.getName() + "CANCEL");
	public static final BusEventType<SelectSkillEvent> CONFIRM = new BusEventType<>(
			SelectSkillEvent.class.getName() + "CONFIRM");

	private Skill skill;

	public SelectSkillEvent(BusEventType<? extends BusEvent> busEventType, Skill skill) {
		super(busEventType);
		this.skill = skill;
	}

	public Skill getSkill() {
		return skill;
	}

}
