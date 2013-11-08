package org.mozilla.up;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class ChineseTokenizerTestCase {

	private ChineseTokenizer tokenizer = null;
	
	public ChineseTokenizerTestCase() throws IOException {
		this.tokenizer = new ChineseTokenizer();
	}
	
	private int traverse() {
		int count = 0;
		while (this.tokenizer.hasMoreElements()) {
			System.out.println(this.tokenizer.nextElement());
			count++;
		}
		
		return count;
	}
	
	@Test
	public void testNormalString() {
		this.tokenizer.tokenize("由于在与休斯敦迪纳摩的较量中败北而" +
				"无缘今年美国大联盟季后赛，10月底已宣布本赛季结束后挂靴的意大利传" +
				"奇球星内斯塔职业生涯最后亮相是在10月19日主场2-1击败费城联合的比赛中");
		
		assertTrue(traverse() > 10);
	}

	@Test
	public void testEmptyString() {
		this.tokenizer.tokenize(null);
		assertTrue(traverse() == 0);
	}
	
	@Test
	public void testMixedContent() {
		this.tokenizer.tokenize("在Android市场查找相关应用会很方便。");
		

		int count = 0;
		boolean gotAndroid = false;
		while (this.tokenizer.hasMoreElements()) {
			String elem = this.tokenizer.nextElement().toString();
			System.out.println(elem);
			if (!gotAndroid && "android".equals(elem)) {
				gotAndroid = true;
			}
			count++;
		}
		
		assertTrue(count > 5 && gotAndroid);
	}
	
	@Test
	public void testStopWords() {
		this.tokenizer.tokenize("我的大学");

		int count = 0;
		boolean hasStopWord = false;
		while (this.tokenizer.hasMoreElements()) {
			String elem = this.tokenizer.nextElement().toString();
			System.out.println(elem);
			if (!hasStopWord && "的".equals(elem)) {
				hasStopWord = true;
			}
			count++;
		}

		// Only "大学" is left
		assertTrue(count == 1 && !hasStopWord);
	}
}
