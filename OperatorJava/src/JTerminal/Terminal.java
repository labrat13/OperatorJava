/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Mar 21, 2022 12:37:20 AM - Не написаны несколько функций класса.
 */
package JTerminal;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;

/**
 * @author jsmith
 *         Terminal control class
 */
public class Terminal
{

    /**
     * Подать короткий звуковой сигнал
     */
    public static void Beep()
    {
        Write("\007");
    }

    /**
     * Show cursor on screen
     */
    public static void CursorShow()
    {
        Write("\033[?25l");
    }

    /**
     * Hide cursor on screen
     */
    public static void CursorHide()
    {
        Write("\033[?25h");
    }

    /**
     * Set terminal width in columns
     * 
     * @param cols
     *            - number of columns as chars in line
     */
    public static void SetTerminalWidth(int cols)
    {
        String s = "\033[" + String.valueOf(cols) + "u";
        Write(s);
    }

    /**
     * Set terminal height in rows
     * 
     * @param rows
     *            - number of lines in window
     */
    public static void SetTerminalHeight(int rows)
    {
        String s = "\033[" + String.valueOf(rows) + "t";
        Write(s);
    }

    // *************** Cursor positions ****************
    /**
     * Set cursor position
     * If an attempt is made to move the cursor out of the window, the result is
     * undefined.
     * 
     * @param row
     *            - row position of cursor
     * @param col
     *            - column position of cursor
     */
    public static void SetCursorPosition(int row, int col)
    {
        String s = "\033[" + String.valueOf(row) + ";" + String.valueOf(col) + "H";
        Write(s);
    }

    /**
     * Set cursor position as force (???)
     * If an attempt is made to move the cursor out of the window, the result is
     * undefined.
     * 
     * @param row
     *            row position of cursor = 1 or more
     * @param col
     *            column position of cursor = 1 or more
     */
    public static void ForceCursorPosition(int row, int col)
    {
        // TODO: выяснить что это и как работает
        String s = "\033[" + String.valueOf(row) + ";" + String.valueOf(col) + "f";
        Write(s);
    }

    /**
     * Move cursor position to the home position (1;1) at the upper left of the
     * screen.
     */
    public static void ResetCursorPosition()
    {
        Write("\033[H");
    }

    /**
     * move cursor right from current position
     * 
     * @param cols
     *            Number of columns to move
     */
    public static void MoveCursorRight(int cols)
    {
        String s = "\033[" + String.valueOf(cols) + "C";
        Write(s);
    }

    /**
     * move cursor left from current position
     * 
     * @param cols
     *            Number of columns to move
     */
    public static void MoveCursorLeft(int cols)
    {
        String s = "\033[" + String.valueOf(cols) + "D";
        Write(s);
    }

    /**
     * move cursor down from current position
     * 
     * @param rows
     *            Number of rows to move down
     */
    public static void MoveCursorDown(int rows)
    {
        String s = "\033[" + String.valueOf(rows) + "B";
        Write(s);
    }

    /**
     * move cursor up from current position
     * 
     * @param rows
     *            number of rows to move up
     */
    public static void MoveCursorUp(int rows)
    {
        String s = "\033[" + String.valueOf(rows) + "A";
        Write(s);
    }

    /**
     * Save current cursor position
     */
    public static void StoreCursorPosition()
    {
        Write("\033[s");
    }

    /**
     * Restores cursor position after a Save Cursor
     */
    public static void RestoreCursorPosition()
    {
        Write("\033[u");
    }

    /**
     * Saves the cursor position, encoding shift state and formatting attributes
     */
    public static void StoreCursorPositionAttributes()
    {
        Write("\033" + "7");
    }

    /**
     * Restores the cursor position, encoding shift state and formatting
     * attributes from the previous DECSC if any, otherwise resets these all to
     * their defaults
     */
    public static void RestoreCursorPositionAttributes()
    {
        Write("\033" + "8");
    }

    // *** Scrolling ***

    /**
     * Enable scrolling for entire display.
     */
    public static void Scroll()
    {
        Write("\033[r");
    }

    /**
     * Enable scrolling from row start to row end
     * 
     * @param start
     *            Start row for scrolling
     * @param end
     *            End row for scrolling
     */
    public static void Scroll(int start, int end)
    {
        String s = "\033[" + String.valueOf(start) + ";" + String.valueOf(end) + "r";
        Write(s);
    }

