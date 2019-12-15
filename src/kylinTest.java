import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yongbo1 on 2019/8/30.
 */
public class kylinTest {
    private static final String baseURL = "http://10.222.80.65:7070/kylin/api";

    public static String createModel() {
        String method = "POST";
        String para = "/models";
        String modelDescData = "{\n" +
                "    \"name\": \"model_test\",\n" +
                "     \"description\": \"\",\n" +
                "  \"fact_table\": \"DEFAULT.SD_DC_USER_PUBBLOG_DAY\",\n" +
                "  \"lookups\": [],\n" +
                "  \"dimensions\": [\n" +
                "    {\n" +
                "      \"table\": \"SD_DC_USER_PUBBLOG_DAY\",\n" +
                "      \"columns\": [\n" +
                "        \"USER_F_LEVEL\",\n" +
                "        \"USER_V_TYPE\",\n" +
                "        \"USER_STAR_FLAG\",\n" +
                "        \"WEIBO_APP_TYPE\",\n" +
                "        \"PUBBLOG_TYPE\",\n" +
                "        \"DT\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"metrics\": [\n" +
                "    \"SD_DC_USER_PUBBLOG_DAY.UID\",\n" +
                "    \"SD_DC_USER_PUBBLOG_DAY.PUB_TOTAL_CNT\",\n" +
                "    \"SD_DC_USER_PUBBLOG_DAY.PUB_PIC_CNT\",\n" +
                "    \"SD_DC_USER_PUBBLOG_DAY.PUB_VIDEO_CNT\",\n" +
                "    \"SD_DC_USER_PUBBLOG_DAY.PUB_ARTICLE_CNT\",\n" +
                "    \"SD_DC_USER_PUBBLOG_DAY.PUB_GIFVIDEO_CNT\"\n" +
                "  ],\n" +
                "  \"filter_condition\": \"\",\n" +
                "  \"partition_desc\": {\n" +
                "    \"partition_date_column\": \"SD_DC_USER_PUBBLOG_DAY.DT\",\n" +
                "    \"partition_time_column\": null,\n" +
                "    \"partition_date_start\": 0,\n" +
                "    \"partition_date_format\": \"yyyyMMdd\",\n" +
                "    \"partition_time_format\": \"HH:mm:ss\",\n" +
                "    \"partition_type\": \"APPEND\",\n" +
                "    \"partition_condition_builder\": \"org.apache.kylin.metadata.model.PartitionDesc$DefaultPartitionConditionBuilder\"\n" +
                "  },\n" +
                "  \"capacity\": \"MEDIUM\"\n" +
                "}";
        System.out.println(modelDescData);
        modelDescData = modelDescData.replaceAll("\"", "\\\\\"");
        modelDescData = modelDescData.replaceAll("[\r\n]", " ");
        modelDescData = modelDescData.trim();
        String body = "{" + "\"modelDescData\":" + "\"" + modelDescData + "\"" +
                ",\"modelName\" : \"model_test\"" +
                ",\"project\" :  \"kylin_test\"" +
                "}";
        System.out.println(body);
        return excute(para, method, body);
    }

    private static String excute(String para, String method, String body) {
        StringBuilder out = new StringBuilder();
        try {
            URL url = new URL(baseURL + para);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic QURNSU46S1lMSU4=");
            connection.setRequestProperty("Content-Type", "application/json");
            if (body != null) {
                byte[] outputInBytes = body.getBytes("UTF-8");
                OutputStream os = connection.getOutputStream();
                os.write(outputInBytes);
                os.close();
            }
            InputStream content = (InputStream) connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = in.readLine()) != null) {
                out.append(line);
            }
            in.close();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        String model = createModel();
        System.out.println(model);
    }
}
