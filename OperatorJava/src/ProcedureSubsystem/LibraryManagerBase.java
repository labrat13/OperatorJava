/**
 * @author Селяков Павел
 *         Created: Mar 14, 2022 10:19:37 PM
 *         State: Mar 21, 2022 12:37:20 AM - Ported, Готов к отладке.
 */
package ProcedureSubsystem;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import Lexicon.EnumDialogConsoleColor;
import Lexicon.EnumSpeakDialogResult;
import LogSubsystem.EnumLogMsgClass;
import LogSubsystem.EnumLogMsgState;
import OperatorEngine.ArgumentCollection;
import OperatorEngine.Engine;
import OperatorEngine.EnumProcedureResult;
import OperatorEngine.FileSystemManager;
import OperatorEngine.Place;
import OperatorEngine.Procedure;
import OperatorEngine.UserQuery;
import OperatorEngine.Utility;
import OperatorEngine.Version;

/**
 * NT- Базовый класс для менеджеров бибилиотек Процедур.
 * 
 * @author Селяков Павел
 *
 */
public class LibraryManagerBase
{
    // TODO: добавить сюда поле пути к папке утилит этой библиотеки Процедур. И функцию его заполнения тоже.
    // так как в каждой библиотеке путь свой, то придется подставлять этот путь во время сборки массива Процедур для передачи в Оператор.
    // Задача эта выглядит сложной пока что..

    // TODO: в производном классе переопределить методы onInit() и onExit() для реализации новых ресурсов данного класса

    // *** Fields and constants ***

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
     * Название библиотеки Процедур как идентификатор Библиотеки.
     */
    protected String        m_LibraryTitle;

    // *** Constructors ***
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
     * NT-Конструктор
     * 
     * @param engine
     *            Backreference for Engine
     * @param title
     *            Library title
     * @param libPath
     *            Library JAR file path
     */
    public LibraryManagerBase(Engine engine, String title, String libPath)
    {
        // set backreference
        m_Engine = engine;
        this.m_LibraryPath = libPath;
        this.m_LibraryTitle = title;
        this.m_Initialized = false;

        return;
    }

    // *** Member access functions ***

    /**
     * NT- Get current library file path.
     * 
     * @return Function returns current library file path.
     */
    public String get_LibraryPath()
    {
        return this.m_LibraryPath;
    }

    // /**
    // * NT- Set current library file path.
    // *
    // * @param p
    // * Current library file path.
    // */
    // public void set_LibraryPath(String p)
    // {
    // this.m_LibraryPath = p;
    // }

    /**
     * NT- Получить флаг инициализации Менеджера Библиотеки Процедур.
     * 
     * @return Функция возвращает состояние инициализации Менеджера Библиотеки Процедур.
     */
    public boolean get_Initialized()
    {
        return this.m_Initialized;
    }

    /**
     * NT- Get library title
     * 
     * @return the libraryTitle
     */
    public String get_LibraryTitle()
    {
        return m_LibraryTitle;
    }

    // /**
    // * NT-Set library title
    // *
    // * @param libraryTitle
    // * the libraryTitle to set
    // */
    // public void set_LibraryTitle(String libraryTitle)
    // {
    // this.m_LibraryTitle = libraryTitle;
    // }

