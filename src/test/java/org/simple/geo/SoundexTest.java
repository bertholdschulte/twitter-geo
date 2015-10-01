package org.simple.geo;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.language.Soundex;
import org.junit.Test;

public class SoundexTest {

	//@Test
	public void test() {

		Soundex x  = new Soundex();
		try {
			int difference = x.difference("Germany", "germ");
			System.out.println(difference);
		} catch (EncoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
