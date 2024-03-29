package cum.jesus.ctni;

import cum.jesus.ctni.exception.BadHandleException;
import cum.jesus.ctni.exception.SecurityException;

import java.util.List;

/**
 * This interface provides functions to interact with parts of the virtual machine with ease.
 * This includes several functions to find modules and existing functions in the vm, calling functions using java type parameters to avoid confusion
 * and utilities to create new data in the vm. <br>
 * This interface is implemented and provided by the vm to native functions.
 * <br><br>
 * The main way this passes around values is by using {@link cum.jesus.ctni.Handle}s as they act as a better abstraction than entire vm data classes.
 *
 * @author JesusTouchMe
 * @see Handle
 * @see NativeFunction
 * @since 1.0
 */
public interface IEnvironment {
    /**
     * Searches the vm for a loaded module matching the given name.
     * The name is a case-sensitive string.
     *
     * @param name the case-sensitive name of the module to find
     * @return the handle pointing to the module or null if no module with the given name is loaded
     * @see #GetModule()
     * @since 1.0
     */
    Handle GetModule(String name);

    /**
     * Will get the module which loaded the current library.
     *
     * @return the handle pointing to the module which is never null
     * @see #GetModule(String)
     * @since 1.0
     */
    Handle GetModule();

    /**
     * Will simply get a preview of the latest error in the vm's diagnostics system.
     * This won't remove the error like {@link #GetLatestError()}, meaning the programmer should not attempt to solve this without getting it first.
     *
     * @return a diagnostics report for preview which is still stored in the vm or null if no errors are present
     * @see DiagnosticReport
     * @see #GetLatestError()
     * @see #GetAllErrors()
     * @since 1.0
     */
    DiagnosticReport PreviewLatestError();

    /**
     * Will release this report from the vm's diagnostics and return it for fixing  or disposal.
     * This can return null if there's no errors present in the vm at the time of calling.
     *
     * @return the latest error or null if none are present
     * @since 1.0
     */
    DiagnosticReport GetLatestError();

    /**
     * Will release all errors from the vm and provide them in a neat array for handling them one at a time.
     * This will never return null, instead it returns a {@code new DiagnosticReport[0]} if no errors are present.
     *
     * @return Always an array containing all the errors from the vm (even if there's 0)
     * @since 1.0
     */
    DiagnosticReport[] GetAllErrors();

    /**
     * Searches for a function within the module pointed to by the given handle.
     * The name is a case-sensitive string.
     *
     * @param module handle to the module it should search through
     * @param name case-sensitive name of the function to find
     * @return the handle which points to the found function or null if no function matching the name was found
     * @throws BadHandleException if the given module handle is either null or doesn't point to a module
     * @see #GetModule(String)
     * @see #GetModule()
     * @see #GetFunction(String)
     * @see #CallHandleFunction(Handle, Object...)
     * @since 1.0
     */
    Handle GetFunction(Handle module, String name) throws BadHandleException;

    /**
     * Searches for a function within the module that loaded the current library.
     * The name is a case-sensitive string.
     *
     * @param name case-sensitive name of the function to find
     * @return the handle which points to the found function or null if no function matching the name was found
     * @see #GetModule()
     * @see #GetFunction(Handle, String)
     * @see #CallHandleFunction(Handle, Object...)
     * @since 1.0
     */
    Handle GetFunction(String name);

    /**
     * Will call a function pointed to by the given handle and even if a value is returned by it, it will be voided.
     * The arguments are values of Java types and will be wrapped to ct values by the vm before calling.
     * 
     * @param function handle to the function that should be called
     * @param args varargs of Java type values that the vm should pass to the function
     * @throws BadHandleException if the function handle is either null or doesn't point to a valid function
     * @see #GetFunction(String) 
     * @see #CallVoidFunctionA(Handle, Object[]) 
     * @see #CallVoidFunctionL(Handle, List)
     * @since 1.0
     */
    void CallVoidFunction(Handle function, Object... args) throws BadHandleException;

