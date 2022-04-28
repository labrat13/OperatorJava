/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Mar 21, 2022 12:37:20 AM - В процессе портирования.
 */
package OperatorEngine;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.xml.stream.XMLStreamException;

import DbSubsystem.OperatorDbAdapter;
import Lexicon.BCSA;
import Lexicon.DialogConsole;
import Lexicon.EnumDialogConsoleColor;
import LogSubsystem.EnumLogMsgClass;
import LogSubsystem.EnumLogMsgState;
import LogSubsystem.LogManager;
import LogSubsystem.LogManager2;
import ProcedureSubsystem.ProcedureExecutionManager;
import Settings.ApplicationSettingsKeyed;
import Settings.EnumSettingKey;
import Settings.SettingItem;

// Консоль Оператора:
// Функции доступа к консоли из сборок процедур сейчас перенесены в класс Operator.Lexicon.DialogConsole.
// Для вывода сообщений на консоль использовать только! объект engine.OperatorConsole.
// так как я планирую вынести консоль совсем отдельно, то надо уже сейчас ее использование ограничить.

/** 
 * NT-Движок исполнения Процедур.
 * @author jsmith
 *         
 */
public class Engine
{

    /**
     * Строка названия Оператора для платформы Linux Java
     */
    public final static String        ApplicationTitle    = "Operator";

    /**
     * Строка версии Оператора для платформы Linux Java
     */
    public final static String        EngineVersionString = "1.0.0.0";

    /**
     * Статический объект версии движка для платформы Linux Java
     */
    public static Version             EngineVersion       = Version.tryParse(EngineVersionString);

    // TODO: Заменить все обращения к logWriter на вызовы функций менеджера лога
    /**
     * Менеджер подсистемы лога
     */
    private LogManager                m_logman;

    /**
     * Объект адаптера БД Оператора.
     * Кешированный адаптер БД содержит коллекции элементов и сам их обслуживает.
     */
    private OperatorDbAdapter         m_db;
    // TODO: Объект реализован частично. Исправить весь код для него.

    /**
     * Объект консоли Оператора. Выделен чтобы упорядочить код работающий с консолью, так как он вызывается из сборок процедур, создаваемых сторонними
     * разработчиками.
     */
    private DialogConsole             m_OperatorConsole;
    // TODO: Объект реализован частично. Исправить весь код для него.

    /**
     * Объект настроек Оператора
     */
    private ApplicationSettingsKeyed  m_Settings;

    /**
     * Объект менеджера исполнения Процедур
     */
    private ProcedureExecutionManager m_PEM;

    /**
     * Объект Менеджера кэша Мест и Процедур Оператора
     */
    private ElementCacheManager       m_ECM;

    /**
     * Объект семантического анализатора
     */
    private BCSA                      m_BCSA;

    /**
     * NT-Стандартный конструктор
     * 
     * @throws Exception Ошибка при создании объекта Движка.
     */
    public Engine() throws Exception
    {
        // create log manager object
        this.m_logman = new LogManager2(this);
        // создать объект консоли Оператора
        this.m_OperatorConsole = new DialogConsole(this);
        // create engine settings object
        this.m_Settings = new ApplicationSettingsKeyed(this);
        // create database adapter object
        this.m_db = new OperatorDbAdapter(this);
        // create execution manager
        this.m_PEM = new ProcedureExecutionManager(this);
        // create cache manager object - after DB and PEM only.
        this.m_ECM = new ElementCacheManager(this, this.m_db, this.m_PEM);
        // create semantic analyzer object
        this.m_BCSA = new BCSA(this);

        return;
    }

    // #region Properties
    /**
     * NT- Get log manager object
     * 
     * @return Функция возвращает log manager object
     */
    public LogManager getLogManager()
    {
        return this.m_logman;
    }

    /**
     * NT-Получить объект адаптера БД Оператора.
     * 
     * @return Функция возвращает Объект адаптера БД Оператора.
     */
    public OperatorDbAdapter get_Database()
    {
        return this.m_db;
    }

    /**
     * NT-Получить объект консоли Оператора. Должен быть доступен из сторонних сборок.
     * 
     * @return Функция возвращает Объект консоли Оператора.
     */
    public DialogConsole get_OperatorConsole()
    {
        return this.m_OperatorConsole;
    }

    /**
     * NT-Получить объект настроек движка Оператора.
     * 
     * @return Функция возвращает объект настроек движка Оператора.
     */
    public ApplicationSettingsKeyed get_EngineSettings()
    {
        return this.m_Settings;
    }

    /**
     * NT-Получить объект кеш-коллекции Процедур и Мест Оператора.
     * 
     * @return Возвращает объект кеш-коллекции Процедур и Мест Оператора.
     */
    public ElementCacheManager get_ECM()
    {
        return this.m_ECM;
    }

    /**
     * NT-Получить объект Менеджера Библиотек Процедур.
     * 
     * @return Функция возвращает объект Менеджера Библиотек Процедур.
     */
    public ProcedureExecutionManager get_PEM()
    {
        return this.m_PEM;
    }

    /**
     * NT- получить объект семантического анализатора запросов.
     * 
     * @return Функция возвращает объект семантического анализатора запросов.
     */
    public BCSA get_BCSA()
    {
        return this.m_BCSA;
    }
    // #endregion

    // Функции инициализации и завершения движка =================

    /**
     * NT- Инициализация механизма
     * 
     * @throws Exception Ошибка при инициализации Движка.
     */
    public void Init() throws Exception
    {
        // this.m_OperatorConsole.PrintTextLine("Operator loading...", EnumDialogConsoleColor.Сообщение);

        // 1. check operator folder exist
        if (FileSystemManager.isAppFolderExists() == false)
        {
            String msg1 = "Ошибка: Каталог Оператор не найден: " + FileSystemManager.getAppFolderPath();
            String msg2 = "Будет создан новый каталог Оператор с настройками по умолчанию.";
            this.m_OperatorConsole.PrintTextLine(msg1, EnumDialogConsoleColor.Предупреждение);
            this.m_OperatorConsole.PrintTextLine(msg2, EnumDialogConsoleColor.Предупреждение);
            FileSystemManager.CreateOperatorFolder();
        }

        // 2. open engine log session
        // this.m_logman.AddMessage(new
        // LogMessage(EnumLogMsgClass.SessionStarted, EnumLogMsgState.OK,
        // "Session opened"));
        // - это уже сделано в this.m_logman.Open();
        this.m_logman.Open();

        // 3. load engine settings
        // Если файл настроек не обнаружен, вывести сообщение об этом и
        // создать новый файл настроек с дефолтовыми значениями.
        String settingsFilePath = FileSystemManager.getAppSettingsFilePath();
        if (!FileSystemManager.isAppSettingsFileExists())
        {
            String msg3 = "Файл настроек " + settingsFilePath + " не найден! Будет создан файл с настройками по умолчанию.";
            this.m_logman.AddMessage(EnumLogMsgClass.Default, EnumLogMsgState.Fail, msg3);
            this.m_OperatorConsole.PrintTextLine(msg3, EnumDialogConsoleColor.Предупреждение);
            this.m_Settings.Reset();
            this.m_Settings.Store(settingsFilePath);
        }
        else this.m_Settings.Load(settingsFilePath);

        // 4. init database
        // заполнить кеш-коллекции процедур и мест данными из БД
        // CachedDbAdapter делает это сам

        // если новой бд нет в каталоге приложения, создаем ее.
        String dbFile = FileSystemManager.getAppDbFilePath();
        String connectionString = OperatorDbAdapter.CreateConnectionString(dbFile);
        if (FileSystemManager.isAppDbFileExists() == false)
        {
            // print warning about database
            String msg4 = "Файл базы данных " + dbFile + " не найден! Будет создан новый пустой файл базы данных.";
            this.m_logman.AddMessage(EnumLogMsgClass.Default, EnumLogMsgState.Fail, msg4);
            this.m_OperatorConsole.PrintTextLine(msg4, EnumDialogConsoleColor.Предупреждение);
            // TODO: тут лучше попытаться загрузить бекап-копию БД, после
            // предупреждения о отсутствии основного файла.
            // Но эту копию надо сначала создать.

            // create new database here. Open, write, close.
            OperatorDbAdapter.CreateNewDatabase(this, dbFile);
            // но работать с пустой БД - начинать все сначала.
        }
        // else
        // open existing database and close - as test
        this.m_db.Open(connectionString);
        this.m_db.Close();
        // Использовать адаптер так, чтобы он открывал БД только на
        // время чтения или записи, а не держал ее постоянно открытой.
        // Так меньше вероятность повредить бд при глюках линукса.

        // 5. Open PEM
        // TODO: дополнить код здесь полезными проверками
        this.m_PEM.Open();// TODO: this function not completed now.

        // 6. Open ECM
        // TODO: дополнить код здесь полезными проверками
        this.m_ECM.Open();// TODO: this function not completed now.

        // 7. Open BCSA
        this.m_BCSA.Open();// TODO: this function not completed now.

        return;
    }

