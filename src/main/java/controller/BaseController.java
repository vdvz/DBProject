package controller;

import init.Main;
import javafx.scene.Node;

public class BaseController implements Controller {
    private Node view;

    @Override
    public Node getView() {
        return view;
    }

    @Override
    public void setView(Node view) {
        this.view = view;
    }

    @Override
    public void show() {
        Main.getNavigation().show(this);
    }

}
