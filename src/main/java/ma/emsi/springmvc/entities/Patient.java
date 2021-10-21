package ma.emsi.springmvc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
@Entity @Data @NoArgsConstructor @AllArgsConstructor
@ToString
public class Patient {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @NotNull
    @Size(min = 5,max=15)
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;
    private boolean malade;
    @DecimalMin("4")
    private int score;
}
