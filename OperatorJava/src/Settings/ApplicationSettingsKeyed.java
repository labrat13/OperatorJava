/**
 * @author Селяков Павел
 *         Created: Mar 2, 2022 12:32:43 PM
 *         State: Mar 21, 2022 12:37:20 AM - Ported, Готов к отладке.
 */
package Settings;

import OperatorEngine.Engine;

/**
 * NT-Класс настроек приложения, использующий EnumSettingKey в качестве ключей
 * настроек.
 * 
 * @author Селяков Павел
 *
 */
public class ApplicationSettingsKeyed extends ApplicationSettingsBase
{
    // Добавление настроек:
    // Новые настройки добавлять в EnumSettingKey как название и описание,
    // и потом сюда в функцию Reset() вместе с значениями по умолчанию.

    /**
     * Backreference to Engine object - for logging
     */
    protected Engine m_Engine;

    /**
     * Default constructor
     */
    public ApplicationSettingsKeyed(Engine engine)
    {
        this.m_Engine = engine;
    }

    // Load() and Store() functions come from base class

    /**
     * NR-Reset settings to default values
     */
    public void Reset()
    {
        super.Reset();
        // added in base class: this.addItem("EngineVersion",
        // Engine.EngineVersionString, "Engine version string");

        // TODO: Добавить сюда все настройки из енума
        // TODO: установить правильные значения настроек по умолчанию
        this.addItem(EnumSettingKey.LoneTerminal, "exo-open --launch TerminalEmulator");
        this.addItem(EnumSettingKey.ForCommandTerminal, "exo-open --launch TerminalEmulator");
        this.addItem(EnumSettingKey.ForProcedureTerminal, "exo-open --launch TerminalEmulator");
        //тексты встроенных команд Оператор 
        this.addItem(EnumSettingKey.ExitAppCommands, "выход, выйти, закрыть, quit, close, exit");
        //Команды или Процедуры стартапа и финиша Оператор.
        this.addItem(EnumSettingKey.CmdStartup, "");        
        this.addItem(EnumSettingKey.CmdFinish, "");
        this.addItem(EnumSettingKey.IgnoreStartup, "false");
        //Команды или Процедуры результата исполнения Процедур.
        this.addItem(EnumSettingKey.CmdLogoff, "");
        this.addItem(EnumSettingKey.CmdReload, "");
        this.addItem(EnumSettingKey.CmdShutdown, "");
        this.addItem(EnumSettingKey.CmdSleep, "");
        this.addItem(EnumSettingKey.CmdHybernate, "");

        
        // TODO: определить, нужно ли тут сбрасывать флаг modified?
        return;
    }

    /**
     * NT-Check setting is present
     * 
     * @param key
     *            Setting key
     * @return Returns true if setting present in collection, false otherwise.
     */
    public boolean hasSetting(EnumSettingKey key)
    {
        return super.hasSetting(key.getTitle());
    }

    /**
     * NT-Get settings item by title
     * 
     * @param key
     *            Setting item key
     * @return Returns SettingsItem; returns null if title not exists in
     *         collection.
     */
    public SettingItem getItem(EnumSettingKey key)
    {
        return super.getItem(key.getTitle());
    }

    /**
     * NT-Add new or replace existing settings item in collection.
     * 
     * @param key
     *            Setting item key
     * @param item
     *            Settings item object.
     */
    public void addItem(EnumSettingKey key, SettingItem item)
    {
        super.addItem(key.getTitle(), item);
    }

    /**
     * NT-Add new or replace existing settings item in collection.
     * 
     * @param key
     *            Setting item key.
     * @param value
     *            Setting item value as String.
     */
    public void addItem(EnumSettingKey key, String value)
    {
        super.addItem(key.getTitle(), value, key.getDescription());
    }

    /**
     * NT-Add new or replace existing settings item in collection.
     * 
     * @param key
     *            Setting item key.
     * @param value
     *            Setting item value as Integer.
     */
    public void addItem(EnumSettingKey key, Integer value)
    {
        super.addItem(key.getTitle(), value, key.getDescription());
    }

    /**
     * NT-Add new or replace existing settings item in collection.
     * 
     * @param key
     *            Setting item key.
     * @param value
     *            Setting item value as Boolean.
     */
    public void addItem(EnumSettingKey key, Boolean value)
    {
        super.addItem(key.getTitle(), value, key.getDescription());
    }

    /**
     * NT-Remove setting item from collection
     * 
     * @param key
     *            Setting item key
     */
    public void removeItem(EnumSettingKey key)
    {
        super.removeItem(key.getTitle());
    }

    /**
     * NT- Get value by title
     * 
     * @param key
     *            Setting key
     * @return Returns Value as String; returns null if title not exists in
     *         collection.
     */
    public String getValue(EnumSettingKey key)
    {
        return super.getValue(key.getTitle());
    }

    /**
     * NT- Get value by title
     * 
     * @param key
     *            Setting key
     * @return Returns Value as Integer; returns null if title not exists in
     *         collection or has invalid format.
     */
    public Integer getValueAsInteger(EnumSettingKey key)
    {
        return super.getValueAsInteger(key.getTitle());
    }

    /**
     * NT- Get value by title
     * 
     * @param key
     *            Setting key
     * @return Returns Value as Boolean; returns null if title not exists in
     *         collection or has invalid format.
     */
    public Boolean getValueAsBoolean(EnumSettingKey key)
    {
        return super.getValueAsBoolean(key.getTitle());

    }

    // -------------------------------------------------
    /**
     * NT-Replace value by key
     * 
     * @param key
     *            Key
     * @param value
     *            Value string
     */
    public void setValue(EnumSettingKey key, String value)
    {
        super.setValue(key.getTitle(), value, key.getDescription());
    }

    /**
     * NT-Replace value by title
     * 
     * @param key
     *            Key
     * @param value
     *            Value string
     */
    public void setValue(EnumSettingKey key, Integer value)
    {
        // add or replace value by title
        super.setValue(key.getTitle(), value, key.getDescription());

        return;
    }

    /**
     * NT-Set value by title
     * 
     * @param key
     *            Key
     * @param value
     *            Value string
     */
    public void setValue(EnumSettingKey key, Boolean value)
    {
        super.setValue(key.getTitle(), value, key.getDescription());
    }

}
