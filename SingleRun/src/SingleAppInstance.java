import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.Random;

/**
 * @author Селяков Павел
 *         Created: 1 мая 2022 г. 23:13:47
 *         State: 1 мая 2022 г. 23:13:47 - initial
 */

/**
 * NT-Определить, работает ли уже другой экзмпляр приложения.
 * И если нет, была ли его работа завершена некорректно.
 * @author Селяков Павел
 *
 * Механизм основан на создании пустого файла блокировки, обычно в рабочем каталоге приложения/проекта.
 * - Если файл блокировки отсутствует, то нет ранее запущенного экземпляра приложения, и его работа была завершена корректно.
 * - Если файл блокировки присутствует, то:
 *   - Если файл блокирован от чтения-записи, то существует ранее запущенный экземпляр приложения. 
 *      И он должен был восстановить данные, если они были повреждены.
 *   - Если файл не блокирован от чтения-записи, то нет ранее запущенного экземпляра приложения. 
 *      Работа предыдущего экземпляра приложения была завершена неожиданно. 
 *      Верояно, потребуется восстановление данных приложения.
 */
public class SingleAppInstance
{

    // *** Constants and Fields ***
    /**
     * Имя файла блокировки.
     */
    public static final String      LockingFileName    = "lockfile.lock";

    /**
     * Флаг, что предыдущая копия приложения уже запущена.
     */
    private static boolean          m_hasDuplicate     = false;

    /**
     * Флаг, что предыдущая копия не была завершена корректно, и требуется восстановить данные приложения.
     */
    private static boolean          m_needRestoreData  = false;

    /**
     * Путь к файлу блокировки.
     */
    private static String           m_lockFilePathName = null;

    /**
     * Объект файла
     */
    private static RandomAccessFile m_raf              = null;

    /**
     * Объект блокировки файла.
     */
    private static FileLock         m_fl               = null;
    // *** Constructors ***

    // *** Properties ***
    /**
     * NT-Флаг, что предыдущая копия приложения уже запущена.
     * 
     * @return Возвращает значение флага.
     */
    public static boolean hasDuplicate()
    {
        return m_hasDuplicate;
    }

    /**
     * NT-Флаг, что нет запущенных копий, но предыдущая копия не была завершена корректно, и требуется восстановить данные приложения.
     * 
     * @return Возвращает значение флага.
     */
    public static boolean needRestoreData()
    {
        return m_needRestoreData;
    }

    // *** Service functions ***
    /**
     * NT-Try lock application before start application routine.
     * 
     * @param lockfilepath Locking file pathname.
     * @throws IOException Error on locking application.
     * @throws InterruptedException Unknown error.
     */
    public static void lockInstance(String lockfilepath) throws IOException, InterruptedException
    {
        
        boolean isExists = false;
        boolean isLocked = false;
        //wait random time
        waitRandom(7000.0d);
        
        File fi = new File(lockfilepath);
        //store filepath to variable
        m_lockFilePathName = new String(lockfilepath);
        // set flags
        isExists = true;
        if (fi.exists() == false)
        {
            isExists = false;
            fi.createNewFile();
        }
        // set lock
        m_raf = new RandomAccessFile(fi, "rw");
        m_fl = m_raf.getChannel().tryLock();
        if (m_fl == null)
        {
            isLocked = true;
        }
        else
        {
            isLocked = false;
        }
        // calculate
        if (isExists == false)
        {
            m_hasDuplicate = false;
            m_needRestoreData = false;
        }
        else
        {
            // flag isExists = true
            if (isLocked == false)
            {
                m_hasDuplicate = false;
                m_needRestoreData = true;
            }
            else
            {
                m_hasDuplicate = true;
                m_needRestoreData = false;
            }

        }

        return;
    }

    /**
     * NT - Current thread sleep for 50..maxMs milliseconds.
     * @param maxMs Maximum amount of milliseconds for sleep.
     * @throws InterruptedException Unknown error.
     */
    private static void waitRandom(double maxMs) throws InterruptedException
    {
        Random r = new Random(System.nanoTime());
        
        Double d = r.nextDouble() * maxMs;
        d = d + 50.0;//min pause is 50 milliseconds
        long ms = d.longValue();
        Thread.sleep(ms);
        
        return;
    }

    /**
     * NT-Unlock application at exit application routine.
     * 
     * @throws IOException
     *             Error on unlocking
     */
    public static void unlockInstance() throws IOException
    {
        if (m_fl != null)
        {
            m_fl.release();
            m_fl = null;
        }
        
        if (m_raf != null)
        {
            m_raf.close();
            m_raf = null;
        }
        //delete lock file at exit application
        File f = new File(m_lockFilePathName);
        if (f.exists())
            f.delete();

        m_lockFilePathName = null;

        return;
    }

    // *** End of file ***
}
