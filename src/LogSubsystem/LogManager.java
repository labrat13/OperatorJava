/**
 * @author Селяков Павел
 *         Created: Feb 22, 2022 4:52:59 PM
 *         State: Feb 23, 2022 11:29:32 PM - ready to test
 */
package LogSubsystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;

import OperatorEngine.Engine;
import OperatorEngine.FileSystemManager;
import OperatorEngine.SystemInfoManager;
import OperatorEngine.Utility;

/*
 * Для каждого сеанса лога - свой файл лога, а не все в один файл лога.
 * А более старые файлы лога удалять при превышении их количества?
 * TODO: Это не отработано в виндовс-прототипе!
 */

/**
 * NT-Менеджер лога Оператор
 * 
 * @author Селяков Павел
 *
 */
public class LogManager
{
    // TODO: Надо описать подсистему лога в документации проекта!
    // TODO: Надо добавить функцию регулирования размера каталога файлов лога.
    // - когда ее запускать? Подсчет размера каталога лога - долгое дело?
    // - предел размера каталога лога - задавать в настройках Оператор, а они не
    // реализованы.
    // TODO: Надо переделать на XmlWriter, поскольку в строках текстов могут оказаться недопустимые символы!
    // в доках по хмл есть CDATA, но он тоже имеет ограничения на символы.
    
    /**
     * Application log folder path
     */
    public final static String   AppLogFolderPath = SystemInfoManager.GetUserHomeFolderPath() + File.separator + "Operator" + File.separator + "logs";
    // = FileSystemManager.AppLogFolderPath; - заменено на время отладки,
    // поскольку Engine класс не готов.

    /**
     * Line break symbol "/n"
     */
    public final static String   LineSeparator    = System.lineSeparator();

    /**
     * Log writer object
     */
    protected OutputStreamWriter m_Writer;

    /**
     * Backreference to Engine object
     */
    protected Engine             m_Engine;

    /**
     * Log subsystem is ready to serve
     */
    protected boolean            m_Ready;

    /**
     * Default constructor
     * 
     * @param en
     *            Engine object reference
     */
    public LogManager(Engine en)
    {
        this.m_Engine = en;
        this.m_Writer = null;
        // log subsystem not ready
        this.m_Ready = false;
    }

    /**
     * Log subsystem is ready to serve
     * 
     * @return
     */
    public boolean isReady()
    {
        return this.m_Ready;
    }

    /**
     * NT-Initialize log subsystem here and open log session
     * 
     * @throws Exception
     *             "Session already exists" or "Error on writing to log file."
     * 
     */
    public void Open() throws Exception
    {
        // Если каталог лога не найден - создать новый каталог лога и файл лога
        // в нем.
        // 1. create file if not exists
        // - if log folder not exists, try create it.
        // - if log folder not writable, throw exception.
        File logFolder = new File(LogManager.AppLogFolderPath);
        if (!logFolder.exists()) logFolder.mkdir();
        // 2. create filename as log-datetime.xml
        String filename = logFolder.getPath() + FileSystemManager.FileSeparator + this.makeNewFileName();// session_timestamp.xml
        File logfile = new File(filename);
        // если файл сессии уже существует, выбросить исключение об этом
        if (logfile.exists()) throw new Exception("Session already exists");
        // open or create file with StreamWriter with UTF-8 encoding and path
        // Operator/logs directory/
        // 4. create writer object
        FileOutputStream os = new FileOutputStream(logfile.getPath(), false);
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
        // И вывести стандартный заголовок XML файла: <?xml version="1.0"
        // encoding="UTF-8" standalone="yes" ?>
        // 5. write log file header
        osw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>");
        osw.write(LineSeparator);
        // Вывести открытие сессии <session> как корень документа.
        osw.write("<session>");
        osw.write(LineSeparator);
        // store to class field
        this.m_Writer = osw;
        // 6. write session start message
        this.AddMessage(EnumLogMsgClass.SessionStarted, EnumLogMsgState.OK, "Session opened");
        // set ready flag
        this.m_Ready = true;

        return;
    }

    /**
     * NT-Close log session
     * 
     * @throws IOException
     *             Error on writing to log file
     */
    public void Close() throws IOException
    {
        // Cleanup log subsystem here
        if (this.m_Ready == true)
        {
            // write session finish message
            this.AddMessage(EnumLogMsgClass.SessionFinished, EnumLogMsgState.OK, "Session closed");
            // Вывести закрытие сессии </session>.
            this.m_Writer.write("</session>");
            this.m_Writer.write(LineSeparator);
        }
        // clear ready flag
        this.m_Ready = false;
        // Закрыть StreamWriter
        if (this.m_Writer != null)
        {
            this.m_Writer.close();
            this.m_Writer = null;
        }

        return;
    }

    /**
     * NT-Append new message object to log
     * 
     * @param msg
     *            New message object.
     * @throws IOException
     *             Error on writing to log file.
     */
    public void AddMessage(LogMessage msg) throws IOException
    {
        String s = msg.ToXmlString();
        this.m_Writer.write(s);
        this.m_Writer.write(LineSeparator);
        this.m_Writer.flush();

        return;
    }

    /**
     * NT-append new message object to log
     * 
     * @param c
     *            Event class code
     * @param s
     *            Event state code
     * @param text
     *            Event text description
     * @throws IOException
     *             Error on writing to log file.
     */
    public void AddMessage(EnumLogMsgClass c, EnumLogMsgState s, String text) throws IOException
    {
        LogMessage msg = new LogMessage(c, s, text);
        this.AddMessage(msg);

        return;
    }

    /**
     * NT-Create new log file name for new session
     * 
     * @return Function returns new log file name/
     */
    private String makeNewFileName()
    {
        LocalDateTime dt = LocalDateTime.now();
        String p = Utility.DateTimeToFileNameString(dt);

        return "session_" + p + ".xml";
    }

    /**
     * NT-Add log message about exception. This function not thrown (suppress) any exceptions.
     * @param e Exception object to log
     */
    public void AddExceptionMessage(Exception e)
    {
        try {
        LogMessage msg = new LogMessage(EnumLogMsgClass.ExceptionRaised, EnumLogMsgState.Fail, e.toString());
        this.AddMessage(msg);
        }
        catch(Exception ex)
        {
            //add debug breakpoint here;
        }
        return;
    }

}
