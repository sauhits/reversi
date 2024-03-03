import java.util.InputMismatchException;
import java.util.Scanner;

public class Logic {
    public static int chooseX;
    public static int chooseY;
    // white:1 black:2
    public static int nowStone = -1;
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
            try {
                new ArrayDB();
                Scanner sc = new Scanner(System.in);
                System.out.print("--------------------" + "\n" + "どこに置きますか？(123): ");
                chooseY = sc.nextInt();
                System.out.print("\n" + "どこに置きますか？(abc): ");
                String strAlpha = sc.next();
                chooseX = strAlphaToInt(strAlpha);
                // 隣接を確認
                if (adjoinCheck(chooseX, chooseY)) {
                    System.out.println("adjust");
                    return;
                }
                System.out.println("Please adjust");
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Please check language");
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

    // aroundStone配列から指定座標への差分を返す
    public static int[] aroundStoneToIncrement(int aroundStoneNum) {
        int[] increment = new int[2];
        // 0 1 2
        // 3 ● 4
        // 5 6 7
        if (aroundStoneNum == (0 | 1 | 2)) {
            increment[1] = 1;
        }
        if (aroundStoneNum == (5 | 6 | 7)) {
            increment[1] = -1;
        }
        if (aroundStoneNum == (0 | 3 | 5)) {
            increment[0] = -1;
        }
        if (aroundStoneNum == (2 | 4 | 7)) {
            increment[0] = 1;
        }
        return increment;
    }

    // 置くことができるか確認する
    public static boolean installableCheck(int x, int y) {
        // 隣接した石の状況を格納した配列を取得
        stoneCheck(x, y);
        // 隣接した石を走査する
        for (int i = 0; i < 8; i++) {
            // targetStoneに隣接座標を格納
            int[] increment = aroundStoneToIncrement(i);
            int[] targetStone = new int[2];
            targetStone[0] = x + increment[0];
            targetStone[1] = y + increment[1];
            // 配列の範囲確認(周囲8マス)
            if (ArrayDB.checkOutOfIndex(targetStone[0], targetStone[1])) {
                // 隣接石が存在するかどうかの確認
                if (aroundStone[i] != ArrayDB.INDEX_NONE) {
                    // 隣接石が他色であるならば同色までたどる
                    for (int j = 0;;) {
                        // 調べる石が配列範囲内であるか確認
                        targetStone[0] += increment[0];
                        targetStone[1] += increment[1];
                        if (ArrayDB.checkOutOfIndex(targetStone[0], targetStone[1])) {
                            // 調べる石の色を判定する
                            if(ArrayDB.getWBarray(targetStone[0], targetStone[1])!=)
                        }
                    }
                }
            }
        }
    }

    // 指定した座標の石が同色であるか他色であるかを返す
    // 空白マスである場合はnullを返す
    public static Boolean checkSameColor(int x, int y) {
        try {
            int color = ArrayDB.getWBarray(x, y);
            if (color == nowStone) {
                return true;
            } else if (color == ArrayDB.INDEX_NONE) {
                return null;
            } else {
                return false;
            }
        } catch (IndexOutOfBoundsException i) {
            return false;
        }

    }

    // コマと隣接しているかどうか
    // true: 隣接している false: 隣接していない
    public static Boolean adjoinCheck(int x, int y) {
        int sumIndex = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (ArrayDB.checkOutOfIndex(x, y)) {
                    sumIndex += ArrayDB.getWBarray((x + i), (y + j));
                }

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
