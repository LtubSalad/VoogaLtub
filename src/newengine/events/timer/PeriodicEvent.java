package newengine.events.timer;

import bus.BusEvent;
import bus.BusEventType;
import newengine.utils.Callback;

public class PeriodicEvent extends BusEvent {

	public static final BusEventType<PeriodicEvent> ANY = new BusEventType<>(PeriodicEvent.class.getName() + "ANY");

	private int repeatingTimes;
	private double interval;
	private Callback callback;

	public PeriodicEvent(int repeatingTimes, double interval, Callback callback) {
		super(ANY);
		this.repeatingTimes = repeatingTimes;
		this.interval = interval;
		this.callback = callback;
	}

	public int getRepeatingTimes() {
		return repeatingTimes;
	}

	public double getInterval() {
		return interval;
	}

	public Callback getCallback() {
		return callback;
	}

}
