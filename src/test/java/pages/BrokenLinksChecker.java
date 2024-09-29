package pages;

import config.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageFactory.PageFactoryInitializer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BrokenLinksChecker extends PageFactoryInitializer {

    public void checkBrokenLinks() {
        // Find all anchor tags on the page
        List<WebElement> links = getWebDriver().findElements(By.tagName("a"));

        // Iterate through the links
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (url != null && !url.isEmpty()) {
                checkLink(url);
            }
        }
    }

    private void checkLink(String url) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setRequestMethod("HEAD");
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();

            // Check if the URL is broken
            if (responseCode >= 400) {
                System.out.println("Broken link: " + url + " - Response Code: " + responseCode);
                Reporter.info("Broken link: " + url + " - Response Code: " + responseCode);
            } else {
                System.out.println("Valid link: " + url + " - Response Code: " + responseCode);
                Reporter.info("Valid link: " + url + " - Response Code: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("Exception for URL: " + url + " - " + e.getMessage());
            Reporter.info("Exception for URL: " + url + " - " + e.getMessage());
        }
    }
}
