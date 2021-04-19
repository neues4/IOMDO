package bachelorthesis.IOMDOProject;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * From slides "Programmiermethoden" by Mr. Locher
 * @author romap1
 *
 */
public class I18n {
	private static final String BASE_NAME = "bachelorthesis.IOMDOProject.lang";
	private static ResourceBundle i18nBundle = ResourceBundle.getBundle(BASE_NAME);
	
	public static void setLocale(Locale locale) {
		i18nBundle = ResourceBundle.getBundle(BASE_NAME, locale);
	}
	
	public static ResourceBundle getResourceBundle() {
		return i18nBundle;
	}
	
	public static String getString(String key) {
		try {
			return i18nBundle.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}