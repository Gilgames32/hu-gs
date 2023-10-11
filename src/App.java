public class App {
    public static void main(String[] args) {
        System.out.println("start");
        Thread game = new Game();
        game.start();
        // javax.swing.SwingUtilities.invokeLater(new Game());
    }
}