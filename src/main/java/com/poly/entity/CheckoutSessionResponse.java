package com.poly.entity;

import lombok.Data;

@Data
public class CheckoutSessionResponse {
	private String session_id;
    private String order_id;
}
