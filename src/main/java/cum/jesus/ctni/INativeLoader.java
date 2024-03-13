package cum.jesus.ctni;

import java.util.Map;

/**
 * Native library loader which is loaded and invoked at runtime. <br>
 * This is the interface for the main class of your library. <br><br>
 *
 * The location of the main class implementing this has to be stored in the jar manifest under the field "CT-Entry"
 */
public interface INativeLoader {
    /**
     * Called by the vm library loader once it's ready to receive functions
     * @param functionMap map of functions and their names
     */
    void injectMethods(Map<String, NativeFunction> functionMap);
}
