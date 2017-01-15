package command.elements;

public interface UndoRedoStatusListener {
	public void undoStatusChanged(boolean undoEnabled);
	public void redoStatusChanged(boolean redoEnabled);
}
