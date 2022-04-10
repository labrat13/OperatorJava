/**
 * @author Селяков Павел
 *         Created: Feb 28, 2022 11:44:22 AM
 *         State: Mar 21, 2022 12:37:20 AM - Ported, Готов к отладке.
 */
package Settings;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import javax.xml.stream.XMLStreamException;

import OperatorEngine.Engine;
import OperatorEngine.Utility;

/**
 * NT-Представляет настройки приложения.
 * 
 * @author Селяков Павел
 *
 */
public class ApplicationSettingsBase
{

    // TODO: класс избыточен по функциям создания-изменения настроек!
    /**
     * Application settings file name
     */
    public final static String              AppSettingsFileName = "settings.txt";

    /**
     * Line separator
     */
    protected final static String           lineSeparator       = System.lineSeparator();

    /**
     * Settings file comment symbol as string('#')
     */
    protected final static String           commentChar         = "#";

    /**
     * Строка-маркер окончания файла настроек. Заодно проверяет сохранность русскоязычного текста файла.
     */
    protected final static String           EndOfSettingsFile   = "Конец файла настроек";

    /**
     * Dictionary<String, String> for application setings
     */
    protected HashMap<String, SettingItem> m_Dict;

    /**
     * Settings file pathname
     */
    protected String                        m_filepath;

    /**
     * Flag for Settings has been changed
     */
    protected boolean                       m_ModifiedFlag;

    /**
     * Constructor
     */
    public ApplicationSettingsBase()
    {
        m_Dict = new HashMap<String, SettingItem>();
        this.m_ModifiedFlag = false;
    }

    /**
     * NT-Load or reload settings from file
     * 
     * @throws Exception
     *             Error's on read settings file.
     */
    public void Load() throws Exception
    {
        // open current file and read all items to dictionary
        this.Load(this.m_filepath);
    }

    /**
     * NT-Load or reload settings from file
     * 
     * @param filepath
     *            Settings file path
     * @throws Exception
     *             Error's on read settings file.
     */
    public void Load(String filepath) throws Exception
    {

        /*
         * Стандартный формат файла:
         * Комментарий Заголовок с копирайтом
         * Пустая строка
         * Комментарий Описание настройки
         * Строка ключ=значение настройки
         * Пустая строка
         * Комментарий Описание настройки №2
         * Строка ключ=значение настройки №2
         * Пустая строка
         * и так далее...
         * Запись о окончании файла настроек EndOfSettingsFile
         * - чтобы обнаружить, когда он не до конца записался.
         */

        /*
         * Порядок разбора:
         * Читаем файл по одной строке
         * В строке уже нет символов конца строки - их удалил BufferedReader.
         * А) Если строка начинается с символа комментария - добавить ее в буфер
         * комментариев после удаления символа комментария и тримминга.
         * Если за строкой комментария следующей идет совершенно пустая строка,
         * то буфер комментариев очистить. Так как это не строка описания итема
         * настроек, то она не пригодится.
         * Б) Если строка не комментарий, и содержит символ =, то это строка
         * ключ=значение.
         * Тогда триммим строку и делим по =. Первой частью будет Ключ,
         * второй частью будет Значение. Их тоже триммим и вместе с описанием из
         * буфера комментариев добавляем в объект итема настроек, который
         * добавляем в словарь настроек.
         * И очистить буфер комментариев, так как он уже не нужен.
         * В) Если строка - пустая, то тут либо она после комментария, либо она
         * после строки ключ=значение.
         * В любом случае буфер комментариев уже не нужен, его надо очистить.
         */

        // 1. open specified file and read all items to dictionary
        FileInputStream fi = new FileInputStream(filepath);
        InputStreamReader isr = new InputStreamReader(fi, "UTF-8");
        // при нормальной работе ридер закрывается, а при исключении все приложение
        // закрывается, так что утечки дескриптора не должно образоваться.
        BufferedReader reader = new BufferedReader(isr);

        String line, line2, title, value, descr;
        StringBuilder descriptionLines = new StringBuilder();
        boolean hasEndOfSettingsFile = false;// флаг, что был прочитан маркер окончания файла настроек.
        // read file lines
        while ((line = reader.readLine()) != null)
        {
            line = line.trim();
            // if line has comment char - add it to description buffer
            if (isCommentLine(line))
            {
                // удалить знак комментария и триммить остальной текст
                line2 = conditeComment(line);
                // это или комментарий, или маркер завершения файла
                if (isEndOfSettingsMarker(line2))
                    hasEndOfSettingsFile = true;// установить флаг
                else this.appendDescriptionLine(descriptionLines, line2);
            }
            // descriptionLines.append(makeCommentLine(line));
            // if line is empty - reset description buffer
            else if (line.isEmpty())
                descriptionLines.setLength(0); // clear string builder
            // if line has = then it key-value pair, process it and clear
            // description buffer
            else if (isKeyValueLine(line))
            {
                String[] sar = Utility.StringSplitFirstMatch(line, "=");
                if (sar == null)
                    throw new Exception(String.format("Invalid settings file line format: %s at %s", filepath, line));
                title = sar[0].trim();
                // выбросить исключение, если название итема настроек - пустое.
                // а вот значение может быть и пустой строкой.
                if (title.isEmpty())
                    throw new Exception(String.format("Invalid settings title: %s at %s", filepath, line));
                value = sar[1].trim();
                // extract description from buffer
                descr = descriptionLines.toString();
                descriptionLines.setLength(0);// clear string builder
                // add item
                this.addItem(title, value, descr);
            }
            // else line is wrong format and file is invalid
            else throw new Exception("Invalid settings file format: " + filepath);
        }

        // close all
        reader.close();
        if (hasEndOfSettingsFile == false)
            throw new Exception("Invalid end of settings file: " + filepath);
        // 2. set specified file as current file
        this.m_filepath = filepath;
        // 3. modified flag clear
        this.m_ModifiedFlag = false;

        return;
    }

