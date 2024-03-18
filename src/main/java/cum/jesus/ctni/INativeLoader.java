package cum.jesus.ctni;

import java.util.Map;

/**
 * This interface is what you need at least 1 implementation of when creating a native library.
 * This is necessary as it provides a main class for the vm library loader to call from when loading the library.
 * <br><br>
 * To use this for your library, you must put the "CT-Loader" entry in your jar manifest, with the value of your loader implementation class. <br>
 * Example: "com.example.LoaderExample".
 *
 * @author JesusTouchMe
 * @since 1.0
 */
public interface INativeLoader {
    /**
     * This function is called once and only once by the library loader and is provided with the native function map.
     * The map is a custom implementation of Map, which disallows getting and removing, meaning only put is allowed.
     *
     * @param functionMap the map of functions provided by the library loader
     * @see NativeFunction
     * @see Map
     * @since 1.0
     */
    void injectMethods(Map<String, NativeFunction> functionMap);
}
