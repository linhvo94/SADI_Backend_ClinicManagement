package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "prescription")
public class Prescription {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
    private VisitLog visitLog;

    @OneToMany(mappedBy = "prescription", cascade = javax.persistence.CascadeType.ALL ,fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    private List<PrescribedDrug> prescribedDrugs = new ArrayList<>();

    public Prescription() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @JsonIgnore
    public VisitLog getVisitLog() {
        return visitLog;
    }

    @JsonProperty("visitLog")
    public void setVisitLog(VisitLog visitLog) {
        this.visitLog = visitLog;
    }

    public List<PrescribedDrug> getPrescribedDrugs() {
        return prescribedDrugs;
    }

    public void setPrescribedDrugs(List<PrescribedDrug> prescribedDrugs) {
        this.prescribedDrugs = prescribedDrugs;
    }


}
