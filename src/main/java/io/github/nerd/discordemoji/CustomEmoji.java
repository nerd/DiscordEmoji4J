package io.github.nerd.discordemoji;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author ashley
 * @since 4/28/17 11:20 PM
 * An emoji object that represents custom emoji from a discord server
 */
public class CustomEmoji extends Emoji {
	private final long id;
	private final long guildID;

	public CustomEmoji(long id, String name, long guildID) {
		super(new ArrayList<>(Collections.singletonList(name)),
				EmojiCategory.CUSTOM, String.format("<:%s:%s>", name, Long.toUnsignedString(id)),
				false, SkinTone.NONE, false);
		this.guildID = guildID;
		this.id = id;
	}

	/**
	 * @return the id that this emoji is known by
	 */
	public long getID() {
		return id;
	}

	/**
	 * @return the id of the guild in which this emoji was defined
	 */
	public long getGuildID() {
		return guildID;
	}
}
