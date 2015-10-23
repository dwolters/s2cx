package de.upb.s2cx.event;

public class OptionalEvent implements IBufferedEvent {
	private boolean start;

	public OptionalEvent(boolean start) {
		super();
		this.start = start;
	}

	@Override
	public void fire(IEventHandler exec) {
		if (start) {
			exec.onOptionalStart();
		} else {
			exec.onOptionalEnd();
		}
	}

}
