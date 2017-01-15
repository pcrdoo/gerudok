package clipboard.serialize;

import java.util.Set;
import java.util.HashMap;

import model.elements.GraphicShape;
import model.elements.GraphicShapeType;

/**
 * Serializer for graphic shape lists, as appear on the clipboard when
 * copying/cutting.
 * 
 * @author geomaster
 *
 */
public class GraphicElementClipboardSerializer {
	/**
	 * Factory for specific shape serializers.
	 */
	private GraphicShapeClipboardSerializerDeserializerFactory factory = new GraphicShapeClipboardSerializerDeserializerFactory();

	/**
	 * Serializes a set of shapes into a string that can be passed to
	 * GraphicElementClipboardDeserializer to reconstruct the original set of
	 * shapes.
	 * 
	 * @param shapes
	 *            Shape set to serialize
	 * @return Serialized string
	 */
	public String serialize(Set<GraphicShape> shapes) {
		StringBuilder result = new StringBuilder();
		result.append("GeRuDokShapes[" + shapes.size() + "]\n");
		HashMap<GraphicShapeType, GraphicShapeClipboardSerializer> serializerMap = new HashMap<>();

		for (GraphicShape s : shapes) {
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
