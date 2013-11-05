package org.mozilla.up;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import weka.core.tokenizers.Tokenizer;
import weka.core.RevisionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 <!-- globalinfo-start -->
 * A Chinese tokenizer using ansj_seg to tokenize the strings.
 * <p/>
 <!-- globalinfo-end -->
 * 
 * @author  Rui You (ryou@mozilla.com)
 * @version $Revision: 8034 $
 */

public class ChineseTokenizer
  extends Tokenizer {

  /** for serialization */
  private static final long serialVersionUID = -273497067344939898L;

  /** the list of the stings */
  protected ArrayList<String> m_List;

  /** the current position */
  protected Iterator<String> m_Iterator;

  /**
   * Returns a string describing the stemmer
   * 
   * @return 		a description suitable for displaying in the 
   * 			explorer/experimenter gui
   */
   @Override
  public String globalInfo() {
    return 
      "A Chinese tokenizer using ansj_seg to tokenize the strings.";
	}

  /**
   * Tests if this enumeration contains more elements.
   * 
   * @return 		true if and only if this enumeration object contains 
   * 			at least one more element to provide; false otherwise.
   */
  @Override
  public boolean hasMoreElements() {
    return m_Iterator.hasNext();
  }

  /**
   * Returns the next element of this enumeration if this enumeration object 
   * has at least one more element to provide.
   * 
   * @return		the next element of this enumeration.
   */
  @Override
  public Object nextElement() {
    return m_Iterator.next();
  }

  /**
   * Sets the string to tokenize. Tokenization happens immediately.
   * 
   * @param s		the string to tokenize
   */
  @Override
  public void tokenize(String s) {
    List<Term> termList = ToAnalysis.parse(s);
    Iterator<Term> it = termList.iterator();
    m_List = new ArrayList<String>();
      while (it.hasNext()) {
      m_List.add(it.next().toString());
    }
    m_Iterator = m_List.iterator();
  }

  /**
   * Returns the revision string.
   * 
   * @return		the revision
   */
  @Override
  public String getRevision() {
    return RevisionUtils.extract("$Revision: 8034 $");
  }

  /**
   * Runs the tokenizer with the given options and strings to tokenize.
   * The tokens are printed to stdout.
   * 
   * @param args	the commandline options and strings to tokenize
   */
  public static void main(String[] args) {
    runTokenizer(new ChineseTokenizer(), args);
  }
}