    // *** Public API ***
    /**
     * NT- Initialize library manager.
     * This function must be overriden in child class.
     * 
     * @throws Exception
     *             Error in processing.
     */
    public void Init() throws Exception
    {
        if (this.m_Initialized == false)
        {
            // тут вызвать метод инициализации Библиотеки процедур, переопределяемый в производных классах.
            this.onInit();
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
            // тут вызвать метод де-инициализации Библиотеки процедур, переопределяемый в производных классах.
            this.onExit();
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
        // if (this.m_Initialized == false)
        // {
        //
        // }
        // else
        // {
        //
        // }
        throw new Exception("Function must be overridden");
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
        // if (this.m_Initialized == false)
        // {
        //
        // }
        // else
        // {
        //
        // }
        throw new Exception("Function must be overridden");
    }

    // *** Override this in child classes ***
    /**
     * NT-Initialize Library Data. This function must be overrided in child classes.
     */
    protected void onInit() throws Exception
    {
        throw new Exception("Function must be overridden");
    }

    /**
     * NT-De-initialize Library Data. This function must be overrided in child classes.
     */
    protected void onExit() throws Exception
    {
        throw new Exception("Function must be overridden");
    }

    // *** Статические функции загрузки классов ***

    /**
     * NT-Try get library manager from specified jar file.
     * 
     * @param engine
     *            Operator engine reference.
     * @param title
     *            Library title.
     * @param path
     *            JAR file path.
     * @return Function returns library manager object or null if it is not found.
     * @throws Exception
     *             Error on loading.
     * 
     */
    public static LibraryManagerBase loadLibraryManager(
            Engine engine,
            String title,
            String path) throws Exception
    {
        // throw exceptions if any error
        // return null if no manager in that file
        // TODO: не забыть вписать в объект менеджера путь к его файлу path!
        LibraryManagerBase result = null;
        URLClassLoader loader = null;
        String classTitle = title + ".LibraryManager";

        try
        {
            if (Utility.StringIsNullOrEmpty(path) || (FileSystemManager.isFileExists(path) == false))
                throw new Exception("Не найдена библиотека Процедур: " + title);

            // 2. load class from specified JarFile
            URL classUrl = new URL("file://" + path);
            URL[] classUrls = new URL[] { classUrl };
            loader = new URLClassLoader(classUrls, engine.getClass().getClassLoader());
            // Если класслоадер потребуется более одного раза, следует как-то сделать его постоянным?
            Class<?> cls = null;
            // load class, return null if not exists
            cls = loader.loadClass(classTitle);

            // 3. Package annotation check
            Package pg = cls.getPackage();
            // не может не быть корневого пакета
            if (pg == null)
                throw new Exception("Корневой пакет не найден в библиотеке Процедур " + path);
            OperatorProcedure packageAnnot = pg.getAnnotation(OperatorProcedure.class);
            if (packageAnnot == null)
                throw new Exception(String.format("Корневой пакет в библиотеке Процедур \"%s\" не помечен аннотацией OperatorProcedure.", path));
            if (packageAnnot.State() == ImplementationState.NotRealized)
                throw new Exception(String.format("Корневой пакет в библиотеке Процедур \"%s\" помечен как NotRealized.", path));

            // 4. if class not annotated, stop work
            OperatorProcedure annot = cls.getAnnotation(OperatorProcedure.class);
            if (annot == null)
                throw new Exception(String.format("Класс LibraryManager в библиотеке Процедур \"%s\" не помечен аннотацией OperatorProcedure.", path));
            // 5. find method
            ImplementationState state = annot.State();
            // String class_title = annot.Title();
            // String class_description = annot.Description();
            if (state == ImplementationState.NotRealized)
                throw new Exception(String.format("Класс LibraryManager в библиотеке Процедур \"%s\" помечен как NotRealized.", path));
            if (state == ImplementationState.NotTested)
            {
                // print warning "This Procedure method class %s marked as not tested"
                String msg1 = String.format("Класс LibraryManager в библиотеке Процедур \"%s\" помечен как NotTested", path);
                engine.get_OperatorConsole().PrintTextLine(msg1, EnumDialogConsoleColor.Предупреждение);
                // add msg to log
                engine.getLogManager().AddMessage(EnumLogMsgClass.Default, EnumLogMsgState.Default, msg1);
            }
            // 6. create object
            Constructor<?> cs = cls.getConstructor(Engine.class, String.class, String.class);
            result = (LibraryManagerBase) cs.newInstance(engine, title, path);

        }
        finally
        {
            // close loader before exit
            if (loader != null)
                loader.close();
        }
        return result;
    }

    /**
     * NT-Invoke procedure method
     * 
     * @param p
     *            Procedure for execution
     * @param names
     *            assembly.class.method titles array
     * @param jarFilePath
     *            Path to library JAR file.
     * @param engine
     *            Engine object reference
     * @param manager
     *            Library manager object reference.
     * @param command
     *            Query object
     * @param args
     *            Procedure arguments array
     * @return Function returns Procedure result code.
     * @throws Exception
     *             "Не найдена библиотека Процедур" и другие.
     */
    public static EnumProcedureResult invokeProcedure(
            Procedure p,
            String[] names,
            String jarFilePath,
            Engine engine,
            LibraryManagerBase manager,
            UserQuery command,
            ArgumentCollection args)
            throws Exception
    {
        URLClassLoader loader = null;
        EnumProcedureResult result = EnumProcedureResult.Error;

        try
        {

            String AssemblyTitle = names[0];
            String ClassTitle = AssemblyTitle + "." + names[1]; // = "AssemblyTitle.ClassTitle"
            String MethodTitle = names[2];
            String printMethodTitle = ClassTitle + "." + MethodTitle; // = "AssemblyTitle.ClassTitle.MethodTitle"

            // 1. Получить абсолютный путь к JAR файлу сборки.
            if (Utility.StringIsNullOrEmpty(jarFilePath) || (FileSystemManager.isFileExists(jarFilePath) == false))
                throw new Exception("Не найдена библиотека Процедур: " + AssemblyTitle);
            // а далее - по коду из прототипа

            // 2. load class from specified JarFile
            URL classUrl = new URL("file://" + jarFilePath);
            URL[] classUrls = new URL[] { classUrl };
            loader = new URLClassLoader(classUrls, engine.getClass().getClassLoader());
            Class<?> cls = loader.loadClass(ClassTitle);

            // 3. Package annotation check
            Package pg = cls.getPackage();
            // не может не быть корневого пакета
            if (pg == null)
                throw new Exception("Корневой пакет не найден в библиотеке Процедур " + jarFilePath);
            OperatorProcedure packageAnnot = pg.getAnnotation(OperatorProcedure.class);
            if (packageAnnot == null)
                throw new Exception(String.format("Корневой пакет в библиотеке Процедур \"%s\" не помечен аннотацией OperatorProcedure.", jarFilePath));
            if (packageAnnot.State() == ImplementationState.NotRealized)
                throw new Exception(String.format("Корневой пакет в библиотеке Процедур \"%s\" помечен как NotRealized.", jarFilePath));
            if (packageAnnot.State() == ImplementationState.NotTested)
            {
                // print warning "This Procedure method class %s marked as not tested"
                String msg1 = "Корневой пакет библиотеки \"" + AssemblyTitle + "\", содержащий данную Процедуру, помечен как NotTested";
                engine.get_OperatorConsole().PrintTextLine(msg1, EnumDialogConsoleColor.Предупреждение);
                // add msg to log
                engine.getLogManager().AddMessage(EnumLogMsgClass.Default, EnumLogMsgState.Default, msg1);
            }

            // 4. if class not annotated, stop work
            OperatorProcedure annot = cls.getAnnotation(OperatorProcedure.class);
            if (annot == null)
                throw new Exception("Класс \"" + ClassTitle + "\", содержащий данную Процедуру, не помечен аннотацией OperatorProcedure.");
            // 5. find method
            ImplementationState state = annot.State();
            // String class_title = annot.Title();
            // String class_description = annot.Description();
            if (state == ImplementationState.NotRealized)
                throw new Exception("Класс \"" + ClassTitle + "\", содержащий данную Процедуру, помечен как NotRealized.");
            if (state == ImplementationState.NotTested)
            {
                // print warning "This Procedure method class %s marked as not tested"
                String msg1 = "Класс \"" + ClassTitle + "\", содержащий данную Процедуру, помечен как NotTested";
                engine.get_OperatorConsole().PrintTextLine(msg1, EnumDialogConsoleColor.Предупреждение);
                // add msg to log
                engine.getLogManager().AddMessage(EnumLogMsgClass.Default, EnumLogMsgState.Default, msg1);
            }
            // 6. get method
            Method m = cls.getMethod(MethodTitle, Engine.class, LibraryManagerBase.class, UserQuery.class, ArgumentCollection.class);
            // throw NoSuchMethodException if cannot find method

            // 7. check method annotation
            OperatorProcedure annot2 = m.getAnnotation(OperatorProcedure.class);

            if (annot2 == null)
                throw new Exception("Указанный метод \"" + printMethodTitle + "\" не помечен аннотацией OperatorProcedure.");
            ImplementationState state2 = annot2.State();
            // String method_title = annot2.Title();
            // String method_description = annot2.Description();
            if (state2 == ImplementationState.NotRealized)
                throw new Exception("Указанный метод \"" + printMethodTitle + "\" помечен как NotRealized.");
            // запросить у пользователя подтверждение на запуск процедуры, помеченной как требующая отладки.
            if (state2 == ImplementationState.NotTested)
            {
                String question = String.format("Исполняемый метод \"%s\" помечен как NotTested. Продолжить выполнение?", printMethodTitle);
                EnumSpeakDialogResult esdr = engine.get_OperatorConsole().PrintДаНетОтмена(question);
                // если пользователь ответил не "Да", то отменить исполнение Процедуры.
                if (!esdr.isДа())
                {
                    // TODO: тут надо прервать выполнение и вернуть результат EnumProcedureResult.CancelledByUser
                    // Я накидал сейчас что попало, это не то, что нужно.
                    result = EnumProcedureResult.CancelledByUser;
                    // throw new Exception("Процедура прервана пользователем.");
                }
            }

            // 8. execute method if not previous cancelled by user
            if (result != EnumProcedureResult.CancelledByUser)
            {
                Object returned = m.invoke(null, engine, manager, command, args);
                // 8. return result
                if (returned == null)
                    throw new Exception("Ошибка: Метод \"" + printMethodTitle + "\" вернул null.");

                result = (EnumProcedureResult) returned;
            }
        }
        // ловить тут ничего не будем - все вызывающему коду передаем.
        finally
        {
            // close class loader
            if (loader != null)
                loader.close();
        }

        return result;
    }

}
