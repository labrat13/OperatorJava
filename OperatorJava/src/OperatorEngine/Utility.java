/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Mar 21, 2022 12:37:20 AM - Ported, Готов к отладке.
 */
package OperatorEngine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Lexicon.BCSA;

/**
 * Разные статические вспомогательные функции
 * 
 * @author 1
 */
public class Utility
{

    /**
     * NT-Check String.IsNullOrEmpty()
     * 
     * @param s
     *            string object
     * @return Returns true if string is null or empty. Returns false otherwise/
     */
    public static boolean StringIsNullOrEmpty(String s)
    {
        if (s == null)
            return true;
        // else
        return s.isEmpty();
    }

    /**
     * Compare two strings ignore case
     * 
     * @param s1
     *            String
     * @param s2
     *            String
     * @return Returns True if strings are equal, returns False otherwise.
     */
    public static boolean StringEqualsOrdinalIgnoreCase(String s1, String s2)
    {
        return s1.equalsIgnoreCase(s2);
    }

    /**
     * Compare two strings
     * 
     * @param s1
     *            String
     * @param s2
     *            String
     * @return Returns True if strings are equal, returns False otherwise.
     */
    public static boolean StringEquals(String s1, String s2)
    {
        return (s1.compareTo(s2) == 0);
    }

    /**
     * NT-Create copy of specified string
     * 
     * @param s
     * @return
     */
    public static String StringCopy(String s)
    {
        // TODO: проверить что это работает и копируется а не хз что.
        String result = new String(s);
        return result;
    }

    /**
     * NT-Return formatted string for DateTime.Now
     * 
     * @return Return formatted string for DateTime.Now
     */

    public static String DateTimeNowToString()
    {
        LocalDateTime dt = LocalDateTime.now();

        return DateTimeToString(dt);
    }

    /**
     * NT-Return formatted string for specified LocalDateTime object.
     * 
     * @param dt
     *            LocalDateTime object.
     * @return Return formatted string for specified LocalDateTime object.
     */
    public static String DateTimeToString(LocalDateTime dt)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss", BCSA.RuCulture);
        return dtf.format(dt);
    }

    /**
     * NT-Return part of filename string for specified LocalDateTime object.
     * 
     * @param dt
     *            LocalDateTime object.
     * @return Return part of filename for specified LocalDateTime object.
     */
    public static String DateTimeToFileNameString(LocalDateTime dt)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMdd_HHmmss", BCSA.RuCulture);
        return dtf.format(dt);
    }

    /**
     * NT-Получить строку версии сборки Оператора
     * 
     * @return Returns Operator version string
     */
    public static String getOperatorVersionString()
    {
        return Engine.EngineVersionString;
    }

    /**
     * NT-Remove empty string items from source array
     * 
     * @param sar
     *            Source array with empty or null items
     * @return Result array without empty or null items
     */
    public static String[] RemoveEmptyItems(String[] sar)
    {
        int count = 0;
        // find size of result array
        for (String s : sar)
            if (!Utility.StringIsNullOrEmpty(s))
                count++;
        // create result array
        String[] result = new String[count];
        // fill array
        count = 0;
        for (String s : sar)
            if (!Utility.StringIsNullOrEmpty(s))
            {
                result[count] = s;
                count++;
            }

        return result;
    }

    /**
     * NT-Split string by regex and optional remove empty elements from result
     * array
     * 
     * @param text
     *            Source string
     * @param regex
     *            regex string as described in String.split() function
     *            documentation.
     *            " " -> " ";
     *            "k" "m " -> "[km]" and so on...
     * @param RemoveEmptyItems
     *            if True - remove empty items from result array.
     * @return Returns array of string's
     */
    public static String[] StringSplit(
            String text,
            String regex,
            boolean RemoveEmptyItems)
    {
        String[] sar = text.split(regex);

        if (RemoveEmptyItems)
            return Utility.RemoveEmptyItems(sar);
        else return sar;
    }

    /**
     * NT-Faster split string at first match delimiter string
     * 
     * @param text
     *            Source string
     * @param delimiter
     *            Delimiter string as "="
     * @return Returns array of 2 parts: before and after delimiter. Returns null if delimiter not found.
     */
    public static String[] StringSplitFirstMatch(String text, String delimiter)
    {
        String[] result = null;

        int start = text.indexOf(delimiter);
        int delimiterLength = delimiter.length();
        if (start >= 0)
        {
            result = new String[2];
            result[0] = text.substring(0, start);
            result[1] = text.substring(start + delimiterLength);
        }
        // else if(start == 0)
        // {
        // result = new String[2];
        // result[0] = "";
        // result[1] = text.substring(start+delimiterLength);
        // }
        else result = null;

        return result;
    }

    /**
     * NT-Return string value or [Null] if string is null.
     * 
     * @param s
     *            String
     * @return Return string value or [Null] if string is null.
     */
    public static String GetStringTextNull(String s)
    {
        if (s == null)
            return "[Null]";
        else return s;
    }

    /**
     * NT-Get file extension from file title string
     * 
     * @param s
     *            Filename without path
     * @return Function returns file extension without leading dot.
     *         Function returns empty string if filename has not extension.
     */
    public static String getFileExtension(String s)
    {
        int pos = s.lastIndexOf(".");
        if (pos == -1)
            return "";
        // если точка - последняя в строке
        if (s.length() == (pos + 1))
            return "";
        // else
        String result = s.substring(pos + 1);
        return result;
    }

    /**
     * NT- Get filename without last extension par and dot.
     * 
     * @param s
     *            Filename without path
     * @return Function returns filename without last extension and dot.
     */
    public static String getFilenameWithoutExtension(String s)
    {
        int pos = s.lastIndexOf(".");
        if (pos == -1)
            return s;
        // else
        String result = s.substring(0, pos);
        return result;

    }

    // 0123.4

    // /**
    // * NT-Получить версию сборки Оператора
    // * @return
    // */
    // public static Version getOperatorVersion()
    // {
    // return Assembly.GetExecutingAssembly().GetName().Version;
    // }

}