    /**
     * Will call a function pointed to by the given handle and even if a value is returned by it, it will be voided.
     * The arguments are values of Java types and will be wrapped to ct values by the vm before calling.
     *
     * @param function handle to the function that should be called
     * @param args array of Java type values that the vm should pass to the function
     * @throws BadHandleException if the function handle is either null or doesn't point to a valid function
     * @see #GetFunction(String)
     * @see #CallVoidFunction(Handle, Object...)
     * @see #CallVoidFunctionL(Handle, List)
     * @since 1.0
     */
    void CallVoidFunctionA(Handle function, Object[] args) throws BadHandleException;

    /**
     * Will call a function pointed to by the given handle and even if a value is returned by it, it will be voided.
     * The arguments are values of Java types and will be wrapped to ct values by the vm before calling.
     *
     * @param function handle to the function that should be called
     * @param args a standard {@link List} of Java type values that the vm should pass to the function
     * @throws BadHandleException if the function handle is either null or doesn't point to a valid function
     * @see #GetFunction(String)
     * @see #CallVoidFunction(Handle, Object...)
     * @see #CallVoidFunctionA(Handle, Object[])
     * @since 1.0
     */
    void CallVoidFunctionL(Handle function, List<Object> args) throws BadHandleException;

    /**
     * Will call a function pointed to by the given handle and attempt to wrap the returned value as a Java byte or default to 0 if wrapping was not possible,
     * if this does happen, the error will occur in diagnostics and can be found with {@link #GetLatestError()} or {@link #PreviewLatestError()}. <br>
     * In cases of functions which return void, this may return a garbage value due to how functions are managed at runtime.
     *
     * @param function handle to the function that should be called
     * @param args varargs of Java type values that the vm should pass to the function
     * @return the return value of the called function wrapped as a Java byte
     * @throws BadHandleException if the function handle is either null or doesn't point to a valid function
     * @see #GetFunction(String)
     * @see #GetLatestError()
     * @see #PreviewLatestError()
     * @see #CallByteFunctionA(Handle, Object[])
     * @see #CallByteFunctionL(Handle, List)
     * @since 1.0
     */
    byte CallByteFunction(Handle function, Object... args) throws BadHandleException;

    /**
     * Will call a function pointed to by the given handle and attempt to wrap the returned value as a Java byte or default to 0 if wrapping was not possible,
     * if this does happen, the error will occur in diagnostics and can be found with {@link #GetLatestError()} or {@link #PreviewLatestError()}. <br>
     * In cases of functions which return void, this may return a garbage value due to how functions are managed at runtime.
     *
     * @param function handle to the function that should be called
     * @param args array of Java type values that the vm should pass to the function
     * @return the return value of the called function wrapped as a Java byte
     * @throws BadHandleException if the function handle is either null or doesn't point to a valid function
     * @see #GetFunction(String)
     * @see #GetLatestError()
     * @see #PreviewLatestError()
     * @see #CallByteFunction(Handle, Object...)
     * @see #CallByteFunctionL(Handle, List)
     * @since 1.0
     */
    byte CallByteFunctionA(Handle function, Object[] args) throws BadHandleException;

    /**
     * Will call a function pointed to by the given handle and attempt to wrap the returned value as a Java byte or default to 0 if wrapping was not possible,
     * if this does happen, the error will occur in diagnostics and can be found with {@link #GetLatestError()} or {@link #PreviewLatestError()}. <br>
     * In cases of functions which return void, this may return a garbage value due to how functions are managed at runtime.
     *
     * @param function handle to the function that should be called
     * @param args a standard {@link List} of Java type values that the vm should pass to the function
     * @return the return value of the called function wrapped as a Java byte
     * @throws BadHandleException if the function handle is either null or doesn't point to a valid function
     * @see #GetFunction(String)
     * @see #GetLatestError()
     * @see #PreviewLatestError()
     * @see #CallByteFunction(Handle, Object...)
     * @see #CallByteFunctionA(Handle, Object[])
     * @since 1.0
     */
    byte CallByteFunctionL(Handle function, List<Object> args) throws BadHandleException;

