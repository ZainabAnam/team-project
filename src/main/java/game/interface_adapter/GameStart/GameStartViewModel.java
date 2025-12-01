package game.interface_adapter.GameStart;

import game.interface_adapter.ViewModel;

/**
 * The View Model for the Load Game View.
 */
public class GameStartViewModel extends ViewModel<GameStartState> {

    public GameStartViewModel() {
        super("loading");
        setState(new GameStartState());
    }
}
