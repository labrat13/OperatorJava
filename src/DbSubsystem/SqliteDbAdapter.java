/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Feb 6, 2022 4:59:55 AM - TODO: указать состояние файла здесь.
 */
package DbSubsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import OperatorEngine.Place;
import OperatorEngine.Procedure;
import OperatorEngine.Utility;

/**
 * NT-Адаптер для БД sqlite3
 * @author  Pavel Seliakov
 */
public class SqliteDbAdapter
{

    // #region Fields
    // public final static String DatabaseFileExtension = ".sqlite";
    /**
     * Application database file name
     */
    public final static String    AppDbFileName = "db.sqlite";

    /**
     * Places table title
     */
    public final static String TablePlaces   = "places";

    /**
     * Routines table title
     */
    public final static String TableProcs    = "routines";

    /**
     * Database file connection string
     */
    protected String              m_connectionString;

    /**
     * Database connection object
     */
    protected Connection          m_connection;

    /**
     * Command execution default timeout
     */
    protected int                 m_Timeout;

    // TODO: add new command here! Add code for new command to ClearCommand()!
    /**
     * SQL Command for AddPlace function
     */
    protected PreparedStatement   m_cmdAddPlace;
/**
 * SQL Command for AddProcedure  function
 */
    protected PreparedStatement   m_cmdAddProcedure;
    // #endregion

    /**
     * NT-Default constructor
     * @throws ClassNotFoundException Ошибка Класс коннектора SQLITE невозможно загрузить.
     * @throws SQLException  Ошибка при использовании БД.
     */
    public SqliteDbAdapter() throws ClassNotFoundException, SQLException
    {
        // load class from library
        Class.forName("org.sqlite.JDBC");

        this.m_connection = null;
        this.m_connectionString = "";// string.Empty;
        this.m_Timeout = 60;
        this.ClearCommands();

        return;
    }
    // //TODO: как реализовать деструктор на Java, если он необходим?
    // ~SqliteDbAdapter()
    // {
    // this.Close();
    //
    // return;
    // }

    // # region Properties

    /**
     * NT-Get command execution timeout in seconds.
     * 
     * @return Returns command execution timeout in seconds.
     */
    public int getTimeout()
    {
        return this.m_Timeout;
    }

    /**
     * NT-Set command execution timeout in seconds.
     * 
     * @param sec
     *            Command execution timeout in seconds.
     */
    public void setTimeout(int sec)
    {
        this.m_Timeout = sec;
    }

    // public String ConnectionString
    // {
    // get
    // {
    // return this.m_connectionString;
    // }
    // set
    // {
    // this.m_connectionString = value;
    // this.m_connection = new SQLiteConnection(this.m_connectionString);
    // }
    // }

    /**
     * NT- Check is database connection active
     * 
     * @return Returns True if connection active.
     * @throws SQLException  Ошибка при использовании БД.
     */
    public boolean isConnectionActive() throws SQLException
    {
        return ((this.m_connection != null) && (this.m_connection.isValid(m_Timeout) == true));
    }

    // #endregion

    // #region Service functions
    /**
     * NR-Clear all member commands
     * 
     * @throws SQLException
     *             Error on database access occured.
     */
    protected void ClearCommands() throws SQLException
    {
        // TODO: add code for new command here!
        CloseAndClearCmd(this.m_cmdAddPlace);
        CloseAndClearCmd(this.m_cmdAddProcedure);
    }

    /**
     * NT-Open connection to database.
     * Specified connection string will be stored inside this object for next
     * using.
     * 
     * @param connectionString
     *            Connection string for database
     * @throws Exception  Ошибка при использовании БД.
     */
    public void Open(String connectionString) throws Exception
    {
        this.m_connectionString = Utility.StringCopy(connectionString);
        this.Open();
    }

    /**
     * NT-Open connection to database, using previous stored connection string.
     * @throws Exception  Ошибка при использовании БД.
     */
    public void Open() throws Exception
    {
        DriverManager.setLoginTimeout(this.m_Timeout);
        this.m_connection = DriverManager.getConnection(this.m_connectionString);
        this.m_connection.setAutoCommit(false);

        return;
    }

