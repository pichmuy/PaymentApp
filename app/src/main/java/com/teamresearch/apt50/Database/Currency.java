package com.teamresearch.apt50.Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Currency")
public class Currency {
    @DatabaseField(columnName = "id",generatedId = true)
    public int id;
    @DatabaseField(columnName = "description")
    public String description;

    public int getId()                                       { return this.id;}
    public void setId(int id)                                { this.id=id;}
    public String getDescription()                           { return this.description;}
    public void setDescription(String description)           { this.description=description;}


}