    /**
     * NT-Close engine
     * 
     * @throws Exception
     *             Ошибка при освобождении ресурсов.
     */
    public void Exit() throws Exception
    {
        // закрыть BCSA
        if (this.m_BCSA != null)
            this.m_BCSA.Close();
        // закрыть ECM
        if (this.m_ECM != null)
            this.m_ECM.Close();
        // закрыть PEM
        if (this.m_PEM != null)
            this.m_PEM.Close();
        // Закрыть БД если еще не закрыта
        if (this.m_db != null)
            this.m_db.Close();
        // close settings object
        this.m_Settings.StoreIfModified();
        // close log session - последним элементом
        this.m_logman.Close();
        this.m_logman = null;

        return;
    }

    // *************************************************************
    // *** Safe access to Log ***

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
     * @throws XMLStreamException
     *             Error on writing to log file.
     */
    protected void safeAddLogMsg(
            EnumLogMsgClass c,
            EnumLogMsgState s,
            String text)
            throws IOException, XMLStreamException
    {
        // проверить существование движка и лога, и затем добавить сообщение в
        // лог.
        if (Engine.isLogReady(this))
            this.getLogManager().AddMessage(c, s, text);

        return;
    }

    /**
     * NT-Write exception to engine log if available
     * 
     * @param en
     *            Engine object
     * @param e
     *            Exception object
     */
    public static void LoggingException(Engine en, Exception e)
    {
        try
        {
            // get log manager
            LogManager l = en.getLogManager();
            // check log ready and write exception object
            if (l.isReady())
                l.AddExceptionMessage(e);
        }
        catch (Exception e2)
        {
            ;// add breakpoint here
        }

        return;
    }

    /**
     * NT-Check log is available
     * 
     * @param en
     *            Engine object
     * @return Function returns True if log writing is available, returns False
     *         otherwise.
     */
    public static boolean isLogReady(Engine en)
    {
        if (en == null)
            return false;
        // get log manager
        LogManager l = en.getLogManager();
        if (l == null)
            return false;
        // check log ready
        return l.isReady();
    }

//    /**
//     * NT-Вывести на консоль информацию об исключении
//     * 
//     * @param e
//     *            Объект исключения.
//     */
//    private void PrintExceptionToConsole(Exception e)
//    {
//        // TODO: заменить эту функцию на PrintExceptionMessageToConsoleAndLog(String msg, Exception ex)
//
//        // TODO: вложенное исключение выводить, если есть, вместо первого.
//        // так как в процедурах сборок процедур они упаковываются в исключение
//        // механизма отражения
//        // if (e. != null)
//        // this.OperatorConsole.PrintExceptionMessage(e.InnerException);
//        // else
//        this.m_OperatorConsole.PrintExceptionMessage(e);
//
//        return;
//    }

    /**
     * NT-Add exception message to Log and Console.
     * 
     * @param msg
     *            Message title.
     * @param ex
     *            Exception object.
     */
    public void PrintExceptionMessageToConsoleAndLog(String msg, Exception ex)
    {
        this.m_OperatorConsole.PrintExceptionMessage(msg, ex);
        this.m_logman.AddExceptionMessage(msg, ex);

        return;
    }

    /**
     * NT-Вывести сообщение на консоль и в лог.
     * 
     * @param text
     *            Текст сообщения.
     * @param color
     *            Класс сообщения Консоли.
     * @param cls
     *            Класс сообщения Лога.
     * @param state
     *            Состояние сообщения Лога.
     * @throws Exception
     *             Ошибка при работе Лога.
     */
    public void AddMessageToConsoleAndLog(
            String text,
            EnumDialogConsoleColor color,
            EnumLogMsgClass cls,
            EnumLogMsgState state) throws Exception
    {
        this.m_OperatorConsole.PrintTextLine(text, color);
        this.m_logman.AddMessage(cls, state, text);
    }

    /**
     * NT-Извлечь значение Настройки из ФайлНастроекОператора или ТаблицаНастроекОператора, иначе вывести сообщение о ее отсутствии.
     * 
     * @return Функция возвращает значение настройки или null.
     * @throws Exception
     *             Ошибка при работе с файлом настроек.
     */
    private String getSettingOrMessage(EnumSettingKey setting, String operation)
            throws Exception
    {
        // TODO: хорошо бы и из БД извлекать такую настройку, если она там есть. Но пока - только ФайлНастроекОператора проверяется.
        String newQuery = this.m_Settings.getValue(setting);
        if (Utility.StringIsNullOrEmpty(newQuery))
        {
            String msg = String.format("Невозможно выполнить %s, поскольку настройка %s не найдена в ФайлНастроекОператора.", operation, setting.getTitle());
            this.AddMessageToConsoleAndLog(msg, EnumDialogConsoleColor.Предупреждение, EnumLogMsgClass.SubsystemEvent_Settings, EnumLogMsgState.Fail);
            //from database
            newQuery = this.m_ECM.getSettingFirstValue(setting.getTitle());
            if(Utility.StringIsNullOrEmpty(newQuery))
            {
                String msg2 = String.format("Невозможно выполнить %s, поскольку настройка %s не найдена в ТаблицаНастроекОператора.", operation, setting.getTitle());
                this.AddMessageToConsoleAndLog(msg2, EnumDialogConsoleColor.Предупреждение, EnumLogMsgClass.SubsystemEvent_Settings, EnumLogMsgState.Fail);   
            }
        }
        return newQuery;
    }

    /** NT - Получить значение настройки из ФайлНастроекОператора или ТаблицаНастроекОператора.
     * @param setting Ключ - название настройки.
     * @return Функция возвращает значение настройки из ФайлНастроекОператора или ТаблицаНастроекОператора. 
     * Функция возвращает null, если поля настройки не найдено.
     * Функция возвращает пустую строку, если значение настройки не указано.
     */
    private String getSettingFromFileOrTable(EnumSettingKey setting)
    {
        //Файл настроек всегда должен проверяться раньше, чем таблица настроек.
        String result = this.m_Settings.getValue(setting);
        if(Utility.StringIsNullOrEmpty(result) == true)
        {
         result = this.m_ECM.getSettingFirstValue(setting.getTitle());
        }
        
        return result;
    }
    
    
    // **********************************************************

    // #endregion

    // #region Основной цикл исполнения механизма

