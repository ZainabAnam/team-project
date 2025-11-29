package game.app;

import game.data_access.RenamePetDataAccessObject;
import game.entity.User;
import game.interface_adapter.RenamePet.RenamePetController;
import game.interface_adapter.ViewManagerModel;
import game.interface_adapter.shop.ShopViewModel;
import game.interface_adapter.shop.ShopPresenter;
import game.use_case.PetCard.RenamePet.RenamePetInteractor;
import game.view.ShopView;
import game.view.ViewManager;
import game.use_case.PetShop.ShopController;
import game.use_case.PetShop.BuyItem.*;
import game.use_case.PetShop.BuyLootBox.*;
import game.use_case.PetShop.UpgradeClicker.*;
import game.use_case.PetShop.UnlockSlot.*;
import game.data_access.ShopDataAccessObject;
import game.interface_adapter.RenamePet.RenamePetViewModel;
import game.interface_adapter.RenamePet.RenamePetPresenter;
import game.view.PetRenameView;
import game.use_case.PetCard.RenamePet.RenamePetDataAccessInterface;



import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private ShopView shopView;
    private ShopViewModel shopViewModel;
    private ShopPresenter shopPresenter;
    private ShopController shopController;
    private ShopDataAccessObject shopDataAccessObject;
    private PetRenameView petRenameView;
    private RenamePetViewModel renamePetViewModel;
    private RenamePetDataAccessInterface renamePetDataAccess;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
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
    public AppBuilder addRenamePetView() {
        renamePetViewModel = new RenamePetViewModel();


        if (shopDataAccessObject != null) {
            renamePetDataAccess = new RenamePetDataAccessObject(shopDataAccessObject.getCurrentUser());
        } else {
            renamePetDataAccess = new RenamePetDataAccessObject(new User());
        }

        RenamePetPresenter renamePetPresenter = new RenamePetPresenter(
                viewManagerModel,
                renamePetViewModel
        );

        RenamePetInteractor renamePetInteractor = new RenamePetInteractor(
                renamePetDataAccess,
                renamePetPresenter
        );

        RenamePetController renamePetController = new RenamePetController(renamePetInteractor);
        petRenameView = new PetRenameView(renamePetController, renamePetViewModel);
        cardPanel.add(petRenameView, "rename_pet");

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
