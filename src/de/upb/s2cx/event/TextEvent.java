package de.upb.s2cx.event;

public class TextEvent implements IBufferedEvent {
	private String value;
	private String context;

	public TextEvent(String value, String context) {
		super();
		this.value = value;
		this.context = context;
	}

	@Override
	public void fire(IEventHandler exec) {
		System.out.println("onStringEvent(" + value + "," + context + ")");
		exec.onText(value, context);

	}

}
