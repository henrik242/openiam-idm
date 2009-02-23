/*
 * Ext JS Library 2.2
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */

// Add the additional 'advanced' VTypes

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
  	var queryString = '';

  	if (format != null && format.length > 0) {
 		queryString = 'format=' + format.getValue();
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
		 * ================  Date Range  =======================
		 */
    
    var dr = new Ext.FormPanel({
      labelWidth: 125,
      frame: true,
      title: 'Password Change Report Parameters',
	  bodyStyle:'padding:5px 5px 0',
	  width: 400,
      defaults: {width: 175},
      defaultType: 'datefield',
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
           }),{
        fieldLabel: 'Start Date',
        name: 'startdt',
        id: 'startdt',
        vtype: 'daterange',
        endDateField: 'enddt' // id of the end date field
      },{
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
     	  	if (dr.getForm().isValid()) {
          			qryStr = buildReportQueryString();
          			if (qryStr != null) {
          				reportUrl = '/webconsole/passwordChangeReport.report?method=pswd&' + qryStr;
          			}else {
          				reportUrl = '/webconsole/passwordChangeReport.report?method=pswd';
          			}
          			window.location = reportUrl; 
    	  	}
          }
      },{
          text: 'Cancel'
      }]
    });

    dr.render("body_cell");
    

});