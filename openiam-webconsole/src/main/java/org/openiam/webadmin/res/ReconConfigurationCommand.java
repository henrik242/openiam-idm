package org.openiam.webadmin.res;

import org.openiam.idm.srvc.recon.dto.ReconciliationConfig;
import org.openiam.idm.srvc.recon.dto.ReconciliationSituation;
import org.openiam.idm.srvc.res.dto.Resource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Command object for Synchronization Configuration
 *
 * @author suneet
 */
public class ReconConfigurationCommand implements Serializable {

    private Resource res;
    private ReconciliationConfig config = new ReconciliationConfig();
    private List<ReconciliationSituation> situationList = new ArrayList<ReconciliationSituation>();

    public Resource getRes() {
        return res;
    }

    public void setRes(Resource res) {
        this.res = res;
    }

    public ReconciliationConfig getConfig() {
        return config;
    }

    public void setConfig(ReconciliationConfig config) {
        this.config = config;
    }

    public List<ReconciliationSituation> getSituationList() {
        return situationList;
    }

    public void setSituationList(List<ReconciliationSituation> situationList) {
        this.situationList = situationList;
    }


}
