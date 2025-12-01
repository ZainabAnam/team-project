package game.interface_adapter.collections;

import game.interface_adapter.ViewModel;
import game.view.CollectionsView;

/**
 * ViewModel for the Collections tab.
 * Stores a CollectionsState and notifies listeners when it changes.
 */
public class CollectionsViewModel extends ViewModel<CollectionsState> {

    public static final String VIEW_NAME = "collections";

    public CollectionsViewModel() {
        super(VIEW_NAME);
        setState(new CollectionsState());
    }
}
