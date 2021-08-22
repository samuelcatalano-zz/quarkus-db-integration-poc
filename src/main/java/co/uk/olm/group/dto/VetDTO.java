package co.uk.olm.group.dto;

import co.uk.olm.group.enums.Gender;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VetDTO implements Serializable {

    private Long id;
    private String name;
    private Gender gender;
    private String vetRegister;
    @JsonIgnore
    private List<PetDTO> pets;
    private transient List<Long> petIds;
}
