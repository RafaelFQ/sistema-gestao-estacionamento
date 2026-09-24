package com.rafaelfq.sistema_gestao_estacionamento.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class SpringTimezoneConfig {

   @PostConstruct
   public void timezoneConfig(){
      TimeZone.setDefault(TimeZone.getTimeZone("America/Recife"));
   }
}
