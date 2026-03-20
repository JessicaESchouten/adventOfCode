package com.adventofcode.aoc2025;

public class Dag02 extends Dag {

    private long antwoordEersteDeel = 0;
    private long antwoordTweedeDeel = 0;

    @Override
    protected String[] splitsRegel(String regel) {
        return regel.split(",");
    }

    @Override
    protected void verwerkPatroon(String patroon) {
        Bereik bereik = Bereik.parse(patroon);
        DeelAntwoorden sommen = somHerhalingen(bereik);
        antwoordEersteDeel += sommen.deel1();
        antwoordTweedeDeel += sommen.deel2();
    }

    long berekenAntwoordEersteDeel() {
        return antwoordEersteDeel;
    }

    long berekenAntwoordTweedeDeel() {
        return antwoordTweedeDeel;
    }

    private static DeelAntwoorden somHerhalingen(Bereik bereik) {
        long somDeel1 = 0;
        long somDeel2 = 0;

        for (long code = bereik.begin(); ; code++) {
            String patroon = Long.toString(code);
            if (isHerhalingExactTweeKeer(patroon)) somDeel1 += code;
            if (isHerhalingMinstensTweeKeer(patroon)) somDeel2 += code;
            if (code == bereik.einde()) break;
        }

        return new DeelAntwoorden(somDeel1, somDeel2);
    }

    private static boolean isHerhalingExactTweeKeer(String patroon) {
        int lengte = patroon.length();
        if (lengte % 2 != 0) return false;

        String links = patroon.substring(0, lengte / 2);
        String rechts = patroon.substring(lengte / 2);
        return links.equals(rechts);
    }

    private static boolean isHerhalingMinstensTweeKeer(String patroon) {
        String verdubbeld = patroon + patroon;
        return verdubbeld.substring(1, verdubbeld.length() - 1).contains(patroon);
    }

    private record DeelAntwoorden(long deel1, long deel2) {}

    private record Bereik(long begin, long einde) {
        static Bereik parse(String patroon) {
            int index = patroon.indexOf('-');
            long begin = Long.parseLong(patroon.substring(0, index));
            long einde = Long.parseLong(patroon.substring(index + 1));
            return new Bereik(begin, einde);
        }
    }
}
