package com.teamresearch.apt50.Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "APISecurity")
public class APISecurity {
    @DatabaseField(columnName = "id",generatedId = true)
    public int id;
    @DatabaseField(columnName = "token")
    public String token;
    @DatabaseField(columnName = "secret")
    public String secret;

    public int getId()                           { return id;}
    public void setId(int id)                    { this.id=id;}
    public String getToken()                     { return this.token;}
    public void setToken(String token)           { this.token=token;}
    public String getSecret()                    { return this.secret;}
    public void setSecret(String secret)         { this.secret=secret;}
}
