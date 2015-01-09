/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hmetaxa
 */
@Entity
@IdClass(EntitiesSimilarityId.class)
@Table(name = "entitysimilarityview")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entitysimilarityview.findAll", query = "SELECT e FROM Entitysimilarityview e"),
    @NamedQuery(name = "Entitysimilarityview.findByEntityId1", query = "SELECT e FROM Entitysimilarityview e WHERE e.entityId1 = :entityId1"),
    @NamedQuery(name = "Entitysimilarityview.findByEntityId2", query = "SELECT e FROM Entitysimilarityview e WHERE e.entityId2 = :entityId2"),
    @NamedQuery(name = "Entitysimilarityview.findBySimilarity", query = "SELECT e FROM Entitysimilarityview e WHERE e.similarity = :similarity"),
    @NamedQuery(name = "Entitysimilarityview.findByExperimentId", query = "SELECT e FROM Entitysimilarityview e WHERE e.experimentId = :experimentId"),
    @NamedQuery(name = "Entitysimilarityview.findByGrantsCnt1", query = "SELECT e FROM Entitysimilarityview e WHERE e.grantsCnt1 = :grantsCnt1"),
    @NamedQuery(name = "Entitysimilarityview.findByGrantsCnt2", query = "SELECT e FROM Entitysimilarityview e WHERE e.grantsCnt2 = :grantsCnt2"),
    @NamedQuery(name = "Entitysimilarityview.findByAcr1", query = "SELECT e FROM Entitysimilarityview e WHERE e.acr1 = :acr1"),
    @NamedQuery(name = "Entitysimilarityview.findByAcr2", query = "SELECT e FROM Entitysimilarityview e WHERE e.acr2 = :acr2"),
    @NamedQuery(name = "Entitysimilarityview.findByGr1Category3", query = "SELECT e FROM Entitysimilarityview e WHERE e.gr1Category3 = :gr1Category3"),
    @NamedQuery(name = "Entitysimilarityview.findByGr2Category3", query = "SELECT e FROM Entitysimilarityview e WHERE e.gr2Category3 = :gr2Category3"),
    @NamedQuery(name = "Entitysimilarityview.findByGr1Category3Descr", query = "SELECT e FROM Entitysimilarityview e WHERE e.gr1Category3Descr = :gr1Category3Descr"),
    @NamedQuery(name = "Entitysimilarityview.findByGr2Category3Descr", query = "SELECT e FROM Entitysimilarityview e WHERE e.gr2Category3Descr = :gr2Category3Descr"),
    @NamedQuery(name = "Entitysimilarityview.findByGr1Category2", query = "SELECT e FROM Entitysimilarityview e WHERE e.gr1Category2 = :gr1Category2"),
    @NamedQuery(name = "Entitysimilarityview.findByGr2Category2", query = "SELECT e FROM Entitysimilarityview e WHERE e.gr2Category2 = :gr2Category2"),
    @NamedQuery(name = "Entitysimilarityview.findByGr1Category1", query = "SELECT e FROM Entitysimilarityview e WHERE e.gr1Category1 = :gr1Category1"),
    @NamedQuery(name = "Entitysimilarityview.findByGr2Category1", query = "SELECT e FROM Entitysimilarityview e WHERE e.gr2Category1 = :gr2Category1"),
    @NamedQuery(name = "Entitysimilarityview.findByGr1Category0", query = "SELECT e FROM Entitysimilarityview e WHERE e.gr1Category0 = :gr1Category0"),
    @NamedQuery(name = "Entitysimilarityview.findSimilar", query = "SELECT e FROM Entitysimilarityview e WHERE e.experimentId = :experimentId and  e.similarity = :similarity"),
    @NamedQuery(name = "Entitysimilarityview.findByGr2Category0", query = "SELECT e FROM Entitysimilarityview e WHERE e.gr2Category0 = :gr2Category0")})
