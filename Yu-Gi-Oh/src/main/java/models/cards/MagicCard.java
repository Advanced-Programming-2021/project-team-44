package models.cards;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MagicCard extends Card{
    protected MagicType type;
    protected MagicIcon icon;
    private String status;

    public MagicCard() {
    }

    public static MagicCard createMagicCard(String name) {
        MagicCard template = (MagicCard) Card.getCardByName(name);
        assert template != null;
        return (MagicCard) template.clone();
    }

    //Utils
    public static HashMap<String, String> getHashMapFromString(String data) {
        String[] dataSplit = data.split(",(?!\\s)");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Name", dataSplit[0]);
        hashMap.put("Type", dataSplit[1]);
        hashMap.put("Icon", dataSplit[2]);
        hashMap.put("Description", dataSplit[3]);
        hashMap.put("Status", dataSplit[4]);
        hashMap.put("Price", dataSplit[5]);
        return hashMap;
    }

    public static ArrayList<Card> magicCardsInitializer() {
        //TODO LOG
        ArrayList<Card> magicCards = new ArrayList<>();
        String path = File.separator + "." +
                File.separator + "data" +
                File.separator + "static" +
                File.separator + "cards" +
                File.separator + "SpellTrap.csv";
        try {
            File source = new File(path);
            Scanner reader = new Scanner(source);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                HashMap<String, String> toBeAddedCard = getHashMapFromString(data);
                //File Creation
                String newCardFilePath = File.separator + "." +
                        File.separator + "data" +
                        File.separator + "static" +
                        File.separator + "cards" +
                        File.separator + "magic" +
                        File.separator + toBeAddedCard.get("Name") + ".json";
                File newCardFile = new File(newCardFilePath);
                try {
                    FileWriter writer = new FileWriter(newCardFile.getAbsolutePath());
                    String jsonData = generateJSONByHashMap(toBeAddedCard);
                    writer.write(jsonData);
                    writer.write(jsonData);
                    writer.close();
                    GsonBuilder builder = new GsonBuilder();
                    builder.setPrettyPrinting();
                    Gson gson = builder.create();
                    MagicCard tmpMagicCard = gson.fromJson(jsonData, MagicCard.class);
                    magicCards.add(tmpMagicCard);
                } catch (IOException e) {
                    System.out.println("Can't parse magics JSON Files!");
                    //Error Code: Can't parse JSON files - Parse Error: 3
                    System.exit(3);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Magics source file missing!");
            //Error Code: Source not found - Source Error: 2
            System.exit(2);
        }
        return magicCards;
    }

    public static String generateJSONByHashMap(HashMap<String, String> hashMap) {
        String jsonData = "{\"name\":\"" + hashMap.get("Name") + "\", " +
                "\"type\":\"" + hashMap.get("Type") + "\", " +
                "\"icon\":\"" + hashMap.get("Icon") + "\", " +
                "\"description\":\"" + hashMap.get("Description") + "\", " +
                "\"status\":\"" + hashMap.get("Status") + "\", " +
                "\"price\":" + Integer.parseInt(hashMap.get("Price")) + "}";
        return jsonData;
    }

    //Getters and Setters
    public MagicType getType() {
        return type;
    }

    public MagicIcon getIcon() {
        return icon;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String getStringForShow() {
        String magicCardInfo;
        magicCardInfo = "Name: " + this.getName() + "\n" +
                this.getType() + "\n" +
                "Type: " + this.getIcon() + "\n" +
                "Description: " + this.getDescription();
        return magicCardInfo;
    }

    @Override
    public HashMap<String, String> getHashMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Name", this.name);
        hashMap.put("Type", this.type.stringName);
        hashMap.put("Icon", this.icon.stringName);
        hashMap.put("Description", this.description);
        hashMap.put("Status", this.status);
        hashMap.put("Price", String.valueOf(this.price));
        return hashMap;
    }

    @Override
    protected Object clone() {
        MagicCard dummy = new MagicCard();
        dummy.name = this.name;
        dummy.description = this.description;
        dummy.price = this.price;
        dummy.type = this.type;
        dummy.icon = this.icon;
        dummy.status = this.status;
        return dummy;
    }
}
