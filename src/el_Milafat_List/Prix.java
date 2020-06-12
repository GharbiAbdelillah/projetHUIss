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
public class Prix {
    SimpleStringProperty pay = new SimpleStringProperty (); 
    SimpleStringProperty advance_payment = new SimpleStringProperty (); 
    SimpleStringProperty Reglement = new SimpleStringProperty (); 
    SimpleStringProperty totale = new SimpleStringProperty (); 

    public String getPay() {
        return pay.get();
    }

    public String getAdvance_payment() {
        return advance_payment.get();
    }

    public String getReglement() {
        return Reglement.get();
    }

    public String getTotale() {
        return totale.get();
    }

    public void setPay(String pay) {
        this.pay.set(pay);
    }

    public void setAdvance_payment(String advance_payment) {
        this.advance_payment.set(advance_payment);
    }

    public void setReglement(String Reglement) {
        this.Reglement.set(Reglement);
    }

    public void setTotale(String totale) {
        this.totale.set(totale);
    }
    
}
