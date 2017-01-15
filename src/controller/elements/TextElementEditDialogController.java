package controller.elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import command.Invoker;
import command.elements.TextElementEditCommitCommand;
import command.elements.TextElementEditCancelCommand;
import model.Model;
import view.elements.TextElementEditDialog;

public class TextElementEditDialogController {
   public Model model;
   public TextElementEditDialog dialog;
   private String oldHtml;
   
   public TextElementEditDialogController(Model model, TextElementEditDialog dialog)
   {
	   this.model = model;
	   this.dialog = dialog;
	   this.oldHtml = dialog.getTextElement().getHtml();
	   dialog.addOkListener(new OkListener());
	   dialog.addCancelListener(new CancelListener());
	   dialog.addKeyListener(new TextChangeListener());
   }
   
	class OkListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new TextElementEditCommitCommand(model, dialog.getTextElement(), dialog.getHtml()));
			dialog.hide();
		}
	}
	
	class CancelListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new TextElementEditCancelCommand(model, dialog.getTextElement(), oldHtml));
			dialog.hide();
		}
	}
	
	class TextChangeListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
			dialog.getTextElement().setHtml(dialog.getHtml() + (e.isActionKey() ? "" : e.getKeyChar()));
		}
		
		public void keyPressed(KeyEvent e) { }
		public void keyReleased(KeyEvent e) { }
	}
}
