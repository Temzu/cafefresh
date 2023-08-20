package com.temzu.cafefresh.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@NoArgsConstructor
@Table(name = "categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "image_source")
  private String imageSource;

  @Column(name = "active_status")
  private boolean activeStatus;

  @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
  private List<Product> products;

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

    Category category = (Category) o;

    return new EqualsBuilder().append(activeStatus, category.activeStatus).append(id, category.id)
        .append(title, category.title).append(imageSource, category.imageSource)
        .append(products, category.products).append(createdAt, category.createdAt)
        .append(updatedAt, category.updatedAt).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(title).append(imageSource)
        .append(activeStatus).append(products).append(createdAt).append(updatedAt).toHashCode();
  }
}

