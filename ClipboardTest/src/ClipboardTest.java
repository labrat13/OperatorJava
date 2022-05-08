import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * @author Селяков Павел
 *         Created: May 8, 2022 7:52:27 PM
 *         State: May 8, 2022 7:52:27 PM - initial
 */

/**
 * @author Селяков Павел
 *
 */
public class ClipboardTest
{

    /**NT- Test clipboard reading code
     * @param args
     */
    public static void main(String[] args)
    {
        // test beep()
        ClipboardManager.Beep();

        // Create a Clipboard object using getSystemClipboard() method
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        // Get data stored in the clipboard that is in the form of a string (text)
        while (true)
        {
            try
            {
                System.out.println(c.getData(DataFlavor.stringFlavor));

                printAvailableContentTypes2(c);

                System.out.println("press enter to next");
                System.console().readLine();

            }
            catch (UnsupportedFlavorException | IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    /**
     * NT-Print available clipboard data types
     * 
     * @param c
     *            Clipboard object.
     */
    @SuppressWarnings({ "deprecation" })
    private static void printAvailableContentTypes2(Clipboard c)
    {

        System.out.println("Available content types:");
        printAvailableFlavor(c, DataFlavor.allHtmlFlavor, "allHtmlFlavor");
        printAvailableFlavor(c, DataFlavor.fragmentHtmlFlavor, "fragmentHtmlFlavor");
        printAvailableFlavor(c, DataFlavor.imageFlavor, "imageFlavor");
        printAvailableFlavor(c, DataFlavor.javaFileListFlavor, "javaFileListFlavor");
        printAvailableFlavor(c, DataFlavor.plainTextFlavor, "plainTextFlavor");
        printAvailableFlavor(c, DataFlavor.selectionHtmlFlavor, "selectionHtmlFlavor");
        printAvailableFlavor(c, DataFlavor.stringFlavor, "stringFlavor");
        // printAvailableFlavor(c, DataFlavor.javaJVMLocalObjectMimeType, "javaJVMLocalObjectMimeType");
        // printAvailableFlavor(c, DataFlavor.javaRemoteObjectMimeType, "javaRemoteObjectMimeType");
        // printAvailableFlavor(c, DataFlavor.javaSerializedObjectMimeType, "javaSerializedObjectMimeType");

        System.out.println();

    }

    /**
     * NT-Print title of specified clipboard data type, if available.
     * 
     * @param df
     *            Clipboard data type object
     * 
     */
    private static void printAvailableFlavor(
            Clipboard c,
            DataFlavor df,
            String title)
    {
        if (c.isDataFlavorAvailable(df))
            System.out.println("- DataFlavor." + title + "(" + df.getDefaultRepresentationClassAsString() + ")");
        return;
    }

    /**
     * NT-Print available clipboard content data types
     * 
     * @param c
     *            Clipboard object
     */
    @SuppressWarnings("unused")
    private static void printAvailableContentTypes(Clipboard c)
    {

        System.out.println("Available content types:");
        DataFlavor[] df = c.getAvailableDataFlavors();
        for (DataFlavor d : df)
            System.out.println(d.getHumanPresentableName());
        System.out.println();

        return;
    }

    // *** End of file ***
}
