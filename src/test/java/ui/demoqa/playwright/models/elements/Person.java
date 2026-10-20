package ui.demoqa.playwright.models.elements;

public record Person(
        String fullName,
        String firstname,
        String lastname,
        Integer age,
        Integer salary,
        String department,
        String email,
        String currentAddress,
        String permanentAddress,
        String mobile
) {}
