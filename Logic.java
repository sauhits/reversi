import java.util.Scanner;

public class Logic {
    public static int chooseX;
    public static int chooseY;

    public Logic() {

    }

    public static boolean chooseCoordinate(int x, int y, int Index) {
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("--------------------" + "\n" + "どこに置きますか？(123): ");

        } while (adjoinCheck(x, y));

    }

    public static int[] whiteCheck(int x, int y) {
        // 0 1 2
        // 3 ● 4
        // 5 6 7
        int[] whiteStone = new int[8];

        return whiteStone;
    }

    // true: 隣接している
    // false: 隣接していない
    public static boolean adjoinCheck(int x, int y) {
        // コマと隣接しているかどうか
        int sumIndex = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                sumIndex += ArrayDB.getWBarray((x + i), (y + j));
            }
        }
        sumIndex -= ArrayDB.getWBarray(x, y);
        // 指定コマ周辺のIndexを合計し，0であるか判定する．
        // 周囲がすべてnoneである場合，SUMは0となる．
        return sumIndex != ArrayDB.INDEX_NONE;
    }

    public static boolean endCheck() {
        for (int x = 0; x < ArrayDB.ARRAY_LENGTH; x++) {
            for (int y = 0; y < ArrayDB.ARRAY_LENGTH; y++) {
                if (ArrayDB.getWBarray(x, y) == ArrayDB.INDEX_NONE) {
                    return false;
                }
            }
        }
        return true;
    }
}
