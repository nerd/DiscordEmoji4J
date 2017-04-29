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
	private final SkinTone diversity;
	private final boolean isGlobal;

	private Emoji() {
		this.diversity = SkinTone.NONE;
		this.isGlobal = true;
	}

	protected Emoji(List<String> aliases,
	                EmojiCategory category,
	                String unicode,
	                boolean hasDiversity,
	                SkinTone diversity,
	                boolean isGlobal) {

		this.aliases = aliases;
		this.category = category;
		this.unicode = unicode;
		this.hasDiversity = hasDiversity;
		this.diversity = diversity;
		this.isGlobal = isGlobal;
	}

	/**
	 * @return all strings this emoji is identifiable by
	 */
	public List<String> getAliases() {
		return aliases;
	}

	/**
	 * @return the category in which this emoji fits
	 */
	public EmojiCategory getCategory() {
		return category;
	}

	/**
	 * @return the unicode representation of this emoji
	 */
	public String getUnicode() {
		return unicode + this.diversity;
	}

	/**
	 * @return whether or not the fitzpatrick scale is applicable to this emoji
	 */
	public boolean hasDiversity() {
		return hasDiversity;
	}

	/**
	 * @return whether or not this is a global emoji
	 */
	public boolean isGlobal() {
		return isGlobal;
	}

	/**
	 * @return the skin tone represented with this emoji
	 */
	public SkinTone getDiversity() {
		return diversity;
	}

	/**
	 * @return The unicode representation of this emoji without a skin tone
	 */
	public String getRawUnicode() {
		return this.unicode;
	}

	/**
	 * @return the unicode representation of this emoji object
	 */
	@Override
	public String toString() {
		return this.getUnicode();
	}

	/**
	 * @param diversity the skin tone to apply to this emoji
	 * @return a copy of this emoji with the given skin tone, or this if otherwise
	 */
	public Emoji withDiversity(SkinTone diversity) {
		if (!this.hasDiversity())
			return this;
		else return new Emoji(aliases, category, unicode, hasDiversity, diversity, isGlobal);
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
