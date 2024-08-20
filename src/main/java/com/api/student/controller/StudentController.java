package com.api.student.controller;

import com.api.student.entities.Student;
import com.api.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/estudantes")
public class StudentController {

    //TODO: MIGRAR A REGRA DE NEGÓCIO PARA O SERVIÇO

    @Autowired
    private StudentService studentService;

    @Operation(summary = "Criar um novo estudante",
            description = "Essa funcionalidade cria um novo estudante!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estudante criado com sucesso!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class)))
    })
    @PostMapping
    public ResponseEntity<Student> criarEstudante(@RequestBody Student estudante) {
        Student salvo = studentService.salvarEstudante(estudante);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Buscar todos estudantes!",
            description = "Essa funcionalidade busca todos estudantes no banco de dados!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudante encontrado!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class)))
    })
    @GetMapping
    public ResponseEntity<List<Student>> listarEstudantes() {
        List<Student> estudantes = studentService.listarTodos();
        return new ResponseEntity<>(estudantes, HttpStatus.OK);
    }

    @Operation(summary = "Buscar um estudante por id!",
            description = "Essa funcionalidade um estudante por id!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudante encontrado!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class)))
    })
    @GetMapping("/{id}")
    public Optional<Student> buscarEstudantePorId(@PathVariable UUID id) {
        Optional<Student> estudante = studentService.buscarPorId(id);
        return estudante; 
    }

    @Operation(summary = "Atualizar um estudante por id",
            description = "Essa funcionalidade atualiza um estudante por id!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudante atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Student> atualizarEstudante(@PathVariable UUID id, @RequestBody Student estudante) {
       Student student = studentService.atualizarEstudante(id,estudante);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @Operation(summary = "Deletar um estudante por id!",
            description = "Essa funcionalidade deleta um estudante!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Estudante deletado com sucesso!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEstudante(@PathVariable UUID id) {

        studentService.deletarEstudante(id);
        return ResponseEntity.noContent().build();
    }
}