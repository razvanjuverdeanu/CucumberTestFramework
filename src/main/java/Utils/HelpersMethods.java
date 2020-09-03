package Utils;

import java.util.List;
import java.util.Random;

public class HelpersMethods {
    public static String getRandomStringFromList(List<String> list) {
        int listSize = list.size();
        Random random = new Random();
        int index = random.nextInt(listSize - 1) + 1;
        return list.get(index);
    }
}
