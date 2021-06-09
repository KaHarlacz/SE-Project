package controller;

import files_management.Paths;

public final class Controllers {
    private Controllers() {}

    public static MenuController getMenuController() {
        return (MenuController) loadControllerOfScene(Paths.MENU_PANE);
    }

    public static ViewController getAddingNewDishesController() {
        return loadControllerOfScene(Paths.ADD_DISH_PANE);
    }

    public static ViewController getChoosingDishesController() {
        return loadControllerOfScene(Paths.DISH_CHOOSE_PANE);
    }

    public static ViewController getEditCookBookController() {
        return loadControllerOfScene(Paths.EDIT_COOK_BOOK_PANE);
    }

    public static ViewController getSummaryController() {
        return loadControllerOfScene(Paths.SUMMARY_PANE);
    }

    private static ViewController loadControllerOfScene(String path) {
        var loader = new ViewControllerLoader(path);
        var optional = loader.load();
        if (optional.isPresent())
            return optional.get();
        throw new IllegalArgumentException("Cannot load controller from file");
    }
}
