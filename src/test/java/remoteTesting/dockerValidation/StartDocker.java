package remoteTesting.dockerValidation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import org.testng.Assert;


public class StartDocker {

	public void startFile() throws IOException, InterruptedException {
		boolean flag = false;
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("cmd /c start dockerup.bat");
		String logFile = "output.log";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 45);
		long stopNow = cal.getTimeInMillis();
		//Give some time to create the file before going further
		Thread.sleep(3000);
		
		while (System.currentTimeMillis() < stopNow) {
			if(flag) {
				break;
			}
			BufferedReader reader = new BufferedReader(new FileReader(logFile));
			String currentLine = reader.readLine();
			while (currentLine != null && !flag) {
				if (currentLine.contains("registered to the hub and ready to use")) {
					System.out.println("registered to the hub and ready to use");
					flag = true;
					break;
				}
				currentLine = reader.readLine();
			}
			reader.close();
		}

		Assert.assertTrue(flag);
		//scale up, if hub is up and nodes are connected to hub successfully
		runtime.exec("cmd /c start scale.bat");
		//Since there is no way to find if the scale up instances are created after initial instance, we just wait for certain time.
		Thread.sleep(10000);
	}
}