    /**
     * Scroll display down one line.
     */
    public static void ScrollDown()
    {
        Write("\033" + "D");
    }

    /**
     * Scroll display up one line.
     */
    public static void ScrollUp()
    {
        Write("\033" + "M");// TODO: убедиться что строки не слипаются и Escape
                            // + M получается из них
    }

    // *** Tab control ***

    /**
     * Sets a tab at the current position.
     */
    public static void SetTab()
    {
        Write("\033" + "M");
    }

    /**
     * Clears tab at the current position.
     */
    public static void ClearTab()
    {
        Write("\033[g");
    }

    /**
     * Clears all tabs.
     */
    public static void ClearAllTabs()
    {
        Write("\033[3g");
    }

    // *** Erasing text ***

    /**
     * Erases from the current cursor position to the end of the current line.
     * Cursor position does not change.
     */
    public static void EraseToLineEnd()
    {
        Write("\033[K");// or "\033[0K"
    }

    /**
     * Erases from the current cursor position to the start of the current line.
     * Cursor position does not change.
     */
    public static void EraseToLineStart()
    {
        Write("\033[1K");
    }

    /**
     * Erases the entire current line.
     * Cursor position does not change.
     */
    public static void EraseLine()
    {
        Write("\033[2K");
    }

    /**
     * Erases the screen from the current line down to the bottom of the screen.
     * clear from cursor to end of screen.
     */
    public static void EraseDown()
    {
        Write("\033[J");// or "\033[0J"
    }

    /**
     * Erases the screen from the current line up to the top of the screen.
     * clear from cursor to beginning of the screen
     */
    public static void EraseUp()
    {
        Write("\033[1J");
    }

    /**
     * Erases the screen with the background colour and moves the cursor to
     * home.
     */
    public static void EraseScreen()
    {
        Write("\033[2J");
    }

    /**
     * Erases the screen with the background colour and moves the cursor to
     * home.
     * clear entire screen and delete all lines saved in the scrollback buffer
     */
    public static void EraseScreenAndBuffer()
    {
        Write("\033[3J");
    }

    // *** Printing ***
    // Some terminals support local printing:

    /**
     * Print the current screen on printer
     */
    public static void PrintScreen()
    {
        Write("\033[i");
    }

    /**
     * Print the current line on printer
     */
    public static void PrintLine()
    {
        Write("\033[1i");
    }

    /**
     * Start log; all received text is echoed to a printer.
     */
    public static void StartPrintLog()
    {
        Write("\033[5i");
    }

    /**
     * Stop printing all received text.
     * see startPrintLog() function.
     */
    public static void StopPrintLog()
    {
        Write("\033[4i");
    }

    // *** Define key macro ***

    /**
     * Associates a string of text to a keyboard key.
     * 
     * @param key
     *            Indicates the key by its ASCII value in decimal.
     * @param text
     *            String for specified key
     */
    public static void SetKeyDefinition(int key, String text)
    {
        // TODO: выяснить что эта штука делает
        String s = "\033[" + String.valueOf(key) + ";\"" + text + "\"p";
        Write(s);
    }

    // *** set attributes ***
    /**
     * Sets 3 display attribute settings: fonttype, foreground color, background
     * color
     * 
     * @param attr1
     *            - font type attribute value from TerminalMode class.
     * @param attr2
     *            - text color attribute value from TerminalMode class.
     * @param attr3
     *            - back color attribute value from TerminalMode class.
     */
    public static void SetAttributeMode(int attr1, int attr2, int attr3)
    {
        String s = "\033[" + String.valueOf(attr1) + ";" + String.valueOf(attr2) + ";" + String.valueOf(attr3) + "m";
        Write(s);
    }

    /**
     * Sets 2 display attribute settings: fonttype, foreground color, background
     * color
     * 
     * @param attr1
     *            - one of display attribute settings: fonttype, foreground
     *            color, background color value from TerminalMode class.
     * @param attr2
     *            - other of display attribute settings: fonttype, foreground
     *            color, background color value from TerminalMode class.
     */
    public static void SetAttributeMode(int attr1, int attr2)
    {
        String s = "\033[" + String.valueOf(attr1) + ";" + String.valueOf(attr2) + "m";
        Write(s);
    }

