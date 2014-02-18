/**
 * Firejack Open Flame - Copyright (c) 2011 Firejack Technologies
 * <p/>
 * This source code is the product of the Firejack Technologies
 * Core Technologies Team (Benjamin A. Miller, Oleg Marshalenko, and Timur
 * Asanov) and licensed only under valid, executed license agreements
 * between Firejack Technologies and its customers. Modification and / or
 * re-distribution of this source code is allowed only within the terms
 * of an executed license agreement.
 * <p/>
 * Any modification of this code voids any and all warranties and indemnifications
 * for the component in question and may interfere with upgrade path. Firejack Technologies
 * encourages you to extend the core framework and / or request modifications. You may
 * also submit and assign contributions to Firejack Technologies for consideration
 * as improvements or inclusions to the platform to restore modification
 * warranties and indemnifications upon official re-distributed in patch or release form.
 */

package net.firejack.platform.generate.beans.web.model.column;

import net.firejack.platform.api.registry.model.FieldType;
import net.firejack.platform.api.registry.model.IndexType;
import net.firejack.platform.api.registry.model.RelationshipType;
import net.firejack.platform.generate.beans.Import;
import net.firejack.platform.generate.beans.web.model.Model;

import java.util.List;


public class Field implements Import {
    private String name;
    private String column;
    private String method;
    private String description;
    private String hint;
    private IndexType index;
    private FieldType type;
    private String displayName;
    private String displayDescription;
    private String value;
    private Model target;
    private Model source;
    private MappedType mapped;
    private RelationshipType relationshipType;
    private String table;
    private String join;
    private String inverseJoin;
    private String projection;
    private boolean lazy;
    private boolean deleteCascade;
    private boolean definition;
    private boolean insertable = true;
    private boolean updatable = true;
    private boolean nullable = true;
    private boolean searchable;
    private boolean autoGenerated;
    private boolean hidden;
    private List<Model> options;
    private List<String> allowValues;

    public IndexType getIndex() {
        return index;
    }

    public void setIndex(IndexType index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public String getColumn() {
        return column;
    }

    /**
     * @param column
     */
    public void setColumn(String column) {
        this.column = column;
    }

    public String getMethod() {
        return method;
    }

    /**
     * @param method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    public FieldType getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(FieldType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public boolean isLazy() {
        return lazy;
    }

    public void setLazy(boolean lazy) {
        this.lazy = lazy;
    }

    public boolean isDeleteCascade() {
        return deleteCascade;
    }

    public void setDeleteCascade(boolean deleteCascade) {
        this.deleteCascade = deleteCascade;
    }

    public boolean isDefinition() {
        return definition;
    }

    public void setDefinition(boolean definition) {
        this.definition = definition;
    }

    public boolean isInsertable() {
        return insertable;
    }

    public void setInsertable(boolean insertable) {
        this.insertable = insertable;
    }

    public boolean isUpdatable() {
        return updatable;
    }

    public void setUpdatable(boolean updatable) {
        this.updatable = updatable;
    }

    /**
     * @return
     */
    public boolean isNullable() {
        return nullable;
    }

    /**
     * @param nullable
     */
    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public String getTypeName() {
        if (mapped == MappedType.ManyToMany) {
            return "List<" + target.getName() + ">";
        } else if (target != null) {
            return target.getName();
        } else {
            return type.getClassName();
        }
    }

    public String getDomainTypeName() {
        if (mapped == MappedType.ManyToOne) {
            return target.getDomain().getName();
        } else if (mapped == MappedType.ManyToMany) {
            String name = target.getDomain().getName();
            if (name.equalsIgnoreCase("property"))
                return "List<" + target.getDomain().getFullName() + ">";
            else
                return "List<" + name + ">";
        } else {
            return type.getClassName();
        }
    }

    public Model getTarget() {
        return target;
    }

    public void setTarget(Model target) {
        this.target = target;
    }

    public Model getSource() {
        return source;
    }

    public void setSource(Model source) {
        this.source = source;
    }

    public MappedType getMapped() {
        return mapped;
    }

    public void setMapped(MappedType mapped) {
        this.mapped = mapped;
    }

    public RelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(RelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

    public String getInverseJoin() {
        return inverseJoin;
    }

    public void setInverseJoin(String inverseJoin) {
        this.inverseJoin = inverseJoin;
    }

    public String getProjection() {
        return projection;
    }

    public void setProjection(String projection) {
        this.projection = projection;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayDescription() {
        return displayDescription;
    }

    public void setDisplayDescription(String displayDescription) {
        this.displayDescription = displayDescription;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isAutoGenerated() {
        return autoGenerated;
    }

    public void setAutoGenerated(boolean autoGenerated) {
        this.autoGenerated = autoGenerated;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isOptionExist() {
        return options != null && !options.isEmpty();
    }

    public List<Model> getOptions() {
        return options;
    }

    public void setOptions(List<Model> options) {
        this.options = options;
    }

    public List<String> getAllowValues() {
        return allowValues;
    }

    public void setAllowValues(List<String> allowValues) {
        this.allowValues = allowValues;
    }

    @Override
    public String getPackage() {
        if (target != null && !target.isNested()) {
            return target.getPackage();
        }
        return null;
    }

    @Override
    public String getFullName() {
        return target.getFullName();
    }

    @Override
    public int compareTo(Import o) {
        return target != null ? target.compareTo(o) : 0;
    }

    /**
     * @return
     */
    public boolean isSimply() {
        return !type.equals(FieldType.OBJECT) && !type.equals(FieldType.LIST);
    }

    public String getJsonType() {
        if (type.isString()) {
            return "string";
        } else if (type.isBoolean()) {
            return "boolean";
        } else if (type.isTimeRelated()) {
            return "date";
        } else if (type.isInteger()) {
            return "int";
        } else if (type.isReal()) {
            return "float";
        } else {
            return "auto";
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Field");
        sb.append("{name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}