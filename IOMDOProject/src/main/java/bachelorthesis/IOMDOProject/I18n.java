package bachelorthesis.IOMDOProject;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Helper class for Localization
 * From slides "Programmiermethoden" Spring semester 2019 by Mr. Locher
 * @author romap1
 *
 */
public class I18n {
	
	private static final String BASE_NAME = "bachelorthesis/IOMDOProject/lang";
	private static ResourceBundle i18nBundle = ResourceBundle.getBundle(BASE_NAME);
	
	/**
	 * Set the locale.
	 * @param locale
	 */
	public static void setLocale(Locale locale) {
		i18nBundle = ResourceBundle.getBundle(BASE_NAME, locale);
	}
	
	/**
	 * Returns the ResourceBundle of the current Locale.
	 * @return RecousceBundle
	 */
	public static ResourceBundle getResourceBundle() {
		return i18nBundle;
	}
	
	/**
	 * Returns the String from a given Key.
	 * @param key
	 * @return String from a given Key.
	 */
	public static String getString(String key) {
		try {
			return i18nBundle.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}