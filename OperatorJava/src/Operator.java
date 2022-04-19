
/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Mar 21, 2022 12:37:20 AM - Ported, Готов к отладке.
 */

import JTerminal.Terminal;
import OperatorEngine.Engine;

/**
 * @author jsmith
 *
 */
public class Operator
{

    /**
     * NR-Основной процесс приложения.
     * 
     * @param args
     *            - main program arguments
     */
    public static void main(String[] args)
    {
        // Сейчас тут уже построен каркас Оператор для запуска движка.
        // Тесты вставлять только в виде вызовов функций тестирования!

        Engine engine = null;
        try
        {

            // 1. попытаться определить, запущен ли уже Оператор,
            // и если да, завершить работу и передать фокус ввода более старой копии

            // 2. TODO: приложение не умеет перехватывать свое завершение, поэтому не
            // обрабатывает закрытие окна. Ctrl+C / Break
            // Так что в лог не выводится запись, завершающая сеанс работы.
            // И БД не закрывается, это надо исправить!

            // 3. create engine object
            engine = new Engine();
            // 4. init engine object
            engine.Init();

            // 5. запускаем цикл приема запросов
            // TODO: тут надо изменить схему запросов, так как выход из цикла запросов
            // в старой схеме запускал процедуру выключения компьютера.
            // А в Линукс все не так, тут теперь просто приложение закрывается.
            // m_exitcode = m_engine.ProcessLoop();
            // processExitCode(m_exitcode);
            engine.CommandLoop();

            // TODO: разобраться с исключениями в engine.Exit()
            engine.Exit();
            engine = null;

        }
        catch (Exception e)
        {
            // если лог работоспособен, то вывести сообщение в него
            // if(Engine.isLogReady(engine))
            Engine.LoggingException(engine, e);

            // print exception
            Terminal.WriteLine(e.getClass().getTypeName());
            Terminal.WriteLine(e.getMessage());
            Terminal.WriteLine();
        }
        finally
        {
            // close engine object
            // записываем в лог сообщение о завершении работы и завершаем работу
            // приложения.
            // TODO: разобраться с исключениями в engine.Exit()
            // if(engine != null)
            // engine.Exit();
            // engine = null;

        }

        return;
    }

    // /**
    // * @throws Exception
    // *
    // */
    // private static void testRegexACP() throws Exception
    // {
    // String s1 = "a.b.c()";
    // String s2 = "aa.bb.cc ( arg1, arg2, arg3 ) ";
    // String s3 = "aa.bb.cc (arg1, arg2, arg3) ";
    //
    // String[] result1 = RegexManager.ParseAssemblyCodePath(s1);
    // String[] result2 = RegexManager.ParseAssemblyCodePath(s2);
    // String[] result3 = RegexManager.ParseAssemblyCodePath(s3);
    //
    // return;
    // }

    // /**
    // * @throws Exception
    // *
    // */
    // private static void testCachedDbAdapter() throws Exception
    // {
    // // TODO Auto-generated method stub
    // CachedDbAdapter db = new CachedDbAdapter();
    // String dbFile = FileSystemManager.getAppDbFilePath();
    // String constr = CachedDbAdapter.CreateConnectionString(dbFile);
    // db.Open(constr);
    //
    //
    // db.Close();
    //
    // return;
    // }

