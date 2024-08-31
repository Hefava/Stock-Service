package com.bootcamp.stock.categoria.ports.persistency.mysql.adapter;

import com.bootcamp.stock.domain.model.Category;
import com.bootcamp.stock.domain.utils.pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.pagination.PagedResult;
import com.bootcamp.stock.domain.utils.pagination.SortUtil;
import com.bootcamp.stock.ports.persistency.mysql.adapter.CategoryAdapter;
import com.bootcamp.stock.ports.persistency.mysql.mapper.CategoryEntityMapper;
import com.bootcamp.stock.ports.persistency.mysql.entity.CategoryEntity;
import com.bootcamp.stock.ports.persistency.mysql.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private CategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryAdapter categoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCategory_ShouldCallRepositorySave() {
        Category category = new Category(1L, "Electronics", "Electronic gadgets");

        categoryAdapter.saveCategory(category);

        verify(categoryEntityMapper, times(1)).toEntity(category);
        verify(categoryRepository, times(1)).save(any());
    }

    @Test
    void getCategoriesByNombre_ShouldReturnPagedResult() {
        SortUtil sort = new SortUtil("name", SortUtil.Direction.ASC);
        PageRequestUtil pageRequestCategory = new PageRequestUtil(0, 5);

        Page<CategoryEntity> page = mock(Page.class);
        List<CategoryEntity> entities = List.of(
                new CategoryEntity(1L, "Electronics", "Electronic gadgets"),
                new CategoryEntity(2L, "Books", "Various books")
        );

        when(categoryRepository.findAll(any(PageRequest.class))).thenReturn(page);
        when(page.getContent()).thenReturn(entities);
        when(page.getNumber()).thenReturn(0);
        when(page.getSize()).thenReturn(5);
        when(page.getTotalPages()).thenReturn(1);
        when(page.getTotalElements()).thenReturn(2L);

        List<Category> categories = List.of(
                new Category(1L, "Electronics", "Electronic gadgets"),
                new Category(2L, "Books", "Various books")
        );

        when(categoryEntityMapper.toCategory(any())).thenReturn(categories.get(0), categories.get(1));

        PagedResult<Category> result = categoryAdapter.getCategoriesByNombre(sort, pageRequestCategory);

        assertEquals(0, result.getPage());
        assertEquals(5, result.getPageSize());
        assertEquals(1, result.getTotalPages());
        assertEquals(2L, result.getTotalCount());
        assertEquals(categories, result.getContent());

        verify(categoryRepository, times(1)).findAll(any(PageRequest.class));
        verify(categoryEntityMapper, times(2)).toCategory(any());
    }

    @Test
    void findByNombre_ShouldReturnCategory() {
        CategoryEntity entity = new CategoryEntity(1L, "Electronics", "Electronic gadgets");
        Category expectedCategory = new Category(1L, "Electronics", "Electronic gadgets");

        when(categoryRepository.findByNombre("Electronics")).thenReturn(Optional.of(entity));
        when(categoryEntityMapper.toCategory(entity)).thenReturn(expectedCategory);

        Optional<Category> result = categoryAdapter.findByNombre("Electronics");

        assertEquals(Optional.of(expectedCategory), result);
        verify(categoryRepository, times(1)).findByNombre("Electronics");
        verify(categoryEntityMapper, times(1)).toCategory(entity);
    }

    @Test
    void findByNombre_ShouldReturnEmptyOptionalWhenNotFound() {
        when(categoryRepository.findByNombre("NonExistent")).thenReturn(Optional.empty());

        Optional<Category> result = categoryAdapter.findByNombre("NonExistent");

        assertEquals(Optional.empty(), result);
        verify(categoryRepository, times(1)).findByNombre("NonExistent");
        verify(categoryEntityMapper, never()).toCategory(any());
    }
}