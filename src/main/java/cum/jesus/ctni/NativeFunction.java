package cum.jesus.ctni;

/**
 * Functional interface for native functions to be injected. A native function must have an IEnvironment argument and as many java type arguments as wanted
 */
public interface NativeFunction {
    /**
     * Amount of arguments the function takes. Used by the vm when calling a native function to fetch arguments
     * @return function argument count
     */
    int argc();

    /**
     * Calls the function. Not recommended to use this directly, but rather use this to call a function with the established arguments
     * @param env vm environment
     * @param args function args as java types
     * @return return value as ct handle
     */
    Handle call(IEnvironment env, Object[] args);
}
