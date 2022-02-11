/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Feb 11, 2022 2:36:55 AM - Готов к отладке
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
     * @return
     */
    public FuncArgument GetByName(String argname)
    {
        for (FuncArgument f : m_args)
        {
            if (argname.equalsIgnoreCase(f.get_ArgumentName())) return f;
        }
        return null;
    }
}
