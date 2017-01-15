package clipboard;

import java.util.Set;
import java.util.HashMap;

import model.elements.GraphicShape;
import model.elements.GraphicShapeType;

public class GraphicElementClipboardSerializer {
	private GraphicShapeClipboardSerializerDeserializerFactory factory = new GraphicShapeClipboardSerializerDeserializerFactory();
	
	public GraphicElementClipboardSerializer()
	{
		
	}
	
	String serialize(Set<GraphicShape> shapes)
	{
		StringBuilder result = new StringBuilder();
		result.append("GeRuDokShapes[" + shapes.size() + "]\n");
		HashMap<GraphicShapeType, GraphicShapeClipboardSerializer> serializerMap = new HashMap<>();
		
		for (GraphicShape s: shapes) {
			GraphicShapeClipboardSerializer serializer;
			GraphicShapeType t = s.getType();
			if (!serializerMap.containsKey(t)) {
				serializer = factory.createSerializer(s.getType());
				serializerMap.put(t, serializer);
			} else {
				serializer = serializerMap.get(t);
			}
			
			result.append(t + "\n");
			serializer.serialize(s, result);
			result.append('\n');
		}
		
		return result.toString();
	}
}
