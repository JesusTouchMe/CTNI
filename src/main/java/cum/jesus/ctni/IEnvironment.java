package cum.jesus.ctni;

import cum.jesus.ctni.exception.BadHandleException;

import java.util.List;

/**
 * Environment interface for interacting with the VM in several ways
 */
public interface IEnvironment {
    /**
     * Find a loaded module in the vm and return its handle
     * @param name module name
     * @return handle to the module or null if wasn't found
     */
    Handle GetModule(String name);

    /**
     * Get the handle to the module the native library belongs to
     * @return module handle, never null
     */
    Handle GetModule();

    /**
     * Find a function in a given module handle and return its handle
     * @param module module handle to find the function in
     * @param name function name
     * @return handle to the function or null if wasn't found
     * @throws BadHandleException if the provided handle doesn't point to a module
     */
    Handle GetFunction(Handle module, String name) throws BadHandleException;

    /**
     * Find a function in the module the native library is a part of and return its handle
     * @param name function name
     * @return handle to the function or null if wasn't found
     */
    Handle GetFunction(String name);

    /**
     * Call a function without a return value. Not fact checked
     * @param function handle to callee
     * @param args varargs of java type values
     */
    void CallVoidFunction(Handle function, Object... args) throws BadHandleException;

    /**
     * Call a function without a return value. Not fact checked
     * @param function handle to callee
     * @param args array of java type values
     */
    void CallVoidFunctionA(Handle function, Object[] args) throws BadHandleException;

    /**
     * Call a function without a return value. Not fact checked
     * @param function handle to callee
     * @param args any iterable List of java type args
     */
    void CallVoidFunctionL(Handle function, List<?> args) throws BadHandleException;

    /**
     * Call a function with a byte return value. Not fact checked
     * @param function handle to callee
     * @param args varargs of java type values
     * @return function return value as a byte
     */
    byte CallByteFunction(Handle function, Object... args) throws BadHandleException;

    /**
     * Call a function with a byte return value. Not fact checked
     * @param function handle to callee
     * @param args array of java type values
     * @return function return value as a byte
     */
    byte CallByteFunctionA(Handle function, Object[] args) throws BadHandleException;

    /**
     * Call a function with a byte return value. Not fact checked
     * @param function handle to callee
     * @param args any iterable List of java type args
     * @return function return value as a byte
     */
    byte CallByteFunctionL(Handle function, List<?> args) throws BadHandleException;

    /**
     * Call a function with a short return value. Not fact checked
     * @param function handle to callee
     * @param args varargs of java type values
     * @return function return value as a short
     */
    short CallShortFunction(Handle function, Object... args) throws BadHandleException;

    /**
     * Call a function with a short return value. Not fact checked
     * @param function handle to callee
     * @param args array of java type values
     * @return function return value as a short
     */
    short CallShortFunctionA(Handle function, Object[] args) throws BadHandleException;

    /**
     * Call a function with a short return value. Not fact checked
     * @param function handle to callee
     * @param args any iterable List of java type values
     * @return function return value as a short
     */
    short CallShortFunctionL(Handle function, List<?> args) throws BadHandleException;

    /**
     * Call a function with an int return value. Not fact checked
     * @param function handle to callee
     * @param args varargs of java type values
     * @return function return value as an int
     */
    int CallIntFunction(Handle function, Object... args) throws BadHandleException;

    /**
     * Call a function with an int return value. Not fact checked
     * @param function handle to callee
     * @param args array of java type values
     * @return function return value as an int
     */
    int CallIntFunctionA(Handle function, Object[] args) throws BadHandleException;

    /**
     * Call a function with an int return value. Not fact checked
     * @param function handle to callee
     * @param args any iterable List
     * @return function return value as an int
     */
    int CallIntFunctionL(Handle function, List<?> args) throws BadHandleException;

    /**
     * Call a function with a long return value. Not fact checked
     * @param function handle to callee
     * @param args varargs of java type values
     * @return function return value as a long
     */
    long CallLongFunction(Handle function, Object... args) throws BadHandleException;

    /**
     * Call a function with a long return value. Not fact checked
     * @param function handle to callee
     * @param args array of java type values
     * @return function return value as a long
     */
    long CallLongFunctionA(Handle function, Object[] args) throws BadHandleException;

    /**
     * Call a function with a long return value. Not fact checked
     * @param function handle to callee
     * @param args any iterable List of java type args
     * @return function return value as a long
     */
    long CallLongFunctionL(Handle function, List<?> args) throws BadHandleException;

    /**
     * Call a function with a string return value. Not fact checked
     * @param function handle to callee
     * @param args varargs of java type values
     * @return function return value as a string
     */
    String CallStringFunction(Handle function, Object... args) throws BadHandleException;

    /**
     * Call a function with a string return value. Not fact checked
     * @param function handle to callee
     * @param args array of java type values
     * @return function return value as a string
     */
    String CallStringFunctionA(Handle function, Object[] args) throws BadHandleException;

    /**
     * Call a function with a string return value. Not fact checked
     * @param function handle to callee
     * @param args any iterable List of java type args
     * @return function return value as a string
     */
    String CallStringFunctionL(Handle function, List<?> args) throws BadHandleException;

    byte GetByteFromHandle(Handle handle) throws BadHandleException;
}
