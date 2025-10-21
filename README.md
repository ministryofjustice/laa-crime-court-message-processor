# laa-crime-court-message-processor

This is a Java 21 based Spring Boot application hosted on [MOJ Cloud Platform](https://user-guide.cloud-platform.service.justice.gov.uk/documentation/concepts/about-the-cloud-platform.html).

[![MIT license](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## Prerequisities
- Java 21
- Gradle
- Docker
- Cloud Platform CLI 
- An editor/IDE of some sort - preferably Intellij/Eclipse 
- kubectl
- Helm

## Building the application

To build the application run:
```sh
./gradlew clean build
```

## Running the application locally

TODO



## CI/CD Pipeline

[GitHub Actions](https://github.com/ministryofjustice/laa-crime-court-message-processor/actions) are used to manage the CI/CD pipeline for this application.

This table shows the automated workflows that are configured for this application.


| Workflow                                                                                                             | Triggered On                                                                 | Actions                                                                                                                                                   |
|----------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------|
| Gradle build and test<br/>[gradle-build-and-test.yaml](.github/workflows/gradle-build-and-test.yaml)                 | * Push to `main` branch<br/>* Push to a PR                                   | 1. Run the Gradle Build.  Produce JUnit and test coverage reports<br/>2. Run Snyk scan on the application<br/>3. Run Snyk scan on the docker image         |
| CodeQL<br/>[codeql-analysis.yml](.github/workflows/codeql-analysis.yml)                                              | * Push to `main` branch<br/>* Push to a PR<br/>* 05:34 every Saturday        | Perform CodeQL analysis                                                                                                                                   |
| Build and Deploy to Non-Prod Environments<br/>[cp-deployment-branch.yml](.github/workflows/cp-deployment-branch.yml) | * Push to any branch other than `main`<br/>* Manual invocation via GitHub UI | 1. Build docker image & push to image repository<br/>2. Simultaneously deploy to `dev`, `test` & `uat` upon approval                                      |
| Build and Deploy CCMP to CP<br/>[cp-deployment.yml](.github/workflows/cp-deployment.yml)                             | * Push to `main`<br/>* Manual invocation via GitHub UI                       | 1. Build docker image & push to image repository<br/>2. Deploy to `dev` upon approval<br>3. Simultaneously deploy to `test`, `uat` & `prod` upon approval |

### Deploying Manually 

The application can be deployed manually via the GitHub UI from PR branches or from the `main` branch.

1. Go to the Actions page https://github.com/ministryofjustice/laa-crime-court-message-processor/actions
2. Click on either the `Build and Deploy to Non-Prod Environments` or `Build and Deploy CCMP to CP` workflows
3. Click on the `Run workflow` button
4. Select the branch to deploy from the dropdown
5. Click on the `Run workflow` button

### View workflow logs

1. Go to the Actions page https://github.com/ministryofjustice/laa-crime-court-message-processor/actions
2. Click on the workflow in question to show a list of runs
3. Click on the run you want to view to show the jobs in that run. Any failed jobs will have a red X next to them.
4. Click on any individual job to show the steps in that job
5. Drill down in to the individual step to see the logs


### Deployment issues

If a deployment fails on the Helm deployment step, it will display a simple error message and exit.
```text
Run cd helm_deploy/crime-court-message-processor-service
Error: UPGRADE FAILED: context deadline exceeded
Error: Process completed with exit code 1.
```
Use these commands to troubleshoot the issue.

* Look at events in the namespace

  ```kubectl get events -n laa-crime-court-message-processor-<env>```

* Find pods in the environment

  ```kubectl get pods -n laa-crime-court-message-processor-<env>```

* Look at the logs for the application

  ```kubectl logs -n laa-crime-court-message-processor-<env> <pod-name>```
