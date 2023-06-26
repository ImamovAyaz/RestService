package com.example.restservice.Calculator;

import com.example.restservice.Calculator.Calculator;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CalculatorController {
    private final AtomicLong counter = new AtomicLong();

    @PostMapping("/addition")
    public Calculator addition(@RequestParam(value = "firstValue", defaultValue = "1") int firstValue
            , @RequestParam(value = "secondValue", defaultValue = "1") int secondValue) {
        return new Calculator(counter.incrementAndGet(), firstValue, secondValue, "Сумма", firstValue + secondValue);
    }

    @PostMapping("/subtraction")
    public Calculator subtraction(@RequestParam(value = "firstValue", defaultValue = "1") int firstValue
            , @RequestParam(value = "secondValue", defaultValue = "1") int secondValue) {
        return new Calculator(counter.incrementAndGet(), firstValue, secondValue, "Вычитание", firstValue - secondValue);
    }

    @PostMapping("/multiplication")
    public Calculator multiplication(@RequestParam(value = "firstValue", defaultValue = "1") int firstValue
            , @RequestParam(value = "secondValue", defaultValue = "1") int secondValue) {
        return new Calculator(counter.incrementAndGet(), firstValue, secondValue, "Умножение", firstValue * secondValue);
    }

    @PostMapping("/division")
    public Calculator division(@RequestParam(value = "firstValue", defaultValue = "1") int firstValue
            , @RequestParam(value = "secondValue", defaultValue = "1") int secondValue) {
        if (Calculator.isEvenlyDivisable(firstValue, secondValue)){
            return new Calculator(counter.incrementAndGet(), firstValue, secondValue, "Деление", firstValue / secondValue, "Числа делятся без остатка");
        }
        else{
            return new Calculator(counter.incrementAndGet(), firstValue, secondValue, "Деление", firstValue / secondValue, "Деление чисел не целое, результат округлён в меньшую сторону");
        }
    }

    @PostMapping("/squared")
    public Calculator squared(@RequestParam(value = "value", defaultValue = "1") int value) {
        int result = (int) Math.pow(value, 2);
        return new Calculator(counter.incrementAndGet(), value, "Возведение в квадрат", result);
    }

    @PostMapping("/squareRoot")
    public Calculator squareRoot(@RequestParam(value = "value", defaultValue = "1") int value) {
        int result = (int) Math.sqrt(value);
        if (Calculator.isSqrtEvenlyDivisable(value)){
            return new Calculator(counter.incrementAndGet(), value, "Извлечение из корня", result);
        }
        else{
            return new Calculator(counter.incrementAndGet(), value, "Извлечение из корня", result, "Извлечение из корня не целое, результат округлён в меньшую сторону");
        }
    }
}
