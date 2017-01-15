package clipboard;
import model.elements.GraphicShape;
import model.elements.GraphicTriangleShape;

public class GraphicTriangleShapeClipboardSerializer implements GraphicShapeClipboardSerializer {
	public void serialize(GraphicShape shape, StringBuilder sb) {
		GraphicTriangleShape s = (GraphicTriangleShape) shape;
		new RectangleSerializer().serialize(s.getBounds(), sb);
	}
}