    /*
     * Закомментировал старую функцию, пишу новую, более сложный алгоритм, можно запутаться.
     * Старая оставлена как образец, новые функции все начинаются с (или содержат) Command
     * 
     * /// <summary>
     * /// NR-Основной цикл исполнения механизма
     * /// </summary>
     * public ProcedureResult ProcessLoop()
     * {
     * 
     * 
     * 
     * ProcedureResult result = ProcedureResult.Unknown;
     * // запускаем цикл приема запросов
     * while (true)
     * {
     * this.m_OperatorConsole.PrintTextLine("", EnumDialogConsoleColor.Сообщение);
     * this.m_OperatorConsole.PrintTextLine("Введите ваш запрос:", EnumDialogConsoleColor.Сообщение);
     * String query = this.OperatorConsole.ReadLine();
     * // если был нажат CTRL+C, query может быть null
     * // пока я не знаю, что делать в этом случае, просто перезапущу цикл
     * // приема команды
     * // и при пустой строке тоже просто перезапустить цикл приема команды
     * 
     * // Операторы: return закрывает Оператор, а continue - переводит на
     * // следующий цикл приема команды
     * if (String.IsNullOrEmpty(query))
     * continue;
     * 
     * // триммим из запроса пробелы всякие лишние сразу же
     * // если строка пустая, начинаем новый цикл приема команды
     * query = query.Trim();// query теперь может оказаться пустой строкой
     * if (String.IsNullOrEmpty(query))
     * continue;
     * // а если нет - обрабатываем запрос
     * // logWriter.WriteLine("QUERY {0}", query);
     * this.m_logman.AddMessage(new LogMessage(EnumLogMsgClass.QueryStarted, EnumLogMsgState.Default, query));
     * 
     * 
     * 
     * // Если запрос требует завершения работы, завершаем цикл приема
     * // запросов.
     * // Далее должно следовать сохранение результатов и закрытие
     * // приложения.
     * if (Dialogs.isSleepCommand(query) == true) // спящий режим
     * // компьютера
     * {
     * PowerManager.DoSleep();// запущенные приложения не закрываются,
     * // и Оператор - тоже
     * continue; // не return, так как return завершает работу
     * // Оператора!
     * }
     * else if (Dialogs.isExitAppCommand(query) == true)
     * return ProcedureResult.Exit; // закрытие приложения
     * else if (Dialogs.isExitShutdownCommand(query) == true)
     * return ProcedureResult.ExitAndShutdown;// закрытие приложения и
     * // выключение машины
     * else if (Dialogs.isExitReloadCommand(query) == true)
     * return ProcedureResult.ExitAndReload;// закрытие приложения и
     * // перезагрузка машины
     * else if (Dialogs.isExitLogoffCommand(query) == true)
     * return ProcedureResult.ExitAndLogoff;// закрытие
     * // приложения
     * // и
     * // завершение
     * // сеанса
     * // пользователя
     * // TODO: вообще-то при команде перезагрузки надо сначала запрашивать
     * // подтверждение
     * // А это нужно делать внутри кода процедуры, а не здесь. Но пока мы
     * // просто тестируем возможность.
     * // TODO: пользователь должен решать, какие слова использовать для
     * // этих команд. А они сейчас прошиты в коде. Надо вынести их в настройки Оператор.
     * 
     * 
     * // если функция вернет любой флаг выхода, завершаем цикл приема
     * // запросов
     * result = EventCommandArrived(query);
     * 
     * // вывести сообщение-подтверждение результата процедуры
     * describeProcedureResult(result);
     * 
     * // Режимы сна: если прочие программы не завершаются при
     * // засыпании, то и завершать работу здесь не нужно.
     * if ((result == ProcedureResult.Exit) || (result == ProcedureResult.ExitAndReload) // перезагрузка
     * // компьютера
     * || (result == ProcedureResult.ExitAndShutdown)// выключение
     * // компьютера
     * || (result == ProcedureResult.ExitAndLogoff)) // выход
     * // пользователя
     * // - программы
     * // закрываются!
     * return result;
     * 
     * // модификация запроса и перезапуск его обработки здесь не
     * // предполагается.
     * }
     * 
     * return ProcedureResult.Unknown; // тут никогда мы не должны оказаться.
     * }
     */

    /**
     * NR-Основной цикл исполнения механизма
     * @throws Exception Ошибка при работе ЦиклИсполненияЗапросов.
     */
    public void CommandLoop() throws Exception
    {

        // TODO: работать здесь!!! Такая свалка недоделок получилась, бардак в проекте нарастает.
        // Надо расчищать эти завалы скорее, пока я помню, что тут как и зачем.

        // выводим приветствие и описание программы
        this.m_OperatorConsole.PrintEmptyLine();
        this.m_OperatorConsole.PrintTextLine("Консоль речевого интерфейса. Версия " + Utility.getOperatorVersionString(), EnumDialogConsoleColor.Сообщение);
        this.m_OperatorConsole.PrintTextLine("Для завершения работы приложения введите слово выход или quit", EnumDialogConsoleColor.Сообщение);
        this.m_OperatorConsole.PrintTextLine("Сегодня " + BCSA.CreateLongDatetimeString(LocalDateTime.now()), EnumDialogConsoleColor.Сообщение);
        this.m_OperatorConsole.PrintEmptyLine();
        
        // -3.1 выполнить СтартоваяПроцедура.
        int StartResult = CommandStartupProcedure();
        // -3.2 вывести приглашение пользователю.
        EnumProcedureResult result = EnumProcedureResult.Unknown;
        // запускаем цикл приема запросов
        while (true)
        {
            this.m_OperatorConsole.PrintTextLine("", EnumDialogConsoleColor.Сообщение);
            this.m_OperatorConsole.PrintTextLine("Введите ваш запрос:", EnumDialogConsoleColor.Сообщение);
            // -3.3 ожидать ввод запроса от пользователя.
            String query = this.m_OperatorConsole.ReadLine();
            // если был нажат CTRL+C, query может быть null
            // пока я не знаю, что делать в этом случае, просто перезапущу цикл
            // приема команды
            // и при пустой строке тоже просто перезапустить цикл приема команды

            // Операторы: return закрывает Оператор, а continue - переводит на
            // следующий цикл приема команды

            if (query == null)
                continue;
            // триммим из запроса пробелы всякие лишние сразу же
            // если строка пустая, начинаем новый цикл приема команды
            query = query.trim();
            // query теперь может оказаться пустой строкой
            if (query.isEmpty())
                continue;
            // -3.4 TODO: событие С10 Поступил новый запрос от пользователя.
            // TODO: добавить событие С10 в лог вместе с текстом запроса.

            // TODO: Отложить это все до готовности остальных частей проекта: БД, настроек, остального.

            // Тут упакуем все исполнение запроса и эту функцию, потому
            // что она нужна еще и в CommandStartupProcedure(), где она должна
            // исполнить либо Процедуру, если указан путь к ней, либо
            // прокрутить выборку и исполнение Процедуры для запроса, если это запрос.

            // а семантическую обработку запроса - упустили! Я ее потом приделаю, когда весь Оператор заработает.
            // EnumProcedureResult result = Lexicon.BCSA.ProcessQuery(this, query);

            result = DoCommandExecution(query);
            
            //Описать результат исполнения Процедуры.
            this.describeProcedureResult(result);
            
            // завершение работы Оператор
            if (result == EnumProcedureResult.Exit)
                break;

            // -3.6 если С11 Исполнение запроса успешно завершено.
            // -3.7 если С12, то переход на следующую итерацию цикла.
            // -3.8 если С13, то завершение работы Оператор.
            // -3.9 если С14, то переход на следующую итерацию цикла.

            // -3.10 конец цикла обработки запроса
        }// end while

        // -3.11 выполнить ФинишнаяПроцедура.
        // switch(result)
        // {
        // case ExitAndHybernate:
        // case ExitAndLogoff:
        // case ExitAndReload:
        // case ExitAndShutdown:
        // case ExitAndSleep:
        // //тут не запускаем финишную процедуру, так как можем не успеть до завершения приложения.
        // break;
        // case CancelledByUser:
        // case Error:
        // case Exit:
        // case Success:
        // case Unknown:
        // case WrongArguments:
        // default:
        // //тут надо запустить финишную процедуру
        // break;
        // }
        int FinishResult = CommandFinishProcedure(result);

        return;
    }

    /**
     * NR- Запустить исполнение запроса и вернуть результат
     * 
     * @param query
     *            Строка запроса или путь к Процедуре.
     * @throws Exception 
     */
    private EnumProcedureResult DoCommandExecution(String query) throws Exception
    {
        // TODO: Отложить это все до готовности остальных частей проекта: БД, настроек, остального.

        //TODO: этот код должен перехватывать все исключения и возвращать только EnumProcedureResult.Error при любой ошибке.
        
        // Создать объект запроса пользователя для использования в алгоритме и Процедурах.
        UserQuery userQuery = new UserQuery(query);
        // если это строка запроса, то запустить цикл выбора и исполнения Процедур.
        // если это путь к Процедуре, то запустить эту выбранную Процедуру только.

        // вернуть - яхз пока что возвращать. Наверно, Код результата процедуры?
        EnumProcedureResult exitCode = EnumProcedureResult.Success;
        // -3.5 исполнение запроса
        // - 3.5.1 Выполнить ПредОбработкаЗапроса.
        exitCode = DoPreProcessing(userQuery);//готово
        if (exitCode != EnumProcedureResult.Exit)
        {
            // - 3.5.2 Выполнить ИсполнениеЗапроса.
            // если запрос это путь к Процедуре, то:
            if (RegexManager.IsAssemblyCodePath(userQuery.getQuery()) == true)
            {
                // выполнить Процедуру без аргументов, по ее пути.
                // потом выполнить пост-обработку, если она нужна.
                exitCode = DoSimpleProcedureExecution(userQuery);//готово
            }
            else
            {
                // если запрос - англоязычный, то перенаправить его в Терминал
                if (BCSA.IsNotRussianFirst(userQuery.getQuery()) == true)
                {
                    exitCode = DoCommandEnglishTerminal(userQuery);//готово
                    // TODO: всегда возвращает EnumProcedureResult.Success ?
                }
                else
                {
                    // иначе - запустить цикл выборки Процедур для Запроса и исполнить Процедуру.
                    exitCode = DoProcedureLoopExecution(userQuery);//TODO: работать здесь!!!
                }
            }
            // - 3.5.3 Выполнить ПостОбработкаЗапроса.
            exitCode = DoPostProcessing(exitCode);//готово
            // -3.6 если С11 Исполнение запроса успешно завершено.
            // -3.7 если С12, то переход на следующую итерацию цикла.
            // -3.8 если С13, то завершение работы Оператор.
            // -3.9 если С14, то переход на следующую итерацию цикла.
        }
        // else - DoPreProcessing() returns Exit - нужно завершить работу приложения в вызывающем коде.
        return exitCode;
    }

