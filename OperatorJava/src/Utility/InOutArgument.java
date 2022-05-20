/**
 * @author Селяков Павел
 * Created: May 20, 2022 11:15:04 PM
 * State: May 20, 2022 11:15:04 PM - initial
 */
package Utility;

import OperatorEngine.Place;
import OperatorEngine.Procedure;

/** 
 * RT-Класс-контейнер для объектов, передаваемых в функции и обратно.
 * @author Селяков Павел
 *
 */
public class InOutArgument
{
    //*** Constants and  Fields ***
    /**
     * Object value
     */
    private Object m_value;
    
    //*** Constructors ***
    /**
     * Default constructor
     */
    public InOutArgument()
    {
        this.m_value = null;
    }
    /**
     * Parameter constructor
     * @param val Initial value
     */
    public InOutArgument(Object val)
    {
        this.m_value = val;
    }
    //*** Properties ***
    /**
     * Check this value is null
     * @return Returns true if value is null, returns false otherwise,
     */
    public boolean isNull()
    {
        return (this.m_value == null);
    }
    /**
     * Set value
     * @param ob value
     */
    public void setValue(Object ob)
    {
        this.m_value = ob;
    }
    /**
     * Get value as String
     * @return Function returns value as string
     */
    public String getValueString()
    {
        return (String) this.m_value;
    }
    /**
     * Get value as Double
     * @return Function returns value as Double
     */
    public Double getValueDouble()
    {
        return (Double) this.m_value;
    }
    /**
     * Get value as Integer
     * @return Function returns value as Integer
     */
    public Integer getValueInteger()
    {
        return (Integer) this.m_value;
    }
    /**
     * Get value as Procedure
     * @return Function returns value as Procedure
     */
    public Procedure getValueProcedure()
    {
        return (Procedure) this.m_value;   
    }
    
    /**
     * Get value as Place
     * @return Function returns value as Place
     */
    public Place getValuePlace()
    {
        return (Place) this.m_value;   
    }
    /**
     * Return string representation of object.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        if(this.m_value == null) return "Null";
        else return m_value.toString();
    }
    
    //*** Service  functions ***

    //*** End of file ***
}
