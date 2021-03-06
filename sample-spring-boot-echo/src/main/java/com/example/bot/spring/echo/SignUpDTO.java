package com.example.bot.spring.echo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SignUpDTO {

    private Long nonce;
    private String cellPhone;
    private String licensePlate;

    private NewFollowerEntity newFollower;
}
