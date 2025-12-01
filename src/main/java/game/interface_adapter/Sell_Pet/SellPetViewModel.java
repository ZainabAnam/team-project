package game.interface_adapter.Sell_Pet;

import game.interface_adapter.ViewModel;

public class SellPetViewModel extends ViewModel<SellPetState> {

    public static final String TITLE_LABEL = "Sell Pet";
    public static final String SELL_BUTTON_LABEL = "Sell";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public SellPetViewModel() {
        super("sell pet");
        setState(new SellPetState());
    }
}
