package clipboard;
import model.elements.Rectangle;

public class RectangleSerializer {
	public RectangleSerializer() {
		
	}
	
	void serialize(Rectangle r, StringBuilder sb) {
		sb.append("rect[x=");
		sb.append(r.getOrigin().getX());
		sb.append(",y=");
		sb.append(r.getOrigin().getY());
		sb.append(",w=");
		sb.append(r.getWidth());
		sb.append(",h=");
		sb.append(r.getHeight());
		sb.append("]");
	}
}