    // /**
    // * @throws Exception
    // * @throws ClassNotFoundException
    // *
    // */
    // private static void test_Database() throws ClassNotFoundException, Exception
    // {
    //
    // SqliteDbAdapter db = new SqliteDbAdapter();
    // String dbFile = FileSystemManager.getAppDbFilePath();
    // String constr = SqliteDbAdapter.CreateConnectionString(dbFile);
    // db.Open(constr);
    // boolean result = db.CreateDatabaseTables();
    // if(result == false)
    // throw new Exception("Cannot create database tables");
    // db.Close();
    // //create 3 procedures and 3 places
    // Procedure pr1 = new Procedure();
    // pr1.set_Description("Description");
    // pr1.set_Path("a.b.c");
    // pr1.set_Regex("regex");
    // pr1.set_TableId(0);
    // pr1.set_Title("TestProcedure1");
    // pr1.set_Ves(0.5);
    //
    // Procedure pr2 = new Procedure();
    // pr2.set_Description("Description");
    // pr2.set_Path("a.b.c");
    // pr2.set_Regex("regex");
    // pr2.set_TableId(0);
    // pr2.set_Title("TestProcedure2");
    // pr2.set_Ves(0.5);
    //
    // Procedure pr3 = new Procedure();
    // pr3.set_Description("Description");
    // pr3.set_Path("a.b.c");
    // pr3.set_Regex("regex");
    // pr3.set_TableId(0);
    // pr3.set_Title("TestProcedure3");
    // pr3.set_Ves(0.5);
    //
    // Place pl1 = new Place();
    // pl1.set_Description("descr");
    // pl1.set_Path("a:b:c");
    // pl1.set_PlaceTypeExpression("Class::Class1");
    // pl1.set_Synonim("syno1, syno12");
    // pl1.set_TableId(0);
    // pl1.set_Title("place1");
    // pl1.ParseEntityTypeString();
    //
    // Place pl2 = new Place();
    // pl2.set_Description("descr");
    // pl2.set_Path("a:b:c");
    // pl2.set_PlaceTypeExpression("Class::Class2");
    // pl2.set_Synonim("syno2, syno22");
    // pl2.set_TableId(0);
    // pl2.set_Title("place2");
    // pl2.ParseEntityTypeString();
    //
    // Place pl3 = new Place();
    // pl3.set_Description("descr");
    // pl3.set_Path("a:b:c");
    // pl3.set_PlaceTypeExpression("Class::Class3");
    // pl3.set_Synonim("syno3, syno32");
    // pl3.set_TableId(0);
    // pl3.set_Title("place3");
    // pl3.ParseEntityTypeString();
    //
    // //add to database
    // db.Open();
    // db.AddPlace(pl1);
    // db.AddPlace(pl2);
    // db.AddPlace(pl3);
    //
    // db.TransactionCommit();
    //
    // db.AddProcedure(pr1);
    // db.AddProcedure(pr2);
    // db.AddProcedure(pr3);
    //
    // db.TransactionCommit();
    //
    // db.Close();
    //
    // //test item operations
    // db.Open();
    //
    // boolean active = db.isConnectionActive();
    // int lastId = db.getLastRowId(SqliteDbAdapter.TablePlaces, 50);
    // int lastId2 = db.getLastRowId(SqliteDbAdapter.TableProcs, 50);
    // int maxid = db.getTableMaxInt32(SqliteDbAdapter.TablePlaces, "id", 50);
    // int minid = db.getTableMinInt32(SqliteDbAdapter.TablePlaces, "id", 50);
    // int rowcount = db.GetRowCount(SqliteDbAdapter.TablePlaces, "id", 50);
    // boolean isrowExists = db.IsRowExists(SqliteDbAdapter.TablePlaces, "id", 2, 50);
    // //read all
    // LinkedList<Place> places = db.GetAllPlaces();
    // LinkedList<Procedure> procs = db.GetAllProcedures();
    // //delete row
    // int r1 = db.RemovePlace(2);
    // int r2 = db.RemoveProcedure(2);
    // db.TransactionCommit();
    //
    // db.Close();
    // return;
    // }

    // /** NT- test
    // * @throws Exception
    // */
    // private static void test_ApplicationSettings() throws Exception
    // {
    // ApplicationSettingsBase as = new ApplicationSettingsBase();
    // as.addItem("test1", "0", "Too short field description text");
    // as.addItem("test2", "Text text text /home/jsmith/text>5", "this specified text");
    // as.addItem("test3", "a", "Очень\nДлинное\nмногострочное \n\"Описание\"");
    // as.Store("/home/jsmith/settings.txt");
    //
    // as.Load("/home/jsmith/settings.txt");
    //
    // return;
    // }

    // /**
    // * @throws Exception
    // *
    // */
    // private static void test_LogSubsystem2() throws Exception
    // {
    // LogManager lm = new LogManager2(null);
    //
    // lm.Open();
    //
    // lm.AddMessage(EnumLogMsgClass.QueryStarted, EnumLogMsgState.OK, "Test started");
    //
    // lm.Close();
    //
    // return;
    // }

