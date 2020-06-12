/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package el_Archive.archiveList;


import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author gharbi abdelillah
 */
public class viewArchiveList {
    
    SimpleStringProperty column_الملف= new SimpleStringProperty();
    SimpleStringProperty column_المخاطب= new SimpleStringProperty();
    SimpleStringProperty column_المطلوب= new SimpleStringProperty();
    SimpleStringProperty column_الجلسة= new SimpleStringProperty();
    SimpleStringProperty column_نوع_المحضر= new SimpleStringProperty();
    SimpleStringProperty column_رقم_العام = new SimpleStringProperty();

    public String getColumn_رقم_العام() {
        return column_رقم_العام.get();
    }

    public void setColumn_رقم_العام(String column_رقم_العام) {
        this.column_رقم_العام .set(column_رقم_العام);
    }

    public String getColumn_نوع_المحضر() {
        return column_نوع_المحضر.get();
    }

    public void setColumn_نوع_المحضر(String column_نوع_المحضر) {
        this.column_نوع_المحضر.set(column_نوع_المحضر);
    }

    public String getColumn_الملف() {
        return column_الملف.get();
    }

    public void setColumn_الملف(String column_الملف) {
        this.column_الملف.set(column_الملف);
    }


    public String getColumn_المخاطب() {
        return column_المخاطب.get();
    }

    public void setColumn_المخاطب(String column_المخاطب) {
        this.column_المخاطب .set(column_المخاطب);
    }

    public String getColumn_المطلوب() {
        return column_المطلوب.get();
    }

    public void setColumn_المطلوب(String column_المطلوب) {
        this.column_المطلوب .set(column_المطلوب);
    }

    

    public String getColumn_الجلسة() {
        return column_الجلسة.get();
    }

    public void setColumn_الجلسة(String column_الجلسة) {
        this.column_الجلسة .set(column_الجلسة);
    }
    
}
