package project.webapplication.erpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.webapplication.erpsystem.models.Attendance;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    @Query("SELECT a FROM Attendance a WHERE a.employee.employeeId = :employeeId")
    List<Attendance> findByEmployeeId(String employeeId);

    @Query("SELECT COUNT(a) > 0 FROM Attendance a WHERE a.checkIn = :checkIn AND a.employee.employeeId = :employeeId")
    boolean existsByCheckInAndEmployeeId(LocalDate checkIn,String employeeId);

    @Query("SELECT a FROM Attendance a WHERE a.employee.employeeId = :employeeId AND a.checkIn = :checkIn")
    Optional<Attendance> findByEmployeeIdAndCheckIn(String employeeId, LocalDate checkIn);

    @Query("SELECT COUNT(a) FROM Attendance a WHERE FUNCTION('MONTH', a.checkIn) = :month AND FUNCTION('YEAR', a.checkIn) = :year AND a.employee.employeeId = :employeeId")
    int countDaysByMonthAndYearAndEmployeeId(int month, int year, String employeeId);

}
