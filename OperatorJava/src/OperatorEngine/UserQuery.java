/**
 * @author Селяков Павел
 * Created: Apr 7, 2022 10:41:26 AM
 * State: Apr 7, 2022 10:41:26 AM - initial
 */
package OperatorEngine;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import LogSubsystem.EnumLogMsgClass;
import LogSubsystem.EnumLogMsgState;

/**
 * NT - User query text as object.
 * @author Селяков Павел
 *
 */
public class UserQuery
{
    /*
     * Поскольку класс используется в Процедурах, то важно минимизировать число связанных с ним классов.
     * Например, RegexManager не следует использовать внутри данного класса - следует реализовать операции с ним вне класса.
     */
    
    //*** Constants and  Fields ***
    protected String m_Query;
    
    //*** Constructors ***
    /**
     * RT- default constructor
     */
    public UserQuery(String q)
    {
        this.m_Query = q;
    }
    
    //*** Properties ***
    /** RT-Get query text
     * @return Returns query text string.
     */
    public String getQuery()
    {
        return m_Query;
    }
    /** RT-Set query text
     * @param q Query text.
     */
    public void setQuery(String q)
    {
        this.m_Query = q;
    }

    //*** Service  functions ***
    /**
     * RT-Returns string representation of object.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return GetNullOrString(this.m_Query);
    }

    /**
     * RT-Get String with null as String.
     * @param val String object.
     * @return String object.
     */
public static String GetNullOrString(String val)
{
    if(val == null)
        return "[Null]";
    else
        return val;
}

    /**
     * NT- Change query text and write message to log.
     * @param en Current Engine object reference.
     * @param q New text of query.
     * @throws Exception Error on log writing
     */
    public void ChangeQuery(Engine en, String q) throws Exception
    {
        //TODO:  add code here
        //записать в лог старое значение и новое значение запроса
        String old = GetNullOrString(this.m_Query);
        StringBuilder sb = new StringBuilder();
        sb.append("Изменен текст запроса: \"");
        sb.append(old);
        sb.append("\" заменен на \"");
        sb.append(q);
        sb.append("\".");
        //записать в лог сообщение
        en.getLogManager().AddMessage(EnumLogMsgClass.QueryReplaced, EnumLogMsgState.OK, sb.toString());
      //изменить значение запроса
        this.m_Query = q;
        
        return;
    }


    //*** End of file ***
}
