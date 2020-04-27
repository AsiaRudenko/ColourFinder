import java.util.Hashtable;
import java.util.Set;

class ColoursStore {

    public Hashtable<String, Integer> coloursCounterDict;

    ColoursStore(){
        Hashtable<String, Integer> coloursCounter = new Hashtable<>();
        for (Colour colour : Colour.values()) {
            coloursCounter.put(colour.toString(), 0);
        }
        coloursCounterDict = coloursCounter;
    }

    public void Update(ColoursStore source){
        var sourceDict = source.coloursCounterDict;
        Set<String> setOfColours = sourceDict.keySet();
        for (String key : setOfColours){
            int number = sourceDict.get(key);
            if (number != 0)
                this.coloursCounterDict.put(key,this.coloursCounterDict.get(key)+number);
        }
    }
}
