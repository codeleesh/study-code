package me.lovethefeel.product.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lovethefeel.product.domain.ProductRedis;
import me.lovethefeel.product.repository.ProductRedisRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductRedisService {

    private final ProductRedisRepository productRedisRepository;

    public ProductRedis saveProductItem(final ProductRedis productRedis) {

        log.info("saveProductItem :: ProductRedis {}", productRedis);

        try {
            final ProductRedis saveProductRedis = productRedisRepository.save(productRedis);
            log.info("Redis Save Success :: Product {}", saveProductRedis);

            return saveProductRedis;
        } catch (DataAccessException dae) {

            log.error("Redis Connection fail :: {}", dae.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, dae.getMessage());
        } catch (UnsupportedOperationException uoe) {

            log.error("Request type is unsupported operation :: {}", uoe.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, uoe.getMessage());
        }
    }

    public ProductRedis findProductItem(final String productUuid) {

        log.info("findProductItem :: ProductUuid {}", productUuid);

        try {
            final ProductRedis findProductRedis = productRedisRepository.findById(productUuid)
                    .orElseThrow(IllegalArgumentException::new);
            log.info("Redis Find Success :: Product {}", findProductRedis);

            return findProductRedis;
        } catch (IllegalArgumentException ie) {

            log.error("Redis Key is not exist :: {}", ie.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ie.getMessage());
        } catch (DataAccessException dae) {

            log.error("Redis Connection fail :: {}", dae.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, dae.getMessage());
        } catch (UnsupportedOperationException uoe) {

            log.error("Request type is unsupported operation :: {}", uoe.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, uoe.getMessage());
        }
    }

    public void deleteProductItem(final String productUuid) {

        log.info("deleteProductItem :: ProductUuid {}", productUuid);

        try {
            final ProductRedis findProductRedis = productRedisRepository.findById(productUuid)
                    .orElseThrow(IllegalArgumentException::new);
            productRedisRepository.delete(findProductRedis);
            log.info("Redis Delete Success :: deleteProductRedis {}", findProductRedis);

        } catch (IllegalArgumentException ie) {

            log.error("Redis Key is not exist :: {}", ie.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ie.getMessage());
        } catch (DataAccessException dae) {

            log.error("Redis Connection fail :: {}", dae.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, dae.getMessage());
        } catch (UnsupportedOperationException uoe) {

            log.error("Request type is unsupported operation :: {}", uoe.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, uoe.getMessage());
        }
    }
}
