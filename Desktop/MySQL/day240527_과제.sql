USE sakila;

# 1. 배우의 이름을 알파벳 내림차순 10명을 보여주세요.
SELECT first_name FROM actor
ORDER BY first_name DESC LIMIT 10;

# 2. 영화의 카테고리를 보여주세요.
SELECT * FROM category;

# 3. 고객 중에 휴면 회원들을 모두 보여주세요.
SELECT * FROM customer 
WHERE active=0;

# 4. 영화를 대여기간 순으로 오름차순 정렬하세요
SELECT * FROM film
ORDER BY rental_duration;

# 5. 영화를 대여료 순으로 내림차순 정렬하세요.
SELECT * FROM film
ORDER BY rental_rate DESC;

# 6. 영화 중 개(dog)가 나오는 것을 모두 보여주세요.
SELECT *FROM film 
WHERE description like '%dog%';

# 7. 영화 중 2000년 이전에 나온 영화들을 모두 보여주세요.
SELECT * FROM film
WHERE release_year <= 2000;

# 8. 필름 액터 테이블로 페넬로페 귀네스가 나온 영화의 제목들을 출력해주세요.
SELECT * FROM film;
SELECT * FROM film_actor;
SELECT * FROM actor;

SELECT * FROM actor
WHERE first_name = 'PENELOPE' AND last_name ='GUINESS';

SELECT * FROM film_actor
INNER JOIN actor
ON film_actor.actor_id = actor.actor_id
WHERE actor.first_name = 'PENELOPE' AND last_name ='GUINESS';

SELECT film.title FROM film
INNER JOIN film_actor
ON film.film_id = film_actor.film_id
INNER JOIN actor
ON film_actor.actor_id = actor.actor_id
WHERE actor.first_name = 'PENELOPE' AND last_name ='GUINESS';

# 9. 가장 많은 영화에 출연한 배우의 성과 이름과 출연한 영화의 갯수를 출력해주세요.
SELECT actor.first_name, actor.last_name, COUNT(fa.actor_id) FROM film_actor AS fa
INNER JOIN actor
ON fa.actor_id = actor.actor_id
GROUP BY fa.actor_id
ORDER BY COUNT(fa.actor_id) DESC
LIMIT 1;

# 10. 회원 번호와 그 회원이 대여료를 총 얼마 냈는지 보여주세요.
SELECT customer_id, sum(amount) FROM payment
GROUP BY customer_id;

# 11. 1번 store가 가지고 있는 총 비디오의 갯수를 보여주세요.
SELECT store_id, count(film_id) FROM inventory
WHERE store_id = 1
GROUP BY store_id;

# 12. 1번 store가 가지고 있는 영화와 해당 영화의 비디오 갯수를 보여주세요.
SELECT film.title,count(inventory.film_id) FROM inventory
INNER JOIN film
ON film.film_id = inventory.film_id
WHERE store_id = 1
GROUP BY film.title;

# 13. 영화의 제목과 카테고리를 텍스트로 보여주세요.
SELECT film.title AS '영화 제목', category.name AS '카테고리' FROM film_category
INNER JOIN film
ON film.film_id = film_category.film_id
INNER JOIN category
ON film_category.category_id = category.category_id;

# 14. 도시와 해당 국가를 보여주세요.
SELECT city.city, country.country FROM city
INNER JOIN country
ON city.country_id = country.country_id;

# 15. 고객 중 미국에 살고 있는 회원의 이름과 이메일을 보여주세요.
SELECT customer.first_name, customer.last_name, customer.email FROM customer
INNER JOIN country ON customer.address_id = country.country_id
INNER JOIN address ON customer.address_id = address.address_id 
WHERE country.country = '%American%';

# 16. 영화 중 대여횟수가 가장 많은 영화의 이름을 보여주세요.
SELECT film.title, COUNT(rental.inventory_id) AS rental_count
FROM rental
INNER JOIN inventory ON inventory.inventory_id = rental.inventory_id
INNER JOIN film ON film.film_id = inventory.film_id
GROUP BY film.title
ORDER BY rental_count DESC
LIMIT 1;

# 17. 영화 중 대여기간이 4일 미만이고 주연의 이름이 JOHNNY LOLLOBRIGIDA 이고 비디오 부록에 예고편이 있는 영화의 제목을 보여주세요.
SELECT film.title FROM film
INNER JOIN film_actor ON film.film_id = film_actor.film_id
INNER JOIN actor ON film_actor.actor_id = actor.actor_id
WHERE film.rental_duration < 4 
AND actor.first_name ='JOHNNY' 
AND actor.last_name = 'LOLLOBRIGIDA' 
AND film.special_features LIKE '%Trailer%';
 
# 18. 직원 중 Mike Hilyer가 LINDA WILLAMS 에게 대여해준 영화의 제목, 대여일, 반납일을 보여주세요.
SELECT film.title, rental.rental_date, rental.return_date FROM rental
INNER JOIN staff ON rentla.staff_id = staff.staff_id
INNER JOIN inventory ON rental.inventory_id = inventory.inventory_id
INNER JOIN film ON inventory.film_id = film.film_id
WHERE staff.first_name = 'MIKE'
AND staff.last_name = 'HILYER'
AND customer.first_name = 'LINDA'
AND customer.last_name = 'WILLIAMS';

# 19. 영화의 이름과 출연한 배우의 숫자를 배우의 숫자로 내림차순해서 보여주되 출연한배우의 숫자 컬럼의 이름을 출연배우수로 만들어 보여세요.
SELECT film.title, COUNT(distinct film_actor.actor_id) AS 출연배우수 FROM film
INNER JOIN film_actor ON film_actor.film_id = film.film_id
GROUP BY film.title
ORDER BY 출연배우수 DESC; 

# 20. 회원의 이름, 빌린 비디오의 숫자, 빌린 비디오 대여점의 주소, 회원의 거주 도시를 회원 번호순으로 정렬하여 보여주세요.
SELECT r.customer_id, c.first_name, c.last_name, count(r.rental_id), a.address, ci.city
FROM customer c
INNER JOIN rental r ON c.customer_id = r.customer_id
INNER JOIN store s ON r.staff_id = s.manager_staff_id
INNER JOIN address a ON s.address_id = a.address_id
INNER JOIN city ci ON a.city_id = ci.city_id
GROUP BY c.first_name, c.last_name, r.customer_id, a.address, ci.city
ORDER BY c.customer_id ASC;