    /**
     * NT-Пре-процессинг введенного пользователем запроса.
     * 
     * @param userQuery
     *            Объект введенного пользователем запроса.
     */
    private EnumProcedureResult DoPreProcessing(UserQuery userQuery)
    {
        // Тут если текст запроса = тексту одной из встроенных команд, то
        // заменить текст запроса на текст соответствующей Процедуры или команды из ФайлНастроекОператора.

        // 1. Проверяем команду Выход
        if (Lexicon.Dialogs.isExitAppCommand(userQuery.getQuery()) == true)
            return EnumProcedureResult.Exit;
        // если будут еще встроенные команды, то для них надо извлечь из настроек текст запроса и заменить его в userQuery объекте.
        // - и эта замена должна быть записана в Лог (выяснить, делается ли это уже автоматически?).

        // возвращаем код продолжения разбора запроса.
        return EnumProcedureResult.Success;
    }

    /**
     * NT-Выполнить пост-обработку результата исполнения Процедуры.
     * 
     * @param code
     *            Код результата исполнения предыдущей Процедуры.
     * @return Функция возвращает код результата исполнения Процедуры, выбранной как пост-обработка результата предыдущей Процедуры.
     * @throws Exception
     *             Ошибка при работе с файлом настроек.
     */
    private EnumProcedureResult DoPostProcessing(EnumProcedureResult code)
            throws Exception
    {
        // Превратить exitCode в путь Процедуры или текст Запроса
        String newQuery = null;
        switch (code)
        {
            // вызываем вспомогательную функцию, которая и настройку извлекает, и сообщение об отсутствии настройки выводит.
            case ExitAndHybernate:
                newQuery = getSettingOrMessage(EnumSettingKey.CmdHybernate, code.name());
                break;
            case ExitAndLogoff:
                newQuery = getSettingOrMessage(EnumSettingKey.CmdLogoff, code.name());
                break;
            case ExitAndReload:
                newQuery = getSettingOrMessage(EnumSettingKey.CmdReload, code.name());
                break;
            case ExitAndShutdown:
                newQuery = getSettingOrMessage(EnumSettingKey.CmdShutdown, code.name());
                break;
            case ExitAndSleep:
                newQuery = getSettingOrMessage(EnumSettingKey.CmdSleep, code.name());
                break;
            case CancelledByUser:
            case Error:
            case Exit:
            case Success:
            case Unknown:
            case WrongArguments:
            default:
                // ничего не делаем.
                break;
        }
        // run query execution if query has been assigned.
        if (Utility.StringIsNullOrEmpty(newQuery) == false)
            return DoCommandExecution(newQuery);
        else return code;
    }


    
    /**
     * NT-Исполнить запрос через ЦиклПеребораПроцедур.
     * 
     * @param userQuery
     *            Текущий текст запроса.
     * @return Функция возвращает код результата исполнения Процедуры.
     */
    private EnumProcedureResult DoProcedureLoopExecution(UserQuery userQuery)
    {
        //запустить цикл выборки Процедур для Запроса и исполнить Процедуру.
        // TODO: образец: public EnumProcedureResult DoQuery(String query)
        
        EnumProcedureResult result = EnumProcedureResult.Success;
        String regex = null;
        
        try
        {
            // для каждой процедуры из списка процедур из кеша элементов:
            for (Procedure p : this.m_ECM.get_ProcedureCollection().get_Procedures()) 
            {
                // собрать нормальный регекс для процедуры
                // TODO: optimization - можно же это сделать после загрузки регекса
                // из БД как часть процесса распаковки данных, записав в объект
                // Процедуры как служебное поле.
                // а не при каждом исполнении команды от пользователя.
                regex = MakeNormalRegex(p);
                // выполнить регекс и, заодно, определить, является ли процедура пригодной для исполнения
                ArgumentCollection args = RegexManager.ExtractArgumentsFromCommand(userQuery.getQuery(), regex);
                if (args != null)
                {
                    // Тут запускаем процедуру. Она должна теперь проверить свои аргументы и все условия,
                    // и если они не подходят, то завершиться с флагом ProcedureResult.WrongArguments.
                    result = DoProcedureExecute(userQuery, regex, p, args);
                    if (result != EnumProcedureResult.WrongArguments)
                        return result;
                }
            }
            // Тут состояние "Не удалось подобрать процедуру для исполнения запроса"
            // Вынесем его в функцию-обработчик, чтобы модифицировать обработку
            // этого события.
            EventCommandNotExecuted();
            result = EnumProcedureResult.Success;        
        }
        catch (Exception e)
        {
            this.PrintExceptionMessageToConsoleAndLog("Ошибка", e);
            result = EnumProcedureResult.Error;
        }
        
        return result;
    }

    /** 
     * NT- Execute Procedure
     * @param userQuery User query object
     * @param regex Procedure regex string
     * @param p Procedure object
     * @param args Procedure argument collection
     * @return Функция возвращает код результата исполнения Процедуры.
     */
    private EnumProcedureResult DoProcedureExecute(
            UserQuery userQuery,
            String regex,
            Procedure p,
            ArgumentCollection args) throws Exception
    {
        // и еще нужно этим аргументам сопоставить типы мест хотя бы
        TryAssignPlaces(args);
        
        // надо определить, путь исполнения это путь к процедуре или к
        // приложению.
        // TODO:оптимизация: сделать это при загрузке Процедуры из БД и
        // сохранить в служебном поле Процедуры
        boolean isAssemblyCodePath = RegexManager.IsAssemblyCodePath(p.get_Path());
        if (isAssemblyCodePath == false)
        {
            // если к приложению, его надо запустить и вернуть стандартное
            // значение для продолжения работы.
            return DoShellExecute(p, args); //return RunShellExecute(p, args);
            
        }
        else
        {
            // если к процедуре, надо приготовить аргументы, найти сборку,
            // вызвать функцию, передать ей аргументы и вернуть результат.
            return DoLocalAssembly(userQuery, p, args);//return RunLocalAssembly(command, p, args);
        }
        
        
    }

    /** 
     * NT- Запустить Процедуру из локальной БиблиотекаПроцедурОператора.
     * @param userQuery Команда пользователя.
     * @param p Объект процедуры.
     * @param args Коллекция аргументов.
     * @return Функция возвращает результат выполнения процедуры.
     */
    private EnumProcedureResult DoLocalAssembly(
            UserQuery userQuery,
            Procedure p,
            ArgumentCollection args)
    {
        //источник: private EnumProcedureResult RunLocalAssembly(..)
        
        EnumProcedureResult result = EnumProcedureResult.Success;
        try
        {
            // получить имена частей пути. в порядке: сборка, класс, функция,
            // аргументы по порядку следования если они есть
            String[] names = RegexManager.ParseAssemblyCodePath(p.get_Path());
            // тут аргументы уже должны быть заполнены значениями и типами
            // и местами и готовы к выполнению.
            result = this.m_PEM.invokeProcedure(p, names, userQuery, this, args);
            // Это выбрасывает исключения процесса запуска и исполнения Процедуры.
        }
        catch (Exception e)
        {
            // вызов исполнения не удался.
            // пока выведем сообщение об исключении в консоль.
            // TODO: вот надо завести в механизме статическую переменную
            // отладки, включаемую через отдельную процедуру, и по ней выводить
            // на экран эти отладочные данные.
            // TODO: надо вывести тут сообщение об исключении в общий лог.
            // если не выводить сообщение об ошибке, то непонятно, почему
            // команда не исполняется.
            // например, когда выключился спящий режим, команда спать просто
            // выводила сообщение я не умею.

            this.PrintExceptionMessageToConsoleAndLog("Ошибка: ", e);

            // вернуть флаг ошибки
            // TODO: Определить, что тут должна возвращать текущая функция, если во время исполнения Процедуры произошла ошибка.
            // Ошибка - это ошибка, а не несоответствие Запроса и Процедуры. А тут - наобум назначено возвращаемое значение.
            result = EnumProcedureResult.WrongArguments;
        }
        
        // возвращаем то что вернет процедура
        return result;
    }

