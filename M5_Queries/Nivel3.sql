/* 61. Â¿Cuantos empleos diferentes, cuantos empleados, y cuantos salarios diferentes 
encontramos en el departamento 30, y a cuanto asciende la suma de salarios de dicho departamento?

   NUM_JOB    NUM_EMP    NUM_SAL   SUM(SAL)
---------- ---------- ---------- ----------
         3          6          5       9400
         
1 fila seleccionada.*/
SELECT COUNT(DISTINCT JOB) AS NUM_JOB, COUNT(DISTINCT ENAME) AS NUM_EMP, COUNT(DISTINCT SAL) AS NUM_SAL, SUM(SAL)
FROM empleados
WHERE DEPTNO = 30; 


/* 62. Â¿Cuantos empleados tienen comision?

  COUNT(*)
----------
         3
         
1 fila seleccionada.*/
SELECT COUNT(*)
FROM empleados
WHERE COMM > 0;


/* 63. Â¿Cuantos empleados tiene el departamento 20?

   NUM_EMP
----------
         5
         
1 fila seleccionada.*/
SELECT COUNT(*)
FROM empleados
WHERE DEPTNO=20;



/* 64. Halla los departamentos que tienen mas de tres empleados, y el numero de empleados de los mismos.

    DEPTNO    NUM_EMP
---------- ----------
        20          5
        30          6
        
2 filas seleccionadas.*/
SELECT DEPTNO, COUNT(*) AS NUM_EMP
FROM empleados
GROUP BY DEPTNO
HAVING NUM_EMP > 3;



/* 65. Obten los empleados del departamento 10 que tienen el mismo empleo que 
alguien del departamento de Ventas. Desconocemos el codigo de dicho departamento.

ENAME
----------
CLARK
MILLER

2 filas seleccionadas.*/
SELECT ENAME
FROM empleados
WHERE DEPTNO=10 AND JOB IN (
SELECT DISTINCT JOB 
FROM empleados 
WHERE DEPTNO =
(SELECT DEPTNO
FROM departamentos
WHERE DNAME = 'SALES'));




/* 66. Halla los empleados que tienen por lo menos un empleado a su mando, ordenados inversamente por nombre.

ENAME
----------
SCOTT
KING
JONES
FORD
CLARK
BLAKE

6 filas seleccionadas.*/
SELECT ENAME
FROM empleados
WHERE EMPNO IN (SELECT DISTINCT MGR FROM empleados)
ORDER BY ENAME DESC;



/* 67. Obten informacion sobre los empleados que tienen el mismo trabajo que 
algun empleado que trabaje en Chicago.

     EMPNO ENAME      JOB              MGR HIREDATE        SAL       COMM     DEPTNO
---------- ---------- --------- ---------- -------- ---------- ---------- ----------
      7369 SMITH      CLERK           7902 17/12/80        800                    20
      7876 ADAMS      CLERK           7788 12/01/83       1100                    20
      7934 MILLER     CLERK           7782 23/01/82       1300                    10
      7900 JAMES      CLERK           7698 03/12/81        950                    30
      7566 JONES      MANAGER         7839 02/04/81       2975                    20
      7782 CLARK      MANAGER         7839 09/06/81       2450                    10
      7698 BLAKE      MANAGER         7839 01/05/81       2850                    30
      7499 ALLEN      SALESMAN        7698 20/02/81       1600        300         30
      7654 MARTIN     SALESMAN        7698 28/09/81       1250       1400         30
      7844 TURNER     SALESMAN        7698 08/09/81       1500          0         30
      7521 WARD       SALESMAN        7698 22/02/81       1250        500         30

11 filas seleccionadas.*/
SELECT EMPNO, ENAME, JOB, IFNULL(MGR,'') AS MGR, date_format(hiredate, '%d/%m/%y') AS HIREDATE, SAL, IFNULL(COMM,'') AS COMM, DEPTNO
FROM empleados
WHERE JOB IN (SELECT JOB FROM empleados WHERE DEPTNO = (SELECT DEPTNO FROM departamentos WHERE LOC='CHICAGO'))
ORDER BY JOB;



/* 68. Â¿Que empleos distintos encontramos en la empresa, y cuantos empleados desempeÃ±an cada uno de ellos?

JOB         COUNT(*)
--------- ----------
ANALYST            2
CLERK              4
MANAGER            3
PRESIDENT          1
SALESMAN           4

5 filas seleccionadas.*/
SELECT JOB, COUNT(*)
FROM empleados
GROUP BY JOB
ORDER BY JOB;


/* 69. Halla la suma de salarios de cada departamento.

DNAME            SUM(SAL)
-------------- ----------
ACCOUNTING           8750
RESEARCH            10875
SALES                9400

3 filas seleccionadas.*/

SELECT d.DNAME, A.SAL
FROM departamentos d
JOIN 
(SELECT SUM(SAL) AS SAL, DEPTNO
FROM empleados
GROUP BY DEPTNO)A
ON d.DEPTNO=A.DEPTNO
GROUP BY d.DNAME;



/* 70. Obten todos los departamentos sin empleados.

DNAME
--------------
OPERATIONS

1 fila seleccionada.*/
SELECT d.DNAME
FROM departamentos d
LEFT JOIN empleados e
ON d.DEPTNO=e.DEPTNO 
WHERE e.EMPNO IS NULL;




/* 71. Halla los empleados que no tienen a otro empleado a sus ordenes.

ENAME
----------
SMITH
ALLEN
WARD
MARTIN
TURNER
ADAMS
JAMES
MILLER

8 filas seleccionadas.*/



