package io.github.nerd.discordemoji;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;

/**
 * @author ashley
 * @since 4/27/17 7:04 PM
 */
public class DiscordEmojiTest {
	@Test
	public void testInitialization() {
		assertFalse(EmojiRegistry.getEmojis().isEmpty());
	} // success (i hope)

	@Test
	public void testGetByAlias() { // shout out to techtony96
		EmojiRegistry.getEmojiByAlias("track_next").ifPresent(System.out::println);
	} // ⏭

	@Test
	public void testDiversity() {
		DiscordEmoji emoji = EmojiRegistry.getEmojisWithDiversity().get(0)
				.withDiversity(EmojiDiversity.MEDIUM_SKIN);
		System.out.println(emoji);
	} // 🙌🏽 <- intellij doesn't display this nicely but it works promise

	@Test
	public void testDiversityUnicode() {
		EmojiRegistry.getEmojiByUnicode("\uD83D\uDE4C\uD83C\uDFFD")
				.ifPresent(emoji -> System.out.println(emoji.getDiversity().name()));
	} // MEDIUM_SKIN

	@Test
	public void testDiversityAlias() {
		EmojiRegistry.getEmojiByAlias(":raised_hands::skin-tone-2:")
				.ifPresent(emoji -> System.out.println(emoji.getDiversity().name()));
	} // MEDIUM_LIGHT_SKIN
}
