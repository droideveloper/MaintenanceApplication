/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.common;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.fs.maintenance.entities.*;

/**
 *
 * @author Fatih
 */
public interface IDatabaseManager {
    
    /**
     * 
     * @throws SQLException 
     */
    void setUp() throws SQLException;
    
    /**
     * 
     * @throws SQLException 
     */
    void initWithMockData() throws SQLException;
    
    /**
     * 
     * @throws SQLException
     */
    void dispose() throws SQLException;
    
    /**
     * 
     * @param userName
     * @return
     * @throws SQLException 
     */
    User findUserByUserName(String userName) throws SQLException;
    
    /**
     * 
     * @param id
     * @return
     * @throws SQLException 
     */
    User findUserByUserId(Integer id) throws SQLException;

    /**
     * 
     * @param id
     * @return
     * @throws SQLException 
     */
    Employee findEmployeeByEmployeeId(Integer id) throws SQLException;
    
    /**
     * 
     * @param name
     * @param surname
     * @return
     * @throws SQLException 
     */
    Employee findEmployeeByEmployeeNameAndSurname(String name, String surname) throws SQLException;
    
    /**
     * 
     * @return
     * @throws SQLException 
     */
    List<Employee> findEmployeesWorkAtItDepartment() throws SQLException;
    
    /**
     * 
     * @param id
     * @return
     * @throws SQLException 
     */
    Manager findManagerByManagerId( Integer id) throws SQLException;
    
    /**
     * 
     * @param name
     * @param surname
     * @return
     * @throws SQLException 
     */
    Manager findManagerByManagerNameAndSurname(String name, String surname) throws SQLException;
    
    /**
     * 
     * @param code
     * @return
     * @throws SQLException 
     */
    Manager findManagerByDepartmentCode(String code) throws SQLException;
    
    /**
     * 
     * @param id
     * @return
     * @throws SQLException 
     */
    Department findDepartmentByDepartmentId(Integer id) throws SQLException;
    
    /**
     * 
     * @param departmentCode
     * @return
     * @throws SQLException 
     */
    Department findDepartmentByDepartmentCode(String departmentCode) throws SQLException;
    
    /**
     * 
     * @param usr
     * @return
     * @throws SQLException 
     */
    Department findDepartmentByUser(User usr) throws SQLException;
    
    /**
     * 
     * @param request
     * @return
     * @throws SQLException 
     */
    Request createNewRequest(Request request) throws SQLException;
    
    /**
     * 
     * @param request
     * @return
     * @throws SQLException 
     */
    Request updateExistingRequest(Request request) throws SQLException;
    
    /**
     * 
     * @param request
     * @return
     * @throws SQLException 
     */
    Request deleteExisitingRequest(Request request) throws SQLException;
    
    /**
     * 
     * @param department
     * @return
     * @throws SQLException 
     */
    List<Request> findRequestsCreatedByDepartmentWaitingManagerApproval(Department department) throws SQLException;
    
    /**
     * 
     * @param user
     * @return
     * @throws SQLException 
     */
    List<Request> findRequestsCreatedByUser(User user) throws SQLException;
    
    /**
     * 
     * @return
     * @throws SQLException 
     */
    List<Request> findRequestsWaitingItManagerApproval() throws SQLException;
    
    /**
     * 
     * @param taks
     * @return
     * @throws SQLException 
     */
    Task createNewTask(Task taks) throws SQLException;
    
    /**
     * 
     * @param task
     * @return
     * @throws SQLException 
     */
    Task updateExisitingTask(Task task) throws SQLException;
    
    /**
     * 
     * @param task
     * @return
     * @throws SQLException 
     */
    Task deleteExistingTask(Task task) throws SQLException;
    
    /**
     * 
     * @param id
     * @return
     * @throws SQLException 
     */
    Task findTaskByTaskId(Integer id) throws SQLException;
    
    /**
     * 
     * @param employee
     * @return
     * @throws SQLException 
     */
    Task findTaskItEmployeeCurrentlyWorkingOn(Employee employee) throws SQLException;
    
    /**
     * 
     * @param employee
     * @return
     * @throws SQLException 
     */
    Integer calculateNumberOfTasksAssignedToItEmployee(Employee employee) throws SQLException;
    
    /**
     * 
     * @param employee
     * @return
     * @throws SQLException 
     */
    List<Task> findTasksAssignedToItEmployee(Employee employee) throws SQLException;
    
    /**
     * 
     * @param employee
     * @return
     * @throws SQLException 
     */
    List<Task> findTasksNotCompletedAssignedToItEmployee(Employee employee) throws SQLException;
    
    /**
     * 
     * @return
     * @throws SQLException 
     */
    List<Task> findTasksNotAssignedToAnyItEmployees() throws SQLException;

    /**
     * @param startDate
     * @param endDate
     * @return
     * @throws SQLException 
     */
    List<Pair<Department, Integer>> calculateNumberOfRequestsCreatedByEachDepartment(Date startDate, Date endDate) throws SQLException;
    
    /**
     * @param startDate
     * @param endDate
     * @return
     * @throws SQLException 
     */
    List<Pair<Employee, Task>> findTasksEachEmployeeCurrentlyWorkingOn(Date startDate, Date endDate) throws SQLException;
    
    /**
     * @param startDate
     * @param endDate
     * @return
     * @throws SQLException 
     */
    List<Pair<Employee, Integer>> calculateNumberOfTasksAssignedToEachItEmployee(Date startDate, Date endDate) throws SQLException; 
    
    /**
     * @param startDate
     * @param endDate
     * @return
     * @throws SQLException 
     */
    List<Pair<Employee, Integer>> calculateNumberOfTasksCompletedByEachItEmployee(Date startDate, Date endDate) throws SQLException;
    
    /**
     * 
     * @param notify
     * @return 
     * @throws java.sql.SQLException 
     */
    Notification createNewNotificaiton(Notification notify) throws SQLException;
    
    /**
     * 
     * @param notify
     * @return 
     * @throws java.sql.SQLException 
     */
    Notification deleteExistingNotificaiton(Notification notify) throws SQLException;
    
    /**
     * 
     * @param notify
     * @return 
     * @throws java.sql.SQLException 
     */
    Notification updateExistingNotification(Notification notify) throws SQLException;
    
    /**
     * 
     * @param usr
     * @return 
     * @throws java.sql.SQLException 
     */
    List<Notification> findNotificationsByUser(User usr) throws SQLException;
    
    /**
     * 
     * @param e
     * @return
     * @throws SQLException 
     */
    User findUserByEmployee(Employee e) throws SQLException;
    
    /**
     * 
     * @param m
     * @return
     * @throws SQLException 
     */
    User findUserByManager(Manager m) throws SQLException;
}
