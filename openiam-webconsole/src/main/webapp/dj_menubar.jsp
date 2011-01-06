
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/dijit/themes/claro/claro.css"    />
<script src="<%=request.getContextPath()%>/js/dojo/dojo.js"  djConfig="parseOnLoad: true" ></script>

<script type="text/javascript">
    dojo.require("dijit.MenuBar");
    dojo.require("dijit.PopupMenuBarItem");
    dojo.require("dijit.Menu");
    dojo.require("dijit.MenuItem");
    dojo.require("dijit.PopupMenuItem");
</script>

    <body class=" claro ">
        <div dojoType="dijit.MenuBar" id="navMenu">
        	
            <div dojoType="dijit.PopupMenuBarItem">
                <span>
                    User Admin
                </span>
                <div dojoType="dijit.Menu" id="fileMenu">
                    <div dojoType="dijit.MenuItem" onClick="alert('file 1')">
                        User
                    </div>                     
                    <div dojoType="dijit.MenuItem" onClick="alert('file 2')">
                       Organization
                    </div>
                </div>
            </div>
           
            <div dojoType="dijit.PopupMenuBarItem">
                <span>
                    Access Control
                </span>
                <div dojoType="dijit.Menu" id="editMenu">
                    <div dojoType="dijit.MenuItem" onClick="alert('edit 1')">
                        Group
                    </div>
                    <div dojoType="dijit.MenuItem" onClick="alert('edit 2')">
                       Role
                    </div>
                    <div dojoType="dijit.MenuItem" onClick="alert('edit 2')">
                       Resources
                    </div>
                </div>            </div>
 
            <div dojoType="dijit.PopupMenuBarItem">
                <span>
                    Provisioning
                </span>
            </div>
            <div dojoType="dijit.PopupMenuBarItem">
                <span>
                    Password
                </span>
            </div>
            <div dojoType="dijit.PopupMenuBarItem">
                <span>
                    Report
                </span>
            </div>
            <div dojoType="dijit.PopupMenuBarItem">
                <span>
                    Administration
                </span>
            </div>                        
	 </body>

 

