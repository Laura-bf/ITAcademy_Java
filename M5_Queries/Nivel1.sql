/* 1. Obtener todos los datos de todos los empleados. 

     EMPNO ENAME      JOB              MGR HIREDATE        SAL       COMM     DEPTNO
---------- ---------- --------- ---------- -------- ---------- ---------- ----------
      7369 SMITH      CLERK           7902 17/12/80        800                    20
      7499 ALLEN      SALESMAN        7698 20/02/81       1600        300         30
      7521 WARD       SALESMAN        7698 22/02/81       1250        500         30
      7566 JONES      MANAGER         7839 02/04/81       2975                    20
      7654 MARTIN     SALESMAN        7698 28/09/81       1250       1400         30
      7698 BLAKE      MANAGER         7839 01/05/81       2850                    30
      7782 CLARK      MANAGER         7839 09/06/81       2450                    10
      7788 SCOTT      ANALYST         7566 09/12/82       3000                    20
      7839 KING       PRESIDENT            17/11/81       5000                    10
      7844 TURNER     SALESMAN        7698 08/09/81       1500          0         30
      7876 ADAMS      CLERK           7788 12/01/83       1100                    20
      7900 JAMES      CLERK           7698 03/12/81        950                    30
      7902 FORD       ANALYST         7566 03/12/81       3000                    20
      7934 MILLER     CLERK           7782 23/01/82       1300                    10

14 filas seleccionadas.*/
SELECT EMPNO, ENAME, JOB, IFNULL(MGR,'') AS MGR, date_format(hiredate, '%d/%m/%y') AS HIREDATE, SAL, IFNULL(COMM,'') AS COMM, DEPTNO
FROM empleados;

/* 2. Obtener todos los datos de todos los departamentos.

    DEPTNO DNAME          LOC
---------- -------------- -------------
        10 ACCOUNTING     NEW YORK
        20 RESEARCH       DALLAS
        30 SALES          CHICAGO
        40 OPERATIONS     BOSTON
        
4 filas seleccionadas.*/
SELECT *
FROM departamentos;

/* 3. Obtener todos los datos de los administrativos (su trabajo es, en ingles, 'CLERK').

     EMPNO ENAME      JOB              MGR HIREDATE        SAL       COMM     DEPTNO
---------- ---------- --------- ---------- -------- ---------- ---------- ----------
      7369 SMITH      CLERK           7902 17/12/80        800                    20
      7876 ADAMS      CLERK           7788 12/01/83       1100                    20
      7900 JAMES      CLERK           7698 03/12/81        950                    30
      7934 MILLER     CLERK           7782 23/01/82       1300                    10
4 filas seleccionadas.*/
SELECT EMPNO, ENAME, JOB, IFNULL(MGR,'') AS MGR, date_format(hiredate, '%d/%m/%y') AS HIREDATE, SAL, IFNULL(COMM,'') AS COMM, DEPTNO
FROM empleados 
WHERE JOB = 'CLERK';


/* 4. Idem, pero ordenado por el nombre.
     EMPNO ENAME      JOB              MGR HIREDATE        SAL       COMM     DEPTNO
---------- ---------- --------- ---------- -------- ---------- ---------- ----------
      7876 ADAMS      CLERK           7788 12/01/83       1100                    20
      7900 JAMES      CLERK           7698 03/12/81        950                    30
      7934 MILLER     CLERK           7782 23/01/82       1300                    10
      7369 SMITH      CLERK           7902 17/12/80        800                    20
      
4 filas seleccionadas.*/
SELECT EMPNO, ENAME, JOB, IFNULL(MGR,'') AS MGR, date_format(hiredate, '%d/%m/%y') AS HIREDATE, SAL, IFNULL(COMM,'') AS COMM, DEPTNO
FROM empleados
WHERE JOB='CLERK'
ORDER BY ENAME;