    /**
     * NT-Очистить текст описания от знака комментария, итп.
     * 
     * @param line
     *            Строка со знаком комментария.
     * @return Функция возвращает строку без знака комментария.
     */
    private String conditeComment(String line)
    {
        // первым символом строки комментария должен быть символ комментария
        if (line.length() > 1)
            return line.substring(1).trim();
        else return "";
    }

    /**
     * NT-Проверить, что строка это маркер конца файла настроек.
     * 
     * @param line
     *            Проверяемая строка
     * @return Функция возвращает True, если строка полностью совпадает с маркером конца файла настроек.
     *         Функция возвращает False в противном случае.
     */
    private boolean isEndOfSettingsMarker(String line)
    {
        return Utility.StringEquals(line, ApplicationSettingsBase.EndOfSettingsFile);
    }

    /**
     * NT-Проверить, содержит ли входная строка пару ключ=значение
     * 
     * @param line
     *            Проверяемая строка
     * @return
     */
    private boolean isKeyValueLine(String line)
    {
        // если строка содержит = то, скорее всего, это строка ключ=значение
        int pos = line.indexOf("=");
        return (pos >= 0);
    }

    /**
     * NT- Add comment line to description buffer.
     * 
     * @param buf
     *            Existing StringBuilder object as description buffer.
     * @param line
     *            Trimmed comment line.
     */
    private void appendDescriptionLine(StringBuilder buf, String line)
    {
        // add to buffer as line with line end
        // if new text is empty, skip it
        if (!line.isEmpty())
        {
            // if buf not empty, add line separator, then add new text
            if (buf.length() > 0)
                buf.append(lineSeparator);
            buf.append(line);
        }

        return;
    }

    /**
     * NT- Проверить что переданная строка является строкой комментария
     * 
     * @param line
     *            Строка
     * @return
     */
    private boolean isCommentLine(String line)
    {
        // первым символом строки комментария должен быть символ комментария
        int pos = line.indexOf(commentChar);
        return (pos == 0);
    }

    /**
     * NT- Write settings to file
     * 
     * @throws IOException
     * @throws XMLStreamException
     */
    public void Store() throws IOException, XMLStreamException
    {
        // open current file, write all items from dictionary to file and close
        // file.
        this.Store(this.m_filepath);

        return;
    }

    /**
     * NT- Write settings to file - if modified only
     * 
     * @throws XMLStreamException
     * @throws IOException
     * 
     */
    public void StoreIfModified() throws IOException, XMLStreamException
    {
        if (this.m_ModifiedFlag == true)
            this.Store(this.m_filepath);

        return;
    }

