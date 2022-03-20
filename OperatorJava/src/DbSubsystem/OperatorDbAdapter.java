/**
 * @author Селяков Павел
 *         Created: Mar 5, 2022 5:55:16 PM
 *         State: Mar 21, 2022 12:37:20 AM - Ported, Готов к отладке.
 */
package DbSubsystem;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.xml.stream.XMLStreamException;

import LogSubsystem.EnumLogMsgClass;
import LogSubsystem.EnumLogMsgState;
import OperatorEngine.Engine;
import OperatorEngine.Item;
import OperatorEngine.Place;
import OperatorEngine.Procedure;
import OperatorEngine.Utility;

/**
 * NT-Адаптер приложения Оператор, для БД sqlite3
 * 
 * @author Селяков Павел
 *
 */
public class OperatorDbAdapter extends SqliteDbAdapter
{

    // Для сообщений в лог использовать только функцию this.safeAddLogMsg(...)

    /**
     * Application database file name
     */
    public final static String  AppDbFileName = "db.sqlite";

    /**
     * Places table title
     */
    public final static String  TablePlaces   = "places";

    /**
     * Routines table title
     */
    public final static String  TableProcs    = "routines";

    /**
     * Backreference to Engine object - for logging
     * Can be null where call this.CreateNewDatabase() !
     */
    protected Engine            m_Engine;

    // TODO: add new command here! Add code for new command to ClearCommand()!
    /**
     * SQL Command for AddPlace function
     */
    protected PreparedStatement m_cmdAddPlace;

    /**
     * SQL Command for AddProcedure function
     */
    protected PreparedStatement m_cmdAddProcedure;

    /**
     * NT-Default constructor
     * 
     * @param engine
     *            Engine backreference for log writing.
     * @throws Exception
     *             Error in database.
     */
    public OperatorDbAdapter(Engine engine) throws Exception
    {
        super();
        this.m_Engine = engine;// for logging
        // reset command object
        // TODO: Add code for new command here!
        this.m_cmdAddPlace = null;
        this.m_cmdAddProcedure = null;

        return;
    }

    /**
     * NT-Get string representation of object.
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        boolean active = this.isConnectionActive();
        String cstr = Utility.GetStringTextNull(this.m_connectionString);
        String result = String.format("OperatorDbAdapter; connection=\"%s\"; active=%s", cstr, active);

        return result;
    }

    /**
     * NT-append new message object to log
     * 
     * @param c
     *            Event class code
     * @param s
     *            Event state code
     * @param text
     *            Event text description
     * @throws IOException
     *             Error on writing to log file.
     * @throws XMLStreamException
     *             Error on writing to log file.
     */
    protected void safeAddLogMsg(
            EnumLogMsgClass c,
            EnumLogMsgState s,
            String text)
            throws IOException, XMLStreamException
    {
        // проверить существование движка и лога, и затем добавить сообщение в
        // лог.
        if (Engine.isLogReady(this.m_Engine))
            this.m_Engine.getLogManager().AddMessage(c, s, text);

        return;
    }

    /**
     * RT-Clear all member commands
     * 
     * @throws SQLException
     *             Error on database access occured.
     */
    @Override
    protected void ClearCommands() throws SQLException
    {
        // TODO: add code for new command here!
        CloseAndClearCmd(this.m_cmdAddPlace);
        CloseAndClearCmd(this.m_cmdAddProcedure);
    }

    /**
     * NT-Create new application database and fill with tables and initial data
     * 
     * @param engine
     *            Engine object reference for logging
     * @param dbFile
     *            Database file path
     * @throws Exception
     *             Error on database access occured.
     */
    public static void CreateNewDatabase(Engine engine, String dbFile)
            throws Exception
    {
        // try
        // {
        // Open
        String connectionString = OperatorDbAdapter.CreateConnectionString(dbFile);
        OperatorDbAdapter db = new OperatorDbAdapter(engine);
        db.Open(connectionString);
        // Write
        db.CreateDatabaseTables();
        // Close database
        db.Close();
        // }
        // catch(Exception ex)
        // {
        // //если произошла ошибка, перезапустить исключение с понятным
        // описанием причины.
        // String msg = "";
        // throw new Exception(msg);
        // }

        return;
    }

