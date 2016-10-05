package codes.walery.ast.openjdk.drawer.visjs.model;

import java.util.Map;

// TODO lombok
// TODO ignore null when convert to json
// TODO rename?
public class NodeProps {
	private int start;
	private int end;
	private Map<String, ?> otherProps;

	public int getStart() {
		return start;
	}

	public void setStart(final int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(final int end) {
		this.end = end;
	}

	public Map<String, ?> getOtherProps() {
		return otherProps;
	}

	public void setOtherProps(final Map<String, ?> otherProps) {
		this.otherProps = otherProps;
	}
}
