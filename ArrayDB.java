public class ArrayDB {
    final static int ARRAY_LENGTH = 8;
    final static int INDEX_NONE = 0;
    final static int INDEX_WHITE = 1;
    final static int INDEX_BLACK = -1;
    private static String NAME_WHITE = "white";
    private static String NAME_BLACK = "black";
    private static int[][] WBarray = new int[ARRAY_LENGTH][ARRAY_LENGTH];

    public ArrayDB() {
        fillArrayIndex(0);
        setWBarray(3, 3, -1);
        setWBarray(3, 4, 1);
        setWBarray(4, 3, 1);
        setWBarray(4, 4, -1);
    }

    // 配列を特定の数値でfillするメソッド
    public static void fillArrayIndex(int Index) {
        for (int x = 0; x < ARRAY_LENGTH; x++) {
            for (int y = 0; y < ARRAY_LENGTH; y++) {
                WBarray[x][y] = Index;
            }
        }
    }

    // 盤面の表示
    public static void showWBarray() {
        // test
        // setWBarray(5, 3, 1);
        // setWBarray(5, 6, 2);
        // test
        // 手番の表示
        System.out.println("====================");
        showNowTurn();
        System.err.println("  |a|b|c|d|e|f|g|h|");
        for (int y = 0; y < ARRAY_LENGTH; y++) {
            System.out.print("|" + y + "|");
            for (int x = 0; x < ARRAY_LENGTH; x++) {
                processPrintFigure(WBarray[x][y]);
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }

    // 手番のプレイヤーネームと石の色を表示する
    public static void showNowTurn() {
        // ターンの表示
        switch (Logic.nowStone) {
            case INDEX_WHITE:
                System.out.println("Turn:WHITE" + "\n" + "Name:" + NAME_WHITE);
                break;
            default:
                System.out.println("Turn:BLACK" + "\n" + "Name:" + NAME_BLACK);
                break;
        }
    }

    // Indexに応じて表示する記号を変更する
    public static void processPrintFigure(int Index) {
        switch (Index) {
            case INDEX_NONE:
                System.out.print(" ");
                break;
            case INDEX_WHITE:
                System.out.print("○");
                break;
            case INDEX_BLACK:
                System.out.print("●");
                break;
            default:
                break;
        }
    }

    // 配列範囲内であるか確認
    public static boolean checkOutOfIndex(int x, int y) {
        if (((x | y) < 0) || ((x | y) > 7)) {
            return false;
        } else {
            return true;
        }
    }

    public static void setWBarray(int x, int y, int Index) {
        try {
            WBarray[x][y] = Index;
            Logic.nowStone *= -1;
        } catch (IndexOutOfBoundsException i) {
        }
    }

    public static int getWBarray(int x, int y) {
        try {
            return WBarray[x][y];
        } catch (IndexOutOfBoundsException i) {
            System.out.println(i);
            return 99;
        }
    }

    public static void setWhiteName(String whiteName) {
        NAME_WHITE = whiteName;
    }

    public static String getWhiteName() {
        return NAME_BLACK;
    }

    public static void setBlackName(String blackName) {
        NAME_BLACK = blackName;
    }

    public static String getBlackName() {
        return NAME_BLACK;
    }
}
