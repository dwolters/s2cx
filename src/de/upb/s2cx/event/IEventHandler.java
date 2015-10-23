package de.upb.s2cx.event;

import de.upb.s2cx.query.QueryTree;

public interface IEventHandler {
	void init();

	void onElement(String name, boolean start);

	void onText(String string, String context);

	void onOptionalOmit();

	void onOptionalStart();

	void onOptionalEnd();

	void onKleeneStart(QueryTree query);

	void onKleeneNext(QueryTree query);

	void onKleeneEnd(QueryTree query);

}
