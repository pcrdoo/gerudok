package command.elements;

/**
 * Listener for changes in the 'undo enabled' and 'redo enabled' status. Used by
 * the graphic element edit toolbar to enable/disable the toolbar icons for
 * these actions.
 * 
 * @author geomaster
 *
 */
public interface UndoRedoStatusListener {
	/**
	 * Called when the enabled status of undo is changed.
	 * 
	 * @param undoEnabled
	 *            Whether undo is now enabled
	 */
	public void undoStatusChanged(boolean undoEnabled);

	/**
	 * Called when the enabled status of redo is changed.
	 * 
	 * @param redoEnabled
	 *            Whether redo is now enabled
	 */
	public void redoStatusChanged(boolean redoEnabled);
}