    /**
     * RT-Create database tables
     * 
     * @return Returns True if success, False otherwise.
     * @throws SQLException
     *             Ошибка при использовании БД.
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
            TableDrop(OperatorDbAdapter.TablePlaces, 60);
            String query = String.format("CREATE TABLE \"%s\" (\"id\" Integer Primary Key Autoincrement,\"title\" Text,\"type\" Text,\"path\" Text,\"descr\" Text,\"syno\" Text);", OperatorDbAdapter.TablePlaces);
            this.ExecuteNonQuery(query, 60);
            TableDrop(OperatorDbAdapter.TableProcs, 60);
            query = String.format("CREATE TABLE \"%s\"(\"id\" Integer Primary Key Autoincrement,\"title\" Text,\"ves\" Real,\"path\" Text,\"regex\" Text,\"descr\" Text);", OperatorDbAdapter.TableProcs);
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

    // ==============================================================================
    /**
     * RT-Получить все записи таблицы Places
     * 
     * @return Функция возвращает список записей таблицы Places
     * @throws Exception
     *             Ошибка
     * @throws SQLException
     *             Ошибка при использовании БД.
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
        String query = String.format("SELECT * FROM \"%s\";", OperatorDbAdapter.TablePlaces);
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
            //set storage title as database
            place.set_Storage(Item.StorageKeyForDatabaseItem);
            //add to result list
            list.add(place);
        }

        // ((DbDataReader) sqLiteDataReader).Close();
        // close command and result set objects
        reader.getStatement().close();

        return list;
    }

    /**
     * RT-Добавить Место в таблицу Мест.
     * 
     * @param p
     *            Добавляемое Место.
     * @throws Exception
     *             Ошибка при использовании БД.
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
            String query = String.format("INSERT INTO \"%s\"(\"title\", \"type\", \"path\", \"descr\", \"syno\") VALUES (?,?,?,?,?);", OperatorDbAdapter.TablePlaces);
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
     * RT-Удалить Место по ИД.
     * 
     * @param placeId
     *            ИД Места.
     * @return Функция возвращает число измененных строк таблицы.
     * @throws SQLException
     *             Ошибка при использовании БД.
     */
    public int RemovePlace(int placeId) throws SQLException
    {
        return this.DeleteRow(OperatorDbAdapter.TablePlaces, "id", placeId, this.m_Timeout);
    }

    /**
     * NR-Update Place
     * 
     * @param p
     *            Place object
     * @return Функция возвращает число измененных строк таблицы.
     * @throws Exception
     *             Ошибка при использовании БД.
     */
    public int UpdatePlace(Place p) throws Exception
    {
        throw new Exception("Function not implemented"); // TODO: add code here
    }

    // =======================================================================
    /**
     * NT-Получить все записи таблицы Процедур
     * 
     * @return Функция возвращает список записей из таблицы Процедур.
     * @throws SQLException
     *             Ошибка при использовании БД.
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
        String query = String.format("SELECT * FROM \"%s\";", OperatorDbAdapter.TableProcs);
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
            //set storage title as database
            proc.set_Storage(Item.StorageKeyForDatabaseItem);
            //add to result list
            list.add(proc);
        }
        // ((DbDataReader) sqLiteDataReader).Close();
        // close command and result set objects
        reader.getStatement().close();

        return list;
    }

    /**
     * RT-Добавить Процедуру.
     * 
     * @param p
     *            Добавляемая Процедура.
     * @throws SQLException
     *             Ошибка при использовании БД.
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
            String query = String.format("INSERT INTO \"%s\"(\"title\", \"ves\", \"path\", \"regex\", \"descr\") VALUES (?,?,?,?,?);", OperatorDbAdapter.TableProcs);
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
     * RT-Удалить Процедуру
     * 
     * @param id
     *            ИД Процедуры
     * @return Функция возвращает число измененных строк таблицы.
     * @throws SQLException
     *             Ошибка при использовании БД.
     */
    public int RemoveProcedure(int id) throws SQLException
    {
        return this.DeleteRow(OperatorDbAdapter.TableProcs, "id", id, this.m_Timeout);
    }

    /**
     * NR-Update Procedure
     * 
     * @param p
     *            Procedure object
     * @return Функция возвращает число измененных строк таблицы.
     * @throws Exception
     *             Ошибка при использовании БД.
     */
    public int UpdateProcedure(Procedure p) throws Exception
    {
        throw new Exception("Function not implemented"); // TODO: add code here
    }

    // ============================================================

}
