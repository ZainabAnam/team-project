package game.app;

import game.entity.Pet;
import game.entity.User;
import game.interface_adapter.ViewManagerModel;
import game.interface_adapter.collections.CollectionsController;
import game.interface_adapter.collections.CollectionsPresenter;
import game.interface_adapter.collections.CollectionsViewModel;
import game.interface_adapter.shop.ShopViewModel;
import game.interface_adapter.shop.ShopPresenter;
import game.use_case.Collections.CollectionsDataAccessInterface;
import game.use_case.Collections.CollectionsInputBoundary;
import game.use_case.Collections.CollectionsInteractor;
import game.view.CollectionsView;
import game.view.MainView;
import game.view.PetCardView;
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
import java.util.HashMap;
import java.util.Map;

import static game.Constants.*;

public class AppBuilder {
    private MainView mainView;
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

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

    private PetCardView petCardView;

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

        // 3. Interactor + Controller
        CollectionsInputBoundary interactor =
                new CollectionsInteractor(collectionsPresenter, collectionsDataAccessObject);
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
        Pet p1 = new Pet("Common", "Golden Retriever", goldenIcon);
        Pet p2 = new Pet("Elite", "German Shepherd", shephredIcon);
        Pet p3 = new Pet("Common", "Poodle", poodleIcon);
        Pet p4 = new Pet("Common", "Boxer", boxerIcon);
        Pet p5 = new Pet("Elite", "Sphynx", sphynxIcon);
        Pet p6 = new Pet("Common", "American Shorthair", americanShorthairIcon);

        p1.setName("Max");
        p2.setName("Rex");
        p3.setName("Doodle");
        p4.setName("Kieser");
        p5.setName("Belle");
        p6.setName("Sprinkles");
        
        for (int i = 0; i < 10 ; i++) {
            p1.depleteEnergy();
        }

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
