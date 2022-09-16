import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

/**
 * @author Селяков Павел
 *         Created: 1 мая 2022 г. 20:00:11
 *         State: 1 мая 2022 г. 20:00:11 - initial
 */

/**
 * @author Селяков Павел
 *
 */
public class SingleRun
{


    /**
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            // int pid = simpleGetCurrentPid();
            //
            // System.out.println("PID = " + Integer.toString(pid));
            
            String filename = "/home/jsmith/Документы/" + SingleAppInstance.LockingFileName;
            //try lock application before start work
            SingleAppInstance.lockInstance(filename);
            //work here
            System.out.println("hasDuplicate = " + Boolean.toString(SingleAppInstance.hasDuplicate()));
            System.out.println("needRestoreData = " + Boolean.toString(SingleAppInstance.needRestoreData()));
            
            System.out.println("Press enter to exit");
            System.in.read();
            //unlock application before exit
            SingleAppInstance.unlockInstance();

        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
        }
        return;
    }

    

    /** NT-Get application ProcessID (Linux PID)
     * @return Function returns PID of current Process.
     * @throws IOException File access error.
     * @throws NumberFormatException Formatting error.
     */
    private static Integer simpleGetCurrentPid()
            throws NumberFormatException, IOException
    {
        int pid = Integer.parseInt(new File("/proc/self").getCanonicalFile().getName());

        return pid;
    }

    // *** End of file ***
}
