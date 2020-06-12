/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package el_Milafat_List;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author gharbi abdelillah
 */
public class Milafet {
    private SimpleStringProperty column_رقم_الملف= new SimpleStringProperty();
    private SimpleStringProperty column_تاريخ_ايداع_الملف= new SimpleStringProperty();
    private SimpleStringProperty column_نوع_الملف= new SimpleStringProperty();
    private SimpleStringProperty column_طبيعة_الملف= new SimpleStringProperty();
    private SimpleStringProperty  column_تاريخ_الإستدعاء= new SimpleStringProperty();
    private SimpleStringProperty column_تاريخ_الإنجاز= new SimpleStringProperty();
    private SimpleStringProperty column_الطالب= new SimpleStringProperty();
    private SimpleStringProperty column_المطلوب= new SimpleStringProperty();
    private SimpleStringProperty column_رقم_العام= new SimpleStringProperty();

    public String getColumn_رقم_العام() {
        return column_رقم_العام.get();
    }

    public void setColumn_رقم_العام(String column_رقم_العام) {
        this.column_رقم_العام .set(column_رقم_العام);
    }


   
    public void setColumn_رقم_الملف(String column_رقم_الملف) {
        this.column_رقم_الملف.set(column_رقم_الملف) ;
    }

    public void setColumn_تاريخ_ايداع_الملف(String column_تاريخ_ايداع_الملف) {
        this.column_تاريخ_ايداع_الملف.set(column_تاريخ_ايداع_الملف);
    }

    public void setColumn_نوع_الملف(String column_نوع_الملف) {
        this.column_نوع_الملف.set(column_نوع_الملف);
    }

    public void setColumn_طبيعة_الملف(String column_طبيعة_الملف) {
        this.column_طبيعة_الملف.set(column_طبيعة_الملف);
    }

    public void setColumn_تاريخ_الإستدعاء(String column_تاريخ_الإستدعاء) {
        this.column_تاريخ_الإستدعاء.set(column_تاريخ_الإستدعاء);
    }

    public void setColumn_تاريخ_الإنجاز(String column_تاريخ_الإنجاز) {
        this.column_تاريخ_الإنجاز.set(column_تاريخ_الإنجاز);
    }

    public void setColumn_الطالب(String column_الطالب) {
        this.column_الطالب.set(column_الطالب);
    }

    public void setColumn_المطلوب(String column_المطلوب) {
        this.column_المطلوب.set(column_المطلوب);
    }

    
    
    
    public String getColumn_رقم_الملف() {
        return column_رقم_الملف.get();
    }

    public String getColumn_تاريخ_ايداع_الملف() {
        return column_تاريخ_ايداع_الملف.get();
    }

    public String getColumn_نوع_الملف() {
        return column_نوع_الملف.get();
    }

    public String getColumn_طبيعة_الملف() {
        return column_طبيعة_الملف.get();
    }

    public String getColumn_تاريخ_الإستدعاء() {
        return column_تاريخ_الإستدعاء.get();
    }

    public String getColumn_تاريخ_الإنجاز() {
        return column_تاريخ_الإنجاز.get();
    }

    public String getColumn_الطالب() {
        return column_الطالب.get();
    }

    public String getColumn_المطلوب() {
        return column_المطلوب.get();
    }
    
   
    
}
