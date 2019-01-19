package LogikaGry;

public enum Kolor {
    BIALY,
    CZARNY;
    public int getWartosc(){
        switch (this){
            case BIALY:
                return 1;
            case CZARNY:
                return -1;
            default:
                throw new IllegalArgumentException();
        }
    }
    public Kolor opposite(){
        switch (this){
            case BIALY:
                return CZARNY;
            case CZARNY:
                return BIALY;
            default:
                throw new IllegalArgumentException();
        }
    }
}
