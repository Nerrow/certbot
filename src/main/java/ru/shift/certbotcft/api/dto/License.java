package ru.shift.certbotcft.api.dto;

import java.time.LocalDate;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "license", schema = "public")
public class License {// rename
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(name = "start_date")
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date")
    private LocalDate endDate;

    @NotNull
    @Column(name = "certificate_type")
    private String certificateType;

    @NotNull
    @Column(name = "person_type")
    private String personType;

    @NotNull
    @Column(name = "product_type")
    private String productType;

    @NotNull
    @Column(name = "product_version")
    private String productVersion;

    @NotNull
    @Column(name = "certificate")
    private String certificate;


}
