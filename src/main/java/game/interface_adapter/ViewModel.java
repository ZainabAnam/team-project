package game.interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @param <T> The type of state object contained in the model.
 */
public class ViewModel<T> {
    /**
     * View name.
     */
    private final String viewName;

    /**
     * Property change support.
     */
    private final PropertyChangeSupport support =
            new PropertyChangeSupport(this);

    /**
     * State object.
     */
    private T state;

    /**
     * Constructs ViewModel.
     * @param name view name
     */
    public ViewModel(final String name) {
        this.viewName = name;
    }

    /**
     * Gets view name.
     * @return view name
     */
    public String getViewName() {
        return this.viewName;
    }

    /**
     * Gets state.
     * @return state object
     */
    public T getState() {
        return this.state;
    }

    /**
     * Sets state.
     * @param newState state object
     */
    public void setState(final T newState) {
        this.state = newState;
    }

    /**
     * Fires a property changed event for the state of this ViewModel.
     */
    public void firePropertyChange() {
        this.support.firePropertyChange("state", null, this.state);
    }

    /**
     * Fires a property changed event for the state of this ViewModel, which
     * allows the user to specify a different propertyName. This can be useful
     * when a class is listening for multiple kinds of property changes.
     * @param propertyName the label for the property that was changed
     */
    public void firePropertyChange(final String propertyName) {
        this.support.firePropertyChange(propertyName, null, this.state);
    }

    /**
     * Adds a PropertyChangeListener to this ViewModel.
     * @param listener The PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(
            final PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }

}
