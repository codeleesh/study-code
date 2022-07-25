package me.lovethefeel.domain;

public final class MenuProductBuilder {
    private Long seq;
    private Long menuId;
    private Long productId;
    private Integer quantity;

    private MenuProductBuilder() {
    }

    public static MenuProductBuilder builder() {
        return new MenuProductBuilder();
    }

    public MenuProductBuilder withSeq(Long seq) {
        this.seq = seq;
        return this;
    }

    public MenuProductBuilder withMenuId(Long menuId) {
        this.menuId = menuId;
        return this;
    }

    public MenuProductBuilder withProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public MenuProductBuilder withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public MenuProduct build() {
        return MenuProduct.of(seq, menuId, productId, quantity);
    }
}
