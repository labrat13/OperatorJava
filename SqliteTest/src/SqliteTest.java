import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author Селяков Павел
 *         Created: Mar 2, 2022 11:29:02 AM
 *         State: Mar 2, 2022 11:29:02 AM - initial
 */

/**
 * @author Селяков Павел
 *
 */
public class SqliteTest
{

    /**
     * 
     */
    public SqliteTest()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {

        
        
        Connection c = null;
        Statement stmt = null;
        
        try
        {
            makeFileForWget();
            //load class from library
            Class.forName("org.sqlite.JDBC");
            //connection strings:
            //jdbc:sqlite::memory: - in-memory database
            //jdbc:sqlite:C:/sqlite/db/chinook.db - windows absolute path
            //jdbc:sqlite:test.db - test.db in current folder           
            String connectionString = "jdbc:sqlite:/home/jsmith/test.db"; //
            c = DriverManager.getConnection(connectionString);
            //autocommit off
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE COMPANY " +
                           "(ID INT PRIMARY KEY     NOT NULL," +
                           " NAME           TEXT    NOT NULL, " + 
                           " AGE            INT     NOT NULL, " + 
                           " ADDRESS        CHAR(50), " + 
                           " SALARY         REAL)"; 
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();//commit changes
            //
            stmt = c.createStatement();
            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                           "VALUES (1, 'Paul', 32, 'California', 20000.00 );"; 
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                     "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );"; 
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                     "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );"; 
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                     "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );"; 
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
            
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Finished successfully");

    }

    /**
     * @throws Exception 
     * @throws FileNotFoundException 
     * 
     */
    private static void makeFileForWget() throws FileNotFoundException, Exception
    {
        String filepath = "/home/jsmith/towget.txt";
        BufferedWriter wr = ProcedureUtils.FileSystemUtils.openBufferedWriter(filepath, "UTF-8");
        for(int i = 0; i < 4096; i++)
        {
            wr.write("https://books.ifmo.ru/images/book/big/p");
            wr.write(Integer.toString(i));
            wr.write(".jpg");
            wr.newLine();
        }
        wr.close();
        
        return;
    }



}
