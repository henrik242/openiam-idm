


def postalAddress = null;

if ( user.bldgNum != null || user.streetDirection != null || user.address1 != null ) {
 postalAddress = user.bldgNum + " " + user.streetDirection + " " + user.address1;
}
if (postalAddress == null || postalAddress.length() == 0) {
	println("postalAddress is null");
	output=null;
}else {
	println("postalAddress =" + postalAddress);
	output = postalAddress
}
