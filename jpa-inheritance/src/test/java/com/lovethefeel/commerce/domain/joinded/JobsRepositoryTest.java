package com.lovethefeel.commerce.domain.joinded;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class JobsRepositoryTest {

    @Autowired
    private JobsRepository jobsRepository;

    @DisplayName("직업 테이블의 개발자를 등록한다.")
    @Test
    void 개발자_생성() {
        final Developer developer = Developer.from("Lee", 5000, "Java");
        final Developer savedDeveloper = jobsRepository.save(developer);

        final Developer jobDeveloper = (Developer) jobsRepository.findById(savedDeveloper.getId()).get();

        assertAll(
                () -> assertThat(jobDeveloper.getName()).isEqualTo(developer.getName()),
                () -> assertThat(jobDeveloper.getSalary()).isEqualTo(developer.getSalary()),
                () -> assertThat(jobDeveloper.getSkill()).isEqualTo(developer.getSkill())
        );
    }

    @DisplayName("직업 테이블의 연구원을 등록한다.")
    @Test
    void saveResearcher() {
        final Researcher researcher = Researcher.from("Lee", 10000, "DNA");
        final Researcher savedResearcher = jobsRepository.save(researcher);

        final Researcher jobResearcher = (Researcher) jobsRepository.findById(savedResearcher.getId()).get();

        assertAll(
                () -> assertThat(jobResearcher.getName()).isEqualTo(researcher.getName()),
                () -> assertThat(jobResearcher.getSalary()).isEqualTo(researcher.getSalary()),
                () -> assertThat(jobResearcher.getTitle()).isEqualTo(researcher.getTitle())
        );
    }

    @DisplayName("직업 테이블의 영업사원을 등록한다.")
    @Test
    void saveSales() {
        final Sales sales = Sales.from("Lee", 10000, 50000);
        final Sales savedSales = jobsRepository.save(sales);

        final Sales jobSales = (Sales) jobsRepository.findById(savedSales.getId()).get();

        assertAll(
                () -> assertThat(jobSales.getName()).isEqualTo(sales.getName()),
                () -> assertThat(jobSales.getSalary()).isEqualTo(sales.getSalary()),
                () -> assertThat(jobSales.getAmount()).isEqualTo(sales.getAmount())
        );
    }
}