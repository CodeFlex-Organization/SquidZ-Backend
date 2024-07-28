package com.flabum.squidzbackend.shopping.domain.model.aggregates;

import com.flabum.squidzbackend.shopping.domain.model.entities.Product;
import com.flabum.squidzbackend.shopping.domain.model.entities.SalesOrderItem;
import com.flabum.squidzbackend.shopping.domain.model.entities.ShoppingCar;
import com.flabum.squidzbackend.shopping.domain.model.valueobjects.SalesOrderState;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;

@Getter
@Entity
public class SalesOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @ManyToOne
    private ShoppingCar shoppingCar;

    @Getter
    private SalesOrderState salesOrderState;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateAt;

    public SalesOrder(ShoppingCar shoppingCar) {
        this.shoppingCar = shoppingCar;
        this.salesOrderState = SalesOrderState.PENDING;
    }

    public SalesOrder() {
        this.shoppingCar = new ShoppingCar(List.of(new SalesOrderItem(new Product(), 0)));
    }

    public void confirmSalesOrder() {
        this.salesOrderState = SalesOrderState.CONFIRMED;
    }

    public void shipSalesOrder() {
        this.salesOrderState = SalesOrderState.SHIPPED;
    }

    public void deliverSalesOrder() {
        this.salesOrderState = SalesOrderState.DELIVERED;
    }

    public void cancelSalesOrder() {
        this.salesOrderState = SalesOrderState.CANCELLED;
    }
}