    /**
     * Will call a function pointed to by the given handle and attempt to wrap the returned value as a Java short or default to 0 if wrapping was not possible,
     * if this does happen, the error will occur in diagnostics and can be found with {@link #GetLatestError()} or {@link #PreviewLatestError()}. <br>
     * In cases of functions which return void, this may return a garbage value due to how functions are managed at runtime.
     *
     * @param function handle to the function that should be called
     * @param args varargs of Java type values that the vm should pass to the function
     * @return the return value of the called function wrapped as a Java short
     * @throws BadHandleException if the function handle is either null or doesn't point to a valid function
     * @see #GetFunction(String)
     * @see #GetLatestError()
     * @see #PreviewLatestError()
     * @see #CallShortFunctionA(Handle, Object[])
     * @see #CallShortFunctionL(Handle, List)
     * @since 1.0
     */
    short CallShortFunction(Handle function, Object... args) throws BadHandleException;

    /**
     * Will call a function pointed to by the given handle and attempt to wrap the returned value as a Java short or default to 0 if wrapping was not possible,
     * if this does happen, the error will occur in diagnostics and can be found with {@link #GetLatestError()} or {@link #PreviewLatestError()}. <br>
     * In cases of functions which return void, this may return a garbage value due to how functions are managed at runtime.
     *
     * @param function handle to the function that should be called
     * @param args array of Java type values that the vm should pass to the function
     * @return the return value of the called function wrapped as a Java short
     * @throws BadHandleException if the function handle is either null or doesn't point to a valid function
     * @see #GetFunction(String)
     * @see #GetLatestError()
     * @see #PreviewLatestError()
     * @see #CallShortFunction(Handle, Object...)
     * @see #CallShortFunctionL(Handle, List)
     * @since 1.0
     */
    short CallShortFunctionA(Handle function, Object[] args) throws BadHandleException;

    /**
     * Will call a function pointed to by the given handle and attempt to wrap the returned value as a Java short or default to 0 if wrapping was not possible,
     * if this does happen, the error will occur in diagnostics and can be found with {@link #GetLatestError()} or {@link #PreviewLatestError()}. <br>
     * In cases of functions which return void, this may return a garbage value due to how functions are managed at runtime.
     *
     * @param function handle to the function that should be called
     * @param args a standard {@link List} of Java type values that the vm should pass to the function
     * @return the return value of the called function wrapped as a Java short
     * @throws BadHandleException if the function handle is either null or doesn't point to a valid function
     * @see #GetFunction(String)
     * @see #GetLatestError()
     * @see #PreviewLatestError()
     * @see #CallShortFunction(Handle, Object...)
     * @see #CallShortFunctionA(Handle, Object[])
     * @since 1.0
     */
    short CallShortFunctionL(Handle function, List<Object> args) throws BadHandleException;

    /**
     * Will call a function pointed to by the given handle and attempt to wrap the returned value as a Java int or default to 0 if wrapping was not possible,
     * if this does happen, the error will occur in diagnostics and can be found with {@link #GetLatestError()} or {@link #PreviewLatestError()}. <br>
     * In cases of functions which return void, this may return a garbage value due to how functions are managed at runtime.
     *
     * @param function handle to the function that should be called
     * @param args varargs of Java type values that the vm should pass to the function
     * @return the return value of the called function wrapped as a Java int
     * @throws BadHandleException if the function handle is either null or doesn't point to a valid function
     * @see #GetFunction(String)
     * @see #GetLatestError()
     * @see #PreviewLatestError()
     * @see #CallIntFunctionA(Handle, Object[])
     * @see #CallIntFunctionL(Handle, List)
     * @since 1.0
     */
    int CallIntFunction(Handle function, Object... args) throws BadHandleException;

    /**
     * Will call a function pointed to by the given handle and attempt to wrap the returned value as a Java int or default to 0 if wrapping was not possible,
     * if this does happen, the error will occur in diagnostics and can be found with {@link #GetLatestError()} or {@link #PreviewLatestError()}. <br>
     * In cases of functions which return void, this may return a garbage value due to how functions are managed at runtime.
     *
     * @param function handle to the function that should be called
     * @param args array of Java type values that the vm should pass to the function
     * @return the return value of the called function wrapped as a Java int
     * @throws BadHandleException if the function handle is either null or doesn't point to a valid function
     * @see #GetFunction(String)
     * @see #GetLatestError()
     * @see #PreviewLatestError()
     * @see #CallIntFunction(Handle, Object...)
     * @see #CallIntFunctionL(Handle, List)
     * @since 1.0
     */
    int CallIntFunctionA(Handle function, Object[] args) throws BadHandleException;