    /**
     * NT- Close connection and reset all resources to initial state.
     * Connection string not cleared.
     * @throws SQLException  Ошибка при использовании БД.
     */
    public void Close() throws SQLException
    {
        if (this.m_connection == null) return;
        if (!this.m_connection.isClosed()) this.m_connection.close();
        this.m_connection = null;
        this.ClearCommands();

        return;
    }

    /**
     * NT-Create connection string
     * 
     * @param dbFilePath
     *            Database file pathname string
     * @return Returns connection string
     */
    public static String CreateConnectionString(String dbFilePath)
    {
        // connection strings:
        // jdbc:sqlite::memory: - in-memory database
        // jdbc:sqlite:C:/sqlite/db/chinook.db - windows absolute path
        // jdbc:sqlite:test.db - test.db in current folder
        return "jdbc:sqlite:" + dbFilePath;
    }
    // #endregion

    // #region Transaction functions
    /**
     * NR-Transaction begins on first query
     * @throws Exception Функция не реализована.
     */
    public void TransactionBegin() throws Exception
    {
        // this.m_transaction = this.m_connection.BeginTransaction();
        // this.ClearCommands();
        throw new Exception("Not implemented function");
    }

    /**
     * NT- Commit current transaction
     * @throws SQLException  Ошибка при использовании БД.
     */
    public void TransactionCommit() throws SQLException
    {
        this.m_connection.commit();
        this.ClearCommands();
        return;
    }

    /**
     * NT-Rollback current transaction
     * @throws SQLException  Ошибка при использовании БД.
     */
    public void TransactionRollback() throws SQLException
    {
        this.m_connection.rollback();
        this.ClearCommands();
        return;
    }
    // #endregion

    // #region General adapter functions
    // //DB created automatically on Open() function
    // public static void DatabaseCreate(String filename)
    // {
    // SQLiteConnection.CreateFile(filename);
    // }

    /**
     * NT- Execute INSERT UPDATE DELETE query
     * 
     * @param query
     *            SQL query string
     * @param timeout
     *            Execution timeout in seconds.
     * @return Returns number of changed rows or 0 if no changes.
     * @throws SQLException  Ошибка при использовании БД.
     */
    public int ExecuteNonQuery(String query, int timeout) throws SQLException
    {
        Statement sqLiteCommand = this.m_connection.createStatement();
        sqLiteCommand.setQueryTimeout(timeout);
        int result = sqLiteCommand.executeUpdate(query);
        sqLiteCommand.close();

        return result;
    }

    /**
     * NT-Execute SELECT query without arguments.
     * Caller must close Statement after reading result set via accessor
     * ResultSet.getStatement();
     * 
     * @param query
     *            SQL query string
     * @param timeout
     *            Execution timeout in seconds.
     * @return Returns ResultSet for this query.
     * @throws SQLException  Ошибка при использовании БД.
     */
    public ResultSet ExecuteReader(String query, int timeout) throws SQLException
    {
        Statement sqLiteCommand = this.m_connection.createStatement();
        sqLiteCommand.setQueryTimeout(timeout);
        ResultSet result = sqLiteCommand.executeQuery(query);
        // get Statement to close it later: result.getStatement();
        return result;
    }

    /**
     * NT-Execute SELECT query without arguments.
     * Returns result in first row first column as int.
     * 
     * @param query
     *            SQL query string
     * @param timeout
     *            Execution timeout in seconds.
     * @return Returns result in first row first column as int. Returns -1 if
     *         errors.
     * @throws SQLException  Ошибка при использовании БД.
     */
    public int ExecuteScalar(String query, int timeout) throws SQLException
    {
        Statement sqLiteCommand = this.m_connection.createStatement();
        sqLiteCommand.setQueryTimeout(timeout);
        ResultSet rs = sqLiteCommand.executeQuery(query);
        int result = -1;
        // read first row in result
        // if(rs.first() == true) - throws exception with: ResultSet has mode FORWARD_ONLY 
        if (rs.next() == true)
        {
            // read first column in result
            result = rs.getInt(1);// first column = 1!
        }
        sqLiteCommand.close();

        return result;
    }

