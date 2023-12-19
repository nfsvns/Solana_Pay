package com.poly.entity;


import java.util.List;

import lombok.Data;

@Data
public class CheckoutSessionRequest {
    private String network;
    private String successUrl;
    private String cancelUrl;
    private List<String> tokens;
    private List<Item> items;
    private Double shippingFees;
    private Config config;

}
