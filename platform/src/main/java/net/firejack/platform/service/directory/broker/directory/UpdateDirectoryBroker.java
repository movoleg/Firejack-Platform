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

package net.firejack.platform.service.directory.broker.directory;

import net.firejack.platform.api.directory.domain.Directory;
import net.firejack.platform.api.registry.domain.RegistryNodeTree;
import net.firejack.platform.core.broker.OPFSaveBroker;
import net.firejack.platform.core.model.registry.directory.DirectoryModel;
import net.firejack.platform.core.store.registry.IDirectoryStore;
import net.firejack.platform.web.statistics.annotation.TrackDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@TrackDetails
@Component("updateDirectoryBroker")
public class UpdateDirectoryBroker extends OPFSaveBroker<DirectoryModel, Directory, RegistryNodeTree> {

	@Autowired
	private IDirectoryStore store;

	@Override
	protected String getSuccessMessage(boolean isNew) {
		return "Directory has been updated successfully.";
	}

	@Override
	protected DirectoryModel convertToEntity(Directory model) {
		return factory.convertFrom(DirectoryModel.class, model);
	}

	@Override
	protected RegistryNodeTree convertToModel(DirectoryModel entity) {
		return factory.convertTo(RegistryNodeTree.class, entity);
	}

	@Override
	protected void save(DirectoryModel model) throws Exception {
		store.save(model);
	}
}
