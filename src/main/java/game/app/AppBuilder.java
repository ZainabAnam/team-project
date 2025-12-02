package game.app;

import game.entity.Pet;
import game.entity.User;
import game.interface_adapter.ViewManagerModel;
import game.interface_adapter.collections.CollectionsController;
import game.interface_adapter.collections.CollectionsPresenter;
import game.interface_adapter.collections.CollectionsViewModel;
import game.interface_adapter.shop.ShopViewModel;
import game.interface_adapter.shop.ShopPresenter;
import game.interface_adapter.RenamePet.RenamePetViewModel;
import game.interface_adapter.RenamePet.RenamePetPresenter;
import game.interface_adapter.RenamePet.RenamePetController;
import game.interface_adapter.Sell_Pet.SellPetViewModel;
import game.interface_adapter.Sell_Pet.SellPetPresenter;
import game.interface_adapter.Sell_Pet.SellPetController;

import game.use_case.Collections.CollectionsDataAccessInterface;
import game.use_case.Collections.CollectionsInputBoundary;
import game.use_case.Collections.CollectionsInteractor;
import game.view.*;

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
import java.util.HashMap;
import java.util.Map;

import static game.Constants.*;

public class AppBuilder {
    private MainView mainView;
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

    public AppBuilder addMainView() {
        mainView = new MainView(viewManagerModel);
        cardPanel.add(mainView, mainView.getViewName());
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

    public AppBuilder addCollectionsView() {
        // 1. ViewModel and Presenter
        CollectionsViewModel collectionsViewModel = new CollectionsViewModel();
        CollectionsPresenter collectionsPresenter = new CollectionsPresenter(collectionsViewModel);

        // 2. In-memory implementation of CollectionsDataAccessInterface
        CollectionsDataAccessInterface collectionsDataAccessObject = new CollectionsDataAccessInterface() {
            private User testUser = createTestUser();

            @Override
            public User getCurrentUser() {
                return testUser;
            }

            @Override
            public void saveUsr(User user) {
                this.testUser = user;
            }
        };

        // 3. Interactor + Controller
        CollectionsInputBoundary interactor =
                new CollectionsInteractor(collectionsPresenter, collectionsDataAccessObject);
        CollectionsController collectionsController = new CollectionsController(interactor);

        // 4. Swing view
        CollectionsView collectionsView = new CollectionsView(collectionsViewModel, collectionsController, viewManagerModel,null);
        collectionsView.setCollectionsController(collectionsController);

        // 5. Register view with CardLayout
        JScrollPane scrollPane = new JScrollPane(collectionsView);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);

        cardPanel.add(scrollPane, CollectionsViewModel.VIEW_NAME);

        collectionsView.load();

        return this;
    }

    private User createTestUser() {
        User u = new User();

        // Add pets
        Pet p1 = new Pet("Dog", "Golden Retriever", "Common",5,4,5,null);
        Pet p2 = new Pet("Dog", "German Shepherd", "Elite",7,6,6,null);
        u.addToPetInventory(p1);
        u.addToPetInventory(p2);

        // Items
        u.addToItemList(ITEM_CANNED_FOOD);
        u.addToItemList(ITEM_KIBBLE);
        u.addToItemList(ITEM_HOME_COOKED);
        u.addToItemList(ITEM_CHEW_TOY);
        u.addToItemList(ITEM_TOSS_TOY);
        u.addToItemList(ITEM_PLUSH_TOY);

        return u;
    }

    public JFrame build() {
        final JFrame application = new JFrame("Pet Clicker Game");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        // Start with shop view as test
        //viewManagerModel.setState(shopView.getViewName());
        //viewManagerModel.firePropertyChange();

        // Collection view test
        viewManagerModel.setState(CollectionsViewModel.VIEW_NAME);
        viewManagerModel.firePropertyChange();


        return application;
    }
}