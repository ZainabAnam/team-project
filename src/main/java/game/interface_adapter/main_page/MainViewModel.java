package game.interface_adapter.main_page;


import game.entity.User;
import game.interface_adapter.ViewModel;

public class MainViewModel extends ViewModel<MainState> {
    public static final String title = "Pet Clicker";

    public MainViewModel() {
        super("main_page");
        setState(new MainState());
    }
}
