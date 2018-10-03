package model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drug")
public class Drug {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 500)
    private String drugName;

    @OneToMany(mappedBy = "drug", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private List<PrescribedDrug> prescribedDrugs = new ArrayList<>();

    public Drug() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    @JsonIgnore
    public List<PrescribedDrug> getPrescribedDrugs() {
        return prescribedDrugs;
    }

    @JsonProperty("prescribedDrugs")
    public void setPrescribedDrugs(List<PrescribedDrug> prescribedDrugs) {
        this.prescribedDrugs = prescribedDrugs;
    }

}
