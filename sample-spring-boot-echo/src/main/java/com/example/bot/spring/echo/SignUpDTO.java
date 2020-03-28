package com.example.bot.spring.echo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class SignUpDTO {

    private Long nonce;
    private String cellPhone;
    private String licensePlate;

    private NewFollowerEntity newFollower;
}
