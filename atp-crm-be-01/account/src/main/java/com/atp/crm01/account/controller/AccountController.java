package com.atp.crm01.account.controller;

import java.time.LocalDateTime;
import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atp.crm01.common.utils.DateFormats;
import com.atp.crm01.common.exception.DataNotFoundException;

@RestController
@RequestMapping("/account-controller")
public class AccountController {
//    @Value("${db.url}")
//    String dbURl;
    @GetMapping("/index/{isError}")
     public ResponseEntity<?> index(@PathVariable boolean isError){
    	  if(isError) throw new DataNotFoundException("No founded account");
    	  System.out.println("Now = "+(LocalDateTime.now().format(DateFormats.VN_DATE_TIME.getFormatter())));
          return ResponseEntity.ok("Account Management : ");
    }
}
