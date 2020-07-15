import java.util.HashMap;



/**
 * @author ${user}
 */
public class DebugTest {
    public static void main(String[] args) {
        HashMap<String,String> map = new HashMap<>();
        map.put("name","zhangsan");
        map.put("age","18");
        map.put("school","清华");
        map.put("work","java");
        getMy(map);
        System.out.println("args = [" + args + "]");
        System.out.println("map = " + map);
        map.remove("age");
    }

    private static void getMy(HashMap<String, String> map) {
        System.out.println("map get= [" + map + "]");
    }
}
