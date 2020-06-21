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
public class mahadirList {

    SimpleStringProperty column_المحاضر= new SimpleStringProperty();

    public String getColumn_المحاضر() {
        return column_المحاضر.get();
    }

    public void setColumn_المحاضر(String column_المحاضر) {
        this.column_المحاضر .set(column_المحاضر);
    }
}
