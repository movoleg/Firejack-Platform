/**
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
package net.firejack.platform.service.securitymanager.endpoint;

import net.firejack.platform.api.securitymanager.domain.MoveSecuredRecordInfo;
import net.firejack.platform.api.securitymanager.domain.SecuredRecord;
import net.firejack.platform.api.securitymanager.domain.SecuredRecordNode;
import net.firejack.platform.api.securitymanager.domain.TreeNodeSecuredRecord;
import net.firejack.platform.core.domain.IdLookup;
import net.firejack.platform.core.request.ServiceRequest;
import net.firejack.platform.core.response.ServiceResponse;
import org.apache.cxf.interceptor.InFaultInterceptors;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutInterceptors;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@SOAPBinding(style = SOAPBinding.Style.RPC)
@InInterceptors(interceptors = {
		"org.apache.cxf.interceptor.LoggingInInterceptor",
		"org.apache.cxf.binding.soap.saaj.SAAJInInterceptor",
		"net.firejack.platform.web.security.ws.interceptor.OpenFlameWSS4JInInterceptor",
		"net.firejack.platform.web.security.ws.interceptor.OpenFlameAuthorizingInInterceptor"})
@OutInterceptors(interceptors = {
		"org.apache.cxf.interceptor.LoggingOutInterceptor",
		"net.firejack.platform.web.security.ws.interceptor.OpenFlameWSS4JOutInterceptor"})
@InFaultInterceptors(interceptors = "org.apache.cxf.interceptor.LoggingOutInterceptor")
@WebService(endpointInterface = "net.firejack.platform.service.securitymanager.endpoint.ISecuredRecordEndpoint")
public interface ISecuredRecordEndpoint {
	@WebMethod
	ServiceResponse<SecuredRecord> readSecuredRecordByEntityIdAndType(
			@WebParam(name = "entityId") Long entityId,
			@WebParam(name = "typeLookup") String typeLookup);

	@WebMethod
	ServiceResponse<SecuredRecord> createSecuredRecord(
			@WebParam(name = "parentId") Long parentId,
			@WebParam(name = "parentType") String parentType,
			@WebParam(name = "request") ServiceRequest<SecuredRecord> request);

    @WebMethod
    ServiceResponse createSecuredRecords(
            @WebParam(name = "request") ServiceRequest<TreeNodeSecuredRecord> request);

	@WebMethod
	ServiceResponse<SecuredRecordNode> readAllSecuredRecords();

	@WebMethod
	ServiceResponse<SecuredRecord> moveSecuredRecord(@WebParam(name = "request") ServiceRequest<MoveSecuredRecordInfo> request);

	@WebMethod
	ServiceResponse updateSecuredRecord(@WebParam(name = "request") ServiceRequest<SecuredRecord> request);

	@WebMethod
	ServiceResponse deleteSecuredRecord(
            @WebParam(name = "entityId") Long entityId,@WebParam(name = "typeLookup") String typeLookup,
            @WebParam(name = "entityCustomId") String entityCustomId);

    @WebMethod
    ServiceResponse deleteSecuredRecords(@WebParam(name = "request") ServiceRequest<IdLookup> request);

}