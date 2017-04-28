package io.github.nerd.discordemoji;

import java.util.List;

/**
 * @author ashley
 * @since 4/27/17 6:55 PM
 */
public class DiscordEmoji {
	private List<String> aliases;
	private EmojiCategory category;
	private String unicode;
	private boolean hasDiversity;
	private final EmojiDiversity diversity;

	private DiscordEmoji() {
		this.diversity = EmojiDiversity.NONE;
	}

	private DiscordEmoji(List<String> aliases,
			EmojiCategory category,
			String unicode,
			boolean hasDiversity,
			EmojiDiversity diversity) {

		this.aliases = aliases;
		this.category = category;
		this.unicode = unicode;
		this.hasDiversity = hasDiversity;
		this.diversity = diversity;
	}

	public List<String> getAliases() {
		return aliases;
	}

	public EmojiCategory getCategory() {
		return category;
	}

	public String getUnicode() {
		return unicode;
	}

	public boolean hasDiversity() {
		return hasDiversity;
	}

	public DiscordEmoji withDiversity(EmojiDiversity diversity) {
		if(!this.hasDiversity())
			return this;
		else return new DiscordEmoji(aliases, category, unicode, hasDiversity, diversity);
	}

	public EmojiDiversity getDiversity() {
		return diversity;
	}

	@Override
	public String toString() {
		return this.getUnicode() + diversity;
	}

	private void setCategory(EmojiCategory category) {
		this.category = category;
	}

	private void setUnicode(String unicode) {
		this.unicode = unicode;
	}

	private void setHasDiversity(boolean hasDiversity) {
		this.hasDiversity = hasDiversity;
	}

	private void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}
}
