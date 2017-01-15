package clipboard;
import model.elements.GraphicShape;

public interface GraphicShapeClipboardDeserializer {
	public GraphicShape deserialize(String serializedString) throws SerializationDeserializationException;
}
