/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.restservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Description("Тестирование сумма двух произвольных чисел")
    public void additionValuesTest() throws Exception {
        int firstValue = 12;
        int secondValue = 21;
        this.mockMvc.perform(post("/addition")
                        .param("firstValue", String.format("%s", firstValue))
                        .param("secondValue", String.format("%s", secondValue)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation").value("Сумма"))
                .andExpect(jsonPath("$.result").value(firstValue + secondValue));
    }

    @Test
    @Description("Тестирование сумма двух чисел по умолчанию")
    public void additionDefaultValuesTest() throws Exception {

        this.mockMvc.perform(post("/addition"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation").value("Сумма"))
                .andExpect(jsonPath("$.result").value("2"));
    }

    @Test
    @Description("Тестирование сумма двух некоррктных значений")
    public void additionNegativeValuesTest() throws Exception {
        String firstValue = "12!@#";
        String secondValue = "21!#";
        this.mockMvc.perform(post("/addition")
                        .param("firstValue", firstValue)
                        .param("secondValue", secondValue))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Description("Тестирование вычитания двух произвольных чисел")
    public void subtractionValuesTest() throws Exception {
        int firstValue = 21;
        int secondValue = 12;
        this.mockMvc.perform(post("/subtraction")
                        .param("firstValue", String.format("%s", firstValue))
                        .param("secondValue", String.format("%s", secondValue)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation").value("Вычитание"))
                .andExpect(jsonPath("$.result").value(firstValue - secondValue));
    }

    @Test
    @Description("Тестирование вычитания двух чисел по умолчанию")
    public void subtractionDefaultValuesTest() throws Exception {

        this.mockMvc.perform(post("/subtraction"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation").value("Вычитание"))
                .andExpect(jsonPath("$.result").value("0"));
    }

    @Test
    @Description("Тестирование вычитания двух некорректных значений")
    public void subtractionNegativeValuesTest() throws Exception {
        String firstValue = "12!@#";
        String secondValue = "21!#";
        this.mockMvc.perform(post("/subtraction")
                        .param("firstValue", firstValue)
                        .param("secondValue", secondValue))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Description("Тестирование умножения двух произвольных чисел")
    public void multiplicationValuesTest() throws Exception {
        int firstValue = 21;
        int secondValue = 12;
        this.mockMvc.perform(post("/multiplication")
                        .param("firstValue", String.format("%s", firstValue))
                        .param("secondValue", String.format("%s", secondValue)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation").value("Умножение"))
                .andExpect(jsonPath("$.result").value(firstValue * secondValue));
    }

    @Test
    @Description("Тестирование умножения двух чисел по умолчанию")
    public void multiplicationDefaultValuesTest() throws Exception {

        this.mockMvc.perform(post("/multiplication"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation").value("Умножение"))
                .andExpect(jsonPath("$.result").value("1"));
    }

    @Test
    @Description("Тестирование умножения двух некорректных значений")
    public void multiplicationNegativeValuesTest() throws Exception {
        String firstValue = "12!@#";
        String secondValue = "21!#";
        this.mockMvc.perform(post("/multiplication")
                        .param("firstValue", firstValue)
                        .param("secondValue", secondValue))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Description("Тестирование деления двух произвольных чисел")
    public void divisionValuesTest() throws Exception {
        int firstValue = 21;
        int secondValue = 12;
        this.mockMvc.perform(post("/division")
                        .param("firstValue", String.format("%s", firstValue))
                        .param("secondValue", String.format("%s", secondValue)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation").value("Деление"))
                .andExpect(jsonPath("$.additionalInformation").value("Деление чисел не целое, результат округлён в меньшую сторону"))
                .andExpect(jsonPath("$.result").value(firstValue / secondValue));
    }

    @Test
    @Description("Тестирование деления двух чисел по умолчанию")
    public void divisionDefaultValuesTest() throws Exception {
        this.mockMvc.perform(post("/division"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation").value("Деление"))
                .andExpect(jsonPath("$.result").value("1"));
    }

    @Test
    @Description("Тестирование деления двух некорректных значений")
    public void divisionNegativeValuesTest() throws Exception {
        String firstValue = "12!@#";
        String secondValue = "21!#";
        this.mockMvc.perform(post("/division")
                        .param("firstValue", firstValue)
                        .param("secondValue", secondValue))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Description("Тестирование возведения в квадрат произвольного числа")
    public void squaredValuesTest() throws Exception {
        int value = 21;
        this.mockMvc.perform(post("/squared")
                        .param("value", String.format("%s", value)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation").value("Возведение в квадрат"))
                .andExpect(jsonPath("$.result").value(Math.pow(value, 2)));
    }

    @Test
    @Description("Тестирование возведения в квадрат числа по умолчанию")
    public void squaredDefaultValuesTest() throws Exception {
        this.mockMvc.perform(post("/squared"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation").value("Возведение в квадрат"))
                .andExpect(jsonPath("$.result").value(1));
    }

    @Test
    @Description("Тестирование возведения в квадрат некорректно значения")
    public void squaredNegativeValuesTest() throws Exception {
        String value = "21!@#";
        this.mockMvc.perform(post("/squared").param("value", String.format("%s", value)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Description("Тестирование извлечения из корня произвольного числа")
    public void squareRootValuesTest() throws Exception {
        int value = 21;
        this.mockMvc.perform(post("/squareRoot")
                        .param("value", String.format("%s", value)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation").value("Извлечение из корня"))
                .andExpect(jsonPath("$.additionalInformation").value("Извлечение из корня не целое, результат округлён в меньшую сторону"))
                .andExpect(jsonPath("$.result").value((int) Math.sqrt(value)));
    }

    @Test
    @Description("Тестирование извлечения из корня числа по умолчанию")
    public void squareRootDefaultValuesTest() throws Exception {
        this.mockMvc.perform(post("/squareRoot"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation").value("Извлечение из корня"))
                .andExpect(jsonPath("$.result").value(1));
    }

    @Test
    @Description("Тестирование извлечения из корня некорректного значения")
    public void squareRootNegativeValuesTest() throws Exception {
        String value = "21!@#";
        this.mockMvc.perform(post("/squareRoot").param("value", String.format("%s", value)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
