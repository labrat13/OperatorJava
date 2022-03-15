/**
 * @author Селяков Павел
 * Created: Mar 14, 2022 8:21:10 PM
 * State: Mar 14, 2022 8:21:10 PM - initial
 */
package ProcedureSubsystem;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;

import OperatorEngine.ArgumentCollection;
import OperatorEngine.Engine;
import OperatorEngine.EnumProcedureResult;
import OperatorEngine.FileSystemManager;
import OperatorEngine.Procedure;
import OperatorEngine.Utility;

/**
 * NR-Менеджер  подсистемы исполнения Процедур
 * @author Селяков Павел
 *
 */
public class ProcedureExecutionManager
{
    //TODO: что если тут хранить не пути к файлам сборок Процедур, а объекты менеджеров LibraryManager?
    // а пути к сборкам процедур хранить в этих объектах. 
    //Тогда и объекты в памяти остаются - и данные инициализации в памяти остаются на время хранения объекта.
    // - пока непонятно, что там должно храниться в памяти, и как что выгружается сборщиком мусора.
    
     
    
    /**
     * Engine backreference
     */
    protected Engine m_Engine; 
    /**
     * Словарь (Сборка,Путь) для путей к библиотекам.
     */
    protected HashMap<String, String> m_Libraries;
    
    /**
     * Constructor
     */
    public ProcedureExecutionManager(Engine engine)
    {
        this.m_Engine = engine;
    }
    
    /**
     * NR-Initialize Execution manager
     */
    public void Open()
    {
        //find all libraries
        this.m_Libraries = findLibraries();
        //TODO: init all libraries
      //TODO: тут нужен код для загрузки менеджеров библиотек из этих библиотек.
        //тут нужен код для выгрузки Процедур и Мест из кажддой библиотеки в Оператор. Но не в БД, а в общий такой буфер для них.
        //Поверх CachedDbAdapter такой буфер нужно добавить в Движок Оператора.
        return;
    }
    


    /**
     * NR-Close execution manager
     */
    public void Close()
    {
        //TODO: free all libraries
        
        return;
    }

    /** 
     * NT-Fill dictionary with jar files pathes
     * @return Function returns dictionary (title, path) of each jar file in main libraries folder.
     */
    private HashMap<String, String> findLibraries()
    {
        // TODO Тут jar файлы библиотек процедур должны быть сложены непосредственно в каталог Процедур, а не как положено.
        //Надо переделать этот код после отладки.
        
        //создать словарь название библиотеки -путь к jar-файлу библиотеки.
        HashMap<String, String> result = new HashMap<String, String>();
        //получить путь к корневой папке хранилища библиотек
        String libraryFolder = FileSystemManager.getAssembliesFolderPath();
        //сейчас просто извлечь все jar-файлы из этой папки, но не подпапок, 
        //хотя это неправильно, но для отладки на первое время сойдет.
        //TODO:  переделать после отладки код здесь на правильную структуру папок и файлов Процедур.
        //а правильно - каждый файл в свою одноименную папку помещать 
        //так как там еще должны быть всякие папки и файлы ресурсов потом.
        File libFolder = new File(libraryFolder);
        //собираем файлы только в указанном каталоге, но не в подкаталогах.
        File[] files = FileSystemManager.getDirectoryFiles(libFolder, new String[] {".jar", ".JAR" });
        //извлечь имя файла без расширения и путь к файлу и поместить в словарь
        for(File f : files)
        {
            String filetitle = f.getName();
            filetitle = Utility.getFileExtension(filetitle);
            String path = f.getAbsolutePath();
            
            result.put(filetitle, path);
        }
        
        return result;
    }
    
