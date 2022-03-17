/**
 * @author Селяков Павел
 * Created: Mar 17, 2022 11:56:12 PM
 * State: Mar 17, 2022 11:56:12 PM - initial
 */
package OperatorEngine;

import java.sql.SQLException;
import java.util.LinkedList;

import DbSubsystem.OperatorDbAdapter;
import DbSubsystem.PlacesCollection;
import DbSubsystem.ProcedureCollection;
import ProcedureSubsystem.ProcedureExecutionManager;

/**
 * NR-Менеджер кэша Процедур и Мест, загружает их из БД и Библиотек Процедур.
 * Замена CachedDbAdapter по причине введения новой архитектуры Сборок Процедур.
 * Теперь он должен собирать и хранить Места и Процедуры и принимать вызовы операций с ним, 
 *  перенаправляя их менеджерам соответствующих подсистем и библиотек.
 * @author Селяков Павел
 *
 */
public class ElementCacheManager
{
    
    // TODO: Следует переделать этот класс так, чтобы он открывал БД только на
    // время чтения или записи, а не держал ее постоянно открытой.
    // Так меньше возможность повредить бд при глюках линукса.
    
    // Constants and Fields ==============================
    /**
     * Engine backreference
     */
    protected Engine m_Engine;
    /**
     * Db adapter backreference.
     */
    protected OperatorDbAdapter m_db;
    /**
     * PEM backreference.
     */
    protected ProcedureExecutionManager m_PEM;
    
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

    // Constructors =============================
    
/**
 * NT- Constructor
 * @param engine Engine backreference.
 * @param db  Db adapter backreference.
 * @param pem PEM backreference.
 */
    public ElementCacheManager(Engine engine, OperatorDbAdapter db, ProcedureExecutionManager pem)
    {
        this.m_Engine = engine;
        this.m_db = db;
        this.m_PEM = pem;
        //create collection objects
        this.m_places = new PlacesCollection();
        this.m_procedures = new ProcedureCollection();
        
        return;
    }
    
    
    /**
     * NT-Список процедур, все процедуры держим здесь в памяти.
     * 
     * @return the procedures
     */
    public ProcedureCollection get_Procedures()
    {
        return m_procedures;
    }

    /**
     * NT-Список процедур, все процедуры держим здесь в памяти.
     * 
     * @param procedures
     *            the procedures to set
     */
    public void set_Procedures(ProcedureCollection procedures)
    {
        this.m_procedures = procedures;
    }

    /**
     * NT-Список мест, все места держим здесь в памяти.
     * 
     * @return the places
     */
    public PlacesCollection get_Places()
    {
        return m_places;
    }

    /**
     * NT-Список мест, все места держим здесь в памяти.
     * 
     * @param places
     *            the places to set
     */
    public void set_Places(PlacesCollection places)
    {
        this.m_places = places;
    }
    
    /**
     * NT-Get string representation of object.
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        int procCount = 0;
        if (this.m_procedures != null) procCount = this.m_procedures.getCount();
        int placeCount = 0;
        if (this.m_places != null) placeCount = this.m_places.getCount();
        String result = String.format("ElementCacheManager; procedures=%d; places=%d;",  procCount, placeCount);

        return result;
    }
    
    
    /**
     * NR-Заполнить кеши элементов и подготовить к работе.
     * 
     * @throws Exception
     *             Ошибка.
     */
    public void Open() throws Exception
    {
        throw new Exception("Function not implemented"); //TODO: add code here
//        this.m_places.Clear();
//        this.m_procedures.Clear();

//        reloadProcedures();
//        reloadPlaces();

        //return;
    }

    /**
     * NR-Завершить работу и сбросить кеши элементов
     * 
     * @throws Exception
     *             Ошибка при использовании БД.
     */
    public void Close() throws Exception
    {
        this.m_places.Clear();
        this.m_procedures.Clear();
        throw new Exception("Function not implemented"); //TODO: add code here

        //return;
    }
    
    //==== Procedure function ==============================
    /**
     * NR-Добавить Процедуру в БД и обновить кеш процедур
     * 
     * @param p
     *            Procedure for adding to database
     * @throws Exception
     *             Ошибка при использовании БД.
     */
    public void AddProcedure(Procedure p) throws Exception
    {
        throw new Exception("Function not implemented"); //TODO: add code here
//        // Добавить объект в БД
//        super.AddProcedure(p);
//        // reload cache
//        reloadProcedures();

//        return;
    }

