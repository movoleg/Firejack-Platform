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

package net.firejack.platform.service.content.broker.collection;

import net.firejack.platform.core.broker.ServiceBroker;
import net.firejack.platform.core.domain.NamedValues;
import net.firejack.platform.core.request.ServiceRequest;
import net.firejack.platform.core.response.ServiceResponse;
import net.firejack.platform.core.store.registry.resource.ICollectionStore;
import net.firejack.platform.web.statistics.annotation.TrackDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("swapCollectionBroker")
@TrackDetails
public class SwapCollectionBroker
        extends ServiceBroker<ServiceRequest<NamedValues<Long>>, ServiceResponse> {

	@Autowired
	@Qualifier("collectionStore")
	private ICollectionStore collectionStore;

	@Override
	public ServiceResponse perform(ServiceRequest<NamedValues<Long>> request) throws Exception {
        NamedValues<Long> data = request.getData();
        Long collectionId = data.get("collectionId");
		Long oneRefId = data.get("oneRefId");
		Long twoRefId = data.get("twoRefId");

		collectionStore.swapCollectionMemberships(collectionId, oneRefId, twoRefId);
		return new ServiceResponse("Swap Success", true);
	}

}