/* 5. Obten el mismo resultado de la pregunta anterior, pero ahora ordenando sólo por deptno en sentido descendente:

     EMPNO ENAME      JOB              MGR HIREDATE        SAL       COMM     DEPTNO
---------- ---------- --------- ---------- -------- ---------- ---------- ----------
      7900 JAMES      CLERK           7698 03/12/81        950                    30
      7369 SMITH      CLERK           7902 17/12/80        800                    20
      7876 ADAMS      CLERK           7788 12/01/83       1100                    20
      7934 MILLER     CLERK           7782 23/01/82       1300                    10
      
4 filas seleccionadas.*/
SELECT EMPNO, ENAME, JOB, IFNULL(MGR,'') AS MGR, date_format(hiredate, '%d/%m/%y') AS HIREDATE, SAL, IFNULL(COMM,'') AS COMM, DEPTNO
FROM empleados
WHERE JOB = 'CLERK'
ORDER BY DEPTNO DESC;


/* 6. Obten el Obten (codigo), nombre y salario de los empleados.

     EMPNO ENAME             SAL
---------- ---------- ----------
      7369 SMITH             800
      7499 ALLEN            1600
      7521 WARD             1250
      7566 JONES            2975
      7654 MARTIN           1250
      7698 BLAKE            2850
      7782 CLARK            2450
      7788 SCOTT            3000
      7839 KING             5000
      7844 TURNER           1500
      7876 ADAMS            1100
      7900 JAMES             950
      7902 FORD             3000
      7934 MILLER           1300

14 filas seleccionadas.*/
SELECT EMPNO, ENAME, SAL
FROM empleados;


/* 7. Lista los nombres de todos los departamentos.

DNAME
--------------
ACCOUNTING
RESEARCH
SALES
OPERATIONS

4 filas seleccionadas.*/
SELECT DISTINCT DNAME
FROM departamentos;


/* 8. Idem, pero ordenandolos por nombre.

DNAME
--------------
ACCOUNTING
OPERATIONS
RESEARCH
SALES

4 filas seleccionadas.*/
SELECT DNAME
FROM departamentos
ORDER BY DNAME;



/* 9. Idem, pero ordenandolo por la ciudad (no se debe seleccionar la ciudad en el resultado).

DNAME
--------------
OPERATIONS
SALES
RESEARCH
ACCOUNTING

4 filas seleccionadas.*/
SELECT DNAME
FROM departamentos
ORDER BY LOC;



/* 10. Idem, pero el resultado debe mostrarse ordenado por la ciudad en orden inverso.

DNAME
--------------
ACCOUNTING
RESEARCH
SALES
OPERATIONS

4 filas seleccionadas.*/
SELECT DNAME 
FROM departamentos
ORDER BY LOC DESC;


/* 11. Obtener el nombre y empleo de todos los empleados, ordenado por salario.

ENAME      JOB
---------- ---------
SMITH      CLERK
JAMES      CLERK
ADAMS      CLERK
WARD       SALESMAN
MARTIN     SALESMAN
MILLER     CLERK
TURNER     SALESMAN
ALLEN      SALESMAN
CLARK      MANAGER
BLAKE      MANAGER
JONES      MANAGER
SCOTT      ANALYST
FORD       ANALYST
KING       PRESIDENT

14 filas seleccionadas.*/
SELECT ENAME, JOB
FROM empleados
ORDER BY SAL;



/* 12. Obtener el nombre y empleo de todos los empleados, ordenado primero por su trabajo y luego por su salario.

ENAME      JOB
---------- ---------
SCOTT      ANALYST
FORD       ANALYST
SMITH      CLERK
JAMES      CLERK
ADAMS      CLERK
MILLER     CLERK
CLARK      MANAGER
BLAKE      MANAGER
JONES      MANAGER
KING       PRESIDENT
WARD       SALESMAN
MARTIN     SALESMAN
TURNER     SALESMAN
ALLEN      SALESMAN

14 filas seleccionadas.*/
SELECT ENAME, JOB
FROM empleados
ORDER BY JOB, SAL;


/* 13. Idem, pero ordenando inversamente por empleo y normalmente por salario.

ENAME      JOB
---------- ---------
WARD       SALESMAN
MARTIN     SALESMAN
TURNER     SALESMAN
ALLEN      SALESMAN
KING       PRESIDENT
CLARK      MANAGER
BLAKE      MANAGER
JONES      MANAGER
SMITH      CLERK
JAMES      CLERK
ADAMS      CLERK
MILLER     CLERK
SCOTT      ANALYST
FORD       ANALYST

14 filas seleccionadas.*/
SELECT ENAME, JOB
FROM empleados
ORDER BY JOB DESC, SAL;

