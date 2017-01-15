package clipboard;
import model.elements.GraphicShape;
import model.elements.GraphicEllipseShape;

public class GraphicEllipseShapeClipboardDeserializer implements GraphicShapeClipboardDeserializer {
	public GraphicShape deserialize(String serializedString) throws SerializationDeserializationException
	{
		return new GraphicEllipseShape(new RectangleDeserializer().deserialize(serializedString));
	}
}