    /**
     * NT- Write settings to file
     * 
     * @param filepath
     *            Settings file path
     * @throws IOException
     *             Error on writing
     * @throws XMLStreamException
     */
    public void Store(String filepath) throws IOException, XMLStreamException
    {
        // 1. open specified file, write all items from dictionary to file and
        // close file.
        FileOutputStream os = new FileOutputStream(filepath, false);
        OutputStreamWriter writer = new OutputStreamWriter(os, "UTF-8");
        this.WriteCommentLines(writer, "Application settings file");
        this.WriteLine(writer);

        // write each item
        for (SettingItem item : this.m_Dict.values())
        {
            // item.writeXml(writer);
            // write empty line
            this.WriteLine(writer);
            // write description
            String d = item.getDescription();
            this.WriteCommentLines(writer, d);
            // write key-value pair
            this.WriteKeyValuePair(writer, item.getTitle(), item.getValue());
            // write empty line
            this.WriteLine(writer);
        }
        this.WriteCommentLines(writer, EndOfSettingsFile);
        writer.close();
        // 2. do not set specified file as current file
        // 3. clear modified flag
        this.m_ModifiedFlag = false;

        return;
    }

    /**
     * NT-Write item title and value string
     * 
     * @param writer
     *            File writer
     * @param title
     *            Item title as key
     * @param value
     *            Item value
     * @throws IOException
     *             Error on writing
     */
    private void WriteKeyValuePair(
            OutputStreamWriter writer,
            String title,
            String value) throws IOException
    {
        // 1. check title and value
        // 2. print title=value
        writer.write(Utility.GetStringTextNull(title));
        writer.write(" = ");
        writer.write(Utility.GetStringTextNull(value));
        writer.write(lineSeparator);

        return;
    }

    /**
     * NT-Write empty line to output file
     * 
     * @param writer
     *            File writer
     * @throws IOException
     *             Error on writing
     */
    private void WriteLine(OutputStreamWriter writer) throws IOException
    {
        // write empty line
        writer.write(ApplicationSettingsBase.lineSeparator);

        return;
    }

    /**
     * NT-Write item description as multiline comments
     * 
     * @param writer
     *            File writer
     * @param s
     *            Description text
     * @throws IOException
     *             Error on writing
     */
    private void WriteCommentLines(OutputStreamWriter writer, String s)
            throws IOException
    {
        // 1. check null or empty = not print
        if (Utility.StringIsNullOrEmpty(s))
            return;
        // 2. split to lines and print each line as comment
        String[] sar = Utility.StringSplit(s, lineSeparator, true);
        for (String r : sar)
        {
            writer.write(commentChar);
            writer.write(" ");
            writer.write(r);
            writer.write(lineSeparator);
        }

        return;
    }

    /**
     * NR-Reset settings to default values
     */
    public void Reset()
    {
        // clear dictionary
        this.m_Dict.clear();
        // TODO: store default values
        // operator version
        // engine version
        this.addItem("EngineVersion", Engine.EngineVersionString, "Engine version string");
        // engine version
        // TODO: определить, нужно ли тут сбрасывать флаг modified?
        return;
    }

    /**
     * NT-Check setting is present
     * 
     * @param title
     *            Setting title as key
     * @return Returns true if setting present in collection, false otherwise.
     */
    public boolean hasSetting(String title)
    {
        return this.m_Dict.containsKey(title);
    }

    /**
     * NT-Get settings item by title
     * 
     * @param title
     *            Setting item title as key
     * @return Returns SettingsItem; returns null if title not exists in
     *         collection.
     */
    public SettingItem getItem(String title)
    {
        if (this.m_Dict.containsKey(title))
            return this.m_Dict.get(title);
        else return null;
    }

    /**
     * NT-Add new or replace existing settings item in collection.
     * 
     * @param title
     *            Setting item title as key
     * @param item
     *            Settings item object.
     */
    public void addItem(String title, SettingItem item)
    {
        this.m_Dict.put(title, item);
        // set modified flag
        this.m_ModifiedFlag = true;

        return;
    }

