import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Селяков Павел
 * Created: May 8, 2022 9:49:17 PM
 * State: May 8, 2022 9:49:17 PM - initial
 */

/**
 * NT-
 * Менеджер системного буфера обмена 
 * @author Селяков Павел
 *
 */
public class ClipboardManager
{
    //*** Constants and  Fields ***
    /**
     * Ссылка на объект клипборда
     */
    private static Clipboard m_clipboard = null;
    //*** Constructors ***

    //*** Properties ***

    /**
     * NT-Sound signal
     */
    public static void Beep()
    {
        Toolkit.getDefaultToolkit().beep();
    }
    
    /**
     * NT-Get content as text string
     * @return Function returns clipboard content, if available. Function returns null if content not available or any error occurred. 
     */
    public static String getAsText()
    {
        String result = null;
        try 
        {
            Clipboard c = getClipboard();
            Object ob = c.getData(DataFlavor.stringFlavor);
            if(ob != null)
                result = ob.toString();
        }
        catch(Exception ex)
        {
            result = null;
        }
        
        return result;
    }

    /**
     * NT-Get content as File path array. 
     * @param existingOnly Add only existing file's or folder's.
     * @return Function returns clipboard content, if available. Function returns null if content not available or any error occurred.
     */
    public static File[] getAsFileList(boolean existingOnly)
    {
        File[] result = null;
        try 
        {
            Transferable transferable = getClipboard().getContents(null);
            if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.imageFlavor))
            {
               List files =  (List) transferable.getTransferData(DataFlavor.imageFlavor);
               //create list for existing files
               LinkedList<File> lresult = new LinkedList<File>();
               //fill list
               for(Object ob: files)
               {
                    if(ob instanceof File)
                    {
                        File f = (File) ob;
                        if(existingOnly == true)
                        {
                            //добавлять только существующие файлы и папки
                            if(f.exists() == true)
                                lresult.add(f);
                            //else skip non-existing file
                        }
                        else
                        {
                            //добавлять все файлы и папки без проверки их существования.
                            lresult.add(f);
                        }
                    }
                    //не добавлять не-файлы в выходной список.
               }
               //make result array
               result = lresult.toArray(new File[lresult.size()]);
            }
            else
            {
              result = null;
            }
        }
        catch(Exception ex)
        {
          result = null;   
        }
        
        return result;
    }
    /**
     * NR-Get content as Html text
     * @return Function returns clipboard content, if available. Function returns null if content not available or any error occurred.
     */
    public static String getAsHtml()
    {
        return null;
        //TODO: add code to read Clipboard as html
    }
    /**
     * NT-Get content as Image data
     * @return Function returns clipboard content, if available. Function returns null if content not available or any error occurred.
     */
    public static Image getAsImage()
    {
        Image result = null;
        try 
        {
        Transferable transferable = getClipboard().getContents(null);
        if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.imageFlavor))
        {
          return (Image) transferable.getTransferData(DataFlavor.imageFlavor);
        }
        else
        {
          return null;
        }
        }
        catch(Exception ex)
        {
          result = null;   
        }
        
        return result;
    }
    
    //*** Service  functions ***
    
    /**
     * NT-Get default system clipboard and cache it to static variable.
     * @return
     */
    private static Clipboard getClipboard()
    {
        if(m_clipboard == null)
            m_clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        
        return m_clipboard;
    }
    

    //*** End of file ***
}
