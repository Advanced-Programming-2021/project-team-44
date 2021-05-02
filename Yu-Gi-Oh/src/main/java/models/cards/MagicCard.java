package models.cards;

import java.util.HashMap;

public class MagicCard extends Card{
    protected MagicType type;
    protected MagicIcon icon;
    private String status;

    public MagicCard() {
    }

    //Utils
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

    public void setType(MagicType type) {
        this.type = type;
    }

    public MagicIcon getIcon() {
        return icon;
    }

    public void setIcon(MagicIcon icon) {
        this.icon = icon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
