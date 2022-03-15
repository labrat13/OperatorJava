/**
 * @author Селяков Павел
 * Created: Mar 14, 2022 10:12:26 PM
 * State: Mar 14, 2022 10:12:26 PM - initial
 */
package GeneralProcedures;

import OperatorEngine.Engine;
import OperatorEngine.Place;
import OperatorEngine.Procedure;
import ProcedureSubsystem.ImplementationState;
import ProcedureSubsystem.LibraryManagerBase;
import ProcedureSubsystem.OperatorProcedure;

/** Класс менеджера библиотеки Процедур.
 * Выполняем инициализацию библиотеки, завершение работы библиотеки, загрузку Процедур и Мест в Оператор.
 * @author Селяков Павел
 *
 */
@OperatorProcedure(State = ImplementationState.NotTested, Title = "GeneralProcedures library manager", Description = "Library manager for GeneralProcedures library")
public class LibraryManager extends LibraryManagerBase
{

//    /**
//     * Static reference to Operator Engine object
//     */
//    protected static Engine m_Engine;
    
    /**
     * Static Version string for current library
     */
    protected static String m_VersionString = "1.0.0.0";//TODO: add valid library version here
    
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
        
      //TODO: add code here
        
    }
    /**
     * NR-Deinitialize library manager.
     * This function must be overriden in child class.
     * @throws Exception Error in processing.
     */
    public static void Exit() throws Exception
    {
        m_Engine = null;
      //TODO: add code here
    }
    /**
     * NR- Get Places collection from this library.
     * This function must be overriden in child class.
     * @return Function returns array of Places, defined in this library.
     * @throws Exception Error in processing.
     */
    public static Place[] getLibraryPlaces() throws Exception
    {
        throw new Exception("Function not implemented");//TODO: add code here
    }
    /**
     * NR-Get Procedures collection from this library.
     * This function must be overriden in child class.
     * @return Function returns array of Procedures, defined in this library.
     * @throws Exception Error in processing.
     */
    public static Procedure[] getLibraryProcedures() throws Exception
    {
        throw new Exception("Function not implemented");//TODO: add code here
    }
    
}
