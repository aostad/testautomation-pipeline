package remoteTesting.dockerValidation;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Chrome1Test {

	@BeforeTest
	public void startDocker() throws IOException, InterruptedException {
		//delete the output.log file, if any
		File fi = new File("output.log");
		if (fi.delete()) {
			System.out.println("output.log has been deleted successfully!");
		}
		
		StartDocker startDocker = new StartDocker();
		startDocker.startFile();
	}
	
	@AfterTest
	public void stopDocker() throws IOException, InterruptedException {
		StopDocker stopDocker = new StopDocker();
		stopDocker.stopFile();
	}
	
	@Test
	public void test1() throws MalformedURLException {
		//This is how you set up and run selenium webdriver on docker
		//need "RemoteWebDriver" as we are going to run on docker
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		URL url = new URL("http://localhost:4444/wd/hub"); 
		RemoteWebDriver driver = new RemoteWebDriver(url, cap);
		
		driver.get("http://google.com");
		System.out.println(driver.getTitle());
	}

}
