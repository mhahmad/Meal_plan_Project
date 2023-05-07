package org.backendmealplan.backendmealplan.beans;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userInfo")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long infoId;

    private Double height;
    private Double weight;
    private String unit;

    @JsonIgnore
    private LocalDate birthday;

    private Integer activity;
    private String medicalRisk;
    private Boolean isReceiveTreatment;
    private String gender;

    @ManyToMany
    @JoinTable(
            name = "user_goals",
            joinColumns = @JoinColumn(name = "user_info_id"),
            inverseJoinColumns = @JoinColumn(name = "goal_id"))
    Set<Goal> goals;

}
