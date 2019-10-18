package remoteTesting.dockerValidation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import org.testng.Assert;


public class StopDocker {

	public void stopFile() throws IOException, InterruptedException {
		boolean flag = false;
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("cmd /c start dockerdown.bat");
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
				if (currentLine.contains("selenium-hub exited")) {
					System.out.println("selenium-hub and all instances shutdown successfully!");
					flag = true;
					break;
				}
				currentLine = reader.readLine();
			}
			reader.close();
		}

		Assert.assertTrue(flag);

	}
}
