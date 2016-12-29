package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import command.TextElementEditCommitCommand;
import command.Invoker;
import controller.ElementController.DoubleClickListener;
import model.Model;
import view.TextElementEditDialog;

public class TextElementEditDialogController {
   public Model model;
   public TextElementEditDialog dialog;
   
   public TextElementEditDialogController(Model model, TextElementEditDialog dialog)
   {
	   this.model = model;
	   this.dialog = dialog;
	   dialog.addOkListener(new OkListener());
	   dialog.addCancelListener(new CancelListener());
   }
   
	class OkListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			TextElementEditCommitCommand command = new TextElementEditCommitCommand(model, dialog.getTextElement(), dialog.getHtml());
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
