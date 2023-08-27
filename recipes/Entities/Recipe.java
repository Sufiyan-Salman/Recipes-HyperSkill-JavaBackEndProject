package recipes.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue
    @JsonIgnore
    private int id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    @NotBlank
    private String category;

    private LocalDateTime date;

    @JsonIgnore
    private String authorEmail;


    @NotEmpty @Size(min = 1)
    @ElementCollection() //jo esi vallues bhi hon jinka collection rkhna ho aur wo zati tor pe khud enities na hon to unhen ese rkha jata h , wrna agar wo khud b enitties hun to one to many ya deegar ese triqe kaar use kie jate hen
    private List<String> ingredients;

    @NotEmpty
    @ElementCollection()
    private List<String> directions;
//json serialization me koi proxy error arahra tha , plus not found bhi show nai horha tha , baad me ye pta chala k service me jo repo ka getById method h, wo sahi kaam nai krrha tha
    //dusra solmene ye nikala tha k alag se getter setter likhdia
    //ek aur sol ye k @jsonINclude laga dia har field me
}
