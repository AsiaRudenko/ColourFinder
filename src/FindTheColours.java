import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Set;

public class FindTheColours {

    public static class Reader
    {
        public static void main(String[] args)
        {
            int millis = 1000;
            String fileName = "data/Wiki.txt";

            if (millis!=0) System.out.println("Hi! This is gonna take a while...");
            else System.out.println("Hi! Gonna be fast...");

            var coloursStore = new ColoursStore();
            coloursStore = ReadTheColours(fileName, millis);

            ListTheColours(coloursStore);
        }

        private static ColoursStore ReadTheColours(String fileName, int millis) {
            BufferedReader reader;
            var coloursStore = new ColoursStore();
            try
            {
                reader = new BufferedReader(new FileReader(fileName));
                String line = reader.readLine();
                while (line != null){
                    if (millis!=0) SlowDown(millis);
                    line = line.toLowerCase();
                    var coloursInLine = ContainsAColour(line);
                    coloursStore.Update(coloursInLine);
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
                coloursInLine.coloursCounterDict.put(colourString, coloursInLine.coloursCounterDict.get(colourString)+count);
            }
            return coloursInLine;
        }

        public static void ListTheColours(ColoursStore coloursStore){
            Set<String> setOfColours = coloursStore.coloursCounterDict.keySet();
            for (String key : setOfColours){
                System.out.println(key+": "+ coloursStore.coloursCounterDict.get(key));
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
