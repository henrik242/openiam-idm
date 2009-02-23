<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Side tree panel

<script type="text/javascript">
Ext.onReady(function(){

	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';

    var form_employee = new Ext.form.Form({
        labelAlign: 'left',
        labelWidth: 175,
		buttonAlign: 'right'
    });

    var employee_name = new Ext.form.TextField({
        fieldLabel: 'Name',
        name: 'name',
        width:190
    });

    var employee_title = new Ext.form.TextField({
        fieldLabel: 'Title',
        name: 'title',
        width:190
    });

    var employee_hire_date = new Ext.form.DateField({
        fieldLabel: 'Hire Date',
        name: 'hire_date',
        width:90,
        allowBlank:false,
		format:'m-d-Y'
    });

	var employee_active = new Ext.form.Checkbox({
        fieldLabel: 'Active',
        name: 'active'
    });

    form_employee.fieldset(
        {legend:'Employee Edit'},
		employee_name,
		employee_title,
		employee_hire_date,
		employee_active
	);
		
	form_employee.addButton('Save', function(){
		if (form_employee.isValid()) {
			Ext.MessageBox.alert('Success', 'You have filled out the form correctly.');	
		}else{
			Ext.MessageBox.alert('Errors', 'Please fix the errors noted.');
		}
	}, form_employee);
	
	form_employee.render('employee-form');

});
</script>
</body>
</html>