    /**
     * Will call a function pointed to by the given handle and attempt to wrap the returned value as a Java int or default to 0 if wrapping was not possible,
     * if this does happen, the error will occur in diagnostics and can be found with {@link #GetLatestError()} or {@link #PreviewLatestError()}. <br>
     * In cases of functions which return void, this may return a garbage value due to how functions are managed at runtime.
     *
     * @param function handle to the function that should be called
     * @param args a standard {@link List} of Java type values that the vm should pass to the function
     * @return the return value of the called function wrapped as a Java int
     * @throws BadHandleException if the function handle is either null or doesn't point to a valid function
     * @see #GetFunction(String)
     * @see #GetLatestError()
     * @see #PreviewLatestError()
     * @see #CallIntFunction(Handle, Object...)
     * @see #CallIntFunctionA(Handle, Object[])
     * @since 1.0
     */
    int CallIntFunctionL(Handle function, List<Object> args) throws BadHandleException;

    /**
     * Will call a function pointed to by the given handle and attempt to wrap the returned value as a Java long or default to 0 if wrapping was not possible,
     * if this does happen, the error will occur in diagnostics and can be found with {@link #GetLatestError()} or {@link #PreviewLatestError()}. <br>
     * In cases of functions which return void, this may return a garbage value due to how functions are managed at runtime.
     *
     * @param function handle to the function that should be called
     * @param args varargs of Java type values that the vm should pass to the function
     * @return the return value of the called function wrapped as a Java long
     * @throws BadHandleException if the function handle is either null or doesn't point to a valid function
     * @see #GetFunction(String)
     * @see #GetLatestError()
     * @see #PreviewLatestError()
     * @see #CallLongFunctionA(Handle, Object[])
     * @see #CallLongFunctionL(Handle, List)
     * @since 1.0
     */
    long CallLongFunction(Handle function, Object... args) throws BadHandleException;

    /**
     * Will call a function pointed to by the given handle and attempt to wrap the returned value as a Java long or default to 0 if wrapping was not possible,
     * if this does happen, the error will occur in diagnostics and can be found with {@link #GetLatestError()} or {@link #PreviewLatestError()}. <br>
     * In cases of functions which return void, this may return a garbage value due to how functions are managed at runtime.
     *
     * @param function handle to the function that should be called
     * @param args array of Java type values that the vm should pass to the function
     * @return the return value of the called function wrapped as a Java long
     * @throws BadHandleException if the function handle is either null or doesn't point to a valid function
     * @see #GetFunction(String)
     * @see #GetLatestError()
     * @see #PreviewLatestError()
     * @see #CallLongFunction(Handle, Object...)
     * @see #CallLongFunctionL(Handle, List)
     * @since 1.0
     */
    long CallLongFunctionA(Handle function, Object[] args) throws BadHandleException;

    /**
     * Will call a function pointed to by the given handle and attempt to wrap the returned value as a Java long or default to 0 if wrapping was not possible,
     * if this does happen, the error will occur in diagnostics and can be found with {@link #GetLatestError()} or {@link #PreviewLatestError()}. <br>
     * In cases of functions which return void, this may return a garbage value due to how functions are managed at runtime.
     *
     * @param function handle to the function that should be called
     * @param args a standard {@link List} of Java type values that the vm should pass to the function
     * @return the return value of the called function wrapped as a Java long
     * @throws BadHandleException if the function handle is either null or doesn't point to a valid function
     * @see #GetFunction(String)
     * @see #GetLatestError()
     * @see #PreviewLatestError()
     * @see #CallLongFunction(Handle, Object...)
     * @see #CallLongFunctionA(Handle, Object[])
     * @since 1.0
     */
    long CallLongFunctionL(Handle function, List<Object> args) throws BadHandleException;

    /**
     * Will call a function pointed to by the given handle and attempt to wrap the returned value as a Java String or default to null if wrapping was not possible,
     * if this does happen, the error will occur in diagnostics and can be found with {@link #GetLatestError()} or {@link #PreviewLatestError()}. <br>
     * In cases of functions which return void, this may return a garbage value due to how functions are managed at runtime.
     *
     * @param function handle to the function that should be called
     * @param args varargs of Java type values that the vm should pass to the function
     * @return the return value of the called function wrapped as a Java String
     * @throws BadHandleException if the function handle is either null or doesn't point to a valid function
     * @see #GetFunction(String)
     * @see #GetLatestError()
     * @see #PreviewLatestError()
     * @see #CallStringFunctionA(Handle, Object[])
     * @see #CallStringFunctionL(Handle, List)
     * @since 1.0
     */
    String CallStringFunction(Handle function, Object... args) throws BadHandleException;

