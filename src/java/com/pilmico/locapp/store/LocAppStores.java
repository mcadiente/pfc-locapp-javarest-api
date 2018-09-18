/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pilmico.locapp.store;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MCadiente
 */
@Entity
@Table(name = "pilmico_customers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocAppStores.findAll", query = "SELECT p FROM LocAppStores p")
    , @NamedQuery(name = "LocAppStores.findById", query = "SELECT p FROM LocAppStores p WHERE p.id = :id")
    , @NamedQuery(name = "LocAppStores.findBySiteId", query = "SELECT p FROM LocAppStores p WHERE p.siteId = :siteId")
    , @NamedQuery(name = "LocAppStores.findByRegionId", query = "SELECT p FROM LocAppStores p WHERE p.regionId = :regionId")
    , @NamedQuery(name = "LocAppStores.findByProvinceId", query = "SELECT p FROM LocAppStores p WHERE p.provinceId = :provinceId")
    , @NamedQuery(name = "LocAppStores.findByTownId", query = "SELECT p FROM LocAppStores p WHERE p.townId = :townId")
    , @NamedQuery(name = "LocAppStores.findByStoreName", query = "SELECT p FROM LocAppStores p WHERE p.storeName = :storeName")
    , @NamedQuery(name = "LocAppStores.findByStoreAnniversary", query = "SELECT p FROM LocAppStores p WHERE p.storeAnniversary = :storeAnniversary")
    , @NamedQuery(name = "LocAppStores.findByStoreContactNumber", query = "SELECT p FROM LocAppStores p WHERE p.storeContactNumber = :storeContactNumber")
    , @NamedQuery(name = "LocAppStores.findByLatitude", query = "SELECT p FROM LocAppStores p WHERE p.latitude = :latitude")
    , @NamedQuery(name = "LocAppStores.findByLongitude", query = "SELECT p FROM LocAppStores p WHERE p.longitude = :longitude")
    , @NamedQuery(name = "LocAppStores.findByExclusivity", query = "SELECT p FROM LocAppStores p WHERE p.exclusivity = :exclusivity")
    , @NamedQuery(name = "LocAppStores.findByOwnerFirstName", query = "SELECT p FROM LocAppStores p WHERE p.ownerFirstName = :ownerFirstName")
    , @NamedQuery(name = "LocAppStores.findByOwnerLastName", query = "SELECT p FROM LocAppStores p WHERE p.ownerLastName = :ownerLastName")
    , @NamedQuery(name = "LocAppStores.findByOwnerBirthday", query = "SELECT p FROM LocAppStores p WHERE p.ownerBirthday = :ownerBirthday")
    , @NamedQuery(name = "LocAppStores.findByOwnerMobileNumber", query = "SELECT p FROM LocAppStores p WHERE p.ownerMobileNumber = :ownerMobileNumber")
    , @NamedQuery(name = "LocAppStores.findByStatus", query = "SELECT p FROM LocAppStores p WHERE p.status = :status")
    , @NamedQuery(name = "LocAppStores.findByCreatedAt", query = "SELECT p FROM LocAppStores p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "LocAppStores.findByUpdatedAt", query = "SELECT p FROM LocAppStores p WHERE p.updatedAt = :updatedAt")
    , @NamedQuery(name = "LocAppStores.findByConsentUrl", query = "SELECT p FROM LocAppStores p WHERE p.consentUrl = :consentUrl")
    , @NamedQuery(name = "LocAppStores.findByOwnerEmailAddress", query = "SELECT p FROM LocAppStores p WHERE p.ownerEmailAddress = :ownerEmailAddress")
    , @NamedQuery(name = "LocAppStores.findByDateSigned", query = "SELECT p FROM LocAppStores p WHERE p.dateSigned = :dateSigned")
    , @NamedQuery(name = "LocAppStores.findByIntegrationStatus", query = "SELECT p FROM LocAppStores p WHERE p.integrationStatus = :integrationStatus")
    , @NamedQuery(name = "LocAppStores.findByIntegrationUpdateDate", query = "SELECT p FROM LocAppStores p WHERE p.integrationUpdateDate = :integrationUpdateDate")
    , @NamedQuery(name = "LocAppStores.findByCrmId", query = "SELECT p FROM LocAppStores p WHERE p.crmId = :crmId")})
