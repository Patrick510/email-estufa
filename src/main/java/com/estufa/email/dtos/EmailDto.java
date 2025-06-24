package com.estufa.email.dtos;

public record EmailDto(String ownerRef, String emailFrom, String emailTo, String subject, String body) {
}