    /**
     * Will call a function pointed to by the given handle and attempt to wrap the returned value as a Java String or default to null if wrapping was not possible,
     * if this does happen, the error will occur in diagnostics and can be found with {@link #GetLatestError()} or {@link #PreviewLatestError()}. <br>
     * In cases of functions which return void, this may return a garbage value due to how functions are managed at runtime.
     *
     * @param function handle to the function that should be called
     * @param args array of Java type values that the vm should pass to the function
     * @return the return value of the called function wrapped as a Java String
     * @throws BadHandleException if the function handle is either null or doesn't point to a valid function
     * @see #GetFunction(String)
     * @see #GetLatestError()
     * @see #PreviewLatestError()
     * @see #CallStringFunction(Handle, Object...)
     * @see #CallStringFunctionL(Handle, List)
     * @since 1.0
     */
    String CallStringFunctionA(Handle function, Object[] args) throws BadHandleException;

    /**
     * Will call a function pointed to by the given handle and attempt to wrap the returned value as a Java String or default to null if wrapping was not possible,
     * if this does happen, the error will occur in diagnostics and can be found with {@link #GetLatestError()} or {@link #PreviewLatestError()}. <br>
     * In cases of functions which return void, this may return a garbage value due to how functions are managed at runtime.
     *
     * @param function handle to the function that should be called
     * @param args a standard {@link List} of Java type values that the vm should pass to the function
     * @return the return value of the called function wrapped as a Java String
     * @throws BadHandleException if the function handle is either null or doesn't point to a valid function
     * @see #GetFunction(String)
     * @see #GetLatestError()
     * @see #PreviewLatestError()
     * @see #CallStringFunctionA(Handle, Object[])
     * @see #CallStringFunctionL(Handle, List)
     * @since 1.0
     */
    String CallStringFunctionL(Handle function, List<Object> args) throws BadHandleException;

    /**
     * Will call a function pointed to by the given handle and return a handle to a clone of the returned value.
     * In cases of functions which return void, this may return a garbage value due to how functions are managed at runtime.
     *
     * @param function handle to the function that should be called
     * @param args varargs of Java type values that the vm should pass to the function
     * @return cloned handle of the function return value
     * @throws BadHandleException if the function handle is either null or doesn't point to a valid function
     * @see #GetFunction(String)
     * @see #GetLatestError()
     * @see #PreviewLatestError()
     * @see #CallHandleFunctionA(Handle, Object[])
     * @see #CallHandleFunctionL(Handle, List)
     * @since 1.0
     */
    Handle CallHandleFunction(Handle function, Object... args) throws BadHandleException;

    /**
     * Will call a function pointed to by the given handle and return a handle to a clone of the returned value.
     * In cases of functions which return void, this may return a garbage value due to how functions are managed at runtime.
     *
     * @param function handle to the function that should be called
     * @param args array of Java type values that the vm should pass to the function
     * @return cloned handle of the function return value
     * @throws BadHandleException if the function handle is either null or doesn't point to a valid function
     * @see #GetFunction(String)
     * @see #GetLatestError()
     * @see #PreviewLatestError()
     * @see #CallHandleFunction(Handle, Object...)
     * @see #CallHandleFunctionL(Handle, List)
     * @since 1.0
     */
    Handle CallHandleFunctionA(Handle function, Object[] args) throws BadHandleException;

    /**
     * Will call a function pointed to by the given handle and return a handle to a clone of the returned value.
     * In cases of functions which return void, this may return a garbage value due to how functions are managed at runtime.
     *
     * @param function handle to the function that should be called
     * @param args varargs of Java type values that the vm should pass to the function
     * @return cloned handle of the function return value
     * @throws BadHandleException if the function handle is either null or doesn't point to a valid function
     * @see #GetFunction(String)
     * @see #GetLatestError()
     * @see #PreviewLatestError()
     * @see #CallHandleFunction(Handle, Object...)
     * @see #CallHandleFunctionA(Handle, Object[])
     * @since 1.0
     */
    Handle CallHandleFunctionL(Handle function, List<Object> args) throws BadHandleException;

