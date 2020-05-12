import java.util.Random;

public class DebuggingExceptions {
    public static void main(String args[]) throws Exception {
        int i = 0;
        while (i<100000){
            try {
                DoSomething();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    public static void DoSomething() throws Exception {
        Random random = new Random();
        int r = random.nextInt(100000);
        System.out.println(r);
        if (ShouldThrow(r)){
            throw new Exception();
        }
    }




    public static boolean ShouldThrow(int r){
        return r<2;
    }
}
