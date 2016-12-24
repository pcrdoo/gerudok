package constants;

import java.awt.Dimension;

public final class Constants {

    private Constants() {
            // Jok
    }

    public static final Dimension WINDOW_SIZE = new Dimension(1600, 900);
    public static final Dimension TREE_SIZE = new Dimension(250, 900);
    public static final Dimension INTERNAL_FRAME_SIZE = new Dimension(770, 770);
    private static final int DOCUMENT_WIDTH = 480;
    public static final int DOCUMENT_BOTTOM_MARGIN = 15;
    public static final Dimension DOCUMENT_SIZE = new Dimension(DOCUMENT_WIDTH, DOCUMENT_BOTTOM_MARGIN + (int)Math.round(Math.sqrt(2) * DOCUMENT_WIDTH));
	public static final int DOCUMENT_TOP_MARGIN = 15;
	public static final int CASCADE_OFFSET = 10;
}