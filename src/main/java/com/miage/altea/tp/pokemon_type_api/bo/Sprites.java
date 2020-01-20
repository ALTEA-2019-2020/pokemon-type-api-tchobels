package com.miage.altea.tp.pokemon_type_api.bo;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Sprites {

    private String back_default;
    private String front_default;
}
