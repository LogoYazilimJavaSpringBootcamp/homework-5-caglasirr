package com.example.movieapp.client;

import com.example.movieapp.dto.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//euroko server implementasyonu yaparsak url'e gerek kalmaz
@FeignClient(value="payment-service", url="http://localhost:3434")
public interface PaymentClient {

    @PostMapping(value="/payments")
    Payment createPayment(@RequestBody Payment payment); //sadece signature ama aynı olmak zorunda


    //@PostMapping(value="/payments")
    //PaymentResponse createPayment(@RequestBody PaymentRequest payment);
}
