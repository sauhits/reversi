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

    // 手番を交代する
    public static void changeTurn() {
        Logic.nowStone *= -1;
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
