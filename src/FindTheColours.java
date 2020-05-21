import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

public class FindTheColours {

    public static class ColourFinder
    {
        public static void main(String[] args)
        {
            String fileName = "data/Wiki.txt";
            var delayProvider = new DelayProvider();
            if (delayProvider.Millis!=0) System.out.println("Hi! This is gonna take a while...");
            else System.out.println("Hi! Gonna be fast...");

            var coloursStore = new ColoursStore();
            coloursStore = ReadTheColours(fileName, delayProvider.Millis);

            ListTheColours(coloursStore);
        }

        private static ColoursStore ReadTheColours(String fileName, int millis) {
            BufferedReader reader;
            var coloursStore = new ColoursStore();
            try
            {
                reader = new BufferedReader(new FileReader(fileName));
                String line = reader.readLine().toLowerCase();
                int lineCounter = 0;
                while (line != null){
                    if (millis!=0) SlowDown(millis);
                    var coloursInLine = ContainsAColour(line);
                    coloursStore.Update(coloursInLine);
                    lineCounter++;
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return coloursStore;
        }

        public static ColoursStore ContainsAColour(String line){
            ColoursStore coloursInLine = new ColoursStore();
            for (Colour colour : Colour.values()) {
                String colourString = colour.toString();
                int lastIndex = 0;
                int count = 0;
                while (lastIndex != -1){
                    lastIndex = line.indexOf(colourString, lastIndex);
                    if (lastIndex!=-1){
                        count++;
                        lastIndex += colourString.length();
                    }
                }
                coloursInLine.coloursCounter.put(colourString, coloursInLine.coloursCounter.get(colourString)+count);
            }
            return coloursInLine;
        }

        public static void ListTheColours(ColoursStore coloursStore){
            Set<String> setOfColours = coloursStore.coloursCounter.keySet();
            for (String key : setOfColours){
                System.out.println(key+": "+ coloursStore.coloursCounter.get(key));
            }
        }

        public static void SlowDown(int millis){
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
