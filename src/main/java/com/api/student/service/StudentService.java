package com.api.student.service;

import com.api.student.entities.Student;
import com.api.student.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student salvarEstudante(Student estudante) {
        return studentRepository.save(estudante);
    }

    public List<Student> listarTodos() {
        return studentRepository.findAll();
    }

    public Optional<Student> buscarPorId(UUID id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Estudante não encontrado no banco de dados."));

        return studentRepository.findById(student.getId());
    }

    public void deletarEstudante(UUID id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Estudante não encontrado no banco de dados."));

        studentRepository.deleteById(student.getId());
    }

    public Student atualizarEstudante (UUID id, Student  estudante) {
        Student oldStudent = studentRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Estudante não encontrado no banco de dados."));

        oldStudent.setEmail(estudante.getEmail());
        oldStudent.setDataNascimento(estudante.getDataNascimento());
        oldStudent.setNome(estudante.getNome());

        return studentRepository.save(oldStudent);
    }
}

