/**
 * @author Селяков Павел
 *         Created: Mar 14, 2022 10:19:37 PM
 *         State: Mar 14, 2022 10:19:37 PM - initial
 */
package ProcedureSubsystem;

import OperatorEngine.Engine;
import OperatorEngine.Place;
import OperatorEngine.Procedure;
import OperatorEngine.Version;

/**
 * NR- Базовый класс для менеджеров бибилиотек Процедур.
 * 
 * @author Селяков Павел
 *
 */
public class LibraryManagerBase
{
    // TODO: добавить сюда статическое поле пути к папке утилит этой библиотеки Процедур. И фнкцию его заполнения тоже.
    // так как в каждой библиотеке путь свой, то придется подставлять этот путь во время сборки массива Процедур для передачи в Оператор.
    // Задача эта выглядит сложной пока что..

    /**
     * Backreference to Operator Engine object
     */
    protected Engine        m_Engine;

    /**
     * Static Version string for current library
     */
    protected static String m_VersionString = "1.0.0.0";

    /**
     * Флаг что менеджер был инициализирован
     */
    protected boolean       m_Initialized;

    /**
     * Путь к файлу библиотеки Процедур.
     */
    protected String        m_LibraryPath;

    /**
     * NT-Constructor
     */
    public LibraryManagerBase()
    {
        this.m_Initialized = false;
        this.m_Engine = null;

        return;
    }

    /**
     * NT- Get current library file path.
     * 
     * @return Function returns current library file path.
     */
    public String get_LibraryPath()
    {
        return this.m_LibraryPath;
    }

    /**
     * NT- Set current library file path.
     * 
     * @param p
     *            Current library file path.
     */
    public void set_LibraryPath(String p)
    {
        this.m_LibraryPath = p;
    }

    /**
     * NT- Initialize library manager.
     * This function must be overriden in child class.
     * 
     * @param engine
     *            Operator Engine object backreference
     * @throws Exception
     *             Error in processing.
     */
    public void Init(Engine engine) throws Exception
    {
        if (this.m_Initialized == false)
        {
            // set backreference
            m_Engine = engine;
            // mark object as initialized
            this.m_Initialized = true;
        }

        return;
    }

    /**
     * NT-Deinitialize library manager.
     * This function must be overriden in child class.
     * 
     * @throws Exception
     *             Error in processing.
     */
    public void Exit() throws Exception
    {
        if (this.m_Initialized == true)
        {
            // reset backreference
            m_Engine = null;
            // clear init flag
            this.m_Initialized = false;
        }

        return;
    }

    /**
     * NT-Get library version
     * 
     * @return Function returns version of current Procedure library
     * @throws Exception
     *             Error in processing.
     */
    public static Version getLibraryVersion() throws Exception
    {
        return Version.parse(m_VersionString);
    }

    /**
     * NR- Get Places collection from this library.
     * This function must be overriden in child class.
     * 
     * @return Function returns array of Places, defined in this library.
     * @throws Exception
     *             Error in processing.
     */
    public Place[] getLibraryPlaces() throws Exception
    {
        throw new Exception("Function not implemented");
    }

    /**
     * NR-Get Procedures collection from this library.
     * This function must be overriden in child class.
     * 
     * @return Function returns array of Procedures, defined in this library.
     * @throws Exception
     *             Error in processing.
     */
    public Procedure[] getLibraryProcedures() throws Exception
    {
        throw new Exception("Function not implemented");
    }

}
