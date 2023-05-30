/**
 * File: EntityHasManyToOneBackReference.java
 * Course materials  CST 8277
 * 
 * @author Mike Norman
 * 
 */
package com.algonquincollege.cst8277.lab.modelentities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@SuppressWarnings("unused")

//TODO - add annotation so that only non-null fields are in JSON body
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonInclude(value = Include.NON_NULL)
public class EntityFHasManyToOneBackReference extends PojoBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    protected String field1;
    protected EntityEHasMany owningEntity;

    public EntityFHasManyToOneBackReference() {
        super();
    }

    public String getField1() {
        return field1;
    }
    public void setField1(String field1) {
        this.field1 = field1;
    }

    // TODO - add annotation to manage ManyToOne back-reference
    @JsonBackReference
    public EntityEHasMany getOwningEntity() {
        return owningEntity;
    }
    public void setOwningEntity(EntityEHasMany owningEntity) {
        this.owningEntity = owningEntity;
    }

}