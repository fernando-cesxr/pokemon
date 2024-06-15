package com.example.pokemon.config;

import java.time.LocalDate;
import java.util.List;

import com.example.pokemon.models.*;
import com.example.pokemon.repository.*;
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

    @Autowired
    PokemonAttacksRepository pokemonAttacksRepository;

    @Autowired
    GymRepository gymRepository;

    @Autowired
    TrainersRepository trainersRepository;

    @Autowired
    TrainersGymRepository trainersGymRepository;

    @Autowired
    CaptureRepository captureRepository;

    @Autowired
    PokestopsRepository pokestopsRepository;

    @Autowired
    PokestopsTrainersRepository pokestopsTrainersRepository;

    @Autowired
    ItensPokestopsRepository itensPokestopsRepository;

    @Override
    public void run(String... args) throws Exception {

        Attacks attackFlameBurst = Attacks.builder().name("Flame Burst").type("Fire").damage(12).isCharged(false).build();
        Attacks attackConfusion = Attacks.builder().name("Confusion").type("Psychic").damage(20).isCharged(false).build();
        Attacks attackPsystrike = Attacks.builder().name("Psystrike").type("Psychic").damage(90).isCharged(true).build();
        Attacks attackEarthquake = Attacks.builder().name("Earthquake").type("Ground").damage(140).isCharged(true).build();
        Attacks attackDragonTail = Attacks.builder().name("Dragon Tail").type("Dragon").damage(15).isCharged(false).build();
        Attacks attackOutrage = Attacks.builder().name("Outrage").type("Dragon").damage(110).isCharged(true).build();
        Attacks attackThunderShock = Attacks.builder().name("Thunder Shock").type("Eletric").isCharged(false).damage(5).build();
        Attacks attackHyperBeam = Attacks.builder().name("Hyper Beam").type("Normal").damage(150).isCharged(true).build();
        Attacks attackWaterfall = Attacks.builder().name("Waterfall").type("Water").damage(16).isCharged(false).build();
        Attacks attackHydroPump = Attacks.builder().name("Hydro Pump").type("Water").damage(130).isCharged(true).build();

        attacksRepository.saveAll(List.of(attackFlameBurst, attackConfusion, attackPsystrike, attackWaterfall,
                attackEarthquake, attackHydroPump, attackDragonTail, attackOutrage, attackThunderShock, attackHyperBeam));

        Pokemon pokemonPikachu = Pokemon.builder()
                .name("Pikachu")
                .type("Electric")
                .height(0.72)
                .firstAttack("ThunderShock")
                .secondAttack("FlameBurst")
                .nr_Attack(4)
                .nr_Defense(8)
                .nr_Hp(65)
                .level(2)
                .build();
        Pokemon pokemonCharmander = Pokemon.builder()
                .name("Charmander")
                .type("fire")
                .height(0.48)
                .firstAttack("Outrage")
                .secondAttack("Earthquake")
                .nr_Attack(14)
                .nr_Defense(21)
                .nr_Hp(13)
                .level(3)
                .build();
        Pokemon pokemonCharmeleon = Pokemon.builder()
                .name("Charmeleon")
                .type("fire")
                .height(1.10)
                .firstAttack("FlameBurst")
                .secondAttack("HyperBeam")
                .nr_Attack(14)
                .nr_Defense(12)
                .nr_Hp(83)
                .level(23)
                .build();
        Pokemon pokemonDragonite = Pokemon.builder()
                .name("Dragonite")
                .type("Dragon")
                .height(1.81)
                .firstAttack("DragonTail")
                .secondAttack("Outrage")
                .nr_Attack(10)
                .nr_Defense(12)
                .nr_Hp(167)
                .level(3)
                .build();
        Pokemon pokemonMewtwo = Pokemon.builder()
                .name("Mewtwo")
                .type("Psychic")
                .height(15.49)
                .firstAttack("Confusion")
                .secondAttack("Psystrike")
                .nr_Attack(15)
                .nr_Defense(15)
                .nr_Hp(177)
                .level(50)
                .build();
        Pokemon pokemonMelmetal = Pokemon.builder()
                .name("Melmetal")
                .type("Steel")
                .height(2.5)
                .firstAttack("ThunderShock")
                .secondAttack("HyperBeam")
                .nr_Attack(13)
                .nr_Defense(15)
                .nr_Hp(211)
                .level(50)
                .build();
        Pokemon pokemonSlaking = Pokemon.builder()
                .name("Slaking")
                .type("Normal")
                .height(1.81)
                .firstAttack("Waterfall")
                .secondAttack("Earthquake")
                .nr_Attack(13)
                .nr_Defense(13)
                .nr_Hp(214)
                .level(35)
                .build();
        Pokemon pokemonRhyperior = Pokemon.builder()
                .name("Rhyperior")
                .type("Groud/Rock")
                .height(2.26)
                .firstAttack("Waterfall")
                .secondAttack("Earthquake")
                .nr_Attack(15)
                .nr_Defense(15)
                .nr_Hp(194)
                .level(35)
                .build();
        Pokemon pokemonGyarados = Pokemon.builder()
                .name("Gyarados")
                .type("Water/Flying")
                .height(5.91)
                .firstAttack("Waterfall")
                .secondAttack("HydroPump")
                .nr_Attack(14)
                .nr_Defense(14)
                .nr_Hp(171)
                .level(32)
                .build();
        Pokemon pokemonSalamance = Pokemon.builder()
                .name("Salamance")
                .type("Dragon/Flying")
                .height(1.77)
                .firstAttack("DragonTail")
                .secondAttack("Outrage")
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
                .type("Pokeball")
                .quantity("20")
                .build();

        Itens item4 = Itens.builder()
                .name("Great Ball")
                .description("A high-performance Poké Ball with a higher catch rate than a standard Poké Ball.")
                .type("Pokeball")
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

        PokemonAttacks pokemonAttack1 = PokemonAttacks.builder().attacks(attackConfusion).pokemon(pokemonMewtwo).build();
        PokemonAttacks pokemonAttack2 = PokemonAttacks.builder().attacks(attackOutrage).pokemon(pokemonDragonite).build();
        PokemonAttacks pokemonAttack3 = PokemonAttacks.builder().attacks(attackDragonTail).pokemon(pokemonSalamance).build();
        PokemonAttacks pokemonAttack4 = PokemonAttacks.builder().attacks(attackHydroPump).pokemon(pokemonGyarados).build();
        PokemonAttacks pokemonAttack5 = PokemonAttacks.builder().attacks(attackEarthquake).pokemon(pokemonRhyperior).build();
        PokemonAttacks pokemonAttack6 = PokemonAttacks.builder().attacks(attackEarthquake).pokemon(pokemonSlaking).build();
        PokemonAttacks pokemonAttack7 = PokemonAttacks.builder().attacks(attackFlameBurst).pokemon(pokemonCharmander).build();
        PokemonAttacks pokemonAttack8 = PokemonAttacks.builder().attacks(attackFlameBurst).pokemon(pokemonCharmeleon).build();
        PokemonAttacks pokemonAttack9 = PokemonAttacks.builder().attacks(attackThunderShock).pokemon(pokemonPikachu).build();
        PokemonAttacks pokemonAttack10 = PokemonAttacks.builder().attacks(attackHyperBeam).pokemon(pokemonMelmetal).build();

        pokemonAttacksRepository.saveAll(List.of(pokemonAttack1, pokemonAttack2, pokemonAttack3, pokemonAttack4, pokemonAttack5,
                pokemonAttack6, pokemonAttack7, pokemonAttack8, pokemonAttack9, pokemonAttack10));


        Gym gym1 = Gym.builder()
                .name("Campinho de futebol vila augusta")
                .location("-7.1873, 116.0633")
                .insignia("Campinho de futebol vila augusta")
                .build();

        Gym gym2 = Gym.builder()
                .name("Boneco da praça")
                .location("3.5523, 73.9604")
                .insignia("Boneco da praça")
                .build();
        Gym gym3 = Gym.builder()
                .name("Garoto dentuço")
                .location("-76.7824, 31.4816")
                .insignia("Garoto dentuço")
                .build();
        Gym gym4 = Gym.builder()
                .name("Escola Jodé Scaramelli")
                .location("88.4242, 138.2667")
                .insignia("Escola Jodé Scaramelli")
                .build();
        Gym gym5 = Gym.builder()
                .name("Grafite O Filme Antigo")
                .location("-63.1892, -15.2891")
                .insignia("Grafite O Filme Antigo")
                .build();
        Gym gym6 = Gym.builder()
                .name("Pintura MUlher Verde Na Ginástica")
                .location("73.7294, -114.9508")
                .insignia("Pintura MUlher Verde Na Ginástica")
                .build();
        Gym gym7 = Gym.builder()
                .name("Escola de Musica Nelsom")
                .location("87.8489, -58.8601")
                .insignia("Escola de Musica Nelsom")
                .build();
        Gym gym8 = Gym.builder()
                .name("Graffiti bom dia em")
                .location("-30.9992, 151.8908")
                .insignia("Graffiti bom dia em")
                .build();
        Gym gym9 = Gym.builder()
                .name("Padre Anchieta")
                .location("28.4821, 23.6005")
                .insignia("Padre Anchieta")
                .build();
        Gym gym10 = Gym.builder()
                .name("Bolo de Aniversário")
                .location("-20.0977, 164.1923")
                .insignia("Bolo de Aniversário")
                .build();
        gymRepository.saveAll(List.of(gym1, gym2, gym3, gym4, gym5, gym6, gym7, gym8, gym9, gym10));


        Trainers trainer1 = Trainers.builder().insignias("Bolo de aniversário").level(32).name("koffee").user(user1).build();
        Trainers trainer2 = Trainers.builder().insignias("Padre Anchieta").level(22).name("Ritzoff").user(user2).build();
        Trainers trainer3 = Trainers.builder().insignias("Graffiti bom dia em").level(32).name("Succinctmann").user(user3).build();
        Trainers trainer4 = Trainers.builder().insignias("Escola de Musica Nelsom").level(32).name("Viasuccinct").user(user4).build();
        Trainers trainer5 = Trainers.builder().insignias("Pintura MUlher Verde Na Ginástica").level(32).name("Exuberantlitz").user(user5).build();
        Trainers trainer6 = Trainers.builder().insignias("Grafite O Filme Antigo").level(32).name("Vaisquawk").user(user6).build();
        Trainers trainer7 = Trainers.builder().insignias("Escola Jodé Scaramelli").level(32).name("ExusquWknt").user(user7).build();
        Trainers trainer8 = Trainers.builder().insignias("Garoto dentuço").level(32).name("Amuckney").user(user8).build();
        Trainers trainer9 = Trainers.builder().insignias("Boneco da praça").level(32).name("MillyAffirmed").user(user9).build();
        Trainers trainer10 = Trainers.builder().insignias("Campinho de futebol vila augusta").level(32).name("HabmiaOwal").user(user10).build();

        trainersRepository.saveAll(List.of(trainer1,trainer2, trainer3, trainer4, trainer5, trainer6, trainer7, trainer8, trainer9, trainer10));

        TrainersGym trainersGym1 = TrainersGym.builder().trainers(trainer1).gym(gym1).build();
        TrainersGym trainersGym2 = TrainersGym.builder().trainers(trainer2).gym(gym2).build();
        TrainersGym trainersGym3 = TrainersGym.builder().trainers(trainer3).gym(gym3).build();
        TrainersGym trainersGym4 = TrainersGym.builder().trainers(trainer4).gym(gym4).build();
        TrainersGym trainersGym5 = TrainersGym.builder().trainers(trainer5).gym(gym5).build();
        TrainersGym trainersGym6 = TrainersGym.builder().trainers(trainer6).gym(gym6).build();
        TrainersGym trainersGym7 = TrainersGym.builder().trainers(trainer7).gym(gym7).build();
        TrainersGym trainersGym8 = TrainersGym.builder().trainers(trainer8).gym(gym8).build();
        TrainersGym trainersGym9 = TrainersGym.builder().trainers(trainer9).gym(gym9).build();
        TrainersGym trainersGym10 = TrainersGym.builder().trainers(trainer10).gym(gym10).build();

        trainersGymRepository.saveAll(List.of(trainersGym1, trainersGym2, trainersGym3, trainersGym4, trainersGym5, trainersGym6,
                trainersGym7, trainersGym8, trainersGym9,trainersGym10));

        Capture capture1 = Capture.builder()
                .date_capture(LocalDate.now())
                .capture_location("48.3124, 173.5974")
                .pokemon(pokemonCharmander)
                .trainers(trainer1)
                .build()
                ;
        Capture capture2 = Capture.builder()
                .date_capture(LocalDate.now())
                .capture_location("-28.8534, 167.2264")
                .pokemon(pokemonMewtwo)
                .trainers(trainer2)
                .build()
                ;

        Capture capture3 = Capture.builder()
                .date_capture(LocalDate.now())
                .capture_location("-52.2805, 114.7877")
                .pokemon(pokemonDragonite)
                .trainers(trainer3)
                .build()
                ;

        Capture capture4 = Capture.builder()
                .date_capture(LocalDate.now())
                .capture_location("-81.0158, 132.0772")
                .pokemon(pokemonCharmander)
                .trainers(trainer4)
                .build()
                ;

        Capture capture5 = Capture.builder()
                .date_capture(LocalDate.now())
                .capture_location("-73.6828, 53.2301")
                .pokemon(pokemonRhyperior)
                .trainers(trainer5)
                .build()
                ;

        Capture capture6 = Capture.builder()
                .date_capture(LocalDate.now())
                .capture_location("24.6808, 61.9504")
                .pokemon(pokemonSalamance)
                .trainers(trainer6)
                .build()
                ;

        Capture capture7 = Capture.builder()
                .date_capture(LocalDate.now())
                .capture_location("-21.2315, -6.2355")
                .pokemon(pokemonSlaking)
                .trainers(trainer7)
                .build()
                ;

        Capture capture8 = Capture.builder()
                .date_capture(LocalDate.now())
                .capture_location("-22.0012, 80.7671")
                .pokemon(pokemonGyarados)
                .trainers(trainer8)
                .build()
                ;

        Capture capture9 = Capture.builder()
                .date_capture(LocalDate.now())
                .capture_location("-75.4812, 75.5511")
                .pokemon(pokemonPikachu)
                .trainers(trainer9)
                .build()
                ;

        Capture capture10 = Capture.builder()
                .date_capture(LocalDate.now())
                .capture_location("-32.1050, -98.5611")
                .pokemon(pokemonSlaking)
                .trainers(trainer10)
                .build()
                ;

        captureRepository.saveAll(List.of(capture1, capture2, capture3, capture4, capture5, capture6, capture7, capture8, capture9, capture10));

        Pokestops pokestop1 = Pokestops.builder()
                .name("Parque infantil")
                .description("Local de diversão para crianças")
                .location("-34.8628, 25.7879")
                .build();
        Pokestops pokestop2 = Pokestops.builder()
                .name("Grafite Camaro SS")
                .location("-40.7838, -135.9595")
                .build();
        Pokestops pokestop3 = Pokestops.builder()
                .name("Grafite Carro Clássico")
                .location("76.9943, -60.4033")
                .build();
        Pokestops pokestop4 = Pokestops.builder()
                .name("Campo de futebol")
                .description("Campo para prática de esportes")
                .location("-19.3136, -102.4933")
                .build();
        Pokestops pokestop5 = Pokestops.builder()
                .name("Quadra de Esportes Fatto")
                .description("Quadra para prática de esportes")
                .location("27.9153, -127.5725")
                .build();
        Pokestops pokestop6 = Pokestops.builder()
                .name("Playground Fatto")
                .description("Area de playground infantil")
                .location("-22.2831, 59.6390")
                .build();
        Pokestops pokestop7 = Pokestops.builder()
                .name("Caixa D'Água Fatto")
                .description("Reservatório de água")
                .location("58.3475, -32.9606")
                .build();
        Pokestops pokestop8 = Pokestops.builder()
                .name("Brasil: Vila Augusta")
                .description("Bandeira brasileira pintada de forma comemorativa")
                .location("13.0611, 95.4967")
                .build();
        Pokestops pokestop9 = Pokestops.builder()
                .name("O alien e o sorvete")
                .description("Pintura de um alien e varios sorvetes em muro")
                .location("3.2923, 6.1611")
                .build();
        Pokestops pokestop10 = Pokestops.builder()
                .name("Graffit Desanimado")
                .location("-73.5861, -87.0501")
                .build();

        pokestopsRepository.saveAll(List.of(pokestop1, pokestop2, pokestop3, pokestop4, pokestop5,
                pokestop6, pokestop7, pokestop8, pokestop9, pokestop10));


        PokestopsTrainers pokestopsTrainers1 = PokestopsTrainers.builder().pokestops(pokestop1).trainers(trainer1).build();
        PokestopsTrainers pokestopsTrainers2 = PokestopsTrainers.builder().pokestops(pokestop2).trainers(trainer2).build();
        PokestopsTrainers pokestopsTrainers3 = PokestopsTrainers.builder().pokestops(pokestop3).trainers(trainer3).build();
        PokestopsTrainers pokestopsTrainers4 = PokestopsTrainers.builder().pokestops(pokestop4).trainers(trainer4).build();
        PokestopsTrainers pokestopsTrainers5 = PokestopsTrainers.builder().pokestops(pokestop5).trainers(trainer5).build();
        PokestopsTrainers pokestopsTrainers6 = PokestopsTrainers.builder().pokestops(pokestop6).trainers(trainer6).build();
        PokestopsTrainers pokestopsTrainers7 = PokestopsTrainers.builder().pokestops(pokestop7).trainers(trainer7).build();
        PokestopsTrainers pokestopsTrainers8 = PokestopsTrainers.builder().pokestops(pokestop8).trainers(trainer8).build();
        PokestopsTrainers pokestopsTrainers9 = PokestopsTrainers.builder().pokestops(pokestop9).trainers(trainer9).build();
        PokestopsTrainers pokestopsTrainers10 = PokestopsTrainers.builder().pokestops(pokestop10).trainers(trainer10).build();

        pokestopsTrainersRepository.saveAll(List.of(pokestopsTrainers1,pokestopsTrainers2, pokestopsTrainers3 ,pokestopsTrainers4, pokestopsTrainers5,
                pokestopsTrainers6, pokestopsTrainers7,pokestopsTrainers8,pokestopsTrainers9, pokestopsTrainers10));

        ItensPokestops itemPokestops1 = ItensPokestops.builder().itens(item1).pokestops(pokestop1).build();
        ItensPokestops itemPokestops2 = ItensPokestops.builder().itens(item2).pokestops(pokestop2).build();
        ItensPokestops itemPokestops3 = ItensPokestops.builder().itens(item3).pokestops(pokestop3).build();
        ItensPokestops itemPokestops4 = ItensPokestops.builder().itens(item4).pokestops(pokestop4).build();
        ItensPokestops itemPokestops5 = ItensPokestops.builder().itens(item5).pokestops(pokestop5).build();
        ItensPokestops itemPokestops6 = ItensPokestops.builder().itens(item6).pokestops(pokestop6).build();
        ItensPokestops itemPokestops7 = ItensPokestops.builder().itens(item7).pokestops(pokestop7).build();
        ItensPokestops itemPokestops8 = ItensPokestops.builder().itens(item8).pokestops(pokestop8).build();
        ItensPokestops itemPokestops9 = ItensPokestops.builder().itens(item9).pokestops(pokestop9).build();
        ItensPokestops itemPokestops10 = ItensPokestops.builder().itens(item10).pokestops(pokestop10).build();

        itensPokestopsRepository.saveAll(List.of(itemPokestops1, itemPokestops2, itemPokestops3, itemPokestops4, itemPokestops5 ,itemPokestops6,
                itemPokestops7, itemPokestops8, itemPokestops9, itemPokestops10));

    }
}

