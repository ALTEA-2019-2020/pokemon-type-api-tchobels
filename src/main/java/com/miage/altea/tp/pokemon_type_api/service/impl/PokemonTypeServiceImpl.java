package com.miage.altea.tp.pokemon_type_api.service.impl;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import com.miage.altea.tp.pokemon_type_api.repository.TranslationRepository;
import com.miage.altea.tp.pokemon_type_api.service.PokemonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    public PokemonTypeRepository pokemonTypeRepository;
    public TranslationRepository translationRepository;

    @Autowired
    public PokemonTypeServiceImpl(PokemonTypeRepository pokemonTypeRepository, TranslationRepository translationRepository) {
        this.pokemonTypeRepository = pokemonTypeRepository;
        this.translationRepository = translationRepository;
    }

    public PokemonTypeServiceImpl() {
    }

    public PokemonTypeServiceImpl(PokemonTypeRepository pokemonTypeRepository) {
        this.pokemonTypeRepository = pokemonTypeRepository;
    }

    @Autowired
    public void setPokemonTypeRepository(PokemonTypeRepository pokemonTypeRepository) {
        this.pokemonTypeRepository = pokemonTypeRepository;
    }

    public void setTranslationRepository(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }

    @Override
    public PokemonType getPokemonType(int id) {
        PokemonType type = pokemonTypeRepository.findPokemonTypeById(id);
        if (translationRepository != null) {
            String translated = translationRepository.getPokemonName(id, LocaleContextHolder.getLocale());
            if (translated != null) type.setName(translated);
        }
        return type;
    }

    @Override
    public PokemonType getPokemonType(String name) {
        return pokemonTypeRepository.findPokemonTypeByName(name);
    }

    @Override
    public List<PokemonType> getAllPokemonTypes() {
        List<PokemonType> list = pokemonTypeRepository.findAllPokemonType();
        list.forEach((PokemonType p) -> {
                    if (translationRepository != null) {
                        String translated = translationRepository.getPokemonName(p.getId(), LocaleContextHolder.getLocale());
                        if (translated != null) p.setName(translated);
                    }
                }
        );
        return list;
    }

    @Override
    public List<PokemonType> getPokemonByTypes(List<String> types) {
        return pokemonTypeRepository.findPokemonTypeByType(types);
    }

}
