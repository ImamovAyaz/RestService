package com.example.restservice.Calculator;

public class Calculator {
    private long id;
    private int firstValue;
    private int secondValue;
    private String operation;
    private int result;
    private String additionalInformation;

    public Calculator(long id, int firstValue, int secondValue, String operation, int result) {
        this.id = id;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.operation = operation;
        this.result = result;
    }

    public Calculator(long id, int firstValue, int secondValue, String operation, int result, String additionalInformation) {
        this.id = id;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.operation = operation;
        this.result = result;
        this.additionalInformation = additionalInformation;
    }

    public Calculator(long id, int firstValue, String operation, int result) {
        this.id = id;
        this.firstValue = firstValue;
        this.operation = operation;
        this.result = result;
    }

    public Calculator(long id, int firstValue, String operation, int result, String additionalInformation) {
        this.id = id;
        this.firstValue = firstValue;
        this.operation = operation;
        this.result = result;
        this.additionalInformation = additionalInformation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(int firstValue) {
        this.firstValue = firstValue;
    }

    public int getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(int secondValue) {
        this.secondValue = secondValue;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public static boolean isEvenlyDivisable(int firstValue, int secondValue) {
        return firstValue % secondValue == 0;
    }

    public static boolean isSqrtEvenlyDivisable(int value) {
        return Math.sqrt(value) % 1 == 0;
    }
}

