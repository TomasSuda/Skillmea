import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        // vytvaram objekt na nahravanie cisel do aplikacie
        Scanner scanner = new Scanner(System.in);

        //definujem pole do ktoreho si ukladam vsetky pokusz, aby som zvalidoval uz pouzite
        int[] zaznamPouzitychPozicii = new int[9];

        //vypisem hracie pole s privitanim
        System.out.println("Vytajce v hre piskorky chujlovia");
        char[][] tictactoe = new char[][]{
                {'*', '|', '*', '|', '*'},
                {'*', '|', '*', '|', '*'},
                {'*', '|', '*', '|', '*'}};

        vypisHraciuPlochu(tictactoe);

        // na zaklade zadefinovanej premennej pokus vyhodnocujem ktory hrac je na rade a ci je remiza
        int pokus = 1;
        while (true) {
            // vyzva pre hraca
            System.out.println("Hrac "+ (2 -(pokus % 2)) +" zadaj pozition vlozenim cisla od 1 - 9");

            // prevodnik ze zadane cisla, aku ma suradnicu na poli. Plus kontroly
            int position = vlozenaSuradnica(scanner, zaznamPouzitychPozicii);

            // zapisujem do pola novy pokus
            zaznamPouzitychPozicii[pokus - 1] = position;

            //tu si zoberiem suradnicu z prevodnika a dam ju do dvoch premien a nasledne
            // zavolam funkciu kde sa vezme hracia plocha a zapise znak ktory je v poradi
            int[] suradnica = znaceniePiskvorok(position);
            int a = suradnica[0];
            int b = suradnica[1];
            char krizikGulka = vratZnak(pokus);

            //zapis znaku do hracieho pola
            tictactoe = zadefinovanePolePiskorok(a, b, tictactoe, krizikGulka);

            //vypisane hracej plochy po update
            vypisHraciuPlochu(tictactoe);

            //tu len vyhodnotim vyhercu
            if (kukniVysledok(tictactoe, krizikGulka)){
                    System.out.println("Gratulujem hrac "+ (2 -(pokus % 2)) +", si vyhral mutigeneracne bohatstvo");
                break;
            };

            // ak prejde 9 pokusov a nikto nevyhra tak sa zobrazi remiza
            if (pokus == 9) {
                System.out.println("remizka bicis");
                break;
            }
            //po kaznej iteracii sa navysi premena pokus
            pokus++;
        }
    }

    // funkcie

    //tato validuje vstup od hraca
    public static int vlozenaSuradnica(Scanner scanner, int[] zoznamPokuusov) throws Exception {
        while (true) {
            int number = 0;
            try {
                number = scanner.nextInt();

                if (number > 9 || number < 1) {
                    throw new Exception("Cislo je mimo rozsah hracieho pola");
                }

                for (int i = 0; i < zoznamPokuusov.length; i++) {
                    if (zoznamPokuusov[i] == number) {
                        throw new Exception("To uz bolo");
                    }
                }
                return number;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }
    }

    //tato metodka vypise hracie pole
    public static void vypisHraciuPlochu(char[][] plocha) {
        for (int i = 0; i < plocha.length; i++) {
            for (int j = 0; j < plocha[i].length; j++) {
                System.out.print(plocha[i][j] + " ");
            }
            System.out.println();
        }
    }

    // tato meto sluzi ako prevodnik na zadane pozicie
    public static int[] znaceniePiskvorok(int position) {
        int[] pole;
        return switch (position) {
            case 1 -> pole = new int[]{0, 0};
            case 2 -> pole = new int[]{0, 2};
            case 3 -> pole = new int[]{0, 4};
            case 4 -> pole = new int[]{1, 0};
            case 5 -> pole = new int[]{1, 2};
            case 6 -> pole = new int[]{1, 4};
            case 7 -> pole = new int[]{2, 0};
            case 8 -> pole = new int[]{2, 2};
            case 9 -> pole = new int[]{2, 4};
            default -> pole = new int[]{10, 10};
        };
    }

    // metodka upravi hracie pole po zadani pozadovanej pozicii
    public static char[][] zadefinovanePolePiskorok(int a, int b, char[][] tictak, char znak) {
        tictak[a][b] = znak;
        return tictak;
    }

    // metodka uda aky znak sa ma zapisat do hracieho pola, cvicenie ternarneho operatora
    public static char vratZnak(int pokus) {
        return pokus % 2 == 0 ? 'X' : '0';
    }

    // kotrolujeme ci dakto vyhral
    public static boolean kukniVysledok(char tictac[][], char znak) {
        for (int i = 0; i < tictac.length; i++) {
                if (tictac[i][0] == znak && tictac[i][2] == znak && tictac[i][4] == znak){
                    return true;
                }
        }
        for (int i = 0; i < 5; i+=2) {
            if (tictac[0][i] == znak && tictac[1][i] == znak && tictac[2][i] == znak){
                return true;
            }
        }
        if (tictac[0][0] == znak && tictac[1][2] == znak && tictac[2][4] == znak){
            return true;
        } else if (tictac[0][4] == znak && tictac[1][2] == znak && tictac[2][0] == znak){
            return true;
        }
        return false;
    }
}