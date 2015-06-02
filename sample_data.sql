--inserts to departments
INSERT INTO "departments" VALUES (1,"Information Technologies","IT");
INSERT INTO "departments" VALUES (2,"Accounting Department","ACC");
INSERT INTO "departments" VALUES (3,"Human Resources","HS");
INSERT INTO "departments" VALUES (4,"Product Department","PD");
INSERT INTO "departments" VALUES (5,"Marketing Department","MD");
INSERT INTO "departments" VALUES (6,"Operation Department","OP");
INSERT INTO "departments" VALUES (7,"Strategy Department","SD");

--inserts to managers
INSERT INTO "managers" VALUES ("1","Bill","Gates","IT Manager","1");
INSERT INTO "managers" VALUES ("2","Jason","Payable","Account Manager","2");
INSERT INTO "managers" VALUES ("3","Terry","Tate","Humen Resources Manager","3");
INSERT INTO "managers" VALUES ("4","Willy","Wonka","Product Manager","4");
INSERT INTO "managers" VALUES ("5","Nicolas","Cage","Marketing Manager","5");
INSERT INTO "managers" VALUES ("6","Angelina","Jolly","Operation Manager","6");
INSERT INTO "managers" VALUES ("7","Kate","Upton","Strategy Manager","7");

--inserts to employees
INSERT INTO "employees" VALUES ("1","Jack","Gregory","Jr. Software Specialist","1");
INSERT INTO "employees" VALUES ("2","Isabelle","Taylor","Jr. Account Specialist","2");
INSERT INTO "employees" VALUES ("3","Tommy","Levi","Jr. PR Specialist","3");
INSERT INTO "employees" VALUES ("4","Sam","Smith","Jr. Product Specialist","4");
INSERT INTO "employees" VALUES ("5","Billy","Hunt","Jr. Marketing Specialist","5");
INSERT INTO "employees" VALUES ("6","Kate","Spade","Jr. Operation Specialist","6");
INSERT INTO "employees" VALUES ("7","Taylor","Middleton","Jr. Strategy Specialist","7");
INSERT INTO "employees" VALUES ("8","Simons","McKinnon","Software Executive","1");
INSERT INTO "employees" VALUES ("9","Elizabeth","Darwin","Account Executive","2");
INSERT INTO "employees" VALUES ("10","Charles","Manson","PR Executive","3");
INSERT INTO "employees" VALUES ("11","Morgan","Freeman","Product Executive","4");
INSERT INTO "employees" VALUES ("12","Timmy","Trumpet","Marketing Executive","5");
INSERT INTO "employees" VALUES ("13","Eric","Time","Operation Executive","6");
INSERT INTO "employees" VALUES ("14","Matsumae","Ohana","Strategy Executive","7");
INSERT INTO "employees" VALUES ("15","Kenny","Mile","Software Specialist","1");
INSERT INTO "employees" VALUES ("16","Hillary","Clinton","Account Specialist","2");
INSERT INTO "employees" VALUES ("17","Mike","Tayson","PR Specialist","3");
INSERT INTO "employees" VALUES ("18","Jeniffer","Aniston","Product Specialist","4");
INSERT INTO "employees" VALUES ("19","Rose","Mccormik","Marketing Specialist","5");
INSERT INTO "employees" VALUES ("20","Chandler","Bing","Operation Specialist","6");
INSERT INTO "employees" VALUES ("21","Monika","Green","Strategy Specialist","7");

--inserts data from managers into users table	
INSERT INTO 'users' (user_name, password,  manager_id)
		SELECT 
				lower(manager_name)||'.'||lower(manager_surname)||'@gmail.com',
				lower(manager_name)||'.'||lower(manager_surname),
				id
		FROM managers;	
		
--inserts data from employees into users table
INSERT INTO 'users' (user_name, password,  employee_id)
		SELECT 
				lower(employee_name)||'.'||lower(employee_surname)||'@gmail.com',--sqlite syntax to lowerCase str values lower(str); see documentation for more.
				lower(employee_name)||'.'||lower(employee_surname),
				id
		FROM employees;		
		
-- generate random requests where these requests will be waiting status in the system (only for employees not for department managers)
INSERT INTO requests (request_title, request_description, request_status, create_date, user_id)
SELECT  	user_name||' waiting request title' , 
			user_name||' watiing request description', 
			upper('waiting'),
			'2015-'||((abs(random()) % 10 ) + 1)||'-'||((abs(random()) % 28) + 1 )||' 19:19:37.000903',--generates random date string
			id
	FROM users
	WHERE employee_id IN (SELECT id 
                                    FROM employees
                                    WHERE department_id IN ( SELECT id
                                                                    FROM departments
                                                                    WHERE department_code != 'IT'));
				
																		
-- generate random requests where these requests will be department_approved status in the system 
INSERT INTO requests (request_title, request_description, request_status, create_date, update_date, user_id)
SELECT  	user_name||' department_approved request title' , 
			user_name||' department_approved request description', 
			upper('department_approved'),
			'2015-'||((abs(random()) % 6 ) + 1)||'-'||((abs(random()) % 28) + 1 )||' 19:19:37.000903',--generates random date string
			'2015-'||((abs(random()) % 6 ) + 7)||'-'||((abs(random()) % 28) + 1 )||' 19:19:37.000903',--generates random date string for update of request
			id
	FROM users
	WHERE employee_id IN (SELECT id 
                                    FROM employees
                                    WHERE department_id IN ( SELECT id
                                                                    FROM departments
                                                                    WHERE department_code != 'IT'))
			OR
			manager_id IN (SELECT id 
                                            FROM managers
                                            WHERE department_id IN (SELECT id
                                                                           FROM departments
                                                                           WHERE department_code != 'IT'));																					


-- generate random requests where these requests will be it_approved status in the system 
INSERT INTO requests (request_title, request_description, request_status, create_date, update_date, user_id)
SELECT  	user_name||' it_approved request title' , 
			user_name||' it_approved request description', 
			upper('it_approved'),
			'2015-'||((abs(random()) % 6 ) + 1)||'-'||((abs(random()) % 28) + 1 )||' 19:19:37.000903',--generates random date string
			'2015-'||((abs(random()) % 6 ) + 7)||'-'||((abs(random()) % 28) + 1 )||' 19:19:37.000903',--generates random date string for update of request
			id
	FROM users
	WHERE employee_id IN (SELECT id 
                                    FROM employees
                                    WHERE department_id IN ( SELECT id
                                                                    FROM departments
                                                                    WHERE department_code != 'IT'))
			OR
             manager_id IN (SELECT id 
                                FROM managers
                                WHERE department_id IN (SELECT id
                                                               FROM departments
                                                               WHERE department_code != 'IT'));	
-- generate task from requests that it's status it_approved																		
INSERT INTO tasks (task_title, task_description, status, create_date, request_id)
	SELECT request_title, request_description, upper('idle'), update_date, id
			FROM requests
			WHERE request_status == 'IT_APPROVED';																		