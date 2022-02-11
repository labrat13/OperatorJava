/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Feb 6, 2022 4:59:55 AM - Готов к отладке.
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
        if (s == null) return true;
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss", BCSA.RuCulture);
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
            if (!Utility.StringIsNullOrEmpty(s)) count++;
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
     * NT-Split string by regex and optionall remove empty elements from result
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
    public static String[] StringSplit(String text, String regex, boolean RemoveEmptyItems)
    {
        String[] sar = text.split(regex);

        if (RemoveEmptyItems)
            return Utility.RemoveEmptyItems(sar);
        else return sar;
    }

    // /**
    // * NT-Получить версию сборки Оператора
    // * @return
    // */
    // public static Version getOperatorVersion()
    // {
    // return Assembly.GetExecutingAssembly().GetName().Version;
    // }

}
