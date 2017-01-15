package clipboard.serialize;

import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import model.elements.GraphicShape;
import model.elements.GraphicShapeType;

import java.util.regex.Pattern;

import clipboard.InvalidDataFormatException;
import clipboard.SerializationDeserializationException;
import clipboard.UnknownShapeTypeException;

import java.util.regex.Matcher;

/**
 * Deserializer for graphic shape lists, as appear on the clipboard when
 * copying/cutting.
 * 
 * @author geomaster
 *
 */
public class GraphicElementClipboardDeserializer {
	/**
	 * Factory for specific shape deserializers
	 */
	private GraphicShapeClipboardSerializerDeserializerFactory factory = new GraphicShapeClipboardSerializerDeserializerFactory();

	/**
	 * Deserializes a set of GraphicShape objects from a string previously
	 * created using GraphicElementClipboardSerializer.
	 * 
	 * @see clipboard.serialize.GraphicElementClipboardSerializer
	 * @param serializedString
	 *            Serialized string
	 * @return Deserialized result
	 * @throws SerializationDeserializationException
	 *             If the string is invalid
	 */
	public Set<GraphicShape> deserialize(String serializedString) throws SerializationDeserializationException {
		String[] lines = serializedString.split("\n");
		String header = lines[0];
		Pattern p = Pattern.compile("^GeRuDokShapes\\[(\\d+)\\]$");
		Matcher m = p.matcher(header);

		int n;
		if (m.find()) {
			n = Integer.parseInt(m.group(1));
		} else {
			throw new InvalidDataFormatException("Malformed header line");
		}

		HashMap<GraphicShapeType, GraphicShapeClipboardDeserializer> deserializerMap = new HashMap<>();
		HashSet<GraphicShape> result = new HashSet<>();
		for (int i = 1; i < 2 * n + 1; i += 2) {
			String typeStr = lines[i];
			GraphicShapeType t;
			try {
				t = GraphicShapeType.valueOf(typeStr);
			} catch (Exception e) {
				throw new UnknownShapeTypeException("Shape type `" + typeStr + "` is unknown");
			}

			GraphicShapeClipboardDeserializer deserializer;
			if (!deserializerMap.containsKey(t)) {
				deserializer = factory.createDeserializer(t);
				deserializerMap.put(t, deserializer);
			} else {
				deserializer = deserializerMap.get(t);
			}

			result.add(deserializer.deserialize(lines[i + 1]));
		}

		return result;
	}
}
