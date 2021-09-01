import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Main {
    private static String imageDestination = "data/images";
    public static void main(String[] args) {

        try {
            Document doc = Jsoup.connect("https://lenta.ru/").userAgent("Google Chrome").get();
            Elements imgElements = doc.select("img.g-picture");

            for(Element imgElement : imgElements){
                String strImageUrl = imgElement.attr("abs:src");

                downloadImage(strImageUrl);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void downloadImage(String strImageUrl) {
        String strImageName = strImageUrl.substring(strImageUrl.lastIndexOf("/") + 1);
        System.out.println("Saving: " + strImageName + " , from: " + strImageUrl);

        try {
            URL urlImage = new URL(strImageUrl);
            InputStream in = urlImage.openStream();

            byte[] buffer = new byte[4096];
            int n = -1;

            OutputStream os = new FileOutputStream(imageDestination + "/" + strImageName);

            while ((n = in.read(buffer)) != -1){
                os.write(buffer, 0 , n);
            }
            os.close();
            System.out.println("Image saved");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
