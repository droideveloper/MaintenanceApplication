/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.common;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.table.TableUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.fs.maintenance.entities.*;
import org.fs.maintenance.utils.StringUtility;

/**
 *
 * @author Fatih
 */
public class DatabaseManager implements IDatabaseManager {
    
    private final static String DATABASE_URL        = System.getProperty("user.connection.str");
    
    private static DatabaseManager sharedInstance   = null;    
    
    private JdbcConnectionSource connectionSource   = null;
    
    /**
     * IDatabaseManager dbManager = DatabaseManager.getSharedInstance(); is advised usage 
     * because we can access all transactions or operations from definition
     * the idea of using singleton over interface definition is 
     * making GC don't feel upset about having static instance is kept in any kind of attribute
     * if we forget to clear that instance its amateur leak.
     * and call dispose on application exit dispose() method too.
     * 
     * @return static instance of class created only once in the life cycle of application
     */
    public static DatabaseManager getSharedInstance() {
        if(sharedInstance == null) {
            sharedInstance = new DatabaseManager();
        }
        return sharedInstance;
    }
    
    private Dao<User, Integer>          userDao;
    private Dao<Employee, Integer>      employeeDao;
    private Dao<Manager, Integer>       managerDao;
    private Dao<Department, Integer>    departmentDao;
    private Dao<Request, Integer>       requestDao;
    private Dao<Task, Integer>          taskDao;
    private Dao<Notification, Integer>  notificationDao;
    
