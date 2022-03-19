/**
 * @author Селяков Павел
 *         Created: Mar 14, 2022 10:12:26 PM
 *         State: Mar 14, 2022 10:12:26 PM - initial
 */
package GeneralProcedures;

import OperatorEngine.Engine;
import OperatorEngine.Item;
import OperatorEngine.Place;
import OperatorEngine.Procedure;
import ProcedureSubsystem.ImplementationState;
import ProcedureSubsystem.LibraryManagerBase;
import ProcedureSubsystem.OperatorProcedure;

/**
 * Класс менеджера библиотеки Процедур.
 * Выполняем инициализацию библиотеки, завершение работы библиотеки, загрузку Процедур и Мест в Оператор.
 * 
 * @author Селяков Павел
 *
 */
@OperatorProcedure(State = ImplementationState.NotTested,
        Title = "GeneralProcedures library manager",
        Description = "Library manager for GeneralProcedures library")
public class LibraryManager extends LibraryManagerBase
{

    // /**
    // * Backreference to Operator Engine object
    // */
    // protected Engine m_Engine;

    /**
     * Static Version string for current library
     */
    protected static String m_VersionString = "1.0.0.0";// TODO: add valid library version here

    /**
     * NT-Constructor
     */
    public LibraryManager()
    {
        this.m_Initialized = false;
        this.m_Engine = null;

        return;
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
    @Override
    public void Init(Engine engine) throws Exception
    {
        if (this.m_Initialized == false)
        {
            // set backreference
            m_Engine = engine;

            // TODO: add code here

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
    @Override
    public void Exit() throws Exception
    {
        if (this.m_Initialized == true)
        {
            m_Engine = null;

            // TODO: add code here

            this.m_Initialized = false;
        }

        return;
    }

    /**
     * NR- Get Places collection from this library.
     * This function must be overriden in child class.
     * 
     * @return Function returns array of Places, defined in this library.
     * @throws Exception
     *             Error in processing.
     */
    @Override
    public Place[] getLibraryPlaces() throws Exception
    {
        throw new Exception("Function not implemented");// TODO: add code here
    }

    /**
     * NT-Get Procedures collection from this library.
     * This function must be overriden in child class.
     * 
     * @return Function returns array of Procedures, defined in this library.
     * @throws Exception
     *             Error in processing.
     */
    @Override
    public Procedure[] getLibraryProcedures() throws Exception
    {

        // Тут надо вернуть вызывающему коду массив объектов Процедур, реализоыванных в этой библиотеке Процедур.
        // Пока просто создаем их в коде, но если Процедур очень много, то следует внести заранее их в XML файл,
        // добавить его в jar-файл этой библиотеки и тут загрузить их из файла XML.
        // Это сэкономит немного памяти VM.

        // создать выходной массив
        Procedure[] result = new Procedure[1];
        // создать объект Процедуры и добавить в выходной массив
        Procedure p = new Procedure();
      //флаг, что элемент не из БД Оператора.
        p.set_TableId(Item.Id_ItemNotFromDatabase);
        p.set_Title("Тест хеловорд");
        p.set_Description("Тестовая процедура: выводит на консоль helloworld  и звуковой сигнал.");
        p.set_Path("GeneralProcedures.TestProcedures.testHelloWorld");
        p.set_Regex("запустить тест хеловорд");
        //вес процедуры надо подобрать более точно, он зависит от общего набора Процедур.
        //TODO: придумать, как динамически изменять и определять вес Процедуры.
        // - но в данном случае его все равно нельзя изменить, он зафиксирован в коде тут.
        p.set_Ves(0.5);
        //Добавить Процедуру в выходной массив
        result[0] = p;
        // вернуть выходной массив
        return result;
    }

}
