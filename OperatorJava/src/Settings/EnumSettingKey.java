/**
 * @author Селяков Павел
 *         Created: Mar 2, 2022 12:31:56 PM
 *         State: Mar 2, 2022 12:31:56 PM - initial
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
    /**
     * Default (0) or Unknown
     */
    Default("Default", "Default or Unknown."),
    /**
     * Engine version string
     */
    EngineVersion("EngineVersion", "Engine version string."),
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
    ForProcedureTerminal("ForProcedureTerminal", "Командная строка для запуска Процедуры в Терминале.");

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
    // /**
    // * Set object value
    // *
    // * @param val
    // * New object value
    // */
    // public void setValue(String val)
    // {
    // this.m_Value = val;
    // }

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
