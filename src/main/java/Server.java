import com.google.gson.Gson;
import model.MessRequest;
import spark.Spark;
import spark.utils.IOUtils;
import static spark.Spark.*;

public class Server {

    final static int PORT_NUM = 7000;
    private static int getHerokuAssignedPort() {
        String herokuPort = System.getenv("PORT");
        if (herokuPort != null) {
            return Integer.parseInt(herokuPort);
        }
        return PORT_NUM;
    }

    public static String geoLocFormatString = "https://www.google.com/maps/search/?api=1&query=%s,%s";

    public static String messRequestDescriptiveFormatString = "[CONTACTNAME], this is [USERNAME]. I am borrowing another number. I am in [1] and for reasons of personal safety cannot call or text at the moment. Do not call or text me, and do not respond to this text. [2]." + "\n" +
            "\n" +
            "My current location is at [GEOLOC]" + "\n" +
            "\n" +
            "This message was sent through Instalert.";

    public static String makeValidFStringFromDescriptiveFString(String descriptiveFString) {
        //replace all square bracket pairs with "%s".
        return "";
    };

    public static String messRequestFormatString = "%s, this is %s. I am borrowing another number. I am in %s and for reasons of personal safety cannot call or text at the moment. Do not call or text me, and do not respond to this text. %s." + "\n" +
            "\n" +
            "My current location is at %s" + "\n" +
            "\n" +
            "This message was sent through Instalert.";

    public static String[][] messRequestFormatStringOptions = {
            {
                "an uncomfortable situation",
                "a bad situation",
                "an emergency",
            },
            {
                "Please come pick me up",
                "Please send help soon",
                "Please send help immediately",
            },
    };

    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        staticFiles.location("/");

        get("", (req, res) -> {
            res.status(200);
            res.type("text/html");

            return IOUtils.toString(Spark.class.getResourceAsStream("/index.html"));
        });

        get("/", (req, res) -> {
            res.status(200);
            res.type("text/html");

            return IOUtils.toString(Spark.class.getResourceAsStream("/index.html"));
        });

        notFound((req, res) -> {
            res.status(200);
            res.type("text/html");

            return IOUtils.toString(Spark.class.getResourceAsStream("/index.html"));
        });

        post("/api/send-message", (req, res) -> {
            res.status(200);

            String blob = req.body();
            MessRequest r = new Gson().fromJson(blob, MessRequest.class);

            //Form string
            String geoLocString        = String.format(geoLocFormatString, r.user.geoLocation.lat, r.user.geoLocation.lng);
            String awaitSeverityString = String.format(messRequestFormatString, r.contact.firstName, r.user.firstName, "%s", "%s", "https://www.google.com/maps/search/?api=1&query=36.26577,-92.54324" + r.user.geoLocation);

            //Send r to twilio endpoint

            return "";
        });
    }
}
