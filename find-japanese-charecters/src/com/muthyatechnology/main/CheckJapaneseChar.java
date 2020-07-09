package com.muthyatechnology.main;

import java.util.HashSet;
import java.util.Set;

public class CheckJapaneseChar {

	public static void main(String[] args) {

		Set<Character.UnicodeBlock> japaneseUnicodeBlocks = new HashSet<Character.UnicodeBlock>() {
			{

				add(Character.UnicodeBlock.HIRAGANA);

				add(Character.UnicodeBlock.KATAKANA);

				add(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS);

			}
		};

		String mixed = "This is a Japanese newspaper headline: ラドクリフ、マラソン五輪代表に1万m出場にも含み";

		for (char c : mixed.toCharArray()) {

			if (japaneseUnicodeBlocks.contains(Character.UnicodeBlock.of(c))) {

				System.out.println(c + " is a Japanese character");

			} else {

				System.out.println(c + " is not a Japanese character");

			}

		}

	}

}