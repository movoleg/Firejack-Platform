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

package net.firejack.platform.core.store.registry.resource;

import net.firejack.platform.core.model.registry.RegistryNodeModel;
import net.firejack.platform.core.model.registry.domain.ActionModel;
import net.firejack.platform.core.model.registry.domain.ActionParameterModel;
import net.firejack.platform.core.model.registry.resource.CollectionModel;
import net.firejack.platform.core.model.registry.resource.ResourceModel;
import net.firejack.platform.core.model.registry.resource.TextResourceModel;

import java.util.List;

public interface ITextResourceStore extends IResourceStore<TextResourceModel> {

    ResourceModel saveTextResource(RegistryNodeModel registryNode, String name, String text);

    void saveActionParameterDescriptions(
            ActionModel action, List<ActionParameterModel> parametersToProcess, CollectionModel collection);

    void deleteActionParameterDescriptions(List<ActionParameterModel> parametersToProcess);

    /**
     * @param action
     */
//    void createBlazeExample(Action action, Collection collection);

}