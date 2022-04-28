package com.agrotis.api.agrotisteste.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class UserFilterParam.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFilterParam {
	
	/** The name. */
	private String name;
	/** The document id. */
	private String documentId;
	/** The initial start date. */
	private String initialStartDate;
	/** The final start date. */
	private String finalStartDate;

}
