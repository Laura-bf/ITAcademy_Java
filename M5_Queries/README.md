# M5_Queries — SQL Query Exercises

![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?logo=mysql)

## Description

Structured SQL query exercises on the classic EMP/DEPT (employees/departments) sample schema. Progresses from basic `SELECT` and filtering (N1) to multi-table `JOIN`s and aggregate functions (N2), then to subqueries, `GROUP BY`/`HAVING`, and complex reporting queries (N3). Each query shows the expected result set inline as a comment.

## What I Learned

- `SELECT`, `WHERE`, `ORDER BY`, `DISTINCT`, `LIMIT`
- `LEFT`/`RIGHT` string functions, `LENGTH`, `DATE_FORMAT`, `IFNULL`, `CASE`
- Comparison operators, `BETWEEN`, `IN`, `LIKE`, `NOT LIKE`
- Multi-level sorting and computed columns (`SAL + 1000`, percentage calculations)
- Subqueries (scalar, row, correlated) with `IN`, `=`, `>=`
- `JOIN` (INNER, LEFT) across departments and employees
- Aggregate functions: `COUNT`, `SUM`, `AVG`, `MIN`, `MAX`
- `GROUP BY` with `HAVING` for group-level filtering
- Self-referencing queries (employees with managers, manager headcount)
- Pattern: finding top-N salaries, departments without employees, group-wise maximums

## Files

| File | Level | Topics |
|------|-------|--------|
| [Nivel1.sql](Nivel1.sql) | N1 — Basic | `SELECT`, `WHERE`, `ORDER BY`, `DISTINCT`, computed columns, `LIKE`, `IFNULL`, simple filtering |
| [Nivel2.sql](Nivel2.sql) | N2 — Intermediate | `BETWEEN`, subqueries in `WHERE`, `COUNT`/`SUM`/`MAX` aggregates, single-row subqueries, basic `JOIN` |
| [Nivel3.sql](Nivel3.sql) | N3 — Advanced | `GROUP BY`/`HAVING`, nested subqueries, `LEFT JOIN`, correlated subqueries, group-wise maximum, departments without employees |

### Example Query (N1)

```sql
-- Halla los empleados que tienen una comision superior a la mitad de su salario.
SELECT ENAME
FROM empleados
WHERE COMM > SAL/2;
```

### Example Query (N3)

```sql
-- Halla la suma de salarios de cada departamento.
SELECT d.DNAME, A.SAL
FROM departamentos d
JOIN (SELECT SUM(SAL) AS SAL, DEPTNO FROM empleados GROUP BY DEPTNO) A
  ON d.DEPTNO = A.DEPTNO
GROUP BY d.DNAME;
```

## How to Run

```bash
mysql -u root -p < Nivel1.sql
mysql -u root -p < Nivel2.sql
mysql -u root -p < Nivel3.sql
```

Requires the classic EMP (`empleados`) and DEPT (`departamentos`) tables.

## Built With

- MySQL 8
