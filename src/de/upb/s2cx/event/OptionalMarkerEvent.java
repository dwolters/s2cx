package de.upb.s2cx.event;

import de.upb.s2cx.query.referencetree.OptionalNode;

public class OptionalMarkerEvent implements IBufferedEvent {
	private OptionalNode node;

	public OptionalMarkerEvent(OptionalNode node) {
		super();
		this.node = node;
	}

	@Override
	public void fire(IEventHandler exec) {
		// No event at this point. just a marker event
	}

	public OptionalNode getNode() {
		return node;
	}

}
