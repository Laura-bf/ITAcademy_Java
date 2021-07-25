/* 41. Obten el nombre, salario y la comision de los empleados que perciben 
un salario que esta entre la mitad de la comision y la propia comision.

ENAME             SAL       COMM
---------- ---------- ----------
MARTIN           1250       1400

1 fila seleccionada.*/
SELECT ENAME, SAL, COMM
FROM empleados
WHERE SAL BETWEEN COMM/2 AND COMM;



/* 42. Obten el nombre, salario y la comision de los empleados que perciben 
un salario complementario al caso 41: que sea superior a la comision o inferior a la mitad de la comision.


ENAME             SAL       COMM
---------- ---------- ----------
ALLEN            1600        300
WARD             1250        500
TURNER           1500          0

3 filas seleccionadas.*/
SELECT ENAME, SAL, COMM
FROM empleados
WHERE SAL NOT BETWEEN COMM/2 AND COMM;



/* 43. Lista los nombres y empleos de aquellos empleados cuyo empleo acaba 
en MAN y cuyo nombre empieza por A.

ENAME      JOB
---------- ---------
ALLEN      SALESMAN

1 fila seleccionada.*/
SELECT ENAME, JOB
FROM empleados
WHERE JOB LIKE ('%MAN') AND ENAME LIKE ('A%');



/* 44. Intenta resolver la pregunta anterior con un predicado simple, es decir, 
de forma que en la clausula WHERE no haya conectores logicos como AND, OR, etc. 
Si ayuda a resolver la pregunta, se puede suponer que el nombre del empleado tiene al menos cinco letras.

ENAME      JOB
---------- ---------
ALLEN      SALESMAN

1 fila seleccionada.*/
SELECT ENAME, JOB
FROM empleados
WHERE JOB LIKE ('%MAN')
HAVING ENAME LIKE ('A%');




/* 45. Halla los nombres de los empleados cuyo nombre tiene como maximo cinco caracteres.

ENAME
----------
SMITH
ALLEN
WARD
JONES
BLAKE
CLARK
SCOTT
KING
ADAMS
JAMES
FORD

11 filas seleccionadas.*/
SELECT ENAME
FROM empleados
WHERE LENGTH(ENAME)<=5;



/* 46. Suponiendo que el a~no proximo la subida del sueldo total de cada empleado sera del 60 %, 
y el siguiente del 70 %, halla los nombres y el salario total actual, del a~no proximo y del 
siguiente, de cada empleado. Indique ademas con SI o NO, si el empleado tiene comision. 

ENAME      COMM     SALARIO_TOTAL SALARIO_DEL_PROXIMO_AÃ‘O SALARIO_DEL_SIGUIENTE_AÃ‘O
---------- -------- ------------- ----------------------- -------------------------
ADAMS      NOSESABE          1100                    1760                      2992
ALLEN      SI                1900                    3040                      5168
BLAKE      NOSESABE          2850                    4560                      7752
CLARK      NOSESABE          2450                    3920                      6664
FORD       NOSESABE          3000                    4800                      8160
JAMES      NOSESABE           950                    1520                      2584
JONES      NOSESABE          2975                    4760                      8092
KING       NOSESABE          5000                    8000                     13600
MARTIN     SI                2650                    4240                      7208
MILLER     NOSESABE          1300                    2080                      3536
SCOTT      NOSESABE          3000                    4800                      8160
SMITH      NOSESABE           800                    1280                      2176
TURNER     NO                1500                    2400                      4080
WARD       SI                1750                    2800                      4760

14 filas seleccionadas.*/

SELECT ENAME, COMM, SALARIO_TOTAL, (SALARIO_TOTAL*1.6) AS SALARIO_PROXIMO_AÑO, (SALARIO_TOTAL*1.6*1.7) AS SALARIO_SIGUIENTE_AÑO
FROM 
	(SELECT ENAME, (CASE
					WHEN COMM IS NULL THEN 'NOSESABE'
					WHEN COMM>0 THEN 'SI'
					ELSE 'NO'
				END) AS COMM,
				(CASE
					WHEN COMM > 0 THEN SAL+COMM
					ELSE SAL
				END) AS SALARIO_TOTAL
	FROM empleados)t
ORDER BY ENAME;
        


/* 47. Lista los nombres y fecha de contratacion de aquellos empleados que no son vendedores (SALESMAN).

ENAME      HIREDATE
---------- --------
SMITH      17/12/80
JONES      02/04/81
BLAKE      01/05/81
CLARK      09/06/81
SCOTT      09/12/82
KING       17/11/81
ADAMS      12/01/83
JAMES      03/12/81
FORD       03/12/81
MILLER     23/01/82

10 filas seleccionadas.*/
SELECT ENAME, DATE_FORMAT(HIREDATE, '%d/%m/%y') AS HIREDATE
FROM empleados
WHERE JOB !='SALESMAN';



