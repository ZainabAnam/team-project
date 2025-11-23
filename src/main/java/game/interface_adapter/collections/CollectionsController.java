package game.interface_adapter.collections;

import game.use_case.Collections.CollectionsInputBoundary;
import game.use_case.Collections.CollectionsInputData;

/**
 * Controller for the Collections feature.
 * Called by the UI (Collections tab) when the user wants to load collections.
 */
public class CollectionsController {

    private final CollectionsInputBoundary collectionsInteractor;

    public CollectionsController(CollectionsInputBoundary collectionsInteractor) {
        this.collectionsInteractor = collectionsInteractor;
    }

    /**
     * To be called when the collections tab is opened / refreshed.
     */
    public void loadCollections() {
        CollectionsInputData inputData = new CollectionsInputData();
        collectionsInteractor.execute(inputData);
    }
}
