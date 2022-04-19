/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Mar 21, 2022 12:37:20 AM - TODO: найти код и написать новый менеджер информации системы.
 */
package OperatorEngine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Менеджер информации о операционной системе
 * 
 * @author 1
 */
public class SystemInfoManager
{

    /**
     * Get Operating system name
     * 
     * @return Operating system name
     */
    public static String GetOsTitle()
    {
        return System.getProperty("os.name");
    }

    /**
     * Get Operating system architecture
     * 
     * @return Operating system architecture
     */
    public static String GetOsArchTitle()
    {
        return System.getProperty("os.arch");
    }

    /**
     * Get Operating system version
     * 
     * @return Operating system version
     */
    public static String GetOsVersionString()
    {
        return System.getProperty("os.version");
    }

    /**
     * Get Line separator ("\n" on UNIX)
     * 
     * @return Line separator ("\n" on UNIX)
     */
    public static String GetLineSeparator()
    {
        return System.getProperty("line.separator");
    }

    /**
     * Get File separator ("/" on UNIX)
     * 
     * @return File separator ("/" on UNIX)
     */
    public static String GetFileSeparator()
    {
        return System.getProperty("file.separator");
    }

    /**
     * Get Path separator (":" on UNIX)
     * 
     * @return Path separator (":" on UNIX)
     */
    public static String GetPathSeparator()
    {
        return System.getProperty("path.separator");
    }

    /**
     * Get User's account name
     * 
     * @return User's account name
     */
    public static String GetUserTitle()
    {
        return System.getProperty("user.name");
    }

    /**
     * Get User's home directory
     * 
     * @return User's home directory
     */
    public static String GetUserHomeFolderPath()
    {
        return System.getProperty("user.home");
    }

    /**
     * Get User's current working directory
     * 
     * @return User's current working directory
     */
    public static String GetUserCurrentDirectory()
    {
        return System.getProperty("user.dir");
    }

    /**
     * Get Java Runtime Environment version
     * 
     * @return Java Runtime Environment version
     */
    public static String GetJREVersion()
    {
        return System.getProperty("user.dir");
    }

    //
    //
    // /// <summary>
    // /// Функция возвращает True если операционная система 64-битная.
    // /// </summary>
    // /// <returns></returns>
    // public static bool is64bitProcess()
    // {
    // return (IntPtr.Size == 8);
    // }

    /**
     * NR-Запустить приложение и немедленно выйти из функции.
     * 
     * @param app
     *            Application path
     * @param args
     *            Argument string
     * @param workDirectory
     *            Application working directory
     * @return Возвращает значение 0.
     * @throws Exception
     *             Исключение при запуске процесса.
     */
    public static int ExecuteApplication(
            String app,
            String args,
            String workDirectory) throws Exception
    {
        List<String> command = new ArrayList<String>();
        command.add(app);
        command.add(args);

        ProcessBuilder builder = new ProcessBuilder(command);
        // get environment variables
        Map<String, String> environ = builder.environment();
        // set directory
        builder.directory(new File(workDirectory));
        // startup
        final Process process = builder.start();

        // InputStream is = process.getInputStream();
        // InputStreamReader isr = new InputStreamReader(is);
        // BufferedReader br = new BufferedReader(isr);
        // String line;
        // while ((line = br.readLine()) != null)
        // {
        // System.out.println(line);
        // }
        // System.out.println("Program terminated!");

        return 0;
    }

}
