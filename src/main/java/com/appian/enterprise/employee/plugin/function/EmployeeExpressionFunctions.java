package com.appian.enterprise.employee.plugin.function;

import com.appian.enterprise.employee.plugin.api.*;

import java.math.BigDecimal;

/**
 * Custom Appian Expression Functions.
 * Callable in SAIL UI components, process node expressions, and script tasks using fn! syntax.
 */
public class EmployeeExpressionFunctions {

    @Function
    @Name("calculateEmployeeBonus")
    @Description("Appian Custom Function fn!calculateEmployeeBonus - calculates bonus based on salary and rating")
    public static BigDecimal calculateEmployeeBonus(
            @Name("salary") Double salary,
            @Name("performanceScore") Double performanceScore) {

        if (salary == null || performanceScore == null) {
            return BigDecimal.ZERO;
        }
        double bonus = salary * 0.10 * performanceScore;
        return BigDecimal.valueOf(bonus);
    }

    @Function
    @Name("validateEmployeeSSN")
    @Description("Appian Custom Function fn!validateEmployeeSSN - validates social security number format")
    public static Boolean validateEmployeeSSN(@Name("ssn") String ssn) {
        if (ssn == null) {
            return false;
        }
        // Standard US SSN format XXX-XX-XXXX
        return ssn.matches("^\\d{3}-\\d{2}-\\d{4}$");
    }
}
