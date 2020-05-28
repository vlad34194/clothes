package ro.siit.clothes.model;

public enum Category {
    PANTS(0),TSHIRT(1),SHOES(2),UNDERWEAR(3),TRANING(4),BLOUSE(5);
    private int category;
    private Category(int category) {
        this.category = category;
    }

    public int getCategory() {
        return category;
    }
}
