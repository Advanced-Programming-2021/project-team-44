package models.utils;

import models.cards.Card;

import java.util.ArrayList;

public class Utils {
    public static ArrayList<Card> cardArrayListDeepClone(ArrayList<Card> list) {
        ArrayList<Card> newList = new ArrayList<>();
        for (Card card : list)
            newList.add((Card) card.clone());
        return newList;
    }
}
