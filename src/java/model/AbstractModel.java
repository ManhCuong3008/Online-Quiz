
package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class AbstractModel {
    private int id ;
    private Timestamp createDate;
    private String createBy;

    public AbstractModel() {
    }

    public AbstractModel(Timestamp createDate, String createBy) {
        this.createDate = createDate;
        this.createBy = createBy;
    }

    
    public AbstractModel(int id, Timestamp createDate, String createBy) {
        this.id = id;
        this.createDate = createDate;
        this.createBy = createBy;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    
    public String getDateConvertToString() {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        String date = dateFormat1.format(this.createDate);
        return date;
    }
    
}
