# Ubots FlowPlay


FlowPay is a backend solution for a relationship center.
This central The relationship will meet different types of customer requests. The main types of transactions are like card problems and taking out a loan, according to the specification presented in [Documentation (pt-BR)](Documentation/Especificacao.pdf).

All the data is stored in a temporary memory-based database, so we have all the "persistence" from a database without the need for a properly installed one, for the purposes of this Tech Demo

## Technologies Used 🛠️

[![Java](https://img.shields.io/badge/java-%233a75b0.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/pt-BR/)
[![SpringBoot](https://img.shields.io/badge/spring%20boot-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/)
[![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)](https://swagger.io/)
[![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)](https://www.postman.com/)
[![H2Database](https://img.shields.io/badge/H2%20Database-%2309476b?style=for-the-badge&logo=data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9Im5vIj8+CjwhLS0gQ3JlYXRlZCB3aXRoIElua3NjYXBlIChodHRwOi8vd3d3Lmlua3NjYXBlLm9yZy8pIC0tPgoKPHN2ZwogICB3aWR0aD0iNjEuODE1MjU0bW0iCiAgIGhlaWdodD0iNjEuODE1MzYxbW0iCiAgIHZpZXdCb3g9IjAgMCA2MS44MTUyNTQgNjEuODE1MzYxIgogICB2ZXJzaW9uPSIxLjEiCiAgIGlkPSJzdmc4NTUyIgogICBpbmtzY2FwZTp2ZXJzaW9uPSIxLjEuMiAoMGEwMGNmNTMzOSwgMjAyMi0wMi0wNCkiCiAgIHNvZGlwb2RpOmRvY25hbWU9ImgyLWxvZ28uc3ZnIgogICB4bWxuczppbmtzY2FwZT0iaHR0cDovL3d3dy5pbmtzY2FwZS5vcmcvbmFtZXNwYWNlcy9pbmtzY2FwZSIKICAgeG1sbnM6c29kaXBvZGk9Imh0dHA6Ly9zb2RpcG9kaS5zb3VyY2Vmb3JnZS5uZXQvRFREL3NvZGlwb2RpLTAuZHRkIgogICB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciCiAgIHhtbG5zOnN2Zz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciCiAgIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyIKICAgeG1sbnM6Y2M9Imh0dHA6Ly9jcmVhdGl2ZWNvbW1vbnMub3JnL25zIyIKICAgeG1sbnM6ZGM9Imh0dHA6Ly9wdXJsLm9yZy9kYy9lbGVtZW50cy8xLjEvIj4KICA8ZGVmcwogICAgIGlkPSJkZWZzODU0NiIgLz4KICA8c29kaXBvZGk6bmFtZWR2aWV3CiAgICAgaWQ9ImJhc2UiCiAgICAgcGFnZWNvbG9yPSIjZmZmZmZmIgogICAgIGJvcmRlcmNvbG9yPSIjNjY2NjY2IgogICAgIGJvcmRlcm9wYWNpdHk9IjEuMCIKICAgICBpbmtzY2FwZTpwYWdlb3BhY2l0eT0iMC4wIgogICAgIGlua3NjYXBlOnBhZ2VzaGFkb3c9IjIiCiAgICAgaW5rc2NhcGU6em9vbT0iMC45ODk5NDk0OSIKICAgICBpbmtzY2FwZTpjeD0iLTE1Ni4wNjg1NyIKICAgICBpbmtzY2FwZTpjeT0iMjQ0Ljk2MTk5IgogICAgIGlua3NjYXBlOmRvY3VtZW50LXVuaXRzPSJtbSIKICAgICBpbmtzY2FwZTpjdXJyZW50LWxheWVyPSJsYXllcjEiCiAgICAgc2hvd2dyaWQ9ImZhbHNlIgogICAgIGlua3NjYXBlOndpbmRvdy13aWR0aD0iMjQ2MCIKICAgICBpbmtzY2FwZTp3aW5kb3ctaGVpZ2h0PSIxNTYzIgogICAgIGlua3NjYXBlOndpbmRvdy14PSIwIgogICAgIGlua3NjYXBlOndpbmRvdy15PSIwIgogICAgIGlua3NjYXBlOndpbmRvdy1tYXhpbWl6ZWQ9IjEiCiAgICAgaW5rc2NhcGU6cGFnZWNoZWNrZXJib2FyZD0iMSIKICAgICBmaXQtbWFyZ2luLXRvcD0iMCIKICAgICBmaXQtbWFyZ2luLWxlZnQ9IjAiCiAgICAgZml0LW1hcmdpbi1yaWdodD0iMCIKICAgICBmaXQtbWFyZ2luLWJvdHRvbT0iMCIgLz4KICA8bWV0YWRhdGEKICAgICBpZD0ibWV0YWRhdGE4NTQ5Ij4KICAgIDxyZGY6UkRGPgogICAgICA8Y2M6V29yawogICAgICAgICByZGY6YWJvdXQ9IiI+CiAgICAgICAgPGRjOmZvcm1hdD5pbWFnZS9zdmcreG1sPC9kYzpmb3JtYXQ+CiAgICAgICAgPGRjOnR5cGUKICAgICAgICAgICByZGY6cmVzb3VyY2U9Imh0dHA6Ly9wdXJsLm9yZy9kYy9kY21pdHlwZS9TdGlsbEltYWdlIiAvPgogICAgICA8L2NjOldvcms+CiAgICA8L3JkZjpSREY+CiAgPC9tZXRhZGF0YT4KICA8ZwogICAgIGlua3NjYXBlOmxhYmVsPSJMYXllciAxIgogICAgIGlua3NjYXBlOmdyb3VwbW9kZT0ibGF5ZXIiCiAgICAgaWQ9ImxheWVyMSIKICAgICB0cmFuc2Zvcm09InRyYW5zbGF0ZSgtNzQuNjYzODI3LC0xMDguMTM2MDUpIj4KICAgIDxwYXRoCiAgICAgICBkPSJtIDExOC44ODg0OCwxNDIuMzE1NTUgYyAwLjM2NDc2LC0wLjAzODkgMC43MjQ2MywtMC4wNTg0IDEuMDc5NjUsLTAuMDU4NCAxLjc4NzYxLDAgMy4xNTczMywwLjQwMzI4IDQuMTA5MjQsMS4yMDk5MyAwLjk1MTkyLDAuODA2NTYgMS40Mjc5MywxLjk1MTA5IDEuNDI3OTMsMy40MzMxNCAwLDEuMjM2MDEgLTAuMzcwNjQsMi40ODU1NCAtMS4xMTE4LDMuNzQ5NTQgLTAuNzQxMjMsMS4yNjQ4OCAtMS45OTEwNiwyLjc0NzE5IC0zLjc0OTYxLDQuNDQ3ODQgLTEuMTc3MTksMS4xNDc1OCAtMi43NDMxNCwyLjUxMzUzIC00LjY5Nzg1LDQuMDk3NzYgLTEuOTU0NywxLjU4NDI2IC0zLjkwNTc5LDMuMDgxMDEgLTUuODUzMjcsNC40OTExNSB2IDUuMzYyNjYgaCAyNS40ODQxMiB2IC02LjIxMjU0IGggLTE0LjU4NDIyIGMgMC41NjY4NiwtMC40MDY4NyAxLjUxODc2LC0xLjEyMzIyIDIuODU1ODEsLTIuMTQ3MjIgMS4zMzcwOCwtMS4wMjQ4OSAyLjY2NjkxLC0yLjE0ODEzIDMuOTg5NDQsLTMuMzY4ODEgMi4xMDcyNywtMS45NjEzOCAzLjY3MzIsLTMuODY1OTIgNC42OTc4MywtNS43MTA5MSAxLjAyNDYzLC0xLjg0NTg5IDEuNTM2ODksLTMuNzkzNzUgMS41MzY4OSwtNS44NDI3MyAwLC0zLjEyNDY2IC0xLjA2ODIsLTUuNTQwODQgLTMuMjA0NiwtNy4yNDg1MiAtMi4xMzYzMSwtMS43MDc1OSAtNS4yMzkyNCwtMi41NjE0MyAtOS4zMDg1MSwtMi41NjE0MyAtMC44OTExMSwwIC0xLjc4MTQsMC4wNDM4IC0yLjY3MTA1LDAuMTMxNTQgdiAtMTIuNDAxMDggaCAtOC41MzQyNSB2IDEyLjExMjI4IEggOTcuNzUyODczIHYgLTEyLjExMjI4IGggLTguNTMzODg5IHYgMzMuMDkxODggaCA4LjUzMzg4OSB2IC0xNC41Nzg5OCBoIDEyLjYwMTM1NyB2IDE0LjU3ODk2IGggMC4wOTY3IGMgMC43OTk4OCwtMC42MTk4IDEuNjAwMzIsLTEuMjU0MDUgMi40MDEzOCwtMS45MDM2MiAxLjk1NDcxLC0xLjU4MzM2IDMuNTIwNzMsLTIuOTUwMTkgNC42OTc5MiwtNC4wOTc3OSAwLjQ4NDc2LC0wLjQ2OTE0IDAuOTMwOSwtMC45MjExNCAxLjMzODMyLC0xLjM1NjkxIHogbSAtMTMuMjg2OTQsMjUuODMxNDYgYyAtMTYuMDYwNDcyLDAgLTI5LjEzMzMxNywtMTMuMDcyODUgLTI5LjEzMzMxNywtMjkuMTMzNTIgMCwtMTYuMDQ4NTQgMTMuMDYwMjE1LC0yOS4wNzMwNCAyOS4xMzMzMTcsLTI5LjA3MzA0IDE2LjA2MDY1LDAgMjkuMDczMTQsMTMuMDEyNDEgMjkuMDczMTQsMjkuMDczMDQgdiAwLjkwMjIyIGggMS44MDQ0IHYgLTAuOTAyMjIgYyAwLC0xNy4wNTcyIC0xMy44MjAzMiwtMzAuODc3NDQgLTMwLjg3NzU0LC0zMC44Nzc0NCAtMTcuMDY4MjI3LDAgLTMwLjkzNzcxMywxMy44MzEyNSAtMzAuOTM3NzEzLDMwLjg3NzQ0IDAsMTcuMDU3NiAxMy44ODAzMTMsMzAuOTM3OTIgMzAuOTM3NzEzLDMwLjkzNzkyIGggMC45MDIxOSB2IC0xLjgwNDQgeiIKICAgICAgIGlkPSJwYXRoNjM5NCIKICAgICAgIGlua3NjYXBlOmNvbm5lY3Rvci1jdXJ2YXR1cmU9IjAiCiAgICAgICBzdHlsZT0iZmlsbDojZmZmZmZmO2ZpbGwtb3BhY2l0eToxO3N0cm9rZS13aWR0aDowLjkwMjE5OCIgLz4KICA8L2c+Cjwvc3ZnPgo=)](http://www.h2database.com/)

## Using the solution :rocket:

1. Clone this repository to your computer:
   ```bash
   git clone https://github.com/GiovaniRizzato/pior-filme-backend.git
   ```
2. Run the application using the Springboot IDE of your choice or enter the commands:
   ```bash
   mvn dependency:resolve
   mvn spring-boot:run
   ```