    /**
     * NT-Получить из ридера строку либо нуль как пустую строку.
     * 
     * @param rdr
     *            Объект ридера.
     * @param index
     *            Индекс столбца для ридера, начинается с 1.
     * @return Функция возвращает строку или пустую строку, если исходное
     *         значение было null.
     * @throws SQLException  Ошибка при использовании БД.
     */
    public static String getDbString(ResultSet rdr, int index) throws SQLException
    {
        String result = rdr.getString(index);
        if (result == null) result = "";

        return result;
    }

    /**
     * NT-Get last used rowid for table. 
     * @param table Название таблицы.
     * @param timeout Таймаут операции, в секундах.
     * @return Returns last used rowid for table.
     * @throws SQLException  Ошибка при использовании БД.
     */
    public int getLastRowId(String table, int timeout) throws SQLException
    {
        String query = String.format("SELECT \"seq\" FROM \"sqlite_sequence\" WHERE \"name\" = \"%s\";", table);
        return this.ExecuteScalar(query, timeout);
    }
    
    /**
     * NT-Удалить строки с указанным значением столбца из таблицы.
     * 
     * @param table
     *            Название таблицы.
     * @param column
     *            Название столбца.
     * @param val
     *            Числовое значение столбца в таблице.
     * @param timeout
     *            Таймаут операции, в секундах.
     * @return Функция возвращает число удаленных строк.
     * 
     *         Эта универсальная функция позволяет удалить строку таблицы по
     *         значению одного из ее столбцов.
     *         Например, по ID записи.
     * @throws SQLException  Ошибка при использовании БД.
     */
    public int DeleteRow(String table, String column, int val, int timeout) throws SQLException
    {
        // SQLiteCommand sqLiteCommand = new
        // SQLiteCommand(String.Format((IFormatProvider)
        // CultureInfo.InvariantCulture, "DELETE FROM \"{0}\" WHERE (\"{1}\" =
        // {2});", (object) table, (object) column, (object) val),
        // this.m_connection, this.m_transaction);
        // ((DbCommand) sqLiteCommand).CommandTimeout = timeout;
        // return ((DbCommand) sqLiteCommand).ExecuteNonQuery();
        String query = String.format("DELETE FROM \"%s\" WHERE (\"%s\" = %d);", table, column, val);
        return this.ExecuteNonQuery(query, timeout);
    }

    /**
     * NT-Получить максимальное значение поля столбца таблицы.
     * 
     * @param table
     *            Название таблицы.
     * @param column
     *            Название столбца.
     * @param timeout
     *            Таймаут операции в секундах.
     * @return Возвращает максимальное значение ячеек столбца таблицы или -1 при
     *         ошибке.
     * @throws SQLException  Ошибка при использовании БД.
     */
    public int getTableMaxInt32(String table, String column, int timeout) throws SQLException
    {
        // SQLiteCommand sqLiteCommand = new
        // SQLiteCommand(String.Format((IFormatProvider)
        // CultureInfo.InvariantCulture, "SELECT MAX(\"{0}\") FROM \"{1}\";",
        // new object[2] {
        // (object) column,
        // (object) table
        // }), this.m_connection, this.m_transaction);
        // ((DbCommand) sqLiteCommand).CommandTimeout = timeout;
        // int num = -1;
        // SQLiteDataReader sqLiteDataReader = sqLiteCommand.ExecuteReader();
        // if (((DbDataReader) sqLiteDataReader).HasRows)
        // {
        // ((DbDataReader) sqLiteDataReader).Read();
        // num = ((DbDataReader) sqLiteDataReader).GetInt32(0);
        // }
        // ((DbDataReader) sqLiteDataReader).Close();
        // return num;

        String query = String.format("SELECT MAX(`%s`) FROM `%s`;", column, table);
        return this.ExecuteScalar(query, timeout);

    }

