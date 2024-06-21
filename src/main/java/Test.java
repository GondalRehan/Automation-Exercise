import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        String str = "Guitar is instrument and Piano is instrument";

        String[] strArray = str.split("\\s+");

        List<String> list2 = new ArrayList<String>();

        Map<String, String> hMap = new LinkedHashMap<String, String>();
        for(int i = 0; i < strArray.length ; i++ ) {
            if(!hMap.containsKey(strArray[i])) {
                list2.add(strArray[i]);
                hMap.put(strArray[i],"Unique");
            }
        }
        System.out.println(hMap);
        System.out.println(list2);
    }
}
