package com.liang.redisdemo.utils.Testdouhao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kfz
 * @create 2023-05-12 14:36
 */
public class test {


    private static Map<String, String> loadRepairFiels(String configValue) throws Exception {
        Map<String, String> fieldData = new HashMap<String, String>();
        if (!SyncUtil.isEmpty(configValue)) {
            String[] fields = configValue.split(",");
            for (String field : fields) {
                int begin = field.indexOf("[");
                int end = field.indexOf("]");
                String srcField = field.substring(0, begin);
                String tarField = field.substring(begin + 1, end);
                fieldData.put(srcField, tarField);
            }
        }
        return fieldData;
    }

    public static void main(String[] args) throws Exception {
         String configValue ="ZJ[XH],XGZD[DSH],URL[url]";
         Map<String, String> fieldData=loadRepairFiels(configValue);
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        for (Map.Entry<String, String> entry : fieldData.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
        }


        URL url = new URL("https://example.com/api");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        String jsonInputString = "{\"name\": \"John Doe\", \"age\": 25}";

        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }





    }
}
