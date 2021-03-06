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

package net.firejack.platform.core.validation;


import net.firejack.platform.core.validation.annotation.GreaterThan;
import net.firejack.platform.core.validation.annotation.ValidationMode;
import net.firejack.platform.core.validation.constraint.vo.Constraint;
import net.firejack.platform.core.validation.exception.RuleValidationException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class GreaterThanProcessor implements IMessageRuleProcessor {

    @Override
    public List<ValidationMessage> validate(Method readMethod, String property, Object value, ValidationMode mode)
            throws RuleValidationException {
        GreaterThan greaterThanAnnotation = readMethod.getAnnotation(GreaterThan.class);
        List<ValidationMessage> messages = null;
        if (greaterThanAnnotation != null) {
            messages = new ArrayList<ValidationMessage>();
            Class<?> returnType = readMethod.getReturnType();
            String parameterName = StringUtils.isBlank(greaterThanAnnotation.parameterName()) ?
                    property : greaterThanAnnotation.parameterName();
            if (value != null) {
                if (returnType.getSuperclass() == Number.class || returnType.isPrimitive()) {
                    boolean checkEquality = greaterThanAnnotation.checkEquality();
                    Number val = null;
                    if (returnType == Float.class || returnType == float.class) {
                        Float f = (Float) value;
                        if (checkEquality && f < greaterThanAnnotation.floatVal() ||
                                !checkEquality && f <= greaterThanAnnotation.floatVal()) {
                            val = greaterThanAnnotation.floatVal();
                        }
                    } else if (returnType == Double.class || returnType == double.class) {
                        Double d = (Double) value;
                        if (checkEquality && d < greaterThanAnnotation.doubleVal() ||
                                !checkEquality && d <= greaterThanAnnotation.doubleVal()) {
                            val = greaterThanAnnotation.doubleVal();
                        }
                    } else {
                        Long longValue = ((Number) value).longValue();
                        Long rangeValue = ((Integer) greaterThanAnnotation.intVal()).longValue();
                        if (checkEquality && longValue < rangeValue || !checkEquality && longValue <= rangeValue) {
                            val = greaterThanAnnotation.intVal();
                        }
                    }
                    if (val != null) {
                        messages.add(new ValidationMessage(property,
                                checkEquality ? greaterThanAnnotation.equalityMsgKey() :
                                        greaterThanAnnotation.msgKey(), parameterName, val));
                    }
                }
            }
        }
        return messages;
    }

    @Override
    public List<Constraint> generate(Method readMethod, String property, Map<String, String> params) {
        return null;
    }

}