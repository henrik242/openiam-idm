/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.idm.srvc.meta.ws;

import javax.jws.WebService;

import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.meta.dto.MetadataElement;
import org.openiam.idm.srvc.meta.dto.MetadataType;
import org.openiam.idm.srvc.meta.service.MetadataService;

/**
 * Implementation class for the MetadataWebServiceImpl
 * @author suneet
 * @version 2.1
 */
@WebService(endpointInterface = "org.openiam.idm.srvc.meta.ws.MetadataWebService", 
		targetNamespace = "urn:idm.openiam.org/srvc/meta/service",
		portName = "MetadataWebServicePort",
		serviceName = "MetadataWebService")
public class MetadataWebServiceImpl implements MetadataWebService {
	
	MetadataService metadataService;

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.meta.ws.MetadataWebService#addMetadataElement(org.openiam.idm.srvc.meta.dto.MetadataElement)
	 */
	public MetadataElementResponse addMetadataElement(
			MetadataElement metadataElement) {
		
		MetadataElementResponse resp = new MetadataElementResponse(ResponseStatus.SUCCESS);
		metadataService.addMetadataElement(metadataElement);
		if (metadataElement.getMetadataElementId() == null || metadataElement.getMetadataElementId().isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setMetadataElement(metadataElement);
		}
		return resp;
		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.meta.ws.MetadataWebService#addMetadataType(org.openiam.idm.srvc.meta.dto.MetadataType)
	 */
	public MetadataTypeResponse addMetadataType(MetadataType type) {
		MetadataTypeResponse resp = new MetadataTypeResponse(ResponseStatus.SUCCESS);
		metadataService.addMetadataType(type);
		if (type.getMetadataTypeId() == null || type.getMetadataTypeId().isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setMetadataType(type);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.meta.ws.MetadataWebService#addTypeToCategory(java.lang.String, java.lang.String)
	 */
	public Response addTypeToCategory(String typeId, String categoryId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		metadataService.addTypeToCategory(typeId, categoryId);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.meta.ws.MetadataWebService#getMetadataElementById(java.lang.String)
	 */
	public MetadataElementResponse getMetadataElementById(String elementId) {
		MetadataElementResponse resp = new MetadataElementResponse(ResponseStatus.SUCCESS);
		MetadataElement element = metadataService.getMetadataElementById(elementId);
		if (element == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setMetadataElement(element);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.meta.ws.MetadataWebService#getMetadataElementByType(java.lang.String)
	 */
	public MetadataElementArrayResponse getMetadataElementByType(String typeId) {
		MetadataElementArrayResponse resp = new MetadataElementArrayResponse(ResponseStatus.SUCCESS);
		MetadataElement[] element = metadataService.getMetadataElementByType(typeId);
		if (element == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setMetadataElementAry(element);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.meta.ws.MetadataWebService#getMetadataType(java.lang.String)
	 */
	public MetadataTypeResponse getMetadataType(String typeId) {
		MetadataTypeResponse resp = new MetadataTypeResponse(ResponseStatus.SUCCESS);
		MetadataType type = metadataService.getMetadataType(typeId);
		if (type == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setMetadataType(type);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.meta.ws.MetadataWebService#getMetadataTypes()
	 */
	public MetadataTypeArrayResponse getMetadataTypes() {
		MetadataTypeArrayResponse resp = new MetadataTypeArrayResponse(ResponseStatus.SUCCESS);
		MetadataType[] typeAry = metadataService.getMetadataTypes();
		if (typeAry == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setMetadataTypeAry(typeAry);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.meta.ws.MetadataWebService#getTypesInCategory(java.lang.String)
	 */
	public MetadataTypeArrayResponse getTypesInCategory(String categoryId) {
		MetadataTypeArrayResponse resp = new MetadataTypeArrayResponse(ResponseStatus.SUCCESS);
		MetadataType[] typeAry = metadataService.getTypesInCategory(categoryId);
		if (typeAry == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setMetadataTypeAry(typeAry);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.meta.ws.MetadataWebService#removeMetadataElement(java.lang.String)
	 */
	public Response removeMetadataElement(String elementId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		metadataService.removeMetadataElement(elementId);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.meta.ws.MetadataWebService#removeMetadataType(java.lang.String)
	 */
	public Response removeMetadataType(String typeId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		metadataService.removeMetadataType(typeId);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.meta.ws.MetadataWebService#removeTypeFromCategory(java.lang.String, java.lang.String)
	 */
	public Response removeTypeFromCategory(String typeId, String categoryId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		metadataService.removeTypeFromCategory(typeId, categoryId);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.meta.ws.MetadataWebService#updateMetadataElement(org.openiam.idm.srvc.meta.dto.MetadataElement)
	 */
	public MetadataElementResponse updateMetadataElement(MetadataElement element) {
		MetadataElementResponse resp = new MetadataElementResponse(ResponseStatus.SUCCESS);
		metadataService.updateMetadataElement(element);
		if (element.getMetadataElementId() == null || element.getMetadataElementId().isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setMetadataElement(element);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.meta.ws.MetadataWebService#updateMetdataType(org.openiam.idm.srvc.meta.dto.MetadataType)
	 */
	public MetadataTypeResponse updateMetdataType(MetadataType type) {
		MetadataTypeResponse resp = new MetadataTypeResponse(ResponseStatus.SUCCESS);
		metadataService.updateMetdataType(type);
		if (type.getMetadataTypeId() == null || type.getMetadataTypeId().isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setMetadataType(type);
		}
		return resp;
	}

	public MetadataService getMetadataService() {
		return metadataService;
	}

	public void setMetadataService(MetadataService metadataService) {
		this.metadataService = metadataService;
	}

}
