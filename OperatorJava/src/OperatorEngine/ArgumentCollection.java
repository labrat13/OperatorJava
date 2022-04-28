/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Mar 21, 2022 12:37:20 AM - Ported, Готов к отладке.
 */
package OperatorEngine;

import java.util.LinkedList;

/**
 *
 * @author 1
 */
public class ArgumentCollection
{

    /**
     * Список аргументов коллекции
     */
    private LinkedList<FuncArgument> m_args;

    /**
     * NT-Default constructor
     */
    public ArgumentCollection()
    {
        m_args = new LinkedList<FuncArgument>();
    }

    /**
     * NT-Список аргументов коллекции
     * 
     * @return Список аргументов коллекции
     */
    public LinkedList<FuncArgument> get_Arguments()
    {
        return this.m_args;
    }

    /**
     * NT-Список аргументов коллекции
     * 
     * @param val
     *            Список аргументов коллекции
     */
    public void set_Arguments(LinkedList<FuncArgument> val)
    {
        this.m_args = val;
    }

    /**
     * NT-Добавить аргумент в коллекцию
     * 
     * @param f
     *            Объект аргумента
     */
    public void Add(FuncArgument f)
    {
        m_args.add(f);
    }

    /**
     * NT-Получить первый найденный объект аргумента по его названию, null если
     * не найдено. Сравнение без учета регистра.
     * 
     * @param argname
     *            Название аргумента
     * @return Функция возвращает объект аргумента
     */
    public FuncArgument getByName(String argname)
    {
        for (FuncArgument f : m_args)
        {
            if (argname.equalsIgnoreCase(f.get_ArgumentName()))
                return f;
        }
        return null;
    }

    /** 
     * NT-Получить объект аргумента по его индексу в списке.
     * @param i Индекс элемента в списке.
     * @return Функция возвращает объект аргумента, находящийся по указанному индексу в списке.
     */
    public FuncArgument getByIndex(int i)
    {
        return this.m_args.get(i);
    }
}
