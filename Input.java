import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    // 座標を指定する
    public static void chooseCoordinate() {
        while (true) {
            try {
                inputYCoordinate();
                // 配列範囲の確認
                if ((Logic.chooseY < 8) || (Logic.chooseY > 0)) {
                    inputXCoordinate();
                    // 想定数値の範囲外なら-1を返すのでこれで確認
                    if (Logic.chooseX != -1) {
                        // 隣接を確認
                        if (Logic.adjoinCheck(Logic.chooseX, Logic.chooseY)) {
                            System.out.println("--------------------" + "\n" + "adjust");
                            // 隣接するコマの中に返すことができるコマがあるか確認
                            int[] distanceOfTurnable = new int[8];
                            distanceOfTurnable = Logic.updateIncrement(Logic.chooseX, Logic.chooseY);
                            for (int i = 0; i < 8; i++) {
                                // 空白もしくは同色なら何もしない
                                if (distanceOfTurnable[i] < 2) {
                                    continue;
                                } else {
                                    // 他色なら同色までたどって返す
                                    for (int j = 1; j < distanceOfTurnable[i]; j++) {
                                        int[] increment = new int[2];
                                        increment = Logic.aroundStoneToIncrement(i);
                                        ArrayDB.setWBarray((Logic.chooseX + (increment[0] * j)),
                                                (Logic.chooseY + (increment[1] * j)),
                                                Logic.nowStone);
                                    }
                                }
                            }
                            return;
                        }
                    } else {
                        System.out.println("--------------------" + "\n" + "Please check alphabet");
                    }
                } else {
                    System.out.println("--------------------" + "\n" + "Please check number");
                }
                System.out.println("--------------------" + "\n" + "Please check adjust");
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("--------------------" + "\n" + "Please check language");
            }
        }
    }

    // y座標を指定する
    public static void inputYCoordinate() {
        Scanner sc = new Scanner(System.in);
        // y座標の指定（数値指定）
        System.out.print("--------------------" + "\n" + "どこに置きますか？(123): ");
        Logic.chooseY = sc.nextInt();
    }

    // x座標を指定する
    public static void inputXCoordinate() {
        Scanner sc = new Scanner(System.in);
        // x座標の指定（アルファベット)
        System.out.print("\n" + "どこに置きますか？(abc): ");
        String strAlpha = sc.next();
        // アルファベットから数値に変換
        Logic.chooseX = Logic.strAlphaToInt(strAlpha);
    }

}
