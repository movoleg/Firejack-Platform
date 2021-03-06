package net.firejack.platform.generate.beans.ipad;
/*
 * Firejack Open Flame - Copyright (c) 2012 Firejack Technologies
 *
 * This source code is the product of the Firejack Technologies
 * Core Technologies Team (Benjamin A. Miller, Oleg Marshalenko, and Timur
 * Asanov) and licensed only under valid, executed license agreements
 * between Firejack Technologies and its customers. Modification and / or
 * re-distribution of this source code is allowed only within the terms
 * of an executed license agreement.
 *
 * Any modification of this code voids any and all warranties and indemnifications
 * for the component in question and may interfere with upgrade path. Firejack Technologies
 * encourages you to extend the core framework and / or request modifications. You may
 * also submit and assign contributions to Firejack Technologies for consideration
 * as improvements or inclusions to the platform to restore modification
 * warranties and indemnifications upon official re-distributed in patch or release form.
 */


import net.firejack.platform.api.registry.model.FieldType;
import net.firejack.platform.core.model.registry.ParameterTransmissionType;

import static net.firejack.platform.api.registry.model.FieldType.LIST;
import static net.firejack.platform.api.registry.model.FieldType.OBJECT;

public class Property {
    private String name;
    private boolean address;
    private boolean autoGenerated;
    private boolean exclude;
    private FieldType fieldType;
    private PropertyType type;
    private Entity entity;
    private ParameterTransmissionType location;
    private String column;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAddress() {
        return address;
    }

    public void setAddress(boolean address) {
        this.address = address;
    }

    public boolean isAutoGenerated() {
        return autoGenerated;
    }

    public void setAutoGenerated(boolean autoGenerated) {
        this.autoGenerated = autoGenerated;
    }

    public boolean isExclude() {
        return exclude;
    }

    public void setExclude(boolean exclude) {
        this.exclude = exclude;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public ParameterTransmissionType getLocation() {
        return location;
    }

    public void setLocation(ParameterTransmissionType location) {
        this.location = location;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public boolean isRelationship() {
        return fieldType == OBJECT || fieldType == LIST || type == PropertyType.PARENT;
    }

    public boolean isEnum() {
        return type == PropertyType.ENUM;
    }

    public String getDisplayName() {
        if (type == PropertyType.PARENT)
            return "";

        if (entity != null) {
            return entity.getDisplayName();
        }
        return "pk";
    }

    public String getTypeClass() {
        if (type == PropertyType.ENUM) {
            return iPad.ENUM_CLASS;
        } else if (fieldType == OBJECT) {
            return entity.getName() + " *";
        } else {
            return fieldType.getObjectiveC();
        }
    }
}
