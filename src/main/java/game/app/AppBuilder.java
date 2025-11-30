package game.app;

import game.data_access.MainDataAccessObject;
import game.interface_adapter.ViewManagerModel;
import game.interface_adapter.main_page.MainController;
import game.interface_adapter.main_page.MainPresenter;
import game.interface_adapter.main_page.MainViewModel;
import game.interface_adapter.shop.ShopViewModel;
import game.interface_adapter.shop.ShopPresenter;
import game.use_case.MainScreenManualClicker.ManualClickerInputBoundary;
import game.use_case.MainScreenManualClicker.ManualClickerInteractor;
import game.view.MainView;
import game.view.ShopView;
import game.view.ViewManager;
import game.use_case.PetShop.ShopController;
import game.use_case.PetShop.BuyItem.*;
import game.use_case.PetShop.BuyLootBox.*;
import game.use_case.PetShop.UpgradeClicker.*;
import game.use_case.PetShop.UnlockSlot.*;
import game.data_access.ShopDataAccessObject;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private MainView mainView;
    private MainViewModel mainViewModel;
    private MainPresenter mainPresenter;
    private MainController mainController;
    private MainDataAccessObject mainDataAccessObject;

    private ShopView shopView;
    private ShopViewModel shopViewModel;
    private ShopPresenter shopPresenter;
    private ShopController shopController;
    private ShopDataAccessObject shopDataAccessObject;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addMainView() {
        mainViewModel = new MainViewModel();
        mainView = new MainView(mainViewModel);
        cardPanel.add(mainView, mainView.getViewName());
        return this;
    }

    public AppBuilder addMainUseCases() {
        mainDataAccessObject = new MainDataAccessObject();
        mainPresenter = new MainPresenter(viewManagerModel, mainViewModel, mainDataAccessObject);
        ManualClickerInputBoundary manualClickerInteractor = new ManualClickerInteractor(mainDataAccessObject, mainPresenter);
        mainController = new MainController(manualClickerInteractor);
        mainView.setMainController(mainController);
        return this;
    }

    public AppBuilder addShopView() {
        shopViewModel = new ShopViewModel();
        shopView = new ShopView(shopViewModel);
        cardPanel.add(shopView, shopView.getViewName());
        return this;
    }

    public AppBuilder addShopUseCases() {
        // Create data access object
        shopDataAccessObject = new ShopDataAccessObject();
        
        // Create presenter
        shopPresenter = new ShopPresenter(viewManagerModel, shopViewModel, shopDataAccessObject);
        
        // Create interactors
        BuyItemInputBoundary buyItemInteractor = new BuyItemInteractor(shopPresenter, shopDataAccessObject);
        BuyLootBoxInputBoundary buyLootBoxInteractor = new BuyLootBoxInteractor(shopPresenter, shopDataAccessObject);
        UpgradeClickerInputBoundary upgradeClickerInteractor = new UpgradeClickerInteractor(shopPresenter, shopDataAccessObject);
        UnlockSlotInputBoundary unlockSlotInteractor = new UnlockSlotInteractor(shopPresenter, shopDataAccessObject);
        
        // Create controller
        shopController = new ShopController(
            buyItemInteractor,
            buyLootBoxInteractor,
            upgradeClickerInteractor,
            unlockSlotInteractor
        );
        
        // Set controller in view
        shopView.setShopController(shopController);
        
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("Pet Clicker Game");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        // Start with shop view as test
        viewManagerModel.setState(shopView.getViewName());
        viewManagerModel.firePropertyChange();

        return application;
    }


}
