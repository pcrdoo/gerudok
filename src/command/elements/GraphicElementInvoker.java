package command.elements;
import command.Command;
import command.Invoker;

import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class GraphicElementInvoker {
	private static GraphicElementInvoker instance;
	private static final int MAX_HISTORY_SIZE = 50;
	private Deque<GraphicElementCommand> history = new ArrayDeque<>();
	private Deque<GraphicElementCommand> redoStack = new ArrayDeque<>();
	private List<UndoRedoStatusListener> listeners = new ArrayList<>();
	private boolean lastUndoEnabledState = false;
	private boolean lastRedoEnabledState = false;
	private GraphicElementCommand lastBeforeSessionStart = null;
	
	private GraphicElementInvoker() {
		super();
	}

	public static GraphicElementInvoker getInstance() {
		if (instance == null) {
			instance = new GraphicElementInvoker();
			// init
		}
		return (GraphicElementInvoker) instance;
	}

	public void executeCommand(GraphicElementCommand command) {
		command.doCommand();
		
		if (command.isUndoable()) {
			makeSpaceIfFull();
			history.addLast(command);
			redoStack.clear();
			
			fireEventIfChanged();
		}
	}
	
	private void makeSpaceIfFull()
	{
		if (history.size() >= MAX_HISTORY_SIZE) {
			history.removeFirst();
		}		
	}
	
	public void registerUndoRedoStatusListener(UndoRedoStatusListener l) {
		listeners.add(l);
	}

	public boolean canUndo() {
		return history.size() > 0;
	}
	
	public boolean canRedo() {
		return redoStack.size() > 0;
	}
	
	public void startSession() {
		lastBeforeSessionStart = history.isEmpty() ? null : history.getLast();
	}
	
	public void abortSession() {
		if (lastBeforeSessionStart != null) {
			while (!history.isEmpty() && history.getLast() != lastBeforeSessionStart) {
				history.removeLast();
			}
			
			lastBeforeSessionStart = null;
		}
		redoStack.clear();
	}
	
	public void fireEventIfChanged()
	{
		boolean undoStatus = canUndo();
		boolean redoStatus = canRedo();
		if (lastUndoEnabledState != undoStatus) {
			for (UndoRedoStatusListener l: listeners) {
				l.undoStatusChanged(undoStatus);
			}
		}
		
		if (lastRedoEnabledState != redoStatus) {
			for (UndoRedoStatusListener l: listeners) {
				l.redoStatusChanged(redoStatus);
			}
		}
		
		lastUndoEnabledState = undoStatus;
		lastRedoEnabledState = redoStatus;
	}
	
	public void undoCommand() throws Exception {
		if (canUndo()) {
			// The order of these operations is important in case c.undoCommand() throws
			GraphicElementCommand c = history.removeLast();
			c.undoCommand();
			redoStack.addLast(c);
		}
		
		fireEventIfChanged();
	}
	
	public void redoCommand() {
		if (canRedo()) {
			GraphicElementCommand c = redoStack.removeLast();
			makeSpaceIfFull();
			history.addLast(c);
			c.doCommand();
		}
		
		fireEventIfChanged();
	}
}
