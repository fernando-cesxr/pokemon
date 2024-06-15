package com.example.pokemon.config;

import java.util.List;

import com.example.pokemon.models.Attacks;
import com.example.pokemon.models.Itens;
import com.example.pokemon.models.Pokemon;
import com.example.pokemon.models.User;
import com.example.pokemon.repository.AttacksRepository;
import com.example.pokemon.repository.ItensRepository;
import com.example.pokemon.repository.PokemonRepository;
import com.example.pokemon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    AttacksRepository attacksRepository;

    @Autowired
    PokemonRepository pokemonRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItensRepository itensRepository;


    @Override
    public void run(String... args) throws Exception {

        Attacks attackFlameBurst = Attacks.builder().name("Flame Burst").type("Fire").damage(12).build();
        Attacks attackConfusion = Attacks.builder().name("Confusion").type("Psychic").damage(20).build();
        Attacks attackPsystrike = Attacks.builder().name("Psystrike").type("Psychic").damage(90).build();
        Attacks attackEarthquake = Attacks.builder().name("Earthquake").type("Ground").damage(140).build();
        Attacks attackDragonTail = Attacks.builder().name("Dragon Tail").type("Dragon").damage(15).build();
        Attacks attackOutrage = Attacks.builder().name("Outrage").type("Dragon").damage(110).build();
        Attacks attackThunderShock = Attacks.builder().name("Thunder Shock").type("Eletric").damage(5).build();
        Attacks attackHyperBeam = Attacks.builder().name("Hyper Beam").type("Normal").damage(150).build();
        Attacks attackWaterfall = Attacks.builder().name("Waterfall").type("Water").damage(16).build();
        Attacks attackHydroPump = Attacks.builder().name("Hydro Pump").type("Water").damage(130).build();

        attacksRepository.saveAll(List.of(attackFlameBurst, attackConfusion, attackPsystrike, attackWaterfall,
                attackEarthquake, attackHydroPump, attackDragonTail, attackOutrage, attackThunderShock, attackHyperBeam));

        Pokemon pokemonPikachu = Pokemon.builder()
                .name("Pikachu")
                .type("Electric")
                .height(0.72)
                .firstAttack("attackThunderShock")
                .secondAttack("attackFlameBurst")
                .nr_Attack(4)
                .nr_Defense(8)
                .nr_Hp(65)
                .level(2)
                .build();
        Pokemon pokemonCharmander = Pokemon.builder()
                .name("Charmander")
                .type("fire")
                .height(0.48)
                .firstAttack("attackOutrage")
                .secondAttack("attackEarthquake")
                .nr_Attack(14)
                .nr_Defense(21)
                .nr_Hp(13)
                .level(3)
                .build();
        Pokemon pokemonCharmeleon = Pokemon.builder()
                .name("Charmeleon")
                .type("fire")
                .height(1.10)
                .firstAttack("attackFlameBurst")
                .secondAttack("attackHyperBeam")
                .nr_Attack(14)
                .nr_Defense(12)
                .nr_Hp(83)
                .level(23)
                .build();
        Pokemon pokemonDragonite = Pokemon.builder()
                .name("Dragonite")
                .type("Dragon")
                .height(1.81)
                .firstAttack("attackDragonTail")
                .secondAttack("attackOutrage")
                .nr_Attack(10)
                .nr_Defense(12)
                .nr_Hp(167)
                .level(3)
                .build();
        Pokemon pokemonMewtwo = Pokemon.builder()
                .name("Mewtwo")
                .type("Psychic")
                .height(15.49)
                .firstAttack("attackConfusion")
                .secondAttack("attackPsystrike")
                .nr_Attack(15)
                .nr_Defense(15)
                .nr_Hp(177)
                .level(50)
                .build();
        Pokemon pokemonMelmetal = Pokemon.builder()
                .name("Melmetal")
                .type("Steel")
                .height(2.5)
                .firstAttack("attackThunderShock")
                .secondAttack("attackHyperBeam")
                .nr_Attack(13)
                .nr_Defense(15)
                .nr_Hp(211)
                .level(50)
                .build();
        Pokemon pokemonSlaking = Pokemon.builder()
                .name("Slaking")
                .type("Normal")
                .height(1.81)
                .firstAttack("attackWaterfall")
                .secondAttack("attackEarthquake")
                .nr_Attack(13)
                .nr_Defense(13)
                .nr_Hp(214)
                .level(35)
                .build();
        Pokemon pokemonRhyperior = Pokemon.builder()
                .name("Rhyperior")
                .type("Groud/Rock")
                .height(2.26)
                .firstAttack("attackWaterfall")
                .secondAttack("attackEarthquake")
                .nr_Attack(15)
                .nr_Defense(15)
                .nr_Hp(194)
                .level(35)
                .build();
        Pokemon pokemonGyarados = Pokemon.builder()
                .name("Gyarados")
                .type("Water/Flying")
                .height(5.91)
                .firstAttack("attackWaterfall")
                .secondAttack("attackHydroPump")
                .nr_Attack(14)
                .nr_Defense(14)
                .nr_Hp(171)
                .level(32)
                .build();
        Pokemon pokemonSalamance = Pokemon.builder()
                .name("Salamance")
                .type("Dragon/Flying")
                .height(1.77)
                .firstAttack("attackDragonTail")
                .secondAttack("attackOutrage")
                .nr_Attack(13)
                .nr_Defense(11)
                .nr_Hp(166)
                .level(39)
                .build();

        pokemonRepository.saveAll(List.of(pokemonPikachu, pokemonCharmander, pokemonCharmeleon, pokemonDragonite, pokemonMewtwo,
                pokemonMelmetal, pokemonSlaking, pokemonRhyperior, pokemonGyarados, pokemonSalamance));


        User user1 = User.builder().email("Fernando@gmail.com").password("rM74%7^Ocnv%").build();
        User user2 = User.builder().email("Cesar@gmail.com").password("w4B0!u4bA%&^").build();
        User user3 = User.builder().email("Carlos@gmail.com").password("ipYD9)6?uh9K").build();
        User user4 = User.builder().email("yangonogueira.martins6@yahoo.com").password("28E~kVR$V6rH").build();
        User user5 = User.builder().email("nbiabraga_xavier@yahoo.com").password("&7bt7vX96k!D").build();
        User user6 = User.builder().email("saranogueira40@live.com").password("J053@e9F7^#X").build();
        User user7 = User.builder().email("calebemacedo.albuquerque@yahoo.com").password("w[VK5V920^it").build();
        User user8 = User.builder().email("denevalxavier24@bol.com.br").password("oTwu7}2DcW4!").build();
        User user9 = User.builder().email("lorrainesilva_macedo@bol.com.br").password("]57cD!2GUw£f").build();
        User user10 = User.builder().email("flixsilva40@live.com").password("6M4n>2NV0Z{*").build();

        userRepository.saveAll(List.of(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10));

        Itens item1 = Itens.builder()
                .name("Potion")
                .description("Restores a small amount of HP.")
                .type("Health")
                .quantity("5")
                .build();

        Itens item2 = Itens.builder()
                .name("Super Potion")
                .description("Restores a moderate amount of HP.")
                .type("Health")
                .quantity("10")
                .build();

        Itens item3 = Itens.builder()
                .name("Pokeball")
                .description("A device for catching wild Pokémon.")
                .type("Capture")
                .quantity("20")
                .build();

        Itens item4 = Itens.builder()
                .name("Great Ball")
                .description("A high-performance Poké Ball with a higher catch rate than a standard Poké Ball.")
                .type("Capture")
                .quantity("15")
                .build();

        Itens item5 = Itens.builder()
                .name("Hyper Potion")
                .description("Restores a large amount of HP.")
                .type("Health")
                .quantity("7")
                .build();

        Itens item6 = Itens.builder()
                .name("Full Heal")
                .description("Cures all status conditions of a Pokémon.")
                .type("Cure")
                .quantity("10")
                .build();

        Itens item7 = Itens.builder()
                .name("Revive")
                .description("Revives a fainted Pokémon and restores half of its max HP.")
                .type("Health")
                .quantity("3")
                .build();

        Itens item8 = Itens.builder()
                .name("Antidote")
                .description("Cures a poisoned Pokémon.")
                .type("Cure")
                .quantity("12")
                .build();

        Itens item9 = Itens.builder()
                .name("Paralyze Heal")
                .description("Cures a paralyzed Pokémon.")
                .type("Cure")
                .quantity("8")
                .build();

        Itens item10 = Itens.builder()
                .name("Max Revive")
                .description("Revives a fainted Pokémon and restores its full HP.")
                .type("Health")
                .quantity("5")
                .build();

        itensRepository.saveAll(List.of(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10));


    }
}

