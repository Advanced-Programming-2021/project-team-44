package Models.Cards;

abstract public class Card {
    protected String name;
    protected String description;
    protected long number;

    //Getters
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public long getNumber() {
        return this.number;
    }

    public abstract String getStringForShow();

    public String getStringForAllCardsShow() {
        return null;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
