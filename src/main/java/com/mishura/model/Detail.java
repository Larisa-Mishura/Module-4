package com.mishura.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private LocalDateTime created;

    @Transient
    private LocalDateTime start;

    @Column(name = "produced_fuel")
    private int producedFuel;

    @Column(name = "spent_fuel")
    private int spentFuel;

    @Column(name = "count_of_broken_chips")
    private int countOfBrokenChips;

    private int seconds;

    @Transient
    private final Object MONITOR = new Object();

    @Transient
    private int pointsOfBaseConstruction;

    @Transient
    private int pointsOfChip;

    @Transient
    private boolean isReady;

    @PrePersist
    public void prePersist() {
        if (seconds == 0) {
            seconds = (int)Duration.between(start, created).getSeconds();
        }
    }

    @Transient
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a");

    @Override
    public String toString() {
        return  "Item ID - " + id + "\n" +
                "Date - " + FORMATTER.format(created) + "\n" +
                "Spent time - " + seconds + "\n" +
                "Total amount of spent fuel - " + producedFuel + "\n" +
                "Total amount of produced fuel - " + spentFuel + "\n" +
                "Total amount of broken chips - " + countOfBrokenChips;
    }
}