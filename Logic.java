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
                ArrayDB.showWBarray();
                Scanner sc = new Scanner(System.in);
                // y座標の指定（数値指定）
                System.out.print("--------------------" + "\n" + "どこに置きますか？(123): ");
                chooseY = sc.nextInt();
                // 配列範囲の確認
                if ((chooseY < 8) || (chooseY > 0)) {
                    // x座標の指定（アルファベット)
                    System.out.print("\n" + "どこに置きますか？(abc): ");
                    String strAlpha = sc.next();
                    // アルファベットから数値に変換
                    chooseX = strAlphaToInt(strAlpha);
                    // 想定数値の範囲外なら-1を返すのでこれで確認
                    if (chooseX != -1) {
                        // 隣接を確認
                        if (adjoinCheck(chooseX, chooseY)) {
                            System.out.println("adjust");
                            // 隣接するコマの中に返すことができるコマがあるか確認
                            int[] distanceOfTurnable = new int[8];
                            distanceOfTurnable = updateIncrement(chooseX, chooseY);
                            for (int i = 0; i < 8; i++) {
                                // 空白もしくは同色なら何もしない
                                if (distanceOfTurnable[i] < 2) {
                                    continue;
                                } else {
                                    // 他色なら同色までたどって返す
                                    for (int j = 1; j < distanceOfTurnable[i]; j++) {
                                        int[] increment = new int[2];
                                        increment = aroundStoneToIncrement(i);
                                        ArrayDB.setWBarray((chooseX + (increment[0] * j)),
                                                (chooseY + (increment[1] * j)),
                                                nowStone);
                                    }
                                }
                            }
                            return;
                        }
                    } else {
                        System.out.println("Please check alphabet");
                    }
                } else {
                    System.out.println("Please check number");
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
                return -1;

        }
    }

    // 隣接する石の色を配列にして返す
    public static int[] updateAroundStone(int x, int y) {
        // 0 1 2
        // 3 ● 4
        // 5 6 7
        int count = 0;
        int[] around = new int[8];
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                // 指定座標を省く
                if (i != 0 || j != 0) {
                    if (ArrayDB.checkOutOfIndex(x, y)) {
                        around[count] = ArrayDB.getWBarray(x, y);
                    }
                    count++;
                }
            }
        }
        return around;
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

    // 近くの連続した他色の先にある同色石までの距離を方向とともに返す
    public static int[] updateIncrement(int x, int y) {
        int[] distance = new int[8];
        // aroundStoneを更新する
        aroundStone = updateAroundStone(x, y);
        for (int i = 0; i < 8; i++) {
            // 方向に応じた座標差分を取得する
            int[] increment = new int[2];
            increment = aroundStoneToIncrement(i);
            // 調べる座標を作成する
            int[] targetCoordinate = new int[2];
            targetCoordinate[0] = x + increment[0];
            targetCoordinate[1] = y + increment[1];
            // 走査開始
            for (int j = 1;; j++) {
                // 配列範囲の確認
                if (!ArrayDB.checkOutOfIndex(targetCoordinate[0], targetCoordinate[1])) {
                    // 配列範囲外なら離脱
                    break;
                } else {
                    // 色の確認
                    if (ArrayDB.getWBarray(targetCoordinate[0], targetCoordinate[1]) == nowStone) {
                        // 同色なら離脱
                        distance[i] = j;
                        break;
                    } else {
                        // 他色なら進む
                        targetCoordinate[0] += increment[0];
                        targetCoordinate[1] += increment[1];
                    }
                }
            }
        }
        return distance;
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
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (0 == (i | j)) {
                    // 指定座標を除外する
                    continue;
                }
                // 配列範囲の確認
                if (ArrayDB.checkOutOfIndex((x + i), (y + j))) {
                    // 隣接マスがnoneでないか確認
                    if (ArrayDB.getWBarray((x + i), (y + j)) != ArrayDB.INDEX_NONE) {
                        return true;
                    }
                }
            }
        }
        return false;
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
