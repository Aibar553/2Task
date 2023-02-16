import java.util.Map;
import java.util.HashMap;

public class First {
    public static void main(String[] args) {
        Map<String, String> params1 = new HashMap<String, String>();
        params1.put("name", "Gerard");
        params1.put("country", "Spain");
        params1.put("city", "Barcelona");
        params1.put("age", null);
        System.out.println(getQuery(params1));
    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder s = new StringBuilder();
        for (Map.Entry<String, String> pair : params.entrySet()) {
            if (pair.getValue() != null) {
                s.append(pair.getKey() + " = '" + pair.getValue() + "' and ");
            }
        }
        s.delete(s.toString().length() - 5, s.toString().length());
        return s.toString();
    }
}