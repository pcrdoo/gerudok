package files;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import constants.Constants;
/**
 * File filter for GeRuDok workspace (grdw) extension
 * 
 * @author Igor Bakovic
 */
public class WorkspaceFile extends FileFilter{
	
	@Override
	public boolean accept(File f) {
		return (f.isDirectory() || f.getName().endsWith(Constants.WORKSPACE_EXT));
	}
	
	@Override
	public java.lang.String getDescription() {
		return "GeRuDok workspace (" + Constants.WORKSPACE_EXT + ")";
	}
	
}
