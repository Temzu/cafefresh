package com.temzu.cafefresh.entities;

import com.temzu.freshcafe.enums.OrderStatuses;
import com.temzu.freshcafe.enums.OrderTypes;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
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
