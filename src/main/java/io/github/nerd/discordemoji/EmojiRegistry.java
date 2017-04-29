package io.github.nerd.discordemoji;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

/**
 * @author ashley
 * @since 4/27/17 7:00 PM
 * A container for emoji objects
 */
public class EmojiRegistry {
	private static List<Emoji> emojis = new ArrayList<>();
	private final  List<Emoji> instanceEmojis = new CopyOnWriteArrayList<>();
	private final  List<Emoji> allEmojis = new CopyOnWriteArrayList<>(emojis);

	static {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.readValue(EmojiRegistry.class.getResource("/discord_emoji.json"), EmojiRegistry.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param name The alias to search for
	 * @return an emoji object that shares an alias with the provided name
	 */
	public Optional<Emoji> getEmojiByAlias(String name) {
		Matcher matcher = SkinTone.SKIN_TONE_ALIAS_PATTERN.matcher(name.toLowerCase());
		SkinTone diversity = SkinTone.NONE;
		if (matcher.find())
			diversity = SkinTone.fromAlias(matcher.group());
		final String name1 = matcher.replaceAll("").replaceAll(":", "");
		Optional<Emoji> opt = allEmojis.stream().filter(emoji -> emoji.getAliases().contains(name1)).findFirst();
		if (opt.isPresent())
			return Optional.of(opt.get().withDiversity(diversity));
		return opt;
	}

	/**
	 * @param unicode The unicode to search for
	 * @return an emoji object that shares unicode representation with the provided unicode
	 */
	public Optional<Emoji> getEmojiByUnicode(String unicode) {
		Matcher matcher = SkinTone.SKIN_TONE_PATTERN.matcher(unicode);
		SkinTone diversity = SkinTone.NONE;
		if (matcher.find())
			diversity = SkinTone.fromUnicode(matcher.group());
		final String unicode1 = matcher.replaceAll("");
		Optional<Emoji> opt = allEmojis.stream().filter(emoji -> emoji.getUnicode().equalsIgnoreCase(unicode1)).findFirst();
		if (opt.isPresent()) {
			return Optional.of(opt.get().withDiversity(diversity));
		}
		return opt;
	}

	/**
	 * @return all emojis that the fitzpatrick scale is applicable to
	 */
	public List<Emoji> getEmojisWithDiversity() {
		return allEmojis.stream().filter(Emoji::hasDiversity).collect(Collectors.toList());
	}

	/**
	 * @return all emojis that the fitzpatrick scale is not applicable to
	 */
	public List<Emoji> getEmojisWithoutDiversity() { // you racist bastard
		return allEmojis.stream().filter(emoji -> !emoji.hasDiversity()).collect(Collectors.toList());
	}

	/**
	 * @param category the category to search for
	 * @return all emojis of a given category
	 */
	public List<Emoji> getEmojisByCategory(EmojiCategory category) {
		return allEmojis.stream().filter(emoji -> emoji.getCategory().equals(category)).collect(Collectors.toList());
	}

	/**
	 * @return all local emoji objects
	 */
	public List<Emoji> getEmojis() {
		return Collections.unmodifiableList(instanceEmojis);
	}

	/**
	 * @return all globally defined emojis
	 */
	public static List<Emoji> getGlobalEmojis() {
		return Collections.unmodifiableList(emojis);
	}

	/**
	 * locally registers an emoji object
	 * @param emoji the emoji object to register locally
	 */
	public void registerEmoji(Emoji emoji) {
		instanceEmojis.add(emoji);
		allEmojis.add(emoji);
	}

	/**
	 * locally unregisters an emoji object
	 * @param emoji the emoji object to unregister locally
	 */
	public void unregisterEmoji(Emoji emoji) {
		if(instanceEmojis.contains(emoji))
			instanceEmojis.remove(emoji);
		if(allEmojis.contains(emoji))
			allEmojis.remove(emoji);
	}

	/**
	 * some jackson shit idk
	 * @param emojis benis lol
	 */
	private void setEmojis(List<Emoji> emojis) {
		EmojiRegistry.emojis = emojis;
	}
}
