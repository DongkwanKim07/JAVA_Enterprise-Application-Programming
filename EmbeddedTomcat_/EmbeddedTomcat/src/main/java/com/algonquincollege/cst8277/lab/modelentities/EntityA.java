package com.algonquincollege.cst8277.lab.modelentities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@SuppressWarnings("unused")

//TODO - add annotation so that only non-null fields are in JSON body
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonInclude(value = Include.NON_NULL)
public class EntityA extends PojoBase implements Serializable {
	private static final long serialVersionUID = 1L;

	public EntityA() {
		super();
	}

}
