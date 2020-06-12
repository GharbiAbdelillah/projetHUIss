/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package el_Mahadir.List;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author gharbi abdelillah
 */
public class SearchList {
    SimpleStringProperty column_رقم_الملف = new SimpleStringProperty();
    SimpleStringProperty column_نوع_الملف = new SimpleStringProperty();
    SimpleStringProperty column_طبيعة_الملف = new SimpleStringProperty();
    SimpleStringProperty column_الطالب = new SimpleStringProperty();
    SimpleStringProperty column_المطلوب = new SimpleStringProperty();

    public String getColumn_رقم_الملف() {
        return column_رقم_الملف.get();
    }

    public void setColumn_رقم_الملف(String column_رقم_الملف) {
        this.column_رقم_الملف .set(column_رقم_الملف);
    }

    

    public String getColumn_نوع_الملف() {
        return column_نوع_الملف.get();
    }

    public void setColumn_نوع_الملف(String column_نوع_الملف) {
        this.column_نوع_الملف .set(column_نوع_الملف);
    }

    public String getColumn_طبيعة_الملف() {
        return column_طبيعة_الملف.get();
    }

    public void setColumn_طبيعة_الملف(String column_طبيعة_الملف) {
        this.column_طبيعة_الملف .set(column_طبيعة_الملف);
    }

    public String getColumn_الطالب() {
        return column_الطالب.get();
    }

    public void setColumn_الطالب(String column_الطالب) {
        this.column_الطالب .set(column_الطالب);
    }

    public String getColumn_المطلوب() {
        return column_المطلوب.get();
    }

    public void setColumn_المطلوب(String column_المطلوب) {
        this.column_المطلوب .set(column_المطلوب);
    }
}
