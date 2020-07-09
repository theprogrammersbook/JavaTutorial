package com.muthyatechnology.main;

import java.util.HashSet;
import java.util.Set;

public class ExtractEnglishString {
	public static void main(String[] args) {

		Set<Character.UnicodeBlock> japaneseUnicodeBlocks = new HashSet<Character.UnicodeBlock>() {
			{
				add(Character.UnicodeBlock.HIRAGANA);

				add(Character.UnicodeBlock.KATAKANA);

				add(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS);
			}
		};

		String mixed = "Japanese newspaper headline: ラドクリ";
		String extractJapanese = "";
		for (char c : mixed.toCharArray()) {
		    if (japaneseUnicodeBlocks.contains(Character.UnicodeBlock.of(c))) {
		    	extractJapanese +=  c;
		    } else {
		       
		    }
		}
		System.out.println(extractJapanese);
		String extractEnglish=mixed.replace(extractJapanese.trim(),"");
        System.out.println(extractEnglish);
	}
}
