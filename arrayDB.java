public class ArrayDB {
    final static int ARRAY_LENGTH = 8;
    public static int INDEX_NONE = 0;
    public static int INDEX_WHITE = 1;
    public static int INDEX_BLACK = 2;
    private static int[][] WBarray = new int[ARRAY_LENGTH][ARRAY_LENGTH];

    public ArrayDB() {
        fillArrayIndex(0);
        setWBarray(4, 4, 2);
        setWBarray(4, 5, 1);
        setWBarray(5, 4, 1);
        setWBarray(5, 5, 2);
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
        System.err.println("  |1|2|3|4|5|6|7|8|");
        for (int y = 0; y < ARRAY_LENGTH; y++) {
            System.out.print("|" + (y + 1) + "|");
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
            case 0:
                // none
                System.out.print(" ");
                break;
            case 1:
                // White
                System.out.print("○");
                break;
            case 2:
                // Black
                System.out.print("●");
                break;

            default:
                break;
        }
    }

    public static void setWBarray(int x, int y, int Index) {
        WBarray[(x - 1)][(y - 1)] = Index;
    }

    public static int getWBarray(int x, int y) {
        return WBarray[(x - 1)][(y - 1)];
    }
}