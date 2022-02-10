/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Feb 6, 2022 4:59:55 AM - .
 */

package JTerminal;

/**
 * @author jsmith
 *         Строки режимов терминала для включения в текст сообщения по месту
 */
public final class TerminalModeStrings
{

    // Если тут будут исправления, внести их и в класс TerminalMode
    // чтобы они уже были одинаковы.

    /**
     * Terminal text style - reset to default
     */
    public static final String STYLE_RESET            = "\033[0m";

    /**
     * Terminal text style - bold (жирный)
     */
    public static final String STYLE_BOLD             = "\033[1m";

    /**
     * Terminal text style - dimmed (тусклый)
     */
    public static final String STYLE_DIM              = "\033[2m";

    /**
     * Terminal text style - italic (курсив)
     */
    public static final String STYLE_ITALIC           = "\033[3m";

    /**
     * Terminal text style - underline (подчеркнутый)
     */
    public static final String STYLE_UNDERLINE        = "\033[4m";

    /**
     * Terminal text style - blinked (мигающий)
     * Sets blinking to less than 150 times per minute
     */
    public static final String STYLE_BLINK            = "\033[5m";

    /**
     * Terminal text style - rapid blinked (быстро мигающий)
     */
    public static final String STYLE_RBLINK           = "\033[6m";

    /**
     * Terminal text style - swap fore and back colors
     */
    public static final String STYLE_REVERSED         = "\033[7m";

    /**
     * Terminal text style - hidden (скрытый, выводится цветом фона)
     */
    public static final String STYLE_CONCEAL          = "\033[8m";

    /**
     * Terminal text style - striked (зачеркнутый)
     */
    public static final String STYLE_CROSSED          = "\033[9m";

    // ------------------------------------------------------------
    /**
     * Terminal text color - black
     */
    public static final String COLOR_BLACK            = "\033[30m";

    /**
     * Terminal text color - red
     */
    public static final String COLOR_RED              = "\033[31m";

    /**
     * Terminal text color - green
     */
    public static final String COLOR_GREEN            = "\033[32m";

    /**
     * Terminal text color - yellow
     */
    public static final String COLOR_YELLOW           = "\033[33m";

    /**
     * Terminal text color - blue
     */
    public static final String COLOR_BLUE             = "\033[34m";

    /**
     * Terminal text color - magenta
     */
    public static final String COLOR_MAGENTA          = "\033[35m";

    /**
     * Terminal text color - cyan
     */
    public static final String COLOR_CYAN             = "\033[36m";

    /**
     * Terminal text color - gray
     */
    public static final String COLOR_GRAY             = "\033[37m";

    /**
     * Terminal text color - reset text color
     */
    public static final String COLOR_RESET            = "\033[39m";

    // --------------------------------------------------------------
    /**
     * Terminal text color - bright black
     */
    public static final String COLOR_BRIGHT_BLACK     = "\033[90m";

    /**
     * Terminal text color - bright red
     */
    public static final String COLOR_BRIGHT_RED       = "\033[91m";

    /**
     * Terminal text color - bright green
     */
    public static final String COLOR_BRIGHT_GREEN     = "\033[92m";

    /**
     * Terminal text color - bright yellow
     */
    public static final String COLOR_BRIGHT_YELLOW    = "\033[93m";

    /**
     * Terminal text color - bright blue
     */
    public static final String COLOR_BRIGHT_BLUE      = "\033[94m";

    /**
     * Terminal text color - bright magenta
     */
    public static final String COLOR_BRIGHT_MAGENTA   = "\033[95m";

    /**
     * Terminal text color - bright cyan
     */
    public static final String COLOR_BRIGHT_CYAN      = "\033[96m";

    /**
     * Terminal text color - bright gray
     */
    public static final String COLOR_BRIGHT_GRAY      = "\033[97m";

    // --------------------------------------------------------------------
    /**
     * Terminal back color - black
     */
    public static final String BGCOLOR_BLACK          = "\033[40m";

    /**
     * Terminal back color - red
     */
    public static final String BGCOLOR_RED            = "\033[41m";

    /**
     * Terminal back color - green
     */
    public static final String BGCOLOR_GREEN          = "\033[42m";

    /**
     * Terminal back color - yellow
     */
    public static final String BGCOLOR_YELLOW         = "\033[43m";

    /**
     * Terminal back color - blue
     */
    public static final String BGCOLOR_BLUE           = "\033[44m";

    /**
     * Terminal back color - magenta
     */
    public static final String BGCOLOR_MAGENTA        = "\033[45m";

    /**
     * Terminal back color - cyan
     */
    public static final String BGCOLOR_CYAN           = "\033[46m";

    /**
     * Terminal back color - gray
     */
    public static final String BGCOLOR_GRAY           = "\033[47m";

    /**
     * Terminal back color - reset back color
     */
    public static final String BGCOLOR_RESET          = "\033[49m";

    // -----------------------------------------------------------------------
    /**
     * Terminal back color - bright black (???)
     */
    public static final String BGCOLOR_BRIGHT_BLACK   = "\033[100m";

    /**
     * Terminal back color - bright red
     */
    public static final String BGCOLOR_BRIGHT_RED     = "\033[101m";

    /**
     * Terminal back color - bright green
     */
    public static final String BGCOLOR_BRIGHT_GREEN   = "\033[102m";

    /**
     * Terminal back color - bright yellow
     */
    public static final String BGCOLOR_BRIGHT_YELLOW  = "\033[103m";

    /**
     * Terminal back color - bright blue
     */
    public static final String BGCOLOR_BRIGHT_BLUE    = "\033[104m";

    /**
     * Terminal back color - bright magenta
     */
    public static final String BGCOLOR_BRIGHT_MAGENTA = "\033[105m";

    /**
     * Terminal back color - bright cyan
     */
    public static final String BGCOLOR_BRIGHT_CYAN    = "\033[106m";

    /**
     * Terminal back color - bright gray
     */
    public static final String BGCOLOR_BRIGHT_GRAY    = "\033[107m";
    // ----------------------------------------------------------------------
}
