/**
 * @author Селяков Павел
 *         Created: Feb 24, 2022 12:43:30 AM
 *         State: Feb 24, 2022 01:23:30 AM - wait for write functions
 */
package Settings;

import OperatorEngine.Engine;

/**
 * NR-Представляет настройки приложения.
 * Использует XML-файл для хранения настроек приложения.
 * Закомментирован, поскольку реализацию неохота писать и она сложнее, чем
 * сейчас требуется.
 * Но класс оставлен в проекте, так как неохота терять уже написанный код -
 * авось потом пригодится.
 * 
 * @author Селяков Павел
 *
 */
public class ApplicationSettingsXml extends ApplicationSettingsBase
{

    /**
     * Backreference to Engine object - for logging
     */
    protected Engine m_Engine;

    /**
     * Constructor
     */
    public ApplicationSettingsXml(Engine engine)
    {
        this.m_Engine = engine;
    }
    //
    // /**
    // * NR-Load or reload settings from file
    // */
    // public void Load()
    // {
    // // open current file and read all items to dictionary
    // this.Load(this.m_filepath);
    // }
    //
    // /**
    // * NR-Load or reload settings from file
    // *
    // * @param filename
    // * Settings file name
    // */
    // public void Load(String filename)
    // {
    // // TODO: Add code here
    // //1. open specified file and read all items to dictionary
    //
    //
    // //2. set specified file as current file
    // this.m_filepath = filename;
    //
    // return;
    // }
    //
    // /**
    // * NR- Write settings to file
    // * @throws XMLStreamException
    // * @throws FileNotFoundException
    // */
    // public void Store() throws FileNotFoundException, XMLStreamException
    // {
    // // open current file, write all items from dictionary to file and close
    // // file.
    // this.Store(this.m_filepath);
    //
    // return;
    // }
    //
    // /**
    // * NR- Write settings to file
    // *
    // * @param filename
    // * Settings file name
    // * @throws FileNotFoundException
    // * @throws XMLStreamException
    // */
    // public void Store(String filename) throws FileNotFoundException,
    // XMLStreamException
    // {
    //
    // String lineSeparator = System.lineSeparator();
    //
    // // TODO: Add code here
    // // 1. open specified file, write all items from dictionary to file and
    // // close file.
    // FileOutputStream os = new FileOutputStream(filename, false);
    // XMLOutputFactory output = XMLOutputFactory.newInstance();
    // XMLStreamWriter writer = output.createXMLStreamWriter(os);
    // writer.writeStartDocument("utf-8", "1.0");
    // writer.writeCharacters(lineSeparator);
    // writer.writeComment("Application settings file");
    // writer.writeCharacters(lineSeparator);
    // // Вывести корень документа.
    // writer.writeStartElement("settings");
    // writer.writeCharacters(lineSeparator);
    // //write each item
    // for (SettingsItem item : this.m_Dict.values())
    // {
    // //item.writeXml(writer);
    // //write empty line
    // writer.writeCharacters(lineSeparator);
    // //write description
    // String d = item.getDescription();
    // if(!Utility.StringIsNullOrEmpty(d))
    // writer.writeComment(d);
    // writer.writeCharacters(lineSeparator);
    // //write key-value pair
    // writer.writeStartElement(item.getTitle());
    // d = Utility.GetStringTextNull(item.getValue());
    // writer.writeCharacters(d);
    // writer.writeEndElement();
    // writer.writeCharacters(lineSeparator);
    // //write empty line
    // writer.writeCharacters(lineSeparator);
    // }
    // writer.writeEndDocument();
    // writer.close();
    // // 2. do not set specified file as current file
    //
    // return;
    // }
    //
    // /**
    // * NR-Reset settings to default values
    // */
    // public void Reset()
    // {
    // // clear dictionary
    // this.m_Dict.clear();
    // // TODO: store default values
    //
    //
    // return;
    // }

}
