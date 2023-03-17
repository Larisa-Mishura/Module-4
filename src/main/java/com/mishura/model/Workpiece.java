package com.mishura.model;

import com.mishura.util.FuelProducer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Workpiece {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private LocalDateTime created;

    @Column(name = "produced_fuel")
    private int producedFuel;

    private int spentFuel;

    private int countOfBrokenChips;

    private long seconds;

    @Transient
    private final Object MONITOR = new Object();

    @Transient
    private int pointsOfBaseConstruction;

    @Transient
    boolean isReady;

    @Transient
    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a");


    @PrePersist
    public void prePersist() {
        if (created == null) {
            created = LocalDateTime.now();
        }
    }
}


