package constants;

import java.awt.Dimension;

/**
 * Contains all global constants, mostly related to GUI element dimensions.
 * 
 * @author Nikola Jovanovic
 *
 */
public final class Constants {

	/**
	 * Preventing class instantiation.
	 */
	private Constants() {

	}

	/**
	 * Application window size.
	 */
	public static final Dimension WINDOW_SIZE = new Dimension(1600, 900);
	/**
	 * Size of the tree area.
	 */
	public static final Dimension TREE_SIZE = new Dimension(250, 900);
	/**
	 * Tree view height.
	 */
	public static final int TREE_VIEW_HEIGHT = 900;
	/**
	 * Tree pane height factor.
	 */
	public static final double TREE_PANE_FACTOR = 0.65;
	/**
	 * Free documents list height factor.
	 */
	public static final double FREE_DOC_FACTOR = 0.2;
	/**
	 * Size of each of the internal frames that represent projects.
	 */
	public static final Dimension INTERNAL_FRAME_SIZE = new Dimension(770, 770);
	/**
	 * Bottom margin to apply when displaying a page.
	 */
	public static final int PAGE_BOTTOM_MARGIN = 15;
	/**
	 * Top margin to apply when displaying a page.
	 */
	public static final int PAGE_TOP_MARGIN = 15;
	/**
	 * Width of a page within a document.
	 */
	public static final int PAGE_WIDTH = 480;
	/**
	 * Height of a page within a document.
	 */
	public static final int PAGE_HEIGHT = (int) Math.round(Math.sqrt(2) * PAGE_WIDTH);
	/**
	 * Size of a page within a document.
	 */
	public static final Dimension PAGE_SIZE = new Dimension(PAGE_WIDTH,
			PAGE_TOP_MARGIN + PAGE_BOTTOM_MARGIN + PAGE_HEIGHT);
	/**
	 * Offset used when cascading internal frames.
	 */
	public static final int CASCADE_OFFSET = 10;
	/**
	 * GeRuDok project file extension.
	 */
	public static final String PROJECT_EXT = ".grdp";
	/**
	 * GeRuDok workspace file extension.
	 */
	public static final String WORKSPACE_EXT = ".grdw";
}