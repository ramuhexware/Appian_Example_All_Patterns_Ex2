package com.appian.enterprise.employee.plugin.smartservice;

import com.appian.enterprise.employee.plugin.api.*;

import java.math.BigDecimal;

/**
 * Appian Smart Service to calculate and apply employee bonuses in Appian Process Models.
 */
@SmartService
@Name("Employee Bonus Calculator Smart Service")
@Description("Calculates recommended bonus based on base salary and performance rating")
public class EmployeeBonusCalculatorSmartService {

    @Input(required = true)
    @Name("BaseSalary")
    @Description("Employee annual base salary")
    private BigDecimal baseSalary;

    @Input(required = true)
    @Name("PerformanceRating")
    @Description("Performance score ranging from 1 to 5")
    private Double performanceRating;

    @Output
    @Name("CalculatedBonus")
    @Description("Recommended bonus amount")
    private BigDecimal calculatedBonus;

    public void run() {
        if (baseSalary == null || performanceRating == null) {
            this.calculatedBonus = BigDecimal.ZERO;
            return;
        }

        double bonusPercentage = 0.05 * performanceRating; // e.g. rating 5 -> 25%
        this.calculatedBonus = baseSalary.multiply(BigDecimal.valueOf(bonusPercentage));
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public void setPerformanceRating(Double performanceRating) {
        this.performanceRating = performanceRating;
    }

    public BigDecimal getCalculatedBonus() {
        return calculatedBonus;
    }
}
