package com.example.ali_nino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ali_nino.dao.entity.Book;
import com.example.ali_nino.dao.repository.BookRepository;
import com.example.ali_nino.exception.BadRequestException;
import com.example.ali_nino.exception.NotFoundException;
import com.example.ali_nino.mapper.BookMapper;
import com.example.ali_nino.model.BookDto;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookMapper bookMapper; 
	
	public List<BookDto> getAllBooks(){
		List<Book> books = bookRepository.findAll();
		List<BookDto> bookDtos = bookMapper.entityListToDtoList(books);
		return bookDtos;
	}
	
	public BookDto getById(Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> {
					throw new NotFoundException("Book doesn't exist with given id");
				});
		BookDto bookDto = bookMapper.entityToDto(book);
		return bookDto;
	}
	
	public BookDto createBook(BookDto bookDto) {
		if(bookDto == null) {
			throw new BadRequestException("Book creation request can't be null");
		}
		
		Book entity = bookMapper.dtoToEntity(bookDto);
		bookRepository.save(entity);
		return bookDto;
	}
	
	public BookDto updateBook(BookDto bookDto,Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> {
					throw new NotFoundException("Book doesn't exist with given id");
				});
		book.setName(bookDto.getName());
		book.setPageCount(bookDto.getPageCount());
		book.setPrice(bookDto.getPrice());
		book.setAuthorName(bookDto.getAuthorName());
		bookRepository.save(book);
		BookDto response = bookMapper.entityToDto(book);
		return response;
	}
	
	public List<BookDto> findBooksByPriceRange(Double minPrice, Double maxPrice){
		if(minPrice>=maxPrice)
			throw new BadRequestException("Minimum price must be less than maximum price");
		List<Book> books = bookRepository.findByPriceBetween(minPrice, maxPrice);
		List<BookDto> response = bookMapper.entityListToDtoList(books);
		return response;
	}
	
}