/* 14. Obten los salarios y las comisiones de los empleados del departamento 30.

       SAL       COMM
---------- ----------
      1600        300
      1250        500
      1250       1400
      2850
      1500          0
       950

6 filas seleccionadas.*/
SELECT SAL, IFNULL(COMM,'') AS COMM
FROM empleados
WHERE DEPTNO = 30;


/* 15. Idem, pero ordenado por comision.

       SAL       COMM
---------- ----------
      1500          0
      1600        300
      1250        500
      1250       1400
      2850
       950

6 filas seleccionadas.*/
SELECT SAL, IFNULL(COMM,'') AS COMM
FROM empleados
WHERE DEPTNO = 30
ORDER BY -COMM DESC;

/* 16. (a) Obten las comisiones de todos los empleados.

      COMM
----------

       300
       500

      1400




         0





14 filas seleccionadas.*/
SELECT IFNULL(COMM,'') AS COMM
FROM empleados;



/* 16. (b) Obten las comisiones de los empleados de forma que no se repitan.

      COMM
----------
         0
       300
       500
      1400
      
4 filas seleccionadas.*/
SELECT DISTINCT COMM
FROM empleados
WHERE COMM IS NOT NULL
ORDER BY COMM;



/* 17. Obten el nombre de empleado y su comision SIN FILAS repetidas.

ENAME            COMM
---------- ----------
ADAMS
ALLEN             300
BLAKE
CLARK
FORD
JAMES
JONES
KING
MARTIN           1400
MILLER
SCOTT
SMITH
TURNER              0
WARD              500

14 filas seleccionadas.*/
SELECT DISTINCT ENAME, IFNULL(COMM,'') AS COMM
FROM empleados
ORDER BY ENAME;


/* 18. Obten los nombres de los empleados y sus salarios, de forma que no se repitan filas.

ENAME             SAL
---------- ----------
ADAMS            1100
ALLEN            1600
BLAKE            2850
CLARK            2450
FORD             3000
JAMES             950
JONES            2975
KING             5000
MARTIN           1250
MILLER           1300
SCOTT            3000
SMITH             800
TURNER           1500
WARD             1250

14 filas seleccionadas.*/
SELECT DISTINCTROW ENAME, SAL
FROM empleados
ORDER BY ENAME;

/* 19. Obten las comisiones de los empleados y sus Obtens de departamento, de forma que no serepitan filas.

COMMISSION     DEPTNO
---------- ----------
                   10
                   20
                   30
         0         30
       300         30
       500         30
      1400         30

7 filas seleccionadas.*/
SELECT DISTINCT IFNULL(COMM,'') AS COMMISSION, DEPTNO
FROM empleados
ORDER BY DEPTNO, COMM;


/* 20. Obten los nuevos salarios de los empleados del departamento 30, que resultar³an 
de sumar a su salario una gratificacion de 1000. Muestra tambien los nombres de los empleados.

ENAME        SAL+1000
---------- ----------
ALLEN            2600
WARD             2250
MARTIN           2250
BLAKE            3850
TURNER           2500
JAMES            1950

6 filas seleccionadas.*/
SELECT ENAME, SAL+1000
FROM empleados
WHERE DEPTNO=30;


/* 21. Lo mismo que la anterior, pero mostrando tambien su salario original, y 
haz que la columna que almacena el nuevo salario se denomine NUEVO SALARIO.

ENAME             SAL NUEVO_SALARIO
---------- ---------- -------------
ALLEN            1600          2600
WARD             1250          2250
MARTIN           1250          2250
BLAKE            2850          3850
TURNER           1500          2500
JAMES             950          1950

6 filas seleccionadas.*/
SELECT ENAME, SAL, (SAL+1000) AS NUEVO_SALARIO
FROM empleados
WHERE DEPTNO=30;

