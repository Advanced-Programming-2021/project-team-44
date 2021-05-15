package models.comparators;

import models.cards.Card;

import java.util.Collections;
import java.util.Comparator;

public class CardSortByName implements Comparator<Card> {
    @Override
    public int compare(Card card1, Card card2) {
        return card1.getName().compareTo(card2.getName());
    }

 //   Collections.sort(cards, new SortByName());
//    cards = ArrayList<Card>
}