    /**
     * NT-Получить минимальное значение поля столбца таблицы.
     * 
     * @param table
     *            Название таблицы.
     * @param column
     *            Название столбца.
     * @param timeout
     *            Таймаут операции в секундах.
     * @return Возвращает минимальное значение ячеек столбца таблицы или -1 при
     *         ошибке.
     * @throws SQLException  Ошибка при использовании БД.
     */
    public int getTableMinInt32(String table, String column, int timeout) throws SQLException
    {
        // SQLiteCommand sqLiteCommand = new
        // SQLiteCommand(String.Format((IFormatProvider)
        // CultureInfo.InvariantCulture, "SELECT MIN(\"{0}\") FROM \"{1}\";",
        // new object[2] {
        // (object) column,
        // (object) table
        // }), this.m_connection, this.m_transaction);
        // ((DbCommand) sqLiteCommand).CommandTimeout = timeout;
        // int num = -1;
        // SQLiteDataReader sqLiteDataReader = sqLiteCommand.ExecuteReader();
        // if (((DbDataReader) sqLiteDataReader).HasRows)
        // {
        // ((DbDataReader) sqLiteDataReader).Read();
        // num = ((DbDataReader) sqLiteDataReader).GetInt32(0);
        // }
        // ((DbDataReader) sqLiteDataReader).Close();
        // return num;

        String query = String.format("SELECT MIN(`%s`) FROM `%s`;", column, table);
        return this.ExecuteScalar(query, timeout);
    }

    /**
     * NT-Получить общее число записей в таблице
     * 
     * @param table
     *            Название таблицы.
     * @param column
     *            Название столбца.
     * @param timeout
     *            Таймаут операции в секундах.
     * @return Возвращает общее число записей в таблице или -1 при ошибке.
     * @throws SQLException  Ошибка при использовании БД.
     */
    public int GetRowCount(String table, String column, int timeout) throws SQLException
    {
        // SQLiteCommand sqLiteCommand = new
        // SQLiteCommand(String.Format((IFormatProvider)
        // CultureInfo.InvariantCulture, "SELECT COUNT(\"{0}\") FROM \"{1}\";",
        // new object[2] {
        // (object) column,
        // (object) table
        // }), this.m_connection, this.m_transaction);
        // ((DbCommand) sqLiteCommand).CommandTimeout = timeout;
        // int num = -1;
        // SQLiteDataReader sqLiteDataReader = sqLiteCommand.ExecuteReader();
        // if (((DbDataReader) sqLiteDataReader).HasRows)
        // {
        // ((DbDataReader) sqLiteDataReader).Read();
        // num = ((DbDataReader) sqLiteDataReader).GetInt32(0);
        // }
        // ((DbDataReader) sqLiteDataReader).Close();
        // return num;

        String query = String.format("SELECT COUNT(`%s`) FROM `%s`;", column, table);
        return this.ExecuteScalar(query, timeout);
    }

    /**
     * NT-Получить число записей таблицы с указанным значением столбца
     * 
     * @param table
     *            Название таблицы.
     * @param column
     *            Название столбца.
     * @param val
     *            Значение столбца.
     * @param timeout
     *            Таймаут операции в секундах.
     * @return Возвращает число записей таблицы с указанным значением столбца
     *         или -1 при ошибке.
     * @throws SQLException  Ошибка при использовании БД.
     */
    public int GetRowCount(String table, String column, int val, int timeout) throws SQLException
    {
        // SQLiteCommand sqLiteCommand = new
        // SQLiteCommand(String.Format((IFormatProvider)
        // CultureInfo.InvariantCulture, "SELECT COUNT(\"{0}\") FROM \"{1}\"
        // WHERE (\"{0}\" = {2});", (object) column, (object) table, (object)
        // val), this.m_connection, this.m_transaction);
        // ((DbCommand) sqLiteCommand).CommandTimeout = timeout;
        // int num = -1;
        // SQLiteDataReader sqLiteDataReader = sqLiteCommand.ExecuteReader();
        // if (((DbDataReader) sqLiteDataReader).HasRows)
        // {
        // ((DbDataReader) sqLiteDataReader).Read();
        // num = ((DbDataReader) sqLiteDataReader).GetInt32(0);
        // }
        // ((DbDataReader) sqLiteDataReader).Close();
        // return num;

        String query = String.format("SELECT COUNT(`%s`) FROM `%s` WHERE (`%s` = %d);", column, table, column, val);
        return this.ExecuteScalar(query, timeout);
    }

