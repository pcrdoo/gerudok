package view;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import controller.GraphicElementToolBarController;
import controller.GraphicElementStateManager;
import model.GraphicElementToolBarAction;
import model.Model;
import java.util.*;

public class GraphicElementToolBarView extends JToolBar {
	GraphicElementToolBarController controller;
	Map<GraphicElementToolBarAction, ActionListener> listeners = new HashMap<>();
	
	public GraphicElementToolBarView(Model model, GraphicElementStateManager stateManager, GraphicCanvasView canvas)
	{
		super();
		controller = new GraphicElementToolBarController(model, this, stateManager, canvas);
		
		final String[] labels = {
				"Select",
				"Lasso Select",
				"Move",
				"Remove",
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
				0,
				0
		};
		
		final GraphicElementToolBarAction[] actions = {
				GraphicElementToolBarAction.SELECT,
				GraphicElementToolBarAction.LASSO_SELECT,
				GraphicElementToolBarAction.MOVE,
				GraphicElementToolBarAction.REMOVE,
				GraphicElementToolBarAction.RECTANGLE,
				GraphicElementToolBarAction.ELLIPSE,
				GraphicElementToolBarAction.TRIANGLE
		};
		
		final String[] filenames = {
				"select.png",
				"multiselect.png",
				"move.png",
				"delete.png",
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
			
			add(button);
			
			if (separators[i] != 0) {
				addSeparator();
			}
		}
	}
	
	public void registerActionListener(GraphicElementToolBarAction action, ActionListener l)
	{
		listeners.put(action, l);
	}
	
}
