package com.poly.entity;

import lombok.Data;

@Data
public class Item {
	private String name;
    private Double price;
    private String image;
    private Integer quantity;
    private String size;
}
