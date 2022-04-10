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
    
    // *** Массив ключей настроек ***
    /**
     * Статический массив имен ключей элементов енума - для оптимизации доступа к ним.
     * Если = null, то надо вызвать getKeyArray() для создания и заполнения массива.
     */
    protected static String[] KeysArray = null;
    
/**
 * NT-Get array of used keynames.
 * @return Function returns array of used keyname strings.
 */
    public static String[] getKeyArray()
    {
        //Если массив не сгенерирован ранее, создать и заполнить его.
        if(KeysArray == null)
        {
            EnumSettingKey[] members = EnumSettingKey.class.getEnumConstants();
            int len = members.length;
            String[] result = new String[len];
            
            for(int i = 0; i < len; i++)
                result[i] = members[i].getTitle();
            //и вписать созданный массив в статическую переменную класса.
            KeysArray = result;
        }
        //вернуть массив строк ключей енума.
        return KeysArray;
    }

    /**
     * NT-Check keyname is in enum keynames collection. Ignore letter case. 
     * @param keyname Keyname string.
     * @return Returns true if specified string already used as some keyname here.
     */
    public static boolean IsKeynameExists(String keyname)
    {
        String[] keys = EnumSettingKey.getKeyArray();
        return OperatorEngine.Utility.arrayContainsStringOrdinal(keys, keyname, true);
    }
    
    // *** Конструктор членов енума ***
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