    /**
     * Acts as if the int instruction of bytecode was used, passing the bytes and arguments.
     * In cases where the int call has no return, the returned handle may be garbage.
     *
     * @param id id of the interrupt call
     * @param byte1 int id byte1 byte2 byte3
     * @param byte2 int id byte1 byte2 byte3
     * @param byte3 int id byte1 byte2 byte3
     * @param args varargs of Java type values that the vm will wrap to ct and pass to the call
     * @return cloned handle of the function return value
     * @since 1.0
     */
    Handle CallInterruptFunction(int id, byte byte1, byte byte2, byte byte3, Object... args);

    /**
     * Performs a check on a handle to see if it points to a byte.
     *
     * @param handle handle to check
     * @return true if the handle points to a valid byte, false otherwise
     * @see #GetByteFromHandle(Handle, boolean)
     * @since 1.0
     */
    boolean IsByte(Handle handle);

    /**
     * Performs a check on a handle to see if it points to a short.
     *
     * @param handle handle to check
     * @return true if the handle points to a valid short, false otherwise
     * @see #GetShortFromHandle(Handle, boolean)
     * @since 1.0
     */
    boolean IsShort(Handle handle);

    /**
     * Performs a check on a handle to see if it points to an int.
     *
     * @param handle handle to check
     * @return true if the handle points to a valid int, false otherwise
     * @see #GetIntFromHandle(Handle, boolean)
     * @since 1.0
     */
    boolean IsInt(Handle handle);

    /**
     * Performs a check on a handle to see if it points to a long.
     *
     * @param handle handle to check
     * @return true if the handle points to a valid long, false otherwise
     * @see #GetLongFromHandle(Handle, boolean)
     * @since 1.0
     */
    boolean IsLong(Handle handle);

    /**
     * Performs a check on a handle to see if it points to any number.
     *
     * @param handle handle to check
     * @return true if the handle points to a valid number, false otherwise
     * @see #GetByteFromHandle(Handle, boolean)
     * @see #GetShortFromHandle(Handle, boolean)
     * @see #GetIntFromHandle(Handle, boolean)
     * @see #GetLongFromHandle(Handle, boolean)
     * @since 1.0
     */
    boolean IsNumber(Handle handle);

    /**
     * Performs a check on a handle to see if it points to a string.
     *
     * @param handle handle to check
     * @return true if the handle points to a valid string, false otherwise
     * @see #GetStringFromHandle(Handle)
     * @since 1.0
     */
    boolean IsString(Handle handle);

    /**
     * Performs a check on a handle to see if it points to a module.
     *
     * @param handle handle to check
     * @return true if the handle points to a valid module, false otherwise
     * @since 1.0
     */
    boolean IsModule(Handle handle);

    /**
     * Performs a check on a handle to see if it points to a function.
     *
     * @param handle handle to check
     * @return true if the handle points to a valid function, false otherwise
     * @since 1.0
     */
    boolean IsFunction(Handle handle);

    /**
     * Retrieves a byte from a handle which may point to a byte.
     *
     * @param handle the handle to retrieve the byte from
     * @param strictType if this is true, the handle HAS to point to a valid byte and only a byte, if not then any number will work
     * @return retrieved byte
     * @throws BadHandleException if the handle doesn't point to a valid number or if strict typing is on, this is thrown if it isn't a byte
     * @since 1.0
     */
    byte GetByteFromHandle(Handle handle, boolean strictType) throws BadHandleException;

    /**
     * Retrieves a short from a handle which may point to a short.
     *
     * @param handle the handle to retrieve the short from
     * @param strictType if this is true, the handle HAS to point to a valid short and only a short, if not then any number will work
     * @return retrieved short
     * @throws BadHandleException if the handle doesn't point to a valid number or if strict typing is on, this is thrown if it isn't a short
     * @since 1.0
     */
    short GetShortFromHandle(Handle handle, boolean strictType) throws BadHandleException;

