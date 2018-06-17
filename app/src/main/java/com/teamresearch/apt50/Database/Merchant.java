package com.teamresearch.apt50.Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Merchant")
public class Merchant {

    @DatabaseField(columnName = "id",generatedId = true)
    public int id;
    @DatabaseField(columnName = "name")
    public String name;
    @DatabaseField(columnName = "address")
    public String address;
    @DatabaseField(columnName = "city")
    public String city;
    @DatabaseField(columnName = "state")
    public String state;
    @DatabaseField(columnName = "zipcode")
    public String zipcode;
    @DatabaseField(columnName = "phone")
    public String phone;
    @DatabaseField(columnName = "language")
    public int language;
    @DatabaseField(columnName = "currency")
    public int currency;

    public int getId()                           { return this.id;}
    public void setId(int id)                    { this.id=id;}
    public String getName()                      { return this.name;}
    public void setName(String name)             { this.name=name;}
    public String getAddress()                   { return this.address;}
    public void setAddress(String address)       { this.address=address;}
    public String getCity()                      { return this.city;}
    public void setCity(String city)             { this.city=city;}
    public String  getState()                    { return this.state;}
    public void setState(String state)           { this.state=state;}
    public String getZipcode()                   { return this.zipcode;}
    public void setZipcode(String zipcode)       { this.zipcode=zipcode;}
    public String getPhone()                     { return this.phone;}
    public void setPhone(String phone)           { this.phone=phone;}
    public int getLanguage()                     { return this.language;}
    public void setLanguage(int language)        { this.language=language;}
    public int getCurrency()                     { return this.currency;}
    public void setCurrency(int currency)        { this.currency=currency;}
}
