package cz.siemens.inventory.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Type;

@Data
@Entity
@Table(name = "audit_log")
@ToString
public class AuditLog implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum Category {GENERAL, INVENTORY, REVISION, CALIBRATION}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    private DeviceInternal device;

    @Column(name = "timestamp")
    @Type(type = "timestamp")
    private Timestamp entryDate;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private LoginUserScd editingUser;

    @Column(name = "description")
    private String description;

    public String getEntryDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = dateFormat.format(entryDate);
        return dateStr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
