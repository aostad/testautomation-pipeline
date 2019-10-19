package remoteTesting.dockerValidation;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeStandaloneTest {

	public static void main(String[] args) throws MalformedURLException {
		
		//This is how you set up and run selenium webdriver on docker
		//need "RemoteWebDriver" as we are going to run on docker
		DesiredCapabilities cap = DesiredCapabilities.firefox();
		URL url = new URL("http://localhost:4444/wd/hub"); 
		RemoteWebDriver driver = new RemoteWebDriver(url, cap);
		
		driver.get("http://vancouverDreamHouse.com");
		System.out.println(driver.getTitle());

	}

}
