package view.elements;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;

import controller.elements.GraphicElementToolBarController;
import model.Model;
import model.elements.GraphicElement;
import model.elements.GraphicElementToolBarAction;
import state.GraphicElementStateManager;

import java.util.*;

public class GraphicElementToolBarView extends JToolBar {
	GraphicElementToolBarController controller;
	Map<GraphicElementToolBarAction, ActionListener> listeners = new HashMap<>();
	Map<GraphicElementToolBarAction, JButton> buttons = new HashMap<>();
	
	public GraphicElementToolBarView(Model model, GraphicElement element, GraphicElementStateManager stateManager, GraphicCanvasView canvas)
	{
		super();
		
		final String[] labels = {
				"Select",
				"Lasso Select",
				"Undo",
				"Redo",
				"Move",
				"Remove",
				"Cut",
				"Copy",
				"Paste",
				"Rectangle",
				"Ellipse",
				"Triangle"
		};
		
		final int[] separators = {
				0,
				1,
				0,
				1,
				0,
				1,
				0,
				0,
				1,
				0,
				0,
				0
		};
		
		final GraphicElementToolBarAction[] actions = {
				GraphicElementToolBarAction.SELECT,
				GraphicElementToolBarAction.LASSO_SELECT,
				GraphicElementToolBarAction.UNDO,
				GraphicElementToolBarAction.REDO,
				GraphicElementToolBarAction.MOVE,
				GraphicElementToolBarAction.REMOVE,
				GraphicElementToolBarAction.CUT,
				GraphicElementToolBarAction.COPY,
				GraphicElementToolBarAction.PASTE,
				GraphicElementToolBarAction.RECTANGLE,
				GraphicElementToolBarAction.ELLIPSE,
				GraphicElementToolBarAction.TRIANGLE
		};
		
		final String[] filenames = {
				"select.png",
				"multiselect.png",
				"undo.png",
				"redo.png",
				"move.png",
				"delete.png",
				"cut.png",
				"copy.png",
				"paste.png",
				"rectangle.png",
				"ellipse.png",
				"triangle.png"
		};
		
		final String filenamePrefix = "src/res/graphic_edit_dialog/";
		
		for (int i = 0; i < labels.length; i++) {
			JButton button = new JButton();
			button.setToolTipText(labels[i]);
			button.setIcon(new ImageIcon(filenamePrefix + filenames[i]));
			
			final int j = i;
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (listeners.containsKey(actions[j])) {
						listeners.get(actions[j]).actionPerformed(e);
					}
				}
			});
			
			buttons.put(actions[i], button);
			add(button);
			
			if (separators[i] != 0) {
				addSeparator();
			}
		}
		
		controller = new GraphicElementToolBarController(model, element, this, stateManager, canvas);
	}
	
	public void setUndoEnabled(boolean enabled)
	{
		buttons.get(GraphicElementToolBarAction.UNDO).setEnabled(enabled);
	}
	
	public void setRedoEnabled(boolean enabled)
	{
		buttons.get(GraphicElementToolBarAction.REDO).setEnabled(enabled);
	}
	
	public void registerActionListener(GraphicElementToolBarAction action, ActionListener l)
	{
		listeners.put(action, l);
	}
	
}
