package io.github.nerd.discordemoji;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author ashley
 * @since 4/27/17 6:56 PM
 */
public enum EmojiCategory {
	PEOPLE,
	NATURE,
	FOOD,
	ACTIVITY,
	TRAVEL,
	OBJECTS,
	SYMBOLS,
	FLAGS,
	CUSTOM;

	@JsonCreator
	public EmojiCategory forValue(String value) {
		return valueOf(value.toUpperCase());
	}
}
