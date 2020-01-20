package com.miage.altea.pokemon_type_api.service;

import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import com.miage.altea.tp.pokemon_type_api.repository.impl.PokemonTypeRepositoryImpl;
import com.miage.altea.tp.pokemon_type_api.service.PokemonTypeService;
import com.miage.altea.tp.pokemon_type_api.service.impl.PokemonTypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PokemonTypeServiceImplTest {

    @Test
    void applicationContext_shouldLoadPokemonTypeService() {
        var context = new AnnotationConfigApplicationContext(PokemonTypeServiceImpl.class, PokemonTypeRepositoryImpl.class);
        var serviceByName = context.getBean("pokemonTypeServiceImpl");
        var serviceByClass = context.getBean(PokemonTypeService.class);

        assertEquals(serviceByName, serviceByClass);
        assertNotNull(serviceByName);
        assertNotNull(serviceByClass);
    }

    @Test
    void pokemonTypeRepository_shouldBeAutowired_withSpring() {
        var context = new AnnotationConfigApplicationContext(PokemonTypeServiceImpl.class, PokemonTypeRepositoryImpl.class);
        var service = context.getBean(PokemonTypeServiceImpl.class);
        assertNotNull(service.pokemonTypeRepository);
    }

    @Test
    void pokemonTypeRepository_shouldBeCalled_whenFindById() {
        var pokemonTypeRepository = mock(PokemonTypeRepository.class);
        var pokemonTypeService = new PokemonTypeServiceImpl(pokemonTypeRepository);

        pokemonTypeService.getPokemonType(25);

        verify(pokemonTypeRepository).findPokemonTypeById(25);
    }

    @Test
    void pokemonTypeRepository_shouldBeCalled_whenFindAll() {
        var pokemonTypeRepository = mock(PokemonTypeRepository.class);
        var pokemonTypeService = new PokemonTypeServiceImpl(pokemonTypeRepository);

        pokemonTypeService.getAllPokemonTypes();

        verify(pokemonTypeRepository).findAllPokemonType();
    }

    @Test
    void pokemonTypeRepository_shouldBeCalled_whenFindByName() {
        var pokemonTypeRepository = mock(PokemonTypeRepository.class);
        var pokemonTypeService = new PokemonTypeServiceImpl(pokemonTypeRepository);

        pokemonTypeService.getPokemonType("zapdos");

        verify(pokemonTypeRepository).findPokemonTypeByName("zapdos");
    }
}
