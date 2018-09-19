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
@Table(name = "v_all_active_stores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocAppActiveStores.findAll", query = "SELECT v FROM LocAppActiveStores v")
    , @NamedQuery(name = "LocAppActiveStores.findById", query = "SELECT v FROM LocAppActiveStores v WHERE v.id = :id")
    , @NamedQuery(name = "LocAppActiveStores.findByStoreName", query = "SELECT v FROM LocAppActiveStores v WHERE v.storeName = :storeName")
    , @NamedQuery(name = "LocAppActiveStores.findByStoreContact", query = "SELECT v FROM LocAppActiveStores v WHERE v.storeContact = :storeContact")
    , @NamedQuery(name = "LocAppActiveStores.findBySite", query = "SELECT v FROM LocAppActiveStores v WHERE v.site = :site")
    , @NamedQuery(name = "LocAppActiveStores.findByRegion", query = "SELECT v FROM LocAppActiveStores v WHERE v.region = :region")
    , @NamedQuery(name = "LocAppActiveStores.findByProvince", query = "SELECT v FROM LocAppActiveStores v WHERE v.province = :province")
    , @NamedQuery(name = "LocAppActiveStores.findByTown", query = "SELECT v FROM LocAppActiveStores v WHERE v.town = :town")
    , @NamedQuery(name = "LocAppActiveStores.findByDivision", query = "SELECT v FROM LocAppActiveStores v WHERE v.division = :division")
    , @NamedQuery(name = "LocAppActiveStores.findByExclusivity", query = "SELECT v FROM LocAppActiveStores v WHERE v.exclusivity = :exclusivity")
    , @NamedQuery(name = "LocAppActiveStores.findByStoreType", query = "SELECT v FROM LocAppActiveStores v WHERE v.storeType = :storeType")
    , @NamedQuery(name = "LocAppActiveStores.findByLongitude", query = "SELECT v FROM LocAppActiveStores v WHERE v.longitude = :longitude")
    , @NamedQuery(name = "LocAppActiveStores.findByLatitude", query = "SELECT v FROM LocAppActiveStores v WHERE v.latitude = :latitude")
    , @NamedQuery(name = "LocAppActiveStores.findByAddedDate", query = "SELECT v FROM LocAppActiveStores v WHERE v.addedDate = :addedDate")
    , @NamedQuery(name = "LocAppActiveStores.findByUpdatedDate", query = "SELECT v FROM LocAppActiveStores v WHERE v.updatedDate = :updatedDate")
    , @NamedQuery(name = "LocAppActiveStores.findByOwnerFirstName", query = "SELECT v FROM LocAppActiveStores v WHERE v.ownerFirstName = :ownerFirstName")
    , @NamedQuery(name = "LocAppActiveStores.findByOwnerLastName", query = "SELECT v FROM LocAppActiveStores v WHERE v.ownerLastName = :ownerLastName")
    , @NamedQuery(name = "LocAppActiveStores.findByOwnerBday", query = "SELECT v FROM LocAppActiveStores v WHERE v.ownerBday = :ownerBday")
    , @NamedQuery(name = "LocAppActiveStores.findByOwnerMobileNumber", query = "SELECT v FROM LocAppActiveStores v WHERE v.ownerMobileNumber = :ownerMobileNumber")
    , @NamedQuery(name = "LocAppActiveStores.findByOwnerEmailAdd", query = "SELECT v FROM LocAppActiveStores v WHERE v.ownerEmailAdd = :ownerEmailAdd")
   , @NamedQuery(name = "LocAppActiveStores.findByIntegrationStatus", query = "SELECT v FROM LocAppActiveStores v WHERE v.integrationStatus = :integrationStatus")
    , @NamedQuery(name = "LocAppActiveStores.findByIntegrationUpdateDate", query = "SELECT v FROM LocAppActiveStores v WHERE v.integrationUpdateDate = :integrationUpdateDate")
    , @NamedQuery(name = "LocAppActiveStores.findByCrmId", query = "SELECT v FROM LocAppActiveStores v WHERE v.crmId = :crmId")})
public class LocAppActiveStores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "ID")
    @Id
    private int id;
    @Basic(optional = false)
    @Column(name = "StoreName")
    private String storeName;
    @Lob
    @Column(name = "StoreAddress")
    private String storeAddress;
    @Column(name = "StoreContact")
    private String storeContact;
    @Basic(optional = false)
    @Column(name = "Site")
    private String site;
    @Basic(optional = false)
    @Column(name = "Region")
    private String region;
    @Basic(optional = false)
    @Column(name = "Province")
    private String province;
    @Basic(optional = false)
    @Column(name = "Town")
    private String town;
    @Basic(optional = false)
    @Column(name = "Division")
    private String division;
    @Basic(optional = false)
    @Column(name = "Exclusivity")
    private String exclusivity;
    @Basic(optional = false)
    @Column(name = "StoreType")
    private String storeType;
    @Basic(optional = false)
    @Column(name = "Longitude")
    private double longitude;
    @Basic(optional = false)
    @Column(name = "Latitude")
    private double latitude;
    @Column(name = "AddedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;
    @Column(name = "UpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "OwnerFirstName")
    private String ownerFirstName;
    @Column(name = "OwnerLastName")
    private String ownerLastName;
    @Column(name = "OwnerBday")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ownerBday;
    @Column(name = "OwnerMobileNumber")
    private String ownerMobileNumber;
    @Column(name = "OwnerEmailAdd")
    private String ownerEmailAdd;
    @Column(name = "IntegrationStatus")
    private String integrationStatus;
    @Column(name = "IntegrationUpdateDate")
    @Temporal(TemporalType.TIMESTAMP)    
    private Date integrationUpdateDate;
    @Column(name = "CRMID")
    private String crmId;

    public LocAppActiveStores() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreContact() {
        return storeContact;
    }

    public void setStoreContact(String storeContact) {
        this.storeContact = storeContact;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getExclusivity() {
        return exclusivity;
    }

    public void setExclusivity(String exclusivity) {
        this.exclusivity = exclusivity;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
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

    public Date getOwnerBday() {
        return ownerBday;
    }

    public void setOwnerBday(Date ownerBday) {
        this.ownerBday = ownerBday;
    }

    public String getOwnerMobileNumber() {
        return ownerMobileNumber;
    }

    public void setOwnerMobileNumber(String ownerMobileNumber) {
        this.ownerMobileNumber = ownerMobileNumber;
    }

    public String getOwnerEmailAdd() {
        return ownerEmailAdd;
    }

    public void setOwnerEmailAdd(String ownerEmailAdd) {
        this.ownerEmailAdd = ownerEmailAdd;
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
    
}
