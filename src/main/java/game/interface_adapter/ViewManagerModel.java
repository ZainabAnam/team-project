package game.interface_adapter;

/**
 * Model for the View Manager. Its state is the name of the View which
 * is currently active. An initial state of "" is used.
 */
public class ViewManagerModel extends ViewModel<String> {

    /**
     * Constructs ViewManagerModel.
     */
    public ViewManagerModel() {
        super("view manager");
        this.setState("");
    }

    /**
     * Sets initial view.
     * @param viewName view name
     */
    public void setInitialView(final String viewName) {
        this.setState(viewName);
    }
}