    /**
     * NT-Проверить существование в таблице записи с указанным идентификатором.
     * 
     * @param table
     *            Название таблицы.
     * @param column
     *            Название столбца идентификаторов записей.
     * @param id
     *            Значение идентификатора записи.
     * @param timeout
     *            Таймаут операции в секундах.
     * @return Возвращает True при существовании записи с указанным ид, иначе
     *         возвращает False.
     * @throws SQLException Ошибка при использовании БД.
     */
    public boolean IsRowExists(String table, String column, int id, int timeout) throws SQLException
    {
        return (this.GetRowCount(table, column, id, timeout) > 0);
    }

    /**
     * NT-Очистить таблицу БД.
     * 
     * @param table
     *            Название таблицы.
     * @param timeout
     *            Таймаут операции в секундах.
     * @throws SQLException  Ошибка при использовании БД.
     */
    public void TableClear(String table, int timeout) throws SQLException
    {
        // SQLiteCommand sqLiteCommand = new
        // SQLiteCommand(String.Format((IFormatProvider)
        // CultureInfo.InvariantCulture, "DELETE FROM {0};", new object[1] {
        // (object) table
        // }), this.m_connection, this.m_transaction);
        // ((DbCommand) sqLiteCommand).CommandTimeout = timeout;
        // ((DbCommand) sqLiteCommand).ExecuteNonQuery();

        String query = String.format("DELETE FROM `%s`;", table);
        this.ExecuteNonQuery(query, timeout);

        return;
    }

    /**
     * NT-Удалить таблицу БД
     * 
     * @param table
     *            Название таблицы.
     * @param timeout
     *            Таймаут операции в секундах.
     * @throws SQLException  Ошибка при использовании БД.
     */
    public void TableDrop(String table, int timeout) throws SQLException
    {
        String query = String.format("DROP TABLE IF EXISTS `%s`;", table);
        this.ExecuteNonQuery(query, timeout);

        return;
    }

    // #endregion
    /**
     * NT- Close and null SQL command object
     * 
     * @param cmd
     *            SQL command object
     * @throws SQLException
     *             Error on database access occured.
     */
    protected void CloseAndClearCmd(PreparedStatement cmd) throws SQLException
    {
        if (cmd != null) cmd.close();
        cmd = null;

        return;
    }


    // #region Custom functions

    /**
     * NT-Create database tables
     * 
     * @return Returns True if success, False otherwise.
     * @throws SQLException  Ошибка при использовании БД.
     */
    public boolean CreateDatabaseTables() throws SQLException
    {
        // using(SQLiteTransaction
        // sqLiteTransaction=this.m_connection.BeginTransaction())
        // {
        // using(SQLiteCommand sqLiteCommand=new
        // SQLiteCommand(this.m_connection))
        // {
        // ((DbCommand)sqLiteCommand).CommandText=String.Format((IFormatProvider)CultureInfo.InvariantCulture,"DROP
        // TABLE IF EXISTS `{0}`;",new object[1]{(object)"places"});
        // ((DbCommand)sqLiteCommand).ExecuteNonQuery();
        // ((DbCommand)sqLiteCommand).CommandText=String.Format((IFormatProvider)CultureInfo.InvariantCulture,"CREATE
        // TABLE \"{0}\"(\"id\" Integer Primary Key Autoincrement,\"title\"
        // Text,\"type\" Text,\"path\" Text,\"descr\" Text,\"syno\" Text);",new
        // object[1]{(object)"places"});
        // ((DbCommand)sqLiteCommand).ExecuteNonQuery();
        // ((DbCommand)sqLiteCommand).CommandText=String.Format((IFormatProvider)CultureInfo.InvariantCulture,"DROP
        // TABLE IF EXISTS `{0}`;",new object[1]{(object)"routines"});
        // ((DbCommand)sqLiteCommand).ExecuteNonQuery();
        // ((DbCommand)sqLiteCommand).CommandText=String.Format((IFormatProvider)CultureInfo.InvariantCulture,"CREATE
        // TABLE \"{0}\"(\"id\" Integer Primary Key Autoincrement,\"title\"
        // Text,\"ves\" Real,\"path\" Text,\"regex\" Text,\"descr\" Text);",new
        // object[1]{(object)"routines"});
        // ((DbCommand)sqLiteCommand).ExecuteNonQuery();
        // }
        // ((DbTransaction)sqLiteTransaction).Commit();
        // }
        boolean result = true;
        try
        {
            TableDrop(SqliteDbAdapter.TablePlaces, 60);
            String query = String.format("CREATE TABLE \"%s\" (\"id\" Integer Primary Key Autoincrement,\"title\" Text,\"type\" Text,\"path\" Text,\"descr\" Text,\"syno\" Text);", SqliteDbAdapter.TablePlaces);
            this.ExecuteNonQuery(query, 60);
            TableDrop(SqliteDbAdapter.TableProcs, 60);
            query = String.format("CREATE TABLE \"%s\"(\"id\" Integer Primary Key Autoincrement,\"title\" Text,\"ves\" Real,\"path\" Text,\"regex\" Text,\"descr\" Text);", SqliteDbAdapter.TableProcs);
            this.ExecuteNonQuery(query, 60);
            this.m_connection.commit();
        }
        catch (Exception ex)
        {
            result = false;
            this.m_connection.rollback();
        }

        return result;
    }

