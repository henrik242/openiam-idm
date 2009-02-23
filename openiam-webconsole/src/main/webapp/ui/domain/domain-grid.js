
domainStatus = [
     ['ACTIVE', 'Active'],
     ['INACTIVE', 'InActive']

];

Ext.onReady(function(){

    // NOTE: This is an example showing simple state management. During development,
    // it is generally best to disable state management as dynamically-generated ids
    // can change across page loads, leading to unpredictable results.  The developer
    // should ensure that stable state ids are set for stateful components in real apps.
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());


    // example of custom renderer function
    function change(val){
        if(val > 0){
            return '<span style="color:green;">' + val + '</span>';
        }else if(val < 0){
            return '<span style="color:red;">' + val + '</span>';
        }
        return val;
    }

    // example of custom renderer function
    function pctChange(val){
        if(val > 0){
            return '<span style="color:green;">' + val + '%</span>';
        }else if(val < 0){
            return '<span style="color:red;">' + val + '%</span>';
        }
        return val;
    }

    // create and load the data source for the grid
    var domain = Ext.data.Record.create([
                                         {name: 'domainId'},
                                         {name: 'name'},
                                         {name: 'status'},
                                      ]);
    var store = new Ext.data.Store({
    	url: '/webconsole/domain.cnt?method=list',
    	reader:new Ext.data.JsonReader({
    		root:"rows"
    	},domain)
    });
    
    store.load(true);

    var colModel = new Ext.grid.ColumnModel([
                 {id:'domainId',header: "Domain ID", width: 110, sortable: true, dataIndex: 'domainId'},
                 {header: "Domain Name", width: 220, sortable: true, dataIndex: 'name'}
                 //{header: "Status", width: 220, sortable: true, dataIndex: 'status'}
             ]);
    
    
    // create the toolbar for the grid
    var tb = new Ext.Toolbar();
    

    // add button to the grid tool bar
    
    var btnNew = new Ext.Button({
 	   text: 'New Domain',
 	   id: 'newBtn',
 	   name: 'newBtn',
 	   // clear the form when NEW is pressed
 	   handler:function(){ 
    		Ext.getCmp("domain-form").getForm().reset();
    		domainForm.findById('domainId').enable();
    	}
    });

    var domainForm = new Ext.FormPanel({
        id: 'domain-form',
        frame: true,
        labelAlign: 'left',
        title: 'Security Domains',
        bodyStyle:'padding:5px',
        width: 700,
        url: '/webconsole/domain.cnt?method=save',
        layout: 'column',	// Specifies that the items will now be arranged in columns
       
        items: [{
            columnWidth: 0.5,
            layout: 'fit',
            items: [{
	            xtype: 'grid',
	            ds: store,
	            cm: colModel,
	            sm: new Ext.grid.RowSelectionModel({
	                singleSelect: true,
	                listeners: {
	                    rowselect: function(sm, row, rec) {
	                        Ext.getCmp("domain-form").getForm().loadRecord(rec);
	                        domainForm.findById('domainId').disable();
	                    }
	                }
	            }),
	            //autoExpandColumn: 'company',
	            height: 300,
	            title:'Domain List',
	            border: true,
	            tbar: tb, 
		        listeners: {
		        	render: function(g) {
		        		g.getSelectionModel().selectRow(0);
		        	},
		        	delay: 10 // Allow rows to be rendered.
		        }            
        	}]
        }, {
          	columnWidth: 0.5,
            xtype: 'fieldset',
            labelWidth: 60,
            title:'Domain details',
            defaults: {width: 175},	// Default config options for child items
            defaultType: 'textfield',
            autoHeight: true,
            bodyStyle: Ext.isIE ? 'padding:0 0 5px 15px;' : 'padding:10px 15px;',
            border: false,
            items: [{
                fieldLabel: 'Domain ID',
                name: 'domainId',
                id: 'domainId',
                allowBlank:false,
                maxLength:20
            },{
                fieldLabel: 'Name',
                name: 'name',
                id: 'name',
                allowBlank:false,
                maxLength:20
            },new Ext.form.ComboBox({
                displayField:'status',
                name:'status',
                width:150,
                fieldLabel:'Status',
                valueField:'statusid',
                store: new Ext.data.SimpleStore({
                    fields:['statusid', 'status'],
                    data:domainStatus
                    }),
                triggerAction:'all',
                mode:'local'
        		
    		})
            ],
            buttons: [{
                text: 'Save',
                id: 'btnSave',
                formBind:true,
                handler:function(){
            		 	domainForm.findById('domainId').enable();
            		 	domainForm.getForm().url = '/webconsole/domain.cnt?method=save';
            			domainForm.getForm().submit({ 
	                        method:'POST', 
	                        success: function() {           				
	            				store.load(true);
	            				domainForm.findById('domainId').disable();
	            	        	statusBar = Ext.getCmp('form-statusbar');
	            	        	statusBar.showBusy('<font color=red>Data has been successfully saved.</font>');
	            	           (function(){
	            	                statusBar.clearStatus({useDefaults:true});
	            	            }).defer(4000);      	        	
	            			}
            				
            			})
            			
            	}
            }, {
            	text: 'Delete',
            	id: 'btnDelete',
                handler:function(){ 
            		domainForm.findById('domainId').enable();
            		domainForm.getForm().url = '/webconsole/domain.cnt?method=delete';
	    			domainForm.getForm().submit({ 
	                method:'POST', 
	                success: function() {
	    				store.load(true);
	    	        	statusBar = Ext.getCmp('form-statusbar');
	    	        	statusBar.showBusy('<font color=red>Data has been successfully deleted.</font>');
	    	           (function(){
	    	                statusBar.clearStatus({useDefaults:true});
	    	            }).defer(4000);      
	    	           Ext.getCmp("domain-form").getForm().reset();
	    			}
    			});
            }
            

            },{
                text: 'Cancel'
            }],
            bbar: new Ext.StatusBar({
                defaultText: '',
                id: 'form-statusbar',
                statusAlign: 'right', 
                items: [{
                    text: 'Status'
                }, '-', ' ', ' ', ' ']
            }),
           listeners: {
                render: function(p) {
                    p.footer.dom.appendChild(p.getBottomToolbar().el.dom);
                }
            }
 
        }
        ]
    });


// domainForm.render(document.body);
    domainForm.render("body_cell");
    
   tb.addField(btnNew);
    
});


