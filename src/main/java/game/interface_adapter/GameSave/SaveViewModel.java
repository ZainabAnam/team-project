package game.interface_adapter.GameSave;

import game.interface_adapter.GameStart.GameStartState;
import game.interface_adapter.ViewModel;

/**
 * The View Model for the save Game View.
 */
public class SaveViewModel extends  ViewModel<SaveState> {
    public SaveViewModel() {
        super("saving");
        setState(new SaveState());
    }
}
