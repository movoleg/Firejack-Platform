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

package net.firejack.platform.service.directory.broker.profile;

import net.firejack.platform.api.directory.domain.UserProfileFieldGroupTree;
import net.firejack.platform.core.broker.ListBroker;
import net.firejack.platform.core.domain.SimpleIdentifier;
import net.firejack.platform.core.exception.BusinessFunctionException;
import net.firejack.platform.core.model.registry.RegistryNodeModel;
import net.firejack.platform.core.model.user.UserProfileFieldGroupModel;
import net.firejack.platform.core.request.ServiceRequest;
import net.firejack.platform.core.store.registry.IRegistryNodeStore;
import net.firejack.platform.core.store.user.IUserProfileFieldGroupStore;
import net.firejack.platform.web.statistics.annotation.TrackDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@TrackDetails
@Component("readUserProfileFieldGroupsByRegistryNodeBroker")
public class ReadUserProfileFieldGroupsByRegistryNodeBroker
        extends ListBroker<UserProfileFieldGroupModel, UserProfileFieldGroupTree, SimpleIdentifier<Long>> {

	@Autowired
    @Qualifier("userProfileFieldGroupStore")
	protected IUserProfileFieldGroupStore store;

    @Autowired
    @Qualifier("registryNodeStore")
    private IRegistryNodeStore<RegistryNodeModel> registryNodeStore;

	@Override
	protected List<UserProfileFieldGroupModel> getModelList(ServiceRequest<SimpleIdentifier<Long>> request) throws BusinessFunctionException {
        Long registryNodeId = request.getData().getIdentifier();
        List<UserProfileFieldGroupModel> userProfileFieldGroupModels = store.findUserProfileFieldGroupsByRegistryNodeId(registryNodeId);
        UserProfileFieldGroupModel general = createGeneral(registryNodeId);
        userProfileFieldGroupModels.add(0, general);
        return userProfileFieldGroupModels;
	}

    private UserProfileFieldGroupModel createGeneral(Long registryNodeId) {
        UserProfileFieldGroupModel general = new UserProfileFieldGroupModel();
        general.setId(0L);
        general.setName("General");
        general.setCreated(new Date());
        general.setChildren(new ArrayList<RegistryNodeModel>());
        RegistryNodeModel registryNodeModel = registryNodeStore.findById(registryNodeId);
        if (registryNodeModel != null) {
            general.setPath(registryNodeModel.getLookup());
            general.setLookup(registryNodeModel.getLookup() + ".general");
            general.setParent(registryNodeModel);
        }
        return general;
    }
}
