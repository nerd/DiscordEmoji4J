package io.github.nerd.discordemoji;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ashley
 * @since 4/27/17 9:45 PM
 * Definitions of various skin tones for applicable emojis
 */
public enum SkinTone {
	LIGHT_SKIN("\uDFFB", "skin-tone-1"),
	MEDIUM_LIGHT_SKIN("\uDFFC", "skin-tone-2"),
	MEDIUM_SKIN("\uDFFD", "skin-tone-3"),
	MEDIUM_DARK_SKIN("\uDFFE", "skin-tone-4"),
	DARK_SKIN("\uDFFF", "skin-tone-5"),

	TYPE_1("\uDFFB", "skin-tone-1"),
	TYPE_2("\uDFFB", "skin-tone-1"),
	TYPE_3("\uDFFC", "skin-tone-2"),
	TYPE_4("\uDFFD", "skin-tone-3"),
	TYPE_5("\uDFFE", "skin-tone-4"),
	TYPE_6("\uDFFF", "skin-tone-5"),

	NONE("", "");

	/**
	 * A pattern that matches the unicode of a fitzpatrick character
	 */
	public static final Pattern SKIN_TONE_PATTERN = Pattern.compile("[\\uD83C\\uDFFB-\\uD83C\\uDFFF]");

	/**
	 * A pattern that matches skin-tone-n
	 */
	public static final Pattern SKIN_TONE_ALIAS_PATTERN = Pattern.compile("skin-tone-[1-5]");

	private final String unicode, alias;

	SkinTone(String unicode, String alias) {
		if(!unicode.isEmpty()) {
			this.unicode = "\uD83C" + unicode;
		} else {
			this.unicode = unicode;
		}
		this.alias = alias;
	}

	/**
	 * gets the alias of this skin tone
	 * @return alias, in the format of skin-tone-n
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * gets the unicode representation of this skin tone
	 * @return unicode representation of this skin tone
	 */
	public String getUnicode() {
		return unicode;
	}

	/**
	 * @return unicode representation of this skin tone
	 */
	@Override
	public String toString() {
		return this.getUnicode();
	}

	/**
	 * @param unicode a unicode string
	 * @return a skin tone that matches the given unicode string, or NONE
	 */
	public static SkinTone fromUnicode(String unicode) {
		Matcher m = SKIN_TONE_PATTERN.matcher(unicode);
		if(!m.find())
			return NONE;
		for(SkinTone diversity : values()) {
			if(diversity.getUnicode().equalsIgnoreCase(m.group())) {
				return diversity;
			}
		}
		return NONE;
	}

	/**
	 * @param alias an alias, matching skin-tone-n
	 * @return a skin tone matching the given alias, or NONE
	 */
	public static SkinTone fromAlias(String alias) {
		return Arrays.stream(SkinTone.values())
				.filter(diversity -> diversity.getAlias().equalsIgnoreCase(alias))
				.findFirst().orElse(NONE);
	}
}
