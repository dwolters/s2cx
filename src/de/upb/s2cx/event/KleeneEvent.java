package de.upb.s2cx.event;

import de.upb.s2cx.query.QueryTree;

public class KleeneEvent implements IBufferedEvent {
	private QueryTree query;
	private boolean avaiable;

	public KleeneEvent(QueryTree query, boolean avaiable) {
		super();
		this.query = query;
		this.avaiable = avaiable;
	}

	@Override
	public void fire(IEventHandler exec) {
		if (avaiable) {
			exec.onKleeneNext(query);
		} else {
			exec.onKleeneEnd(query);
		}
	}

}
