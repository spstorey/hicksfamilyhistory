package spssoftware;


import org.springframework.boot.ansi.AnsiOutput;
import spssoftware.config.Config;
import spssoftware.config.SystemHelper;

import java.io.PrintStream;

public abstract class SpsBannerWriter {

	private static final String[] BANNER = {
    "    _   _ _      _         ______              _ _         _   _ _     _",
    "   | | | (_)    | |        |  ___|            (_) |       | | | (_)   | |                    ",
    "   | |_| |_  ___| | _____  | |_ __ _ _ __ ___  _| |_   _  | |_| |_ ___| |_ ___  _ __ _   _   ",
    "   |  _  | |/ __| |/ / __| |  _/ _` | '_ ` _ \\| | | | | | |  _  | / __| __/ _ \\| '__| | | |  ",
    "   | | | | | (__|   <\\__ \\ | || (_| | | | | | | | | |_| | | | | | \\__ \\ || (_) | |  | |_| |  ",
    "   \\_| |_/_|\\___|_|\\_\\___/ \\_| \\__,_|_| |_| |_|_|_|\\__, | \\_| |_/_|___/\\__\\___/|_|   \\__, |  ",
    "                                                    __/ |                             __/ |  ",
    "                                                   |___/                             |___/   ",
    "                                                                                             ", };
	private static final Config config = SystemHelper.getEnvironment().getConfig();

	private static final String SUBTITLE = "Running with " + config.getClass().getSimpleName() + " config";

	public static void write(PrintStream printStream) {
		for (String line : BANNER) {
			printStream.println(line);
		}
 		printStream.println(AnsiOutput.toString(SUBTITLE));
		printStream.println();
	}
}
