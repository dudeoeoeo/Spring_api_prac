package com.cos.book.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.book.domain.Book;
import com.cos.book.service.BookService;

import lombok.RequiredArgsConstructor;

// 스프링이 IoC 컨테이너에 Bean 들을 올릴 때(객체 생성자의 메모리가 올라감) 받기 위해서
// 변수에 final 을 붙이면 자동으로 이 어노테이션에 의하여 생성자가 자동으로 만들어짐
@RequiredArgsConstructor 
@RestController
public class BookController {
	
	private final BookService bookService;
	
	@PostMapping("/book")
	public ResponseEntity<?> save(@RequestBody Book book) {
		return new ResponseEntity<>(bookService.저장하기(book), HttpStatus.CREATED); // 200
	}
	
	@GetMapping("/book")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(bookService.모두가져오기(), HttpStatus.OK); // 200
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return new ResponseEntity<>(bookService.한건가져오기(id), HttpStatus.OK); // 200
	}
	
	@PutMapping("/book/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Book book) {
		return new ResponseEntity<>(bookService.수정하기(id, book), HttpStatus.OK); // 200
	}
	
	@DeleteMapping("/book/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return new ResponseEntity<>(bookService.삭제하기(id), HttpStatus.OK); // 200
	}
}
