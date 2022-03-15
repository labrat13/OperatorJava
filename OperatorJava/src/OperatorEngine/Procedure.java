/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Готов к отладке.
 */
package OperatorEngine;

/**
 * Класс Процедуры Оператора
 *
 * @author 1
 */
public class Procedure extends Item
{

    // #region Fields
    /**
     * порядковый номер проверки в очереди проверок для команды - для поддержки
     * очередность проверки выражений
     */
    private Double m_ves;

    /**
     * регулярное выражение - для проверки соответствия команды и процедуры.
     */
    private String m_regex;

    // #endregion
    /**
     * Стандартный конструктор
     */
    public Procedure()
    {

    }
    // #region *** Properties ***

    /**
     * порядковый номер проверки в очереди проверок для команды - для поддержки
     * очередность проверки выражений
     *
     * @return порядковый номер проверки в очереди проверок для команды - для
     *         поддержки очередность проверки выражений
     */
    public Double get_Ves()
    {
        return this.m_ves;
    }

    /**
     * порядковый номер проверки в очереди проверок для команды - для поддержки
     * очередность проверки выражений
     *
     * @param val
     *            порядковый номер проверки в очереди проверок для команды - для
     *            поддержки очередность проверки выражений
     */
    public void set_Ves(Double val)
    {
        this.m_ves = val;
    }

    /**
     * регулярное выражение - для проверки соответствия команды и процедуры. До
     * 255 символов.
     *
     * @return регулярное выражение - для проверки соответствия команды и
     *         процедуры. До 255 символов.
     */
    public String get_Regex()
    {
        return this.m_regex;
    }

    /**
     * регулярное выражение - для проверки соответствия команды и процедуры. До
     * 255 символов.
     *
     * @param val
     *            регулярное выражение - для проверки соответствия команды и
     *            процедуры. До 255 символов.
     */
    public void set_Regex(String val)
    {
        this.m_regex = val;
    }

    // #endregion
    @Override
    public String toString()
    {
        return this.getSingleLineProperties();
    }

    /// <summary>
    /// NT-Получить одну строку описания свойств Процедуры
    /// Для вывода списка Процедур в разных случаях работы программы
    /// </summary>
    /// <returns></returns>
    @Override
    public String getSingleLineProperties()
    {
        // Одна строка, 80 символов макс.
        StringBuilder sb = new StringBuilder();
        sb.append(this.m_id);
        sb.append(";");
        sb.append(this.m_title);
        sb.append(";ves=");
        sb.append(this.m_ves);
        sb.append(";path=");
        sb.append(this.m_path);
        sb.append(";");
        sb.append(this.m_descr);
        if (sb.length() > 80)
        {
            sb.setLength(80);
        }
        return sb.toString();
    }

    ///// <summary>
    ///// NT-Должна вернуть труе если запрос подходит под регекс
    ///// </summary>
    ///// <param name="cmdline">Текст запроса</param>
    ///// <returns></returns>
    // internal bool IsMatchProcedure(string cmdline)
    // {
    // String rx = null;
    // //получить тип регекса
    // RegexType rt = RegexManager.determineRegexType(this.Regex);
    // //конвертировать регекс в пригодный для исполнения
    // if (rt == RegexType.NormalRegex)
    // {
    // rx = String.Copy(this.Regex);
    // }
    // else if (rt == RegexType.SimpleString)
    // {
    // rx = RegexManager.ConvertSimpleToRegex2(this.Regex);
    // }
    // else throw new Exception(String.Format("Invalid regex string: {0} in
    ///// {1}", this.Regex, this.Title));
    // //выполнить регекс и вернуть результат проверки
    // bool res = RegexManager.IsMatchQuery(rx, cmdline);
    // return res;
    // }
    
