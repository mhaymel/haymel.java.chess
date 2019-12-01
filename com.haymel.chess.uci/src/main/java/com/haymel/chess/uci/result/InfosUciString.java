/***************************************************
 * (c) Markus Heumel
 *
 * @date:	30.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.result;

import static com.haymel.util.Require.greaterThanZero;
import static com.haymel.util.Require.nonNull;

public class InfosUciString {	
	
	private final Infos infos;
	
	public InfosUciString(Infos infos) {
		this.infos = nonNull(infos, "infos");
		greaterThanZero(infos.value().size(), "infos.value().size()");
	}
	
	public String value() {
		StringBuilder sb = new StringBuilder();
		sb.append("info");
		for(Info info: infos.value()) {
			sb.append(' ');
			append(sb, info);
		}
		
		return sb.toString().stripTrailing();
	}

	private StringBuilder append(StringBuilder sb, Info info) {
		sb
			.append(info.key())
			.append(' ')
			.append(info.value());
			
		return sb;
	}
	
}
