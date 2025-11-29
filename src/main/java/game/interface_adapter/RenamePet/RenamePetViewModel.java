package game.interface_adapter.RenamePet;

import game.interface_adapter.ViewModel;

public class RenamePetViewModel extends ViewModel<RenamePetState> {

    public static final String TITLE_LABEL = "Rename Pet";
    public static final String NAME_LABEL = "New Name:";
    public static final String CONFIRM_BUTTON_LABEL = "Rename";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public RenamePetViewModel() {
        super("rename pet");
        setState(new RenamePetState());
    }
}
