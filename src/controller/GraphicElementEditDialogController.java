package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import command.Invoker;
import command.GraphicElementEditCommitCommand;
import controller.GraphicElementEditDialogController.CancelListener;
import controller.GraphicElementEditDialogController.OkListener;
import model.Model;
import view.GraphicElementEditDialog;

public class GraphicElementEditDialogController {
   public Model model;
   public GraphicElementEditDialog dialog;
   
   public GraphicElementEditDialogController(Model model, GraphicElementEditDialog dialog)
   {
	   this.model = model;
	   this.dialog = dialog;
	   dialog.addOkListener(new OkListener());
	   dialog.addCancelListener(new CancelListener());
   }
   
	class OkListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			GraphicElementEditCommitCommand command = new GraphicElementEditCommitCommand(model, dialog.getGraphicElement(), dialog.getShapes());
			Invoker.getInstance().executeCommand(command);
			dialog.hide();
		}
	}
	
	class CancelListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dialog.hide();
		}
	}
}
