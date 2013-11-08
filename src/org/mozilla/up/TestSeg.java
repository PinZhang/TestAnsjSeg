package org.mozilla.up;

import org.ansj.splitWord.analysis.ToAnalysis;

public class TestSeg {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "测试一个例子，分词的效果";
		System.out.println(ToAnalysis.parse(str));
	}

}
