/* 
	데이터베이스,
		우리가 데이터를 규격화하여 저장하는 공간으로써 우리의 프로그램이 바뀌더라도 데이터베이스 시스템이 남아있으면 
		해당 데이터베이스 접속을 하면 기존에 입력한 데이터가 남아있게 된다.
		데이터베이스의 기능을 가장 간략하게성명하면 CRUD가 된다.
		C: Create. 데이터 생성
		R: Rectrieve(Read). 데이터 호출
		U: Update. 데이터 수정
		D: Delete. 데이터 삭제
        
        데이터베이스의 구조
        컬럼(튜플) -> 로우 -> 테이블 -> 스키마(=데이터베이스) 로 계층이 이루어져있다. 
*/
	#데이터베이스에서 작업ㅇ르 하기 전에는 반드시 특정 사키마를 선택을 해주어야 한다.
    #사용 할 스키마 이름을 더블클릭하거나 USE 스키마이름;
    USE sakila;
	
    #데이터베이스에서 데이터 불러오기
    #SELECT 컬럼이름 FROM 테이블이름;
    SELECT actor_id FROM actor;
    
    #만약 우리가 특정 컬럼만이 아나라 여러컴을 볼 때에는 
    #SELECT 컬럼이름, 컬럼이름 FROM 테이블이름;
    SELECT actor_id, first_name FROM actor;
    
    #모든 칼럼을 볼 때에는
    #SELECT * FROM 테이블 이름;
    SELECT * FROM actor;
    
    #우리가 필요에 따라서는 조건에 따른 검색이 가능하다.
    #이럴 때에는 SELECT * FROM 테이블이름 WHERE 조건;
    SELECT * FROM actor WHERE actor_id =20;  
    
    #데이터를 입력할 때에는 
    #INSERT INTO 테이블이름 VALUES(값);
    INSERT INTO actor VALUES(1080, 'Jeyung', 'CHO', NOW());
    SELECT * FROM actor WHERE actor_id	= 1080;
    
    #만약 우리가 특정 칼럼에만 값을 입력할 때에는 
    #INSERT INTO 테이블이름(컬럼이름1, 컬럼이름2, ....) VALUES(값1, 값2, ....);
    INSERT INTO actor(first_name, last_name) VALUES('철수', '김');
    SELECT * FROM actor WHERE first_name = '철수';
    
    #우리가 특정 데이터의 값을 수정할 때에는
    #UPDATE 테이블이름 SET 컬럼이름 = 새 값, 컬럼이름 = 새 값 ... WHERE 특정 조건;
    UPDATE actor
    SET first_name = '재영2',
    last_name = '조',
    last_update = NOW()
    WHERE actor_id = 1080;
    SELECT * FROM actor WHERE actor_id = 1080;
    
    #로우 삭제하기
    #DELETE FROM 테이블이름 WHERE 삭제조건;
    SELECT * FROM actor WHERE actor_id = 1081;
    DELETE FROM actor WHERE  actor_id = 1081;
    
    #제약 조건
    #		해당 컬럼에 들어갈 값을 제약하는 특수한 조건들을 뜻한다.
    #PK: Primary Key. 해당 컬럼에 들어갈 값을 "고유" 해야 하고, "널"이 아니어야 한다. -> 한 개의 컬럼에만 부여
    #UQ: Unique. 해당 컬럼에 들어갈 값은 "고유"해야 한다.
    #NN: Not Null. 해당 컬럼에 들어갈 값은 "널"이 아니어야 한다.
    #AI: AUto Increment. 해당 컬럼에 들어갈 정수 값은 입력될때마다 자동으로 증가된다.
    
    #데이터타입
    #해당 컬럼에 어떠한 값ㅇ르 저장 가능한지를 지정한다.
    #대부분 자바와 비슷하지만 VARCHAR 라는 타입은 우리가 어떠한 문자열을 저장할때에 가변형 문자열 이라는 특수한 타입이 된다.
    #VARCHAR(45) 라고 한다면, 최대 45글자가 입력 가능하고 우리가 입력한 글자 수에 맞추어 길이가 자동으로 변경되는 타입이다.
    
    #다양한 SQL 쿼리문을 알아보자
    #1. DISTINCT
    #	같은 값이 있을 경우, 하나만 보여준다.
    USE world;
    SELECT COUNTRYCODE FROM city;
    SELECT DISTINCT COUNTRYCODE FROM city;
    
    #2. AND
    # 	AND는 WHERE 조건절에 복합적인 조건을 사용할 때 사용한다.
    SELECT *FROM city;
    # 	컨트리코드가 AFG이고 인구가 1000000 이상인 도시
    SELECT *FROM city WHERE CountryCode = 'AFG' AND Population >= 1000000;
    
    #3. OR
    #	WHERE 조건절에 '혹은' 이라는 의미로써 사용된다.
    # 	컨트리코드가 AFG 혹은 인구가 1000000 이상인 도시
    SELECT * FROM city WHERE CountryCode = 'AFG' OR Population >= 1000000;
    
    #4. ORDER BY
    #	특정 컬럼의 값을 토대로 정렬하여 보여준다.
	#	기본적으로 아무것도 적어주지 않으면 오름차순이지만 
	# 	ORDER BY 컬럼이름 ASC -> 해당 컬럼이름 값으로 오름차순 정렬
    # 	ORDER BY 컬럼이름 DESC -> 해당 컬럼이름의값으로 내림차순 정렬
    SELECT * FROM city ORDER BY Population ASC;
    SELECT * FROM city ORDER BY Population DESC;
    
    #5. NOT
	# 	특정 조건을 만족하지 않는 경우를 지정할때 사용이 된다.
    SELECT * FROM city WHERE NOT CountryCode = 'AFG';
    
    #6. LIMIT
    #	출력되는 줄의 갯수를 제한할때 사용이 된다.
    #	MySql Workbench에서는 기본적으로 출력이 LIMIT 1000이 걸려있다.
    SELECT * FROM city LIMIT 10;
    # 	또한, 특정 줄부터 총 몇개의 줄을 출력할 때에도 LIMIT이 사용된다.
    # 	단, 시작되는 줄은 index와 같은 개념으로써, 0부터 시작이 된다.
    SELECT * FROM city LIMIT 10,20; # -> 11~30
    
    #7. MIN()
    # 	특정 컬럼에서 가장 작은 값을 찾아서 출력한다.
    SELECT MIN(population) FROM city;
    
    #8. MAX()
    #	특정 컬럼에서 가장 큰 값을 찾아서 출력한다.
    SELECT MAX(population) FROM city;
    
    #9. COUNT()
    # 	특정 컬럼의 갯수를 세어서 출력한다.
    SELECT COUNT(name) FROM city; # -> 중복된 값도 포함
    SELECT COUNT(distinct name) FROM city; # -> 중복된 값은 포함하지 않음
    
    #10. SUM()
    #	 특정 칼럼의 총합을 출력한다.
    SELECT SUM(population) FROM city;
    
    #11. AVG()
    #	 특정 컬럼의 평균을 출력한다.
    SELECT AVG(population) FROM city;
    
    #12. LIKE
    #	 WHERE에서 우리가 특정 값을 적는것이 아니라 '비슷한' 값을 찾아준다.
	#	 %를 뒤에 붙이면 해당 글자들로 시작하는 모든 값을 찾아준다.
    # 	 %를 앞에 붙이면 해당 글자들로 끝나는 모든 값을 찾아준다.
    #    %를 앞뒤에 붙이면 해당 글자가 중간에 들어가는 모든 값을 찾아준다.
    SELECT * FROM city
	WHERE NAME LIKE 'se';
    
    #13. 어떤 특정 값들 중 하나라도 만족하는 경우를 검색할때에는 IN을 쓰게 된다. 
    SELECT * FROM city
    WHERE COUNTRYCODE IN ('KOR', 'BRA', 'JPN');
    
    #14. 특정 범위의 값을 검색할 때에는 BETWEEN 시작값 AND 끝값 으로 검색하게 된다. 
    SELECT * FROM city
    WHERE ID BETWEEN 11 AND 20;
    
    #15. 만약 내가 검색 또는 쿼리문에서 임시로 별명을 붙여주여야 하는 경우, AS 별명으로 붙여주게 된다.
    SELECT ID, NAME AS '도시', countrycode AS '국가' FROM city;
    
    #16. JOIN
	#	 우리가 2개의 테이블을 연결하여 결과창에서 그 2개의 테이블을 모두 확인하여 원하는 결과를 뽑아볼때 사용되는 키워드이다. 
    # 	 JOIN에는 이너 조인, 레프트조인, 라이트 조인, 풀 아우터 조인이 있지만 실질적으로 가장 많이 사용되는건 이너 조인이 된다. 
	#		16-A. INNER JOIN
	#		city에서 contrycode를 기준으로 country 테이블을 연결하여 출력해라 -> 교집합
			SELECT id, C.name, CountryCode, N.name FROM city AS C
            INNER JOIN country AS N
            ON C.CountryCode = N.code;
    #		16-B. LEFT JOIN
    #		city에서 id를 기준으로, country 테이블의 capital을 연결하려 출력해라 
			SELECT * FROM city
            LEFT JOIN country
			ON city.id = country.Capital;
	#		16-C. RIGHT JOIN
			SELECT * FROM city
            RIGHT JOIN country
            ON city.id = country.Capital;
	
    #17. UNION
    #	 2개 이상의 SELECT 쿼리 결과를 합쳐서 보고 싶은 경우
    SELECT * FROM city
    LEFT JOIN country
    ON city.id = country.capital
    UNION
    SELECT * FROM city
    RIGHT JOIN country
    ON city.id = country.capital;
    
    #18. GROUP BY
    #	 한 컬럼에 여러 값이 중복되어 있을 경우, 한 주로 묶음 처리 하는 것이다.
    SELECT countrycode, sum(population) FROM city
    GROUP BY CountryCode;
    
    #19. Subquery
    # 	 IN의 조건절에 쿼리를 넣어서 해당 쿼리를 만족하는 것들만 다시 뽑아내는 방법이다.
    SELECT * FROM county
    WHERE code IN (SELECT countrycode FROM city);
    
    SELECT * FROM country
    WHERE capital IN (SELECT id FROM city);
    
    SELECT * FROM city
    INNER JOIN country.capital ON city.id = country.capital
    WHERE city.population >= 5000000
    ORDER BY city.name DESC;
    
    