package project.webapplication.erpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.webapplication.erpsystem.models.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
}
