package standalone;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ContentType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Tasca_5 {

    public static void main(String[] args) {
        String u = "https://mastodont.cat/api/v1";
        String TOKEN = ResourceBundle.getBundle("token").getString("token");

        try {
            String req = Request.get((URI.create(u + "/accounts/109862447110628983/statuses?limit=1")))
                    .addHeader("Authorization", "Bearer " + TOKEN)
                    .execute()
                    .returnContent()
                    .asString();
            JSONArray jsonArray = new JSONArray(req);
            JSONObject result = jsonArray.getJSONObject(0);
            String res = result.getString("content");
            System.out.println("Darrer tut de fib_asw:\n" + res);
            String id = result.getString("id");

            String boost = Request.post((URI.create(u + "/statuses/" + id + "/reblog")))
                    .addHeader("Authorization", "Bearer " + TOKEN)
                    .execute()
                    .returnContent()
                    .asString();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
