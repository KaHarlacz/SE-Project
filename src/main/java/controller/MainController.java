package controller;

import java.util.List;

public class MainController extends ApplicationController {
    private MenuController mainMenuController;
    private ViewController choosingDishesController;
    private ViewController summaryController;
    private ViewController editCookBookController;
    private ViewController addNewDishController;

    @Override
    public void init() {
        setUpStage();
        getChildren();
        setChildrenParent();
        setUpNavigation();
        initChildren();
    }

    @Override
    public void start() {
        goToSceneOf(mainMenuController);
        stage.show();
    }

    @Override
    public void exit() {
        stage.close();
        mainMenuController.exit();
        choosingDishesController.exit();
        summaryController.exit();
        editCookBookController.exit();
        addNewDishController.exit();
    }

    private void setUpNavigation() {
        mainMenuController.setMenuReferences(List.of(choosingDishesController, editCookBookController));
        choosingDishesController.setNext(summaryController);
        choosingDishesController.setPrev(mainMenuController);
        summaryController.setNext(mainMenuController);
        summaryController.setPrev(choosingDishesController);
        editCookBookController.setNext(addNewDishController);
        editCookBookController.setPrev(mainMenuController);
        addNewDishController.setPrev(editCookBookController);
    }

    private void setUpStage() {
        stage.setOnCloseRequest(e -> this.exit());
        stage.setTitle("Shopping Organiser");
    }

    private void setChildrenParent() {
        mainMenuController.setParent(this);
        choosingDishesController.setParent(this);
        summaryController.setParent(this);
        editCookBookController.setParent(this);
        addNewDishController.setParent(this);
    }

    private void getChildren() {
        mainMenuController = Controllers.getMenuController();
        choosingDishesController = Controllers.getChoosingDishesController();
        summaryController = Controllers.getSummaryController();
        editCookBookController = Controllers.getEditCookBookController();
        addNewDishController = Controllers.getAddingNewDishesController();
    }

    private void initChildren() {
        mainMenuController.init();
        choosingDishesController.init();
        summaryController.init();
        editCookBookController.init();
        addNewDishController.init();
        super.currentController = addNewDishController;
    }
}
