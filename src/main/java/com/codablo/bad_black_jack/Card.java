package com.codablo.bad_black_jack;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Card {
    private Rank rank;
    private Suite suite;
}
