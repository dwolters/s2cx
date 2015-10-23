package de.upb.s2cx.event;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import de.upb.s2cx.parser.sql.Queryable;
import de.upb.s2cx.query.QueryTree;
import de.upb.s2cx.query.referencetree.ElementNode;
import de.upb.s2cx.query.referencetree.KleeneNode;
import de.upb.s2cx.query.referencetree.OptionalNode;
import de.upb.s2cx.query.referencetree.QueryableNode;
import de.upb.s2cx.query.referencetree.ReferenceTreeNode;
import de.upb.s2cx.query.referencetree.StringNode;
import de.upb.s2cx.query.referencetree.TreeEvent;
import de.upb.s2cx.query.resultset.IDataQueryResultSet;

public class EventGenerator {

	protected ReferenceTreeNode referenceTree;

	protected IDataQueryResultSet rs;

	private Stack<QueryTree> currentQuery;

	protected Stack<ReferenceTreeNode> path = new Stack<ReferenceTreeNode>();

	protected Stack<String> xmlContext = new Stack<String>();

	protected TreeEvent lastEvent = TreeEvent.FIRST_CHILD;

	private List<IBufferedEvent> bufferList;

	private boolean buffer = false;

	private IEventHandler handler;

	public EventGenerator(IDataQueryResultSet rs, ReferenceTreeNode referenceTree) throws SQLException {
		super();
		this.referenceTree = referenceTree;
		bufferList = new ArrayList<IBufferedEvent>();
		this.rs = rs;
	}

	public void setHandler(IEventHandler handler) {
		this.handler = handler;
	}

	public ReferenceTreeNode getReferenceTree() {
		return referenceTree;
	}

	public IDataQueryResultSet getResultSet() {
		return rs;
	}

	public void execute() throws SQLException {
		currentQuery = new Stack<QueryTree>();
		ReferenceTreeNode current = referenceTree;
		path.clear();
		path.add(current);
		xmlContext.clear();
		lastEvent = TreeEvent.FIRST_CHILD;
		handler.init();
		while (!path.isEmpty()) {
			current = path.peek();
			if (current instanceof KleeneNode) {
				QueryTree q = ((KleeneNode) current).getQuery();
				processQueryNode(q);
			} else if (current instanceof OptionalNode) {
				if (lastEvent == TreeEvent.PARENT) {
					clearBuffer((OptionalNode) current);
					nextSibling();
				} else {
					startBuffering((OptionalNode) current);
					next();
				}
			} else {
				if (current instanceof ElementNode) {
					if (lastEvent != TreeEvent.PARENT) {
						xmlContext.push(current.getLabel());
						fireElement(current.getLabel(), true);
					} else {
						fireElement(current.getLabel(), false);
					}
				} else if (current instanceof StringNode) {
					fireStringNodeEvent(current.getLabel(), xmlContext.peek());
				} else if (current instanceof QueryableNode) {
					fireQueryableNodeEvent(currentQuery.peek(), ((QueryableNode) current).getQueryable(), xmlContext.peek());
				}
				if (lastEvent == TreeEvent.PARENT) {
					nextSibling();
				} else {
					next();
				}
			}
		}
	}

	private void startBuffering(OptionalNode node) {
		buffer = true;
		bufferList.add(new OptionalMarkerEvent(node));
		bufferList.add(new OptionalEvent(true));
	}

	private void clearBuffer(OptionalNode node) {
		if (buffer) {
			List<IBufferedEvent> newBuffer = new ArrayList<IBufferedEvent>();
			for (IBufferedEvent e : bufferList) {
				if (e instanceof OptionalMarkerEvent && ((OptionalMarkerEvent) e).getNode() == node) {
					break;
				}
				newBuffer.add(e);
			}
			bufferList = newBuffer;
			buffer = !bufferList.isEmpty();
			if (buffer) {
				bufferList.add(new OptionalOmitEvent());
			} else {
				handler.onOptionalOmit();
			}
		} else {
			handler.onOptionalEnd();
		}
	}

	private void flush() {
		if (buffer) {
			for (IBufferedEvent e : bufferList) {
				e.fire(handler);
			}
			bufferList.clear();
			buffer = false;
		}
	}

	private void buffer(IBufferedEvent e) {
		bufferList.add(e);
	}

	private void fireQueryableNodeEvent(QueryTree query, Queryable queryable, String context) throws SQLException {
		String s = rs.getValue(query, queryable);
		if (s == null) {
			s = "";
		} else {
			flush();
		}
		if (buffer) {
			buffer(new TextEvent(s, context));
		} else {
			handler.onText(s, context);
		}

	}

	private void fireStringNodeEvent(String string, String context) {
		if (buffer) {
			buffer(new TextEvent(string, context));
		} else {
			handler.onText(string, context);
		}

	}

	private void fireElement(String label, boolean start) {
		if (buffer) {
			buffer(new ElementEvent(label, start));
		} else {
			handler.onElement(label, start);
		}

	}

	private void fireKleeneStartEvent(QueryTree q) {
		if (buffer) {
			buffer(new KleeneStartEvent(q));
		} else {
			handler.onKleeneStart(q);
		}

	}

	private void fireKleeneEndEvent(QueryTree query) {
		if (buffer) {
			buffer(new KleeneEvent(query, false));
		} else {
			handler.onKleeneEnd(query);
		}
	}

	private void fireKleeneNextEvent(QueryTree query) {
		if (buffer) {
			buffer(new KleeneEvent(query, true));
		} else {
			handler.onKleeneNext(query);
		}
	}

	private void processQueryNode(QueryTree q) throws SQLException {
		if (lastEvent != TreeEvent.PARENT) {
			currentQuery.push(q);
			fireKleeneStartEvent(q);
		} else {
			rs.next(q);
		}
		if (rs.hasData(q)) {
			fireKleeneNextEvent(currentQuery.peek());
			flush();
			if (!firstChild()) {
				throw new RuntimeException("There should always be a first child of a kleene node");
			}
		} else {
			fireKleeneEndEvent(currentQuery.peek());
			nextSibling();
			currentQuery.pop();
		}
	}

	private boolean firstChild() {
		ReferenceTreeNode node = path.peek().getFirstChild();
		if (node == null) {
			return false;
		}
		path.add(node);
		lastEvent = TreeEvent.FIRST_CHILD;
		return true;
	}

	private void nextSibling() {
		ReferenceTreeNode node = path.peek().getNextSibling();
		if (node == null) {
			lastEvent = TreeEvent.PARENT;
			ReferenceTreeNode pop;
			do {
				pop = path.pop();
				if (pop instanceof ElementNode) {
					xmlContext.pop();
				}
			} while (!path.isEmpty() && pop != path.peek().getFirstChild());

		} else {
			path.add(node);
			lastEvent = TreeEvent.NEXT_SIBLING;
		}
	}

	private void next() {
		if (!firstChild()) {
			nextSibling();
		}
	}
}