    /**
     * NT-Открыть Терминал и перенаправить в него текущий текст запроса.
     * 
     * @param userQuery
     *            Текущий текст запроса
     * @return Функция возвращает EnumProcedureResult.Success при успехе.
     *         Функция возвращает EnumProcedureResult.Error, если при запуске Терминала произошла ошибка.
     */
    private EnumProcedureResult DoCommandEnglishTerminal(UserQuery userQuery)
    {
        // образец: private EnumProcedureResult ExecuteWithTerminal(String query)
        //Состояние: код есть, тестовый прототип написан, но терминал не запускается - чего-то не хватает.
        //Еще, для программ нужен рабочий каталог. Для wget подойдет каталог Downloads, для других программ - другие варианты желательны.
        //Но возможно указать только один, и в нем все последствия запускаемых программ будут оставаться.
        //Указать его в ФайлНастроекОператора - надо добавить поле для РабочийКаталог.
        // - Рабочий каталог - текущий каталог - часть контекста текущей работы пользователя.
        //   Если запаковать работу пользователя в Проект, то текущий рабочий каталог - это будет элемент контекста проекта.
        // - а как сейчас - все приложения будут в одном этом каталоге исполняться? Там будет свалка.
        // - в итоге, надо пока что избегать этой проблемы с текущим каталогом, и собирать материал по ней.
        
        //вызвать PEM.ExecuteApplicationSimple() or PEM.ExecuteApplication()
        
        EnumProcedureResult result = EnumProcedureResult.Success;
        try
        {
            //1. извлечь из ФайлНастроекОператора или ТаблицаНастроекОператора значение командной строки терминала
            //TODO: EnumSettingKey.ForProcedureTerminal не используется сейчас, вместо него EnumSettingKey.ShellExecuteCommand.
            // - Надо проверить этот факт и решить, что делать с неиспользуемой настройкой.
            String cmdterm = this.getSettingFromFileOrTable(EnumSettingKey.ForCommandTerminal); 
            if(Utility.StringIsNullOrEmpty(cmdterm))
                    throw new Exception("Не найдена команда запуска Терминала из настройки " + EnumSettingKey.ForCommandTerminal.getTitle());
            //2. извлечь из ФайлНастроекОператора или ТаблицаНастроекОператора значение рабочего каталога терминала
            String workDirectory = this.getSettingFromFileOrTable(EnumSettingKey.DefaultWorkingDirectory);
            if(Utility.StringIsNullOrEmpty(workDirectory))
                throw new Exception("Не найден путь к рабочему каталогу Терминала из настройки " + EnumSettingKey.DefaultWorkingDirectory.getTitle());
            //3. TODO: разделить командную строку терминала на приложение и аргументы в классе RegexManager.
            
            //4. пока разделить нечем - вызвать PEM.ExecuteApplicationSimple()
            String cmdline = cmdterm.trim() + " " + userQuery.getQuery();
            this.m_PEM.ExecuteApplicationSimple(cmdline, workDirectory);
        }
        catch (Exception e)
        {
            this.PrintExceptionMessageToConsoleAndLog("Ошибка", e);
            result = EnumProcedureResult.Error;
        }
        return result;
    }

    /**
     * NT-Запустить команду через механизм ShellExecute.
     * 
     * @param p Объект Процедуры
     * 
     * @param args Коллекция аргументов.
     * @return Функция возвращает результат выполнения процедуры.      
     */
    private EnumProcedureResult DoShellExecute(Procedure p, ArgumentCollection args)
    {       
        //вызвать PEM.ExecuteApplicationSimple() or PEM.ExecuteApplication()
        
        EnumProcedureResult result = EnumProcedureResult.Success;
        try
        {
            //1. извлечь из ФайлНастроекОператора или ТаблицаНастроекОператора значение командной строки ShellExecute
            String cmdterm = this.getSettingFromFileOrTable(EnumSettingKey.ShellExecuteCommand);
            if(Utility.StringIsNullOrEmpty(cmdterm))
                    throw new Exception("Не найдена команда запуска из настройки " + EnumSettingKey.ShellExecuteCommand.getTitle());
            //2. извлечь из ФайлНастроекОператора или ТаблицаНастроекОператора значение рабочего каталога терминала
            String workDirectory = this.getSettingFromFileOrTable(EnumSettingKey.DefaultWorkingDirectory);
            if(Utility.StringIsNullOrEmpty(workDirectory))
                throw new Exception("Не найден путь к рабочему каталогу Терминала из настройки " + EnumSettingKey.DefaultWorkingDirectory.getTitle());
            //3. вставить аргументы в командную строку приложения
            //TODO: непонятно тут - нужен ли путь Терминала для Процедуры тут, или вообщен не нужно ничего добавлять?
            //пока что ничего не будем добавлять. 
            String cmdline = RegexManager.ConvertApplicationCommandString(p.get_Path(), args);
            
            //4. пока разделить нечем - вызвать PEM.ExecuteApplicationSimple()
            //String cmdline = cmdterm.trim() + " " + cmdQuery; - ничего не будем добавлять в командную строку от Команды.
            this.m_PEM.ExecuteApplicationSimple(cmdline, workDirectory);
        }
        catch (Exception e)
        {
            // вызов исполнения не удался.
            // пока выведем сообщение об исключении в консоль.
            // TODO: вот надо завести в механизме статическую переменную
            // отладки, включаемую через отдельную процедуру/команду, и по ней
            // выводить на экран эти отладочные данные.
            // TODO: надо вывести тут сообщение об исключении в общий лог.
            // если не выводить сообщение об ошибке, то непонятно, почему
            // команда не исполняется.
            // например, когда выключился спящий режим, команда спать просто
            // выводила сообщение я не умею.
            // this.m_OperatorConsole.PrintExceptionMessage(e);
            
            this.PrintExceptionMessageToConsoleAndLog("Ошибка", e);
            
            //TODO: Флаг, что процедура не годится, выдается для исключения из функции 
            //RegexManager.ConvertApplicationCommandString(..), она могла бы возвращать null как значение ошибки. 
            //а то сейчас при любой ошибке Процедура считается непригодной и тихо пропускается. 
            //А функция играет роль фильтра для отсева неподходящих пар регекс-командная строка из Команды.
            result = EnumProcedureResult.WrongArguments;// флаг что процедура не годится
        }
        
        return result;
    }

    /**
     * NT-Исполнить Процедуру без аргументов.
     * 
     * @param userQuery
     *            Путь к Процедуре.
     * @return Функция возвращает код результата исполнения Процедуры.
     */
    private EnumProcedureResult DoSimpleProcedureExecution(UserQuery userQuery)
    {
        EnumProcedureResult result = EnumProcedureResult.Success;
        
        try
        {
            //1. извлечь из userQuery путь к Процедуре.
            String procedurePath = userQuery.getQuery();
            //2. найти Процедуру с таким путем в ECM.
            Procedure p = this.m_ECM.get_ProcedureCollection().getByPath(procedurePath);
            if(p == null)
                throw new Exception(String.format("Процедура \"%s\" не найдена в Коллекции Процедур Оператора.", procedurePath));
            //3. Запустить Процедуру на исполнение - лучше из самого объекта Процедуры?
            // - без аргументов.
            // PEM.invokeProcedure(Procedure, names[], query, engine, args);
            //вот ее придется обернуть в более короткий вызов и подготовку всех ресурсов.
            // 1. Procedure - объект процедуры, не используется пока.
            // 2. String[] names - массив частей пути Процедуры:
            // [0] - library dll title
            // [1] - class title within library
            // [2] - function title within class
            // [3..n] - argument names, not used here 
            // - этот массив формируется в функции RegexManager.ParseAssemblyCodePath(...);
            String[] names = RegexManager.ParseAssemblyCodePath(procedurePath);
            // 3. String query text - чего туда подавать?
            // - тут проблема, если Процедура проверяет текст запроса - запроса же нет.
            // - А) передавать что есть - путь к Процедуре.
            // - Б) передавать текст описания из объекта Процедуры.
            // 4. Engine object ref
            // 5. ArgumentCollection args - формируется в функции RegexManager.ExtractArgumentsFromCommand(query, regex);
            // - если регекс подходит, а аргументов нет, возвращается пустая коллекция аргументов.
            ArgumentCollection args = new ArgumentCollection();
            
            //TODO: вот тут можно предложить запрашивать необходимые Процедуре аргументы - у пользователя, через диалог.
            // Раз он сам такой путь Процедуры ввел в качестве команды, так пусть и аргументы укажет, если они есть.
            // - для этого надо где-то взять список аргументов с именами и описаниями. Где?
            //   - в регексе Команды; либо в Пути Процедуры должны быть указаны имена аргументов. Но толку от их названий мало будет.
            // Но это - потом опробовать фичу, а сейчас - тупо запустить Процедуру без аргументов и не учитывая регекс.
            
            result = this.m_PEM.invokeProcedure(p, names, userQuery, this, args);       
        }
        catch (Exception e)
        {
            this.PrintExceptionMessageToConsoleAndLog("Ошибка", e);
            result = EnumProcedureResult.Error;
        }
        
        //4. Вернуть код результата Процедуры.
        return result;
    }

