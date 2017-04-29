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

	public EmojiRegistry() {
	}

	public Optional<Emoji> getEmojiByAlias(String name) {
		Matcher matcher = EmojiDiversity.DIVERSITY_PATTERN_ALIAS.matcher(name.toLowerCase());
		EmojiDiversity diversity = EmojiDiversity.NONE;
		if (matcher.find())
			diversity = EmojiDiversity.fromAlias(matcher.group());
		final String name1 = matcher.replaceAll("").replaceAll(":", "");
		Optional<Emoji> opt = allEmojis.stream().filter(emoji -> emoji.getAliases().contains(name1)).findFirst();
		if (opt.isPresent())
			return Optional.of(opt.get().withDiversity(diversity));
		return opt;
	}

	public Optional<Emoji> getEmojiByUnicode(String unicode) {
		Matcher matcher = EmojiDiversity.DIVERSITY_PATTERN.matcher(unicode);
		EmojiDiversity diversity = EmojiDiversity.NONE;
		if (matcher.find())
			diversity = EmojiDiversity.fromUnicode(matcher.group());
		final String unicode1 = matcher.replaceAll("");
		Optional<Emoji> opt = allEmojis.stream().filter(emoji -> emoji.getUnicode().equalsIgnoreCase(unicode1)).findFirst();
		if (opt.isPresent()) {
			return Optional.of(opt.get().withDiversity(diversity));
		}
		return opt;
	}

	public List<Emoji> getEmojisWithDiversity() {
		return allEmojis.stream().filter(Emoji::hasDiversity).collect(Collectors.toList());
	}

	public List<Emoji> getEmojisWithoutDiversity() { // you racist bastard
		return allEmojis.stream().filter(emoji -> !emoji.hasDiversity()).collect(Collectors.toList());
	}

	public List<Emoji> getEmojisByCategory(EmojiCategory category) {
		return allEmojis.stream().filter(emoji -> emoji.getCategory().equals(category)).collect(Collectors.toList());
	}

	public List<Emoji> getEmojis() {
		return Collections.unmodifiableList(instanceEmojis);
	}

	public static List<Emoji> getGlobalEmojis() {
		return Collections.unmodifiableList(emojis);
	}

	public void registerEmoji(Emoji emoji) {
		instanceEmojis.add(emoji);
		allEmojis.add(emoji);
	}

	public void unregisterEmoji(Emoji emoji) {
		if(instanceEmojis.contains(emoji))
			instanceEmojis.remove(emoji);
		if(allEmojis.contains(emoji))
			allEmojis.remove(emoji);
	}

	private void setEmojis(List<Emoji> emojis) {
		EmojiRegistry.emojis = emojis;
	}
}
