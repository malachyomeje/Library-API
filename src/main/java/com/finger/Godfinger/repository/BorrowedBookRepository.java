package com.finger.Godfinger.repository;

import com.finger.Godfinger.model.BorrowEBooks;
import com.finger.Godfinger.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowedBookRepository extends JpaRepository<BorrowEBooks, Long> {

Optional<BorrowEBooks>findByBorrowedBookId(Long id);
List<BorrowEBooks> findAllByStudent(Student student);


}