/* 72. Â¿Cuantos empleados hay en cada departamento, y cual es la media anual 
del salario de cada uno (el salario almacenado es mensual)? 
Indique el nombre del departamento para clarificar el resultado.

DNAME             AVG(SAL)   COUNT(*)
-------------- ---------- ----------
ACCOUNTING     2916,66667          3
RESEARCH             2175          5
SALES          1566,66667          6

3 filas seleccionadas.*/
SELECT d.DNAME, T.SAL, T.COUNT
FROM departamentos d
JOIN
(SELECT AVG(SAL) AS SAL, DEPTNO, COUNT(*) AS COUNT
FROM empleados 
GROUP BY DEPTNO)T
ON d.DEPTNO = T.DEPTNO;


/* 73. Halla los empleados del departamento 30, por orden descendente de comision

ENAME
----------
BLAKE
JAMES
MARTIN
WARD
ALLEN
TURNER

6 filas seleccionadas.*/
SELECT ENAME
FROM empleados
WHERE DEPTNO=30
ORDER BY -COMM ASC;



/* 74. Obten los empleados que trabajan en Dallas o New York.

ENAME
----------
CLARK
KING
MILLER
SMITH
ADAMS
FORD
SCOTT
JONES

8 filas seleccionadas.*/
SELECT ENAME
FROM empleados
WHERE DEPTNO IN (SELECT DEPTNO FROM departamentos WHERE LOC IN ('DALLAS','NEW YORK'))
ORDER BY DEPTNO;



/* 75. Obten un listado en el que se reflejen los empleados y los nombres 
de sus jefes. En el listado deben aparecer todos los empleados, aunque no 
tengan jefe, poniendo un nulo el nombre de este.

ENAME      ENAME
---------- ----------
FORD       JONES
SCOTT      JONES
JAMES      BLAKE
TURNER     BLAKE
MARTIN     BLAKE
WARD       BLAKE
ALLEN      BLAKE
MILLER     CLARK
ADAMS      SCOTT
CLARK      KING
BLAKE      KING
JONES      KING
SMITH      FORD
KING

14 filas seleccionadas.*/




/* 76. Lista los empleados que tengan el mayor salario de su departamento, 
mostrando el nombre del empleado, su salario y el nombre del departamento.

ENAME             SAL DNAME
---------- ---------- --------------
BLAKE            2850 SALES
FORD             3000 RESEARCH
SCOTT            3000 RESEARCH
KING             5000 ACCOUNTING

4 filas seleccionadas.*/
SELECT A.ENAME, A.SAL, D.DNAME
FROM departamentos D
JOIN(SELECT ENAME, SAL, DEPTNO
FROM empleados
WHERE SAL IN (SELECT MAX(SAL) FROM empleados GROUP BY DEPTNO))A
ON D.DEPTNO=A.DEPTNO
ORDER BY DNAME DESC;


/* 77. Deseamos saber cuantos empleados supervisa cada jefe. Para ello, obten un listado 
en el que se reflejen el codigo y el nombre de cada jefe, junto al numero de empleados 
que supervisa directamente. Como puede haber empleados sin jefe, para estos se indicara 
solo el numero de ellos, y los valores restantes (codigo y nombre del jefe) se dejaran como nulos.

ENAME           EMPNO NUM_EMPLEADOS
---------- ---------- -------------
                                  1
FORD             7902             1
KING             7839             3
BLAKE            7698             5
CLARK            7782             1
JONES            7566             2
SCOTT            7788             1

7 filas seleccionadas.*/




/* 78. Hallar el departamento cuya suma de salarios sea la mas alta, mostrando esta 
suma de salarios y el nombre del departamento

DNAME             SUM(SAL)
-------------- ----------
RESEARCH            10875

1 fila seleccionada.*/
SELECT D.DNAME, MAX(T.SAL)
FROM departamentos D
JOIN
(SELECT SUM(SAL) AS SAL, DEPTNO
FROM empleados
GROUP BY DEPTNO)T
ON D.DEPTNO=T.DEPTNO






/* 79. Obten los datos de los empleados que cobren los dos mayores salarios de la empresa. 
(Nota: Procure hacer la consulta de forma que sea facil obtener los empleados de los N mayores salarios)

     EMPNO ENAME      JOB              MGR HIREDATE        SAL       COMM     DEPTNO
---------- ---------- --------- ---------- -------- ---------- ---------- ----------
      7839 KING       PRESIDENT            17/11/81       5000                    10
      7788 SCOTT      ANALYST         7566 09/12/82       3000                    20
      7902 FORD       ANALYST         7566 03/12/81       3000                    20
      
3 filas seleccionadas.*/




/* 80. Obten las localidades que no tienen departamentos sin empleados y en las que 
trabajen al menos cuatro empleados. Indica tambien el numero de empleados que trabajan 
en esas localidades. (Nota: Por ejemplo, puede que en A Coru~na existan dos departamentos, 
uno con mas de cuatro empleados y otro sin empleados, en tal caso, A Coru~na no debe 
aparecer en el resultado, puesto que tiene un departamento SIN EMPLEADOS, a pesar de tener 
otro con empleados Y tener mas de cuatro empleados EN TOTAL. 
ATENCION, la restriccion de que tienen que ser cuatro empleados se refiere a la totalidad 
de los departamentos de la localidad.)

LOC           NUMERO_EMPLEADOS
------------- ----------------
DALLAS                       5
CHICAGO                      6

2 filas seleccionadas.*/
