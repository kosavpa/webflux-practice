package home.owl.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Cat {
    @Id
    private Long id;
    private String catName;
    private String breed;
    private int age;
    
    public Cat(String catName, String breed, int age) {
        this.catName = catName;
        this.breed = breed;
        this.age = age;
    }    
}
