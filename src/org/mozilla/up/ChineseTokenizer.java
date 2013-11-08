package org.mozilla.up;

import java.util.Iterator;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import weka.core.RevisionUtils;
import weka.core.tokenizers.Tokenizer;

/**
 * <!-- globalinfo-start --> A Chinese tokenizer using ansj_seg to tokenize the
 * strings.
 * <p/>
 * <!-- globalinfo-end -->
 * 
 * @author Rui You (ryou@mozilla.com)
 * @version $Revision: 8034 $
 */

public class ChineseTokenizer extends Tokenizer {

	/** for serialization */
	private static final long serialVersionUID = -273497067344939898L;

	/** the current position */
	protected Iterator<Term> iterator;

	/**
	 * Returns a string describing the stemmer
	 * 
	 * @return a description suitable for displaying in the
	 *         explorer/experimenter gui
	 */
	@Override
	public String globalInfo() {
		return "A Chinese tokenizer using ansj_seg to tokenize the strings.";
	}

	/**
	 * Tests if this enumeration contains more elements.
	 * 
	 * @return true if and only if this enumeration object contains at least one
	 *         more element to provide; false otherwise.
	 */
	@Override
	public boolean hasMoreElements() {
		if (null == this.iterator) {
			return false;
		}
		
		return iterator.hasNext();
	}

	/**
	 * Returns the next element of this enumeration if this enumeration object
	 * has at least one more element to provide.
	 * 
	 * @return the next element of this enumeration.
	 */
	@Override
	public Object nextElement() {
		if (null == this.iterator) {
			return null;
		}
		
		return this.iterator.next().getName();
	}

	/**
	 * Sets the string to tokenize. Tokenization happens immediately.
	 * 
	 * @param s
	 *            the string to tokenize
	 */
	@Override
	public void tokenize(String s) {
		if (null == s || s.isEmpty()) {
			this.iterator = null;
			return;
		}
		
		this.iterator = ToAnalysis.parse(s).iterator();
	}

	/**
	 * Returns the revision string.
	 * 
	 * @return the revision
	 */
	@Override
	public String getRevision() {
		return RevisionUtils.extract("$Revision: 8034 $");
	}
}
