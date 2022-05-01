/**
 * @author Селяков Павел
 * Created: 2 мая 2022 г. 0:37:42
 * State: 2 мая 2022 г. 0:37:42 - initial
 */
package ProcedureUtils;

import java.io.File;
import java.io.IOException;

/** NT - Разные функции для Linux Process.
 * @author Селяков Павел
 *
 */
public class ProcessUtils
{
    //*** Constants and  Fields ***

    //*** Constructors ***

    //*** Properties ***

    //*** Service  functions ***
    
    /** NT-Get application ProcessID (Linux PID)
     * @return Function returns PID of current Process.
     * @throws IOException File access error.
     * @throws NumberFormatException Formatting error.
     */
    public static int simpleGetCurrentPid()
            throws NumberFormatException, IOException
    {
        int pid = Integer.parseInt(new File("/proc/self").getCanonicalFile().getName());

        return pid;
    }
    
    //*** End of file ***
}
