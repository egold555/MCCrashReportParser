package mccrash;

import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CrashReport {

	String wittyComment;
	Date time;
	String description;
	Stacktrace exception;
	String[] head;
	String[] initialization;
	SystemDetails systemDetails;
	
	@Getter
	@ToString
	@Builder
	public static class SystemDetails {
		String minecraftVersion;
		String operatingSystem;
		String CPU;
		String javaVersion;
		String javaVMVersion;
		String memory;
		String[] jVMFlags;
		IntCache intCache;
		String launchedVersion;
		String LWJGL;
		String OpenGL;
		String[] GLCaps;
		boolean usingVBOs;
		EnumModded isModded;
		String type;
		String[] resourcePacks;
		String currentLanguage;
		String profilerPosition;
		
		public enum EnumModded {
			YES("Definitely"), LIKELY("Very likely"), NO("Probably not");
			
			final String word;
			EnumModded(String word){
				this.word = word;
			}
			
			public static EnumModded get(String word) {
				for(EnumModded f : values()) {
					if(f.word.equals(word)) {
						return f;
					}
				}
				return null;
			}
		}
	}
	
	@Getter
	@ToString
	@AllArgsConstructor
	@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
	public static class IntCache {
		int cache;
		int tcache;
		int allocated;
		int tallocated;
	}
	
	@Getter
	@ToString
	@AllArgsConstructor
	@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
	public static class Stacktrace {
		String type;
		String desc;
		String[] body;
	}
	
}
