package com.temzu.cafefresh.entities;

import com.temzu.cafefresh.enums.OrderStatuses;
import com.temzu.cafefresh.enums.OrderTypes;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @OneToMany(mappedBy = "order")
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private List<OrderItem> items;

  @Column(name = "price")
  private BigDecimal price;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "address")
  private String address;

  @Column(name = "client_name")
  private String clientName;

  @Column(name = "phone")
  private String phone;

  @Basic
  @Column(name = "order_type", columnDefinition = "integer default 1")
  private Integer orderTypeValue;

  @Basic
  @Column(name = "order_status", columnDefinition = "integer default 1")
  private Integer orderStatusValue;

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Transient
  private OrderTypes orderType;

  @Transient
  private OrderStatuses orderStatus;

  @PostLoad
  void fillTransient() {
    if (orderTypeValue > 0) {
      this.orderType = OrderTypes.of(orderTypeValue);
    }

    if (orderStatusValue > 0) {
      this.orderStatus = OrderStatuses.of(orderStatusValue);
    }
  }

  @PrePersist
  void fillPersistent() {
    if (orderType != null) {
      this.orderTypeValue = orderType.getCode();
    }

    if (orderStatus != null) {
      this.orderStatusValue = orderStatus.getCode();
    }
  }

}
