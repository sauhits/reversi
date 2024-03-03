public class ArrayDB {
    final static int ARRAY_LENGTH = 8;
    final static int INDEX_NONE = 0;
    final static int INDEX_WHITE = 1;
    final static int INDEX_BLACK = -1;
    public static String NAME_WHITE = "white";
    public static String NAME_BLACK = "black";
    private static int[][] WBarray = new int[ARRAY_LENGTH][ARRAY_LENGTH];

    public ArrayDB() {
        fillArrayIndex(0);
        setWBarray(3, 3, -1);
        setWBarray(3, 4, 1);
        setWBarray(4, 3, 1);
        setWBarray(4, 4, -1);
        showWBarray();
    }

    // 配列を特定の数値でfillするメソッド
    public static void fillArrayIndex(int Index) {
        for (int x = 0; x < ARRAY_LENGTH; x++) {
            for (int y = 0; y < ARRAY_LENGTH; y++) {
                WBarray[x][y] = Index;
            }
        }
    }

    public static void showWBarray() {
        // test
        // setWBarray(5, 3, 1);
        // setWBarray(5, 6, 2);
        // test
        // ターンの表示
        switch (Logic.nowStone) {
            case INDEX_WHITE:
                System.out.println("turn: white");
                break;
            default:
                break;
        }
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
}