    /**
     * NT-Получить все записи таблицы Places
     * 
     * @return Функция возвращает список записей таблицы Places
     * @throws Exception Ошибка
     * @throws SQLException  Ошибка при использовании БД.
     */
    public LinkedList<Place> GetAllPlaces() throws SQLException, Exception
    {
        LinkedList<Place> list = new LinkedList<Place>();

        // SQLiteDataReader sqLiteDataReader =
        // this.ExecuteReader(String.Format((IFormatProvider)
        // CultureInfo.InvariantCulture, "SELECT * FROM \"{0}\"", new object[1]
        // {
        // (object) "places"
        // }), this.m_Timeout);
        String query = String.format("SELECT * FROM \"%s\";", SqliteDbAdapter.TablePlaces);
        ResultSet reader = this.ExecuteReader(query, this.m_Timeout);

        // if (((DbDataReader) sqLiteDataReader).HasRows)
        // {
        // while (((DbDataReader) sqLiteDataReader).Read())
        // {
        // Place place = new Place();
        // place.TableId = ((DbDataReader) sqLiteDataReader).GetInt32(0);
        // place.Title = ((DbDataReader) sqLiteDataReader).GetString(1);
        // place.PlaceTypeExpression = ((DbDataReader)
        // sqLiteDataReader).GetString(2);
        // place.Path = ((DbDataReader) sqLiteDataReader).GetString(3);
        // place.Description = ((DbDataReader) sqLiteDataReader).GetString(4);
        // place.Synonim = ((DbDataReader) sqLiteDataReader).GetString(5);
        // place.ParseEntityTypeString();
        // list.Add(place);
        // }
        // }
        while (reader.next())
        {
            Place place = new Place();
            place.set_TableId(reader.getInt(1));
            place.set_Title(reader.getString(2));
            place.set_PlaceTypeExpression(reader.getString(3));
            place.set_Path(reader.getString(4));
            place.set_Description(reader.getString(5));
            place.set_Synonim(reader.getString(6));
            place.ParseEntityTypeString();// TODO: перенести этот вызов на более
                                          // поздний этап и обложить катчем на
                                          // всякий случай.
            list.add(place);
        }

        // ((DbDataReader) sqLiteDataReader).Close();
        // close command and result set objects
        reader.getStatement().close();

        return list;
    }

