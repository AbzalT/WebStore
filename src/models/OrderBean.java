package models;

import java.util.UUID;

/**
 * Created by user01 on 17.05.2017.
 */
public class OrderBean {
    private UUID id;
    private UUID userId;
    private UUID productId;

    public OrderBean() {
        id = UUID.randomUUID();
    }
    public OrderBean(UUID userId, UUID productId) {
        id = UUID.randomUUID();
        this.userId = userId;
        this.productId = productId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }
}
