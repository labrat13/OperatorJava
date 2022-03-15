package ProcedureUtils;
/**
 * @author Селяков Павел
 * Created: Mar 14, 2022 11:14:56 PM
 * State: Mar 14, 2022 11:14:56 PM - initial
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;





/**
 * @author Селяков Павел
 *
 */
public class FileSystemUtils
{
    // Вынесен сюда, поскольку в JDK путаница с этими сепараторами.
    /**
     * File path separator as string
     */
    public final static String FileSeparator  = File.separator;

    /**
     * User home directory
     */
    public final static String UserFolderPath = System.getProperty("user.home");
    /**
     * NT- Get from directory all files with specified extensions.
     * 
     * @param dir
     *            Directory
     * @param exts
     *            Array of filename endings or extensions: .htm
     * @return Returns array of File objects
     */
    public static File[] getDirectoryFiles(File dir, String[] exts)
    {
        FilenameFilter filter = new FilenameFilter()
        {

            public boolean accept(File file, String name)
            {
                // check filename ends
                for (String ext : exts)
                    if (name.endsWith(ext)) return true;
                return false;
            }
        };
        File[] files = dir.listFiles(filter);

        return files;
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
     * NT-Read entire file and return lines array.
     * 
     * @param filepath
     *            File pathname.
     * @param encoding
     *            File text encoding. For example: UTF-8 UTF-16.
     * @return Returns text lines array.
     * @throws IOException
     *             File reading error.
     */
    public static String[] getFileLines(String filepath, String encoding) throws IOException
    {
        BufferedReader br = openBufferedReader(filepath, encoding);
        LinkedList<String> ll = new LinkedList<String>();
        String line;
        while ((line = br.readLine()) != null)
            ll.add(line);
        br.close();
        // return as array
        String[] sar = new String[ll.size()];

        return ll.toArray(sar);
    }

    /**
     * NT- Read entire file and return text as StringBuilder.
     * 
     * @param filepath
     *            File pathname.
     * @param encoding
     *            File text encoding. For example: UTF-8 UTF-16.
     * @return Return text as StringBuilder.
     * @throws IOException
     *             File reading error.
     */
    public static StringBuilder getReadAll(String filepath, String encoding) throws IOException
    {
        BufferedReader br = openBufferedReader(filepath, encoding);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null)
        {
            sb.append(line);
            sb.append(System.lineSeparator());
        }
        br.close();

        return sb;

    }

    /**
     * NT-Получить список дисковых томов данного компьютера
     * 
     * @return Возвращает массив объектов дисковых томов компьютера или null при
     *         неудаче.
     */
    public static File[] GetDrives()
    {
        File[] drives = File.listRoots();

        return drives;
    }

    /**
     * NT-Получить общий объем дискового тома.
     * 
     * @param volume
     *            Дисковый том.
     * @return Возвращает объем дискового тома.
     */
    public static long GetVolumeSpace(File volume)
    {
        return volume.getTotalSpace();
    }

    /**
     * NT-Получить объем свободного места на дисковом томе.
     * 
     * @param volume
     *            Дисковый том.
     * @return Возвращает объем свободного места на дисковом томе.
     */
    public static long GetVolumeFreeSpace(File volume)
    {
        return volume.getFreeSpace();
    }

    /**
     * NT- Создать новые каталоги в указанном пути - сразу всю цепочку
     * каталогов.
     * 
     * @param path
     *            Path to new directory
     * @return Returns True if success, false if errors
     */
    public static boolean CreateDirectory(String path)
    {
        File newDir = new File(path);

        return newDir.mkdirs();
    }

    /**
     * RT-Remove directory with subdirectories recursively
     * 
     * @param dir
     *            Directory to remove
     * @return Returns True if success, false if errors
     */
    public static boolean RemoveDirectory(File dir)
    {
        if (dir.isDirectory())
        {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0)
            {
                for (File aFile : files)
                {
                    RemoveDirectory(aFile);
                }
            }
        }

        return dir.delete();
    }

    /**
     * NT-Clean specified directory
     * 
     * @param dir
     *            Directory to clean
     */
    public static void CleanDirectory(File dir)
    {
        if (dir.isDirectory())
        {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0)
            {
                for (File aFile : files)
                {
                    RemoveDirectory(aFile);
                }
            }
        }

        return;
    }
}
