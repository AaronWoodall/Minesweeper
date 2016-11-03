package minesweeper.game.screensystem;

import minesweeper.game.mine.Mine;

public class MainMenuScreen extends Screen {
    public MainMenuScreen() {
        renderObjects.add(new Mine());
    }
}
