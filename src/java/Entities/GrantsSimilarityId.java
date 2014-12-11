/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author hmetaxa
 */
public class GrantsSimilarityId implements Serializable {

    private static final long serialVersionUID = 1L;
    private String grantId1;
    private String grantId2;
    private String experimentId;

    public GrantsSimilarityId() {
    }

    public GrantsSimilarityId(String experimentId, String grantId1, String grantId2) {
        this.experimentId = experimentId;
        this.grantId1 = grantId1;
        this.grantId2 = grantId2;
    }

    public String getGrantId1() {
        return grantId1;
    }

    public String getGrantId2() {
        return grantId2;
    }

    public String getExperimentId() {
        return experimentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (experimentId != null ? experimentId.hashCode() : 0);
        hash += (grantId1 != null ? grantId1.hashCode() : 0);
        hash += (grantId2 != null ? grantId2.hashCode() : 0);

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (object instanceof GrantsSimilarityId) {
            GrantsSimilarityId grantsSimilarityId = (GrantsSimilarityId) object;
            return grantsSimilarityId.experimentId.equals(this.experimentId)
                    && grantsSimilarityId.grantId1.equals(this.grantId1)
                    && grantsSimilarityId.grantId2.equals(this.grantId2);
        }
        return false;
    }
}
