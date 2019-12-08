package br.com.ortiz.advisor.advices.model.entity;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@RedisHash("Advice")
public class Advice implements Serializable {

    private String symbol;
    private LocalDateTime date;
    private Boolean buy;
    private BigDecimal profitEstimated;
    private String signalTriggered;
    private String observations;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getBuy() {
        return buy;
    }

    public void setBuy(Boolean buy) {
        this.buy = buy;
    }

    public BigDecimal getProfitEstimated() {
        return profitEstimated;
    }

    public void setProfitEstimated(BigDecimal profitEstimated) {
        this.profitEstimated = profitEstimated;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getSignalTriggered() {
        return signalTriggered;
    }

    public void setSignalTriggered(String signalTriggered) {
        this.signalTriggered = signalTriggered;
    }
}
