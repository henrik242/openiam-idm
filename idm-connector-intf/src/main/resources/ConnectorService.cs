
using System.ServiceModel;
namespace WcfIdentityServiceLibrary;

[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
//[System.ServiceModel.ServiceContractAttribute(Namespace="http://www.openiam.org/service/connector", ConfigurationName="RemoteConnectorService")]
[System.ServiceModel.ServiceContractAttribute(Namespace="http://www.openiam.org/service/connector")]
public interface RemoteConnectorService
{
    
    // CODEGEN: Generating message contract since the operation add is neither RPC nor document wrapped.
    //[System.ServiceModel.OperationContractAttribute(Action="http://www.openiam.org/service/connector/RemoteConnectorService/add", ReplyAction="*")]
    [System.ServiceModel.OperationContract]
    [System.ServiceModel.XmlSerializerFormatAttribute()]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resetPasswordResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resetPassword))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(setPasswordResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(setPassword))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(modifyResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(suspendResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(suspend))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(deleteResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(lookupResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resumeResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resume))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(addResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(BaseObject))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(ExtensibleObject))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(BaseRequestType))]
    addResponse1 add(add1 request);
    
    // CODEGEN: Parameter 'return' requires additional schema information that cannot be captured using the parameter mode. The specific attribute is 'System.Xml.Serialization.XmlElementAttribute'.
    //[System.ServiceModel.OperationContractAttribute(Action="http://www.openiam.org/service/connector/RemoteConnectorService/resume", ReplyAction="*")]
    [System.ServiceModel.OperationContract]
    [System.ServiceModel.XmlSerializerFormatAttribute()]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resetPasswordResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resetPassword))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(setPasswordResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(setPassword))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(modifyResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(suspendResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(suspend))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(deleteResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(lookupResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resumeResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resume))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(addResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(BaseObject))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(ExtensibleObject))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(BaseRequestType))]
    [return: System.ServiceModel.MessageParameterAttribute(Name="return")]
    resumeResponse1 resume(resume1 request);
    
    // CODEGEN: Generating message contract since the operation lookup is neither RPC nor document wrapped.
    //[System.ServiceModel.OperationContractAttribute(Action="http://www.openiam.org/service/connector/RemoteConnectorService/lookup", ReplyAction="*")]
    [System.ServiceModel.OperationContract]
    [System.ServiceModel.XmlSerializerFormatAttribute()]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resetPasswordResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resetPassword))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(setPasswordResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(setPassword))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(modifyResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(suspendResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(suspend))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(deleteResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(lookupResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resumeResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resume))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(addResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(BaseObject))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(ExtensibleObject))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(BaseRequestType))]
    lookupResponse1 lookup(lookup1 request);
    
    // CODEGEN: Generating message contract since the operation delete is neither RPC nor document wrapped.
    //[System.ServiceModel.OperationContractAttribute(Action="http://www.openiam.org/service/connector/RemoteConnectorService/delete", ReplyAction="*")]
    [System.ServiceModel.OperationContract]
    [System.ServiceModel.XmlSerializerFormatAttribute()]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resetPasswordResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resetPassword))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(setPasswordResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(setPassword))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(modifyResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(suspendResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(suspend))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(deleteResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(lookupResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resumeResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resume))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(addResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(BaseObject))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(ExtensibleObject))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(BaseRequestType))]
    deleteResponse1 delete(delete1 request);
    
    // CODEGEN: Parameter 'return' requires additional schema information that cannot be captured using the parameter mode. The specific attribute is 'System.Xml.Serialization.XmlElementAttribute'.
    //[System.ServiceModel.OperationContractAttribute(Action="http://www.openiam.org/service/connector/RemoteConnectorService/suspend", ReplyAction="*")]
    [System.ServiceModel.OperationContract]
    [System.ServiceModel.XmlSerializerFormatAttribute()]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resetPasswordResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resetPassword))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(setPasswordResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(setPassword))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(modifyResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(suspendResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(suspend))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(deleteResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(lookupResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resumeResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resume))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(addResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(BaseObject))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(ExtensibleObject))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(BaseRequestType))]
    [return: System.ServiceModel.MessageParameterAttribute(Name="return")]
    suspendResponse1 suspend(suspend1 request);
    
    // CODEGEN: Generating message contract since the operation modify is neither RPC nor document wrapped.
    //[System.ServiceModel.OperationContractAttribute(Action="http://www.openiam.org/service/connector/RemoteConnectorService/modify", ReplyAction="*")]
    [System.ServiceModel.OperationContract]
    [System.ServiceModel.XmlSerializerFormatAttribute()]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resetPasswordResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resetPassword))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(setPasswordResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(setPassword))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(modifyResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(suspendResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(suspend))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(deleteResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(lookupResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resumeResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resume))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(addResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(BaseObject))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(ExtensibleObject))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(BaseRequestType))]
    modifyResponse1 modify(modify1 request);
    
    // CODEGEN: Parameter 'return' requires additional schema information that cannot be captured using the parameter mode. The specific attribute is 'System.Xml.Serialization.XmlElementAttribute'.
    //[System.ServiceModel.OperationContractAttribute(Action="http://www.openiam.org/service/connector/RemoteConnectorService/setPassword", ReplyAction="*")]
    [System.ServiceModel.OperationContract]
    [System.ServiceModel.XmlSerializerFormatAttribute()]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resetPasswordResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resetPassword))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(setPasswordResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(setPassword))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(modifyResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(suspendResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(suspend))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(deleteResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(lookupResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resumeResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resume))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(addResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(BaseObject))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(ExtensibleObject))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(BaseRequestType))]
    [return: System.ServiceModel.MessageParameterAttribute(Name="return")]
    setPasswordResponse1 setPassword(setPassword1 request);
    
    // CODEGEN: Parameter 'return' requires additional schema information that cannot be captured using the parameter mode. The specific attribute is 'System.Xml.Serialization.XmlElementAttribute'.
    //[System.ServiceModel.OperationContractAttribute(Action="http://www.openiam.org/service/connector/RemoteConnectorService/resetPassword", ReplyAction="*")]
    [System.ServiceModel.OperationContract]
    [System.ServiceModel.XmlSerializerFormatAttribute()]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resetPasswordResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resetPassword))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(setPasswordResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(setPassword))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(modifyResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(suspendResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(suspend))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(deleteResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(lookupResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resumeResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(resume))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(addResponse))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(BaseObject))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(ExtensibleObject))]
    [System.ServiceModel.ServiceKnownTypeAttribute(typeof(BaseRequestType))]
    [return: System.ServiceModel.MessageParameterAttribute(Name="return")]
    resetPasswordResponse1 resetPassword(resetPassword1 request);
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class add
{
    
    private UserRequest reqTypeField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public UserRequest reqType
    {
        get
        {
            return this.reqTypeField;
        }
        set
        {
            this.reqTypeField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class UserRequest : BaseRequestType
{
    
    private string userIdentityField;
    
    private string containerIDField;
    
    private string operationField;
    
    private ExtensibleUser userField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public string userIdentity
    {
        get
        {
            return this.userIdentityField;
        }
        set
        {
            this.userIdentityField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
    public string containerID
    {
        get
        {
            return this.containerIDField;
        }
        set
        {
            this.containerIDField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=2)]
    public string operation
    {
        get
        {
            return this.operationField;
        }
        set
        {
            this.operationField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=3)]
    public ExtensibleUser user
    {
        get
        {
            return this.userField;
        }
        set
        {
            this.userField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/types")]
public partial class ExtensibleUser : ExtensibleObject
{
    
    private ExtensibleAddress[] addressField;
    
    private ExtensiblePhone[] phoneField;
    
    private ExtensibleEmailAddress[] emailField;
    
    private ExtensibleGroup[] groupField;
    
    private ExtensibleRole[] roleField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("address", IsNullable=true, Order=0)]
    public ExtensibleAddress[] address
    {
        get
        {
            return this.addressField;
        }
        set
        {
            this.addressField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("phone", IsNullable=true, Order=1)]
    public ExtensiblePhone[] phone
    {
        get
        {
            return this.phoneField;
        }
        set
        {
            this.phoneField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("email", IsNullable=true, Order=2)]
    public ExtensibleEmailAddress[] email
    {
        get
        {
            return this.emailField;
        }
        set
        {
            this.emailField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("group", IsNullable=true, Order=3)]
    public ExtensibleGroup[] group
    {
        get
        {
            return this.groupField;
        }
        set
        {
            this.groupField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("role", IsNullable=true, Order=4)]
    public ExtensibleRole[] role
    {
        get
        {
            return this.roleField;
        }
        set
        {
            this.roleField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/types")]
public partial class ExtensibleAddress : ExtensibleObject
{
    
    private address addressField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public address address
    {
        get
        {
            return this.addressField;
        }
        set
        {
            this.addressField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="urn:idm.openiam.org/srvc/continfo/dto")]
public partial class address
{
    
    private bool isActiveField;
    
    private bool isActiveFieldSpecified;
    
    private string bldgNumberField;
    
    private string streetDirectionField;
    
    private string suiteField;
    
    private string address1Field;
    
    private string address2Field;
    
    private string address3Field;
    
    private string address4Field;
    
    private string address5Field;
    
    private string address6Field;
    
    private string address7Field;
    
    private string addressIdField;
    
    private string cityField;
    
    private string countryField;
    
    private string descriptionField;
    
    private int isDefaultField;
    
    private bool isDefaultFieldSpecified;
    
    private string parentIdField;
    
    private string parentTypeField;
    
    private string postalCdField;
    
    private string stateField;
    
    private string nameField;
    
    private AttributeOperationEnum operationField;
    
    private bool operationFieldSpecified;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public bool isActive
    {
        get
        {
            return this.isActiveField;
        }
        set
        {
            this.isActiveField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool isActiveSpecified
    {
        get
        {
            return this.isActiveFieldSpecified;
        }
        set
        {
            this.isActiveFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=1)]
    public string bldgNumber
    {
        get
        {
            return this.bldgNumberField;
        }
        set
        {
            this.bldgNumberField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=2)]
    public string streetDirection
    {
        get
        {
            return this.streetDirectionField;
        }
        set
        {
            this.streetDirectionField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=3)]
    public string suite
    {
        get
        {
            return this.suiteField;
        }
        set
        {
            this.suiteField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=4)]
    public string address1
    {
        get
        {
            return this.address1Field;
        }
        set
        {
            this.address1Field = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=5)]
    public string address2
    {
        get
        {
            return this.address2Field;
        }
        set
        {
            this.address2Field = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=6)]
    public string address3
    {
        get
        {
            return this.address3Field;
        }
        set
        {
            this.address3Field = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=7)]
    public string address4
    {
        get
        {
            return this.address4Field;
        }
        set
        {
            this.address4Field = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=8)]
    public string address5
    {
        get
        {
            return this.address5Field;
        }
        set
        {
            this.address5Field = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=9)]
    public string address6
    {
        get
        {
            return this.address6Field;
        }
        set
        {
            this.address6Field = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=10)]
    public string address7
    {
        get
        {
            return this.address7Field;
        }
        set
        {
            this.address7Field = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=11)]
    public string addressId
    {
        get
        {
            return this.addressIdField;
        }
        set
        {
            this.addressIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=12)]
    public string city
    {
        get
        {
            return this.cityField;
        }
        set
        {
            this.cityField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=13)]
    public string country
    {
        get
        {
            return this.countryField;
        }
        set
        {
            this.countryField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=14)]
    public string description
    {
        get
        {
            return this.descriptionField;
        }
        set
        {
            this.descriptionField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=15)]
    public int isDefault
    {
        get
        {
            return this.isDefaultField;
        }
        set
        {
            this.isDefaultField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool isDefaultSpecified
    {
        get
        {
            return this.isDefaultFieldSpecified;
        }
        set
        {
            this.isDefaultFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=16)]
    public string parentId
    {
        get
        {
            return this.parentIdField;
        }
        set
        {
            this.parentIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=17)]
    public string parentType
    {
        get
        {
            return this.parentTypeField;
        }
        set
        {
            this.parentTypeField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=18)]
    public string postalCd
    {
        get
        {
            return this.postalCdField;
        }
        set
        {
            this.postalCdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=19)]
    public string state
    {
        get
        {
            return this.stateField;
        }
        set
        {
            this.stateField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=20)]
    public string name
    {
        get
        {
            return this.nameField;
        }
        set
        {
            this.nameField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=21)]
    public AttributeOperationEnum operation
    {
        get
        {
            return this.operationField;
        }
        set
        {
            this.operationField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool operationSpecified
    {
        get
        {
            return this.operationFieldSpecified;
        }
        set
        {
            this.operationFieldSpecified = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public enum AttributeOperationEnum
{
    
    /// <remarks/>
    nochange,
    
    /// <remarks/>
    add,
    
    /// <remarks/>
    replace,
    
    /// <remarks/>
    delete,
}

/// <remarks/>
[System.Xml.Serialization.XmlIncludeAttribute(typeof(CapabilityType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(CapabilitiesListType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(SchemaEntityRefType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(SchemaType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(TargetType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(PSOType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(ResponseType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(LookupResponseType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(ModifyResponseType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(ListTargetsResponseType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(AddResponseType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(NamespacePrefixMappingType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(QueryClauseType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(SelectionType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(ModificationType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(CapabilityDataType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(IdentifierType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(PSOIdentifierType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(RequestType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(ModifyRequestType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(DeleteRequestType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(ListTargetsRequestType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(LookupRequestType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(AddRequestType))]
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class ExtensibleType
{
    
    private System.Xml.XmlElement[] anyField;
    
    private System.Xml.XmlAttribute[] anyAttrField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyElementAttribute(Order=0)]
    public System.Xml.XmlElement[] Any
    {
        get
        {
            return this.anyField;
        }
        set
        {
            this.anyField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr
    {
        get
        {
            return this.anyAttrField;
        }
        set
        {
            this.anyAttrField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class CapabilityType : ExtensibleType
{
    
    private SchemaEntityRefType[] appliesToField;
    
    private string namespaceURIField;
    
    private string locationField;
    
    private System.Xml.XmlAttribute[] anyAttr1Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("appliesTo", IsNullable=true, Order=0)]
    public SchemaEntityRefType[] appliesTo
    {
        get
        {
            return this.appliesToField;
        }
        set
        {
            this.appliesToField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute(DataType="anyURI")]
    public string namespaceURI
    {
        get
        {
            return this.namespaceURIField;
        }
        set
        {
            this.namespaceURIField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute(DataType="anyURI")]
    public string location
    {
        get
        {
            return this.locationField;
        }
        set
        {
            this.locationField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr1
    {
        get
        {
            return this.anyAttr1Field;
        }
        set
        {
            this.anyAttr1Field = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class SchemaEntityRefType : ExtensibleType
{
    
    private string targetIDField;
    
    private string entityNameField;
    
    private bool isContainerField;
    
    private bool isContainerFieldSpecified;
    
    private System.Xml.XmlAttribute[] anyAttr1Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public string targetID
    {
        get
        {
            return this.targetIDField;
        }
        set
        {
            this.targetIDField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public string entityName
    {
        get
        {
            return this.entityNameField;
        }
        set
        {
            this.entityNameField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public bool isContainer
    {
        get
        {
            return this.isContainerField;
        }
        set
        {
            this.isContainerField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool isContainerSpecified
    {
        get
        {
            return this.isContainerFieldSpecified;
        }
        set
        {
            this.isContainerFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr1
    {
        get
        {
            return this.anyAttr1Field;
        }
        set
        {
            this.anyAttr1Field = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class CapabilitiesListType : ExtensibleType
{
    
    private CapabilityType[] capabilityField;
    
    private System.Xml.XmlAttribute[] anyAttr1Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("capability", IsNullable=true, Order=0)]
    public CapabilityType[] capability
    {
        get
        {
            return this.capabilityField;
        }
        set
        {
            this.capabilityField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr1
    {
        get
        {
            return this.anyAttr1Field;
        }
        set
        {
            this.anyAttr1Field = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class SchemaType : ExtensibleType
{
    
    private SchemaEntityRefType[] supportedSchemaEntityField;
    
    private string refField;
    
    private System.Xml.XmlAttribute[] anyAttr1Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("supportedSchemaEntity", IsNullable=true, Order=0)]
    public SchemaEntityRefType[] supportedSchemaEntity
    {
        get
        {
            return this.supportedSchemaEntityField;
        }
        set
        {
            this.supportedSchemaEntityField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute(DataType="anyURI")]
    public string @ref
    {
        get
        {
            return this.refField;
        }
        set
        {
            this.refField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr1
    {
        get
        {
            return this.anyAttr1Field;
        }
        set
        {
            this.anyAttr1Field = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class TargetType : ExtensibleType
{
    
    private SchemaType[] schemaField;
    
    private CapabilitiesListType capabilitiesField;
    
    private string targetIDField;
    
    private string profileField;
    
    private System.Xml.XmlAttribute[] anyAttr1Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("schema", Order=0)]
    public SchemaType[] schema
    {
        get
        {
            return this.schemaField;
        }
        set
        {
            this.schemaField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=1)]
    public CapabilitiesListType capabilities
    {
        get
        {
            return this.capabilitiesField;
        }
        set
        {
            this.capabilitiesField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public string targetID
    {
        get
        {
            return this.targetIDField;
        }
        set
        {
            this.targetIDField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute(DataType="anyURI")]
    public string profile
    {
        get
        {
            return this.profileField;
        }
        set
        {
            this.profileField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr1
    {
        get
        {
            return this.anyAttr1Field;
        }
        set
        {
            this.anyAttr1Field = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class PSOType : ExtensibleType
{
    
    private PSOIdentifierType psoIDField;
    
    private ExtensibleType dataField;
    
    private CapabilityDataType[] capabilityDataField;
    
    private System.Xml.XmlAttribute[] anyAttr1Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public PSOIdentifierType psoID
    {
        get
        {
            return this.psoIDField;
        }
        set
        {
            this.psoIDField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=1)]
    public ExtensibleType data
    {
        get
        {
            return this.dataField;
        }
        set
        {
            this.dataField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("capabilityData", IsNullable=true, Order=2)]
    public CapabilityDataType[] capabilityData
    {
        get
        {
            return this.capabilityDataField;
        }
        set
        {
            this.capabilityDataField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr1
    {
        get
        {
            return this.anyAttr1Field;
        }
        set
        {
            this.anyAttr1Field = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class PSOIdentifierType : IdentifierType
{
    
    private PSOIdentifierType containerIDField;
    
    private string targetIDField;
    
    private System.Xml.XmlAttribute[] anyAttr2Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public PSOIdentifierType containerID
    {
        get
        {
            return this.containerIDField;
        }
        set
        {
            this.containerIDField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public string targetID
    {
        get
        {
            return this.targetIDField;
        }
        set
        {
            this.targetIDField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr2
    {
        get
        {
            return this.anyAttr2Field;
        }
        set
        {
            this.anyAttr2Field = value;
        }
    }
}

/// <remarks/>
[System.Xml.Serialization.XmlIncludeAttribute(typeof(PSOIdentifierType))]
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class IdentifierType : ExtensibleType
{
    
    private string idField;
    
    private System.Xml.XmlAttribute[] anyAttr1Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public string ID
    {
        get
        {
            return this.idField;
        }
        set
        {
            this.idField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr1
    {
        get
        {
            return this.anyAttr1Field;
        }
        set
        {
            this.anyAttr1Field = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class CapabilityDataType : ExtensibleType
{
    
    private bool mustUnderstandField;
    
    private bool mustUnderstandFieldSpecified;
    
    private string capabilityURIField;
    
    private System.Xml.XmlAttribute[] anyAttr1Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public bool mustUnderstand
    {
        get
        {
            return this.mustUnderstandField;
        }
        set
        {
            this.mustUnderstandField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool mustUnderstandSpecified
    {
        get
        {
            return this.mustUnderstandFieldSpecified;
        }
        set
        {
            this.mustUnderstandFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute(DataType="anyURI")]
    public string capabilityURI
    {
        get
        {
            return this.capabilityURIField;
        }
        set
        {
            this.capabilityURIField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr1
    {
        get
        {
            return this.anyAttr1Field;
        }
        set
        {
            this.anyAttr1Field = value;
        }
    }
}

/// <remarks/>
[System.Xml.Serialization.XmlIncludeAttribute(typeof(LookupResponseType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(ModifyResponseType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(ListTargetsResponseType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(AddResponseType))]
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class ResponseType : ExtensibleType
{
    
    private string[] errorMessageField;
    
    private StatusCodeType statusField;
    
    private string requestIDField;
    
    private ErrorCode errorField;
    
    private bool errorFieldSpecified;
    
    private System.Xml.XmlAttribute[] anyAttr1Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("errorMessage", IsNullable=true, Order=0)]
    public string[] errorMessage
    {
        get
        {
            return this.errorMessageField;
        }
        set
        {
            this.errorMessageField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public StatusCodeType status
    {
        get
        {
            return this.statusField;
        }
        set
        {
            this.statusField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute(DataType="ID")]
    public string requestID
    {
        get
        {
            return this.requestIDField;
        }
        set
        {
            this.requestIDField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public ErrorCode error
    {
        get
        {
            return this.errorField;
        }
        set
        {
            this.errorField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool errorSpecified
    {
        get
        {
            return this.errorFieldSpecified;
        }
        set
        {
            this.errorFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr1
    {
        get
        {
            return this.anyAttr1Field;
        }
        set
        {
            this.anyAttr1Field = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public enum StatusCodeType
{
    
    /// <remarks/>
    success,
    
    /// <remarks/>
    failure,
    
    /// <remarks/>
    pending,
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public enum ErrorCode
{
    
    /// <remarks/>
    malformedRequest,
    
    /// <remarks/>
    unsupportedOperation,
    
    /// <remarks/>
    unsupportedIdentifierType,
    
    /// <remarks/>
    noSuchIdentifier,
    
    /// <remarks/>
    customError,
    
    /// <remarks/>
    unsupportedExecutionMode,
    
    /// <remarks/>
    invalidContainment,
    
    /// <remarks/>
    noSuchRequest,
    
    /// <remarks/>
    unsupportedSelectionType,
    
    /// <remarks/>
    resultSetToLarge,
    
    /// <remarks/>
    unsupportedProfile,
    
    /// <remarks/>
    invalidIdentifier,
    
    /// <remarks/>
    alreadyExists,
    
    /// <remarks/>
    invalidManagedSysId,
    
    /// <remarks/>
    userLimitReached,
    
    /// <remarks/>
    containerNotEmpty,
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class LookupResponseType : ResponseType
{
    
    private PSOType psoField;
    
    private System.Xml.XmlAttribute[] anyAttr2Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public PSOType pso
    {
        get
        {
            return this.psoField;
        }
        set
        {
            this.psoField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr2
    {
        get
        {
            return this.anyAttr2Field;
        }
        set
        {
            this.anyAttr2Field = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class ModifyResponseType : ResponseType
{
    
    private PSOType psoField;
    
    private System.Xml.XmlAttribute[] anyAttr2Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public PSOType pso
    {
        get
        {
            return this.psoField;
        }
        set
        {
            this.psoField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr2
    {
        get
        {
            return this.anyAttr2Field;
        }
        set
        {
            this.anyAttr2Field = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class ListTargetsResponseType : ResponseType
{
    
    private TargetType[] targetField;
    
    private System.Xml.XmlAttribute[] anyAttr2Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("target", IsNullable=true, Order=0)]
    public TargetType[] target
    {
        get
        {
            return this.targetField;
        }
        set
        {
            this.targetField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr2
    {
        get
        {
            return this.anyAttr2Field;
        }
        set
        {
            this.anyAttr2Field = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class AddResponseType : ResponseType
{
    
    private PSOType psoField;
    
    private System.Xml.XmlAttribute[] anyAttr2Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public PSOType pso
    {
        get
        {
            return this.psoField;
        }
        set
        {
            this.psoField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr2
    {
        get
        {
            return this.anyAttr2Field;
        }
        set
        {
            this.anyAttr2Field = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class NamespacePrefixMappingType : ExtensibleType
{
    
    private string prefixField;
    
    private string namespaceField;
    
    private System.Xml.XmlAttribute[] anyAttr1Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public string prefix
    {
        get
        {
            return this.prefixField;
        }
        set
        {
            this.prefixField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public string @namespace
    {
        get
        {
            return this.namespaceField;
        }
        set
        {
            this.namespaceField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr1
    {
        get
        {
            return this.anyAttr1Field;
        }
        set
        {
            this.anyAttr1Field = value;
        }
    }
}

/// <remarks/>
[System.Xml.Serialization.XmlIncludeAttribute(typeof(SelectionType))]
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class QueryClauseType : ExtensibleType
{
    
    private System.Xml.XmlAttribute[] anyAttr1Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr1
    {
        get
        {
            return this.anyAttr1Field;
        }
        set
        {
            this.anyAttr1Field = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class SelectionType : QueryClauseType
{
    
    private NamespacePrefixMappingType[] namespacePrefixMapField;
    
    private string pathField;
    
    private string namespaceURIField;
    
    private System.Xml.XmlAttribute[] anyAttr2Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("namespacePrefixMap", IsNullable=true, Order=0)]
    public NamespacePrefixMappingType[] namespacePrefixMap
    {
        get
        {
            return this.namespacePrefixMapField;
        }
        set
        {
            this.namespacePrefixMapField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public string path
    {
        get
        {
            return this.pathField;
        }
        set
        {
            this.pathField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public string namespaceURI
    {
        get
        {
            return this.namespaceURIField;
        }
        set
        {
            this.namespaceURIField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr2
    {
        get
        {
            return this.anyAttr2Field;
        }
        set
        {
            this.anyAttr2Field = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class ModificationType : ExtensibleType
{
    
    private SelectionType componentField;
    
    private ExtensibleType dataField;
    
    private CapabilityDataType[] capabilityDataField;
    
    private ModificationModeType modificationModeField;
    
    private bool modificationModeFieldSpecified;
    
    private System.Xml.XmlAttribute[] anyAttr1Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public SelectionType component
    {
        get
        {
            return this.componentField;
        }
        set
        {
            this.componentField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=1)]
    public ExtensibleType data
    {
        get
        {
            return this.dataField;
        }
        set
        {
            this.dataField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("capabilityData", IsNullable=true, Order=2)]
    public CapabilityDataType[] capabilityData
    {
        get
        {
            return this.capabilityDataField;
        }
        set
        {
            this.capabilityDataField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public ModificationModeType modificationMode
    {
        get
        {
            return this.modificationModeField;
        }
        set
        {
            this.modificationModeField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool modificationModeSpecified
    {
        get
        {
            return this.modificationModeFieldSpecified;
        }
        set
        {
            this.modificationModeFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr1
    {
        get
        {
            return this.anyAttr1Field;
        }
        set
        {
            this.anyAttr1Field = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public enum ModificationModeType
{
    
    /// <remarks/>
    add,
    
    /// <remarks/>
    replace,
    
    /// <remarks/>
    delete,
}

/// <remarks/>
[System.Xml.Serialization.XmlIncludeAttribute(typeof(ModifyRequestType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(DeleteRequestType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(ListTargetsRequestType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(LookupRequestType))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(AddRequestType))]
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class RequestType : ExtensibleType
{
    
    private string requestIDField;
    
    private ExecutionModeType executionModeField;
    
    private bool executionModeFieldSpecified;
    
    private System.Xml.XmlAttribute[] anyAttr1Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute(DataType="ID")]
    public string requestID
    {
        get
        {
            return this.requestIDField;
        }
        set
        {
            this.requestIDField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public ExecutionModeType executionMode
    {
        get
        {
            return this.executionModeField;
        }
        set
        {
            this.executionModeField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool executionModeSpecified
    {
        get
        {
            return this.executionModeFieldSpecified;
        }
        set
        {
            this.executionModeFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr1
    {
        get
        {
            return this.anyAttr1Field;
        }
        set
        {
            this.anyAttr1Field = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public enum ExecutionModeType
{
    
    /// <remarks/>
    synchronous,
    
    /// <remarks/>
    asynchronous,
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class ModifyRequestType : RequestType
{
    
    private PSOIdentifierType psoIDField;
    
    private ModificationType[] modificationField;
    
    private ReturnDataType returnDataField;
    
    private bool returnDataFieldSpecified;
    
    private System.Xml.XmlAttribute[] anyAttr2Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public PSOIdentifierType psoID
    {
        get
        {
            return this.psoIDField;
        }
        set
        {
            this.psoIDField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("modification", Order=1)]
    public ModificationType[] modification
    {
        get
        {
            return this.modificationField;
        }
        set
        {
            this.modificationField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public ReturnDataType returnData
    {
        get
        {
            return this.returnDataField;
        }
        set
        {
            this.returnDataField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool returnDataSpecified
    {
        get
        {
            return this.returnDataFieldSpecified;
        }
        set
        {
            this.returnDataFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr2
    {
        get
        {
            return this.anyAttr2Field;
        }
        set
        {
            this.anyAttr2Field = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public enum ReturnDataType
{
    
    /// <remarks/>
    identifier,
    
    /// <remarks/>
    data,
    
    /// <remarks/>
    everything,
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class DeleteRequestType : RequestType
{
    
    private PSOIdentifierType psoIDField;
    
    private bool recursiveField;
    
    private bool recursiveFieldSpecified;
    
    private System.Xml.XmlAttribute[] anyAttr2Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public PSOIdentifierType psoID
    {
        get
        {
            return this.psoIDField;
        }
        set
        {
            this.psoIDField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public bool recursive
    {
        get
        {
            return this.recursiveField;
        }
        set
        {
            this.recursiveField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool recursiveSpecified
    {
        get
        {
            return this.recursiveFieldSpecified;
        }
        set
        {
            this.recursiveFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr2
    {
        get
        {
            return this.anyAttr2Field;
        }
        set
        {
            this.anyAttr2Field = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class ListTargetsRequestType : RequestType
{
    
    private string profileField;
    
    private System.Xml.XmlAttribute[] anyAttr2Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute(DataType="anyURI")]
    public string profile
    {
        get
        {
            return this.profileField;
        }
        set
        {
            this.profileField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr2
    {
        get
        {
            return this.anyAttr2Field;
        }
        set
        {
            this.anyAttr2Field = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class LookupRequestType : RequestType
{
    
    private PSOIdentifierType psoIDField;
    
    private ReturnDataType returnDataField;
    
    private bool returnDataFieldSpecified;
    
    private System.Xml.XmlAttribute[] anyAttr2Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public PSOIdentifierType psoID
    {
        get
        {
            return this.psoIDField;
        }
        set
        {
            this.psoIDField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public ReturnDataType returnData
    {
        get
        {
            return this.returnDataField;
        }
        set
        {
            this.returnDataField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool returnDataSpecified
    {
        get
        {
            return this.returnDataFieldSpecified;
        }
        set
        {
            this.returnDataFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr2
    {
        get
        {
            return this.anyAttr2Field;
        }
        set
        {
            this.anyAttr2Field = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class AddRequestType : RequestType
{
    
    private PSOIdentifierType psoIDField;
    
    private PSOIdentifierType containerIDField;
    
    private ExtensibleType dataField;
    
    private CapabilityDataType[] capabilityDataField;
    
    private string targetIDField;
    
    private ReturnDataType returnDataField;
    
    private bool returnDataFieldSpecified;
    
    private System.Xml.XmlAttribute[] anyAttr2Field;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public PSOIdentifierType psoID
    {
        get
        {
            return this.psoIDField;
        }
        set
        {
            this.psoIDField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=1)]
    public PSOIdentifierType containerID
    {
        get
        {
            return this.containerIDField;
        }
        set
        {
            this.containerIDField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=2)]
    public ExtensibleType data
    {
        get
        {
            return this.dataField;
        }
        set
        {
            this.dataField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("capabilityData", IsNullable=true, Order=3)]
    public CapabilityDataType[] capabilityData
    {
        get
        {
            return this.capabilityDataField;
        }
        set
        {
            this.capabilityDataField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public string targetID
    {
        get
        {
            return this.targetIDField;
        }
        set
        {
            this.targetIDField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public ReturnDataType returnData
    {
        get
        {
            return this.returnDataField;
        }
        set
        {
            this.returnDataField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool returnDataSpecified
    {
        get
        {
            return this.returnDataFieldSpecified;
        }
        set
        {
            this.returnDataFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAnyAttributeAttribute()]
    public System.Xml.XmlAttribute[] AnyAttr2
    {
        get
        {
            return this.anyAttr2Field;
        }
        set
        {
            this.anyAttr2Field = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class resetPasswordResponse
{
    
    private BaseResponseType returnField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public BaseResponseType @return
    {
        get
        {
            return this.returnField;
        }
        set
        {
            this.returnField = value;
        }
    }
}

/// <remarks/>
[System.Xml.Serialization.XmlIncludeAttribute(typeof(LookupResponse))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(UserResponse))]
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class BaseResponseType
{
    
    private string[] errorMessageField;
    
    private string requestIDField;
    
    private ErrorCode errorField;
    
    private bool errorFieldSpecified;
    
    private StatusCodeType statusField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("errorMessage", Form=System.Xml.Schema.XmlSchemaForm.Unqualified, IsNullable=true, Order=0)]
    public string[] errorMessage
    {
        get
        {
            return this.errorMessageField;
        }
        set
        {
            this.errorMessageField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
    public string requestID
    {
        get
        {
            return this.requestIDField;
        }
        set
        {
            this.requestIDField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=2)]
    public ErrorCode error
    {
        get
        {
            return this.errorField;
        }
        set
        {
            this.errorField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool errorSpecified
    {
        get
        {
            return this.errorFieldSpecified;
        }
        set
        {
            this.errorFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public StatusCodeType status
    {
        get
        {
            return this.statusField;
        }
        set
        {
            this.statusField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class LookupResponse : BaseResponseType
{
    
    private string valueField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public string value
    {
        get
        {
            return this.valueField;
        }
        set
        {
            this.valueField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class UserResponse : BaseResponseType
{
    
    private string userIdentityField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public string userIdentity
    {
        get
        {
            return this.userIdentityField;
        }
        set
        {
            this.userIdentityField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class resetPassword
{
    
    private PasswordRequest requestField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public PasswordRequest request
    {
        get
        {
            return this.requestField;
        }
        set
        {
            this.requestField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class PasswordRequest : BaseRequestType
{
    
    private string userIdentityField;
    
    private string operationField;
    
    private string passwordField;
    
    private string currentPasswordField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public string userIdentity
    {
        get
        {
            return this.userIdentityField;
        }
        set
        {
            this.userIdentityField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
    public string operation
    {
        get
        {
            return this.operationField;
        }
        set
        {
            this.operationField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=2)]
    public string password
    {
        get
        {
            return this.passwordField;
        }
        set
        {
            this.passwordField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=3)]
    public string currentPassword
    {
        get
        {
            return this.currentPasswordField;
        }
        set
        {
            this.currentPasswordField = value;
        }
    }
}

/// <remarks/>
[System.Xml.Serialization.XmlIncludeAttribute(typeof(PasswordRequest))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(SuspendRequest))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(LookupRequest))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(ResumeRequest))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(UserRequest))]
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class BaseRequestType
{
    
    private string requestIDField;
    
    private string executionModeField;
    
    private string targetIDField;
    
    private string hostUrlField;
    
    private string hostPortField;
    
    private string hostLoginIdField;
    
    private string hostLoginPasswordField;
    
    private string baseDNField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public string requestID
    {
        get
        {
            return this.requestIDField;
        }
        set
        {
            this.requestIDField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
    public string executionMode
    {
        get
        {
            return this.executionModeField;
        }
        set
        {
            this.executionModeField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=2)]
    public string targetID
    {
        get
        {
            return this.targetIDField;
        }
        set
        {
            this.targetIDField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=3)]
    public string hostUrl
    {
        get
        {
            return this.hostUrlField;
        }
        set
        {
            this.hostUrlField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=4)]
    public string hostPort
    {
        get
        {
            return this.hostPortField;
        }
        set
        {
            this.hostPortField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=5)]
    public string hostLoginId
    {
        get
        {
            return this.hostLoginIdField;
        }
        set
        {
            this.hostLoginIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=6)]
    public string hostLoginPassword
    {
        get
        {
            return this.hostLoginPasswordField;
        }
        set
        {
            this.hostLoginPasswordField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=7)]
    public string baseDN
    {
        get
        {
            return this.baseDNField;
        }
        set
        {
            this.baseDNField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class SuspendRequest : BaseRequestType
{
    
    private string userIdentityField;
    
    private System.DateTime effectiveDateField;
    
    private bool effectiveDateFieldSpecified;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public string userIdentity
    {
        get
        {
            return this.userIdentityField;
        }
        set
        {
            this.userIdentityField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
    public System.DateTime effectiveDate
    {
        get
        {
            return this.effectiveDateField;
        }
        set
        {
            this.effectiveDateField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool effectiveDateSpecified
    {
        get
        {
            return this.effectiveDateFieldSpecified;
        }
        set
        {
            this.effectiveDateFieldSpecified = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class LookupRequest : BaseRequestType
{
    
    private string searchValueField;
    
    private string searchQueryField;
    
    private ReturnData returnDataField;
    
    private bool returnDataFieldSpecified;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public string searchValue
    {
        get
        {
            return this.searchValueField;
        }
        set
        {
            this.searchValueField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
    public string searchQuery
    {
        get
        {
            return this.searchQueryField;
        }
        set
        {
            this.searchQueryField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=2)]
    public ReturnData returnData
    {
        get
        {
            return this.returnDataField;
        }
        set
        {
            this.returnDataField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool returnDataSpecified
    {
        get
        {
            return this.returnDataFieldSpecified;
        }
        set
        {
            this.returnDataFieldSpecified = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public enum ReturnData
{
    
    /// <remarks/>
    identifier,
    
    /// <remarks/>
    data,
    
    /// <remarks/>
    everything,
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class ResumeRequest : BaseRequestType
{
    
    private string userIdentityField;
    
    private System.DateTime effectiveDateField;
    
    private bool effectiveDateFieldSpecified;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public string userIdentity
    {
        get
        {
            return this.userIdentityField;
        }
        set
        {
            this.userIdentityField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
    public System.DateTime effectiveDate
    {
        get
        {
            return this.effectiveDateField;
        }
        set
        {
            this.effectiveDateField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool effectiveDateSpecified
    {
        get
        {
            return this.effectiveDateFieldSpecified;
        }
        set
        {
            this.effectiveDateFieldSpecified = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class setPasswordResponse
{
    
    private BaseResponseType returnField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public BaseResponseType @return
    {
        get
        {
            return this.returnField;
        }
        set
        {
            this.returnField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class setPassword
{
    
    private PasswordRequest requestField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public PasswordRequest request
    {
        get
        {
            return this.requestField;
        }
        set
        {
            this.requestField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class modifyResponse
{
    
    private UserResponse returnField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public UserResponse @return
    {
        get
        {
            return this.returnField;
        }
        set
        {
            this.returnField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class modify
{
    
    private UserRequest reqTypeField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public UserRequest reqType
    {
        get
        {
            return this.reqTypeField;
        }
        set
        {
            this.reqTypeField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class suspendResponse
{
    
    private BaseResponseType returnField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public BaseResponseType @return
    {
        get
        {
            return this.returnField;
        }
        set
        {
            this.returnField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class suspend
{
    
    private SuspendRequest requestField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public SuspendRequest request
    {
        get
        {
            return this.requestField;
        }
        set
        {
            this.requestField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class deleteResponse
{
    
    private UserResponse returnField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public UserResponse @return
    {
        get
        {
            return this.returnField;
        }
        set
        {
            this.returnField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class delete
{
    
    private UserRequest reqTypeField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public UserRequest reqType
    {
        get
        {
            return this.reqTypeField;
        }
        set
        {
            this.reqTypeField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class lookupResponse
{
    
    private LookupResponse returnField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public LookupResponse @return
    {
        get
        {
            return this.returnField;
        }
        set
        {
            this.returnField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class lookup
{
    
    private LookupRequest reqTypeField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public LookupRequest reqType
    {
        get
        {
            return this.reqTypeField;
        }
        set
        {
            this.reqTypeField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class resumeResponse
{
    
    private BaseResponseType returnField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public BaseResponseType @return
    {
        get
        {
            return this.returnField;
        }
        set
        {
            this.returnField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class resume
{
    
    private ResumeRequest requestField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public ResumeRequest request
    {
        get
        {
            return this.requestField;
        }
        set
        {
            this.requestField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class addResponse
{
    
    private UserResponse returnField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public UserResponse @return
    {
        get
        {
            return this.returnField;
        }
        set
        {
            this.returnField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="urn:idm.openiam.org/srvc/grp/dto")]
public partial class GroupAttribute
{
    
    private string idField;
    
    private string nameField;
    
    private string valueField;
    
    private string metadataElementIdField;
    
    private string groupIdField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public string id
    {
        get
        {
            return this.idField;
        }
        set
        {
            this.idField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=1)]
    public string name
    {
        get
        {
            return this.nameField;
        }
        set
        {
            this.nameField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=2)]
    public string value
    {
        get
        {
            return this.valueField;
        }
        set
        {
            this.valueField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=3)]
    public string metadataElementId
    {
        get
        {
            return this.metadataElementIdField;
        }
        set
        {
            this.metadataElementIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=4)]
    public string groupId
    {
        get
        {
            return this.groupIdField;
        }
        set
        {
            this.groupIdField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="urn:idm.openiam.org/srvc/role/dto")]
public partial class roleAttribute
{
    
    private string roleAttrIdField;
    
    private string serviceIdField;
    
    private string roleIdField;
    
    private string metadataElementIdField;
    
    private string nameField;
    
    private string valueField;
    
    private string attrGroupField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public string roleAttrId
    {
        get
        {
            return this.roleAttrIdField;
        }
        set
        {
            this.roleAttrIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=1)]
    public string serviceId
    {
        get
        {
            return this.serviceIdField;
        }
        set
        {
            this.serviceIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=2)]
    public string roleId
    {
        get
        {
            return this.roleIdField;
        }
        set
        {
            this.roleIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=3)]
    public string metadataElementId
    {
        get
        {
            return this.metadataElementIdField;
        }
        set
        {
            this.metadataElementIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=4)]
    public string name
    {
        get
        {
            return this.nameField;
        }
        set
        {
            this.nameField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=5)]
    public string value
    {
        get
        {
            return this.valueField;
        }
        set
        {
            this.valueField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=6)]
    public string attrGroup
    {
        get
        {
            return this.attrGroupField;
        }
        set
        {
            this.attrGroupField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="urn:idm.openiam.org/srvc/role/dto")]
public partial class roleId
{
    
    private string roleId1Field;
    
    private string serviceIdField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("roleId", Order=0)]
    public string roleId1
    {
        get
        {
            return this.roleId1Field;
        }
        set
        {
            this.roleId1Field = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=1)]
    public string serviceId
    {
        get
        {
            return this.serviceIdField;
        }
        set
        {
            this.serviceIdField = value;
        }
    }
}

/// <remarks/>
[System.Xml.Serialization.XmlIncludeAttribute(typeof(RolePolicy))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(role))]
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/connector")]
public partial class BaseObject
{
    
    private string objectStateField;
    
    private bool selectedField;
    
    private bool selectedFieldSpecified;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
    public string objectState
    {
        get
        {
            return this.objectStateField;
        }
        set
        {
            this.objectStateField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
    public bool selected
    {
        get
        {
            return this.selectedField;
        }
        set
        {
            this.selectedField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool selectedSpecified
    {
        get
        {
            return this.selectedFieldSpecified;
        }
        set
        {
            this.selectedFieldSpecified = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="urn:idm.openiam.org/srvc/role/dto")]
public partial class RolePolicy : BaseObject
{
    
    private string rolePolicyIdField;
    
    private string serviceIdField;
    
    private string roleIdField;
    
    private string nameField;
    
    private string value1Field;
    
    private string value2Field;
    
    private string actionField;
    
    private int executionOrderField;
    
    private bool executionOrderFieldSpecified;
    
    private string policyScriptField;
    
    private string actionQualifierField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public string rolePolicyId
    {
        get
        {
            return this.rolePolicyIdField;
        }
        set
        {
            this.rolePolicyIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=1)]
    public string serviceId
    {
        get
        {
            return this.serviceIdField;
        }
        set
        {
            this.serviceIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=2)]
    public string roleId
    {
        get
        {
            return this.roleIdField;
        }
        set
        {
            this.roleIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=3)]
    public string name
    {
        get
        {
            return this.nameField;
        }
        set
        {
            this.nameField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=4)]
    public string value1
    {
        get
        {
            return this.value1Field;
        }
        set
        {
            this.value1Field = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=5)]
    public string value2
    {
        get
        {
            return this.value2Field;
        }
        set
        {
            this.value2Field = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=6)]
    public string action
    {
        get
        {
            return this.actionField;
        }
        set
        {
            this.actionField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=7)]
    public int executionOrder
    {
        get
        {
            return this.executionOrderField;
        }
        set
        {
            this.executionOrderField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool executionOrderSpecified
    {
        get
        {
            return this.executionOrderFieldSpecified;
        }
        set
        {
            this.executionOrderFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=8)]
    public string policyScript
    {
        get
        {
            return this.policyScriptField;
        }
        set
        {
            this.policyScriptField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=9)]
    public string actionQualifier
    {
        get
        {
            return this.actionQualifierField;
        }
        set
        {
            this.actionQualifierField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="urn:idm.openiam.org/srvc/role/dto")]
public partial class role : BaseObject
{
    
    private System.DateTime createDateField;
    
    private bool createDateFieldSpecified;
    
    private string createdByField;
    
    private string descriptionField;
    
    private groupSetGroupObj[] groupsField;
    
    private roleId idField;
    
    private string provisionObjNameField;
    
    private string parentRoleIdField;
    
    private roleAttributeSetRoleAttributeObj[] roleAttributesField;
    
    private string roleNameField;
    
    private int userAssociationMethodField;
    
    private string metadataTypeIdField;
    
    private string ownerIdField;
    
    private int inheritFromParentField;
    
    private bool inheritFromParentFieldSpecified;
    
    private string statusField;
    
    private role[] childRolesField;
    
    private bool selected1Field;
    
    private bool selected1FieldSpecified;
    
    private string internalRoleIdField;
    
    private AttributeOperationEnum operationField;
    
    private bool operationFieldSpecified;
    
    private System.DateTime startDateField;
    
    private bool startDateFieldSpecified;
    
    private System.DateTime endDateField;
    
    private bool endDateFieldSpecified;
    
    private RolePolicy[] rolePolicyField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public System.DateTime createDate
    {
        get
        {
            return this.createDateField;
        }
        set
        {
            this.createDateField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool createDateSpecified
    {
        get
        {
            return this.createDateFieldSpecified;
        }
        set
        {
            this.createDateFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=1)]
    public string createdBy
    {
        get
        {
            return this.createdByField;
        }
        set
        {
            this.createdByField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=2)]
    public string description
    {
        get
        {
            return this.descriptionField;
        }
        set
        {
            this.descriptionField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlArrayAttribute(Order=3)]
    [System.Xml.Serialization.XmlArrayItemAttribute("groupObj", Namespace="urn:idm.openiam.org/srvc/grp/dto")]
    public groupSetGroupObj[] groups
    {
        get
        {
            return this.groupsField;
        }
        set
        {
            this.groupsField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=4)]
    public roleId id
    {
        get
        {
            return this.idField;
        }
        set
        {
            this.idField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=5)]
    public string provisionObjName
    {
        get
        {
            return this.provisionObjNameField;
        }
        set
        {
            this.provisionObjNameField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=6)]
    public string parentRoleId
    {
        get
        {
            return this.parentRoleIdField;
        }
        set
        {
            this.parentRoleIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlArrayAttribute(Order=7)]
    [System.Xml.Serialization.XmlArrayItemAttribute("roleAttributeObj")]
    public roleAttributeSetRoleAttributeObj[] roleAttributes
    {
        get
        {
            return this.roleAttributesField;
        }
        set
        {
            this.roleAttributesField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=8)]
    public string roleName
    {
        get
        {
            return this.roleNameField;
        }
        set
        {
            this.roleNameField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=9)]
    public int userAssociationMethod
    {
        get
        {
            return this.userAssociationMethodField;
        }
        set
        {
            this.userAssociationMethodField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=10)]
    public string metadataTypeId
    {
        get
        {
            return this.metadataTypeIdField;
        }
        set
        {
            this.metadataTypeIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=11)]
    public string ownerId
    {
        get
        {
            return this.ownerIdField;
        }
        set
        {
            this.ownerIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=12)]
    public int inheritFromParent
    {
        get
        {
            return this.inheritFromParentField;
        }
        set
        {
            this.inheritFromParentField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool inheritFromParentSpecified
    {
        get
        {
            return this.inheritFromParentFieldSpecified;
        }
        set
        {
            this.inheritFromParentFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=13)]
    public string status
    {
        get
        {
            return this.statusField;
        }
        set
        {
            this.statusField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("childRoles", IsNullable=true, Order=14)]
    public role[] childRoles
    {
        get
        {
            return this.childRolesField;
        }
        set
        {
            this.childRolesField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("selected", Order=15)]
    public bool selected1
    {
        get
        {
            return this.selected1Field;
        }
        set
        {
            this.selected1Field = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool selected1Specified
    {
        get
        {
            return this.selected1FieldSpecified;
        }
        set
        {
            this.selected1FieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=16)]
    public string internalRoleId
    {
        get
        {
            return this.internalRoleIdField;
        }
        set
        {
            this.internalRoleIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=17)]
    public AttributeOperationEnum operation
    {
        get
        {
            return this.operationField;
        }
        set
        {
            this.operationField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool operationSpecified
    {
        get
        {
            return this.operationFieldSpecified;
        }
        set
        {
            this.operationFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=18)]
    public System.DateTime startDate
    {
        get
        {
            return this.startDateField;
        }
        set
        {
            this.startDateField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool startDateSpecified
    {
        get
        {
            return this.startDateFieldSpecified;
        }
        set
        {
            this.startDateFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=19)]
    public System.DateTime endDate
    {
        get
        {
            return this.endDateField;
        }
        set
        {
            this.endDateField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool endDateSpecified
    {
        get
        {
            return this.endDateFieldSpecified;
        }
        set
        {
            this.endDateFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("rolePolicy", IsNullable=true, Order=20)]
    public RolePolicy[] rolePolicy
    {
        get
        {
            return this.rolePolicyField;
        }
        set
        {
            this.rolePolicyField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="urn:idm.openiam.org/srvc/grp/dto")]
public partial class groupSetGroupObj
{
    
    private Group groupField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public Group group
    {
        get
        {
            return this.groupField;
        }
        set
        {
            this.groupField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="urn:idm.openiam.org/srvc/grp/dto")]
public partial class Group
{
    
    private roleSetRoleObj[] rolesField;
    
    private groupAttributeMapGroupAttributeEntry[] attributesField;
    
    private string companyIdField;
    
    private System.DateTime createDateField;
    
    private bool createDateFieldSpecified;
    
    private string createdByField;
    
    private string descriptionField;
    
    private string groupClassField;
    
    private string grpIdField;
    
    private string grpNameField;
    
    private bool inheritFromParentField;
    
    private bool inheritFromParentFieldSpecified;
    
    private System.DateTime lastUpdateField;
    
    private bool lastUpdateFieldSpecified;
    
    private string lastUpdatedByField;
    
    private string parentGrpIdField;
    
    private string provisionMethodField;
    
    private string provisionObjNameField;
    
    private string statusField;
    
    private Group[] subGroupField;
    
    private string metadataTypeIdField;
    
    private bool selectedField;
    
    private bool selectedFieldSpecified;
    
    private string ownerIdField;
    
    private string internalGroupIdField;
    
    private AttributeOperationEnum operationField;
    
    private bool operationFieldSpecified;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlArrayAttribute(Order=0)]
    [System.Xml.Serialization.XmlArrayItemAttribute("roleObj", Namespace="urn:idm.openiam.org/srvc/role/dto")]
    public roleSetRoleObj[] roles
    {
        get
        {
            return this.rolesField;
        }
        set
        {
            this.rolesField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlArrayAttribute(Order=1)]
    [System.Xml.Serialization.XmlArrayItemAttribute("groupAttributeEntry")]
    public groupAttributeMapGroupAttributeEntry[] attributes
    {
        get
        {
            return this.attributesField;
        }
        set
        {
            this.attributesField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=2)]
    public string companyId
    {
        get
        {
            return this.companyIdField;
        }
        set
        {
            this.companyIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=3)]
    public System.DateTime createDate
    {
        get
        {
            return this.createDateField;
        }
        set
        {
            this.createDateField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool createDateSpecified
    {
        get
        {
            return this.createDateFieldSpecified;
        }
        set
        {
            this.createDateFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=4)]
    public string createdBy
    {
        get
        {
            return this.createdByField;
        }
        set
        {
            this.createdByField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=5)]
    public string description
    {
        get
        {
            return this.descriptionField;
        }
        set
        {
            this.descriptionField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=6)]
    public string groupClass
    {
        get
        {
            return this.groupClassField;
        }
        set
        {
            this.groupClassField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=7)]
    public string grpId
    {
        get
        {
            return this.grpIdField;
        }
        set
        {
            this.grpIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=8)]
    public string grpName
    {
        get
        {
            return this.grpNameField;
        }
        set
        {
            this.grpNameField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=9)]
    public bool inheritFromParent
    {
        get
        {
            return this.inheritFromParentField;
        }
        set
        {
            this.inheritFromParentField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool inheritFromParentSpecified
    {
        get
        {
            return this.inheritFromParentFieldSpecified;
        }
        set
        {
            this.inheritFromParentFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=10)]
    public System.DateTime lastUpdate
    {
        get
        {
            return this.lastUpdateField;
        }
        set
        {
            this.lastUpdateField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool lastUpdateSpecified
    {
        get
        {
            return this.lastUpdateFieldSpecified;
        }
        set
        {
            this.lastUpdateFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=11)]
    public string lastUpdatedBy
    {
        get
        {
            return this.lastUpdatedByField;
        }
        set
        {
            this.lastUpdatedByField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=12)]
    public string parentGrpId
    {
        get
        {
            return this.parentGrpIdField;
        }
        set
        {
            this.parentGrpIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=13)]
    public string provisionMethod
    {
        get
        {
            return this.provisionMethodField;
        }
        set
        {
            this.provisionMethodField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=14)]
    public string provisionObjName
    {
        get
        {
            return this.provisionObjNameField;
        }
        set
        {
            this.provisionObjNameField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=15)]
    public string status
    {
        get
        {
            return this.statusField;
        }
        set
        {
            this.statusField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("subGroup", IsNullable=true, Order=16)]
    public Group[] subGroup
    {
        get
        {
            return this.subGroupField;
        }
        set
        {
            this.subGroupField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=17)]
    public string metadataTypeId
    {
        get
        {
            return this.metadataTypeIdField;
        }
        set
        {
            this.metadataTypeIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=18)]
    public bool selected
    {
        get
        {
            return this.selectedField;
        }
        set
        {
            this.selectedField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool selectedSpecified
    {
        get
        {
            return this.selectedFieldSpecified;
        }
        set
        {
            this.selectedFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=19)]
    public string ownerId
    {
        get
        {
            return this.ownerIdField;
        }
        set
        {
            this.ownerIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=20)]
    public string internalGroupId
    {
        get
        {
            return this.internalGroupIdField;
        }
        set
        {
            this.internalGroupIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=21)]
    public AttributeOperationEnum operation
    {
        get
        {
            return this.operationField;
        }
        set
        {
            this.operationField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool operationSpecified
    {
        get
        {
            return this.operationFieldSpecified;
        }
        set
        {
            this.operationFieldSpecified = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="urn:idm.openiam.org/srvc/role/dto")]
public partial class roleSetRoleObj
{
    
    private role roleField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public role role
    {
        get
        {
            return this.roleField;
        }
        set
        {
            this.roleField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="urn:idm.openiam.org/srvc/grp/dto")]
public partial class groupAttributeMapGroupAttributeEntry
{
    
    private GroupAttribute groupAttributeField;
    
    private string keyField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public GroupAttribute groupAttribute
    {
        get
        {
            return this.groupAttributeField;
        }
        set
        {
            this.groupAttributeField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlAttributeAttribute()]
    public string key
    {
        get
        {
            return this.keyField;
        }
        set
        {
            this.keyField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="urn:idm.openiam.org/srvc/role/dto")]
public partial class roleAttributeSetRoleAttributeObj
{
    
    private roleAttribute roleAttributeField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public roleAttribute roleAttribute
    {
        get
        {
            return this.roleAttributeField;
        }
        set
        {
            this.roleAttributeField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="urn:idm.openiam.org/srvc/continfo/dto")]
public partial class emailAddress
{
    
    private bool isActiveField;
    
    private bool isActiveFieldSpecified;
    
    private string descriptionField;
    
    private string emailAddress1Field;
    
    private string emailIdField;
    
    private int isDefaultField;
    
    private bool isDefaultFieldSpecified;
    
    private string parentIdField;
    
    private string parentTypeField;
    
    private string nameField;
    
    private AttributeOperationEnum operationField;
    
    private bool operationFieldSpecified;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public bool isActive
    {
        get
        {
            return this.isActiveField;
        }
        set
        {
            this.isActiveField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool isActiveSpecified
    {
        get
        {
            return this.isActiveFieldSpecified;
        }
        set
        {
            this.isActiveFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=1)]
    public string description
    {
        get
        {
            return this.descriptionField;
        }
        set
        {
            this.descriptionField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("emailAddress", Order=2)]
    public string emailAddress1
    {
        get
        {
            return this.emailAddress1Field;
        }
        set
        {
            this.emailAddress1Field = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=3)]
    public string emailId
    {
        get
        {
            return this.emailIdField;
        }
        set
        {
            this.emailIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=4)]
    public int isDefault
    {
        get
        {
            return this.isDefaultField;
        }
        set
        {
            this.isDefaultField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool isDefaultSpecified
    {
        get
        {
            return this.isDefaultFieldSpecified;
        }
        set
        {
            this.isDefaultFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=5)]
    public string parentId
    {
        get
        {
            return this.parentIdField;
        }
        set
        {
            this.parentIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=6)]
    public string parentType
    {
        get
        {
            return this.parentTypeField;
        }
        set
        {
            this.parentTypeField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=7)]
    public string name
    {
        get
        {
            return this.nameField;
        }
        set
        {
            this.nameField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=8)]
    public AttributeOperationEnum operation
    {
        get
        {
            return this.operationField;
        }
        set
        {
            this.operationField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool operationSpecified
    {
        get
        {
            return this.operationFieldSpecified;
        }
        set
        {
            this.operationFieldSpecified = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="urn:idm.openiam.org/srvc/continfo/dto")]
public partial class phone
{
    
    private bool isActiveField;
    
    private bool isActiveFieldSpecified;
    
    private string areaCdField;
    
    private string countryCdField;
    
    private string descriptionField;
    
    private int isDefaultField;
    
    private bool isDefaultFieldSpecified;
    
    private string parentIdField;
    
    private string parentTypeField;
    
    private string phoneExtField;
    
    private string phoneIdField;
    
    private string phoneNbrField;
    
    private string phoneTypeField;
    
    private string nameField;
    
    private AttributeOperationEnum operationField;
    
    private bool operationFieldSpecified;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public bool isActive
    {
        get
        {
            return this.isActiveField;
        }
        set
        {
            this.isActiveField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool isActiveSpecified
    {
        get
        {
            return this.isActiveFieldSpecified;
        }
        set
        {
            this.isActiveFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=1)]
    public string areaCd
    {
        get
        {
            return this.areaCdField;
        }
        set
        {
            this.areaCdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=2)]
    public string countryCd
    {
        get
        {
            return this.countryCdField;
        }
        set
        {
            this.countryCdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=3)]
    public string description
    {
        get
        {
            return this.descriptionField;
        }
        set
        {
            this.descriptionField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=4)]
    public int isDefault
    {
        get
        {
            return this.isDefaultField;
        }
        set
        {
            this.isDefaultField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool isDefaultSpecified
    {
        get
        {
            return this.isDefaultFieldSpecified;
        }
        set
        {
            this.isDefaultFieldSpecified = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=5)]
    public string parentId
    {
        get
        {
            return this.parentIdField;
        }
        set
        {
            this.parentIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=6)]
    public string parentType
    {
        get
        {
            return this.parentTypeField;
        }
        set
        {
            this.parentTypeField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=7)]
    public string phoneExt
    {
        get
        {
            return this.phoneExtField;
        }
        set
        {
            this.phoneExtField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=8)]
    public string phoneId
    {
        get
        {
            return this.phoneIdField;
        }
        set
        {
            this.phoneIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=9)]
    public string phoneNbr
    {
        get
        {
            return this.phoneNbrField;
        }
        set
        {
            this.phoneNbrField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=10)]
    public string phoneType
    {
        get
        {
            return this.phoneTypeField;
        }
        set
        {
            this.phoneTypeField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=11)]
    public string name
    {
        get
        {
            return this.nameField;
        }
        set
        {
            this.nameField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=12)]
    public AttributeOperationEnum operation
    {
        get
        {
            return this.operationField;
        }
        set
        {
            this.operationField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIgnoreAttribute()]
    public bool operationSpecified
    {
        get
        {
            return this.operationFieldSpecified;
        }
        set
        {
            this.operationFieldSpecified = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/types")]
public partial class ExtensibleAttribute
{
    
    private string nameField;
    
    private string valueField;
    
    private string metadataElementIdField;
    
    private int operationField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public string name
    {
        get
        {
            return this.nameField;
        }
        set
        {
            this.nameField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=1)]
    public string value
    {
        get
        {
            return this.valueField;
        }
        set
        {
            this.valueField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=2)]
    public string metadataElementId
    {
        get
        {
            return this.metadataElementIdField;
        }
        set
        {
            this.metadataElementIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=3)]
    public int operation
    {
        get
        {
            return this.operationField;
        }
        set
        {
            this.operationField = value;
        }
    }
}

/// <remarks/>
[System.Xml.Serialization.XmlIncludeAttribute(typeof(ExtensibleRole))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(ExtensibleGroup))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(ExtensibleEmailAddress))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(ExtensiblePhone))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(ExtensibleAddress))]
[System.Xml.Serialization.XmlIncludeAttribute(typeof(ExtensibleUser))]
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/types")]
public partial class ExtensibleObject
{
    
    private string objectIdField;
    
    private string nameField;
    
    private int operationField;
    
    private ExtensibleAttribute[] attributesField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public string objectId
    {
        get
        {
            return this.objectIdField;
        }
        set
        {
            this.objectIdField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=1)]
    public string name
    {
        get
        {
            return this.nameField;
        }
        set
        {
            this.nameField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=2)]
    public int operation
    {
        get
        {
            return this.operationField;
        }
        set
        {
            this.operationField = value;
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute("attributes", IsNullable=true, Order=3)]
    public ExtensibleAttribute[] attributes
    {
        get
        {
            return this.attributesField;
        }
        set
        {
            this.attributesField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/types")]
public partial class ExtensibleRole : ExtensibleObject
{
    
    private role roleField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public role role
    {
        get
        {
            return this.roleField;
        }
        set
        {
            this.roleField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/types")]
public partial class ExtensibleGroup : ExtensibleObject
{
    
    private Group groupField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public Group group
    {
        get
        {
            return this.groupField;
        }
        set
        {
            this.groupField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/types")]
public partial class ExtensibleEmailAddress : ExtensibleObject
{
    
    private emailAddress emailAddressField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public emailAddress emailAddress
    {
        get
        {
            return this.emailAddressField;
        }
        set
        {
            this.emailAddressField = value;
        }
    }
}

/// <remarks/>
[System.CodeDom.Compiler.GeneratedCodeAttribute("svcutil", "3.0.4506.648")]
[System.SerializableAttribute()]
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Xml.Serialization.XmlTypeAttribute(Namespace="http://www.openiam.org/service/types")]
public partial class ExtensiblePhone : ExtensibleObject
{
    
    private phone phoneField;
    
    /// <remarks/>
    [System.Xml.Serialization.XmlElementAttribute(Order=0)]
    public phone phone
    {
        get
        {
            return this.phoneField;
        }
        set
        {
            this.phoneField = value;
        }
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
[System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
public partial class add1
{
    
    [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://www.openiam.org/service/connector", Order=0)]
    public add add;
    
    public add1()
    {
    }
    
    public add1(add add)
    {
        this.add = add;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
[System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
public partial class addResponse1
{
    
    [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://www.openiam.org/service/connector", Order=0)]
    [System.Xml.Serialization.XmlElementAttribute(IsNullable=true)]
    public object addResponse;
    
    public addResponse1()
    {
    }
    
    public addResponse1(object addResponse)
    {
        this.addResponse = addResponse;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
[System.ServiceModel.MessageContractAttribute(WrapperName="resume", WrapperNamespace="http://www.openiam.org/service/connector", IsWrapped=true)]
public partial class resume1
{
    
    [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://www.openiam.org/service/connector", Order=0)]
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
    public ResumeRequest request;
    
    public resume1()
    {
    }
    
    public resume1(ResumeRequest request)
    {
        this.request = request;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
[System.ServiceModel.MessageContractAttribute(WrapperName="resumeResponse", WrapperNamespace="http://www.openiam.org/service/connector", IsWrapped=true)]
public partial class resumeResponse1
{
    
    [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://www.openiam.org/service/connector", Order=0)]
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
    public BaseResponseType @return;
    
    public resumeResponse1()
    {
    }
    
    public resumeResponse1(BaseResponseType @return)
    {
        this.@return = @return;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
[System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
public partial class lookup1
{
    
    [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://www.openiam.org/service/connector", Order=0)]
    public lookup lookup;
    
    public lookup1()
    {
    }
    
    public lookup1(lookup lookup)
    {
        this.lookup = lookup;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
[System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
public partial class lookupResponse1
{
    
    [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://www.openiam.org/service/connector", Order=0)]
    [System.Xml.Serialization.XmlElementAttribute(IsNullable=true)]
    public object lookupResponse;
    
    public lookupResponse1()
    {
    }
    
    public lookupResponse1(object lookupResponse)
    {
        this.lookupResponse = lookupResponse;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
[System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
public partial class delete1
{
    
    [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://www.openiam.org/service/connector", Order=0)]
    public delete delete;
    
    public delete1()
    {
    }
    
    public delete1(delete delete)
    {
        this.delete = delete;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
[System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
public partial class deleteResponse1
{
    
    [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://www.openiam.org/service/connector", Order=0)]
    [System.Xml.Serialization.XmlElementAttribute(IsNullable=true)]
    public object deleteResponse;
    
    public deleteResponse1()
    {
    }
    
    public deleteResponse1(object deleteResponse)
    {
        this.deleteResponse = deleteResponse;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
[System.ServiceModel.MessageContractAttribute(WrapperName="suspend", WrapperNamespace="http://www.openiam.org/service/connector", IsWrapped=true)]
public partial class suspend1
{
    
    [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://www.openiam.org/service/connector", Order=0)]
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
    public SuspendRequest request;
    
    public suspend1()
    {
    }
    
    public suspend1(SuspendRequest request)
    {
        this.request = request;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
[System.ServiceModel.MessageContractAttribute(WrapperName="suspendResponse", WrapperNamespace="http://www.openiam.org/service/connector", IsWrapped=true)]
public partial class suspendResponse1
{
    
    [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://www.openiam.org/service/connector", Order=0)]
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
    public BaseResponseType @return;
    
    public suspendResponse1()
    {
    }
    
    public suspendResponse1(BaseResponseType @return)
    {
        this.@return = @return;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
[System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
public partial class modify1
{
    
    [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://www.openiam.org/service/connector", Order=0)]
    public modify modify;
    
    public modify1()
    {
    }
    
    public modify1(modify modify)
    {
        this.modify = modify;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
[System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
public partial class modifyResponse1
{
    
    [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://www.openiam.org/service/connector", Order=0)]
    [System.Xml.Serialization.XmlElementAttribute(IsNullable=true)]
    public object modifyResponse;
    
    public modifyResponse1()
    {
    }
    
    public modifyResponse1(object modifyResponse)
    {
        this.modifyResponse = modifyResponse;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
[System.ServiceModel.MessageContractAttribute(WrapperName="setPassword", WrapperNamespace="http://www.openiam.org/service/connector", IsWrapped=true)]
public partial class setPassword1
{
    
    [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://www.openiam.org/service/connector", Order=0)]
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
    public PasswordRequest request;
    
    public setPassword1()
    {
    }
    
    public setPassword1(PasswordRequest request)
    {
        this.request = request;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
[System.ServiceModel.MessageContractAttribute(WrapperName="setPasswordResponse", WrapperNamespace="http://www.openiam.org/service/connector", IsWrapped=true)]
public partial class setPasswordResponse1
{
    
    [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://www.openiam.org/service/connector", Order=0)]
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
    public BaseResponseType @return;
    
    public setPasswordResponse1()
    {
    }
    
    public setPasswordResponse1(BaseResponseType @return)
    {
        this.@return = @return;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
[System.ServiceModel.MessageContractAttribute(WrapperName="resetPassword", WrapperNamespace="http://www.openiam.org/service/connector", IsWrapped=true)]
public partial class resetPassword1
{
    
    [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://www.openiam.org/service/connector", Order=0)]
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
    public PasswordRequest request;
    
    public resetPassword1()
    {
    }
    
    public resetPassword1(PasswordRequest request)
    {
        this.request = request;
    }
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
[System.ServiceModel.MessageContractAttribute(WrapperName="resetPasswordResponse", WrapperNamespace="http://www.openiam.org/service/connector", IsWrapped=true)]
public partial class resetPasswordResponse1
{
    
    [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://www.openiam.org/service/connector", Order=0)]
    [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
    public BaseResponseType @return;
    
    public resetPasswordResponse1()
    {
    }
    
    public resetPasswordResponse1(BaseResponseType @return)
    {
        this.@return = @return;
    }
}

[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
public interface RemoteConnectorServiceChannel : RemoteConnectorService, System.ServiceModel.IClientChannel
{
}

[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
public partial class RemoteConnectorServiceClient : System.ServiceModel.ClientBase<RemoteConnectorService>, RemoteConnectorService
{
    
    public RemoteConnectorServiceClient()
    {
    }
    
    public RemoteConnectorServiceClient(string endpointConfigurationName) : 
            base(endpointConfigurationName)
    {
    }
    
    public RemoteConnectorServiceClient(string endpointConfigurationName, string remoteAddress) : 
            base(endpointConfigurationName, remoteAddress)
    {
    }
    
    public RemoteConnectorServiceClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
            base(endpointConfigurationName, remoteAddress)
    {
    }
    
    public RemoteConnectorServiceClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
            base(binding, remoteAddress)
    {
    }
    
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    addResponse1 RemoteConnectorService.add(add1 request)
    {
        return base.Channel.add(request);
    }
    
    public object add(add add1)
    {
        add1 inValue = new add1();
        inValue.add = add1;
        addResponse1 retVal = ((RemoteConnectorService)(this)).add(inValue);
        return retVal.addResponse;
    }
    
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    resumeResponse1 RemoteConnectorService.resume(resume1 request)
    {
        return base.Channel.resume(request);
    }
    
    public BaseResponseType resume(ResumeRequest request)
    {
        resume1 inValue = new resume1();
        inValue.request = request;
        resumeResponse1 retVal = ((RemoteConnectorService)(this)).resume(inValue);
        return retVal.@return;
    }
    
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    lookupResponse1 RemoteConnectorService.lookup(lookup1 request)
    {
        return base.Channel.lookup(request);
    }
    
    public object lookup(lookup lookup1)
    {
        lookup1 inValue = new lookup1();
        inValue.lookup = lookup1;
        lookupResponse1 retVal = ((RemoteConnectorService)(this)).lookup(inValue);
        return retVal.lookupResponse;
    }
    
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    deleteResponse1 RemoteConnectorService.delete(delete1 request)
    {
        return base.Channel.delete(request);
    }
    
    public object delete(delete delete1)
    {
        delete1 inValue = new delete1();
        inValue.delete = delete1;
        deleteResponse1 retVal = ((RemoteConnectorService)(this)).delete(inValue);
        return retVal.deleteResponse;
    }
    
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    suspendResponse1 RemoteConnectorService.suspend(suspend1 request)
    {
        return base.Channel.suspend(request);
    }
    
    public BaseResponseType suspend(SuspendRequest request)
    {
        suspend1 inValue = new suspend1();
        inValue.request = request;
        suspendResponse1 retVal = ((RemoteConnectorService)(this)).suspend(inValue);
        return retVal.@return;
    }
    
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    modifyResponse1 RemoteConnectorService.modify(modify1 request)
    {
        return base.Channel.modify(request);
    }
    
    public object modify(modify modify1)
    {
        modify1 inValue = new modify1();
        inValue.modify = modify1;
        modifyResponse1 retVal = ((RemoteConnectorService)(this)).modify(inValue);
        return retVal.modifyResponse;
    }
    
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    setPasswordResponse1 RemoteConnectorService.setPassword(setPassword1 request)
    {
        return base.Channel.setPassword(request);
    }
    
    public BaseResponseType setPassword(PasswordRequest request)
    {
        setPassword1 inValue = new setPassword1();
        inValue.request = request;
        setPasswordResponse1 retVal = ((RemoteConnectorService)(this)).setPassword(inValue);
        return retVal.@return;
    }
    
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    resetPasswordResponse1 RemoteConnectorService.resetPassword(resetPassword1 request)
    {
        return base.Channel.resetPassword(request);
    }
    
    public BaseResponseType resetPassword(PasswordRequest request)
    {
        resetPassword1 inValue = new resetPassword1();
        inValue.request = request;
        resetPasswordResponse1 retVal = ((RemoteConnectorService)(this)).resetPassword(inValue);
        return retVal.@return;
    }
}
