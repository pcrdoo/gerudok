package command.elements;

import command.Command;
import command.Invoker;

import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

/**
 * Command invoker for graphic element related commands, supporting undo and
 * redo with a fixed maximum history size. It can also keep track of an edit
 * session and remove its commands from the undo history if the session is
 * cancelled.
 * 
 * @author geomaster
 */
public class GraphicElementInvoker {
	/**
	 * Maximum amount of commands to keep in the undo history.
	 */
	private static final int MAX_HISTORY_SIZE = 50;

	/**
	 * Singleton instance.
	 */
	private static GraphicElementInvoker instance;

	/**
	 * History of previous commands. At each point, the back of this deque is
	 * the command that was executed immediately before.
	 */
	private Deque<GraphicElementCommand> history = new ArrayDeque<>();

	/**
	 * Stack of undone commands that can be redone. At the back of the deque is
	 * the most recently undone command.
	 */
	private Deque<GraphicElementCommand> redoStack = new ArrayDeque<>();

	/**
	 * List of registered listeners for undo/redo status (enabled/disabled)
	 * changes.
	 */
	private List<UndoRedoStatusListener> listeners = new ArrayList<>();

	/**
	 * Whether undo was enabled when the listeners were last updated
	 */
	private boolean lastUndoEnabledState = false;

	/**
	 * Whether redo was enabled when the listeners were last updated
	 */
	private boolean lastRedoEnabledState = false;

	/**
	 * Most recent command in the undo history at the time when the current
	 * session was started.
	 */
	private GraphicElementCommand lastBeforeSessionStart = null;

	/**
	 * Constructor. This is a singleton - do not use directly.
	 */
	private GraphicElementInvoker() {
		super();
	}

	/**
	 * Gets the singleton instance of GraphicElementInvoker.
	 * 
	 * @return Instance
	 */
	public static GraphicElementInvoker getInstance() {
		if (instance == null) {
			instance = new GraphicElementInvoker();
			// init
		}
		return (GraphicElementInvoker) instance;
	}

	/**
	 * Executes a command and records it in the undo history.
	 * 
	 * @param command
	 *            Command to execute
	 */
	public void executeCommand(GraphicElementCommand command) {
		command.doCommand();

		// Add the command even if it's not undoable, as that may change later
		makeSpaceIfFull();
		history.addLast(command);
		redoStack.clear();

		fireEventIfChanged();
	}

	/**
	 * Removes the oldest command from the undo history to make space for the
	 * insertion of one new command
	 */
	private void makeSpaceIfFull() {
		if (history.size() >= MAX_HISTORY_SIZE) {
			history.removeFirst();
		}
	}

	/**
	 * Registers an undo/redo status listener.
	 * 
	 * @param listener
	 *            Listener to register
	 */
	public void registerUndoRedoStatusListener(UndoRedoStatusListener listener) {
		listeners.add(listener);
	}

	/**
	 * Unregisters a previously registered undo/redo status listener.
	 * 
	 * @param listener
	 *            Listener to unregister
	 */
	public void unregisterUndoRedoStatusListener(UndoRedoStatusListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Checks if undo is currently possible. Undo is not possible iff there are
	 * no previous commands in the history.
	 * 
	 * @return true if undo is currently possible, false if not
	 */
	public boolean canUndo() {
		return history.size() > 0;
	}

	/**
	 * Checks if redo is currently possible. Redo is not possible iff undo was
	 * never done or if commands have been executed since the last undo
	 * operation or if there are no newer commands than the last redone command.
	 * 
	 * @return true if undo is currently possible, false if not
	 */
	public boolean canRedo() {
		return redoStack.size() > 0;
	}

	/**
	 * Starts a session. All commands from hereafter will be kept track of and
	 * can be removed from the undo history using abortSession().
	 */
	public void startSession() {
		lastBeforeSessionStart = history.isEmpty() ? null : history.getLast();
	}

	/**
	 * Aborts a session previously started using startSession(). All commands
	 * executed since startSession() will be removed from history, and the state
	 * of the invoker will be reverted to the one immediately preceeding the
	 * startSession() call.
	 */
	public void abortSession() {
		if (lastBeforeSessionStart != null) {
			while (!history.isEmpty() && history.getLast() != lastBeforeSessionStart) {
				history.removeLast();
			}

			lastBeforeSessionStart = null;
		}
		redoStack.clear();
	}

	/**
	 * Notifies the listeners of undo/redo status change, if there was one.
	 */
	private void fireEventIfChanged() {
		boolean undoStatus = canUndo();
		boolean redoStatus = canRedo();
		if (lastUndoEnabledState != undoStatus) {
			for (UndoRedoStatusListener l : listeners) {
				l.undoStatusChanged(undoStatus);
			}
		}

		if (lastRedoEnabledState != redoStatus) {
			for (UndoRedoStatusListener l : listeners) {
				l.redoStatusChanged(redoStatus);
			}
		}

		lastUndoEnabledState = undoStatus;
		lastRedoEnabledState = redoStatus;
	}

	/**
	 * Undoes the last command.
	 * 
	 * @throws Exception
	 *             If the command throws an exception during the undoing
	 */
	public void undoCommand() throws Exception {
		if (canUndo()) {
			// The order of these operations is important in case
			// c.undoCommand() throws
			while (canUndo() && !history.getLast().isUndoable()) {
				history.removeLast();
			}
			GraphicElementCommand c = history.removeLast();
			c.undoCommand();
			redoStack.addLast(c);
		}

		fireEventIfChanged();
	}

	/**
	 * Redoes the last undone command.
	 */
	public void redoCommand() {
		if (canRedo()) {
			while (canRedo() && !redoStack.getLast().isRedoable()) {
				redoStack.removeLast();
			}
			GraphicElementCommand c = redoStack.removeLast();
			makeSpaceIfFull();
			history.addLast(c);
			c.doCommand();
		}

		fireEventIfChanged();
	}
}
