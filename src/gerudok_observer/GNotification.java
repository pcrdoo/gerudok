package gerudok_observer;

/**
 * Notifications used by GObservable to label sent messsages.
 * 
 * @author Nikola Jovanovic
 *
 */
public enum GNotification {
	/**
	 * Represents the node addition event.
	 */
	ADD,
	/**
	 * Represents the node deletion event.
	 */
	DELETE,
	/**
	 * Represents the project closing event.
	 */
	PROJECT_CLOSE,
	/**
	 * Represents the project opening event.
	 */
	PROJECT_OPEN,
	/**
	 * Represents the propagation of rename events to the tree.
	 */
	TREE_RENAME,
	/**
	 * Represents the propagation of select events to the tree.
	 */
	TREE_SELECT,
	/**
	 * Represents the propagation of rename events to the desktop.
	 */
	DESKTOP_SELECT,
	/**
	 * Represents the event of an immediate element child's contents changing.
	 */
	ELEMENT_EDIT,
	/**
	 * Represents the rename event.
	 */
	GNODE_RENAME,
	/**
	 * Represents the change in free nodes list.
	 */
	FREE_NODES_CHANGED;
}