    /**
     * NT- выполнить стартовую процедуру
     * 
     * @return Вернуть код результата для правильного перехода в вызывающем алгоритме.
     * @throws Exception
     */
    private int CommandStartupProcedure() throws Exception
    {
        // - TODO: С30 Событие начала стартапа.
        // Если не удалось найти пути для процедуры стартапа ни в файле настроек, ни в БД, то ничего не писать про процедуру стартапа.
        // - вывести сообщение о начале процедуры startUp.
        // если в ФайлНастроекОператора флаг ignore_startup = true, то вывести надпись о игнорировании стартапа.
        // и вывести в лог сообщение об игнорировании стартапа.
        // Иначе вывести надпись о начале процедуры стартапа.
        // и вывести в лог сообщение о начале процедуры стартапа.

        // читаем флаг из ФайлНастроекОператора
        Boolean ignoreStartup = this.m_Settings.getValueAsBoolean(EnumSettingKey.IgnoreStartup);
        // Вместо путей получаем объекты настроек, чтобы выводить на консоль информацию о запускаемых командах.
        SettingItem[] settings = null;
        // если флага нет, или он сброшен, то стартап запускать
        if ((ignoreStartup == null) || (ignoreStartup.booleanValue() == false))
        {
            // читаем настройку из ФайлНастроекОператора
            // - если в ФайлНастроекОператор поле cmd_startup существует и не пустое, то:
            // - строка содержимого поля запускается на исполнение как команда либо как путь Процедуры.
            // - Если строка - путь процедуры, то запускается Процедура.
            // Иначе - считать строку командой и передать в МеханизмИсполненияКоманд Оператор.
            settings = this.m_Settings.getItems(EnumSettingKey.CmdStartup);
            if ((settings == null) || (settings.length == 0))
            {
                // - иначе:
                // - выбрать из таблицы БД ТаблицаНастроекОператор
                // записи по ключу cmd_startup - может быть несколько значений с одинаковым ключом.
                // - строка содержимого поля value запускается на исполнение как команда либо как путь Процедуры.
                // - Если строка - путь процедуры, то запускается Процедура.
                // - todo: нужна функция для запуска Процедуры без аргументов по ее пути.
                // Иначе - считать строку командой и передать в МеханизмИсполненияКоманд Оператор.

                // читаем настройку из ТаблицаНастроекОператора. Она теперь в ECM выведена.
                // Вместо путей получаем объекты настроек, чтобы выводить на консоль информацию о запускаемых командах.
                settings = this.m_ECM.get_SettingCollection().getItems(EnumSettingKey.CmdStartup.getTitle());
            }
            // если массив не null, то исполняем команды из массива
            // а тут как выявить не-пустые значения массива?
            if (settings == null)
            {
                // Вывести на консоль и в лог сообщение об отсутствии команд для стартапа.
                this.AddMessageToConsoleAndLog("Исполнение процедуры старта Оператор невозможно: задания не определены.", EnumDialogConsoleColor.Сообщение, EnumLogMsgClass.StartupExecution, EnumLogMsgState.OK);
            }
            else
            {
                // Вывести на консоль и в лог сообщение о начале исполнения команд для стартапа.
                this.AddMessageToConsoleAndLog("Исполнение процедуры старта Оператор начато:", EnumDialogConsoleColor.Сообщение, EnumLogMsgClass.StartupExecution, EnumLogMsgState.OK);

                for (SettingItem ca : settings)
                {
                    // check null or empty
                    if (ca == null)
                        continue;
                    // get query text
                    String caQuery = ca.get_Path().trim();
                    if (caQuery.isEmpty())
                        continue;
                    // Вывести на консоль и в лог название и описание команды финиша. Предварительно вывести в консоль пустую строку как разделитель.
                    String msgcmd = ca.toSingleDescriptionString();
                    this.AddMessageToConsoleAndLog(msgcmd, EnumDialogConsoleColor.Сообщение, EnumLogMsgClass.StartupExecution, EnumLogMsgState.OK);

                    // TODO: тут запустить Процедуру или Запрос на исполнение в обычном порядке.
                    // TODO: результат Процедуры нужно обработать, вдруг он вызовет перезагрузку компьютера посреди исполнения следующего запроса в списке?
                    EnumProcedureResult result = DoCommandExecution(caQuery);
                }
            }
            // - todo: пост-обработка для КодЗавершенияПроцедуры здесь не выполняется?
            // - TODO: С31 Событие завершения стартапа.
            // - вывести сообщение о завершении процедуры startUp.
            this.AddMessageToConsoleAndLog("Исполнение процедуры старта Оператор завершено.", EnumDialogConsoleColor.Сообщение, EnumLogMsgClass.StartupExecution, EnumLogMsgState.OK);

        }
        else
        {
            // вывести на консоль сообщение о игнорировании стартапа.
            // и вывести в лог сообщение об игнорировании стартапа.
            this.AddMessageToConsoleAndLog("Исполнение процедуры старта Оператор запрещено конфигурацией.", EnumDialogConsoleColor.Сообщение, EnumLogMsgClass.StartupExecution, EnumLogMsgState.OK);
        }
        return 0;
    }

