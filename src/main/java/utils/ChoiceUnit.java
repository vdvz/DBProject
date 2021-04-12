package utils;

public class ChoiceUnit {

    private final String displayedName;
    private final String id;

    public ChoiceUnit(String id, String displayedName){
        this.id = id;
        this.displayedName = displayedName;
    }

    public String getId() {
        return id;
    }

    public String getDisplayedName() {
        return displayedName;
    }

}
