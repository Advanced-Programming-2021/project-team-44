package models.utils;

import models.Deck;
import models.cards.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static <T> Object deepClone(T object) {
        if (object == null) return null;
        if (object instanceof ArrayList) return arrayListDeepClone((ArrayList) object);
        if (object instanceof HashMap) return hashMapDeepCLone((HashMap) object);
        if (object instanceof Card) return ((Card) object).clone();
        if (object instanceof Deck) return ((Deck) object).clone();
        return object;
    }

    public static <T> Object arrayListDeepClone(ArrayList<T> list) {
        ArrayList<T> newList = new ArrayList<>();
        for (T item : list)
            newList.add((T) deepClone(item));
        return newList;
    }

    public static <K, V> Object hashMapDeepCLone(HashMap<K, V> map) {
        HashMap<K, V> newMap = new HashMap<>();
        for (Map.Entry<K, V> set : map.entrySet())
            newMap.put((K) deepClone(set.getKey()), (V) deepClone(set.getValue()));
        return newMap;
    }
}
