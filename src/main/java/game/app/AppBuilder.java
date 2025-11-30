package game.app;

import game.interface_adapter.ViewManagerModel;
import game.interface_adapter.shop.ShopViewModel;
import game.interface_adapter.shop.ShopPresenter;
import game.interface_adapter.RenamePet.RenamePetViewModel;
import game.interface_adapter.RenamePet.RenamePetPresenter;
import game.interface_adapter.RenamePet.RenamePetController;
import game.interface_adapter.Sell_Pet.SellPetViewModel;
import game.interface_adapter.Sell_Pet.SellPetPresenter;
import game.interface_adapter.Sell_Pet.SellPetController;

import game.view.ShopView;
import game.view.PetRenameView;
import game.view.SellPetView;
import game.view.ViewManager;

import game.use_case.PetShop.ShopController;
import game.use_case.PetShop.BuyItem.*;
import game.use_case.PetShop.BuyLootBox.*;
import game.use_case.PetShop.UpgradeClicker.*;
import game.use_case.PetShop.UnlockSlot.*;

import game.use_case.PetCard.RenamePet.*;
import game.use_case.PetCard.SellPet.*;

import game.data_access.ShopDataAccessObject;
import game.data_access.RenamePetDataAccessObject;
import game.data_access.SellPetDataAccessObject;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // Shop components
    private ShopView shopView;
    private ShopViewModel shopViewModel;
    private ShopPresenter shopPresenter;
    private ShopController shopController;
    private ShopDataAccessObject shopDataAccessObject;

    // Rename Pet components
    private PetRenameView petRenameView;
    private RenamePetViewModel renamePetViewModel;
    private RenamePetController renamePetController;

    // Sell Pet components
    private SellPetView sellPetView;
    private SellPetViewModel sellPetViewModel;
    private SellPetController sellPetController;

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
        // Create ViewModel
        renamePetViewModel = new RenamePetViewModel();

        // Create data access (using the same user from shop)
        RenamePetDataAccessInterface renamePetDataAccess = new RenamePetDataAccessObject(
                shopDataAccessObject.getCurrentUser()
        );

        // Create Presenter, Interactor, Controller
        RenamePetPresenter renamePetPresenter = new RenamePetPresenter(
                viewManagerModel,
                renamePetViewModel
        );

        RenamePetInteractor renamePetInteractor = new RenamePetInteractor(
                renamePetDataAccess,
                renamePetPresenter
        );

        renamePetController = new RenamePetController(renamePetInteractor);

        // Create view
        petRenameView = new PetRenameView(renamePetController, renamePetViewModel);

        // Register view
        cardPanel.add(petRenameView, petRenameView.getViewName());

        return this;
    }

    public AppBuilder addSellPetView() {
        // Create ViewModel
        sellPetViewModel = new SellPetViewModel();

        // Create data access (using the same user from shop)
        SellPetDataAccessInterface sellPetDataAccess = new SellPetDataAccessObject(
                shopDataAccessObject.getCurrentUser()
        );

        // Create Presenter, Interactor, Controller
        SellPetPresenter sellPetPresenter = new SellPetPresenter(
                viewManagerModel,
                sellPetViewModel
        );

        SellPetInteractor sellPetInteractor = new SellPetInteractor(
                sellPetDataAccess,
                sellPetPresenter
        );

        sellPetController = new SellPetController(sellPetInteractor);

        // Create view (with ViewManagerModel for navigation)
        sellPetView = new SellPetView(
                sellPetController,
                sellPetViewModel,
                viewManagerModel  // Pass ViewManagerModel for navigation
        );

        // Register view
        cardPanel.add(sellPetView, sellPetView.getViewName());

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