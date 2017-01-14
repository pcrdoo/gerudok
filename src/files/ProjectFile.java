package files;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import constants.Constants;

public class ProjectFile extends FileFilter{
	
	@Override
	public boolean accept(File f) {
		return (f.isDirectory() || f.getName().endsWith(Constants.PROJECT_EXT));
	}
	
	@Override
	public java.lang.String getDescription() {
		return "GeRuDok project (" + Constants.PROJECT_EXT + ")";
	}
	
}
