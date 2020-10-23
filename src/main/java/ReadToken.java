import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadToken {
    public static String loadToken() {
        //Vars
        File tokenFile = new File("token.txt");
        try {
            Scanner s = new Scanner(tokenFile);
            return s.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Couldn't read token";
    }
}
