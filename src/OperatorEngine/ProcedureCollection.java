/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Feb 6, 2022 4:59:55 AM - готов к отладке
 */
package OperatorEngine;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Список процедур
 * 
 * @author 1
 */
public class ProcedureCollection
{

    /**
     * Список объектов процедур
     */
    private LinkedList<Procedure> m_proclist;

    /**
     * Default constructor
     */
    public ProcedureCollection()
    {
        this.m_proclist = new LinkedList<Procedure>();
    }

    /**
     * Список объектов процедур
     * 
     * @return Список объектов процедур
     */
    public LinkedList<Procedure> get_Procedures()
    {
        return this.m_proclist;
    }

    // TODO: Процедуры более не должны создаваться из кода.
    // TODO: Проверить, что Процедуры в коллекции сортируются по Весу после
    // заполнения коллекции!

    // /// <summary>
    // /// NR-Заполнить эту коллекцию процедурами вручную, описав их в коде
    // /// </summary>
    // internal void FillHardcodedProcedures()
    // {
    // //Если нужно добавлять процедуры вручную, рекомендуется добавлять
    // процедуры прямо в БД
    // //DONE: Заполнить эту коллекцию процедурами вручную, описав их в коде
    // //TODO: Описать правила заполнения полей объекта, привести ссылки на
    // форматы, документацию.
    //
    // //Procedure p;
    //
    // //Это шаблон, образец, не трогать его!
    //
    // //p = new Procedure();
    // ////заполнить вручную поля
    // //p.Title = "";//Название процедуры, в работе механизма не используется
    // //p.Regex = "";//Регулярное выражение, простое или сложное.
    // //p.ExecutionPath = "";//Путь к функции в сборке или к приложению, может
    // содержать аргументы.
    // //p.Descr = "";//Текст описания сущности, не обязателен, в работе
    // механизма не используется.
    // //p.Ves = 0.00;//Вес процедуры в пределах от 0 (раньше всех в очереди) до
    // 1 (позже всех в очереди)
    // ////добавление в коллекцию
    // //this.m_proclist.Add(p);
    //
    // ///////////////////////////////////////////////////////////////////////////////
    //
    // this.m_proclist.Sort(Procedure.SortByVes);
    //
    // return;
    // }

    /**
     * Fill collection from database
     * 
     * @param list
     *            List of Procedure from database
     */
    public void FillFromDb(LinkedList<Procedure> list)
    {
        this.m_proclist.addAll(list);
        // сортировать процедуры по весу обязательно, иначе команды будут
        // исполняться не по их весу.
        SortByVes(this.m_proclist);

        return;
    }

    /**
     * NT- Сортировать процедуры по возрастанию веса
     * 
     * @param list
     *            List of procedures for sorting
     */
    public static void SortByVes(LinkedList<Procedure> list)
    {
        if (list.size() > 1)
        {
            Collections.sort(list, new Comparator<Procedure>()
            {

                @Override
                public int compare(Procedure u1, Procedure u2)
                {
                    return u1.get_Ves().compareTo(u2.get_Ves());
                }
            });
        }

        return;
    }

    /**
     * NT-Очистить коллекцию
     */
    public void Clear()
    {
        this.m_proclist.clear();
    }

    /**
     * NT-Выбрать из БД Процедуры по названию, без учета регистра символов
     * 
     * @param title
     *            Название Процедуры
     * @return Возвращает список Процедур с указанным названием
     */
    public LinkedList<Procedure> getByTitle(String title)
    {
        LinkedList<Procedure> result = new LinkedList<Procedure>();
        for (Procedure p : this.m_proclist)
        {
            if (title.equalsIgnoreCase(p.get_Title())) result.add(p);
        }

        return result;
    }
}