    /**
     * Retrieves an int from a handle which may point to an int.
     *
     * @param handle the handle to retrieve the int from
     * @param strictType if this is true, the handle HAS to point to a valid int and only an int, if not then any number will work
     * @return retrieved int
     * @throws BadHandleException if the handle doesn't point to a valid number or if strict typing is on, this is thrown if it isn't an int
     * @since 1.0
     */
    int GetIntFromHandle(Handle handle, boolean strictType) throws BadHandleException;

    /**
     * Retrieves a long from a handle which may point to a long.
     *
     * @param handle the handle to retrieve the long from
     * @param strictType if this is true, the handle HAS to point to a valid long and only a long, if not then any number will work
     * @return retrieved long
     * @throws BadHandleException if the handle doesn't point to a valid number or if strict typing is on, this is thrown if it isn't a long
     * @since 1.0
     */
    long GetLongFromHandle(Handle handle, boolean strictType) throws BadHandleException;

    /**
     * Retrieves a string from a handle which may point to a string.
     *
     * @param handle the handle to retrieve the string from
     * @return retrieved string
     * @throws BadHandleException if the handle doesn't point to a valid string
     * @since 1.0
     */
    String GetStringFromHandle(Handle handle) throws BadHandleException;

    /**
     * Constructs a new ct byte and returns a handle to it.
     *
     * @param b java byte to construct with
     * @return handle to the newly created byte
     * @since 1.0
     */
    Handle NewByte(byte b);

    /**
     * Constructs a new ct short and returns a handle to it.
     *
     * @param s java short to construct with
     * @return handle to the newly created short
     * @since 1.0
     */
    Handle NewShort(short s);

    /**
     * Constructs a new ct int and returns a handle to it.
     *
     * @param i java int to construct with
     * @return handle to the newly created int
     * @since 1.0
     */
    Handle NewInt(int i);

    /**
     * Constructs a new ct long and returns a handle to it.
     *
     * @param l java long to construct with
     * @return handle to the newly created long
     * @since 1.0
     */
    Handle NewLong(long l);

    /**
     * Constructs a new ct string and returns a handle to it.
     *
     * @param jString the java string to construct the ct string with
     * @return handle to newly constructed ct string
     * @since 1.0
     */
    Handle NewString(String jString);

    /**
     * Allocates an amount of values on the heap and returns a handle to this allocation.
     *
     * @param size the amount of values to allocate
     * @return handle to the new allocation or null if out of memory or other error
     * @since 1.0
     */
    Handle Alloc(int size);

    /**
     * Will change the size of an allocation on the heap and returns the handle to the new allocation.
     * The old elements will remain unless it's being sized down. <br>
     * If the handle is null, creates a new allocation, if the new size is 0, frees an allocation. <br>
     * Will report an error if the handle doesn't point to a heap allocation.
     *
     * @param handle handle to the old allocation, must be allocated on heap
     * @param newSize new size of the allocations
     * @return handle to the newly resized allocation, can be the passed handle
     * @see #Alloc(int)
     * @since 1.0
     */
    Handle ReAlloc(Handle handle, int newSize);

    /**
     * Frees an allocation on the heap and deletes all values associated with it.
     * Will report an error if handle isn't a valid heap allocation.
     *
     * @param handle handle to the allocation, must be allocated on heap
     * @see #GetLatestError()
     * @since 1.0
     */
    void Free(Handle handle);

    /**
     * Attempts to read the value stored at a handle (preferably a heap allocation) at an offset.
     *
     * @param ptr handle to heap allocation
     * @param offset the offset to read at
     * @return the value stored at ptr[offset] (pseudocode)
     * @throws BadHandleException if the provided ptr handle is null or doesn't point to a memory allocation
     * @throws SecurityException if a read-protected memory location is attempted to be read
     * @since 1.0
     */
    Handle Read(Handle ptr, int offset) throws BadHandleException, SecurityException;

    /**
     * Attempts to write a given value in memory (preferably a heap allocation) at a specified offset.
     *
     * @param ptr handle to heap allocation
     * @param offset the offset at ptr to write to
     * @param value the value to write at ptr[offset] (pseudocode)
     * @throws BadHandleException If the provided ptr handle is null or doesn't point to a memory location
     * @throws SecurityException if a write-protected memory location is attempted to write to
     * @since 1.0
     */
    void Write(Handle ptr, int offset, Handle value) throws BadHandleException, SecurityException;
}
