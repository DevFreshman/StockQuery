package stockquery.demo.repository.jpa.command;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stockquery.demo.repository.entity.Company;


@Repository
public interface JpaCompaniesCommand extends JpaRepository<Company, String> {

}
