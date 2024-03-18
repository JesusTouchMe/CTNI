package cum.jesus.ctni;

/**
 * The interface used by the virtual machine's internal diagnostics system to report any errors that haven't been bad enough to cause a crash.
 * This is used for when exceptions aren't needed as it can continue without handling these,
 * however it's still the best practise to handle these as the library developer to keep the vm at its best state.
 *
 * @author JesusTouchMe
 * @see IEnvironment#GetLatestError()
 * @see IEnvironment#GetAllErrors()
 * @since 1.0
 */
public interface DiagnosticReport {
    /**
     * The handle pointing to the module this error occurred at.
     * Never null.
     *
     * @return module handle the error happened at
     * @since 1.0
     */
    Handle module();

    /**
     * The handle pointing to the function this error occurred at.
     * Never null
     *
     * @return function handle the error happened at
     * @since 1.0
     */
    Handle function();

    /**
     * Whether the error was causes by a native library, the vm itself or bytecode
     *
     * @return true if the error was caused by a native library or the vm, false otherwise
     * @since 1.0
     */
    boolean causedByNative();

    /**
     * The severity of the error where higher number means more severe.
     * The number is never negative.
     *
     * @return severity as a number
     * @since 1.0
     */
    int severity();

    /**
     * If this error for whatever reason was caused by a Java {@link Throwable}, this will return that throwable.
     * This is allowed to be null if it wasn't caused by a Throwable
     *
     * @return the {@link Throwable} which caused this error or null if it wasn't caused by a Throwable
     * @since 1.0
     */
    Throwable cause();

    /**
     * The message for why this error occurred.
     * Is never null, but will simply be an empty string if there's no message.
     *
     * @return message for this error
     * @since 1.0
     */
    String message();
}
