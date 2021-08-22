package co.uk.olm.group.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Column(name = "created_by")
    @JsonIgnore
    protected String createdBy = "system";

    @Column(name = "created_at")
    @JsonIgnore
    protected Date createdAt = new Date();

    @Column(name = "version")
    @JsonIgnore
    protected String version = "1.0";
}
