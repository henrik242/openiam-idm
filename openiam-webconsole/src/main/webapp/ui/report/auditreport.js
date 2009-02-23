
reportFormat = [
     ['html', 'html'],
     ['pdf', 'pdf']
];

Ext.apply(Ext.form.VTypes, {
    daterange : function(val, field) {
        var date = field.parseDate(val);

        if(!date){
            return;
        }
        if (field.startDateField && (!this.dateRangeMax || (date.getTime() != this.dateRangeMax.getTime()))) {
            var start = Ext.getCmp(field.startDateField);
            start.setMaxValue(date);
            start.validate();
            this.dateRangeMax = date;
        } 
        else if (field.endDateField && (!this.dateRangeMin || (date.getTime() != this.dateRangeMin.getTime()))) {
            var end = Ext.getCmp(field.endDateField);
            end.setMinValue(date);
            end.validate();
            this.dateRangeMin = date;
        }
        /*
         * Always return true since we're only using this vtype to set the
         * min/max allowed values (these are tested for after the vtype test)
         */
        return true;
    }
});

function buildReportQueryString() {
	var format = Ext.get("reportFormat");
	var startDate = Ext.get("startdt");
	var endDate = Ext.get("enddt");
	var userId = Ext.get("userId");
	var loginId = Ext.get("loginId");
	var queryString = '';

 	if (format != null && format.getValue().length > 0) {
 		queryString = 'format=' + format.getValue();
 	}
 	
	
 	if (userId != null && userId.getValue().length > 0) {
 		if (queryString.length > 0) {
 			queryString = queryString + '&';
 		}
 		queryString = queryString + 'userid=' + userId.getValue();
 	}	
 	if (loginId != null && loginId.getValue().length > 0) {
 		if (queryString.length > 0) {
 			queryString = queryString + '&';
 		}
 		queryString = queryString + 'loginid=' + loginId.getValue(); 	
 	}
 	
 	if (startDate != null) {
 		if (queryString.length > 0) {
 			queryString = queryString + '&';
 		}
 		queryString = queryString + 'startdt=' + startDate.getValue();
 	}
 	if (endDate != null) {
 		if (queryString.length > 0) {
 			queryString = queryString + '&';
 		}
 		queryString = queryString + 'enddt=' + endDate.getValue();
 	}
 	return queryString;

}


Ext.onReady(function(){

    Ext.QuickTips.init();

    // turn on validation errors beside the field globally
    Ext.form.Field.prototype.msgTarget = 'side';

    var bd = Ext.getBody();

    /*
     * ================  Simple form  =======================
     */

    var reportForm = new Ext.FormPanel({
        labelWidth: 100, // label settings here cascade unless overridden
        frame:true,
        title: 'Audit Report Parameters',
        bodyStyle:'padding:5px 5px 0',
        width: 350,
        defaults: {width: 200},
        defaultType: 'textfield',

        items: [
 			new Ext.form.ComboBox({
              displayField:'format',
              name:'format',
              id: 'reportFormat',
              fieldLabel:'Report Format',
              valueField:'formatCode',
              store: new Ext.data.SimpleStore({
            	  fields:['formatCode', 'format'],
            	  data:reportFormat
              }),
              triggerAction:'all',
              mode:'local'
           }),{ fieldLabel: 'User GUD',
                name: 'first',
                id: 'userId',
                width:185
            },{
                fieldLabel: 'User Identity',
                name: 'last',
                id: 'loginId',
                width:185,
            },{
          		xtype: 'datefield',
            	fieldLabel: 'Start Date',
        			name: 'startdt',
        			id: 'startdt',
        			vtype: 'daterange',
        			endDateField: 'enddt' // id of the end date field
      			}, {
      				xtype: 'datefield',
      				fieldLabel: 'End Date',
			        name: 'enddt',
			        id: 'enddt',
			        vtype: 'daterange',
			        startDateField: 'startdt' // id of the start date field
      		}],
        buttons: [{
            text: 'Submit',
            id: 'btnSave',
            formBind:true,
            handler:function(){
            		if (reportForm.getForm().isValid()) {
            			qryStr = buildReportQueryString();
            			if (qryStr != null) {
            				reportUrl = '/webconsole/auditReport.report?method=auditReport&' + qryStr;    
            			} else {
            				reportUrl = '/webconsole/auditReport.report?method=auditReport';
            			}  
            			window.location = reportUrl;
            		}    			
            }
        },{
            text: 'Cancel'
        }]
    });

		reportForm.render("body_cell");
    
 });