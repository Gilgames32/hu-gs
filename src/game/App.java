package game;
public class App {
    public static void main(String[] args) {
        // javax.swing.SwingUtilities.invokeLater(new Game());
        World mainGame = new World();
        mainGame.start();
    }
}