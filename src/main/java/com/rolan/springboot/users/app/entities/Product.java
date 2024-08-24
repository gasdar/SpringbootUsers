package com.rolan.springboot.users.app.entities;

import com.rolan.springboot.users.app.validations.customs.ExistSku;
import com.rolan.springboot.users.app.validations.customs.IsRequired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="{message.notblank}")
    @ExistSku
    private String sku;

    @NotBlank(message="{message.notblank}")
    @Size(min=4, max=20)
    private String name;

    @NotNull(message="{message.notnull}")
    @Min(value=500, message="{message.price.product}")
    private Integer price;

    @IsRequired
    @Size(min=10, max=45)
    private String description;

    public Product() {
    }
    public Product(@NotBlank(message = "message.notblank") String sku,
            @NotBlank(message = "{message.notblank}") @Size(min = 4, max = 20) String name,
            @NotNull(message = "{message.notnull}") @Min(value = 500, message = "{message.price.product}") Integer price,
            @Size(min = 10, max = 45) String description) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    

}