    /**
     * NT-Добавить Место в таблицу Мест.
     * 
     * @param p
     *            Добавляемое Место.
     * @throws Exception  Ошибка при использовании БД.
     */
    public void AddPlace(Place p) throws Exception
    {
        // SQLiteCommand sqLiteCommand = this.m_cmdAddPlace;
        PreparedStatement ps = this.m_cmdAddPlace;

        // if (sqLiteCommand == null)
        // {
        // sqLiteCommand = new SQLiteCommand(String.Format((IFormatProvider)
        // CultureInfo.InvariantCulture, "INSERT INTO \"{0}\"(\"title\",
        // \"type\", \"path\", \"descr\", \"syno\") VALUES (?,?,?,?,?);", new
        // object[1] {
        // (object) "places"
        // }), this.m_connection, this.m_transaction);
        // ((DbCommand) sqLiteCommand).CommandTimeout = this.m_Timeout;
        // create if not exists
        if (ps == null)
        {
            String query = String.format("INSERT INTO \"%s\"(\"title\", \"type\", \"path\", \"descr\", \"syno\") VALUES (?,?,?,?,?);", SqliteDbAdapter.TablePlaces);
            ps = this.m_connection.prepareStatement(query);
            // set timeout here
            ps.setQueryTimeout(this.m_Timeout);

            // Эти типы параметров не нужны
            // sqLiteCommand.Parameters.Add("a0", DbType.String);
            // sqLiteCommand.Parameters.Add("a1", DbType.String);
            // sqLiteCommand.Parameters.Add("a2", DbType.String);
            // sqLiteCommand.Parameters.Add("a3", DbType.String);
            // sqLiteCommand.Parameters.Add("a4", DbType.String);
            // this.m_cmdAddPlace = sqLiteCommand;
            // }
            // write back
            this.m_cmdAddPlace = ps;
        }

        // ((DbParameter) sqLiteCommand.Parameters[0]).Value = (object) p.Title;
        // ((DbParameter) sqLiteCommand.Parameters[1]).Value = (object)
        // p.PlaceTypeExpression;
        // ((DbParameter) sqLiteCommand.Parameters[2]).Value = (object) p.Path;
        // ((DbParameter) sqLiteCommand.Parameters[3]).Value = (object)
        // p.Description;
        // ((DbParameter) sqLiteCommand.Parameters[4]).Value = (object)
        // p.Synonim;

        // set parameters
        ps.setString(1, p.get_Title());
        ps.setString(2, p.get_PlaceTypeExpression());
        ps.setString(3, p.get_Path());
        ps.setString(4, p.get_Description());
        ps.setString(5, p.get_Synonim());

        // ((DbCommand) sqLiteCommand).ExecuteNonQuery();
        ps.executeUpdate();
        // Do not close command here - for next reusing

        return;
    }

    /**
     * NT-Удалить Место по ИД.
     * 
     * @param placeId
     *            ИД Места.
     * @return Функция возвращает число измененных строк таблицы.
     * @throws SQLException  Ошибка при использовании БД.
     */
    public int RemovePlace(int placeId) throws SQLException
    {
        return this.DeleteRow(SqliteDbAdapter.TablePlaces, "id", placeId, this.m_Timeout);
    }

    /**
     * NT-Получить все записи таблицы Процедур
     * 
     * @return Функция возвращает список записей из таблицы Процедур.
     * @throws SQLException  Ошибка при использовании БД.
     */
    public LinkedList<Procedure> GetAllProcedures() throws SQLException
    {
        LinkedList<Procedure> list = new LinkedList<Procedure>();

        // SQLiteDataReader sqLiteDataReader =
        // this.ExecuteReader(String.Format((IFormatProvider)
        // CultureInfo.InvariantCulture, "SELECT * FROM \"{0}\"", new object[1]
        // {
        // (object) "routines"
        // }), this.m_Timeout);
        String query = String.format("SELECT * FROM \"%s\";", SqliteDbAdapter.TableProcs);
        ResultSet reader = this.ExecuteReader(query, this.m_Timeout);

        // if (((DbDataReader) sqLiteDataReader).HasRows)
        // {
        // while (((DbDataReader) sqLiteDataReader).Read())
        // {
        // Procedure procedure = new Procedure();
        // procedure.TableId = ((DbDataReader) sqLiteDataReader).GetInt32(0);
        // procedure.Title = ((DbDataReader) sqLiteDataReader).GetString(1);
        // procedure.Ves = ((DbDataReader) sqLiteDataReader).GetDouble(2);
        // procedure.Path = ((DbDataReader) sqLiteDataReader).GetString(3);
        // procedure.Regex = ((DbDataReader) sqLiteDataReader).GetString(4);
        // procedure.Description = ((DbDataReader)
        // sqLiteDataReader).GetString(5);
        // list.Add(procedure);
        // }
        // }
        while (reader.next())
        {
            Procedure proc = new Procedure();
            proc.set_TableId(reader.getInt(1));
            proc.set_Title(reader.getString(2));
            proc.set_Ves(reader.getDouble(3));
            proc.set_Path(reader.getString(4));
            proc.set_Regex(reader.getString(5));
            proc.set_Description(reader.getString(6));

            list.add(proc);
        }
        // ((DbDataReader) sqLiteDataReader).Close();
        // close command and result set objects
        reader.getStatement().close();

        return list;
    }

