
package org.openiam.idm.srvc.role.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.openiam.idm.srvc.role.types package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _IsUserInRoleResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "isUserInRoleResponse");
    private final static QName _GetAllRoles_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getAllRoles");
    private final static QName _IsGroupInRole_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "isGroupInRole");
    private final static QName _AddUserToRoleResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "addUserToRoleResponse");
    private final static QName _AddGroupToRoleResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "addGroupToRoleResponse");
    private final static QName _GetAllAttributesResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getAllAttributesResponse");
    private final static QName _RemoveUserFromRole_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "removeUserFromRole");
    private final static QName _RemoveAllAttributes_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "removeAllAttributes");
    private final static QName _GetRolesInGroupResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getRolesInGroupResponse");
    private final static QName _UpdateAttributeResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "updateAttributeResponse");
    private final static QName _GetAllAttributes_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getAllAttributes");
    private final static QName _RemoveRole_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "removeRole");
    private final static QName _GetUserRolesByService_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getUserRolesByService");
    private final static QName _GetRolesInServiceResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getRolesInServiceResponse");
    private final static QName _GetAttribute_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getAttribute");
    private final static QName _RemoveAllAttributesResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "removeAllAttributesResponse");
    private final static QName _UpdateRoleResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "updateRoleResponse");
    private final static QName _GetUserRolesByServiceResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getUserRolesByServiceResponse");
    private final static QName _GetAllRolesResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getAllRolesResponse");
    private final static QName _GetGroupsInRole_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getGroupsInRole");
    private final static QName _RemoveAllGroupsFromRoleResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "removeAllGroupsFromRoleResponse");
    private final static QName _GetRoleWithDependantsResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getRoleWithDependantsResponse");
    private final static QName _UpdateRole_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "updateRole");
    private final static QName _AddUserToRole_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "addUserToRole");
    private final static QName _RemoveRoleResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "removeRoleResponse");
    private final static QName _GetUsersInRole_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getUsersInRole");
    private final static QName _GetUserRoles_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getUserRoles");
    private final static QName _AddAttributeResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "addAttributeResponse");
    private final static QName _RemoveUserFromRoleResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "removeUserFromRoleResponse");
    private final static QName _GetUsersInRoleResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getUsersInRoleResponse");
    private final static QName _GetRole_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getRole");
    private final static QName _GetRoleResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getRoleResponse");
    private final static QName _IsUserInRole_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "isUserInRole");
    private final static QName _GetRolesInGroup_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getRolesInGroup");
    private final static QName _RemoveAttributeResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "removeAttributeResponse");
    private final static QName _RemoveAllGroupsFromRole_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "removeAllGroupsFromRole");
    private final static QName _GetRoleWithDependants_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getRoleWithDependants");
    private final static QName _AddRoleResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "addRoleResponse");
    private final static QName _GetRolesInService_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getRolesInService");
    private final static QName _RemoveGroupFromRole_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "removeGroupFromRole");
    private final static QName _IsGroupInRoleResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "isGroupInRoleResponse");
    private final static QName _RemoveAttribute_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "removeAttribute");
    private final static QName _AddAttribute_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "addAttribute");
    private final static QName _GetGroupsInRoleResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getGroupsInRoleResponse");
    private final static QName _UpdateAttribute_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "updateAttribute");
    private final static QName _GetUserRolesResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getUserRolesResponse");
    private final static QName _AddRole_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "addRole");
    private final static QName _GetAttributeResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "getAttributeResponse");
    private final static QName _RemoveGroupFromRoleResponse_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "removeGroupFromRoleResponse");
    private final static QName _AddGroupToRole_QNAME = new QName("urn:idm.openiam.org/srvc/role/types", "addGroupToRole");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.openiam.idm.srvc.role.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetUserRolesResponse }
     * 
     */
    public GetUserRolesResponse createGetUserRolesResponse() {
        return new GetUserRolesResponse();
    }

    /**
     * Create an instance of {@link GetRolesInGroupResponse }
     * 
     */
    public GetRolesInGroupResponse createGetRolesInGroupResponse() {
        return new GetRolesInGroupResponse();
    }

    /**
     * Create an instance of {@link GetAllRoles }
     * 
     */
    public GetAllRoles createGetAllRoles() {
        return new GetAllRoles();
    }

    /**
     * Create an instance of {@link GetAttributeResponse }
     * 
     */
    public GetAttributeResponse createGetAttributeResponse() {
        return new GetAttributeResponse();
    }

    /**
     * Create an instance of {@link UpdateRoleResponse }
     * 
     */
    public UpdateRoleResponse createUpdateRoleResponse() {
        return new UpdateRoleResponse();
    }

    /**
     * Create an instance of {@link AddUserToRoleResponse }
     * 
     */
    public AddUserToRoleResponse createAddUserToRoleResponse() {
        return new AddUserToRoleResponse();
    }

    /**
     * Create an instance of {@link AddGroupToRole }
     * 
     */
    public AddGroupToRole createAddGroupToRole() {
        return new AddGroupToRole();
    }

    /**
     * Create an instance of {@link AddGroupToRoleResponse }
     * 
     */
    public AddGroupToRoleResponse createAddGroupToRoleResponse() {
        return new AddGroupToRoleResponse();
    }

    /**
     * Create an instance of {@link IsUserInRole }
     * 
     */
    public IsUserInRole createIsUserInRole() {
        return new IsUserInRole();
    }

    /**
     * Create an instance of {@link GetRole }
     * 
     */
    public GetRole createGetRole() {
        return new GetRole();
    }

    /**
     * Create an instance of {@link IsGroupInRoleResponse }
     * 
     */
    public IsGroupInRoleResponse createIsGroupInRoleResponse() {
        return new IsGroupInRoleResponse();
    }

    /**
     * Create an instance of {@link RemoveUserFromRoleResponse }
     * 
     */
    public RemoveUserFromRoleResponse createRemoveUserFromRoleResponse() {
        return new RemoveUserFromRoleResponse();
    }

    /**
     * Create an instance of {@link AddRole }
     * 
     */
    public AddRole createAddRole() {
        return new AddRole();
    }

    /**
     * Create an instance of {@link RemoveAllAttributes }
     * 
     */
    public RemoveAllAttributes createRemoveAllAttributes() {
        return new RemoveAllAttributes();
    }

    /**
     * Create an instance of {@link GetRoleWithDependantsResponse }
     * 
     */
    public GetRoleWithDependantsResponse createGetRoleWithDependantsResponse() {
        return new GetRoleWithDependantsResponse();
    }

    /**
     * Create an instance of {@link GetAllRolesResponse }
     * 
     */
    public GetAllRolesResponse createGetAllRolesResponse() {
        return new GetAllRolesResponse();
    }

    /**
     * Create an instance of {@link GetRoleWithDependants }
     * 
     */
    public GetRoleWithDependants createGetRoleWithDependants() {
        return new GetRoleWithDependants();
    }

    /**
     * Create an instance of {@link UpdateAttribute }
     * 
     */
    public UpdateAttribute createUpdateAttribute() {
        return new UpdateAttribute();
    }

    /**
     * Create an instance of {@link IsGroupInRole }
     * 
     */
    public IsGroupInRole createIsGroupInRole() {
        return new IsGroupInRole();
    }

    /**
     * Create an instance of {@link RemoveGroupFromRole }
     * 
     */
    public RemoveGroupFromRole createRemoveGroupFromRole() {
        return new RemoveGroupFromRole();
    }

    /**
     * Create an instance of {@link IsUserInRoleResponse }
     * 
     */
    public IsUserInRoleResponse createIsUserInRoleResponse() {
        return new IsUserInRoleResponse();
    }

    /**
     * Create an instance of {@link GetGroupsInRole }
     * 
     */
    public GetGroupsInRole createGetGroupsInRole() {
        return new GetGroupsInRole();
    }

    /**
     * Create an instance of {@link GetUsersInRoleResponse }
     * 
     */
    public GetUsersInRoleResponse createGetUsersInRoleResponse() {
        return new GetUsersInRoleResponse();
    }

    /**
     * Create an instance of {@link GetRolesInService }
     * 
     */
    public GetRolesInService createGetRolesInService() {
        return new GetRolesInService();
    }

    /**
     * Create an instance of {@link RemoveAttributeResponse }
     * 
     */
    public RemoveAttributeResponse createRemoveAttributeResponse() {
        return new RemoveAttributeResponse();
    }

    /**
     * Create an instance of {@link GetUserRolesByService }
     * 
     */
    public GetUserRolesByService createGetUserRolesByService() {
        return new GetUserRolesByService();
    }

    /**
     * Create an instance of {@link RemoveAllGroupsFromRoleResponse }
     * 
     */
    public RemoveAllGroupsFromRoleResponse createRemoveAllGroupsFromRoleResponse() {
        return new RemoveAllGroupsFromRoleResponse();
    }

    /**
     * Create an instance of {@link RemoveRoleResponse }
     * 
     */
    public RemoveRoleResponse createRemoveRoleResponse() {
        return new RemoveRoleResponse();
    }

    /**
     * Create an instance of {@link GetRoleResponse }
     * 
     */
    public GetRoleResponse createGetRoleResponse() {
        return new GetRoleResponse();
    }

    /**
     * Create an instance of {@link GetGroupsInRoleResponse }
     * 
     */
    public GetGroupsInRoleResponse createGetGroupsInRoleResponse() {
        return new GetGroupsInRoleResponse();
    }

    /**
     * Create an instance of {@link UpdateRole }
     * 
     */
    public UpdateRole createUpdateRole() {
        return new UpdateRole();
    }

    /**
     * Create an instance of {@link AddAttributeResponse }
     * 
     */
    public AddAttributeResponse createAddAttributeResponse() {
        return new AddAttributeResponse();
    }

    /**
     * Create an instance of {@link GetAllAttributes }
     * 
     */
    public GetAllAttributes createGetAllAttributes() {
        return new GetAllAttributes();
    }

    /**
     * Create an instance of {@link GetAttribute }
     * 
     */
    public GetAttribute createGetAttribute() {
        return new GetAttribute();
    }

    /**
     * Create an instance of {@link RemoveAllGroupsFromRole }
     * 
     */
    public RemoveAllGroupsFromRole createRemoveAllGroupsFromRole() {
        return new RemoveAllGroupsFromRole();
    }

    /**
     * Create an instance of {@link GetRolesInGroup }
     * 
     */
    public GetRolesInGroup createGetRolesInGroup() {
        return new GetRolesInGroup();
    }

    /**
     * Create an instance of {@link RemoveGroupFromRoleResponse }
     * 
     */
    public RemoveGroupFromRoleResponse createRemoveGroupFromRoleResponse() {
        return new RemoveGroupFromRoleResponse();
    }

    /**
     * Create an instance of {@link AddAttribute }
     * 
     */
    public AddAttribute createAddAttribute() {
        return new AddAttribute();
    }

    /**
     * Create an instance of {@link AddRoleResponse }
     * 
     */
    public AddRoleResponse createAddRoleResponse() {
        return new AddRoleResponse();
    }

    /**
     * Create an instance of {@link GetRolesInServiceResponse }
     * 
     */
    public GetRolesInServiceResponse createGetRolesInServiceResponse() {
        return new GetRolesInServiceResponse();
    }

    /**
     * Create an instance of {@link RemoveAllAttributesResponse }
     * 
     */
    public RemoveAllAttributesResponse createRemoveAllAttributesResponse() {
        return new RemoveAllAttributesResponse();
    }

    /**
     * Create an instance of {@link RemoveRole }
     * 
     */
    public RemoveRole createRemoveRole() {
        return new RemoveRole();
    }

    /**
     * Create an instance of {@link AddUserToRole }
     * 
     */
    public AddUserToRole createAddUserToRole() {
        return new AddUserToRole();
    }

    /**
     * Create an instance of {@link UpdateAttributeResponse }
     * 
     */
    public UpdateAttributeResponse createUpdateAttributeResponse() {
        return new UpdateAttributeResponse();
    }

    /**
     * Create an instance of {@link GetUsersInRole }
     * 
     */
    public GetUsersInRole createGetUsersInRole() {
        return new GetUsersInRole();
    }

    /**
     * Create an instance of {@link GetUserRolesByServiceResponse }
     * 
     */
    public GetUserRolesByServiceResponse createGetUserRolesByServiceResponse() {
        return new GetUserRolesByServiceResponse();
    }

    /**
     * Create an instance of {@link GetAllAttributesResponse }
     * 
     */
    public GetAllAttributesResponse createGetAllAttributesResponse() {
        return new GetAllAttributesResponse();
    }

    /**
     * Create an instance of {@link RemoveAttribute }
     * 
     */
    public RemoveAttribute createRemoveAttribute() {
        return new RemoveAttribute();
    }

    /**
     * Create an instance of {@link RemoveUserFromRole }
     * 
     */
    public RemoveUserFromRole createRemoveUserFromRole() {
        return new RemoveUserFromRole();
    }

    /**
     * Create an instance of {@link GetUserRoles }
     * 
     */
    public GetUserRoles createGetUserRoles() {
        return new GetUserRoles();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsUserInRoleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "isUserInRoleResponse")
    public JAXBElement<IsUserInRoleResponse> createIsUserInRoleResponse(IsUserInRoleResponse value) {
        return new JAXBElement<IsUserInRoleResponse>(_IsUserInRoleResponse_QNAME, IsUserInRoleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllRoles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getAllRoles")
    public JAXBElement<GetAllRoles> createGetAllRoles(GetAllRoles value) {
        return new JAXBElement<GetAllRoles>(_GetAllRoles_QNAME, GetAllRoles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsGroupInRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "isGroupInRole")
    public JAXBElement<IsGroupInRole> createIsGroupInRole(IsGroupInRole value) {
        return new JAXBElement<IsGroupInRole>(_IsGroupInRole_QNAME, IsGroupInRole.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUserToRoleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "addUserToRoleResponse")
    public JAXBElement<AddUserToRoleResponse> createAddUserToRoleResponse(AddUserToRoleResponse value) {
        return new JAXBElement<AddUserToRoleResponse>(_AddUserToRoleResponse_QNAME, AddUserToRoleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddGroupToRoleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "addGroupToRoleResponse")
    public JAXBElement<AddGroupToRoleResponse> createAddGroupToRoleResponse(AddGroupToRoleResponse value) {
        return new JAXBElement<AddGroupToRoleResponse>(_AddGroupToRoleResponse_QNAME, AddGroupToRoleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllAttributesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getAllAttributesResponse")
    public JAXBElement<GetAllAttributesResponse> createGetAllAttributesResponse(GetAllAttributesResponse value) {
        return new JAXBElement<GetAllAttributesResponse>(_GetAllAttributesResponse_QNAME, GetAllAttributesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUserFromRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "removeUserFromRole")
    public JAXBElement<RemoveUserFromRole> createRemoveUserFromRole(RemoveUserFromRole value) {
        return new JAXBElement<RemoveUserFromRole>(_RemoveUserFromRole_QNAME, RemoveUserFromRole.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAllAttributes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "removeAllAttributes")
    public JAXBElement<RemoveAllAttributes> createRemoveAllAttributes(RemoveAllAttributes value) {
        return new JAXBElement<RemoveAllAttributes>(_RemoveAllAttributes_QNAME, RemoveAllAttributes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRolesInGroupResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getRolesInGroupResponse")
    public JAXBElement<GetRolesInGroupResponse> createGetRolesInGroupResponse(GetRolesInGroupResponse value) {
        return new JAXBElement<GetRolesInGroupResponse>(_GetRolesInGroupResponse_QNAME, GetRolesInGroupResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateAttributeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "updateAttributeResponse")
    public JAXBElement<UpdateAttributeResponse> createUpdateAttributeResponse(UpdateAttributeResponse value) {
        return new JAXBElement<UpdateAttributeResponse>(_UpdateAttributeResponse_QNAME, UpdateAttributeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllAttributes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getAllAttributes")
    public JAXBElement<GetAllAttributes> createGetAllAttributes(GetAllAttributes value) {
        return new JAXBElement<GetAllAttributes>(_GetAllAttributes_QNAME, GetAllAttributes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "removeRole")
    public JAXBElement<RemoveRole> createRemoveRole(RemoveRole value) {
        return new JAXBElement<RemoveRole>(_RemoveRole_QNAME, RemoveRole.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserRolesByService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getUserRolesByService")
    public JAXBElement<GetUserRolesByService> createGetUserRolesByService(GetUserRolesByService value) {
        return new JAXBElement<GetUserRolesByService>(_GetUserRolesByService_QNAME, GetUserRolesByService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRolesInServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getRolesInServiceResponse")
    public JAXBElement<GetRolesInServiceResponse> createGetRolesInServiceResponse(GetRolesInServiceResponse value) {
        return new JAXBElement<GetRolesInServiceResponse>(_GetRolesInServiceResponse_QNAME, GetRolesInServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAttribute }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getAttribute")
    public JAXBElement<GetAttribute> createGetAttribute(GetAttribute value) {
        return new JAXBElement<GetAttribute>(_GetAttribute_QNAME, GetAttribute.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAllAttributesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "removeAllAttributesResponse")
    public JAXBElement<RemoveAllAttributesResponse> createRemoveAllAttributesResponse(RemoveAllAttributesResponse value) {
        return new JAXBElement<RemoveAllAttributesResponse>(_RemoveAllAttributesResponse_QNAME, RemoveAllAttributesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateRoleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "updateRoleResponse")
    public JAXBElement<UpdateRoleResponse> createUpdateRoleResponse(UpdateRoleResponse value) {
        return new JAXBElement<UpdateRoleResponse>(_UpdateRoleResponse_QNAME, UpdateRoleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserRolesByServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getUserRolesByServiceResponse")
    public JAXBElement<GetUserRolesByServiceResponse> createGetUserRolesByServiceResponse(GetUserRolesByServiceResponse value) {
        return new JAXBElement<GetUserRolesByServiceResponse>(_GetUserRolesByServiceResponse_QNAME, GetUserRolesByServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllRolesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getAllRolesResponse")
    public JAXBElement<GetAllRolesResponse> createGetAllRolesResponse(GetAllRolesResponse value) {
        return new JAXBElement<GetAllRolesResponse>(_GetAllRolesResponse_QNAME, GetAllRolesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGroupsInRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getGroupsInRole")
    public JAXBElement<GetGroupsInRole> createGetGroupsInRole(GetGroupsInRole value) {
        return new JAXBElement<GetGroupsInRole>(_GetGroupsInRole_QNAME, GetGroupsInRole.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAllGroupsFromRoleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "removeAllGroupsFromRoleResponse")
    public JAXBElement<RemoveAllGroupsFromRoleResponse> createRemoveAllGroupsFromRoleResponse(RemoveAllGroupsFromRoleResponse value) {
        return new JAXBElement<RemoveAllGroupsFromRoleResponse>(_RemoveAllGroupsFromRoleResponse_QNAME, RemoveAllGroupsFromRoleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRoleWithDependantsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getRoleWithDependantsResponse")
    public JAXBElement<GetRoleWithDependantsResponse> createGetRoleWithDependantsResponse(GetRoleWithDependantsResponse value) {
        return new JAXBElement<GetRoleWithDependantsResponse>(_GetRoleWithDependantsResponse_QNAME, GetRoleWithDependantsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "updateRole")
    public JAXBElement<UpdateRole> createUpdateRole(UpdateRole value) {
        return new JAXBElement<UpdateRole>(_UpdateRole_QNAME, UpdateRole.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUserToRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "addUserToRole")
    public JAXBElement<AddUserToRole> createAddUserToRole(AddUserToRole value) {
        return new JAXBElement<AddUserToRole>(_AddUserToRole_QNAME, AddUserToRole.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveRoleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "removeRoleResponse")
    public JAXBElement<RemoveRoleResponse> createRemoveRoleResponse(RemoveRoleResponse value) {
        return new JAXBElement<RemoveRoleResponse>(_RemoveRoleResponse_QNAME, RemoveRoleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsersInRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getUsersInRole")
    public JAXBElement<GetUsersInRole> createGetUsersInRole(GetUsersInRole value) {
        return new JAXBElement<GetUsersInRole>(_GetUsersInRole_QNAME, GetUsersInRole.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserRoles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getUserRoles")
    public JAXBElement<GetUserRoles> createGetUserRoles(GetUserRoles value) {
        return new JAXBElement<GetUserRoles>(_GetUserRoles_QNAME, GetUserRoles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAttributeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "addAttributeResponse")
    public JAXBElement<AddAttributeResponse> createAddAttributeResponse(AddAttributeResponse value) {
        return new JAXBElement<AddAttributeResponse>(_AddAttributeResponse_QNAME, AddAttributeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUserFromRoleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "removeUserFromRoleResponse")
    public JAXBElement<RemoveUserFromRoleResponse> createRemoveUserFromRoleResponse(RemoveUserFromRoleResponse value) {
        return new JAXBElement<RemoveUserFromRoleResponse>(_RemoveUserFromRoleResponse_QNAME, RemoveUserFromRoleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsersInRoleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getUsersInRoleResponse")
    public JAXBElement<GetUsersInRoleResponse> createGetUsersInRoleResponse(GetUsersInRoleResponse value) {
        return new JAXBElement<GetUsersInRoleResponse>(_GetUsersInRoleResponse_QNAME, GetUsersInRoleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getRole")
    public JAXBElement<GetRole> createGetRole(GetRole value) {
        return new JAXBElement<GetRole>(_GetRole_QNAME, GetRole.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRoleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getRoleResponse")
    public JAXBElement<GetRoleResponse> createGetRoleResponse(GetRoleResponse value) {
        return new JAXBElement<GetRoleResponse>(_GetRoleResponse_QNAME, GetRoleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsUserInRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "isUserInRole")
    public JAXBElement<IsUserInRole> createIsUserInRole(IsUserInRole value) {
        return new JAXBElement<IsUserInRole>(_IsUserInRole_QNAME, IsUserInRole.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRolesInGroup }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getRolesInGroup")
    public JAXBElement<GetRolesInGroup> createGetRolesInGroup(GetRolesInGroup value) {
        return new JAXBElement<GetRolesInGroup>(_GetRolesInGroup_QNAME, GetRolesInGroup.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAttributeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "removeAttributeResponse")
    public JAXBElement<RemoveAttributeResponse> createRemoveAttributeResponse(RemoveAttributeResponse value) {
        return new JAXBElement<RemoveAttributeResponse>(_RemoveAttributeResponse_QNAME, RemoveAttributeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAllGroupsFromRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "removeAllGroupsFromRole")
    public JAXBElement<RemoveAllGroupsFromRole> createRemoveAllGroupsFromRole(RemoveAllGroupsFromRole value) {
        return new JAXBElement<RemoveAllGroupsFromRole>(_RemoveAllGroupsFromRole_QNAME, RemoveAllGroupsFromRole.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRoleWithDependants }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getRoleWithDependants")
    public JAXBElement<GetRoleWithDependants> createGetRoleWithDependants(GetRoleWithDependants value) {
        return new JAXBElement<GetRoleWithDependants>(_GetRoleWithDependants_QNAME, GetRoleWithDependants.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddRoleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "addRoleResponse")
    public JAXBElement<AddRoleResponse> createAddRoleResponse(AddRoleResponse value) {
        return new JAXBElement<AddRoleResponse>(_AddRoleResponse_QNAME, AddRoleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRolesInService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getRolesInService")
    public JAXBElement<GetRolesInService> createGetRolesInService(GetRolesInService value) {
        return new JAXBElement<GetRolesInService>(_GetRolesInService_QNAME, GetRolesInService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveGroupFromRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "removeGroupFromRole")
    public JAXBElement<RemoveGroupFromRole> createRemoveGroupFromRole(RemoveGroupFromRole value) {
        return new JAXBElement<RemoveGroupFromRole>(_RemoveGroupFromRole_QNAME, RemoveGroupFromRole.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsGroupInRoleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "isGroupInRoleResponse")
    public JAXBElement<IsGroupInRoleResponse> createIsGroupInRoleResponse(IsGroupInRoleResponse value) {
        return new JAXBElement<IsGroupInRoleResponse>(_IsGroupInRoleResponse_QNAME, IsGroupInRoleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAttribute }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "removeAttribute")
    public JAXBElement<RemoveAttribute> createRemoveAttribute(RemoveAttribute value) {
        return new JAXBElement<RemoveAttribute>(_RemoveAttribute_QNAME, RemoveAttribute.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAttribute }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "addAttribute")
    public JAXBElement<AddAttribute> createAddAttribute(AddAttribute value) {
        return new JAXBElement<AddAttribute>(_AddAttribute_QNAME, AddAttribute.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGroupsInRoleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getGroupsInRoleResponse")
    public JAXBElement<GetGroupsInRoleResponse> createGetGroupsInRoleResponse(GetGroupsInRoleResponse value) {
        return new JAXBElement<GetGroupsInRoleResponse>(_GetGroupsInRoleResponse_QNAME, GetGroupsInRoleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateAttribute }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "updateAttribute")
    public JAXBElement<UpdateAttribute> createUpdateAttribute(UpdateAttribute value) {
        return new JAXBElement<UpdateAttribute>(_UpdateAttribute_QNAME, UpdateAttribute.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserRolesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getUserRolesResponse")
    public JAXBElement<GetUserRolesResponse> createGetUserRolesResponse(GetUserRolesResponse value) {
        return new JAXBElement<GetUserRolesResponse>(_GetUserRolesResponse_QNAME, GetUserRolesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "addRole")
    public JAXBElement<AddRole> createAddRole(AddRole value) {
        return new JAXBElement<AddRole>(_AddRole_QNAME, AddRole.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAttributeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "getAttributeResponse")
    public JAXBElement<GetAttributeResponse> createGetAttributeResponse(GetAttributeResponse value) {
        return new JAXBElement<GetAttributeResponse>(_GetAttributeResponse_QNAME, GetAttributeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveGroupFromRoleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "removeGroupFromRoleResponse")
    public JAXBElement<RemoveGroupFromRoleResponse> createRemoveGroupFromRoleResponse(RemoveGroupFromRoleResponse value) {
        return new JAXBElement<RemoveGroupFromRoleResponse>(_RemoveGroupFromRoleResponse_QNAME, RemoveGroupFromRoleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddGroupToRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:idm.openiam.org/srvc/role/types", name = "addGroupToRole")
    public JAXBElement<AddGroupToRole> createAddGroupToRole(AddGroupToRole value) {
        return new JAXBElement<AddGroupToRole>(_AddGroupToRole_QNAME, AddGroupToRole.class, null, value);
    }

}
