/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Feb 6, 2022 4:59:55 AM - TODO: указать состояние файла здесь.
 */
package OperatorEngine;

/**
 * Менеджер информации о операционной системе
 * 
 * @author 1
 */
public static class SystemInfoManager
{

    /// <summary>
    /// NT-Определить версию ОС.
    /// </summary>
    /// <returns></returns>
    /// <remarks>
    /// Функция приблизительно определяет версию ОС, не различает десктопную и
    /// серверную версии.
    /// Но для Оператора этого должно быть достаточно.
    /// </remarks>
    public static OsType detectOsType()
    {
        int major = Environment.OSVersion.Version.Major;
        int minor = Environment.OSVersion.Version.Minor;

        if (major < 5)
            return OsType.Unknown;
        else if (major == 5)
        {
            if (minor == 0)
                return OsType.Windows2000;
            else if (minor == 1)
                return OsType.WindowsXP;
            else if (minor == 2)
                return OsType.Windows2003;
            else return OsType.Unknown;
        }
        else if (major == 6)
        {
            if (minor == 0)
                return OsType.WindowsVista;
            else if (minor == 1)
                return OsType.Windows7;
            else if (minor == 2)
                return OsType.Windows8;
            else if (minor == 3)
                return OsType.Windows81;
            else return OsType.Unknown;
        }
        else if (major == 10)
            return OsType.Windows10;

        else return OsType.Unknown;
    }

    /// <summary>
    /// Функция возвращает True если операционная система - Windows XP.
    /// </summary>
    /// <returns></returns>
    public static bool isWindowsXP()
    {
        if ((Environment.OSVersion.Version.Major == 5) && (Environment.OSVersion.Version.Minor == 1))
            return true;
        else return false;
    }

    /// <summary>
    /// Функция возвращает True если операционная система 64-битная.
    /// </summary>
    /// <returns></returns>
    public static bool is64bitProcess()
    {
        return (IntPtr.Size == 8);
    }

}