/* 48. Obten la informacion disponible de los empleados cuyo numero es uno de los 
siguientes: 7844, 7900, 7521, 7521, 7782, 7934, 7678 y 7369, pero que no sea uno 
de los siguientes: 7902, 7839, 7499 ni 7878. La sentencia no debe complicarse 
innecesariamente, y debe dar el resultado correcto independientemente de lo 
empleados almacenados en la base de datos.

     EMPNO ENAME      JOB              MGR HIREDATE        SAL       COMM     DEPTNO
---------- ---------- --------- ---------- -------- ---------- ---------- ----------
      7369 SMITH      CLERK           7902 17/12/80        800                    20
      7521 WARD       SALESMAN        7698 22/02/81       1250        500         30
      7782 CLARK      MANAGER         7839 09/06/81       2450                    10
      7844 TURNER     SALESMAN        7698 08/09/81       1500          0         30
      7900 JAMES      CLERK           7698 03/12/81        950                    30
      7934 MILLER     CLERK           7782 23/01/82       1300                    10

6 filas seleccionadas.*/
SELECT EMPNO, ENAME, JOB, IFNULL(MGR,'') AS MGR, date_format(hiredate, '%d/%m/%y') AS HIREDATE, SAL, IFNULL(COMM,'') AS COMM, DEPTNO
FROM empleados
WHERE EMPNO IN (7844, 7900, 7521, 7521, 7782, 7934, 7678, 7369) AND EMPNO NOT IN (7902, 7839, 7499, 7878);



/* 49. Ordena los empleados por su codigo de departamento, y luego de manera descendente por su numero de empleado.

     EMPNO ENAME      JOB              MGR HIREDATE        SAL       COMM     DEPTNO
---------- ---------- --------- ---------- -------- ---------- ---------- ----------
      7934 MILLER     CLERK           7782 23/01/82       1300                    10
      7839 KING       PRESIDENT            17/11/81       5000                    10
      7782 CLARK      MANAGER         7839 09/06/81       2450                    10
      7902 FORD       ANALYST         7566 03/12/81       3000                    20
      7876 ADAMS      CLERK           7788 12/01/83       1100                    20
      7788 SCOTT      ANALYST         7566 09/12/82       3000                    20
      7566 JONES      MANAGER         7839 02/04/81       2975                    20
      7369 SMITH      CLERK           7902 17/12/80        800                    20
      7900 JAMES      CLERK           7698 03/12/81        950                    30
      7844 TURNER     SALESMAN        7698 08/09/81       1500          0         30
      7698 BLAKE      MANAGER         7839 01/05/81       2850                    30
      7654 MARTIN     SALESMAN        7698 28/09/81       1250       1400         30
      7521 WARD       SALESMAN        7698 22/02/81       1250        500         30
      7499 ALLEN      SALESMAN        7698 20/02/81       1600        300         30

14 filas seleccionadas.*/
SELECT EMPNO, ENAME, JOB, IFNULL(MGR,'') AS MGR, date_format(hiredate, '%d/%m/%y') AS HIREDATE, SAL, IFNULL(COMM,'') AS COMM, DEPTNO
FROM empleados
ORDER BY DEPTNO, EMPNO DESC;


/* 50. Para los empleados que tengan como jefe a un empleado con codigo mayor que 
el suyo, obten los que reciben de salario mas de 1000 y menos de 2000, o que estan en el departamento 30.

ENAME
----------
ALLEN
WARD
MARTIN
BLAKE

4 filas seleccionadas.*/
SELECT ENAME 
FROM empleados
WHERE MGR > EMPNO AND (SAL BETWEEN 1000 AND 2000 OR DEPTNO = 30);



/* 51. Obten el salario mas alto de la empresa, el total destinado a 
comisiones y el numero de empleados.

  MAX(SAL)  SUM(COMM)   COUNT(*)
---------- ---------- ----------
      5000       2200         14
      
1 fila seleccionada.*/
SELECT MAX(SAL), SUM(COMM), COUNT(*)
FROM empleados;



