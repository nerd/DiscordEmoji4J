package io.github.nerd.discordemoji;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ashley
 * @since 4/27/17 9:45 PM
 */
public enum EmojiDiversity {
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


	public static final Pattern DIVERSITY_PATTERN = Pattern.compile("[\\uD83C\\uDFFB-\\uD83C\\uDFFF]");
	public static final Pattern DIVERSITY_PATTERN_ALIAS = Pattern.compile("skin-tone-[1-5]");
	private final String unicode, alias;

	EmojiDiversity(String unicode, String alias) {
		if(!unicode.isEmpty()) {
			this.unicode = "\uD83C" + unicode;
		} else {
			this.unicode = unicode;
		}
		this.alias = alias;
	}

	public String getAlias() {
		return alias;
	}

	public String getUnicode() {
		return unicode;
	}

	@Override
	public String toString() {
		return this.getUnicode();
	}

	public static EmojiDiversity fromUnicode(String unicode) {
		Matcher m = DIVERSITY_PATTERN.matcher(unicode);
		if(!m.find())
			return NONE;
		for(EmojiDiversity diversity : values()) {
			if(diversity.getUnicode().equalsIgnoreCase(m.group())) {
				return diversity;
			}
		}
		return NONE;
	}

	public static EmojiDiversity fromAlias(String alias) {
		return Arrays.stream(EmojiDiversity.values())
				.filter(diversity -> diversity.getAlias().equalsIgnoreCase(alias))
				.findFirst().orElse(NONE);
	}
}
