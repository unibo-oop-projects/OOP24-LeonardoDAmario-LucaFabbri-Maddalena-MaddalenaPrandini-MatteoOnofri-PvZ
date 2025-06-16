package pvz.controller.maincontroller.api;

/**
 * Interface for controllers in the application.
 * Provides a method to handle exceptions at the controller level.
 */
public interface Controller {

    /**
     * Handles an exception thrown during application execution.
     *
     * @param exception the exception to handle
     */
    void handleException(Exception exception);
}
