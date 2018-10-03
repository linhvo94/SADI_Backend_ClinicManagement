package model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MedicalService")
public class MedicalService {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 1000)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(length = 1000)
    private String procedure;

    @Column(length = 1000)
    private String image;

    @OneToMany(mappedBy = "medicalService", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LabMedical> labMedicals = new ArrayList<>();

    public MedicalService() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    @JsonIgnore
    public List<LabMedical> getLabMedicals() {
        return labMedicals;
    }

    @JsonProperty("labMedicals")
    public void setLabMedicals(List<LabMedical> labMedicals) {
        this.labMedicals = labMedicals;
    }

}
