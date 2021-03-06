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

package net.firejack.platform.service.registry.broker.server;

import net.firejack.platform.core.model.registry.system.ServerModel;
import net.firejack.platform.core.store.registry.IRegistryNodeStore;
import net.firejack.platform.core.store.registry.IServerStore;
import net.firejack.platform.service.registry.broker.DeleteAliasableBroker;
import net.firejack.platform.web.statistics.annotation.TrackDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@TrackDetails
@Component("deleteServerBroker")
public class DeleteServerBroker extends DeleteAliasableBroker<ServerModel> {

	@Autowired
	private IServerStore store;

	@Override
	protected String getSuccessMessage() {
		return "Server has deleted successfully";
	}

	@Override
	protected IRegistryNodeStore<ServerModel> getStore() {
		return store;
	}

	@Override
	protected void delete(Long id) {
		store.deleteRecursiveById(id);
	}
}

