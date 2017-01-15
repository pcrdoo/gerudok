package clipboard;
import model.elements.GraphicShape;
import model.elements.GraphicRectangleShape;

public class GraphicRectangleShapeClipboardSerializer implements GraphicShapeClipboardSerializer {
	public void serialize(GraphicShape shape, StringBuilder sb) {
		GraphicRectangleShape s = (GraphicRectangleShape) shape;
		new RectangleSerializer().serialize(s.getRectangle(), sb);
	}
}
