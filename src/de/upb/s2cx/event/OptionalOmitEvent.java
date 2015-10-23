package de.upb.s2cx.event;

public class OptionalOmitEvent implements IBufferedEvent {

	public OptionalOmitEvent() {
		super();
	}

	@Override
	public void fire(IEventHandler exec) {
		exec.onOptionalOmit();
	}

}