/* 22. Halla los empleados que tienen una comision superior a la mitad de su salario.

ENAME
----------
MARTIN

1 fila seleccionada.*/
SELECT ENAME
FROM empleados
WHERE COMM > SAL/2;

/* 23. Halla los empleados que no tienen comision, o que la tengan menor o igual que el 25% de su salario.

ENAME
----------
SMITH
ALLEN
JONES
BLAKE
CLARK
SCOTT
KING
TURNER
ADAMS
JAMES
FORD
MILLER

12 filas seleccionadas.*/
SELECT ENAME
FROM empleados
WHERE COMM < 0 OR COMM IS NULL OR COMM <= SAL*0.25;


/* 24. Obten una lista de nombres de empleados y sus salarios, de forma que en 
la salida aparezca en todas las filas \Nombre:" y \Salario:" antes del respectivo campo. 

		 NOMBRE     SALARIO
---------------     -------------------------------------------------
Nombre:  SMITH      Salario: 800
Nombre:  ALLEN      Salario: 1600
Nombre:  WARD       Salario: 1250
Nombre:  JONES      Salario: 2975
Nombre:  MARTIN     Salario: 1250
Nombre:  BLAKE      Salario: 2850
Nombre:  CLARK      Salario: 2450
Nombre:  SCOTT      Salario: 3000
Nombre:  KING       Salario: 5000
Nombre:  TURNER     Salario: 1500
Nombre:  ADAMS      Salario: 1100
Nombre:  JAMES      Salario: 950
Nombre:  FORD       Salario: 3000
Nombre:  MILLER     Salario: 1300

14 filas seleccionadas.*/
SELECT CONCAT('Nombre: ', ENAME) AS NOMBRE, CONCAT('Salario: ', SAL) AS SALARIO
FROM empleados;


/* 25. Hallar el codigo, salario y comision de los empleados cuyo codigo sea mayor que 7500.

     EMPNO        SAL       COMM
---------- ---------- ----------
      7521       1250        500
      7566       2975
      7654       1250       1400
      7698       2850
      7782       2450
      7788       3000
      7839       5000
      7844       1500          0
      7876       1100
      7900        950
      7902       3000
      7934       1300

12 filas seleccionadas.*/
SELECT EMPNO, SAL, IFNULL(COMM,'') AS COMM
FROM empleados
WHERE EMPNO > 7500;


/* 26. Obten todos los datos de los empleados que esten a partir de la J, inclusive.
NOTA: Para ello usa la funcion left de PLMySql   

     EMPNO ENAME      JOB              MGR HIREDATE        SAL       COMM     DEPTNO
---------- ---------- --------- ---------- -------- ---------- ---------- ----------
      7369 SMITH      CLERK           7902 17/12/80        800                    20
      7521 WARD       SALESMAN        7698 22/02/81       1250        500         30
      7566 JONES      MANAGER         7839 02/04/81       2975                    20
      7654 MARTIN     SALESMAN        7698 28/09/81       1250       1400         30
      7788 SCOTT      ANALYST         7566 09/12/82       3000                    20
      7839 KING       PRESIDENT            17/11/81       5000                    10
      7844 TURNER     SALESMAN        7698 08/09/81       1500          0         30
      7900 JAMES      CLERK           7698 03/12/81        950                    30
      7934 MILLER     CLERK           7782 23/01/82       1300                    10

9 filas seleccionadas.*/
SELECT EMPNO, ENAME, JOB, IFNULL(MGR,'') AS MGR, date_format(hiredate, '%d/%m/%y') AS HIREDATE, SAL, IFNULL(COMM,'') AS COMM, DEPTNO
FROM empleados
WHERE LEFT(ENAME,1) IN ('J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');



/* 27. Obten el salario, comision y salario total (salario+comision) de los empleados 
con comision, ordenando el resultado por numero de empleado.

       SAL       COMM SALARIO_TOTAL
---------- ---------- -------------
      1600        300          1900
      1250        500          1750
      1250       1400          2650
      1500          0          1500
      
5 filas seleccionadas.*/
SELECT SAL, COMM, (SAL+COMM) AS SALARIO_TOTAL
FROM empleados
WHERE COMM IS NOT NULL
ORDER BY EMPNO;


