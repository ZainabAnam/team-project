package game.interface_adapter.PetCard;

import game.interface_adapter.ViewModel;
import game.interface_adapter.collections.CollectionsState;

public class PetCardViewModel extends ViewModel<PetCardState> {
    public static final String title = "Pet Card";

    public PetCardViewModel() {
            super("PetCard");
            setState(new PetCardState());
        }
    }
