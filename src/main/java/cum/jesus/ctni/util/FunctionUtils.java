package cum.jesus.ctni.util;

import cum.jesus.ctni.Handle;
import cum.jesus.ctni.IEnvironment;
import cum.jesus.ctni.exception.BadHandleException;

import java.util.List;

/**
 * Utility class for simplifying functions from the vm
 */
public final class FunctionUtils {
    /**
     * Wraps a function handle from the vm in a {@link cum.jesus.ctni.util.FunctionUtils.Function}
     * @param env environment to find function
     * @param name name of the function
     * @return the wrapped function
     */
    public static Function getFunction(IEnvironment env, String name) {
        Handle func = env.GetFunction(name);
        return new Function(env, func);
    }

    /**
     * Wrapper class for a function in the CTVM
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
        public void callL(List<?> args) throws BadHandleException {
            env.CallVoidFunctionL(functionHandle, args);
        }
    }
}