/* 28. Lista la misma informacion, pero para los empleados que no tienen comision.

       SAL       COMM SALARIO_TOTAL
---------- ---------- -------------
       800
      2975
      2850
      2450
      3000
      5000
      1100
       950
      3000
      1300

10 filas seleccionadas.*/
SELECT SAL, IFNULL(COMM, '') AS COMM, IFNULL((COMM + SAL),'') AS SALARIO_TOTAL
FROM empleados
WHERE COMM IS NULL
ORDER BY EMPNO;



/* 29. Muestra el nombre de los empleados que, teniendo un salario superior 
a 1000, tengan como jefe al empleado cuyo codigo es 7698.

ENAME
----------
ALLEN
WARD
MARTIN
TURNER

4 filas seleccionadas.*/
SELECT ENAME
FROM empleados
WHERE SAL>1000 AND MGR=7698;


/* 30. Muestra el nombre de los empleados que, teniendo un salario inferior 
a 1000, no tengan como jefe al empleado cuyo codigo es 7698.


ENAME
----------
SMITH

1 fila seleccionada.*/
SELECT ENAME
FROM empleados
WHERE SAL < 1000 AND MGR != 7698;

/* 31. Indica para cada empleado el porcentaje que supone su comision sobre su 
salario, ordenando el resultado por el nombre del mismo.

PORCENTAJE
----------

15,7894737






52,8301887



0
28,5714286

14 filas seleccionadas.*/
SELECT IFNULL((COMM/(SAL+COMM)),'') AS PORCENTAJE
FROM empleados
ORDER BY ENAME;


/* 32. Hallar los empleados del departamento 10 cuyo nombre no contiene la cadena LA.

ENAME
----------
KING
MILLER

2 filas seleccionadas.*/
SELECT ENAME
FROM empleados
WHERE DEPTNO=10 AND ENAME NOT LIKE ('%LA%');



/* 33. Obten los empleados que no son supervisados por ningun otro.

ENAME
----------
KING

1 fila seleccionada.*/
SELECT ENAME
FROM empleados
WHERE MGR IS NULL;


/* 34. Obten los nombres de los departamentos que no sean Ventas (SALES) ni 
investigacion (RESEARCH). Ordena el resultado por la localidad del departamento.

DNAME
--------------
OPERATIONS
ACCOUNTING

2 filas seleccionadas.*/
SELECT DNAME
FROM departamentos
WHERE DNAME NOT IN('SALES','RESEARCH') 
ORDER BY LOC;


/* 35. Deseamos conocer el nombre de los empleados y el codigo del departamento 
de los administrativos(CLERK) que no trabajan en el departamento 10, y cuyo 
salario es superior a 800, ordenado por fecha de contratacion.

ENAME          DEPTNO
---------- ----------
JAMES              30
ADAMS              20

2 filas seleccionadas.*/
SELECT ENAME, DEPTNO
FROM empleados
WHERE JOB = 'CLERK' AND DEPTNO != 10 AND SAL > 800
ORDER BY HIREDATE;



/* 36. Para los empleados que tengan comision, obten sus nombres y el cociente entre 
su salario y su comision (excepto cuando la comision sea cero), ordenando el resultado por nombre.

ENAME        COCIENTE
---------- ----------
ALLEN      5,33333333
MARTIN     ,892857143
WARD              2,5

3 filas seleccionadas.*/
SELECT ENAME, (SAL/COMM) AS COCIENTE
FROM empleados
WHERE COMM IS NOT NULL AND COMM !=0
ORDER BY ENAME;



