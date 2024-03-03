/**
 * main
 */
public class GameMain {

    public static void main(String[] args) {
        new ArrayDB();
        while (true) {
            // 座標の指定
            Logic.chooseCoordinate();
            ArrayDB.setWBarray(Logic.chooseX, Logic.chooseY, Logic.nowStone);
        }
    }
}