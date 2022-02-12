/**
 * @author jsmith
 * Created: Feb 12, 2022 8:09:17 PM
 * State: Feb 12, 2022 8:09:17 PM - initial
 */

package OperatorEngine;

import OperatorEngine.Utility;

/**
 * @author jsmith
 *
 */
public class Version implements Comparable<Version>
{
    /**
     * TODO: add get-set property here
     */
    protected int m_Version;
    /**
     * 
     */
    protected int m_SubVersion;
    /**
     * 
     */
    protected int m_Revision;
    /**
     * 
     */
    protected int m_Build;
    
    /**
     * NT-Constructor
     */
    public Version()
    {
        this.m_Version = 0;
        this.m_SubVersion = 0;
        this.m_Revision = 0;
        this.m_Build = 0;
    }
    /**
     * NT- Constructor
     * @param ver Major version number
     * @param sub Minor version number
     * @param rev Release number
     * @param bild Build number
     */
    public Version(int ver, int sub, int rev, int bild)
    {
     this.m_Version = ver;
     this.m_SubVersion = sub;
     this.m_Revision = rev;
     this.m_Build = bild;
    }

    /** NT- Полное сравнение двух объектов версий по всем полям
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * @param obj Version object for comparing
     * @return Returns 1..0..-1
     */
    @Override
    public int compareTo(Version obj)
    {
        
        if(obj == null) return 1;
        //else
        int result = Integer.compare(this.m_Version, obj.m_Version);
        if(result != 0) return result;
        result = Integer.compare(this.m_SubVersion, obj.m_SubVersion);
        if(result != 0) return result;
        result = Integer.compare(this.m_Revision, obj.m_Revision);
        if(result != 0) return result;
        result = Integer.compare(this.m_Build, obj.m_Build);
        return result;

    }
    
    /** NT- Частичное сравнение двух объектов версий по полям MajorVersion и MinorVersion
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * @param obj Version object for comparing
     * @return Returns 1..0..-1
     */
    public int comparePartial(Version obj)
    {
        if(obj == null) return 1;
        //else
        int result = Integer.compare(this.m_Version, obj.m_Version);
        if(result != 0) return result;
        result = Integer.compare(this.m_SubVersion, obj.m_SubVersion);

        return result;
    }
    
    /** 
     * RT-Return typical version string like "0.18.16.29997"
     */
    @Override
    public String toString()
    {
        return String.format("%d.%d.%d.%d", this.m_Version, this.m_SubVersion, this.m_Revision, this.m_Build);
    }
    /**
     * RT-Get version object from string.
     * @param s Version string.
     * @return Returns version object.
     * @throws Exception Ошибка при парсинге входной строки
     */
    public static Version parse(String s) throws Exception
    {
        //TODO: add parsing version string code here
        int[] v = new int[4];
        v[0] = 0;
        v[1] = 0;
        v[2] = 0;
        v[3] = 0;
        //1. split by '.'
        String[] sar = Utility.StringSplit(s, "\\.", true);
        if((sar.length < 1) || (sar.length > 4))
                throw new Exception("Invalid format input string");
        //2. convert string array to int array
        for(int i  =0; i < sar.length; i++)
            v[i] = Integer.parseInt(sar[i]);
        //3. put to Version
        Version result = new Version(v[0], v[1], v[2], v[3]);
        
        return result;
    }
    /**
     * NT-Попытка преобразовать входную строку в объект Version
     * @param s Входная строка
     * @return Возвращает объект Version при успехе или null при неудаче парсинга.
     */
    public static Version tryParse(String s)
    {
        Version result = null;
        try 
        {
            result = Version.parse(s);
        }
        catch(Exception ex)
        {
          result = null;   
        }
        return result;
    }


}