    /**
     * NT-Выполнить процедуру завершения Оператор.
     * 
     * @param pr
     *            Код результата после завершения цикла исполнения команд.
     * @return
     * @throws Exception
     */
    private int CommandFinishProcedure(EnumProcedureResult pr) throws Exception
    {
        // - TODO: С35 Событие начала финиша.
        // Если не удалось найти пути для процедуры финиша ни в файле настроек, ни в БД, то ничего не писать про процедуру финиша.
        // - вывести сообщение о начале процедуры финиша.
        // если в ФайлНастроекОператора флаг ignore_startup = true, то вывести надпись о игнорировании финиша.
        // и вывести в лог сообщение об игнорировании финиша.
        // Иначе вывести надпись о начале процедуры финиша.
        // и вывести в лог сообщение о начале процедуры финиша.

        // читаем флаг из ФайлНастроекОператора
        Boolean ignoreStartup = this.m_Settings.getValueAsBoolean(EnumSettingKey.IgnoreStartup);
        // Вместо путей получаем объекты настроек, чтобы выводить на консоль информацию о запускаемых командах.
        SettingItem[] settings = null;
        // если флага нет, или он сброшен, то стартап запускать
        if ((ignoreStartup == null) || (ignoreStartup.booleanValue() == false))
        {
            // читаем настройку из ФайлНастроекОператора
            // - если в ФайлНастроекОператор поле cmd_finish существует и не пустое, то:
            // - строка содержимого поля запускается на исполнение как команда либо как путь Процедуры.
            // - Если строка - путь процедуры, то запускается Процедура.
            // Иначе - считать строку командой и передать в МеханизмИсполненияКоманд Оператор.
            settings = this.m_Settings.getItems(EnumSettingKey.CmdFinish);
            if ((settings == null) || (settings.length == 0))
            {
                // - иначе:
                // - выбрать из таблицы БД ТаблицаНастроекОператор
                // записи по ключу cmd_finish - может быть несколько значений с одинаковым ключом.
                // - строка содержимого поля value запускается на исполнение как команда либо как путь Процедуры.
                // - Если строка - путь процедуры, то запускается Процедура.
                // - todo: нужна функция для запуска Процедуры без аргументов по ее пути.
                // Иначе - считать строку командой и передать в МеханизмИсполненияКоманд Оператор.

                // читаем настройку из ТаблицаНастроекОператора. Она выведена в ECM.
                // Вместо путей получаем объекты настроек, чтобы выводить на консоль информацию о запускаемых командах.
                settings = this.m_ECM.get_SettingCollection().getItems(EnumSettingKey.CmdFinish.getTitle());
            }
            // если массив не null, то исполняем команды из массива
            // а тут как выявить не-пустые значения массива?
            if (settings == null)
            {
                // Вывести на консоль и в лог сообщение об отсутствии команд для финиша.
                this.AddMessageToConsoleAndLog("Исполнение процедуры завершения Оператор невозможно: задания не определены.", EnumDialogConsoleColor.Сообщение, EnumLogMsgClass.FinishExecution, EnumLogMsgState.OK);
            }
            else
            {
                // Вывести на консоль и в лог сообщение о начале исполнения команд для финиша.
                this.AddMessageToConsoleAndLog("Исполнение процедуры завершения Оператор начато:", EnumDialogConsoleColor.Сообщение, EnumLogMsgClass.FinishExecution, EnumLogMsgState.OK);

                for (SettingItem ca : settings)
                {
                    // check null or empty
                    if (ca == null)
                        continue;
                    // get query text
                    String caQuery = ca.get_Path().trim();
                    if (caQuery.isEmpty())
                        continue;
                    // Вывести на консоль и в лог название и описание команды финиша. Предварительно вывести в консоль пустую строку как разделитель.
                    String msgcmd = ca.toSingleDescriptionString();
                    this.AddMessageToConsoleAndLog(msgcmd, EnumDialogConsoleColor.Сообщение, EnumLogMsgClass.FinishExecution, EnumLogMsgState.OK);

                    // TODO: тут запустить Процедуру или Запрос на исполнение в обычном порядке.
                    // TODO: результат Процедуры нужно обработать, вдруг он вызовет перезагрузку компьютера посреди исполнения следующего запроса в списке?
                    EnumProcedureResult result = DoCommandExecution(caQuery);
                }
            }
            // - todo: пост-обработка для КодЗавершенияПроцедуры здесь не выполняется?
            // - TODO: С36 Событие завершения финиша.
            // - вывести сообщение о завершении процедуры finish.
            this.AddMessageToConsoleAndLog("Исполнение процедуры завершения Оператор завершено.", EnumDialogConsoleColor.Сообщение, EnumLogMsgClass.FinishExecution, EnumLogMsgState.OK);

        }
        else
        {
            // вывести на консоль и в лог сообщение о игнорировании финиша.
            this.AddMessageToConsoleAndLog("Исполнение процедуры старта Оператор запрещено конфигурацией.", EnumDialogConsoleColor.Сообщение, EnumLogMsgClass.FinishExecution, EnumLogMsgState.OK);
        }
        // return result code
        return 0;
    }

//    /// <summary>
//    /// NR-Обработчик события "Поступила новая команда"
//    /// </summary>
//    /// <param name="query">Текст запроса команды</param>
//    /// <returns></returns>
//    private EnumProcedureResult EventCommandArrived(String query) throws Exception
//    {
//        // сейчас тупо исполним весь запрос целиком
//        // result = DoQuery(query);
//
//        EnumProcedureResult result = Lexicon.BCSA.ProcessQuery(this, query);
//
//        return result;
//    }

    /// <summary>
    /// NR-вывести сообщение-подтверждение результата процедуры
    /// Играть звуковой сигнал, если результат - ошибка.
    /// Подтверждение не выводится, если завершение процедуры - успешное.
    /// </summary>
    /// <param name="result">Результат исполнения процедуры</param>
    private void describeProcedureResult(EnumProcedureResult result)
    {
        String msg = null;
        boolean ErrorAndBeep = false;
        switch (result)
        {
            case CancelledByUser:
                msg = "Процедура прервана пользователем.";
                break;
            case Error:
            case Unknown:
                msg = "Ошибка при исполнении процедуры.";
                ErrorAndBeep = true;
                break;
            case Exit:
                msg = "Завершение программы...";
                break;
            case ExitAndHybernate:
            case ExitAndSleep:
                msg = "Переход в спящий режим...";
                break;
            case ExitAndLogoff:
                msg = "Завершение сеанса пользователя...";
                break;
            case ExitAndReload:
                msg = "Перезагрузка компьютера...";
                break;
            case ExitAndShutdown:
                msg = "Выключение компьютера...";
                break;
            case WrongArguments:
                msg = "Ошибка: неправильные аргументы.";
                ErrorAndBeep = true;
                break;
            case Success:
                msg = "Процедура выполнена успешно.";
            default:
                break;
        }
        // выбрать цвет сообщения о результате процедуры
        // подать звуковой сигнал при ошибке
        EnumDialogConsoleColor color;
        if (ErrorAndBeep == true)
        {
            this.m_OperatorConsole.Beep();
            color = EnumDialogConsoleColor.Предупреждение;
        }
        else color = EnumDialogConsoleColor.Сообщение;
        // выдать сообщение о результате процедуры
        // если курсор не в начале строки, начать сообщение с новой строки.
        this.m_OperatorConsole.SureConsoleCursorStart();
        this.m_OperatorConsole.PrintTextLine(msg, color);

        return;
    }

//    /// <summary>
//    /// NR-обрабатываем запрос пользователя.
//    /// Возвращаем false для завершения работы приложения
//    /// </summary>
//    /// <param name="cmdline">Текст запроса</param>
//    public EnumProcedureResult DoQuery(String query) throws Exception
//    {
//        // найти подходящую процедуру для запроса
//        // перебором всех процедур.
//        EnumProcedureResult result;
//        String regex = null;
//
//        // 22052020 - фича: если запрос не русскоязычный, то передать его в
//        // терминал. Иначе - исполнять.
//        if (BCSA.IsNotRussianFirst(query))
//            return ExecuteWithTerminal(query);
//        // для каждой процедуры из списка процедур из кеша элементов:
//        for (Procedure p : this.m_ECM.get_ProcedureCollection().get_Procedures()) // this.m_db.Procedures.Procedures)
//        {
//            // собрать нормальный регекс для процедуры
//            // TODO: optimization - можно же это сделать после загрузки регекса
//            // из БД как часть процесса распаковки данных, записав в объект
//            // Процедуры как служебное поле.
//            // а не при каждом исполнении команды от пользователя.
//            regex = MakeNormalRegex(p);
//            // выполнить регекс и определить, является ли процедура пригодной
//            // для исполнения
//            // bool res = RegexManager.IsMatchQuery(rx, cmdline);
//            // if (res == true)
//            ArgumentCollection args = RegexManager.ExtractArgumentsFromCommand(query, regex);
//            if (args != null)
//            {
//                // Тут запускаем процедуру. Она должна теперь проверить свои
//                // аргументы и все условия,
//                // и если они не подходят, то завершиться с флагом
//                // ProcedureResult.WrongArguments.
//                result = Execute(query, regex, p, args);
//                if (result != EnumProcedureResult.WrongArguments)
//                    return result;
//            }
//        }
//        // Тут состояние "Не удалось подобрать процедуру для исполнения запроса"
//        // Вынесем его в функцию-обработчик, чтобы модифицировать обработку
//        // этого события.
//        EventCommandNotExecuted();
//
//        return EnumProcedureResult.Success;
//    }

    /**
     * NT-Обработать событие "Не удалось подобрать процедуру для исполнения запроса"
     */
    private void EventCommandNotExecuted()
    {
        // TODO: добавить эту функцию -ивент в документацию по работе движка.
        // выводим сообщение что для запроса не удалось подобрать процедуру
        this.m_OperatorConsole.PrintTextLine("Я такое не умею", EnumDialogConsoleColor.Сообщение);

        // вообще же тут можно выполнять другую обработку, наверно...

        return;
    }

//    /**
//     * NR-Открыть англоязычный запрос в Терминале Линукс
//     * 
//     * @param query
//     *            Текст запроса.
//     * @return Функция возвращает Код результата исполнения запроса.
//     */
//    private EnumProcedureResult ExecuteWithTerminal(String query)
//    {
//        // TODO: переделать на условия Линукс.
//        EnumProcedureResult result = EnumProcedureResult.Success;
//        try
//        {
//            // путь к терминалу и аргументы получить из настроек Оператора специально для терминала.
//            String app = this.m_Settings.getValue(EnumSettingKey.ForCommandTerminal);
//            // если поле отсутствует или не содержит значения, сообщить об этом и закончить выполнение.
//            if (Utility.StringIsNullOrEmpty(app))
//            {
//                String msg = "Невозможно выполнить запрос \"" + query + "\", поскольку не найден путь к Терминалу в поле ForCommandTerminal в ФайлНастроекОператора ";
//                this.AddMessageToConsoleAndLog(msg, EnumDialogConsoleColor.Предупреждение, EnumLogMsgClass.SubsystemEvent_Settings, EnumLogMsgState.Fail);
//            }
//            else
//            {
//                // сформировать строку аргументов
//                String args = app + " " + query;
//                // исполнить запрос
//                // TODO: тут надо назначить рабочий каталог
//                // - его надо взять из настроек, которые у каждого случая Терминала должны быть свои.
//                // - а пока назначим как каталог пользователя.
//                String workDirectory = FileSystemManager.UserFolderPath;
//                // TODO: надо проработать код вызова Терминала на прототипе!
//                Runtime.getRuntime().exec(args);// простой способ, без указания рабочего каталога. Для уточнения нужны эксперименты на прототипе!
//                // SystemInfoManager.ExecuteApplication(app, query, workDirectory);
//                // PowerManager.ExecuteApplication(app, args); - старый способ для винды
//            }
//        }
//        catch (Exception e)
//        {
//            this.PrintExceptionMessageToConsoleAndLog("Ошибка при исполнении в Терминале", e);
//            result = EnumProcedureResult.Error;// флаг что процедура не годится
//        }
//
//        return result;
//    }

