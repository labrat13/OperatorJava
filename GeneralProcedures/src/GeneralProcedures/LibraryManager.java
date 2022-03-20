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
    //Памятка: переменные родительского класса LibraryManagerBase:
    // * Backreference to Operator Engine object
    // protected Engine m_Engine;
    // * Флаг что менеджер был инициализирован
    // protected boolean       m_Initialized;
    // * Путь к файлу библиотеки Процедур.
    // protected String        m_LibraryPath;
    // * Название библиотеки Процедур как идентификатор Библиотеки.
    // protected String        m_LibraryTitle;
    
    
    /**
     * Static Version string for current library
     */
    protected static String m_VersionString = "1.0.0.0";// TODO: add valid library version here

    /**
     * NT-Конструктор
     * 
     * @param engine
     *            Backreference for Engine
     * @param title
     *            Library title
     * @param libPath
     *            Library JAR file path
     */
    public LibraryManager(Engine engine, String title, String libPath)
    {
        super(engine, title, libPath);

        //Добавлять код только для новых ресурсов.
        
        return;
    }

    /**
     * NT- Initialize library manager.
     * This function must be overriden in child class.
     * 
     * @throws Exception
     *             Error in processing.
     */
    @Override
    protected void onInit() throws Exception
    {
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
    protected void onExit() throws Exception
    {
        return;
    }

    /**
     * NT- Get Places collection from this library.
     * This function must be overriden in child class.
     * 
     * @return Function returns array of Places, defined in this library.
     * @throws Exception
     *             Error in processing.
     */
    @Override
    public Place[] getLibraryPlaces() throws Exception
    {
        Place[] result = new Place[0];// TODO: add library places here
        
        // //Place p;
        //
        // //Это шаблон, образец, не трогать его!
        // //TODO: Описать правила заполнения полей объекта, привести ссылки на
        // форматы, документацию.
        //
        // //p = new Place();
        // ////заполнить вручную поля
        // //p.Title = "";//Название сущности места, не обязательно, в работе
        // механизма не используется
        // //p.Synonim = "";//Список синонимов названия сущности, должны быть
        // уникальными в системе.
        // //p.Description = "";//Текст описания сущности, не обязателен, в работе
        // механизма не используется
        // //p.Path = "";//Веб-путь или файловый путь к месту
        // //p.PlaceTypeExpression = "";//Перечисление типов сущности
        // ////добавление в коллекцию
        // //p.ParseEntityTypeString();//Распарсить список типов сущностей
        // //this.AddPlace(p);//добавить строку
        
        
        return result;
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

        // Тут надо вернуть вызывающему коду массив объектов Процедур, реализованных в этой библиотеке Процедур.
        // Пока просто создаем их в коде, но если Процедур очень много, то следует внести заранее их в XML файл,
        // добавить его в jar-файл этой библиотеки и тут загрузить их из файла XML.
        // Это сэкономит немного памяти VM.
        
        //TODO: добавить ссылку на текущую библиотеку в каждый объект здесь.

        // создать выходной массив
        Procedure[] result = new Procedure[1];
        // создать объект Процедуры и добавить в выходной массив
        Procedure p = new Procedure();
      //флаг, что элемент не из БД Оператора.
        p.set_TableId(Item.Id_ItemNotFromDatabase);
        p.set_Title("Тест хеловорд");
        p.set_Description("Тестовая процедура: выводит на консоль helloworld  и звуковой сигнал.");
        p.set_Path("GeneralProcedures.TestProcedures.testHelloWorld()");
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
