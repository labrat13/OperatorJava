import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Селяков Павел
 * Created: 20 апр. 2022 г. 2:28:01
 * State: 20 апр. 2022 г. 2:28:01 - initial
 */

/**
 * @author Селяков Павел
 *
 */
public class RTT
{

    /**
     * 
     */
    public RTT()
    {
        // TODO Auto-generated constructor stub
    }
    //*** Constants and  Fields ***

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        String command = "ping www.codejava.net";
        System.out.println(command);
        
        try {
ExecuteApplication("xfce4-terminal --hold -x ", command, "~");
         
        } catch (Exception e) 
        {
            e.printStackTrace();
        }

    }
    
    /**
     * NR-Запустить приложение и немедленно выйти из функции.
     * @param app Application path
     * @param args Argument string
     * @param workDirectory Application working directory
     * @return Возвращает значение 0.
     * @throws Exception Исключение при запуске процесса.
     */
    public static int ExecuteApplication(String app, String args, String workDirectory) throws Exception
    {
    List<String> command = new ArrayList<String>();
    command.add(app);
    command.add(args);

    ProcessBuilder builder = new ProcessBuilder(command);
    //get environment variables
    Map<String, String> environ = builder.environment();
    //set directory
    builder.directory(new File(workDirectory));
    //startup 
    final Process process = builder.start();
    
    
//    InputStream is = process.getInputStream();
//    InputStreamReader isr = new InputStreamReader(is);
//    BufferedReader br = new BufferedReader(isr);
//    String line;
//    while ((line = br.readLine()) != null) 
//    {
//      System.out.println(line);
//    }
//    System.out.println("Program terminated!");
    
    return 0;
    }

    //*** Constructors ***

    //*** Properties ***

    //*** Service  functions ***

    //*** End of file ***
}
