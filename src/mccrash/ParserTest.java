package mccrash;

import java.io.File;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserTest {

	public static void main(String[] args) throws Exception{
		File test = new File("crashes/test2.txt");
		CrashReport cr = CrashReportParser.parse(test);
		
		int startStacktrace = 0;
		for(int i = 0; i < cr.getException().getBody().length; i++) {
			String line = cr.getException().getBody()[i];
			System.err.println("Searching for: " + line);
			if(!line.contains(" ") && line.length() > 3) {
				startStacktrace = i;
				break;
			}
		}
		
		String[] split = cr.getException().getBody()[startStacktrace].split("\\.");
		
		int found = 0;
		for(int i = 0; i < split.length; i++) {
			if(split[i].contains("(")) {
				found = i;
				break;
			}
		}
		
		String path = combine(split, 0, found, ".");
		String linetest = combine(split, found, split.length, ".");
		String function = linetest.split("\\(")[0];
		
		Matcher m = Pattern.compile("\\((.*?)\\)").matcher(linetest);
		m.find();
		String classAndLine = m.group(1);
		String[] classesSplit = classAndLine.split(":");
		path = path.substring(0, path.length() - 1); //remove last .
		System.out.println(Arrays.toString(split));
		System.out.println(linetest);
		System.out.println(function);
		System.out.println(path);
		System.out.println(classAndLine);
		System.out.println(Arrays.toString(classesSplit));
		System.out.println(cr.toString());
	}
	
	static String combine(String[] arr, int starting, int ending, String combineChar) {
		StringBuilder builder = new StringBuilder();
		for(int i = starting; i < ending; i++) {
		    builder.append(arr[i] + combineChar);
		}
		return builder.toString();
	}


}
