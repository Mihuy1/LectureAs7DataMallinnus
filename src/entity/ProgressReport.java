package entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ProgressReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime reportDate;

    private String achievements;

    private String areasForImprovement;

    @Version
    private int version;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public ProgressReport() {

    }

    public ProgressReport(LocalDateTime reportDate, String achievements, String areasForImprovement) {
        this.reportDate = reportDate;
        this.achievements = achievements;
        this.areasForImprovement = areasForImprovement;
    }

    public int getVersion() {
        return version;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public String getAreasForImprovement() {
        return areasForImprovement;
    }

    public void setAreasForImprovement(String areasForImprovement) {
        this.areasForImprovement = areasForImprovement;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "ProgressReport{" +
                "id=" + id +
                ", reportDate=" + reportDate +
                ", achievements='" + achievements + '\'' +
                ", areasForImprovement='" + areasForImprovement + '\'' +
                '}';
    }


}