    /** NR-Invoke procedure method
     * @param p         Procedure for execution
     * @param command   Query text string
     * @param names     assembly.class.method titles array
     * @param engine    Engine object reference
     * @param args      Procedure arguments array
     */
    public void invokeProcedure(Procedure p, String command, String[] names, Engine engine, ArgumentCollection args)
    {
        // TODO Add code here
        //1. Получить абсолютный путь к JAR файлу сборки.
        // Файл должен называться по names[0] и иметь расширение jar.
        // Путь к файлу следует получить из HashMap по ключу names[0].
        //TODO: сначала надо заполнить HashMap <String, String> парами <AssemblyTitle, JarFilePath> в процедуре Open().
        // - Для этого нужен путь к каталогу Оператор и подкаталогу Сборок Процедур в нем. Он должен быть в FileSystemManager классе.
        
        //а далее - по коду из прототипа ниже
        
    }
    
    
//    /**
//     * NR-Test of invoking Procedure from TestProcLib.Procedures.testProcedure()
//     * 
//     * @param text
//     *            Text query
//     * @param engine
//     *            Engine object for console access test
//     * @param args
//     *            List of arguments for Procedure
//     * @return Returns a code returned by called procedure
//     * @throws Exception 
//     */
//    public static int invokeProcedure(String text, Engine engine, LinkedList<String> args) throws Exception
//    {
//        // TODO: add code here
//        String AssemblyTitle = "TestProcLib";
//        String ClassTitle = "Procedures";
//        String MethodTitle = "testProcedure";
//        // 1. load class from specified JarFile
//
//        // 2. if jarfile not marked attribute, stop work
//        // 3. load class
//        //TODO: как загрузить класс из TestProcLib?
//        // - Это надо прицепить ее к этому проекту.
//        //   - но нельзя - классы должны загружаться из сборки AssemblyTitle.jar, находящейся в этом же либо другом каталоге.
//        //     - так надо сначала собрать сборку в jar файл и поместить в правильный каталог. А пока ее там нет - вот оно и выдает ошибку.
//        //       А правильный каталог на время теста назначить /home/smith/
//        //       А для Оператора - либо каталог Оператора, либо подкаталог по AssemblyTitle.
//        // TODO: Добавить в задачи по Оператору, что нужна процедура поиска сборок Процедур и внесения в список Сборок процедур, чтобы пути быстро находить.
//        //  Это все надо делать при запуске Оператора, заодно собирать из этих сборок данные о Командах и вносить их в общий список Процедур Оператора.
//        // Для этого явно потребуется отдельная подсистема для управления Сборками Процедур.
//        
//        //Class<?> cls = Class.forName("TestProcLib.Procedures"); - class not found exception
//        
//        URL classUrl = new URL("file:///home/jsmith/Documents/TestProcLib.jar");
//        URL[] classUrls = { classUrl };
//        URLClassLoader ucl = new URLClassLoader(classUrls);
//        Class<?> cls = ucl.loadClass("Procedures"); 
//        
//                
//        printClassInfo(cls);
//        
//        //Package annotation check
//        Package p = cls.getPackage();
//        //может не быть корневого пакета
//        if(p != null)
//        {
//            OperatorProcedure packageAnnot = p.getAnnotation(OperatorProcedure.class);
//            if(packageAnnot.State() == OperatorProcedure.ImplementationState.NotRealized)
//                throw new Exception("Specified package marked as ImplementationState.NotRealized.");
//        }
//        // 4. if class not annotated, stop work
//        if(!cls.isAnnotationPresent(OperatorProcedure.class))
//            throw new Exception("Specified class not marked with OperatorProcedure annotation.");
//        // 5. find method
//        OperatorProcedure annot = cls.getAnnotation(OperatorProcedure.class);
//        OperatorProcedure.ImplementationState state = annot.State();
////      String class_title = annot.Title();
////      String class_description = annot.Description();
//        if(state == OperatorProcedure.ImplementationState.NotRealized)
//            throw new Exception("Specified class marked as ImplementationState.NotRealized.");
//        //get method       
//        Method m = cls.getMethod(MethodTitle, String.class, Engine.class, LinkedList.class);
//        //throw NoSuchMethodException if cannot find method
//        OperatorProcedure annot2 = m.getAnnotation(OperatorProcedure.class);
//        if(annot2 == null)
//            throw new Exception("Specified method not marked with OperatorProcedure annotation.");
//        if(annot2.State() == OperatorProcedure.ImplementationState.NotRealized)
//            throw new Exception("Specified method marked as ImplementationState.NotRealized.");
//        if(annot2.State() == OperatorProcedure.ImplementationState.NotTested)
//        {
//          //TODO: print warning "This Procedure method  %s marked as not tested"
//            //запросить у пользователя подтверждение на запуск процедуры, помеченной как требующая отладки.
//        }
////      String method_title = annot2.Title();
////      String method_description = annot2.Description();
//        // 6. if method not marked attribute, stop work
////        if(m == null)
////            throw new Exception("Specified method not found");
//        
//        // 7. execute method
////        Object[] arguments = new Object[3];
////        arguments[0] = text;
////        arguments[1] = engine;
////        arguments[2] = args;
//        @SuppressWarnings("unused")
//        Object returned = m.invoke(null, text, engine, args);
//        // 8. return result
//        if(returned == null)
//            throw new Exception("Error: Method returns null.");
//        
//        return (int) returned;
//    }
    
