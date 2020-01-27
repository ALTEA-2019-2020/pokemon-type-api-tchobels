package com.miage.altea.tp.pokemon_type_api.service;

import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import com.miage.altea.tp.pokemon_type_api.repository.impl.PokemonTypeRepositoryImpl;
import com.miage.altea.tp.pokemon_type_api.service.impl.PokemonTypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

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

    @Test
    void pokemonTypeRepository_shouldBeCalled_whenFindByTypes() {
        var pokemonTypeRepository = mock(PokemonTypeRepository.class);
        var pokemonTypeService = new PokemonTypeServiceImpl(pokemonTypeRepository);

        var typeElectric = new ArrayList<String>();
        typeElectric.add("electric");

        var typeBugPoison = new ArrayList<String>();
        typeBugPoison.add("bug");
        typeBugPoison.add("poison");

        pokemonTypeService.getPokemonByTypes(typeElectric);
        pokemonTypeService.getPokemonByTypes(typeBugPoison);

        verify(pokemonTypeRepository).findPokemonTypeByType(typeElectric);
        verify(pokemonTypeRepository).findPokemonTypeByType(typeBugPoison);
    }
}
