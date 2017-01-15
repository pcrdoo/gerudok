package clipboard;
import model.elements.GraphicShape;
import model.elements.GraphicEllipseShape;

public class GraphicEllipseShapeClipboardSerializer implements GraphicShapeClipboardSerializer {
	public void serialize(GraphicShape shape, StringBuilder sb) {
		GraphicEllipseShape s = (GraphicEllipseShape) shape;
		new RectangleSerializer().serialize(s.getBounds(), sb);
	}
}
