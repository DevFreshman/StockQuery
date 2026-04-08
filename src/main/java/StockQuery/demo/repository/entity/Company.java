package stockquery.demo.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "companies")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Company {

    @Id
    @Column(name = "ticker", nullable = false)
    private String ticker;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "exchange")
    private String exchange;

    @Column(name = "sector")
    private String sector;

    @Column(name = "industry")
    private String industry;
}
