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

package net.firejack.platform.service.registry.broker.domain;

import net.firejack.platform.core.config.meta.utils.DiffUtils;
import net.firejack.platform.core.exception.BusinessFunctionException;
import net.firejack.platform.core.model.registry.domain.RootDomainModel;
import net.firejack.platform.core.store.IStore;
import net.firejack.platform.core.store.registry.IRootDomainStore;
import net.firejack.platform.core.utils.OpenFlame;
import net.firejack.platform.service.registry.broker.DeleteRegistryNodeBroker;
import net.firejack.platform.web.statistics.annotation.TrackDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@TrackDetails
@Component("deleteRootDomainBroker")
public class DeleteRootDomainBroker extends DeleteRegistryNodeBroker<RootDomainModel> {

	@Autowired
	private IRootDomainStore store;

	@Override
	protected String getSuccessMessage() {
		return "Root domain has deleted successfully";
	}

	@Override
	protected IStore<RootDomainModel, Long> getStore() {
		return store;
	}

    @Override
    protected void delete(Long id) {
        RootDomainModel rootDomainModel = store.findById(id);
        if (DiffUtils.extractPathFromLookup(OpenFlame.PACKAGE).equals(rootDomainModel.getLookup())) {
            throw new BusinessFunctionException("Can't delete 'net.firejack' root domain.");
        } else {
            super.delete(id);
        }
    }

}