    // /**
    // * @throws Exception
    // *
    // */
    // private static void test_LogSubsystem() throws Exception
    // {
    // LogManager lm = new LogManager(null);
    //
    // lm.Open();
    //
    // lm.AddMessage(EnumLogMsgClass.QueryStarted, EnumLogMsgState.OK, "Test started");
    //
    // lm.Close();
    //
    // return;
    // }

    // /**
    // * @throws Exception
    // */
    // private static void test_Version() throws Exception
    // {
    // Version v = new Version(1, 2, 3, 4);
    // Version g = Version.parse("1.2.3.4");
    // String vs1 = v.toString();
    // String gs1 = g.toString();
    // Terminal.WriteLine(vs1 + " == " + gs1);
    // }

    // /// <summary>
    // /// Объект механизма исполнения команд поьзователя
    // /// </summary>
    // private static Engine m_engine;
    //
    // /// <summary>
    // /// Писатель файла лога
    // /// </summary>
    // private static StreamWriter logWriter = null;
    //
    // /// <summary>
    // /// код операции выполняемой перед завершением приложения
    // /// </summary>
    // private static ProcedureResult m_exitcode;
    //
    //
    // /// <summary>
    // /// Это консольное приложение, работающее подобно интерпретатору
    // командной строки,
    // /// чтобы обрабатывать полноценный русскоязычный текст.
    // /// </summary>
    // /// <param name="args"></param>
    // static void Main(string[] args)
    // {
    // //попытаться определить, запущен ли уже Оператор,
    // //и если да, завершить работу и передать фокус ввода более старой копии
    // //Это работает, но криво сделано.
    // if (Utility.DoubleApplication() == true)
    // return;
    //
    // //TODO: приложение не умеет перехватывать свое завершение, поэтому не
    // обрабатывает закрытие окна.
    // //Так что в лог не выводится запись, завершающая сеанс работы.
    // //И БД не закрывается, это надо исправить!
    // Utility.DisableConsoleCloseButton();
    // //это выключает кнопку Close на окне, и теперь приложение нельзя закрыть
    // через системное меню.
    // //И по alt+F4 консольные приложения не закрываются никакие.
    // //Закрываются по Ctrl+C или Ctrl+Break
    // //Console.TreatControlCAsInput = true; - я пробовал включить это, но
    // консоль команды странно обрабатывает - виснет на них.
    // Console.TreatControlCAsInput = false;
    // Console.CancelKeyPress += new
    // ConsoleCancelEventHandler(Console_CancelKeyPress);
    //
    // //можно добавить поддержку ввода клавиш команд Да Нет Отмена,
    // //есть идеи в
    // ms-help://MS.VSCC.v90/MS.MSDNQTR.v90.en/fxref_mscorlib/html/bcf70b80-2b4b-d7d2-05ed-15ff0ceab52f.htm
    // //Для этого надо сначала запрашивать первый символ через
    // Console.readKey(true),
    // //а потом, если это не командная клавиша, выводить ее на экран и собирать
    // остальной ввод как обычную строку.
    // //Esc = Отмена; + = Да; - = Нет;
    // //Решено: не делать подобные фичи - использовать только обычный текст!
    //
    //
    // try
    // {
    // //открываем в текущем каталоге текстовый файл лога для записи введенных
    // запросов
    // logWriter = new StreamWriter("log.txt", true, Encoding.UTF8);
    // logWriter.AutoFlush = true;
    //
    // //создать и инициализировать механизм обработки команд пользователя
    // m_engine = new Engine(logWriter);
    // m_engine.Init();
    //
    // //DEBUG TESTS: место для запуска проверочных тестов
    // DebugTestPlace();
    //
    // //запускаем цикл приема запросов
    // m_exitcode = m_engine.ProcessLoop();
    //
    // m_engine.Exit();
    // //записываем в лог сообщение о завершении работы и завершаем работу
    // приложения.
    // logWriter.WriteLine("ENDSESSION {0}", Utility.DateTimeNowToString());
    // }
    // catch (Exception ex)
    // {
    // Console.WriteLine("EXCEPTION {0} : {1}", ex.GetType().ToString(),
    // ex.Message);
    // if (logWriter != null)
    // logWriter.WriteLine("EXCEPTION {0} : {1}", ex.GetType().ToString(),
    // ex.Message);
    // //попробуем закрыть движок при ошибке
    // if(m_engine != null)
    // m_engine.Exit();
    // }
    //
    // //закрываем лог
    // if (logWriter != null)
    // logWriter.Close();
    //
    // //обработать требование завершения работы перед самым закрытием
    // приложения.
    // PowerManager.ProcessExitCode(m_exitcode);
    //
    // return;
    // }
    //
    // static void Console_CancelKeyPress(object sender, ConsoleCancelEventArgs
    // e)
    // {
    // if (e.SpecialKey == ConsoleSpecialKey.ControlC)
    // e.Cancel = true;
    // else if (e.SpecialKey == ConsoleSpecialKey.ControlBreak)
    // {
    // e.Cancel = false;
    // //
    // m_engine.Exit();
    // logWriter.WriteLine("Close by Ctrl+Break {0}",
    // Utility.DateTimeNowToString());
    // //записываем в лог сообщение о завершении работы и завершаем работу
    // приложения.
    // logWriter.WriteLine("ENDSESSION {0}", Utility.DateTimeNowToString());
    // }
    //
    // return;
    // }
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    // /// <summary>
    // /// Площадка для отладки функций. Размещать тесты здесь.
    // /// </summary>
    // private static void DebugTestPlace()
    // {
    // //тест парсера выражения классов сущностей - успешно 31072016
    // //EntityTypesCollection etc = new EntityTypesCollection();
    // //etc.ParseExpression("Файловая система :: Папка <Файловая система ::
    // Папка, Файл > ; Мои каталоги :: Каталог музыки <Файловая система ::
    // Папка, Файл::Файл музыки>; моя музыка");
    //
    // //Тест парсера простого регекса.
    // //String regex = RegexManager.ConvertSimpleToRegex2("копировать %файл в
    // %папку");
    // //bool match = RegexManager.IsMatchQuery(regex, "Копировать
    // С:\\Temp\\file.txt в C:\\Мои Документы");
    //
    // //Тест замены аргументов командной строки приложения
    // //Dictionary<String, String> dict = new Dictionary<string,string>();
    // //dict.Add("arg1", "value1");
    // //dict.Add("arg2", "value2");
    // //String result = RegexManager.ConvertApplicationCommandString(" my
    // app.exe -t -d%arg1%[56*4765] -c\"%arg2\"", dict);
    //
    // //Тест деления командной строки на аргументы и путь приложения.
    // //Только exe и com приложения поддерживаются
    // //String[] sar = RegexManager.ParseCommandLine("C:\\Temp
    // files\\notepad.exe -dfile cmd");
    //
    // //PowerManager.ExecuteApplication("C:\\Program Files\\Windows
    // NT\\Accessories\\wordpad.exe");
    // //PowerManager.ExecuteApplication("\"C:\\Program Files\\Windows
    // NT\\Accessories\\wordpad.exe\"");
    // //PowerManager.ExecuteApplication("C:\\Program Files\\Windows
    // NT\\Accessories\\wordpad.exe C:\\Tenp\\1.txt");
    // //PowerManager.ExecuteApplication("\"C:\\Program Files\\Windows
    // NT\\Accessories\\wordpad.exe\" C:\\Tenp\\1.txt");
    //
    // ////проверить что путь это путь к функции сборки
    // //bool result = false;
    // //result =
    // RegexManager.IsAssemblyCodePath("имясборки.имякласса.имяфункции()");
    // //result =
    // RegexManager.IsAssemblyCodePath("имясборки.имякласса.имяфункции(арг1)");
    // //result =
    // RegexManager.IsAssemblyCodePath("имясборки.имякласса.имяфункции(арг1,
    // арг2, арг3 )");
    //
    // ////тест выделения аргументов из команды пользователя - успешно
    // //String command = "копировать кошку в резиновую кошку";
    // //String pattern = "^копировать (?<file>.+) в (?<folder>.+)$";
    // //ArgumentCollection coll =
    // RegexManager.ExtractArgumentsFromCommand(command, pattern);
    //
    // }
    //
    //
    //

}
