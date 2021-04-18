package models.cards;

abstract public class Card {
    protected String name;
    protected String description;
    protected long id;

    //Getters
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public long getId() {
        return this.id;
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

    public void setId(long id) {
        this.id = id;
    }
}
