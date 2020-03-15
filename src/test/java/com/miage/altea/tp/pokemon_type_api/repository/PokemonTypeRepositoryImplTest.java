package com.miage.altea.tp.pokemon_type_api.repository;

import com.miage.altea.tp.pokemon_type_api.repository.impl.PokemonTypeRepositoryImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PokemonTypeRepositoryImplTest {

    private PokemonTypeRepositoryImpl repository = new PokemonTypeRepositoryImpl();

    @Test
    public void applicationContext_shouldLoadPokemonRepository(){

        var context = new AnnotationConfigApplicationContext("com.miage.altea.tp.pokemon_type_api.repository");
        var repoByName = context.getBean("pokemonTypeRepositoryImpl");
        var repoByClass = context.getBean(PokemonTypeRepository.class);

        assertEquals(repoByName, repoByClass);
        assertNotNull(repoByName);
        assertNotNull(repoByClass);
    }

    @Test
    public void findPokemonTypeById_with25_shouldReturnPikachu() {
        var pikachu = repository.findPokemonTypeById(25);
        assertNotNull(pikachu);
        assertEquals("pikachu", pikachu.getName());
        assertEquals(25, pikachu.getId());
    }

    @Test
    public void findPokemonTypeById_with145_shouldReturnZapdos() {
        var zapdos = repository.findPokemonTypeById(145);
        assertNotNull(zapdos);
        assertEquals("zapdos", zapdos.getName());
        assertEquals(145, zapdos.getId());
    }

    @Test
    public void findPokemonTypeByName_withEevee_shouldReturnEevee() {
        var eevee = repository.findPokemonTypeByName("eevee");
        assertNotNull(eevee);
        assertEquals("eevee", eevee.getName());
        assertEquals(133, eevee.getId());
    }

    @Test
    public void findPokemonTypeByName_withMewTwo_shouldReturnMewTwo() {
        var mewtwo = repository.findPokemonTypeByName("mewtwo");
        assertNotNull(mewtwo);
        assertEquals("mewtwo", mewtwo.getName());
        assertEquals(150, mewtwo.getId());
    }

    @Test
    public void findAllPokemonType_shouldReturn151Pokemons() {
        var pokemons = repository.findAllPokemonType();
        assertNotNull(pokemons);
        assertEquals(151, pokemons.size());
    }

    @Test
    public void findPokemonTypeByTypes_withElec_shouldReturnListWith9Pokemon_WithBugPoisoned_shouldReturnListWith5Pokemon() {
        var typeElectric = new ArrayList<String>();
        typeElectric.add("electric");

        var typeBugPoison = new ArrayList<String>();
        typeBugPoison.add("bug");
        typeBugPoison.add("poison");

        var pokemonElecList = repository.findPokemonTypeByType(typeElectric);
        assertNotNull(pokemonElecList);
        Assert.assertEquals(9,pokemonElecList.size());

        var pokemonBugPoison = repository.findPokemonTypeByType(typeBugPoison);
        assertNotNull(pokemonBugPoison);
        Assert.assertEquals(5,pokemonBugPoison.size());

    }
}
