/*
 * Firejack Open Flame - Copyright (c) 2011 Firejack Technologies
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

package net.firejack.platform.core.config.meta.expr;


public enum ExpressionType {
    IN,
    NOT, OR, AND, XOR,
    EQ, NEQ, GT, LT, GTE, LTE,
    PLUS, MINUS, MULTIPLY, DIVIDE,
    IS_NULL, IS_NOT_NULL,
    FUNCTION, BRACES, IDENTIFIER, VALUE;

    /**
     * @return
     */
    public boolean isBinary() {
        return !isUnary() && !this.equals(FUNCTION) && !this.equals(BRACES) && !this.equals(IN) && !this.equals(IDENTIFIER) && !this.equals(VALUE);
    }

    /**
     * @return
     */
    public boolean isNullable() {
        return this.equals(IS_NULL) || this.equals(IS_NOT_NULL);
    }

    /**
     * @return
     */
    public boolean isUnary() {
        return isNullable() || this.equals(NOT);
    }

}