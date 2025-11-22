package game.use_case.Collections;

public interface CollectionsOutputBoundary {

    void presentSuccess(CollectionsOutputData outputData);

    void presentFailure(String message);
}
