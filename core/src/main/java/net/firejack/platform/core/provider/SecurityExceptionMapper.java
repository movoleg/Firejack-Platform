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

package net.firejack.platform.core.provider;

import net.firejack.platform.core.broker.security.SecurityException;
import net.firejack.platform.core.response.ServiceResponse;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Component
@Provider
public class SecurityExceptionMapper implements ExceptionMapper<SecurityException> {

    @Context
    private HttpHeaders headers;

    @Override
    public Response toResponse(SecurityException exception) {
        ServiceResponse respStatus = new ServiceResponse(exception.getMessage(), false);
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(respStatus).type(headers.getMediaType()).build();
    }

}