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
    // Памятка: переменные родительского класса LibraryManagerBase:
    // * Backreference to Operator Engine object
    // protected Engine m_Engine;
    // * Флаг что менеджер был инициализирован
    // protected boolean m_Initialized;
    // * Путь к файлу библиотеки Процедур.
    // protected String m_LibraryPath;
    // * Название библиотеки Процедур как идентификатор Библиотеки.
    // protected String m_LibraryTitle;

    // Памятка: Если аннотация @OperatorProcedure не указана, Движок будет выдавать исключение, что соответствующий элемент не помечен аннотацией, и не будет
    // исполнять Процедуру.
    // Если @OperatorProcedure.State = ImplementationState.NotRealized, Движок будет выдавать исключение, что соответствующий элемент не реализован, и не будет
    // исполнять Процедуру.
    // Если @OperatorProcedure.State = ImplementationState.NotTested, Движок будет выдавать сообщение, что соответствующий элемент не тестирован, и будет
    // исполнять Процедуру.
    // Если @OperatorProcedure.State = ImplementationState.Ready, Движок будет исполнять Процедуру.

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

        // Добавлять код только для новых ресурсов.

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
        // Добавить код инициализации ресурсов библиотеки процедур здесь.
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
        // Добавить код завершения ресурсов библиотеки процедур здесь.
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

        // //Это шаблон, образец, не трогать его!
        // //TODO: Описать правила заполнения полей объекта, привести ссылки на
        // форматы, документацию.

        // Place p;
        //
        // //заполнить вручную поля
        // p = new Place();
        // //Уникальный идентификатор элемента или первичный ключ таблицы. 0 по умолчанию, здесь можно не указывать.
        // //p.set_TableId(0);
        // //Краткое однострочное название сущности места.
        // p.set_Title("title");
        // //Краткое однострочное описание Сущности, не обязателен.
        // p.set_Description("descr");
        // //Веб-путь или файловый путь к месту
        // p.set_Path("path");
        // //Перечисление типов сущности по моей методике.
        // //TODO: добавить сюда ссылку на документацию по методике классов Мест.
        // p.set_PlaceTypeExpression("Приложение::ТекстовыйРедактор<Файл::ТекстовыйФайл>;");
        // //Название хранилища как ключ для поиска в движке.
        // p.set_Storage(this.m_LibraryTitle);
        // //Список синонимов названия сущности, должны быть уникальными в системе.
        // p.set_Synonim("слон, слона, слону, слоне, слоном");
        // //Распарсить список типов сущностей
        // p.ParseEntityTypeString();
        // //добавить объект Места в выходной массив
        // result[0] = p;

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

        // TODO: добавить ссылку на текущую библиотеку в каждый объект здесь.

        // создать выходной массив
        Procedure[] result = new Procedure[1];
        // создать объект Процедуры и добавить в выходной массив
        Procedure p = new Procedure();
        // Уникальный идентификатор элемента или первичный ключ таблицы. 0 по умолчанию, здесь можно не указывать.
        // p.set_TableId(0);
        // Краткое однострочное название сущности места.
        p.set_Title("Тест хеловорд");
        // Краткое однострочное описание Сущности, не обязателен.
        p.set_Description("Тестовая процедура: выводит на консоль helloworld  и звуковой сигнал.");
        // Веб-путь или командная строкас аргументами или путь к процедуре в библиотеке процедур.
        p.set_Path("GeneralProcedures.TestProcedures.testHelloWorld()");// Скобки обязательны!
        // Регекс процедуры
        p.set_Regex("запустить тест хеловорд");
        // вес процедуры надо подобрать более точно, он зависит от общего набора Процедур.
        // TODO: придумать, как динамически изменять и определять вес Процедуры.
        // - но в данном случае его все равно нельзя изменить, он зафиксирован в коде тут.
        p.set_Ves(0.5);
        // добавить ссылку на текущую библиотеку
        p.set_Storage(this.m_LibraryTitle);
        // Добавить Процедуру в выходной массив
        result[0] = p;

        // вернуть выходной массив
        return result;
    }

}