    /**
     * NR-Добавить несколько Процедур в БД и обновить кеш процедур
     * 
     * @param procedures
     *            Список заполненных Процедур
     * @throws Exception
     *             Ошибка при использовании БД.
     */
    public void AddProcedure(LinkedList<Procedure> procedures) throws Exception
    {
        throw new Exception("Function not implemented"); //TODO: add code here
//        // Добавить объект в БД
//        for (Procedure p : procedures)
//            super.AddProcedure(p);
//        // reload cache
//        reloadProcedures();
//
//        return;
    }

    /**
     * NR-Remove Procedure from database
     * 
     * @param p
     *            Procedure for remove
     * @throws Exception
     *             Ошибка при использовании БД.
     */
    public void RemoveProcedure(Procedure p) throws Exception
    {
        throw new Exception("Function not implemented"); //TODO: add code here
//        this.RemoveProcedure(p.get_TableId());
//        this.reloadProcedures();
    }
    /**
     * NR-Update Procedure in database
     * 
     * @param p
     *            Procedure for update
     * @throws Exception
     *             Ошибка при использовании БД.
     */
    public void UpdateProcedure(Procedure p) throws Exception
    {
        throw new Exception("Function not implemented"); //TODO: add code here
        
    }
    
    
  //==== Place function ==============================
    /**
     * NR-Добавить новое место и обновить кеш мест
     * 
     * @param p
     *            Добавляемое место
     * @throws Exception
     *             Ошибка при использовании БД.
     */
    public void AddPlace(Place p) throws Exception
    {
        throw new Exception("Function not implemented"); //TODO: add code here
//        super.AddPlace(p);
//        // update cache
//        reloadPlaces();

//        return;
    }

    /**
     * NR-Добавить несколько Мест в БД и обновить кеш мест
     * 
     * @param places
     *            Список заполненных Мест
     * @throws Exception
     *             Ошибка при использовании БД.
     */
    public void AddPlace(LinkedList<Place> places) throws Exception
    {
        throw new Exception("Function not implemented"); //TODO: add code here
//        // Добавить объекты в БД
//        for (Place p : places)
//            super.AddPlace(p);
//        // update cache
//        reloadPlaces();

//        return;
    }
    
    /**
     * NR-Remove Place from database
     * 
     * @param p
     *            Place for remove.
     * @throws Exception
     *             Ошибка при исполнении.
     */
    public void RemovePlace(Place p) throws Exception
    {
        throw new Exception("Function not implemented"); //TODO: add code here

    }
    
    /**
     * NR-Update Place in database
     * 
     * @param p
     *            Place for update.
     * @throws Exception
     *             Ошибка при исполнении.
     */
    public void UpdatePlace(Place p) throws Exception
    {
        throw new Exception("Function not implemented"); //TODO: add code here

    }
    
    //========= Reloading functions ========================= 
    /**
     * NR-Перезагрузить кеш-коллекции мест данными из источника.
     * Чтобы они соответствовали содержимому источника, если он был изменен.
     * 
     * @throws Exception
     *             Ошибка при использовании БД.
     */
    protected void reloadPlaces() throws  Exception
    {
        throw new Exception("Function not implemented"); //TODO: add code here
//        // тут заполнить коллекцию мест данными мест.
//        this.m_places.Clear();
//        this.m_places.FillFromDb(GetAllPlaces());
//
//        return;
    }

    /**
     * NR-Перезагрузить кеш-коллекции процедур данными из источника.
     * Чтобы они соответствовали содержимому источника, если он был изменен.
     * 
     * @throws Exception
     *             Ошибка при использовании БД.
     */
    protected void reloadProcedures() throws Exception
    {
        throw new Exception("Function not implemented"); //TODO: add code here
//        // тут заполнить коллекцию процедур данными процедур. И не забыть их
//        // отсортировать по весу.
//        this.m_procedures.Clear();
//        this.m_procedures.FillFromDb(GetAllProcedures());
//
//        return;
    }
    
    

}