    /**
     * NT-Собрать нормальный регекс для процедуры
     * 
     * @param p
     *            Объект Процедуры
     * @return Функция возвращает Нормальный регекс для указанной Процедуры.
     * @throws Exception
     *             Procedure has invalid regex string.
     */
    private String MakeNormalRegex(Procedure p) throws Exception
    {
        String result = null;
        String procedureRegex = p.get_Regex();
        // получить тип регекса
        EnumRegexType rt = RegexManager.determineRegexType(procedureRegex);
        // конвертировать регекс в пригодный для исполнения
        if (rt == EnumRegexType.NormalRegex)
        {
            result = Utility.StringCopy(procedureRegex);
        }
        else if (rt == EnumRegexType.SimpleString)
        {
            // Тут Простой регекс превращается в Нормальный регекс, русские
            // названия аргументов заменяются на arg_#.
            result = RegexManager.ConvertSimpleToRegex2(procedureRegex);
        }
        else throw new Exception(String.format("Procedure has invalid regex string: %s in %s", procedureRegex, p.get_Title()));

        return result;
    }

//    /// <summary>
//    /// NR-Должна вернуть облом при неподходящих параметрах, успех при
//    /// исполнении, выход если требуется завершение работы приложения или
//    /// компьютера
//    /// </summary>
//    /// <param name="command">Текст команды пользователя</param>
//    /// <param name="regex">Регулярное выражение, готовое для работы</param>
//    /// <param name="p">Объект процедуры</param>
//    /// <param name="args">Коллекция аргументов</param>
//    /// <returns></returns>
//    private EnumProcedureResult Execute(
//            String command,
//            String regex,
//            Procedure p,
//            ArgumentCollection args) throws Exception
//    {
//        // и еще нужно этим аргументам сопоставить типы мест хотя бы
//        TryAssignPlaces(args);
//
//        // надо определить, путь исполнения это путь к процедуре или к
//        // приложению.
//        // TODO:оптимизация: сделать это при загрузке Процедуры из БД и
//        // сохранить в служебном поле Процедуры
//        boolean isAssemblyCodePath = RegexManager.IsAssemblyCodePath(p.get_Path());
//        if (isAssemblyCodePath == false)
//        {
//            // если к приложению, его надо запустить и вернуть стандартное
//            // значение для продолжения работы.
//            return RunShellExecute(p, args);
//        }
//        else
//        {
//            // если к процедуре, надо приготовить аргументы, найти сборку,
//            // вызвать функцию, передать ей аргументы и вернуть результат.
//            return RunLocalAssembly(command, p, args);
//        }
//
//    }

//    /**
//     * NT-Запустить функцию из локальной сборки
//     * 
//     * @param command
//     *            Команда пользователя
//     * @param p
//     *            Объект процедуры
//     * @param args
//     *            Коллекция аргументов
//     * @return Возвращается результат выполнения процедуры.
//     */
//    private EnumProcedureResult RunLocalAssembly(
//            String command,
//            Procedure p,
//            ArgumentCollection args)
//    {
//        EnumProcedureResult result = EnumProcedureResult.Success;
//        try
//        {
//            // получить имена частей пути. в порядке: сборка, класс, функция,
//            // аргументы по порядку следования если они есть
//            String[] names = RegexManager.ParseAssemblyCodePath(p.get_Path());
//            // тут аргументы уже должны быть заполнены значениями и типами
//            // и местами и готовы к выполнению.
//            // result = p.invokeProcedure(command, names, this, args);
//            result = this.m_PEM.invokeProcedure(p, names, command, this, args);
//            // Это выбрасывает исключения процесса запуска и исполнения Процедуры.
//        }
//        catch (Exception e)
//        {
//            // вызов исполнения не удался.
//            // пока выведем сообщение об исключении в консоль.
//            // TODO: вот надо завести в механизме статическую переменную
//            // отладки, включаемую через отдельную процедуру, и по ней выводить
//            // на экран эти отладочные данные.
//            // TODO: надо вывести тут сообщение об исключении в общий лог.
//            // если не выводить сообщение об ошибке, то непонятно, почему
//            // команда не исполняется.
//            // например, когда выключился спящий режим, команда спать просто
//            // выводила сообщение я не умею.
//
//            PrintExceptionToConsole(e);
//            // add exception to log
//            this.m_logman.AddExceptionMessage(e);
//
//            // вернуть флаг ошибки
//            // TODO: Определить, что тут должна возвращать текущая функция, если во время исполнения Процедуры произошла ошибка.
//            // Ошибка - это ошибка, а не несоответствие Запроса и Процедуры. А тут - наобум назначено возвращаемое значение.
//            result = EnumProcedureResult.WrongArguments;
//        }
//        // возвращаем то что вернет процедура
//        return result;
//    }

//    /// <summary>
//    /// NR-Запустить команду через механизм ShellExecute
//    /// </summary>
//    /// <param name="p">Объект процедуры</param>
//    /// <param name="args">Коллекция аргументов</param>
//    /// <returns>Возвращается результат выполнения процедуры</returns>
//    private EnumProcedureResult RunShellExecute(
//            Procedure p,
//            ArgumentCollection args)
//    {
//        // типы мест определять здесь не имеет смысла - они все равно не
//        // учитываются в запускаемом приложении
//        EnumProcedureResult result = EnumProcedureResult.Success;
//        try
//        {
//            // вставить аргументы в командную строку приложения
//            String cmdline = RegexManager.ConvertApplicationCommandString(p.get_Path(), args);
//            // запустить приложение
//            PowerManager.ExecuteApplication(cmdline);
//        }
//        catch (Exception e)
//        {
//            // вызов исполнения не удался.
//            // пока выведем сообщение об исключении в консоль.
//            // TODO: вот надо завести в механизме статическую переменную
//            // отладки, включаемую через отдельную процедуру/команду, и по ней
//            // выводить на экран эти отладочные данные.
//            // TODO: надо вывести тут сообщение об исключении в общий лог.
//            // если не выводить сообщение об ошибке, то непонятно, почему
//            // команда не исполняется.
//            // например, когда выключился спящий режим, команда спать просто
//            // выводила сообщение я не умею.
//            // this.m_OperatorConsole.PrintExceptionMessage(e);
//            PrintExceptionToConsole(e);
//            // add exception to log
//            this.m_logman.AddExceptionMessage(e);
//
//            result = EnumProcedureResult.WrongArguments;// флаг что процедура не годится
//        }
//        // вернуть результат
//        return result;
//    }

    /**
     * NT-Сопоставить данные аргументов и места из коллекции мест, насколько это возможно.
     * 
     * @param args
     * @throws Exception
     */
    private void TryAssignPlaces(ArgumentCollection args) throws Exception
    {

        // тут надо если у аргумента название есть в словаре мест, то скопировать в аргумент значение этого места
        // пока без проверки типов и всего такого, так как это должна бы делать процедура.
        for (FuncArgument f : args.get_Arguments())
        {
            String name = f.get_ArgumentValue();
            if (this.m_ECM.get_PlaceCollection().ContainsPlace(name)) 
            {
                // извлечем место
                Place p = this.m_ECM.get_PlaceCollection().GetPlace(name);
                // копируем свойства места в аргумент
                f.ПодставитьМесто(p);
            }
        }

        return;
    }


}

