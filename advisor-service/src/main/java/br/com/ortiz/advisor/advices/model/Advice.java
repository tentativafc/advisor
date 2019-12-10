package br.com.ortiz.advisor.advices.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@RedisHash("Advice")
public class Advice implements Serializable {
    private String symbol;
    private LocalDateTime date;
    private Boolean isToBuy;
    private BigDecimal symbolValue;
    private BigDecimal profitEstimated;
    private String signalsTriggered;
    private String observations;
}
