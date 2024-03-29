/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Mar 21, 2022 12:37:20 AM - Ported, Готов к отладке.
 */
package OperatorEngine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

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
     *            Образец для копирования.
     * @return Функция возвращает копию образца.
     */
    public static String StringCopy(String s)
    {
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
     *            " " result " ";
     *            "k" "m " result "[km]" and so on...
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
     * NT-Разделить строку ключевых слов на отдельные слова по , и ;
     * 
     * @param text
     *            Входная строка
     * @return Возвращает массив ключевых слов, очищенных от разделителей и пробельных символов по краям.
     */
    public static String[] SplitCommaDelimitedString(String text)
    {
        // 1. split text to array
        String[] sar = text.split("[,;]");
        // 2. trim each string in array
        LinkedList<String> li = new LinkedList<String>();
        String t;
        for (String s : sar)
        {
            // 3. put each string in array to output list
            if (s == null)
                continue;
            t = s.trim();
            if (t.isEmpty())
                continue;
            li.add(t);
        }
        // 4. return list as array
        return li.toArray(new String[li.size()]);
    }

    /**
     * NT-Разделить строку ключевых слов на отдельные слова по , и ;
     * 
     * @param text
     *            Входная строка
     * @return Возвращает список ключевых слов, очищенных от разделителей и пробельных символов по краям.
     */
    public static LinkedList<String> SplitCommaDelimitedString2(String text)
    {
        // 1. split text to array
        String[] sar = text.split("[,;]");
        // 2. trim each string in array
        LinkedList<String> li = new LinkedList<String>();
        String t;
        for (String s : sar)
        {
            // 3. put each string in array to output list
            if (s == null)
                continue;
            t = s.trim();
            if (t.isEmpty())
                continue;
            li.add(t);
        }
        // 4. return list as array
        return li;
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

    /**
     * NT-Open or Create text file.
     * 
     * @param fpath
     *            file pathname string
     * @param encoding
     *            Text file encoding, "UTF-8" as sample.
     * @return Function returns BufferedWriter object. Close it on exit!
     * @throws IOException
     *             General IO error.
     * @throws FileNotFoundException
     *             File not found.
     * @throws UnsupportedEncodingException
     *             Unsupported encoding.
     */
    public static BufferedWriter FileWriterOpenOrCreate(
            String fpath,
            String encoding)
            throws IOException, FileNotFoundException,
            UnsupportedEncodingException
    {
        File f = new File(fpath);
        if (f.exists() == false)
        {
            f.createNewFile();
        }
        FileOutputStream os = new FileOutputStream(f, true);
        OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
        BufferedWriter result = new BufferedWriter(osw);
        
        return result;
    }

    /**
     * NT-Open Buffered Reader for read file with specified encoding.
     * 
     * @param filepath
     *            File pathname.
     * @param encoding
     *            File text encoding. For example: UTF-8 UTF-16.
     * @return Returns BufferedReader object ready for use.
     * @throws FileNotFoundException
     *             File not founded.
     * @throws UnsupportedEncodingException
     *             Wrong encoding title.
     */
    public static BufferedReader openBufferedReader(String filepath, String encoding)
            throws FileNotFoundException, UnsupportedEncodingException
    {
        FileInputStream fis = new FileInputStream(filepath);
        InputStreamReader isr = new InputStreamReader(fis, encoding);
        BufferedReader result = new BufferedReader(isr);
        
        return result;
    }
    
    /**
     * NT- Open Buffered Writer for write to file with specified encoding.
     * 
     * @param filepath
     *            File pathname.
     * @param encoding
     *            File text encoding. For example: UTF-8 UTF-16.
     * @return Returns BufferedWriter object ready for use.
     * @throws FileNotFoundException
     *             File cannot be created.
     * @throws UnsupportedEncodingException
     *             Wrong encoding title.
     */
    public static BufferedWriter openBufferedWriter(String filepath, String encoding)
            throws FileNotFoundException, UnsupportedEncodingException
    {
        FileOutputStream fos = new FileOutputStream(filepath);
        OutputStreamWriter osw = new OutputStreamWriter(fos, encoding);
        BufferedWriter result = new BufferedWriter(osw);
        
        return result;
    }
    
    /**
     * NT-Проверить что указанный массив содержит указанную строку.
     * 
     * @param array
     *            Массив строк.
     * @param sample
     *            Строка-образец для поиска.
     * @param ignoreCase
     *            Игнорировать регистр символов строки.
     * @return Возвращает True, если массив содержит указанную строку; False в
     *         противном случае.
     */
    public static boolean arrayContainsStringOrdinal(
            String[] array,
            String sample,
            boolean ignoreCase)
    {
        for (String s : array)// as foreach
            if (ignoreCase == true)
            {
                if (sample.equalsIgnoreCase(s))
                    return true;
            }
            else
            {
                if (sample.compareTo(s) == 0)
                    return true;
            }

        return false;
    }


    /**
     * NT-Заменить недопустимые символы в названии файла на указанный символ
     * @param title Название файла без расширения
     * @param p Символ-замена.
     * @return Возвращает безопасное название файла
     */
    public static String ReplaceInvalidPathChars(String title, String p)
    {
        //TODO: перенести эту функцию в более правильное место по семантике.
        String result = title.replaceAll("[\\\\/:*?\"<>|]", p);
        
        return result;        
    }
    
    /** 
     * NT-Превратить путь к файлу в URI для вызова ShellExecute
     * 
     * @param path Путь к файлу.
     * @return Функция возвращает текстовый URI файла для использования в ShellExecute. 
     */
    public static String MakeUriFromFilePath(String path)
    {
        File f = new File(path);
        URI uri = f.toURI();
        //URL url = uri.toURL();
        String result = uri.toString();
        
        return result;
    }

    /** 
     * NT-Превратить логическое значение в русскоязычное Да или Нет.
     * @param flag Логическое значение.
     * @return Функция возвращает русскоязычное значение логического значения.
     */
    public static String BoolToДаНет(boolean flag)
    {
        if(flag == true) return "Да";
        else return "Нет";
    }

    /** NT-Распарсить строку в целое число.
     * @param str входная строка
     * @return Функция возвращает объект числа, если удалось его распарсить.
     * Функция возвращает null при любой ошибке парсинга.
     */
    public static Integer tryParseInteger(String str)
    {
        Integer result = null;
        try 
        {
            result = new Integer(str);
        }
        catch(Exception ex)
        {
         result = null;   
        }
        
        return result;
    }

    /** NT-Remove ending dot's from file title string
     * @param title file title string
     * @return Fumction returns title without ending dot's
     */
    public static String RemoveEndingDots(String title)
    {
        String s = title;
        while(s.endsWith("."))
            s = s.substring(0, s.length() - 1);
            
        return s;
    }

    /** 
     * NT-проверить, что строка это веб-ссылка
     * @param addr Проверяемая строка
     * @return Возвращает True, если строка это веб-ссылка, False в противном случае.
     */
    public static boolean isWebUri(String addr)
    {
        return (addr.startsWith("http:") 
                || addr.startsWith("https:") 
                || addr.startsWith("www.")
                || addr.startsWith("ftp:")
                || addr.startsWith("sftp:"));
    }

    /** 
     * NT-проверить, что строка это сетевой путь файла
     * @param addr Проверяемая строка
     * @return Возвращает True, если строка это веб-ссылка, False в противном случае.
     */
    public static boolean isFileUri(String addr)
    {
        return (addr.startsWith("file:"));
    }

    /** 
     * NT-проверить, что строка это локальный путь файла
     * @param addr Проверяемая строка
     * @return Возвращает True, если строка это веб-ссылка, False в противном случае.
     */
    public static boolean isLocalFile(String addr)
    {
        boolean result = true;
        if(addr.isEmpty()) return false;
        //если это локальный путь ФС, то он должен начинаться с / или ~
        if(addr.startsWith("/") || addr.startsWith("~"))
            return true;
        //тут конструктор File() подставит текущий (или рабочий?) каталог приложения (.Operator), если addr не содержит путь ФС.
        //так что оно сможет запускать все файлы, что находятся внутри каталога Оператор, по относительным путям.
        try
        {
            File f = new File(addr);   
            result = f.exists();
        }
        catch(Exception ex)
        {
         result = false;   
        }
        
        return result;
    }
    


}
