package controller;

import java.util.List;

abstract class MenuController extends ViewController {
    abstract void setMenuReferences(List<ViewController> controllerList);
}
