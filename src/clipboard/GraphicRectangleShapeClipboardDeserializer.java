package clipboard;
import model.elements.GraphicShape;
import model.elements.GraphicRectangleShape;

public class GraphicRectangleShapeClipboardDeserializer implements GraphicShapeClipboardDeserializer {
	public GraphicShape deserialize(String serializedString) throws SerializationDeserializationException
	{
		return new GraphicRectangleShape(new RectangleDeserializer().deserialize(serializedString));
	}
}
