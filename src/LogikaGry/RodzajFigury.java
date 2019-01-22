package LogikaGry;

public enum RodzajFigury {
    BIALA_KROLOWA,
    BIALY_PIONEK,
    CZARNA_KROLOWA,
    CZARNY_PIONEK;

    public Kolor getKolor() {
        switch (this) {
            case BIALY_PIONEK:
                return Kolor.BIALY;
            case BIALA_KROLOWA:
                return Kolor.BIALY;
            case CZARNY_PIONEK:
                return Kolor.CZARNY;
            case CZARNA_KROLOWA:
                return Kolor.CZARNY;
            default:
                throw new IllegalArgumentException();

        }
    }

    @Override
    public String toString() {
        switch (this){
            case BIALY_PIONEK:
                return "P";
            case CZARNY_PIONEK:
                return "p";
            case BIALA_KROLOWA:
                return "Q";
            case CZARNA_KROLOWA:
                return "q";
            default:
                throw new IllegalArgumentException();
        }
    }
}