/* 52. Halla los datos de los empleados cuyo salario es mayor que el del empleado de codigo 7934, ordenando por el salario.

     EMPNO ENAME      JOB              MGR HIREDATE        SAL       COMM     DEPTNO
---------- ---------- --------- ---------- -------- ---------- ---------- ----------
      7844 TURNER     SALESMAN        7698 08/09/81       1500          0         30
      7499 ALLEN      SALESMAN        7698 20/02/81       1600        300         30
      7782 CLARK      MANAGER         7839 09/06/81       2450                    10
      7698 BLAKE      MANAGER         7839 01/05/81       2850                    30
      7566 JONES      MANAGER         7839 02/04/81       2975                    20
      7788 SCOTT      ANALYST         7566 09/12/82       3000                    20
      7902 FORD       ANALYST         7566 03/12/81       3000                    20
      7839 KING       PRESIDENT            17/11/81       5000                    10

8 filas seleccionadas.*/
SELECT EMPNO, ENAME, JOB, IFNULL(MGR,'') AS MGR, date_format(hiredate, '%d/%m/%y') AS HIREDATE, SAL, IFNULL(COMM,'') AS COMM, DEPTNO
FROM empleados
WHERE SAL > (SELECT SAL FROM empleados WHERE EMPNO = 7934)
ORDER BY SAL;

/* 53. Obten informacion en la que se reflejen los nombres, empleos y salarios tanto de 
los empleados que superan en salario a Allen como del propio Allen.

ENAME      JOB              SAL
---------- --------- ----------
ALLEN      SALESMAN        1600
JONES      MANAGER         2975
BLAKE      MANAGER         2850
CLARK      MANAGER         2450
SCOTT      ANALYST         3000
KING       PRESIDENT       5000
FORD       ANALYST         3000

7 filas seleccionadas.*/
SELECT ENAME, JOB, SAL
FROM empleados
WHERE SAL >= (SELECT SAL FROM empleados WHERE ENAME='ALLEN');



/* 54. Halla el nombre el ultimo empleado por orden alfabetico.

MAX(ENAME)
----------
WARD

1 fila seleccionada.*/
SELECT MAX(ENAME)
FROM empleados;

/* 55. Halla el salario mas alto, el mas bajo, y la diferencia entre ellos.

SAL_MAS_ALTO SAL_MAS_BAJO DIFERNECIA
------------ ------------ ----------
        5000          800       4200
        
1 fila seleccionada.*/
SELECT MAX(SAL) AS SAL_MAS_ALTO, MIN(SAL) AS SAL_MAS_BAJO, MAX(SAL)-MIN(SAL) AS DIFERENCIA
FROM empleados;


/* 56. Sin conocer los resultados del ejercicio anterior, Â¿quienes reciben el 
salario mas alto y el mas bajo, y a cuanto ascienden estos salarios?

ENAME             SAL
---------- ----------
KING             5000
SMITH             800

2 filas seleccionadas.*/
SELECT ENAME, SAL
FROM empleados
WHERE SAL = (SELECT MAX(SAL) FROM empleados) OR SAL = (SELECT MIN(SAL) FROM empleados)
ORDER BY ENAME;

/* 57. Considerando empleados con salario menor de 5000, halla la media de los salarios 
de los departamentos cuyo salario minimo supera a 900. Muestra tambien el codigo y 
el nombre de los departamentos.

DNAME          AVG(A.SAL)
-------------- ----------
ACCOUNTING           1875
SALES          1566,66667

2 filas seleccionadas.*/
SELECT d.DNAME, AVG(A.SAL)
FROM departamentos d
JOIN
(SELECT SAL, DEPTNO 
FROM empleados
WHERE SAL < 5000
AND DEPTNO IN
(SELECT DEPTNO
FROM empleados
GROUP BY DEPTNO
HAVING MIN(SAL) > 900))A
ON d.DEPTNO=A.DEPTNO
GROUP BY A.DEPTNO
ORDER BY d.DNAME;




/* 58. Â¿Que empleados trabajan en ciudades de mas de cinco letras? Ordena el resultado 
inversamente por ciudades y normalmente por los nombres de los empleados.

ENAME
----------
CLARK
KING
MILLER
ADAMS
FORD
JONES
SCOTT
SMITH
ALLEN
BLAKE
JAMES
MARTIN
TURNER
WARD

14 filas seleccionadas.*/
SELECT ENAME
FROM empleados e
JOIN departamentos d
ON e.DEPTNO=d.DEPTNO AND LENGTH(d.LOC)>5
ORDER BY LOC DESC, ENAME;


/* 59. Halla los empleados cuyo salario supera o coincide con la media del salario de la empresa.

ENAME
----------
JONES
BLAKE
CLARK
SCOTT
KING
FORD

6 filas seleccionadas.*/
SELECT ENAME
FROM empleados
WHERE SAL >= (SELECT AVG(SAL) FROM empleados);


/* 60. Obten los empleados cuyo salario supera al de sus compa~neros de departamento.

ENAME
----------
BLAKE
SCOTT
KING
FORD

4 filas seleccionadas.*/
SELECT ENAME
FROM empleados e
JOIN (SELECT MAX(SAL) AS SAL, DEPTNO FROM empleados GROUP BY DEPTNO)A
ON A.DEPTNO=e.DEPTNO AND e.SAL=A.SAL

