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
@Table(name = "Grant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grant.findAll", query = "SELECT g FROM Grant g"),
    @NamedQuery(name = "Grant.findByGrantId", query = "SELECT g FROM Grant g WHERE g.grantId = :grantId"),
    @NamedQuery(name = "Grant.findByAcronym", query = "SELECT g FROM Grant g WHERE g.acronym = :acronym"),
    @NamedQuery(name = "Grant.findByFundingLvl0", query = "SELECT g FROM Grant g WHERE g.fundingLvl0 = :fundingLvl0"),
    @NamedQuery(name = "Grant.findByFundingLvl1", query = "SELECT g FROM Grant g WHERE g.fundingLvl1 = :fundingLvl1"),
    @NamedQuery(name = "Grant.findByFundingLvl2", query = "SELECT g FROM Grant g WHERE g.fundingLvl2 = :fundingLvl2"),
    @NamedQuery(name = "Grant.findBySc39", query = "SELECT g FROM Grant g WHERE g.sc39 = :sc39"),
    @NamedQuery(name = "Grant.findByNumber", query = "SELECT g FROM Grant g WHERE g.number = :number")})
public class Grant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "GrantId")
    private String grantId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "acronym")
    private String acronym;
    @Size(max = 2000000000)
    @Column(name = "funding_lvl0")
    private String fundingLvl0;
    @Size(max = 2000000000)
    @Column(name = "funding_lvl1")
    private String fundingLvl1;
    @Size(max = 2000000000)
    @Column(name = "funding_lvl2")
    private String fundingLvl2;
    @Size(max = 2000000000)
    @Column(name = "sc39")
    private String sc39;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number")
    private int number;

    public Grant() {
    }

    public Grant(String grantId) {
        this.grantId = grantId;
    }

    public Grant(String grantId, String acronym, int number) {
        this.grantId = grantId;
        this.acronym = acronym;
        this.number = number;
    }

    public String getGrantId() {
        return grantId;
    }

    public void setGrantId(String grantId) {
        this.grantId = grantId;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getFundingLvl0() {
        return fundingLvl0;
    }

    public void setFundingLvl0(String fundingLvl0) {
        this.fundingLvl0 = fundingLvl0;
    }

    public String getFundingLvl1() {
        return fundingLvl1;
    }

    public void setFundingLvl1(String fundingLvl1) {
        this.fundingLvl1 = fundingLvl1;
    }

    public String getFundingLvl2() {
        return fundingLvl2;
    }

    public void setFundingLvl2(String fundingLvl2) {
        this.fundingLvl2 = fundingLvl2;
    }

    public String getSc39() {
        return sc39;
    }

    public void setSc39(String sc39) {
        this.sc39 = sc39;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grantId != null ? grantId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grant)) {
            return false;
        }
        Grant other = (Grant) object;
        if ((this.grantId == null && other.grantId != null) || (this.grantId != null && !this.grantId.equals(other.grantId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Grant[ grantId=" + grantId + " ]";
    }
    
}
