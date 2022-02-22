/**
 * @author Селяков Павел
 *         Created: Feb 22, 2022 1:14:49 PM
 *         State: Feb 22, 2022 1:14:49 PM - initial
 */
package OperatorEngine;

import java.io.File;

/**
 * Всевозможные операции с файлами и каталогами
 * 
 * @author Селяков Павел
 *
 */
public class FileSystemManager
{

    // Вынесен сюда, поскольку в JDK путаница с этими сепараторами.
    /**
     * File path separator as string
     */
    public final static String FileSeparator       = File.separator;

    /**
     * User home directory
     */
    public final static String UserFolderPath      = SystemInfoManager.GetUserHomeFolderPath();

    /**
     * Application main folder
     */
    public final static String AppFolderPath       = SystemInfoManager.GetUserHomeFolderPath() + File.separator + Engine.ApplicationTitle;

    /**
     * Application settings file name
     */
    public final static String AppSettingsFileName = "settings.xml";

    /**
     * Application database file name
     */
    public final static String AppDbFileName       = "db.sqlite";

    /**
     * Application log folder path
     */
    public final static String AppLogFolderPath    = AppFolderPath + File.separator + "logs";

    /**
     * NR-Создать каталог Оператора с правильной структурой файлов и папок
     */
    public static void CreateOperatorFolder()
    {
        //TODO: Добавить код сосздания папок и файлов и копировать сюда все нужные файлы.
        //Этот код для общего понимания устройства структуры каталогов Оператора.
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
