package item2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuProductBuilderTest {

    @DisplayName("모든 항목에 대해서 메뉴상품 객체를 생성한다.")
    @Test
    void menu_product_builder() {

        final MenuProduct menuProduct = MenuProductBuilder.builder()
                .withSeq(1L)
                .withMenuId(1L)
                .withProductId(1L)
                .withQuantity(10)
                .build();

        assertAll(
                () -> assertEquals(menuProduct.getSeq(), 1L),
                () -> assertEquals(menuProduct.getMenuId(), 1L),
                () -> assertEquals(menuProduct.getProductId(), 1L),
                () -> assertEquals(menuProduct.getQuantity(), 10)
        );
    }

}