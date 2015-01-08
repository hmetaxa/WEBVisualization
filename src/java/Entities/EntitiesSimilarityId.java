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
public class EntitiesSimilarityId implements Serializable {

    private static final long serialVersionUID = 1L;
    private String entityId1;
    private String entityId2;
    private String experimentId;

    public EntitiesSimilarityId() {
    }

    public EntitiesSimilarityId(String experimentId, String entityId1, String entityId2) {
        this.experimentId = experimentId;
        this.entityId1 = entityId1;
        this.entityId2 = entityId2;
    }

    public String getEntityId1() {
        return entityId1;
    }

    public String getEntityId2() {
        return entityId2;
    }

    public String getExperimentId() {
        return experimentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (experimentId != null ? experimentId.hashCode() : 0);
        hash += (entityId1 != null ? entityId1.hashCode() : 0);
        hash += (entityId2 != null ? entityId2.hashCode() : 0);

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (object instanceof EntitiesSimilarityId) {
            EntitiesSimilarityId grantsSimilarityId = (EntitiesSimilarityId) object;
            return grantsSimilarityId.experimentId.equals(this.experimentId)
                    && grantsSimilarityId.entityId1.equals(this.entityId1)
                    && grantsSimilarityId.entityId2.equals(this.entityId2);
        }
        return false;
    }
}
