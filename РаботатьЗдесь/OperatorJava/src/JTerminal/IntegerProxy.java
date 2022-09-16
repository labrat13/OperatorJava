/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Mar 21, 2022 12:37:20 AM - Прокси как замена "out int x" вроде
 *         готов.
 */
package JTerminal;

/**
 * @author jsmith
 *
 */
public class IntegerProxy
{

    /**
     * Integer value to proxy to
     */
    private int m_Value;

    /**
     * Constructor
     */
    public IntegerProxy()
    {
        this.setValue(0);
    }

    /**
     * Constructor
     * 
     * @param value
     *            Object value
     */
    public IntegerProxy(int value)
    {
        this.setValue(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "IntegerProxy [toString()=" + super.toString() + "]";
    }

    /**
     * Get object value
     * 
     * @return the value
     */
    public int getValue()
    {
        return m_Value;
    }

    /**
     * Set object value
     * 
     * @param value
     *            the value to set
     */
    public void setValue(int value)
    {
        this.m_Value = value;
    }

    /**
     * Set object value
     * 
     * @param val
     *            Value as decimal string
     */
    public void setValue(String val)
    {
        int t = Integer.parseInt(val);
        this.m_Value = t;
    }

}
