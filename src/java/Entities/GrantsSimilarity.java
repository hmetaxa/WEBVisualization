/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hmetaxa
 */
@Entity
@IdClass(GrantsSimilarityId.class)
@Table(name = "EntitySimilarityView")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrantsSimilarity.findAll", query = "SELECT g FROM GrantsSimilarity g"),
    @NamedQuery(name = "GrantsSimilarity.findByGrantId1", query = "SELECT g FROM GrantsSimilarity g WHERE g.grantId1 = :grantId1"),
    @NamedQuery(name = "GrantsSimilarity.findByGrantId2", query = "SELECT g FROM GrantsSimilarity g WHERE g.grantId2 = :grantId2"),
    @NamedQuery(name = "GrantsSimilarity.findBySimilarity", query = "SELECT g FROM GrantsSimilarity g WHERE g.similarity = :similarity"),
    @NamedQuery(name = "GrantsSimilarity.findSimilar", query = "SELECT g FROM GrantsSimilarity g WHERE g.similarity > :similarity AND g.experimentId = :experimentId"),
    @NamedQuery(name = "GrantsSimilarity.findByExperimentId", query = "SELECT g FROM GrantsSimilarity g WHERE g.experimentId = :experimentId")})
public class GrantsSimilarity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "EntityId1")
    private String grantId1;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "EntityId2")
    private String grantId2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "Similarity")
    private double similarity;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "ExperimentId")
    private String experimentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "Acr1")
    private String acr1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "Acr2")
    private String acr2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "f1")
    private String f1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "f2")
    private String f2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "p1")
    private int p1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "p2")
    private int p2;

    public String getGrantId1() {
        return grantId1;
    }

    public void setGrantId1(String grantId1) {
        this.grantId1 = grantId1;
    }

    public String getGrantId2() {
        return grantId2;
    }

    public void setGrantId2(String grantId2) {
        this.grantId2 = grantId2;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public String getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(String experimentId) {
        this.experimentId = experimentId;
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

    public int getP1() {
        return p1;
    }

    public void setP1(int p1) {
        this.p1 = p1;
    }

    public int getP2() {
        return p2;
    }

    public void setP2(int p2) {
        this.p2 = p2;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }

    @Override
    public String toString() {
        return "Entities.GrantsSimilarity[ experimentId=" + experimentId + " grantId1=" + grantId1 + " grantId2=" + grantId2 + " ]";
    }
}
