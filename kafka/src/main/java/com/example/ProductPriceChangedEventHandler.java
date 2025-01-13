package com.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductPriceChangedEventHandler {

    public static final String TOPIC = "product_price_changed";
    private static final String GROUP_ID = "demo";

    private final ProductService productService;

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
    public void handle(ProductPriceChangedEvent productPriceChangedEvent) {
        log.info("Received product price changed event with Product code: {} at :{}", productPriceChangedEvent.getProductCode(), productPriceChangedEvent.getEventDate());
        productService.updateProductPrice(productPriceChangedEvent.getProductCode(), productPriceChangedEvent.getPrice());
    }

}
