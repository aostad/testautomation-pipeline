package remoteTesting.dockerValidation;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class Chrome3Test {

	@Test
	public void test3() throws MalformedURLException {
		//This is how you set up and run selenium webdriver on docker
		//need "RemoteWebDriver" as we are going to run on docker
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		URL url = new URL("http://localhost:4444/wd/hub"); 
		RemoteWebDriver driver = new RemoteWebDriver(url, cap);
		
		driver.get("http://yahoo.com");
		System.out.println(driver.getTitle());

	}

}
