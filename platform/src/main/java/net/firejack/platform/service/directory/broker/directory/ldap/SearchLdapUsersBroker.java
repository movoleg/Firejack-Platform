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

package net.firejack.platform.service.directory.broker.directory.ldap;

import net.firejack.platform.api.directory.domain.User;
import net.firejack.platform.core.domain.NamedValues;
import net.firejack.platform.core.external.ldap.LdapServiceFacade;
import net.firejack.platform.core.request.ServiceRequest;
import net.firejack.platform.core.response.ServiceResponse;
import net.firejack.platform.core.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
@Transactional
public class SearchLdapUsersBroker extends BaseLdapBroker<User> {

    public static final String PARAM_SEARCH_TERM = "PARAM_SEARCH_TERM";

    @Override
    protected ServiceResponse<User> performInternal(
            ServiceRequest<NamedValues<Object>> request, LdapServiceFacade ldapService) {
        ServiceResponse<User> response;
        try {
            String searchTerm = (String) request.getData().get(PARAM_SEARCH_TERM);
            List<User> foundUsers = StringUtils.isBlank(searchTerm) ?
                    ldapService.getAllUsers() : ldapService.searchUsers(searchTerm);
            response = foundUsers == null || foundUsers.isEmpty() ?
                    new ServiceResponse<User>("No users found for specified search criteria.", true) :
                    new ServiceResponse<User>(foundUsers, "Users were found for specified search criteria.", true);
        } catch (Throwable e) {
            logger.error("Failed to update ldap user. Reason: " + e.getMessage(), e);
            response = new ServiceResponse<User>(e.getMessage(), false);
        }
        return response;
    }

}