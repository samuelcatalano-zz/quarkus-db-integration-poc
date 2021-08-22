package co.uk.olm.group.dto;

import co.uk.olm.group.enums.Gender;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDTO {

    private Long id;
    private String name;
    private Gender gender;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private List<PetDTO> pets;
}
