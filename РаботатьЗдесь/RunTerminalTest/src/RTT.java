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
//            //это работает, открывает пустой терминал с указанным текущим каталогом.
//            Runtime.getRuntime().exec("exo-open --launch TerminalEmulator", null, new File("/home/jsmith/Документы/") );
            
//            //это работает, открывает терминал и запускает команду пинг.
//            Runtime.getRuntime().exec("xfce4-terminal -x ping localhost");
            
//            //run tux commander - рабочий каталог игнорируется
//            Runtime.getRuntime().exec("tuxcmd", null, new File("/home/jsmith/Документы/"));
            
         
//          //run midnight commander - терминал завис.
//          Runtime.getRuntime().exec("mc", null, new File("/home/jsmith/Документы/"));
            
//          //это работает, открывает указанный файл в редакторе по умолчанию.
//          Runtime.getRuntime().exec("exo-open /home/jsmith/Документы/InternetNotes.txt", null, new File("/home/jsmith/Документы/") );            
//          ExecuteApplication("exo-open", "/home/jsmith/Документы/InternetNotes.txt", "/home/jsmith/Документы/"); 
            
            //Это открывает файрфокс пустой.
            ExecuteApplication("exo-open", "https://www.google.com", "/home/jsmith/Документы/", false);
            
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }

    }
    
    /**
     * NR-Запустить приложение и немедленно выйти из функции.
     * @param workDirectory Application working directory
     * @return Возвращает значение 0.
     * @throws Exception Исключение при запуске процесса.
     */
    public static int ExecuteApplication(String cmd, String args, String workDirectory, boolean logEnvironmentVariables) throws Exception
    {
List<String> command = new ArrayList<String>();
command.add(cmd);
command.add(args);

    ProcessBuilder builder = new ProcessBuilder(command);
    //get environment variables
    if(logEnvironmentVariables == true)
    {
    Map<String, String> environ = builder.environment();
    System.out.println("-------------------------------");
    System.out.println("--- Environment: ---");
    System.out.println("-------------------------------");
    for(Map.Entry<String, String> ent : environ.entrySet())
        System.out.println(String.format("\"%s\"-->\"%s\"", ent.getKey(), ent.getValue()));
    System.out.println("-------------------------------");
    }
    //set directory
    builder.directory(new File(workDirectory));
    //startup 
    final Process process = builder.start();
    
    System.out.println("Process started!");
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


    //*** End of file ***
}
