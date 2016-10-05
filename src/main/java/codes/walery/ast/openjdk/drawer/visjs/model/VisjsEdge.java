package codes.walery.ast.openjdk.drawer.visjs.model;

// TODO lombok
// TODO ignore null when convert to json
public class VisjsEdge<T> {
	private T from;
	private T to;
	private String label;

	public T getFrom() {
		return from;
	}

	public void setFrom(final T from) {
		this.from = from;
	}

	public T getTo() {
		return to;
	}

	public void setTo(final T to) {
		this.to = to;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(final String label) {
		this.label = label;
	}
}