    /**
     * Sets 1 display attribute settings: fonttype, foreground color, background
     * color
     * 
     * @param attr1
     *            - one of display attribute settings: fonttype, foreground
     *            color, background color value from TerminalMode class.
     */
    public static void SetAttributeMode(int attr1)
    {
        String s = "\033[" + String.valueOf(attr1) + "m";
        Write(s);
    }

    /**
     * Reset all attributes for new terminal text
     */
    public static void ClearAttributeMode()
    {
        Write("\033[0m");// TODO: проверить что это сбрасывает все атрибуты
                         // точно.
    }

    // *** functions ***
    /**
     * Write text to console
     * 
     * @param s
     *            - text
     */
    public static void Write(String s)
    {
        System.out.print(s);
        System.out.flush();
        return;
    }

    /**
     * Write line of text to console
     * 
     * @param s
     *            - text
     */
    public static void WriteLine(String s)
    {
        System.out.println(s);
        System.out.flush();
        return;
    }

    /**
     * Write end of line to console
     */
    public static void WriteLine()
    {
        System.out.println("");
        System.out.flush();
        return;
    }

    /*
     * Функции вывода в std::error Терминала
     */
    /**
     * Write text to std::error console stream
     * 
     * @param s
     *            - text
     */
    public static void ErrorWrite(String s)
    {
        System.err.print(s);
        System.err.flush();
        return;
    }

    /**
     * Write line of text to std::error console stream
     * 
     * @param s
     *            - text
     */
    public static void ErrorWriteLine(String s)
    {
        System.err.println(s);
        System.err.flush();
        return;
    }

    /**
     * Write end of line to std::error console stream
     */
    public static void ErrorWriteLine()
    {
        System.err.println("");
        System.err.flush();
        return;
    }

    // TODO: Get terminal size нужен для работы с терминалом!

    // /**
    // * Get cursor position
    // * @param row Out reference to row position of cursor
    // * @param col Out reference to column position of cursor
    // * @throws Exception
    // */
    // public static void getCursorPosition(IntegerProxy row, IntegerProxy col)
    // throws Exception
    // {
    // StringBuilder sb = new StringBuilder();
    // int t = 0;
    // char c = ' ';
    // //get all other chars from terminal
    // while(System.in.available() > 0)
    // t = System.in.read();
    // //mark start label for debug
    // System.out.print("1");
    //
    // Write("\033[6n");//get cursor position report
    //
    // //тут собираем ответ от консоли - он может быть хз каким длинным
    //
    // //wait and read chars to EOF
    // //- wait
    // while(System.in.available() == 0);
    // //mark start label for debug
    // System.out.print("2");
    // //- read
    // do
    // {
    // if(System.in.available() > 0)
    // t = System.in.read();
    // if(t != -1)
    // {
    // c = (char) t;
    // sb.append(c);
    // }
    // } while(t != -1);
    //
    // //тут очевидно мы проходимся по буферу и ищем вхождение Esc и [
    // //а за ним должны идти 99;99 как row и col
    //
    // int start = sb.indexOf("\033[");
    // if(start == -1) throw new Exception("get_cursor_position(): result not
    // found in the response");
    // int comm = sb.indexOf(";", start);
    // if(comm == -1) throw new Exception("get_cursor_position(): result could
    // not be parsed");
    // int end = sb.indexOf("R", comm);
    // if(end == -1) throw new Exception("get_cursor_position(): R symbol not
    // founded");
    // String part1 = sb.substring(start + 2, comm);
    // String part2 = sb.substring(comm + 1, end);
    // System.out.println(part1);
    // System.out.println(part2);
    //
    // row.setValue(part1);
    // row.setValue(part2);
    //
    // return;
    // }

    // TODO: Нужны функции чтения с консоли
    /**
     * NT- замена для чтения с консоли под Эклипсой.
     * Но ридер не закрывается нигде здесь до конца работы приложения.
     * Вроде бы это нормально должно быть.
     */
    private static BufferedReader s_reader = null;

    /**
     * NT-Read text line from terminal
     * 
     * @return Returns text line from terminal without \r\n; returns null if no
     *         input text.
     */
    public static String ReadLine()
    {
        String result = null;
        try
        {
            Console c = System.console();
            if (c != null)
                return c.readLine();
            else
            {
                if (s_reader == null)
                    s_reader = new BufferedReader(new InputStreamReader(System.in));
                // read
                result = s_reader.readLine();
            }
        }
        catch (Exception e)
        {
            ;
        }

        return result;
    }

}
