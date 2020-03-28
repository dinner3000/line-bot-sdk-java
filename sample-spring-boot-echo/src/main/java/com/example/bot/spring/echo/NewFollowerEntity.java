package com.example.bot.spring.echo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class NewFollowerEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private long uid;
    private String branchCode;
    private String userId;
}
