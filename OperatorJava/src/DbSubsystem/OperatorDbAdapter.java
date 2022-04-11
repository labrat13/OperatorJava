/**
 * @author Селяков Павел
 *         Created: Mar 5, 2022 5:55:16 PM
 *         State: 11 Апреля 2022 13:24:20 - Ported, Добавлены функции для TableSetting, Готов к отладке.
 */
package DbSubsystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import OperatorEngine.Engine;
import OperatorEngine.Item;
import OperatorEngine.Place;
import OperatorEngine.Procedure;
import OperatorEngine.Utility;
import Settings.SettingItem;

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
     * Settings table title
     */
    public final static String TableSetting  = "setting";

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
     * SQL Command for UpdatePlace function
     */
    protected PreparedStatement m_cmdUpdatePlace;
    /**
     * SQL Command for AddProcedure function
     */
    protected PreparedStatement m_cmdAddProcedure;
    /**
     * SQL Command for UpdateProcedure function
     */
    protected PreparedStatement m_cmdUpdateProcedure;
    /**
     * SQL Command for AddSetting function
     */
    protected PreparedStatement m_cmdAddSetting;
    /**
     * SQL Command for UpdateSetting function
     */
    protected PreparedStatement m_cmdUpdateSetting;

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
        this.m_cmdUpdatePlace = null;
        this.m_cmdAddProcedure = null;
        this.m_cmdUpdateProcedure = null;
        this.m_cmdAddSetting = null;
        this.m_cmdUpdateSetting = null;

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
        CloseAndClearCmd(this.m_cmdUpdatePlace);
        CloseAndClearCmd(this.m_cmdAddProcedure);
        CloseAndClearCmd(this.m_cmdUpdateProcedure);
        CloseAndClearCmd(this.m_cmdAddSetting);
        CloseAndClearCmd(this.m_cmdUpdateSetting);
        
        return;
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

        boolean result = true;
        try
        {
            //create Places table
            TableDrop(OperatorDbAdapter.TablePlaces, 60);
            String query = String.format("CREATE TABLE \"%s\" (\"id\" INTEGER PRIMARY KEY AUTOINCREMENT,\"title\" TEXT,\"type\" TEXT,\"path\" TEXT\"descr\" TEXT,\"syno\" TEXT);", OperatorDbAdapter.TablePlaces);
            this.ExecuteNonQuery(query, 60);
            //create Procedures table
            TableDrop(OperatorDbAdapter.TableProcs, 60);
            query = String.format("CREATE TABLE \"%s\"(\"id\" INTEGER PRIMARY KEY AUTOINCREMENT,\"title\" TEXT,\"ves\" Real,\"path\" TEXT,\"regex\" TEXT,\"descr\" TEXT);", OperatorDbAdapter.TableProcs);
            this.ExecuteNonQuery(query, 60);
            //create Setting table
            TableDrop(OperatorDbAdapter.TableSetting, 60);
            query = String.format("CREATE TABLE \"%s\" ( \"id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"title\" TEXT NOT NULL DEFAULT '', \"descr\" TEXT NOT NULL DEFAULT '', \"val\" TEXT NOT NULL DEFAULT '' )", OperatorDbAdapter.TableSetting);
            this.ExecuteNonQuery(query, 60);
            //create index
            query = String.format("CREATE INDEX \"%s_ix_title\" ON \"%s\" (\"title\" ASC);", OperatorDbAdapter.TableSetting, OperatorDbAdapter.TableSetting);
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

    // === Places table function =============================
    
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

        String query = String.format("SELECT * FROM \"%s\";", OperatorDbAdapter.TablePlaces);
        ResultSet reader = this.ExecuteReader(query, this.m_Timeout);

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
        PreparedStatement ps = this.m_cmdAddPlace;

        // create if not exists
        if (ps == null)
        {
            String query = String.format("INSERT INTO \"%s\"(\"title\", \"type\", \"path\", \"descr\", \"syno\") VALUES (?,?,?,?,?);", OperatorDbAdapter.TablePlaces);
            ps = this.m_connection.prepareStatement(query);
            // set timeout here
            ps.setQueryTimeout(this.m_Timeout);
            // write back
            this.m_cmdAddPlace = ps;
        }

        // set parameters
        ps.setString(1, p.get_Title());
        ps.setString(2, p.get_PlaceTypeExpression());
        ps.setString(3, p.get_Path());
        ps.setString(4, p.get_Description());
        ps.setString(5, p.get_Synonim());

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
     * NT-Update Place
     * 
     * @param p
     *            Place object
     * @return Функция возвращает число измененных строк таблицы.
     * @throws Exception
     *             Ошибка при использовании БД.
     */
    public int UpdatePlace(Place p) throws Exception
    {
        PreparedStatement ps = this.m_cmdUpdatePlace;

        // create if not exists
        if (ps == null)
        {
            String query = String.format("UPDATE \"%s\" SET \"title\" = ?, \"type\" = ?, \"path\" = ?, \"descr\" = ?, \"syno\" = ?) WHERE(\"id\" = ?);", OperatorDbAdapter.TablePlaces);
            ps = this.m_connection.prepareStatement(query);
            // set timeout here
            ps.setQueryTimeout(this.m_Timeout);
            // write back
            this.m_cmdUpdatePlace = ps;
        }

        // set parameters
        ps.setString(1, p.get_Title());
        ps.setString(2, p.get_PlaceTypeExpression());
        ps.setString(3, p.get_Path());
        ps.setString(4, p.get_Description());
        ps.setString(5, p.get_Synonim());
        ps.setInt(6, p.get_TableId());

        int result = ps.executeUpdate();
        // Do not close command here - for next reusing

        return result;
    }

    // === Procedures table function ===================
    
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

        String query = String.format("SELECT * FROM \"%s\";", OperatorDbAdapter.TableProcs);
        ResultSet reader = this.ExecuteReader(query, this.m_Timeout);

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

        PreparedStatement ps = this.m_cmdAddProcedure;

        if (ps == null)
        {
            String query = String.format("INSERT INTO \"%s\"(\"title\", \"ves\", \"path\", \"regex\", \"descr\") VALUES (?,?,?,?,?);", OperatorDbAdapter.TableProcs);
            ps = this.m_connection.prepareStatement(query);
            // set timeout here
            ps.setQueryTimeout(this.m_Timeout);
            // write back
            this.m_cmdAddProcedure = ps;
        }

        // set parameters
        ps.setString(1, p.get_Title());
        ps.setDouble(2, p.get_Ves());
        ps.setString(3, p.get_Path());
        ps.setString(4, p.get_Regex());
        ps.setString(5, p.get_Description());

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
     * NT-Update Procedure
     * 
     * @param p
     *            Procedure object
     * @return Функция возвращает число измененных строк таблицы.
     * @throws Exception
     *             Ошибка при использовании БД.
     */
    public int UpdateProcedure(Procedure p) throws Exception
    {
        PreparedStatement ps = this.m_cmdUpdateProcedure;

        if (ps == null)
        {
            String query = String.format("UPDATE \"%s\" SET \"title\" = ?, \"ves\" = ?, \"path\" = ?, \"regex\" = ?, \"descr\" = ? WHERE (\"id\" = ?);", OperatorDbAdapter.TableProcs);
            ps = this.m_connection.prepareStatement(query);
            // set timeout here
            ps.setQueryTimeout(this.m_Timeout);
            // write back
            this.m_cmdUpdateProcedure = ps;
        }

        // set parameters
        ps.setString(1, p.get_Title());
        ps.setDouble(2, p.get_Ves());
        ps.setString(3, p.get_Path());
        ps.setString(4, p.get_Regex());
        ps.setString(5, p.get_Description());
        ps.setInt(6, p.get_TableId());

        int result = ps.executeUpdate();
        // Do not close command here - for next reusing

        return result;
    }

    // === Setting table function ====================================
    /**
     * NT- Получить все записи таблицы настроек Оператора
     * @return Функция возвращает все записи из ТаблицыНастроекОператора.
     * @throws SQLException Ошибка при использовании БД.
     */
    public LinkedList<SettingItem> GetAllSettings() throws SQLException
    {
        //SELECT * FROM `setting` WHERE (`id` = 1);
        
        LinkedList<SettingItem> list = new LinkedList<SettingItem>();

        String query = String.format("SELECT * FROM \"%s\";", OperatorDbAdapter.TableSetting);
        ResultSet reader = this.ExecuteReader(query, this.m_Timeout);

        while (reader.next())
        {
            SettingItem si = new SettingItem();
            si.setTableId(reader.getInt(1));
            si.setTitle(reader.getString(2));
            si.setDescription(reader.getString(3));
            si.setValue(reader.getString(4));
            
            //add to result list
            list.add(si);
        }

        // close command and result set objects
        reader.getStatement().close();

        return list;
        
    }
    /**
     * NT-Добавить Настройку.
     * @param item Добавляемая Настройка.
     * @throws SQLException Ошибка при использовании БД.
     */
    public void AddSetting(SettingItem item) throws SQLException
    {
        //INSERT INTO `setting` (`title`, `descr`, `val`) VALUES('t1', 'd1', 'v1');
        
        PreparedStatement ps = this.m_cmdAddSetting;

        if (ps == null)
        {
            String query = String.format("INSERT INTO \"%s\"(\"title\", \"descr\", \"val\") VALUES (?,?,?);", OperatorDbAdapter.TableSetting);
            ps = this.m_connection.prepareStatement(query);
            // set timeout here
            ps.setQueryTimeout(this.m_Timeout);
            // write back
            this.m_cmdAddSetting = ps;
        }

        // set parameters
        ps.setString(1, item.getTitle());
        ps.setString(2, item.getDescription());
        ps.setString(3, item.getValue());

        ps.executeUpdate();
        // Do not close command here - for next reusing

        return;
    }
    /**
     * NT-Удалить Настройку.
     * @param id ИД Настройки.
     * @return Функция возвращает число измененных строк таблицы.
     * @throws SQLException Ошибка при использовании БД.
     */
    public int RemoveSetting( int id) throws SQLException
    {
        //DELETE FROM `setting` WHERE (`id` = 1);
        return this.DeleteRow(OperatorDbAdapter.TableSetting, "id", id, this.m_Timeout);
    }
    /**
     * NT- Изменить Настройку (title, descr, value)
     * @param item Изменяемая Настройка
     * @return Функция возвращает число измененных строк таблицы.
     * @throws SQLException Ошибка при использовании БД.
     */
    public int UpdateSetting(SettingItem item) throws SQLException
    {
        //UPDATE `setting` SET `val` = 'new value', `descr` = 'new description'  WHERE (`id` = 1);
        
        PreparedStatement ps = this.m_cmdUpdateSetting;
        
        if (ps == null)
        {
            String query = String.format("UPDATE \"%s\" SET \"title\" = ?, \"descr\" = ?, \"val\" = ? WHERE (\"id\" = ?);", OperatorDbAdapter.TableSetting);
            ps = this.m_connection.prepareStatement(query);
            // set timeout here
            ps.setQueryTimeout(this.m_Timeout);
            // write back
            this.m_cmdUpdateSetting = ps;
        }
        
        // set parameters
        ps.setString(1, item.getTitle());
        ps.setString(2, item.getDescription());
        ps.setString(3, item.getValue());
        ps.setInt(4, item.getTableId());

        int result = ps.executeUpdate();
        // Do not close command here - for next reusing

        return result;
    }
    
    // ===  ============================================================
    
    
}
