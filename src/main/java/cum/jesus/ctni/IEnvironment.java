package cum.jesus.ctni;

/**
 * Environment interface for interacting with the VM in several ways
 */
public interface IEnvironment {
    /**
     * Will find a function in the module the native library is a part of and return its handle
     * @param name function name
     * @return handle to the function or null if wasn't found
     */
    Handle getFunction(String name);
}
