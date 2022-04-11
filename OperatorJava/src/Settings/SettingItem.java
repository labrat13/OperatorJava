/**
 * @author Селяков Павел
 *         Created: Feb 27, 2022 7:01:06 PM
 *         State: Mar 21, 2022 12:37:20 AM - Ported, Готов к отладке.
 */
package Settings;

import OperatorEngine.Utility;

/**
 * NT-Класс представляет элемент данных в файле настроек приложения.
 * Элемент данных содержит название-ключ, текстовое значение и текстовое описание.
 * Текстовое описание должно выводиться на одной строке комментария, если оно однострочное, или на нескольких строках, если оно многострочное.
 * На следующей строке должно выводиться пара ключ-значение и затем следующая строка должна быть пустой
 * 
 * @author Селяков Павел
 *
 */
public class SettingItem
{

    /**
     * Значение неправильного TableID, если итем не из ТаблицаНастроекОператора.
     */
    public static final int Invalid_TableID = -1;

    /**
     * Table ID for item from database.
     */
    protected int           m_Id;

    /**
     * Setting title as dictionary key.
     */
    protected String        m_Title;

    /**
     * Settings description multiline text.
     */
    protected String        m_Description;

    /**
     * Settings value as String.
     */
    protected String        m_Value;

    /**
     * NT-Default constructor
     */
    public SettingItem()
    {
        this.m_Id = SettingItem.Invalid_TableID;
        this.m_Description = null;
        this.m_Title = null;
        this.m_Value = null;
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
        this.m_Id = SettingItem.Invalid_TableID;
        this.m_Title = key.getTitle();
        this.m_Description = key.getDescription();
        this.m_Value = value;

        return;
    }

    /**
     * NT-Parameter constructor
     * 
     * @param id
     *            item table id or 0 if not.
     * @param title
     *            item title
     * @param value
     *            item value
     * @param descr
     *            item description text
     */
    public SettingItem(int id, String title, String value, String descr)
    {
        this.m_Id = id;
        this.m_Value = value;
        this.m_Description = descr;
        this.m_Title = title;

        return;
    }

    /**
     * NT- Table ID for item from database.
     * 
     * @return the id
     */
    public int getTableId()
    {
        return this.m_Id;
    }

    /**
     * NT-Table ID for item from database.
     * 
     * @param id
     *            the id to set
     */
    public void setTableId(int id)
    {
        this.m_Id = id;
    }

    /**
     * NT- check current Item has InvalidTableID value.
     * 
     * @return Returns true if Item has not tableID value.
     */
    public boolean isInvalidID()
    {
        return (this.m_Id == SettingItem.Invalid_TableID);
    }

    /**
     * NT-Setting title as dictionary key
     * 
     * @return the title
     */
    public String getTitle()
    {
        return this.m_Title;
    }

    /**
     * NT-Setting title as dictionary key
     * 
     * @param title
     *            the title to set
     */
    public void setTitle(String title)
    {
        this.m_Title = title;
    }

    /**
     * NT-Settings description multiline text
     * 
     * @return the description
     */
    public String getDescription()
    {
        return this.m_Description;
    }

    /**
     * NT-Settings description multiline text
     * 
     * @param description
     *            the description to set
     */
    public void setDescription(String description)
    {
        this.m_Description = description;
    }

    /**
     * NT-Settings value as String
     * 
     * @return the value
     */
    public String getValue()
    {
        return this.m_Value;
    }

    /**
     * NT-Settings value as String
     * 
     * @param value
     *            the value to set
     */
    public void setValue(String value)
    {
        this.m_Value = value;
    }

    /**
     * NT-Return string for debug
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        String t = Utility.GetStringTextNull(this.m_Title);
        String v = Utility.GetStringTextNull(this.m_Value);
        String d = Utility.GetStringTextNull(this.m_Description);
        return String.format("%i: \"%s\" = \"%s\" : \"%s\"", this.m_Id, t, v, d);
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
            result = Integer.valueOf(this.m_Value);
        }
        catch (Exception e)
        {
            result = null;
        }

        return result;
    }

    /**
     * NT- Get value by key
     * 
     * @return Returns Value as Boolean; returns null if Value has invalid format.
     */
    public Boolean getValueAsBoolean()
    {
        Boolean result = null;

        try
        {
            result = Boolean.valueOf(this.m_Value);
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
        this.m_Value = value.toString();
    }

    /**
     * NT-Settings value as Integer
     * 
     * @param value
     *            the value to set
     */
    public void setValue(Integer value)
    {
        this.m_Value = value.toString();
    }

}
