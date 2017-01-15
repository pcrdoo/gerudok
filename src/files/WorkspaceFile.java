package files;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import constants.Constants;

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
