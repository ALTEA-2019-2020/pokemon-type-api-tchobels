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
public class Stats {

    private Integer speed;
    private Integer defense;
    private Integer attack;
    private Integer hp;
}