    private DatabaseManager() {
        try {            
            connectionSource = new JdbcConnectionSource(DATABASE_URL);
            setUp();
        } 
        catch(Exception e) {
            try {
                if(connectionSource != null) {
                    connectionSource.close();
                }
            } catch(Exception x) { }
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void setUp() throws SQLException {
        userDao = DaoManager.createDao(connectionSource, User.class);
        employeeDao = DaoManager.createDao(connectionSource, Employee.class);
        managerDao  = DaoManager.createDao(connectionSource, Manager.class);
        departmentDao = DaoManager.createDao(connectionSource, Department.class);
        requestDao = DaoManager.createDao(connectionSource, Request.class);
        taskDao = DaoManager.createDao(connectionSource, Task.class);
        notificationDao = DaoManager.createDao(connectionSource, Notification.class);
                
        TableUtils.createTableIfNotExists(connectionSource, Department.class);
        TableUtils.createTableIfNotExists(connectionSource, User.class);
        TableUtils.createTableIfNotExists(connectionSource, Employee.class);
        TableUtils.createTableIfNotExists(connectionSource, Manager.class);
        TableUtils.createTableIfNotExists(connectionSource, Request.class);
        TableUtils.createTableIfNotExists(connectionSource, Task.class);   
        TableUtils.createTableIfNotExists(connectionSource, Notification.class);
    }    
    
    /**
    * closes connection to the database
    * @throws SQLException 
    */
    @Override
    public void dispose() throws SQLException {
        if(connectionSource != null) {
            connectionSource.close();
        }
    }

    @Override
    public void initWithMockData() throws SQLException {
       String sqlPath = String.format(Locale.getDefault(), 
                                      "%s/%s", 
                                      System.getProperty("user.dir"), System.getProperty("mock.sql"));
       
       BufferedReader bufferedReader = null;
       Connection connection = null;
       try {
           String rawQuery = "";
           //read raw query file into string
           bufferedReader = new BufferedReader(new FileReader(sqlPath));
           String line = "";
           while((line = bufferedReader.readLine()) != null) {
               rawQuery += line + "\n";
           }
           //create entra connection
           connection = DriverManager.getConnection(DATABASE_URL);
           Statement statement = connection.createStatement();
           statement.setQueryTimeout(60);
           statement.executeUpdate(rawQuery);
       } catch(Exception e) {  
       } finally {
           try {
               if(bufferedReader != null) {
                   bufferedReader.close();
               }
               if(connection != null) {
                   connection.close();
               }
           } catch(Exception e) {
           }
       }
    }

    @Override
    public User findUserByUserName(String userName) throws SQLException {
        if(StringUtility.isNullOrEmpty(userName)) {
            throw new SQLException("username should not be null or empty");
        }
        return userDao.queryBuilder()
                      .where()
                      .eq(DbConstants.User.COLUMN_USER_NAME, userName)
                      .queryForFirst();
    }

    @Override
    public User findUserByUserId(Integer id) throws SQLException {
        if(StringUtility.isNullOrEmpty(id)) {
            throw new SQLException("id is null");
        }
        return userDao.queryForId(id);
    }

    @Override
    public Employee findEmployeeByEmployeeId(Integer id) throws SQLException {
        if(StringUtility.isNullOrEmpty(id)) {
            throw new SQLException("id is null");
        }
        return employeeDao.queryForId(id);
    }

    @Override
    public Employee findEmployeeByEmployeeNameAndSurname(String name, String surname) throws SQLException {
        boolean nameInvalid = StringUtility.isNullOrEmpty(name);
        boolean surnameInvalid = StringUtility.isNullOrEmpty(surname);
        if(nameInvalid || surnameInvalid) {
            if(nameInvalid && !surnameInvalid) {
                throw new SQLException("name is null or empty");
            } else if(!nameInvalid && surnameInvalid) {
                throw new SQLException("surname is null or empty");
            } else {
                throw new SQLException("name and surname are null or empty");
            }
        }
        Where<Employee, Integer> wh = employeeDao.queryBuilder()
                                                 .where();
        return wh.and(
                    wh.eq(DbConstants.Employee.COLUMN_EMPLOYEE_NAME, name),                              
                    wh.eq(DbConstants.Employee.COLUMN_EMPLOYEE_SURNAME, surname)
                ).queryForFirst();
    }

    @Override
    public List<Employee> findEmployeesWorkAtItDepartment() throws SQLException {
       /**
        * select * 
        *   from employees 
        *   where department_id in ( select id 
        *                                   from departments
        *                                   where department_code = 'IT' );
        */
        QueryBuilder<Department, Integer> departmentQuery = departmentDao.queryBuilder();
        departmentQuery.selectColumns(DbConstants.Department.COLUMN_ID);
        departmentQuery.setWhere(departmentQuery.where()
                                                .eq(DbConstants.Department.COLUMN_DEPARTMENT_CODE, "IT"));
        
        Where<Employee, Integer> wh = employeeDao.queryBuilder().where();
        
        
        return wh.in(DbConstants.Employee.COLUMN_DEPARTMENT_ID, departmentQuery)
                 .query();
    }

    @Override
    public Manager findManagerByManagerId(Integer id) throws SQLException {
        if(StringUtility.isNullOrEmpty(id)) {
            throw new SQLException("id is null");
        }    
        return managerDao.queryForId(id);
    }

    @Override
    public Manager findManagerByManagerNameAndSurname(String name, String surname) throws SQLException {
        boolean nameInvalid = StringUtility.isNullOrEmpty(name);
        boolean surnameInvalid = StringUtility.isNullOrEmpty(surname);
        if(nameInvalid || surnameInvalid) {
            if(nameInvalid && !surnameInvalid) {
                throw new SQLException("name is null or empty");
            } else if(!nameInvalid && surnameInvalid) {
                throw new SQLException("surname is null or empty");
            } else {
                throw new SQLException("name and surname are null or empty");
            }
        }
        Where<Manager, Integer> wh = managerDao.queryBuilder()
                                                .where();
        return wh.and(
                    wh.eq(DbConstants.Manager.COLUMN_MANAGER_NAME, name),                              
                    wh.eq(DbConstants.Manager.COLUMN_MANAGER_SURNAME, surname)
                ).queryForFirst();
    }

    @Override
    public Department findDepartmentByDepartmentId(Integer id) throws SQLException {
        if(StringUtility.isNullOrEmpty(id)) {
            throw new SQLException("id is null");
        }
        return departmentDao.queryForId(id);
    }

    @Override
    public Department findDepartmentByDepartmentCode(String departmentCode) throws SQLException {
        if(StringUtility.isNullOrEmpty(departmentCode)) {
            throw new SQLException("department code is null");
        }
        return departmentDao.queryBuilder()
                            .where()
                            .eq(DbConstants.Department.COLUMN_DEPARTMENT_CODE, departmentCode)
                            .queryForFirst();
    }
    @Override
    public Department findDepartmentByUser(User usr) throws SQLException {
        if(StringUtility.isNullOrEmpty(usr)) {
            throw new SQLException("user is null");
        }
        
        /**
         * select * from departments 
         *          where id in ( select department_id from employees
         *                                             where id in ( select employee_id from users
         *                                                                              where id = ?
         *                                                          ) 
         *                      )
         *                      or
         *                id in ( select department_id from managers
         *                                             where id in ( select manager_id from users
         *                                                                             where id = ? 
         *                                                          ) 
         *                      )  
         */
        
        QueryBuilder<User, Integer> userQueryForEmployee = userDao.queryBuilder();
        userQueryForEmployee.selectColumns(DbConstants.User.COLUMN_EMPLOYEE_ID);
        userQueryForEmployee.setWhere(userQueryForEmployee.where()
                                                           .eq(DbConstants.User.COLUMN_ID, usr.getId()));
        
        QueryBuilder<User, Integer> userQueryForManager = userDao.queryBuilder();
        userQueryForManager.selectColumns(DbConstants.User.COLUMN_MANAGER_ID);
        userQueryForManager.setWhere(userQueryForManager.where()
                                                        .eq(DbConstants.User.COLUMN_ID, usr.getId()));
        
        QueryBuilder<Employee, Integer> employeeQuery = employeeDao.queryBuilder();
        employeeQuery.selectColumns(DbConstants.Employee.COLUMN_DEPARTMENT_ID);
        employeeQuery.setWhere(employeeQuery.where()
                                            .in(DbConstants.Employee.COLUMN_ID, userQueryForEmployee));
        
        QueryBuilder<Manager, Integer> managerQuery = managerDao.queryBuilder();
        managerQuery.selectColumns(DbConstants.Manager.COLUMN_DEPARTMENT_ID);
        managerQuery.setWhere(managerQuery.where()
                                            .in(DbConstants.Manager.COLUMN_ID, userQueryForManager));
        
        Where<Department, Integer> wh = departmentDao.queryBuilder().where();
        return wh.or(
                    wh.in(DbConstants.Department.COLUMN_ID, employeeQuery),
                    wh.in(DbConstants.Department.COLUMN_ID, managerQuery)
                 ).queryForFirst();                
    }

    @Override
    public Request createNewRequest(Request request) throws SQLException {
        if(StringUtility.isNullOrEmpty(request)) {
            throw new SQLException("request is null");
        }        
        requestDao.create(request);
        return request;
    }

    @Override
    public Request updateExistingRequest(Request request) throws SQLException {
        if(StringUtility.isNullOrEmpty(request)) {
            throw new SQLException("request is null");
        }        
        requestDao.update(request);
        return request;
    }

    @Override
    public Request deleteExisitingRequest(Request request) throws SQLException {
        if(StringUtility.isNullOrEmpty(request)) {
            throw new SQLException("request is null");
        }
        requestDao.delete(request);
        return request;
    }

    @Override
    public List<Request> findRequestsCreatedByDepartmentWaitingManagerApproval(Department department) throws SQLException {
        if(StringUtility.isNullOrEmpty(department)) {
            throw new SQLException("department is null");
        }
        /**
         * 
         * 
         * select * 
         *      from requests 
         *      where request_status = 'waiting' and user_id in ( select id 
         *                                                          from users 
         *                                                          where employee_id in ( select id 
         *                                                                                      from employees 
         *                                                                                      where department_id = ? ) );
         */       
        
        QueryBuilder<Employee, Integer> employeeInnerQuery = employeeDao.queryBuilder();
        employeeInnerQuery.selectColumns(DbConstants.Employee.COLUMN_ID);
        employeeInnerQuery.setWhere(employeeInnerQuery.where()
                                                      .eq(DbConstants.Employee.COLUMN_DEPARTMENT_ID, department.getId()));
        
        
        QueryBuilder<User, Integer> userInnerQuery = userDao.queryBuilder();
        userInnerQuery.selectColumns(DbConstants.User.COLUMN_ID);
        userInnerQuery.setWhere(userInnerQuery.where()
                                              .in(DbConstants.User.COLUMN_EMPLOYEE_ID, employeeInnerQuery));    
        
        Where<Request, Integer> wh = requestDao.queryBuilder().where();
        return wh.and(
                    wh.eq(DbConstants.Request.COLUMN_REQUEST_STATUS, RequestStatus.WAITING),
                    wh.in(DbConstants.Request.COLUMN_USER_ID, userInnerQuery)
                ).query();
    }

    @Override
    public List<Request> findRequestsCreatedByUser(User user) throws SQLException {
        if(StringUtility.isNullOrEmpty(user)) {
            throw new SQLException("user is null");
        }
        return requestDao.queryForEq(DbConstants.Request.COLUMN_USER_ID, user.getId());
    }

    @Override
    public List<Request> findRequestsWaitingItManagerApproval() throws SQLException {
        return requestDao.queryForEq(DbConstants.Request.COLUMN_REQUEST_STATUS, RequestStatus.DEPARTMENT_APPROVED);
    }

    @Override
    public Task createNewTask(Task task) throws SQLException {
        if(StringUtility.isNullOrEmpty(task)) {
            throw new SQLException("task is null");
        }
        taskDao.create(task);
        return task;
    }

    @Override
    public Task updateExisitingTask(Task task) throws SQLException {
        if(StringUtility.isNullOrEmpty(task)) {
            throw new SQLException("task is null");
        }
        taskDao.update(task);
        return task;    
    }

    @Override
    public Task deleteExistingTask(Task task) throws SQLException {
        if(StringUtility.isNullOrEmpty(task)) {
            throw new SQLException("task is null");
        }
        taskDao.delete(task);
        return task;    
    }

    @Override
    public Task findTaskByTaskId(Integer id) throws SQLException {
        if(StringUtility.isNullOrEmpty(id)) {
            throw new SQLException("id is null");
        }
        return taskDao.queryForId(id);
    }

    @Override
    public Task findTaskItEmployeeCurrentlyWorkingOn(Employee employee) throws SQLException {
        if(StringUtility.isNullOrEmpty(employee)) {
            throw new SQLException("employee is null");
        }
        /**
         * 
         * select * 
         *      from tasks
         *      where employee_id = ? and task_status = 'PROGRESS'
         *      orderBy start_date descending;
         */        
        
        Where<Task, Integer> wh = taskDao.queryBuilder()
                                         .orderBy(DbConstants.Task.COLUMN_TASK_START_DATE, false)
                                         .where();
        return wh.and(
                    wh.eq(DbConstants.Task.COLUMN_EMPLOYEE_ID, employee.getId()),
                    wh.eq(DbConstants.Task.COLUMN_TASK_STATUS, TaskStatus.PROGRESS)
                ).queryForFirst();
    }

    @Override
    public Integer calculateNumberOfTasksAssignedToItEmployee(Employee employee) throws SQLException {
        if(StringUtility.isNullOrEmpty(employee)) {
            throw new SQLException("employee is null");
        }
        
        /**
         * select count(*) 
         *      from tasks
         *      where employee_id = ? and ( task_status = 'IDLE' or task_status = 'PROGRESS' );
         */
        
        Where<Task, Integer> wh = taskDao.queryBuilder().where();
        
        return (int)wh.and(
                        wh.eq(DbConstants.Task.COLUMN_EMPLOYEE_ID, employee.getId()),
                        wh.or(
                            wh.eq(DbConstants.Task.COLUMN_TASK_STATUS, TaskStatus.IDLE),
                            wh.eq(DbConstants.Task.COLUMN_TASK_STATUS, TaskStatus.PROGRESS)
                        )
                    ).countOf();
    }

    @Override
    public List<Task> findTasksAssignedToItEmployee(Employee employee) throws SQLException {
        if(StringUtility.isNullOrEmpty(employee)) {
            throw new SQLException("employee is null");
        }
        
        /**
         * select * 
         *      from tasks
         *      where employee_id = ? 
         *      orderBy task_status descending
         */
        
        Where<Task, Integer> wh = taskDao.queryBuilder()
                                         .orderBy(DbConstants.Task.COLUMN_TASK_STATUS, false)
                                         .where()
                                            .eq(DbConstants.Task.COLUMN_EMPLOYEE_ID, employee.getId());
        
        return wh.query();
    }

    @Override
    public List<Task> findTasksNotCompletedAssignedToItEmployee(Employee employee) throws SQLException {
        if(StringUtility.isNullOrEmpty(employee)) {
            throw new SQLException("employee is null");
        }
        
        /**
         * select * 
         *      from tasks
         *      where employee_id = ? and ( task_status = 'IDLE' or task_status = 'PROGRESS' )
         *      orderBy task_status descending
         */        
        
        Where<Task, Integer> wh = taskDao.queryBuilder()
                                         .orderBy(DbConstants.Task.COLUMN_TASK_STATUS, false)
                                         .where();
        
        return wh.and(
                    wh.eq(DbConstants.Task.COLUMN_EMPLOYEE_ID, employee.getId()),
                    wh.or(
                        wh.eq(DbConstants.Task.COLUMN_TASK_STATUS, TaskStatus.IDLE),
                        wh.eq(DbConstants.Task.COLUMN_TASK_STATUS, TaskStatus.PROGRESS)
                    )
                ).query();
        
    }

    @Override
    public List<Task> findTasksNotAssignedToAnyItEmployees() throws SQLException {
        return taskDao.queryBuilder()
                      .where()
                      .isNull(DbConstants.Task.COLUMN_EMPLOYEE_ID)
                      .query();
    }

    @Override
    public List<Pair<Department, Integer>> calculateNumberOfRequestsCreatedByEachDepartment(Date startDate, Date endDate) throws SQLException {
        List<Pair<Department, Integer>> data = new ArrayList();
        
        /**
         * select * from departments
         *          where department_code != 'IT';
         */
        
        List<Department> departments = departmentDao.queryBuilder()
                                                    .where()
                                                        .not()
                                                        .eq(DbConstants.Department.COLUMN_DEPARTMENT_CODE, "IT")
                                                    .query();
        
        for( Department d : departments ) {
            
            /**
             * select count(*) from requests
             *                 where user_id in ( select id from users
             *                                              where employee_id in ( select id from employees 
             *                                                                               where department_id = ? ) 
             *                                                      or
             *                                                    manager_id in ( select id from managers 
             *                                                                              where department_id = ? )  
             *                                      )
             *                          and
             *                      create_date between ? and ?;//if dates are passed in else last one is excluded
             */
            
            QueryBuilder<Employee, Integer> employeeQuery = employeeDao.queryBuilder();
            employeeQuery.selectColumns(DbConstants.Employee.COLUMN_ID);
            employeeQuery.setWhere(employeeQuery.where()
                                                .eq(DbConstants.Employee.COLUMN_DEPARTMENT_ID, d.getId()));
            
            QueryBuilder<Manager, Integer> managerQuery = managerDao.queryBuilder();
            managerQuery.selectColumns(DbConstants.Manager.COLUMN_ID);
            managerQuery.setWhere(managerQuery.where()
                                              .eq(DbConstants.Manager.COLUMN_DEPARTMENT_ID, d.getId()));
        
            QueryBuilder<User, Integer> userQuery = userDao.queryBuilder();
            userQuery.selectColumns(DbConstants.User.COLUMN_ID);

            Where<User, Integer> usrWhere = userQuery.where();
            userQuery.setWhere(usrWhere.or(
                                            usrWhere.in(DbConstants.User.COLUMN_EMPLOYEE_ID, employeeQuery),
                                            usrWhere.in(DbConstants.User.COLUMN_MANAGER_ID, managerQuery)    
                                        ));

            Where<Request, Integer> wh = requestDao.queryBuilder().where();
            long count;
            //if dates are initialized
            if(startDate != null && endDate != null) {
                count = wh.and(
                                wh.in(DbConstants.Request.COLUMN_USER_ID, userQuery),
                                wh.between(DbConstants.Request.COLUMN_REQUEST_CREATE_DATE, startDate, endDate)
                            ).countOf();
            } else {
                count = wh.in(DbConstants.Request.COLUMN_USER_ID, userQuery)
                           .countOf();
            }
            data.add(new Pair<>(d, (int)count));
        }       
        return data;
    }

    @Override
    public List<Pair<Employee, Task>> findTasksEachEmployeeCurrentlyWorkingOn(Date startDate, Date endDate) throws SQLException {
        List<Pair<Employee, Task>> data = new ArrayList();
        //get it employees
        List<Employee> employees = findEmployeesWorkAtItDepartment();
        
        /**
         * select * from tasks
         *          where (employee_id = ? and task_status = 'PROGRESS')
         *                  and
         *                start_date between ? and ?; //this part is if date parameters are provided  
         */
        
        for(Employee e : employees) {
            List<Task> tasks;       
            Where<Task, Integer> wh = taskDao.queryBuilder().where();
            if(startDate != null && endDate != null) {                 
                 tasks = wh.and(
                                wh.and(
                                    wh.eq(DbConstants.Task.COLUMN_EMPLOYEE_ID, e.getId()),
                                    wh.eq(DbConstants.Task.COLUMN_TASK_STATUS, TaskStatus.PROGRESS)    
                                ),
                                wh.between(DbConstants.Task.COLUMN_TASK_START_DATE, startDate, endDate)
                           ).query();
            } else {
                tasks = wh.and(
                                wh.eq(DbConstants.Task.COLUMN_EMPLOYEE_ID, e.getId()),
                                wh.eq(DbConstants.Task.COLUMN_TASK_STATUS, TaskStatus.PROGRESS)
                            ).query();
            }
            
            for(Task t : tasks) {
                data.add(new Pair<>(e, t));
            }
        }
        
        return data;
    }

    @Override
    public List<Pair<Employee, Integer>> calculateNumberOfTasksAssignedToEachItEmployee(Date startDate, Date endDate) throws SQLException {
        List<Pair<Employee, Integer>> data = new ArrayList();
        //get it employees
        List<Employee> employees = findEmployeesWorkAtItDepartment();
        
        /**
         * select * from tasks
         *          where (employee_id = ? and (task_status = 'PROGRESS' or task_status = 'IDLE'))
         *                  and
         *                update_date between ? and ?; //this part is if date parameters are provided  
         */
        
        for(Employee e : employees) {
            long count;       
            Where<Task, Integer> wh = taskDao.queryBuilder().where();
            if(startDate != null && endDate != null) {                 
                 count = wh.and(
                                wh.and(
                                    wh.eq(DbConstants.Task.COLUMN_EMPLOYEE_ID, e.getId()),
                                    wh.or(
                                        wh.eq(DbConstants.Task.COLUMN_TASK_STATUS, TaskStatus.PROGRESS),
                                        wh.eq(DbConstants.Task.COLUMN_TASK_STATUS, TaskStatus.IDLE)      
                                    )
                                ),
                                wh.between(DbConstants.Task.COLUMN_TASK_UPDATE_DATE, startDate, endDate)
                           ).countOf();
            } else {
                count = wh.and(
                                wh.eq(DbConstants.Task.COLUMN_EMPLOYEE_ID, e.getId()),
                                wh.or(
                                        wh.eq(DbConstants.Task.COLUMN_TASK_STATUS, TaskStatus.PROGRESS),
                                        wh.eq(DbConstants.Task.COLUMN_TASK_STATUS, TaskStatus.IDLE)      
                                    )
                            ).countOf();
            }
            
            data.add(new Pair<>(e, (int)count));
        }
        
        return data;
    }

    @Override
    public List<Pair<Employee, Integer>> calculateNumberOfTasksCompletedByEachItEmployee(Date startDate, Date endDate) throws SQLException {
        List<Pair<Employee, Integer>> data = new ArrayList();
        //get it employees
        List<Employee> employees = findEmployeesWorkAtItDepartment();
        
        /**
         * select * from tasks
         *          where (employee_id = ? and task_status = 'COMPLETE')
         *                  and
         *                complete_date between ? and ?; //this part is if date parameters are provided  
         */
        
        for(Employee e : employees) {
            long count;       
            Where<Task, Integer> wh = taskDao.queryBuilder().where();
            if(startDate != null && endDate != null) {                 
                 count = wh.and(
                                wh.and(
                                    wh.eq(DbConstants.Task.COLUMN_EMPLOYEE_ID, e.getId()),                                    
                                    wh.eq(DbConstants.Task.COLUMN_TASK_STATUS, TaskStatus.COMPLETE)                                        
                                ),
                                wh.between(DbConstants.Task.COLUMN_TASK_COMPLETE_DATE, startDate, endDate)
                           ).countOf();
            } else {
                count = wh.and(
                                wh.eq(DbConstants.Task.COLUMN_EMPLOYEE_ID, e.getId()),
                                wh.eq(DbConstants.Task.COLUMN_TASK_STATUS, TaskStatus.COMPLETE)  
                            ).countOf();
            }
            
            data.add(new Pair<>(e, (int)count));
        }
        
        return data;
    }
    
    @Override
    public Notification createNewNotificaiton(Notification notify) throws SQLException {
        notificationDao.create(notify);
        return notify;
    }
    
    @Override
    public Notification deleteExistingNotificaiton(Notification notify) throws SQLException {
        notificationDao.delete(notify);
        return notify;
    }
    
    @Override
    public Notification updateExistingNotification(Notification notify) throws SQLException {
        notificationDao.update(notify);
        return notify;
    }
    
    @Override
    public List<Notification> findNotificationsByUser(User usr) throws SQLException {        
        return notificationDao.queryBuilder()
                .where()
                .eq(DbConstants.Notification.COLUMN_NOTIFICATION_USER, usr.getId())
                .query();
    }
    
    @Override
    public User findUserByEmployee(Employee e) throws SQLException {
        return userDao.queryBuilder()
                        .where()
                        .eq(DbConstants.User.COLUMN_EMPLOYEE_ID, e.getId())
                      .queryForFirst();
    }
    
    @Override
    public User findUserByManager(Manager m) throws SQLException {
        return userDao.queryBuilder()
                        .where()
                        .eq(DbConstants.User.COLUMN_MANAGER_ID, m.getId())
                      .queryForFirst();
    }
    
    @Override
    public Manager findManagerByDepartmentCode(String code) throws SQLException {
        /**
         * select * from managers 
         *          where department_id in ( select id from departments
         *                                             where department_code = ? )
         *          limit 1;
         */
        QueryBuilder<Department, Integer> departmentQuery = departmentDao.queryBuilder();
        departmentQuery.selectColumns(DbConstants.Department.COLUMN_ID);
        departmentQuery.setWhere(departmentQuery.where()
                                                .eq(DbConstants.Department.COLUMN_DEPARTMENT_CODE, code));
        
        Where<Manager, Integer> wh = managerDao.queryBuilder().where();
        
        return wh.in(DbConstants.Manager.COLUMN_DEPARTMENT_ID, departmentQuery)
                 .queryForFirst();
    }
}
