package de.upb.s2cx.event;

import de.upb.s2cx.query.QueryTree;

public class KleeneStartEvent implements IBufferedEvent {

	private QueryTree query;

	public KleeneStartEvent(QueryTree query) {
		super();
		this.query = query;
	}

	@Override
	public void fire(IEventHandler exec) {
		exec.onKleeneStart(query);

	}

}
