package com.efubtoy.team1.domain.record.domain;

import com.efubtoy.team1.global.customEnum.State;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Used_Record")
@Getter
@NoArgsConstructor
public class UsedRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "used_record_id")
    private Long usedRecordId;

    private int price;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id", updatable = false)
    private Record record;
}