    /**
     * NT-Добавить Процедуру.
     * 
     * @param p
     *            Добавляемая Процедура.
     * @throws SQLException  Ошибка при использовании БД.
     */
    public void AddProcedure(Procedure p) throws SQLException
    {
        // SQLiteCommand sqLiteCommand = this.m_cmdAddProcedure;
        PreparedStatement ps = this.m_cmdAddProcedure;

        // if (sqLiteCommand == null)
        // {
        // sqLiteCommand = new SQLiteCommand(String.Format((IFormatProvider)
        // CultureInfo.InvariantCulture, "INSERT INTO \"{0}\"(\"title\",
        // \"ves\", \"path\", \"regex\", \"descr\") VALUES (?,?,?,?,?);", new
        // object[1] {
        // (object) "routines"
        // }), this.m_connection, this.m_transaction);
        // ((DbCommand) sqLiteCommand).CommandTimeout = this.m_Timeout;
        // sqLiteCommand.Parameters.Add("a0", DbType.String);
        // sqLiteCommand.Parameters.Add("a1", DbType.Double);
        // sqLiteCommand.Parameters.Add("a2", DbType.String);
        // sqLiteCommand.Parameters.Add("a3", DbType.String);
        // sqLiteCommand.Parameters.Add("a4", DbType.String);
        // this.m_cmdAddProcedure = sqLiteCommand;
        // }
        if (ps == null)
        {
            String query = String.format("INSERT INTO \"%s\"(\"title\", \"ves\", \"path\", \"regex\", \"descr\") VALUES (?,?,?,?,?);", SqliteDbAdapter.TableProcs);
            ps = this.m_connection.prepareStatement(query);
            // set timeout here
            ps.setQueryTimeout(this.m_Timeout);
            // write back
            this.m_cmdAddProcedure = ps;
        }
        // ((DbParameter) sqLiteCommand.Parameters[0]).Value = (object) p.Title;
        // ((DbParameter) sqLiteCommand.Parameters[1]).Value = (object) p.Ves;
        // ((DbParameter) sqLiteCommand.Parameters[2]).Value = (object) p.Path;
        // ((DbParameter) sqLiteCommand.Parameters[3]).Value = (object) p.Regex;
        // ((DbParameter) sqLiteCommand.Parameters[4]).Value = (object)
        // p.Description;
        // set parameters
        ps.setString(1, p.get_Title());
        ps.setDouble(2, p.get_Ves());
        ps.setString(3, p.get_Path());
        ps.setString(4, p.get_Regex());
        ps.setString(5, p.get_Description());

        // ((DbCommand) sqLiteCommand).ExecuteNonQuery();
        ps.executeUpdate();
        // Do not close command here - for next reusing

        return;
    }

    /**
     * NT-Удалить Процедуру
     * 
     * @param id
     *            ИД Процедуры
     * @return Функция возвращает число измененных строк таблицы.
     * @throws SQLException  Ошибка при использовании БД.
     */
    public int RemoveProcedure(int id) throws SQLException
    {
        return this.DeleteRow(SqliteDbAdapter.TableProcs, "id", id, this.m_Timeout);
    }

    // TODO: Добавить функции UPDATE для Процедур и Мест
    // #endregion
}
