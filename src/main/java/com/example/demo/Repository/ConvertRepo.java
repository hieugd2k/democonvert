package com.example.demo.Repository;

import com.example.demo.Domain.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface ConvertRepo extends JpaRepository<QuestionAnswer,String> {
}
