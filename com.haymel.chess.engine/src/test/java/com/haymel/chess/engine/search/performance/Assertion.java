/***************************************************
 * (c) Markus Heumel
 *
 * @date 25.06.2018
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.performance;

import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

/**
 * @author markus
 *
 */
public class Assertion {

	private static boolean on = false;
    
	static {
		assert on = true; 
	}
    
	public static boolean assertion() {
		return on;
	}
	
	public static void assumeAssertion() {
		assumeTrue(assertion());
	}
	
	public static void assumeNoAssertion() {
		assumeFalse(assertion());
	}
	
}
