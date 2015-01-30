package tpcs.test.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/26 14:30
 */
public class JsonUtil {

    /**
     * 将输入流 转为 json字符串
     * @param in 输入流
     * @return json字符串
     */
    public static String getJsonFromResource(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder builder = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String jsonStr = builder.toString();
        return jsonStr;
    }
}
