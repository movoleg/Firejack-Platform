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

package net.firejack.platform.service.content.broker.resource.image;

import net.firejack.platform.api.content.domain.ImageResourceVersion;
import net.firejack.platform.core.broker.ServiceBroker;
import net.firejack.platform.core.model.registry.resource.Cultures;
import net.firejack.platform.core.model.registry.resource.ImageResourceVersionModel;
import net.firejack.platform.core.request.ServiceRequest;
import net.firejack.platform.core.response.ServiceResponse;
import net.firejack.platform.core.store.registry.resource.IResourceVersionStore;
import net.firejack.platform.core.utils.ResourceFileUtil;
import net.firejack.platform.web.statistics.annotation.TrackDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("updateImageResourceVersionBroker")
@TrackDetails
public class UpdateImageResourceVersionBroker
        extends ServiceBroker<ServiceRequest<ImageResourceVersion>, ServiceResponse<ImageResourceVersion>> {

    @Autowired
    @Qualifier("resourceFileUtil")
    private ResourceFileUtil resourceFileUtil;

    @Autowired
    @Qualifier("imageResourceVersionStore")
    private IResourceVersionStore<ImageResourceVersionModel> imageResourceVersionStore;

    @Override
    protected ServiceResponse<ImageResourceVersion> perform(ServiceRequest<ImageResourceVersion> request) throws Exception {
        Long resourceId = request.getData().getResourceId();
        String resourceLookup = request.getData().getResourceLookup();
        Integer version = request.getData().getVersion();
        Cultures culture = request.getData().getCulture();
        String temporaryUploadedFileName = request.getData().getResourceFileTemporaryName();
        String resourceFileOriginalName = request.getData().getResourceFileOriginalName();
        
        try {
            ImageResourceVersionModel imageResourceVersionModel = null;
            if (resourceId != null) {
                imageResourceVersionModel = imageResourceVersionStore.findByResourceIdCultureAndVersion(resourceId, culture, version);
            } else if (resourceLookup != null) {
                imageResourceVersionModel = imageResourceVersionStore.findLastVersionByLookup(resourceLookup);
            }
            if (imageResourceVersionModel == null) {
                imageResourceVersionModel = imageResourceVersionStore.createNewResourceVersion(resourceId, version, culture);
            }

            resourceFileUtil.processTempFile(temporaryUploadedFileName, imageResourceVersionModel);
            imageResourceVersionModel.setOriginalFilename(resourceFileOriginalName);

            imageResourceVersionStore.saveOrUpdate(imageResourceVersionModel);

            ImageResourceVersion imageResourceVersion = factory.convertTo(ImageResourceVersion.class, imageResourceVersionModel);
            return new ServiceResponse<ImageResourceVersion>(imageResourceVersion, "Image resource saved successfully.", true);
        } catch (Exception e) {
            logger.error("Error saving image resource", e);
            return new ServiceResponse("Error saving image resource!", false);
        }
    }
}