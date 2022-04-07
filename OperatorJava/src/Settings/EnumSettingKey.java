/**
 * @author Селяков Павел
 *         Created: Mar 2, 2022 12:31:56 PM
 *         State: Mar 21, 2022 12:37:20 AM - Ported, Готов к отладке.
 */
package Settings;

/**
 * Енум ключей словаря настроек. Содержит члены-ключи с названием и описанием.
 * 
 * @author Селяков Павел
 *
 */
public enum EnumSettingKey
{
    
    // *** Settings version ***
    
    /**
     * Default (0) or Unknown
     */
    Default("Default", "Default or Unknown."),
    /**
     * Engine version string
     */
    EngineVersion("EngineVersion", "Engine version string."),
    /**
     * This settings file version string
     */
    SettingsFileVersion("SettingsFileVersion", "This settings file version string"),
    
    // *** Terminal command line templates ***
    
    /**
     * Командная строка для запуска пустого Терминала
     */
    LoneTerminal("LoneTerminal", "Командная строка для запуска пустого Терминала."),
    /**
     * Командная строка для запуска англоязычной команды в Терминале.
     */
    ForCommandTerminal("ForCommandTerminal", "Командная строка для запуска англоязычной команды в Терминале."),
    /**
     * Командная строка для запуска Процедуры в Терминале.
     */
    ForProcedureTerminal("ForProcedureTerminal", "Командная строка для запуска Процедуры в Терминале."),
    
    // *** Startup and finish settings ***
    
    /**
     * Текст команды или путь Процедуры, исполняемой при запуске Оператора.
     */
    CmdStartup("CmdStartup", "Текст команды или путь Процедуры, исполняемой при запуске Оператора."),
    /**
     * Текст команды или путь Процедуры, исполняемой при завершении Оператора.
     */
    CmdFinish("CmdFinish", "Текст команды или путь Процедуры, исполняемой при завершении Оператора."),
    /**
     * Логическое значение true или false, игнорировать ли настройки файла настроек и БД при загрузке и завершении Оператора.
     */
    IgnoreStartup("IgnoreStartup", "Логическое значение, игнорировать ли настройки файла настроек и БД при загрузке и завершении Оператора."),
    
    // *** Internal command text's ***
    
    /**
     * Перечиcление через запятую кодовых слов для встроенной команды Выход из Оператора.
     */
    ExitAppCommands("ExitAppCommands", "Перечисление через запятую кодовых слов для встроенной команды Выход из Оператора"),
    
    // *** Procedure result code processing settings ***
    
    /**
     * Текст команды или путь Процедуры, исполняемой при EnumProcedureResult.ExitAndSleep..
     */
    CmdSleep("CmdSleep", "Текст команды или путь Процедуры, исполняемой при EnumProcedureResult.ExitAndSleep."),

    /**
     * Текст команды или путь Процедуры, исполняемой при EnumProcedureResult.ExitAndReload.
     */
    CmdReload("CmdReload", "Текст команды или путь Процедуры, исполняемой при EnumProcedureResult.ExitAndReload"),

    /**
     * Текст команды или путь Процедуры, исполняемой при EnumProcedureResult.ExitAndShutdown.
     */
    CmdShutdown("CmdShutdown", "Текст команды или путь Процедуры, исполняемой при EnumProcedureResult.ExitAndShutdown"),

    /**
     * Текст команды или путь Процедуры, исполняемой при EnumProcedureResult.ExitAndLogoff.
     */
    CmdLogoff("CmdLogoff", "Текст команды или путь Процедуры, исполняемой при EnumProcedureResult.ExitAndLogoff"),

    /**
     * Текст команды или путь Процедуры, исполняемой при EnumProcedureResult.ExitAndHybernate.
     */
    CmdHybernate("CmdHybernate", "Текст команды или путь Процедуры, исполняемой при EnumProcedureResult.ExitAndHybernate");


    
    // *** Enum members ***
    
    /**
     * Key title string
     */
    private String m_Title;

    /**
     * Key description string
     */
    private String m_Description;

    /**
     * Get key title
     * 
     * @return Returns key title
     */
    public String getTitle()
    {
        return this.m_Title;
    }

    /**
     * Get key description multiline string
     * 
     * @return Returns key description
     */
    public String getDescription()
    {
        return this.m_Description;
    }


    /**
     * Constructor
     * 
     * @param title
     *            New key title
     * @param description
     *            New key description multiline text
     */
    private EnumSettingKey(String title, String description)
    {
        this.m_Title = title;
        this.m_Description = description;
    }
}
