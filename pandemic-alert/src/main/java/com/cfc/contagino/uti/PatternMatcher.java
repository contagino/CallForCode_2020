package com.cfc.contagino.uti;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {

/*	public static void main(String[] args) {
		
		LocationEpidemicPk pk1 = new LocationEpidemicPk("Kol", "abc");
		LocationEpidemicPk pk2 = new LocationEpidemicPk("Kol", "abc");
		System.out.println("Pk1 hash:"+pk1.hashCode());
		System.out.println("Pk2 hash:"+pk2.hashCode());
		System.out.println("Hascode equal:"+(pk1.hashCode() == pk2.hashCode()));
		System.out.println("Hascode equal:"+(pk1.equals(pk2)));
		String data1 = "Searching in words : java javap myjava myjavaprogram";

		String searchKeyword = "bava OR program";
		String[] regexs = searchKeyword.split(" OR ");

		for (String regex : regexs) {
			System.out.println("Expression:" + regex);
			Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(data1);
			while (matcher.find()) {
				System.out.println("Found:");
//		    return true;
				break;
			}
		}
	}*/

	public static boolean matcher(String searchPattern, String post) {
		String[] regexs = searchPattern.split(" OR ");
		for (String regex : regexs) {
			Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(post);
			while (matcher.find()) {
				return true;
			}
		}
		return false;
	}

}