    /// <summary>
    /// NT-Запустить процедуру
    /// </summary>
    /// <param name="command">Текст команды пользователя</param>
    /// <param name="names">Путь к процедуре</param>
    /// <param name="args">Готовый для применения список аргументов</param>
    /// <returns></returns>
    // public EnumProcedureResult invokeProcedure(String command, String[] names, Engine engine, ArgumentCollection args) 
    // {
//    //получить сборку и метод в ней
//    MethodInfo mi = getMethodInfo(names);
//    //проверить готовность кода процедуры
//    if (getStateOfImplement(mi) == ImplementationState.NotRealized) 
//    {
//         throw new Exception(String.Format("Процедура {0}.{1}.{2} не готова для исполнения.", names[0], names[1], names[2]));
//    }
//    //загрузить в нее аргументы
//    //make arguments array
//    List<Object> li = new List<object>();
//    li.Add(engine);//Engine object
//    li.Add(command);//user command text
//    li.Add(args);//Argument collection
//    //запустить метод
//    Object resval = mi.Invoke(null, li.ToArray());
//    //вернуть результат
//    return (EnumProcedureResult) resval;
        
    //}
    
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
    
    private static void printClassInfo(Class<?> c)
    {
        if(c == null) 
        {
            System.out.println("PrintClassInfo: Class = null");
            return;
        }
        System.out.println("Class information:");
        System.out.println("getCanonicalName(): " + c.getCanonicalName());
        System.out.println("getName():" + c.getName());
        System.out.println("getSimpleName():" + c.getSimpleName());
        System.out.println("getTypeName():" + c.getTypeName());
        System.out.println("toGenericString():" + c.toGenericString());
        System.out.println("toString():" + c.toString());
        printAnnotations(c.getAnnotations());
        printMethodsInfo(c.getMethods());
        printPackageInfo(c.getPackage());
        
        return;
    }

    private static void printMethodsInfo(Method[] mar)
    {
        System.out.println("Methods information:");
        if (mar.length == 0)
            System.out.println("   No methods.");
        else
        {
        for(Method m : mar)
        {
            System.out.println("getName(): " + m.getName());
            System.out.println("toString(): " + m.toString());
            printAnnotations(m.getAnnotations());
            System.out.println("");
        }
        }
        
        return;
    }
    
    private static void printPackageInfo(Package p)
    {
        if(p == null) 
        {
            System.out.println("PrintPackageInfo: Package = null");
            return;
        }
        System.out.println("Package information:");
        System.out.println("getName(): " + p.getName());
        System.out.println("getImplementationTitle(): " + p.getImplementationTitle());
        System.out.println("getImplementationVendor(): " + p.getImplementationVendor());      
        System.out.println("getImplementationVersion(): " + p.getImplementationVersion());
        System.out.println("getSpecificationTitle(): " + p.getSpecificationTitle());
        System.out.println("getSpecificationVendor(): " + p.getSpecificationVendor());
        System.out.println("getSpecificationVersion(): " + p.getSpecificationVersion());
        System.out.println("toString(): " + p.toString());
        printAnnotations(p.getAnnotations());
        System.out.println("");
        return;
    }
    
    /**
     * @param annotations
     */
    private static void printAnnotations(Annotation[] annotations)
    {
        System.out.println("Annotation information:");
        if (annotations.length == 0)
            System.out.println("   No annotations.");
        else
        {
            for (Annotation a : annotations)
                System.out.println(a.toString());
        }
        System.out.println("");
        return;
    }

    
    
}
