package chaplinskiy.crud;

import chaplinskiy.crud.view.RunnerView;

import static chaplinskiy.crud.util.DbInit.initDatabase;

public class Main {
    public static void main(String[] args) {
        initDatabase();
        RunnerView.getInstance().run();
    }
}
