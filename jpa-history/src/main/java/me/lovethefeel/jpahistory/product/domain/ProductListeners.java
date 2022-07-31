package me.lovethefeel.jpahistory.product.domain;

import lombok.extern.slf4j.Slf4j;
import me.lovethefeel.jpahistory.product.repository.ProductHistoryRepository;
import me.lovethefeel.jpahistory.product.utils.BeanUtils;

import javax.persistence.*;

@Slf4j
public class ProductListeners {

    @PostPersist
    public void productInsert(final Product product) {

        final ProductHistoryRepository historyRepository = (ProductHistoryRepository) BeanUtils.getBean("productHistoryRepository");
        log.info("Product Insert product {}", product);

        final ProductHistory productHistory = ProductHistory.fromEntity(product, "신규 저장");
        historyRepository.save(productHistory);
        log.info("ProductHistory Insert productHistory {}", productHistory);
    }

    @PostUpdate
    public void productUpdate(final Product product) {

        final ProductHistoryRepository historyRepository = (ProductHistoryRepository) BeanUtils.getBean("productHistoryRepository");
        log.info("Product Update product {}", product);

        final ProductHistory productHistory = ProductHistory.fromEntity(product, "이름 변경");
        historyRepository.save(productHistory);
        log.info("ProductHistory Insert productHistory {}", productHistory);
    }
}
