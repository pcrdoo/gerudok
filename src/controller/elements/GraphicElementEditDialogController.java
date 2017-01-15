package controller.elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import command.Invoker;
import command.elements.GraphicElementInvoker;
import command.elements.GraphicElementEditCancelCommand;
import command.elements.GraphicElementEditCommitCommand;
import controller.elements.GraphicElementEditDialogController.CancelListener;
import controller.elements.GraphicElementEditDialogController.OkListener;
import model.Model;
import model.elements.GraphicElement;
import model.elements.GraphicShape;
import view.elements.GraphicElementEditDialog;

public class GraphicElementEditDialogController {
   public Model model;
   public GraphicElementEditDialog dialog;
   private List<GraphicShape> previousShapes = new ArrayList<>();
   
   public GraphicElementEditDialogController(Model model, GraphicElementEditDialog dialog)
   {
	   this.model = model;
	   this.dialog = dialog;
	   dialog.addOkListener(new OkListener());
	   dialog.addCancelListener(new CancelListener());
	  
	   for (GraphicShape s : dialog.getGraphicElement().getShapes()) {
		   previousShapes.add(s.clone());
	   }
	   
	   GraphicElementInvoker.getInstance().startSession();
   }
   
	class OkListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new GraphicElementEditCommitCommand(model, dialog.getGraphicElement()));
			dialog.hide();
		}
	}
	
	class CancelListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new GraphicElementEditCancelCommand(model, dialog.getGraphicElement(), previousShapes));
			GraphicElementInvoker.getInstance().abortSession();
			dialog.hide();
		}
	}
}
