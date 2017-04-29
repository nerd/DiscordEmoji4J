package io.github.nerd.discordemoji;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author ashley
 * @since 4/28/17 11:20 PM
 */
public class DiscordEmoji extends Emoji {
	private final long id;

	public DiscordEmoji(long id, String name) {
		super(new ArrayList<>(Collections.singletonList(name)),
				EmojiCategory.OTHER, String.format("<:%s:%d>", name, id),
				false, EmojiDiversity.NONE);
		this.id = id;
	}

	public long getID() {
		return id;
	}
}
