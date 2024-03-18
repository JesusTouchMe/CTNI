package cum.jesus.ctni.util;

import cum.jesus.ctni.Handle;
import cum.jesus.ctni.IEnvironment;
import cum.jesus.ctni.NativeFunction;
import cum.jesus.ctni.exception.BadHandleException;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Utility class to make both native and bytecode functions easier to call or create.
 *
 * @author JesusTouchMe
 * @see IEnvironment
 * @since 1.0
 */
public final class FunctionUtils {
    /**
     * Wraps a function handle from the vm in a {@link cum.jesus.ctni.util.FunctionUtils.Function}
     * @param env environment to find function
     * @param name name of the function
     * @return the wrapped function
     * @see IEnvironment
     * @since 1.0
     */
    public static Function getFunction(IEnvironment env, String name) {
        Handle func = env.GetFunction(name);
        return new Function(env, func);
    }

    /**
     * Constructs a new {@link NativeFunction} using the provided parameters.
     *
     * @param argc amount of args the function requires
     * @param function the function which will be called by the native function
     * @return the newly made native function
     * @see NativeFunction
     * @since 1.0
     */
    public static NativeFunction createNative(int argc, BiFunction<IEnvironment, Object[], Handle> function) {
        return new NativeFunction() {
            @Override
            public int argc() {
                return argc;
            }

            @Override
            public Handle call(IEnvironment env, Object... args) {
                return function.apply(env, args);
            }
        };
    }

    /**
     * Wrapper class for a function in the CTVM
     *
     * @author JesusTouchMe
     * @since 1.0
     */
    public static final class Function {
        private IEnvironment env;
        private Handle functionHandle;

        private Function(IEnvironment env, Handle functionHandle) {
            this.env = env;
            this.functionHandle = functionHandle;
        }

        /**
         * Call the function with no return
         * @param args varargs of java type values
         * @throws BadHandleException if the function contains a bad handle
         */
        public void call(Object... args) throws BadHandleException {
            env.CallVoidFunctionA(functionHandle, args);
        }

        /**
         * Call the function with no return
         * @param args array of java type values
         * @throws BadHandleException if the function contains a bad handle
         */
        public void callA(Object[] args) throws BadHandleException {
            env.CallVoidFunctionA(functionHandle, args);
        }

        /**
         * Call the function with no return
         * @param args any iterable List of java type values
         * @throws BadHandleException if the function contains a bad handle
         */
        public void callL(List<Object> args) throws BadHandleException {
            env.CallVoidFunctionL(functionHandle, args);
        }
    }
}
