package com.miage.altea.tp.pokemon_type_api.controller;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.service.PokemonTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PokemonTypeControllerTest {

    @Test
    void pokemonTypeController_shouldBeAnnotated(){
        var controllerAnnotation =
                PokemonTypeController.class.getAnnotation(RestController.class);
        assertNotNull(controllerAnnotation);

        var requestMappingAnnotation =
                PokemonTypeController.class.getAnnotation(RequestMapping.class);
        assertArrayEquals(new String[]{"/pokemon-types"}, requestMappingAnnotation.value());
    }

    @Test
    void getPokemonTypeFromId_shouldBeAnnotated() throws NoSuchMethodException {
        var getPokemonTypeFromId =
                PokemonTypeController.class.getDeclaredMethod("getPokemonTypeFromId", int.class);
        var getMapping = getPokemonTypeFromId.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/{id}"}, getMapping.value());
    }

    @Test
    void getPokemonTypeFromName_shouldBeAnnotated() throws NoSuchMethodException {
        var getPokemonTypeFromName =
                PokemonTypeController.class.getDeclaredMethod("getPokemonTypeFromName", String.class);
        var getMapping = getPokemonTypeFromName.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/"}, getMapping.value());
    }

    @Test
    void getAllPokemonTypes_shouldBeAnnotated() throws NoSuchMethodException {
        var getAllPokemonTypes =
                PokemonTypeController.class.getDeclaredMethod("getAllPokemonTypes");
        var getMapping = getAllPokemonTypes.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/"}, getMapping.value());
    }

    @Test
    void getPokemonTypeFromTypes_shouldBeAnnotated() throws NoSuchMethodException {
        var getPokemonTypeFromTypes =
                PokemonTypeController.class.getDeclaredMethod("getPokemonTypeFromTypes", List.class);
        var getMapping = getPokemonTypeFromTypes.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/"}, getMapping.value());
    }


    @Test
    void getPokemonType_shouldCallTheService() {
        var service = mock(PokemonTypeService.class);
        var controller = new PokemonTypeController(service);

        var pikachu = new PokemonType();
        pikachu.setId(25);
        pikachu.setName("pikachu");
        when(service.getPokemonType(25)).thenReturn(pikachu);

        var pokemon = controller.getPokemonTypeFromId(25);
        assertEquals("pikachu", pokemon.getName());

        verify(service).getPokemonType(25);
    }


    @Test
    void getPokemonTypeByName_shouldCallTheService() {
        var service = mock(PokemonTypeService.class);
        var controller = new PokemonTypeController(service);

        var pikachu = new PokemonType();
        pikachu.setId(25);
        pikachu.setName("pikachu");
        when(service.getPokemonType("pikachu")).thenReturn(pikachu);

        var pokemon = controller.getPokemonTypeFromName("pikachu");
        assertEquals("pikachu", pokemon.getName());

        verify(service).getPokemonType("pikachu");
    }

    @Test
    void getAllPokemonTypes_shouldCallTheService() {
        var service = mock(PokemonTypeService.class);
        var controller = new PokemonTypeController(service);

        controller.getAllPokemonTypes();

        verify(service).getAllPokemonTypes();
    }

    @Test
    void getPokemonTypeFromTypes_shouldCallTheService() {
        var service = mock(PokemonTypeService.class);
        var controller = new PokemonTypeController(service);

        var typeElectric = new ArrayList<String>();
        typeElectric.add("electric");

        var typeBugPoison = new ArrayList<String>();
        typeBugPoison.add("bug");
        typeBugPoison.add("poison");

        var pokemonElecList = new ArrayList<PokemonType>();
        var pikachu = new PokemonType();
        pikachu.setId(25);
        pikachu.setName("pikachu");
        pokemonElecList.add(pikachu);

        var pokemonBugPoisonList = new ArrayList<PokemonType>();
        var venonat = new PokemonType();
        venonat.setId(48);
        venonat.setName("venonat");
        pokemonBugPoisonList.add(venonat);

        when(service.getPokemonByTypes(typeElectric)).thenReturn(pokemonElecList);
        when(service.getPokemonByTypes(typeBugPoison)).thenReturn(pokemonBugPoisonList);

        var pokemonElec = controller.getPokemonTypeFromTypes(typeElectric);
        assertEquals("pikachu", pokemonElecList.get(0).getName());
        assertNotEquals("venonat", pokemonElecList.get(0).getName());

        var pokemonBugPoison = controller.getPokemonTypeFromTypes(typeBugPoison);
        assertEquals("venonat",pokemonBugPoison.get(0).getName());

        verify(service).getPokemonByTypes(typeElectric);
        verify(service).getPokemonByTypes(typeBugPoison);
    }

}