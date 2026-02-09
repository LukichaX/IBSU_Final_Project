# 🚀 AutomationExercise E2E Framework

A comprehensive Test Automation Framework designed for the [Automation Exercise](https://automationexercise.com) platform. This project is a final group submission for the Test Automation course.

## 🛠 Tech Stack
* **Language:** Java
* **Automation:** Selenium WebDriver
* **API Testing:** RestAssured
* **Test Runner:** TestNG
* **Build Tool:** Maven
* **Reporting:** Allure Reports
* **Design Pattern:** Page Object Model (POM)

---

## 👥 Team & Task Distribution
We followed a modular approach to ensure the UI and API layers were developed concurrently and integrated into a single reporting dashboard.

| Contributor | Role | Responsibilities |
| :--- | :--- | :--- |
| **Luka Khirdaevi** | **Lead / UI Engineer** | Framework Architecture, Maven/Allure setup, POM design, and **all 10 UI Test Cases**. |
| **Mate** | **API Architect** | Base API configuration, RestAssured Request utilities, and **API Test Cases 3 & 4**. |
| **Javid** | **API Automation** | Implementation of **8 API Test Cases** and endpoint validation logic. |

---

## 🏗 Framework Architecture
The project is structured using the **Page Object Model (POM)** to separate the test logic from the page-specific UI elements, ensuring maintainability.



* `src/main/java/pages`: UI Page Objects.
* `src/main/java/api`: API Client and Request utilities.
* `src/test/java/ui`: UI test scripts.
* `src/test/java/api`: API test scripts.

---

## 🚀 Execution & Reporting

### 1. Run all tests
To run the full suite (UI + API) in parallel:
```bash
mvn clean test
