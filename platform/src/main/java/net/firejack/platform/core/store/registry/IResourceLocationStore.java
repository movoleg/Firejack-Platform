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

package net.firejack.platform.core.store.registry;

import net.firejack.platform.core.model.SpecifiedIdsFilter;
import net.firejack.platform.core.model.registry.authority.ResourceLocationModel;
import net.firejack.platform.core.model.registry.authority.RoleModel;
import net.firejack.platform.core.utils.Paging;

import java.util.List;
import java.util.Map;

public interface IResourceLocationStore extends IRegistryNodeStore<ResourceLocationModel> {

    ResourceLocationModel findById(Long id);

    /**
     * @param term
     * @param filter
     * @return
     */
    List<ResourceLocationModel> findAllBySearchTermWithFilter(String term, SpecifiedIdsFilter<Long> filter);

    /**
     * @param registryNodeIds
     * @param term
     * @param filter
     * @return
     */
    List<ResourceLocationModel> findAllBySearchTermWithFilter(List<Long> registryNodeIds, String term, SpecifiedIdsFilter<Long> filter);

    List<ResourceLocationModel> findAllBySearchTermWithFilter(
            List<Long> registryNodeIds, String term, SpecifiedIdsFilter<Long> filter, Paging paging);

    List<ResourceLocationModel> findAllBySearchTermWithFilter(String term, SpecifiedIdsFilter<Long> filter, Paging paging);

    /**
     * @param resourceLocation
     */
    void saveWithPermission(ResourceLocationModel resourceLocation);

    /**
     * @return
     */
    Map<ResourceLocationModel, List<RoleModel>> findRolesByResourceLocation();

    /**
     * @return
     */
    List<ResourceLocationModel> findAllWithPermissions();

    /**
     * @param packageLookup
     * @return
     */
    List<ResourceLocationModel> findAllWithPermissions(String packageLookup);

    /**
     * @param resourceLocation
     */
    void saveForGenerator(ResourceLocationModel resourceLocation);

    void delete(ResourceLocationModel resourceLocation);

}