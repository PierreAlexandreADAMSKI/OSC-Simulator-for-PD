package app.binUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * app.binUtil Created by Pierre-Alexandre Adamski on 02/02/2016.
 */
public class Shell {

	public static String executeCommand(String command) {
		StringBuilder output = new StringBuilder();
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = reader.readLine())!= null) {
				output.append(line).append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}
}
