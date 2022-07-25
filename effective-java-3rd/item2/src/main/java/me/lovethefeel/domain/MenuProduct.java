package me.lovethefeel.domain;

public class MenuProduct {

    private Long seq;

    private Long menuId;

    private Long productId;

    private Long quantity;

    protected MenuProduct() {}

    private MenuProduct(final Long seq, final Long menuId, final Long productId, final Integer quantity) {
        this.seq = seq;
        this.menuId = menuId;
        this.productId = productId;
        this.quantity = quantity.longValue();
    }

    public static MenuProduct of(final Long seq, final Long menuId, final Long product, final Integer quantity) {
        return new MenuProduct(seq, menuId, product, quantity);
    }

    public static MenuProduct of(final Long menuId, final Long productId, final Integer quantity) {
        return new MenuProduct(null, menuId, productId, quantity);
    }

    public static MenuProduct of(final Long product, final Integer quantity) {
        return new MenuProduct(null, null, product, quantity);
    }

    public Long getSeq() {
        return seq;
    }

    public Long getMenuId() {
        return menuId;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getQuantity() {
        return quantity;
    }
}
