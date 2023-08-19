import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumFirst {
	DesiredCapabilities caps = new DesiredCapabilities();

	@BeforeTest
	public void mySetup() {
		File app = new File("calculator.apk");
		caps.setCapability("automationName", "Appium");
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "mohammed");
//		caps.setCapability("chromedriverExecutable", "E:\\التنزيلات\\chromedriver_win32\\chromedriver.exe");
//		caps.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
		caps.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

	}

	@Test()
	public void myFirstTest() throws MalformedURLException {
		AndroidDriver driver = new AndroidDriver(new URL(" http://192.168.1.10:4723/wd/hub"), caps);
		List<WebElement> myBtns = driver.findElements(By.className("android.widget.ImageButton"));
		for (WebElement num : myBtns) {
			if (num.getAttribute("resource-id").contains("digit")) {
				num.click();

			} else {
				System.out.println("not number !");
			}

		}
		String expectedValue = "7894561230";
		Assertion myAssert = new Assertion();
		myAssert.assertEquals(expectedValue,
				driver.findElement(By.id("com.google.android.calculator:id/formula")).getText());

	}

	@AfterTest
	public void Finsh() {

	}
}
