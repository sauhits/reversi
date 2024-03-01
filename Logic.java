public class Logic {
    public Logic() {

    }

    public static boolean chooseCoordinate(int x, int y, int Index) {
        // コマと隣接しているかどうか
        int sumIndex = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                sumIndex += ArrayDB.getWBarray((x + i), (y + j));
            }
        }
        sumIndex-=ArrayDB.getWBarray(x, y);
        
    }

    public static boolean endCheck() {
        for (int x = 0; x < (ArrayDB.ARRAY_LENGTH + 1); x++) {
            for (int y = 0; y < (ArrayDB.ARRAY_LENGTH + 1); y++) {
                if (ArrayDB.getWBarray(x, y) == ArrayDB.INDEX_NONE) {
                    return false;
                }
            }
        }
        return true;
    }
}