public class Entitysimilarityview implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Size(max = 2000000000)
    @Column(name = "EntityId1")
    private String entityId1;
    @Id
    @Size(max = 2000000000)
    @Column(name = "EntityId2")
    private String entityId2;
    @Size(max = 2000000000)
    @Column(name = "Similarity")
    private String similarity;
    @Id
    @Size(max = 2000000000)
    @Column(name = "ExperimentId")
    private String experimentId;
    @Size(max = 2000000000)
    @Column(name = "grantsCnt1")
    private String grantsCnt1;
    @Size(max = 2000000000)
    @Column(name = "grantsCnt2")
    private String grantsCnt2;
    @Size(max = 2000000000)
    @Column(name = "Acr1")
    private String acr1;
    @Size(max = 2000000000)
    @Column(name = "Acr2")
    private String acr2;
    @Size(max = 2000000000)
    @Column(name = "Gr1_Category3")
    private String gr1Category3;
    @Size(max = 2000000000)
    @Column(name = "Gr2_Category3")
    private String gr2Category3;
    @Size(max = 2000000000)
    @Column(name = "Gr1_Category3Descr")
    private String gr1Category3Descr;
    @Size(max = 2000000000)
    @Column(name = "Gr2_Category3Descr")
    private String gr2Category3Descr;
    @Size(max = 2000000000)
    @Column(name = "Gr1_Category2")
    private String gr1Category2;
    @Size(max = 2000000000)
    @Column(name = "Gr2_Category2")
    private String gr2Category2;
    @Size(max = 2000000000)
    @Column(name = "Gr1_Category1")
    private String gr1Category1;
    @Size(max = 2000000000)
    @Column(name = "Gr2_Category1")
    private String gr2Category1;
    @Size(max = 2000000000)
    @Column(name = "Gr1_Category0")
    private String gr1Category0;
    @Size(max = 2000000000)
    @Column(name = "Gr2_Category0")
    private String gr2Category0;

    public Entitysimilarityview() {
    }

    public String getEntityId1() {
        return entityId1;
    }

    public void setEntityId1(String entityId1) {
        this.entityId1 = entityId1;
    }

    public String getEntityId2() {
        return entityId2;
    }

    public void setEntityId2(String entityId2) {
        this.entityId2 = entityId2;
    }

    public String getSimilarity() {
        return similarity;
    }

    public void setSimilarity(String similarity) {
        this.similarity = similarity;
    }

    public String getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(String experimentId) {
        this.experimentId = experimentId;
    }

    public String getGrantsCnt1() {
        return grantsCnt1;
    }

    public void setGrantsCnt1(String grantsCnt1) {
        this.grantsCnt1 = grantsCnt1;
    }

    public String getGrantsCnt2() {
        return grantsCnt2;
    }

    public void setGrantsCnt2(String grantsCnt2) {
        this.grantsCnt2 = grantsCnt2;
    }

    public String getAcr1() {
        return acr1;
    }

    public void setAcr1(String acr1) {
        this.acr1 = acr1;
    }

    public String getAcr2() {
        return acr2;
    }

    public void setAcr2(String acr2) {
        this.acr2 = acr2;
    }

    public String getGr1Category3() {
        return gr1Category3;
    }

    public void setGr1Category3(String gr1Category3) {
        this.gr1Category3 = gr1Category3;
    }

    public String getGr2Category3() {
        return gr2Category3;
    }

    public void setGr2Category3(String gr2Category3) {
        this.gr2Category3 = gr2Category3;
    }

    public String getGr1Category3Descr() {
        return gr1Category3Descr;
    }

    public void setGr1Category3Descr(String gr1Category3Descr) {
        this.gr1Category3Descr = gr1Category3Descr;
    }

    public String getGr2Category3Descr() {
        return gr2Category3Descr;
    }

    public void setGr2Category3Descr(String gr2Category3Descr) {
        this.gr2Category3Descr = gr2Category3Descr;
    }

    public String getGr1Category2() {
        return gr1Category2;
    }

    public void setGr1Category2(String gr1Category2) {
        this.gr1Category2 = gr1Category2;
    }

    public String getGr2Category2() {
        return gr2Category2;
    }

    public void setGr2Category2(String gr2Category2) {
        this.gr2Category2 = gr2Category2;
    }

    public String getGr1Category1() {
        return gr1Category1;
    }

    public void setGr1Category1(String gr1Category1) {
        this.gr1Category1 = gr1Category1;
    }

    public String getGr2Category1() {
        return gr2Category1;
    }

    public void setGr2Category1(String gr2Category1) {
        this.gr2Category1 = gr2Category1;
    }

    public String getGr1Category0() {
        return gr1Category0;
    }

    public void setGr1Category0(String gr1Category0) {
        this.gr1Category0 = gr1Category0;
    }

    public String getGr2Category0() {
        return gr2Category0;
    }

    public void setGr2Category0(String gr2Category0) {
        this.gr2Category0 = gr2Category0;
    }
    
    @Override
    public String toString() {
        return "Entities.EntitiesSimilarityView[ experimentId=" + experimentId + " entityId1=" + entityId1 + " entityId2=" + entityId2 + " ]";
    }
}
