package model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "disease")
public class Disease {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String typeCode;

    @Column(length = 500)
    private String typeName;

    @Column(length = 500)
    private String diseaseName;

    @Column
    private String icd;

    @OneToMany(mappedBy = "disease", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DiagnosedDisease> diagnosedDiseases = new ArrayList<>();

    public Disease() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getIcd() {
        return icd;
    }

    public void setIcd(String icd) {
        this.icd = icd;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @JsonIgnore
    public List<DiagnosedDisease> getDiagnosedDiseases() {
        return diagnosedDiseases;
    }

    @JsonProperty("diagnosedDiseases")
    public void setDiagnosedDiseases(List<DiagnosedDisease> diagnosedDiseases) {
        this.diagnosedDiseases = diagnosedDiseases;
    }


}
