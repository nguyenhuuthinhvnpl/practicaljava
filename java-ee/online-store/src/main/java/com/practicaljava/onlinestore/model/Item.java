package com.practicaljava.onlinestore.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "ITEMS")
@NamedQueries({
    @NamedQuery(name = "Item.all", query = "select i from Item i"),
    @NamedQuery(name = "Item.byCode", query = "select i from Item i where i.productCode = :productCode")
})
public class Item implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productCode;

    private String name;
    private String description;
    private Integer priceInPoints;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Collection<Review> reviews = new ArrayList<Review>();

    public Item(Long productCode, String name, String description, Integer priceInPoints) {
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.priceInPoints = priceInPoints;
    }

    public Item() {
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriceInPoints() {
        return priceInPoints;
    }

    public Collection<Review> getReviews() {
        return reviews;
    }

    public void addReview(User user, Integer score, String text) {
        reviews.add(new Review(user, this, score, text));
    }

    public void setPriceInPoints(Integer priceInPoints) {
        this.priceInPoints = priceInPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Item item = (Item) o;

        return productCode.equals(item.productCode);
    }

    @Override
    public int hashCode() {
        return productCode.hashCode();
    }
}
