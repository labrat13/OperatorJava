/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Feb 11, 2022 2:36:55 AM - TODO: Переделать под новую модель БД и адаптер для нее.
 */
package DbSubsystem;

import java.sql.SQLException;
import java.util.LinkedList;

import OperatorEngine.Place;
import OperatorEngine.PlacesCollection;
import OperatorEngine.Procedure;
import OperatorEngine.ProcedureCollection;

/**
 * Адаптер БД с кешем хранящихся элементов
 * 
 * @author 1
 */
public class CachedDbAdapter extends SqliteDbAdapter
{

    /**
     * Список процедур, все процедуры держим здесь в памяти. Они примерно будут
     * занимать до 1 мб на 1000 процедур.
     */
    private ProcedureCollection m_procedures;

    /**
     * Список мест, все места держим здесь в памяти. Они будут мало памяти
     * занимать, наверно..
     */
    private PlacesCollection    m_places;

    /**
     * NR-Default constructor
     * @throws SQLException Ошибка при использовании БД.
     * @throws ClassNotFoundException Ошибка при использовании БД.
     */
    public CachedDbAdapter() throws ClassNotFoundException, SQLException
    {
        super();
        this.m_places = new PlacesCollection();
        this.m_procedures = new ProcedureCollection();
    }

/** 
 * NT-Список процедур, все процедуры держим здесь в памяти.
     * @return the procedures
     */
    public ProcedureCollection get_Procedures()
    {
        return m_procedures;
    }

    /** 
     * NT-Список процедур, все процедуры держим здесь в памяти.
     * @param procedures the procedures to set
     */
    public void set_Procedures(ProcedureCollection procedures)
    {
        this.m_procedures = procedures;
    }

/** 
 * NT-Список мест, все места держим здесь в памяти.
     * @return the places
     */
    public PlacesCollection get_Places()
    {
        return m_places;
    }

    /** NT-Список мест, все места держим здесь в памяти.
     * @param places the places to set
     */
    public void set_Places(PlacesCollection places)
    {
        this.m_places = places;
    }


    // #region Main functions

    /**
     * NT-Открыть ранее закрытое соединение И перезагрузить кеши элементов. Это
     * перегруженная функция, и она вызывается и из кода родительского класса
     * тоже.
     * @throws Exception Ошибка при использовании БД.
     */
    @Override
    public void Open() throws Exception
        {
            this.m_places.Clear();
            this.m_procedures.Clear();
            super.Open();
            reloadProcedures();
            reloadPlaces();

            return;
        }

    /**
     * NT-Закрыть текущее соединение И сбросить кеши элементов
     * @throws SQLException Ошибка при использовании БД.
     */
    @Override
    public void Close() throws SQLException
        {
            this.m_places.Clear();
            this.m_procedures.Clear();
            super.Close();

            return;
        }

    /**
     * Добавить новое место и обновить кеш мест
     * 
     * @param p
     *            Добавляемое место
     * @throws Exception Ошибка при использовании БД.
     */
    @Override
    public void AddPlace(Place p) throws Exception
        {
            super.AddPlace(p);
            //update cache
            reloadPlaces();

            return;
        }

    /**
     *  NT-Добавить несколько Мест в БД и обновить кеш мест
     * @param places Список заполненных Мест
     * @throws Exception Ошибка при использовании БД.
     */
    public void AddPlace(LinkedList<Place> places) throws Exception
        {
            //Добавить объекты в БД
            for (Place p : places)
                super.AddPlace(p);
            //update cache
            reloadPlaces();

            return;
        }

    /**
     * NT-Remove Place from database
     * @param p Place for removing
     * @throws Exception Ошибка при использовании БД.
     */
    public void RemovePlace(Place p) throws Exception
    {
        this.RemovePlace(p.get_TableId());
        this.reloadPlaces();
    }

    /**
     * NT-Добавить Процедуру в БД и обновить кеш процедур
     * @param p Procedure for adding to database
     * @throws SQLException Ошибка при использовании БД.
     */
    @Override
    public void AddProcedure(Procedure p) throws SQLException
        {
            //Добавить объект в БД
            super.AddProcedure(p);
            //reload cache
            reloadProcedures();

            return;
        }

    /**
     * NT-Добавить несколько Процедур в БД и обновить кеш процедур
     * @param procedures Список заполненных Процедур
     * @throws SQLException Ошибка при использовании БД.
     */
    public void AddProcedure(LinkedList<Procedure> procedures) throws SQLException
        {
            //Добавить объект в БД
            for (Procedure p : procedures)
                super.AddProcedure(p);
            //reload cache
            reloadProcedures();

            return;
        }
    /**
     * NT-Remove Procedure from database
     * @param p Procedure for removing
     * @throws SQLException Ошибка при использовании БД.
     */
    public void RemoveProcedure(Procedure p) throws SQLException
    {
        this.RemoveProcedure(p.get_TableId());
        this.reloadProcedures();
    }

    // #endregion

    // #region Service functions

    /**
     * NT-Перезагрузить кеш-коллекции мест данными из БД.
     * Чтобы они соответствовали содержимому БД, если она была изменена.
     * @throws Exception Ошибка при использовании БД.
     * @throws SQLException Ошибка при использовании БД.
     */
    private void reloadPlaces() throws SQLException, Exception
    {
        // тут заполнить коллекцию мест данными мест.
        this.m_places.Clear();
        this.m_places.FillFromDb(GetAllPlaces());

        return;
    }


    /**
     * NT-Перезагрузить кеш-коллекции процедур данными из БД.
     * Чтобы они соответствовали содержимому БД, если она была изменена.
     * @throws SQLException Ошибка при использовании БД.
     */
    private void reloadProcedures() throws SQLException
        {
            //тут заполнить коллекцию процедур данными процедур. И не забыть их отсортировать по весу.
            this.m_procedures.Clear();
            this.m_procedures.FillFromDb(GetAllProcedures());

            return;
        }
}
// #endregion