    ///// <summary>
    ///// Должна вернуть облом при неподходящих параметрах, успех при
    ///// исполнении, выход если требуется завершение работы приложения или
    ///// компьютера
    ///// </summary>
    ///// <param name="cmdline"></param>
    ///// <returns></returns>
    // internal ProcedureResult Execute(string cmdline)
    // {
    // //надо определить, путь исполнения это путь к процедуре или к приложению.
    // //если к приложению, его надо запустить и все, вернуть стандартное
    ///// значение для продолжения работы.
    // //если к процедуре, надо приготовить аргументы, найти сборку, вызвать
    ///// функцию, передать ей аргументы и вернуть результат.
    // }
    // #region *** Assemblies loading *** - TODO: перенести функции в подходящий
    ///// класс
    // TODO: как это реализовать в Java и надо ли - как это обойти?
    /// <summary>
    /// NT-Get assembly file path for assembly loading
    /// </summary>
    /// <param name="assemblyName">assembly name without extension</param>
    /// <returns>full assmbly file path</returns>
    // public static String getAssemblyFilePath(String assemblyName) {
    // String asmPath =
    ///// System.IO.Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location);
    // asmPath = System.IO.Path.ChangeExtension(System.IO.Path.Combine(asmPath,
    ///// assemblyName), ".dll");
    // return asmPath;
    // }
    //
    
    
    // /// <summary>
    // /// NT-Load assembly and get MethodInfo of method implementation function
    // /// </summary>
    // /// <returns>MethodInfo object represent method code</returns>
    // public static MethodInfo getMethodInfo(string[] names) {
    // //get assembly pathname
    // String asmPath = getAssemblyFilePath(names[0]);
    // //load assembly
    // Assembly aa = Assembly.LoadFile(asmPath);
    // Type tt = aa.GetType(String.Format("{0}.{1}", names[0], names[1]));
    // if (tt == null) {
    // throw new Exception(String.Format("Класс {0} не найден в сборке {1}",
    ///// names[1], names[0])); //
    // }
    // MethodInfo m = tt.GetMethod(names[2]);
    // if (m == null) {
    // throw new Exception(String.Format("Процедура {0} не найдена в классе {1}
    ///// сборки {2}", names[2], names[1], names[0])); //
    // }
    // return m;
    // }
    //
    
    
    // /// <summary>
    // /// Get state of method implementation function
    // /// </summary>
    // /// <returns>One of implementation state values</returns>
    // public static ImplementationState getStateOfImplement(MethodInfo mi) {
    // ImplementationState ist = ImplementationState.NotRealized; //= метод не
    ///// пригоден для исполнения
    // try {
    // Object[] oo = mi.GetCustomAttributes(typeof(ProcedureAttribute), false);
    // if (oo.Length > 0) {
    // ist = ((ProcedureAttribute) oo[0]).ElementValue;
    // }
    // } catch (Exception) {
    // //любое исключение показывает что метод не пригоден для исполнения
    // }
    // return ist;
    // }
    //
    
    
     /// <summary>
     /// NT-Запустить процедуру
     /// </summary>
     /// <param name="command">Текст команды пользователя</param>
     /// <param name="names">Путь к процедуре</param>
     /// <param name="args">Готовый для применения список аргументов</param>
     /// <returns></returns>
     public EnumProcedureResult invokeProcedure(String command, String[] names, Engine engine, ArgumentCollection args) 
     {
//     //получить сборку и метод в ней
//     MethodInfo mi = getMethodInfo(names);
//     //проверить готовность кода процедуры
//     if (getStateOfImplement(mi) == ImplementationState.NotRealized) 
//     {
//          throw new Exception(String.Format("Процедура {0}.{1}.{2} не готова для исполнения.", names[0], names[1], names[2]));
//     }
//     //загрузить в нее аргументы
//     //make arguments array
//     List<Object> li = new List<object>();
//     li.Add(engine);//Engine object
//     li.Add(command);//user command text
//     li.Add(args);//Argument collection
//     //запустить метод
//     Object resval = mi.Invoke(null, li.ToArray());
//     //вернуть результат
//     return (EnumProcedureResult) resval;
         
     }

    // #endregion
    

    /**
     * NT-Проверить на допустимость значение Вес Процедуры, введенное
     * пользователем.
     *
     * @param str
     *            Текстовое значение веса
     * @return Возвращает true если значение допустимо в качестве Веса
     *         Процедуры, false в противном случае.
     */
    public static boolean IsValidVesFormat(String str)
    {
        boolean result = false;
        try
        {
            // это должно парситься в Double, быть меньше 1 и больше 0
            double d = Double.parseDouble(str);
            if ((d > 0.0d) && (d < 1.0d))
            {
                result = true;
            }
        }
        catch (Exception e)
        {
            result = false;
        }
        return result;
    }

}
