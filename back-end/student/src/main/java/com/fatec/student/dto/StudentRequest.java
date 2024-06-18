package com.fatec.student.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentRequest(
    
@NotNull(message = "Não pode ser nada!!")
String name, 

@NotBlank(message = "Não pode ser nada!!")
String course
){
}
