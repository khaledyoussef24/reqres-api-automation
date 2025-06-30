````md
# ReqRes API Automation Suite

## Overview

Automated test suite for the ReqRes public API (https://reqres.in), covering end-to-end user CRUD operations.

## Features

- Create, update, retrieve, delete and verify deletion of users
- Data extraction between requests
- Schema and status-code validations
- Configurable for multiple environments

## Tech Stack

- **Language**: Java 11
- **HTTP Client**: Rest Assured
- **Test Runner**: TestNG
- **Reporting**: Allure
- **Build & Dependency Management**: Maven

## Prerequisites

- JDK 11+
- Maven 3.6+
- Internet access to https://reqres.in

## Installation & Execution

1. Clone the repository
   ```bash
   git clone https://github.com/your-org/reqres-api-automation.git
   cd reqres-api-automation
   ```
````

2. Run tests and view Allure report

   ```bash
   mvn clean test allure:serve
   ```

   This opens a browser with the interactive report.

## Configuration

- Base URL, timeouts and other settings live in `src/main/java/config/RestAssuredConfig.java`.
- Allure results directory is set in `src/test/resources/allure.properties`.

## CI/CD

On every push or pull request, GitHub Actions will:

1. Checkout code
2. Set up Java 11
3. Execute `mvn clean test allure:report`
4. Upload the generated Allure report artifact

Workflow file: `.github/workflows/ci-cd.yaml`

## Extending the Suite

- **Data-Driven**: Add TestNG @DataProvider methods for multiple payloads.
- **Negative Tests**: Cover missing fields, invalid IDs, unauthorized access.
- **Schema Validation**: Integrate JSON-schema checks.
- **Performance**: Assert response times.
- **Parallel Execution**: Configure TestNG to run tests concurrently.

---