/* 37. Lista toda la informacion sobre los empleados cuyo nombre completo tenga exactamente 5 caracteres.

     EMPNO ENAME      JOB              MGR HIREDATE        SAL       COMM     DEPTNO
---------- ---------- --------- ---------- -------- ---------- ---------- ----------
      7369 SMITH      CLERK           7902 17/12/80        800                    20
      7499 ALLEN      SALESMAN        7698 20/02/81       1600        300         30
      7566 JONES      MANAGER         7839 02/04/81       2975                    20
      7698 BLAKE      MANAGER         7839 01/05/81       2850                    30
      7782 CLARK      MANAGER         7839 09/06/81       2450                    10
      7788 SCOTT      ANALYST         7566 09/12/82       3000                    20
      7876 ADAMS      CLERK           7788 12/01/83       1100                    20
      7900 JAMES      CLERK           7698 03/12/81        950                    30

8 filas seleccionadas.*/
SELECT EMPNO, ENAME, JOB, IFNULL(MGR,'') AS MGR, date_format(hiredate, '%d/%m/%y') AS HIREDATE, SAL, IFNULL(COMM,'') AS COMM, DEPTNO
FROM empleados
WHERE LENGTH(ENAME)=5;



/* 38. Lo mismo, pero para los empleados cuyo nombre tenga al menos cinco letras.

     EMPNO ENAME      JOB              MGR HIREDATE        SAL       COMM     DEPTNO
---------- ---------- --------- ---------- -------- ---------- ---------- ----------
      7369 SMITH      CLERK           7902 17/12/80        800                    20
      7499 ALLEN      SALESMAN        7698 20/02/81       1600        300         30
      7566 JONES      MANAGER         7839 02/04/81       2975                    20
      7654 MARTIN     SALESMAN        7698 28/09/81       1250       1400         30
      7698 BLAKE      MANAGER         7839 01/05/81       2850                    30
      7782 CLARK      MANAGER         7839 09/06/81       2450                    10
      7788 SCOTT      ANALYST         7566 09/12/82       3000                    20
      7844 TURNER     SALESMAN        7698 08/09/81       1500          0         30
      7876 ADAMS      CLERK           7788 12/01/83       1100                    20
      7900 JAMES      CLERK           7698 03/12/81        950                    30
      7934 MILLER     CLERK           7782 23/01/82       1300                    10

11 filas seleccionadas.*/
SELECT EMPNO, ENAME, JOB, IFNULL(MGR,''), date_format(hiredate, '%d/%m/%y') AS HIREDATE, SAL, IFNULL(COMM,'') AS COMM, DEPTNO
FROM empleados
WHERE LENGTH(ENAME) >= 5;




/* 39. Halla los datos de los empleados que, o bien su nombre empieza por A y su 
salario es superior a 1000, o bien reciben comision y trabajan en el departamento 30.

     EMPNO ENAME      JOB              MGR HIREDATE        SAL       COMM     DEPTNO
---------- ---------- --------- ---------- -------- ---------- ---------- ----------
      7499 ALLEN      SALESMAN        7698 20/02/81       1600        300         30
      7521 WARD       SALESMAN        7698 22/02/81       1250        500         30
      7654 MARTIN     SALESMAN        7698 28/09/81       1250       1400         30
      7876 ADAMS      CLERK           7788 12/01/83       1100                    20
      
4 filas seleccionadas.*/
SELECT EMPNO, ENAME, JOB, IFNULL(MGR,''), date_format(hiredate, '%d/%m/%y') AS HIREDATE, SAL, IFNULL(COMM,'') AS COMM, DEPTNO
FROM empleados
WHERE (ENAME LIKE ('A%') AND SAL > 1000) OR COMM>0 AND DEPTNO=30;



/* 40. Halla el nombre, el salario y el sueldo total de todos los empleados, ordenando 
el resultado primero por salario y luego por el sueldo total. En el caso de que no 
tenga comision, el sueldo total debe reflejar solo el salario.

ENAME             SAL SALARIO_TOTAL
---------- ---------- -------------
SMITH             800           800
JAMES             950           950
ADAMS            1100          1100
WARD             1250          1750
MARTIN           1250          2650
MILLER           1300          1300
TURNER           1500          1500
ALLEN            1600          1900
CLARK            2450          2450
BLAKE            2850          2850
JONES            2975          2975
SCOTT            3000          3000
FORD             3000          3000
KING             5000          5000

14 filas seleccionadas.*/
SELECT ENAME, SAL, (CASE
						WHEN COMM > 0 THEN COMM+SAL 
						ELSE SAL 
					END) AS SALARIO_TOTAL
FROM empleados
ORDER BY SAL, SALARIO_TOTAL;
