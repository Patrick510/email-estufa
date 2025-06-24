package com.estufa.email.dtos;

public record EstufaEmailDto(
    Double temperaturaEstufa,
    Double sensacaoTermicaEstufa,
    Double umidadeEstufa,
    String emailTo) {
}