    /**
     * NT-Add new or replace existing settings item in collection.
     * 
     * @param title
     *            Setting item title as key.
     * @param value
     *            Setting item value as String.
     * @param descr
     *            Setting item description as multiline String.
     */
    public void addItem(String title, String value, String descr)
    {
        SettingItem item = new SettingItem(title, value, descr);
        this.m_Dict.put(title, item);
        // set modified flag
        this.m_ModifiedFlag = true;

        return;
    }

    /**
     * NT-Add new or replace existing settings item in collection.
     * 
     * @param title
     *            Setting item title as key.
     * @param value
     *            Setting item value as Integer.
     * @param descr
     *            Setting item description as multiline String.
     */
    public void addItem(String title, Integer value, String descr)
    {
        String val = value.toString();
        this.addItem(title, val, descr);

        return;
    }

    /**
     * NT-Add new or replace existing settings item in collection.
     * 
     * @param title
     *            Setting item title as key.
     * @param value
     *            Setting item value as Boolean.
     * @param descr
     *            Setting item description as multiline String.
     */
    public void addItem(String title, Boolean value, String descr)
    {
        String val = value.toString();
        this.addItem(title, val, descr);

        return;
    }

    /**
     * NT-Remove setting item from collection
     * 
     * @param title
     *            Setting item title as key
     */
    public void removeItem(String title)
    {
        this.m_Dict.remove(title);
        // set modified flag
        this.m_ModifiedFlag = true;

        return;
    }

    /**
     * NT- Get value by title
     * 
     * @param title
     *            Setting title
     * @return Returns Value as String; returns null if title not exists in
     *         collection.
     */
    public String getValue(String title)
    {
        if (this.m_Dict.containsKey(title))
            return this.m_Dict.get(title).getValue();
        else return null;
    }

    /**
     * NT- Get value by title
     * 
     * @param title
     *            Setting name
     * @return Returns Value as Integer; returns null if title not exists in
     *         collection or has invalid format.
     */
    public Integer getValueAsInteger(String title)
    {
        if (this.m_Dict.containsKey(title))
            return this.m_Dict.get(title).getValueAsInteger();
        else return null;
    }

    /**
     * NT- Get value by title
     * 
     * @param title
     *            Setting name
     * @return Returns Value as Boolean; returns null if title not exists in
     *         collection or has invalid format.
     */
    public Boolean getValueAsBoolean(String title)
    {

        if (this.m_Dict.containsKey(title))
            return this.m_Dict.get(title).getValueAsBoolean();
        else return null;

    }

    // -------------------------------------------------
    /**
     * NT-Set value by title, create setting if not exists.
     * 
     * @param title
     *            Keyname
     * @param value
     *            Value string
     * @param description
     *            Settings description text for new setting or ""
     */
    public void setValue(String title, String value, String description)
    {
        // add or replace value by title
        if (this.m_Dict.containsKey(title))
            this.m_Dict.get(title).setValue(value);
        else this.m_Dict.put(title, new SettingItem(title, value, description));
        // set modified flag
        this.m_ModifiedFlag = true;

        return;
    }

    /**
     * NT-Set value by title, create setting if not exists.
     * 
     * @param title
     *            Keyname
     * @param value
     *            Value string
     * @param description
     *            Settings description text for new setting or ""
     */
    public void setValue(String title, Integer value, String description)
    {
        // add or replace value by title
        if (this.m_Dict.containsKey(title))
            this.m_Dict.get(title).setValue(value);
        else
        {
            SettingItem t = new SettingItem(title, "", description);
            t.setValue(value);
            this.m_Dict.put(title, t);
        }
        // set modified flag
        this.m_ModifiedFlag = true;

        return;
    }

    /**
     * NT-Set value by title, create setting if not exists.
     * 
     * @param title
     *            Keyname
     * @param value
     *            Value string
     * @param description
     *            Settings description text for new setting or ""
     */
    public void setValue(String title, Boolean value, String description)
    {
        // add or replace value by title
        if (this.m_Dict.containsKey(title))
            this.m_Dict.get(title).setValue(value);
        else
        {
            SettingItem t = new SettingItem(title, "", description);
            t.setValue(value);
            this.m_Dict.put(title, t);
        }
        // set modified flag
        this.m_ModifiedFlag = true;

        return;
    }

}
