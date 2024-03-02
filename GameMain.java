/**
 * main
 */
public class GameMain {

    public static void main(String[] args) {
        new ArrayDB();
        while (!Logic.endCheck()) {
            Logic.chooseCoordinate();
        }

    }
}