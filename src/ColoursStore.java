import java.util.Hashtable;
import java.util.Set;

class ColoursStore {

    public Hashtable<String, Integer> coloursCounter;

    ColoursStore(){
        Hashtable<String, Integer> coloursCounter = new Hashtable<>();
        for (Colour colour : Colour.values()) {
            coloursCounter.put(colour.toString(), 0);
        }
        this.coloursCounter = coloursCounter;
    }

    public void Update(ColoursStore source){
        var sourceDict = source.coloursCounter;
        Set<String> setOfColours = sourceDict.keySet();
        for (String key : setOfColours){
            int number = sourceDict.get(key);
            if (number != 0)
                this.coloursCounter.put(key,this.coloursCounter.get(key)+number);
        }
    }
}
