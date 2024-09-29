package config;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;

import static base.WebDriverFactory.getWebDriver;

public class CommonMethod {

    public CommonMethod(){
        
    }

    public static Robot robot;
    public static Alert alert;
    public static Select select;
    public static Actions actions;


    /* To ZoomOut */
    public void robotZoomOut() throws Exception {
        robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_SUBTRACT);
        robot.keyRelease(KeyEvent.VK_SUBTRACT);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    /* To ZoomIn */
    public void robotZoomIn() throws Exception {
        robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ADD);
        robot.keyRelease(KeyEvent.VK_ADD);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    /* To ScrollUp using JavaScript Executor */
    public static void scrollUp(int pixel) {
        ((JavascriptExecutor) getWebDriver()).executeScript("scroll(0, -" + pixel + ");");
    }

    /* To ScrollDown using JavaScript Executor */
    public static void scrollDown(int pixel) {
        ((JavascriptExecutor) getWebDriver()).executeScript("scroll(0," + pixel + ");");
    }

    /*This method is used to scroll till element found
     */
    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /* To Move cursor to the Desired Location */
    public void moveCursor(int X_Position, int Y_Position) {
        robot.mouseMove(X_Position, Y_Position);
    }
}