public class LocAppStores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "site_id")
    private Integer siteId;
    @Column(name = "region_id")
    private Integer regionId;
    @Column(name = "province_id")
    private Integer provinceId;
    @Column(name = "town_id")
    private Integer townId;
    @Basic(optional = false)
    @Column(name = "store_name")
    private String storeName;
    @Column(name = "store_anniversary")
    @Temporal(TemporalType.TIMESTAMP)
    private Date storeAnniversary;
    @Column(name = "store_contact_number")
    private String storeContactNumber;
    @Lob
    @Column(name = "store_address")
    private String storeAddress;
    @Basic(optional = false)
    @Column(name = "latitude")
    private double latitude;
    @Basic(optional = false)
    @Column(name = "longitude")
    private double longitude;
    @Basic(optional = false)
    @Column(name = "exclusivity")
    private String exclusivity;
    @Column(name = "owner_first_name")
    private String ownerFirstName;
    @Column(name = "owner_last_name")
    private String ownerLastName;
    @Column(name = "owner_birthday")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ownerBirthday;
    @Column(name = "owner_mobile_number")
    private String ownerMobileNumber;
    @Lob
    @Column(name = "photos")
    private String photos;
    @Lob
    @Column(name = "others")
    private String others;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "consent_url")
    private String consentUrl;
    @Column(name = "owner_email_address")
    private String ownerEmailAddress;
    @Column(name = "date_signed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSigned;
    @Column(name = "integration_status")
    private String integrationStatus;
    @Column(name = "integration_update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date integrationUpdateDate;
    @Column(name = "crm_id")
    private String crmId;

    public LocAppStores() {
    }

    public LocAppStores(Integer id) {
        this.id = id;
    }

    public LocAppStores(Integer id, String storeName, double latitude, double longitude, String exclusivity, boolean status) {
        this.id = id;
        this.storeName = storeName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.exclusivity = exclusivity;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getTownId() {
        return townId;
    }

    public void setTownId(Integer townId) {
        this.townId = townId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Date getStoreAnniversary() {
        return storeAnniversary;
    }

    public void setStoreAnniversary(Date storeAnniversary) {
        this.storeAnniversary = storeAnniversary;
    }

    public String getStoreContactNumber() {
        return storeContactNumber;
    }

    public void setStoreContactNumber(String storeContactNumber) {
        this.storeContactNumber = storeContactNumber;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getExclusivity() {
        return exclusivity;
    }

    public void setExclusivity(String exclusivity) {
        this.exclusivity = exclusivity;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public Date getOwnerBirthday() {
        return ownerBirthday;
    }

    public void setOwnerBirthday(Date ownerBirthday) {
        this.ownerBirthday = ownerBirthday;
    }

    public String getOwnerMobileNumber() {
        return ownerMobileNumber;
    }

    public void setOwnerMobileNumber(String ownerMobileNumber) {
        this.ownerMobileNumber = ownerMobileNumber;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getConsentUrl() {
        return consentUrl;
    }

    public void setConsentUrl(String consentUrl) {
        this.consentUrl = consentUrl;
    }

    public String getOwnerEmailAddress() {
        return ownerEmailAddress;
    }

    public void setOwnerEmailAddress(String ownerEmailAddress) {
        this.ownerEmailAddress = ownerEmailAddress;
    }

    public Date getDateSigned() {
        return dateSigned;
    }

    public void setDateSigned(Date dateSigned) {
        this.dateSigned = dateSigned;
    }

    public String getIntegrationStatus() {
        return integrationStatus;
    }

    public void setIntegrationStatus(String integrationStatus) {
        this.integrationStatus = integrationStatus;
    }

    public Date getIntegrationUpdateDate() {
        return integrationUpdateDate;
    }

    public void setIntegrationUpdateDate(Date integrationUpdateDate) {
        this.integrationUpdateDate = integrationUpdateDate;
    }

    public String getCrmId() {
        return crmId;
    }

    public void setCrmId(String crmId) {
        this.crmId = crmId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocAppStores)) {
            return false;
        }
        LocAppStores other = (LocAppStores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pilmico.locapp.customer.LocAppStores[ id=" + id + " ]";
    }
    
}
