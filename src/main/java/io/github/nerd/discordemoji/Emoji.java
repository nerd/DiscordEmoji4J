package io.github.nerd.discordemoji;

import java.util.List;

/**
 * @author ashley
 * @since 4/27/17 6:55 PM
 */
public class Emoji {
	private List<String> aliases;
	private EmojiCategory category;
	private String unicode;
	private boolean hasDiversity;
	private final EmojiDiversity diversity;
	private final boolean isGlobal;

	private Emoji() {
		this.diversity = EmojiDiversity.NONE;
		this.isGlobal = true;
	}

	protected Emoji(List<String> aliases,
	                EmojiCategory category,
	                String unicode,
	                boolean hasDiversity,
	                EmojiDiversity diversity,
	                boolean isGlobal) {

		this.aliases = aliases;
		this.category = category;
		this.unicode = unicode;
		this.hasDiversity = hasDiversity;
		this.diversity = diversity;
		this.isGlobal = true;
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

	public Emoji withDiversity(EmojiDiversity diversity) {
		if (!this.hasDiversity())
			return this;
		else return new Emoji(aliases, category, unicode, hasDiversity, diversity, isGlobal);
	}

	public boolean isGlobal() {
		return isGlobal;
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
