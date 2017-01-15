package model;

import java.io.Serializable;

import model.tree.GLink;
import model.tree.GNode;

/**
 * Represents a node in the WorkspaceTree that does not contain any data but
 * represents and mimics a GeRuDocument.
 * 
 * @author Ognjen Djuricic
 *
 */
public class GeRuDocumentLink extends GLink implements Serializable {

	/**
	 * Constructor.
	 * 
	 * @param node
	 *            The GeRuDocument that this link represents.
	 */
	public GeRuDocumentLink(GNode node) {
		super(node);
	}
}
