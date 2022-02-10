/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Feb 6, 2022 4:59:55 AM - TODO: указать состояние файла здесь.
 */
package JTerminal;

/**
 * @author jsmith
 *
 */
public final class TerminalMode
{
    // Если тут будут исправления, внести их и в класс TerminalModeStrings
    // Так как эти значения взяты оттуда, то чтобы они уже были одинаковы.

    /**
     * Terminal text style - reset to default
     */
    public static final int STYLE_RESET            = 0;

    /**
     * Terminal text style - bold (жирный)
     */
    public static final int STYLE_BOLD             = 1;

    /**
     * Terminal text style - dimmed (тусклый)
     */
    public static final int STYLE_DIM              = 2;

    /**
     * Terminal text style - italic (курсив)
     */
    public static final int STYLE_ITALIC           = 3;

    /**
     * Terminal text style - underline (подчеркнутый)
     */
    public static final int STYLE_UNDERLINE        = 4;

    /**
     * Terminal text style - blinked (мигающий)
     * Sets blinking to less than 150 times per minute
     */
    public static final int STYLE_BLINK            = 5;

    /**
     * Terminal text style - rapid blinked (быстро мигающий)
     */
    public static final int STYLE_RBLINK           = 6;

    /**
     * Terminal text style - swap fore and back colors
     */
    public static final int STYLE_REVERSED         = 7;

    /**
     * Terminal text style - hidden (скрытый, выводится цветом фона)
     */
    public static final int STYLE_CONCEAL          = 8;

    /**
     * Terminal text style - striked (зачеркнутый)
     */
    public static final int STYLE_CROSSED          = 9;

    // ------------------------------------------------------------
    /**
     * Terminal text color - black
     */
    public static final int COLOR_BLACK            = 30;

    /**
     * Terminal text color - red
     */
    public static final int COLOR_RED              = 31;

    /**
     * Terminal text color - green (зеленый)
     */
    public static final int COLOR_GREEN            = 32;

    /**
     * Terminal text color - yellow (желтый)
     */
    public static final int COLOR_YELLOW           = 33;

    /**
     * Terminal text color - blue
     */
    public static final int COLOR_BLUE             = 34;

    /**
     * Terminal text color - magenta (фиолетовый)
     */
    public static final int COLOR_MAGENTA          = 35;

    /**
     * Terminal text color - cyan
     */
    public static final int COLOR_CYAN             = 36;

    /**
     * Terminal text color - gray (серый)
     */
    public static final int COLOR_GRAY             = 37;

    /**
     * Terminal text color - reset text color
     */
    public static final int COLOR_RESET            = 39;

    // --------------------------------------------------------------
    /**
     * Terminal text color - bright black
     */
    public static final int COLOR_BRIGHT_BLACK     = 90;

    /**
     * Terminal text color - bright red (яркий красный)
     */
    public static final int COLOR_BRIGHT_RED       = 91;

    /**
     * Terminal text color - bright green
     */
    public static final int COLOR_BRIGHT_GREEN     = 92;

    /**
     * Terminal text color - bright yellow
     */
    public static final int COLOR_BRIGHT_YELLOW    = 93;

    /**
     * Terminal text color - bright blue
     */
    public static final int COLOR_BRIGHT_BLUE      = 94;

    /**
     * Terminal text color - bright magenta
     */
    public static final int COLOR_BRIGHT_MAGENTA   = 95;

    /**
     * Terminal text color - bright cyan (светло-синий)
     */
    public static final int COLOR_BRIGHT_CYAN      = 96;

    /**
     * Terminal text color - bright gray (Белый)
     */
    public static final int COLOR_BRIGHT_GRAY      = 97;

    // --------------------------------------------------------------------
    /**
     * Terminal back color - black
     */
    public static final int BGCOLOR_BLACK          = 40;

    /**
     * Terminal back color - red
     */
    public static final int BGCOLOR_RED            = 41;

    /**
     * Terminal back color - green
     */
    public static final int BGCOLOR_GREEN          = 42;

    /**
     * Terminal back color - yellow
     */
    public static final int BGCOLOR_YELLOW         = 43;

    /**
     * Terminal back color - blue
     */
    public static final int BGCOLOR_BLUE           = 44;

    /**
     * Terminal back color - magenta
     */
    public static final int BGCOLOR_MAGENTA        = 45;

    /**
     * Terminal back color - cyan
     */
    public static final int BGCOLOR_CYAN           = 46;

    /**
     * Terminal back color - gray
     */
    public static final int BGCOLOR_GRAY           = 47;

    /**
     * Terminal back color - reset back color
     */
    public static final int BGCOLOR_RESET          = 49;

    // -----------------------------------------------------------------------
    /**
     * Terminal back color - bright black (???)
     */
    public static final int BGCOLOR_BRIGHT_BLACK   = 100;

    /**
     * Terminal back color - bright red
     */
    public static final int BGCOLOR_BRIGHT_RED     = 101;

    /**
     * Terminal back color - bright green
     */
    public static final int BGCOLOR_BRIGHT_GREEN   = 102;

    /**
     * Terminal back color - bright yellow
     */
    public static final int BGCOLOR_BRIGHT_YELLOW  = 103;

    /**
     * Terminal back color - bright blue
     */
    public static final int BGCOLOR_BRIGHT_BLUE    = 104;

    /**
     * Terminal back color - bright magenta
     */
    public static final int BGCOLOR_BRIGHT_MAGENTA = 105;

    /**
     * Terminal back color - bright cyan
     */
    public static final int BGCOLOR_BRIGHT_CYAN    = 106;

    /**
     * Terminal back color - bright gray
     */
    public static final int BGCOLOR_BRIGHT_GRAY    = 107;
    // ----------------------------------------------------------------------

}
