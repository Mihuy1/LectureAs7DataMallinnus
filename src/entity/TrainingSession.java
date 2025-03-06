package entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
public class TrainingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    private String location;

    private Integer duration;

    @OneToMany(mappedBy = "trainingSession", cascade = CascadeType.ALL)
    private Set<Attendance> attendances = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public TrainingSession() {

    }

    public TrainingSession(LocalDateTime date, String location, Integer duration) {
        this.date = date;
        this.location = location;
        this.duration = duration;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PostUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Set<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(Set<Attendance> attendances) {
        this.attendances = attendances;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    @Override
    public String toString() {
        return "TrainingSession{" +
                "id=" + id +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", duration=" + duration +
                '}';
    }
}
