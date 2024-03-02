import java.util.Scanner;

public class Logic {
    public static int chooseX;
    public static int chooseY;
    // white:1 black:2
    public static int nowStone;
    // 隣接している石の位置と色を格納
    // 0 1 2
    // 3 ● 4
    // 5 6 7
    public static int[] aroundStone = new int[8];

    public Logic() {
    }

    // 座標を指定する
    public static void chooseCoordinate() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("--------------------" + "\n" + "どこに置きますか？(123): ");
            chooseY = sc.nextInt();
            System.out.print("\n" + "どこに置きますか？(abc): ");
            String strAlpha = sc.next();
            chooseX = strAlphaToInt(strAlpha);
            // 隣接を確認
            if (!adjoinCheck(chooseX, chooseY)) {
                break;
            }
        }
    }

    // アルファベット指定をIntに変換する
    public static int strAlphaToInt(String alpha) {
        switch (alpha) {
            case "a":
                return 0;
            case "b":
                return 1;
            case "c":
                return 2;
            case "d":
                return 3;
            case "e":
                return 4;
            case "f":
                return 5;
            case "g":
                return 6;
            case "h":
                return 7;
            default:
                return 0;

        }
    }

    // 隣接する石の位置と色を配列にして返す
    public static void stoneCheck(int x, int y) {
        // 0 1 2
        // 3 ● 4
        // 5 6 7
        int count = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                // 指定座標を省く
                if (i != 0 || j != 0) {
                    if (ArrayDB.checkOutOfIndex(x, y)) {
                        aroundStone[count] = ArrayDB.getWBarray(x, y);
                    }
                    count++;
                }
            }
        }
    }

    // 置くことができるか確認する
    public static boolean installableCheck(int x, int y) {
        // 隣接した石の状況を格納した配列を取得
        aroundStone = stoneCheck(x, y);
        for (int i = 0; i < 8; i++) {

        }

    }

    // コマと隣接しているかどうか
    // true: 隣接している false: 隣接していない
    public static boolean adjoinCheck(int x, int y) {
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

    // 終了条件の確認
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
