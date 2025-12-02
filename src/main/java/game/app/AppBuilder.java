package game.app;

import game.data_access.PetCardDataAccessObject;
import game.entity.Pet;
import game.entity.User;
import game.interface_adapter.PetCard.IncreaseAffection.IncreaseAffectionController;
import game.interface_adapter.PetCard.IncreaseAffection.IncreaseAffectionPresenter;
import game.interface_adapter.PetCard.IncreaseEnergy.IncreaseEnergyController;
import game.interface_adapter.PetCard.IncreaseEnergy.IncreaseEnergyPresenter;
import game.interface_adapter.PetCard.PetCardViewModel;
import game.interface_adapter.ViewManagerModel;
import game.interface_adapter.collections.CollectionsController;
import game.interface_adapter.collections.CollectionsPresenter;
import game.interface_adapter.collections.CollectionsViewModel;
import game.interface_adapter.shop.ShopViewModel;
import game.interface_adapter.shop.ShopPresenter;

import game.use_case.Collections.CollectionsDataAccessInterface;
import game.use_case.Collections.CollectionsInputBoundary;
import game.use_case.Collections.CollectionsInteractor;
import game.use_case.PetCard.IncreaseAffection.IncreaseAffectionInputBoundary;
import game.use_case.PetCard.IncreaseAffection.IncreaseAffectionInteractor;
import game.use_case.PetCard.IncreaseEnergy.IncreaseEnergyInputBoundary;
import game.use_case.PetCard.IncreaseEnergy.IncreaseEnergyInteractor;
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

    private CollectionsView collectionsView;
    private CollectionsViewModel collectionsViewModel;
    private CollectionsPresenter collectionsPresenter;
    private CollectionsController collectionsController;
    private CollectionsDataAccessInterface collectionsDataAccessObject;
    private PetFactDataAccessInterface petFactGateway;

    private PetCardView petCardView;
    // Rename Pet components
    private PetRenameView petRenameView;
    private RenamePetViewModel renamePetViewModel;
    private RenamePetController renamePetController;

    // Sell Pet components
    private SellPetView sellPetView;
    private SellPetViewModel sellPetViewModel;
    private SellPetController sellPetController;

    private PetCardDialog petCardView;
    private PetCardViewModel petCardViewModel;
    private IncreaseEnergyPresenter  increaseEnergyPresenter;
    private IncreaseEnergyController  increaseEnergyController;
    private IncreaseAffectionPresenter increaseAffectionPresenter;
    private IncreaseAffectionController increaseAffectionController;
    private PetCardDataAccessObject  petCardDataAccessObject;


    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addMainView() {
        mainViewModel = new MainViewModel();
        mainView = new MainView(mainViewModel, viewManagerModel);
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
        collectionsViewModel = new CollectionsViewModel();
        collectionsPresenter = new CollectionsPresenter(collectionsViewModel);

        // 2. In-memory implementation of CollectionsDataAccessInterface
        collectionsDataAccessObject = new CollectionsDataAccessInterface() {
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

        petFactGateway = new CompositePetFactDataAccessObject(
                new DogApiPetFactDataAccessObject(),
                new CatApiPetFactDataAccessObject()
        );

        // 3. Interactor + Controller
        CollectionsInputBoundary interactor =
                new CollectionsInteractor(collectionsPresenter, collectionsDataAccessObject, petFactGateway);
        collectionsController = new CollectionsController(interactor);

        petCardView = new PetCardView(collectionsViewModel);
        cardPanel.add(petCardView, PetCardView.VIEW_NAME);

        // 4. Swing view
        collectionsView = new CollectionsView(collectionsViewModel, collectionsController, viewManagerModel,
                petCardView);
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

    public AppBuilder addPetCardUseCases() {
        petCardDataAccessObject = new PetCardDataAccessObject();
        increaseEnergyPresenter = new IncreaseEnergyPresenter(viewManagerModel, petCardViewModel,  petCardDataAccessObject);
        increaseAffectionPresenter = new IncreaseAffectionPresenter(viewManagerModel, petCardViewModel,  petCardDataAccessObject);

        IncreaseEnergyInputBoundary increaseEnergyInteractor = new IncreaseEnergyInteractor(petCardDataAccessObject, increaseEnergyPresenter);
        IncreaseAffectionInputBoundary increaseAffectionInteractor = new IncreaseAffectionInteractor(petCardDataAccessObject, increaseAffectionPresenter);

        increaseEnergyController = new IncreaseEnergyController(increaseEnergyInteractor);
        increaseAffectionController = new IncreaseAffectionController(increaseAffectionInteractor);

        petCardView.setEnergyController(increaseEnergyController);
        petCardView.setAffectionController(increaseAffectionController);

        return this;
    }
    ImageIcon goldenIcon = new ImageIcon(
            getClass().getResource("/images/Pet Images/Dog Images/Golden Retriever Icon.png")
    );
    ImageIcon shephredIcon = new ImageIcon(
            getClass().getResource("/images/Pet Images/Dog Images/German Shepherd Icon.png")
    );
    ImageIcon poodleIcon = new ImageIcon(
            getClass().getResource("/images/Pet Images/Dog Images/Poodle Icon.png")
    );
    ImageIcon boxerIcon = new ImageIcon(
            getClass().getResource("/images/Pet Images/Dog Images/Boxer Icon.png")
    );
    ImageIcon sphynxIcon = new ImageIcon(
            getClass().getResource("/images/Pet Images/Cat Images/Sphynx Icon.png")
    );
    ImageIcon americanShorthairIcon = new ImageIcon(
            getClass().getResource("/images/Pet Images/Cat Images/American Shorthair Icon.png")
    );

    private User createTestUser() {
        User u = new User();

        // Add pets
        Pet p1 = new Pet("Dog", "Golden Retriever", "Common", 100, 5, 10, goldenIcon);
        Pet p2 = new Pet("Dog", "German Shepherd", "Elite", 100, 5, 10, shephredIcon);
        Pet p3 = new Pet("Dog", "Poodle", "Common", 100, 5, 10, poodleIcon);
        Pet p4 = new Pet("Dog", "Boxer", "Common", 100, 5, 10, boxerIcon);
        Pet p5 = new Pet("Cat", "Sphynx", "Elite", 100, 5, 10, sphynxIcon);
        Pet p6 = new Pet("Cat", "American Shorthair", "Common", 100, 5, 10, americanShorthairIcon);

        
        for (int i = 0; i < 10 ; i++) {
            p1.depleteEnergy();
        }

        p1.setName("Max");
        p2.setName("Rex");
        p3.setName("Doodle");
        p4.setName("Kieser");
        p5.setName("Belle");
        p6.setName("Sprinkles");

        u.addToPetInventory(p1);
        u.addToPetInventory(p2);
        u.addToPetInventory(p3);
        u.addToPetInventory(p4);
        u.addToPetInventory(p5);
        u.addToPetInventory(p6);

        // Items
        u.addToItemList(ITEM_CANNED_FOOD);
        u.addToItemList(ITEM_KIBBLE);
        u.addToItemList(ITEM_HOME_COOKED);
        u.addToItemList(ITEM_CHEW_TOY);
        u.addToItemList(ITEM_TOSS_TOY);
        u.addToItemList(ITEM_PLUSH_TOY);

        u.setCoins(10000);

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