package Resources;

import java.awt.Dimension;
import java.awt.*;

public class GlobalConstants {
	public static final String APPLICATION_NAME = "Fotag";
	public static final String LOAD_ICON = "load.png";
    public static final String RESOURCES_PATH = "/Resources/";
    public static final Dimension FULL_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static final Dimension SCREEN_SIZE = new Dimension(800, 600);
    public static final Dimension MINIMUM_SIZE = new Dimension(430, 453);
    public static final Dimension SCROLL_SIZE = new Dimension(SCREEN_SIZE.width, MINIMUM_SIZE.height);
    public static final int MAX_NAME_DISPLAY = 560;
    public static final int TITLE_FONT_SIZE = 50;
    public static final int BORDER_PADDING = 10;
    public static final int STAR_SIZE = 35;
    public static final String STAR = "\u2606";
    public static final String FILLED_STAR = "\u2605";
    public static final String DATE_FORMAT = "MM-dd-yyyy";
    public static final String SIZE_FORMAT = "0.00";
    public static final int THUMBNAIL_HEIGHT = 200;
    public static final int THUMBNAIL_WIDTH = 100;
}