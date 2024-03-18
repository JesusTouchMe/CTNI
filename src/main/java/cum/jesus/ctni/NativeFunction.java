package cum.jesus.ctni;

/**
 * Interface for creating native functions containing all the information the vm needs to wrap ct calls into Java calls
 *
 * @author JesusTouchMe
 * @since 1.0
 */
public interface NativeFunction {
    /**
     * Amount of arguments the function takes. Used by the vm when calling a native function to fetch arguments
     *
     * @return function argument count
     * @since 1.0
     */
    int argc();

    /**
     * Calls the function. Not recommended to use this directly, but rather use this as a way to wrap a normal function with type casts. <br>
     * E.g. {@code return env.NewInt(Math.addExact((int) args[0], (int) args[1]))}
     * <br><br>
     * Doing the above is completely safe as long as all the types are correctly done on the script side: {@code native func<int> add(int a, int b)}
     *
     * @param env vm environment
     * @param args function args as java types
     * @return return value as ct handle
     * @see IEnvironment
     * @since 1.0
     */
    Handle call(IEnvironment env, Object[] args);
}
