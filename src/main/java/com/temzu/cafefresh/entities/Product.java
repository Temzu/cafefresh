package com.temzu.cafefresh.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "title")
  private String title;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @Column(name = "price")
  private BigDecimal price;

  @Column(name = "description")
  private String description;

  @Column(name = "image_source")
  private String imageSource;

  @Column(name = "active_status")
  private boolean activeStatus;

  @Column(name = "createdAt")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updatedAt")
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;

    if (o == null || getClass() != o.getClass())
      return false;

    Product product = (Product) o;

    return new EqualsBuilder().append(activeStatus, product.activeStatus).append(id, product.id)
        .append(title, product.title).append(category, product.category)
        .append(price, product.price).append(description, product.description)
        .append(imageSource, product.imageSource).append(createdAt, product.createdAt)
        .append(updatedAt, product.updatedAt).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(title).append(category).append(price)
        .append(description).append(imageSource).append(activeStatus).append(createdAt)
        .append(updatedAt).toHashCode();
  }
}
