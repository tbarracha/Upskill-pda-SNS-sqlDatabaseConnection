# Supplementary Specification (FURPS+)

## Functionality

- Every person using the system must be authenticated.

## Usability

- The user interfaces must be simple, intuitive, and consistent.
- The application must provide a user manual.

## Reliability

- In case of a system failure there should be no data loss.

## Performance

- Any interaction between a user and the system must have a maximum response time of 3 seconds, regardless of the existing load.
- The system should start up in less than 10 seconds.
- Overall system availability must be higher than 99% per year.

## Supportability

## +

### Design Constraints

- The system is envisioned to have 2 different applications
    - Web Application (for the DGS staff members)
    - Mobile Application (for the SNS users)
- For now, we will be building 2 console applications.

### Implementation Constraints

- The application must be developed in Java.
- The coding convention should adopt CamelCase.
- Best practices should be used for the software development process.
- All project artifacts should be developed in english.
- The images must be in SVG format.
- The machine serving the applications has 8GB RAM.
- The system must be prepared to easily support data persistence on multiple target systems (relation databases, NoSQL databases or in-memory databases)

### Interface Constraints

- The issuance of the EU COVID Digital Certificate will be executed by an external API (yet to be defined)

### Physical Constraints

- The main computer which runs the applications should be protected by UPS.
