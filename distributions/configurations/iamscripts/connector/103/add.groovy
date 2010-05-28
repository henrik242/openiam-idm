//add.groovy for managedSys 103

import org.openiam.provision.type.ExtensibleObject
import org.openiam.provision.type.ExtensibleAttribute

		System.out.println("Add.groovy - POS Identitfier: " + reqType.getPsoID().getID());
		System.out.println("Add.groovy - RequestID: " + reqType.getRequestID());
		System.out.println("Add.groovy - TargetId: " + reqType.getTargetID());
		
		System.out.println("Data:" );
		objList = reqType.getData().getAny();
		for (obj in objList) {
			System.out.println("Object:" + obj.name + " - operation=" + obj.operation);
			attrList = obj.attributes
			for (att in attrList) {
				System.out.println("-->Attribute:" + att.name + " - value=" + att.value + " operation=" + att.operation);
			}
		}
		
		

output="SUCCESS"


