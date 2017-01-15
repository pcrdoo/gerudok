package clipboard;
import model.elements.GraphicShape;

public interface GraphicShapeClipboardSerializer {
	public void serialize(GraphicShape shape, StringBuilder destination);
}
