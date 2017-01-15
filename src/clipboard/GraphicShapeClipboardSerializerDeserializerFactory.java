package clipboard;
import model.elements.GraphicShapeType;

public class GraphicShapeClipboardSerializerDeserializerFactory {
	public GraphicShapeClipboardSerializer createSerializer(GraphicShapeType type)
	{
		switch(type)
		{
		case RECTANGLE: return new GraphicRectangleShapeClipboardSerializer();
		case ELLIPSE:   return new GraphicEllipseShapeClipboardSerializer();
		case TRIANGLE:  return new GraphicTriangleShapeClipboardSerializer();
		default:        return null;
		}
	}
	
	public GraphicShapeClipboardDeserializer createDeserializer(GraphicShapeType type)
	{
		switch(type)
		{
		case RECTANGLE: return new GraphicRectangleShapeClipboardDeserializer();
		case ELLIPSE:   return new GraphicEllipseShapeClipboardDeserializer();
		case TRIANGLE:  return new GraphicTriangleShapeClipboardDeserializer();
		default:        return null;
		}
	}
}
