/**
 * @author Селяков Павел
 * Created: Mar 14, 2022 10:19:37 PM
 * State: Mar 14, 2022 10:19:37 PM - initial
 */
package ProcedureSubsystem;

import OperatorEngine.Engine;
import OperatorEngine.Place;
import OperatorEngine.Procedure;
import OperatorEngine.Version;

/**
 * NR- Базовый класс для менеджеров бибилиотек Процедур.
 * @author Селяков Павел
 *
 */
public class LibraryManagerBase
{
    //TODO: добавить сюда статическое поле пути к папке утилит этой библиотеки Процедур. И фнкцию его заполнения тоже.
    // так как в каждой библиотеке путь свой, то придется подставлять этот путь во время сборки массива Процедур для передачи в Оператор.
    //Задача эта выглядит сложной пока что..
    
    /**
     * Static reference to Operator Engine object
     */
    protected static Engine m_Engine;
    /**
     * Static Version string for current library
     */
    protected static String m_VersionString = "1.0.0.0";
    
    /**
     * NR- Initialize library manager.
     * This function must be overriden in child class.
     * @param engine Operator Engine object backreference
     * @throws Exception Error in processing.
     */
    public static void Init(Engine engine) throws Exception
    {
        //set backreference
        m_Engine = engine;
        
    }
    /**
     * NR-Deinitialize library manager.
     * This function must be overriden in child class.
     * @throws Exception Error in processing.
     */
    public static void Exit() throws Exception
    {
        m_Engine = null;
    }
    /**
     * NT-Get library version 
     * @return Function returns version of current Procedure library
     * @throws Exception Error in processing.
     */
    public static Version getLibraryVersion() throws Exception
    {
      return Version.parse(m_VersionString);   
    }
    /**
     * NR- Get Places collection from this library.
     * This function must be overriden in child class.
     * @return Function returns array of Places, defined in this library.
     * @throws Exception Error in processing.
     */
    public static Place[] getLibraryPlaces() throws Exception
    {
        throw new Exception("Function not implemented");
    }
    /**
     * NR-Get Procedures collection from this library.
     * This function must be overriden in child class.
     * @return Function returns array of Procedures, defined in this library.
     * @throws Exception Error in processing.
     */
    public static Procedure[] getLibraryProcedures() throws Exception
    {
        throw new Exception("Function not implemented");
    }
    
}
