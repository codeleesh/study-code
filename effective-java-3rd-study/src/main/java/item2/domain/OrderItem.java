package item2.domain;

public class OrderItem {
    private Long seq;
    private Long orderId;
    private Long menuId;
    private Long quantity;

    protected OrderItem() {
    }

    private OrderItem(final Long seq, final Long orderId, final Long menuId, final Long quantity) {
        this.seq = seq;
        this.orderId = orderId;
        this.menuId = menuId;
        this.quantity = quantity;
    }



    public static OrderItemBuilder builder() {
        return new OrderItemBuilder();
    }

    public static class OrderItemBuilder {
        private Long seq;
        private Long orderId;
        private Long menuId;
        private Long quantity;

        OrderItemBuilder() {
        }

        public OrderItemBuilder seq(final Long seq) {
            this.seq = seq;
            return this;
        }

        public OrderItemBuilder orderId(final Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public OrderItemBuilder menuId(final Long menuId) {
            this.menuId = menuId;
            return this;
        }

        public OrderItemBuilder quantity(final Long quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this.seq, this.orderId, this.menuId, this.quantity);
        }

        public String toString() {
            return "OrderItem.OrderItemBuilder(seq=" + this.seq + ", orderId=" + this.orderId + ", menuId=" + this.menuId + ", quantity=" + this.quantity +")";
        }
    }

    public Long getSeq() {
        return seq;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public Long getQuantity() {
        return quantity;
    }
}
