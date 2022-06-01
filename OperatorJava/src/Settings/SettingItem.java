/**
 * @author Селяков Павел
 *         Created: Feb 27, 2022 7:01:06 PM
 *         State: Mar 21, 2022 12:37:20 AM - Ported, Готов к отладке.
 */
package Settings;

/**
 * NT-Класс представляет элемент данных в файле настроек приложения.
 * Элемент данных содержит название-ключ, текстовое значение и текстовое описание.
 * Текстовое описание должно выводиться на одной строке комментария, если оно однострочное, или на нескольких строках, если оно многострочное.
 * На следующей строке должно выводиться пара ключ-значение и затем следующая строка должна быть пустой
 * 
 * @author Селяков Павел
 *
 */
public class SettingItem extends OperatorEngine.Item
{

    // DONE: убедиться, что при извлечении из файла настроек итемы получают источник = ФайлНастроек
    // DONE: убедиться, что при извлечении из БД итемы получают источник = Database

    /**
     * NT-Default constructor
     */
    public SettingItem()
    {
        super();
    }
    
    /**
     * NT-Конструктор копирования.
     * @param p Копируемый объект.
     */
    public SettingItem(SettingItem p)
    {
        this.m_descr = OperatorEngine.Utility.StringCopy(p.m_descr);
        this.m_namespace = OperatorEngine.Utility.StringCopy(p.m_namespace);
        this.m_path = OperatorEngine.Utility.StringCopy(p.m_path);
        this.m_storage = OperatorEngine.Utility.StringCopy(p.m_storage);
        this.m_title = OperatorEngine.Utility.StringCopy(p.m_title);
        this.m_tableid = p.m_tableid;
    }
    
    /**
     * NT - Constructor from EnumSettingKey.
     * 
     * @param key
     *            EnumSettingKey member.
     * @param value
     *            Setting value as string.
     */
    public SettingItem(EnumSettingKey key, String value)
    {
        this.m_tableid = 0;
        this.m_title = key.getTitle();
        this.m_descr = key.getDescription();
        this.m_path = value;
        this.m_namespace = key.getNamespace();

        return;
    }

    /**
     * NT-Parameter constructor - not for Database Item.
     * 
     * @param group
     *            Setting item namespace as group.
     * @param title
     *            item title
     * @param value
     *            item value
     * @param descr
     *            item description text
     */
    public SettingItem(String group, String title, String value, String descr)
    {
        this.m_tableid = 0;
        this.m_path = value;
        this.m_descr = descr;
        this.m_title = title;
        this.m_namespace = group;

        return;
    }

    /**
     * NT-Parameter constructor - for Database Item.
     * 
     * @param id
     *            item table id or 0 if not.
     * @param group
     *            Setting item namespace as group.
     * @param title
     *            item title
     * @param value
     *            item value
     * @param descr
     *            item description text
     * @param storage
     *            item storage keyword
     */
    public SettingItem(int id,
            String group,
            String title,
            String value,
            String descr,
            String storage)
    {
        this.m_tableid = id;
        this.m_namespace = group;
        this.m_path = value;
        this.m_descr = descr;
        this.m_title = title;
        this.m_storage = storage;

        return;
    }

    /**
     * NT-Return string for debug
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return super.getSingleLineProperties();
    }

    /**
     * NT-получить однострочное описание Настройки.
     * 
     * @return Функция возвращает однострочное описание Настройки в формате "Команда "Значение" из "Хранилище"."Название": Описание."
     */
    public String toSingleDescriptionString()
    {
        return String.format("Команда \"%s\" из \"%s\".\"%s\": %s.", this.m_path.trim(), this.m_storage.trim(), this.m_title.trim(), this.m_descr.trim());
    }

    /**
     * NT- Get value.
     * 
     * @return Returns Value as String.
     */
    public String getValueAsString()
    {
        return this.m_path;
    }

    /**
     * NT- Set value.
     * 
     * @param value
     *            Value as String.
     */
    public void setValue(String value)
    {
        this.m_path = value;
    }

    /**
     * NT- Get value
     * 
     * @return Returns Value as Integer; returns null if Value has invalid format.
     */
    public Integer getValueAsInteger()
    {
        Integer result = null;

        try
        {
            result = Integer.valueOf(this.m_path);
        }
        catch (Exception e)
        {
            result = null;
        }

        return result;
    }

    /**
     * NT- Get value
     * 
     * @return Returns Value as Boolean; returns null if Value has invalid format.
     */
    public Boolean getValueAsBoolean()
    {
        Boolean result = null;

        try
        {
            result = Boolean.valueOf(this.m_path);
        }
        catch (Exception e)
        {
            result = null;
        }

        return result;
    }

    /**
     * NT-Settings value as Boolean
     * 
     * @param value
     *            the value to set
     */
    public void setValue(Boolean value)
    {
        this.m_path = value.toString();
    }

    /**
     * NT-Settings value as Integer
     * 
     * @param value
     *            the value to set
     */
    public void setValue(Integer value)
    {
        this.m_path = value.toString();
    }

}
