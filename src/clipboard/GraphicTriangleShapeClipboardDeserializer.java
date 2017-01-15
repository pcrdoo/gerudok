package clipboard;
import model.elements.GraphicShape;
import model.elements.GraphicTriangleShape;

public class GraphicTriangleShapeClipboardDeserializer implements GraphicShapeClipboardDeserializer {
	public GraphicShape deserialize(String serializedString) throws SerializationDeserializationException
	{
		return new GraphicTriangleShape(new RectangleDeserializer().deserialize(serializedString));
	}
}
