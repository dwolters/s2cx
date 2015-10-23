package de.upb.s2cx.event;

public class ElementEvent implements IBufferedEvent {
	private String name;
	private boolean start;

	public ElementEvent(String name, boolean start) {
		super();
		this.name = name;
		this.start = start;
	}

	@Override
	public void fire(IEventHandler exec) {
		exec.onElement(name, start);
	}

}
