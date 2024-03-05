public class Show {

    // 最新の情報を表示する
    public static void show() {
        showNowTurn();
        showNowSurface();
    }

    // 最新の盤面を表示する
    public static void showNowSurface() {
        System.out.println("====================");
        System.err.println("  |a|b|c|d|e|f|g|h|");
        for (int y = 0; y < ArrayDB.ARRAY_LENGTH; y++) {
            System.out.print("|" + y + "|");
            for (int x = 0; x < ArrayDB.ARRAY_LENGTH; x++) {
                ArrayDB.processPrintFigure(ArrayDB.getWBarray(x, y));
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }

    // 手番のプレイヤーネームと石の色を表示する
    public static void showNowTurn() {
        System.out.println("====================");
        // ターンの表示
        switch (Logic.nowStone) {
            case ArrayDB.INDEX_WHITE:
                System.out.println("Turn:WHITE:" + "○" + "\n" + "Name:" + ArrayDB.getWhiteName());
                break;
            default:
                System.out.println("Turn:BLACK;" + "●" + "\n" + "Name:" + ArrayDB.getBlackName());
                break;
        }
    }

}
